/*
 * Copyright 2017 Johannes Donath <johannesd@torchmind.com>
 * and other copyright owners as documented in the project's IP log.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.torchmind.observable.concurrent;

import com.torchmind.observable.Observable;
import com.torchmind.observable.ReadOnlyObservable;
import com.torchmind.observable.listener.ChangeListener;
import com.torchmind.observable.listener.ValidationListener;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * Provides a blocking abstract implementation of the observable specification.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public abstract class AbstractBlockingObservable<V> extends
    AbstractConcurrentReadOnlyObservable<V> implements Observable<V> {

  private final ReadWriteLock lock;
  private final ValidationListener<V> validationListener;
  private final Set<Observable<V>> bidirectionalBinding = new HashSet<>();
  private V value;
  private final ChangeListener<V> bindingListener = (ChangeListener<V>) (property, oldValue, newValue) -> {
    // in case our invalidation flag is set, we have already received this update and probably
    // discovered a circular reference between this observable and the caller and thus do not need
    // to actually perform this update
    if (!this.isValid()) {
      return;
    }

    // otherwise we'll simply set the invalidation flag, update the value and call our subscribers
    // before turning off the invalidation flag once again to complete the cycle
    this.setInternal(newValue);
  };
  private ReadOnlyObservable<? extends V> binding;

  public AbstractBlockingObservable(@Nullable ValidationListener<V> validationListener, V value,
      boolean fair) {
    this.value = value;
    this.validationListener = validationListener;
    this.lock = new ReentrantReadWriteLock(fair);
  }

  public AbstractBlockingObservable(@Nullable ValidationListener<V> validationListener, V value) {
    this(validationListener, value, false);
  }

  public AbstractBlockingObservable(V value) {
    this(null, value);
  }

  public AbstractBlockingObservable() {
    this(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public V get() {
    this.lock.readLock().lock();

    try {
      return this.value;
    } finally {
      this.lock.readLock().unlock();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void set(V value) {
    if (this.binding != null) {
      throw new IllegalStateException(
          "Cannot change observable: Value is bound to another observable");
    }

    this.setInternal(value);
  }

  /**
   * Provides an internal setter for the purposes of skipping state sanity checks when necessary.
   */
  private void setInternal(V value) {
    this.lock.writeLock().lock();

    try {
      // even when we receive updates from a binding, we'll validate whether this value is valid
      // Note, however, that this may cause unexpected behavior as the initial caller may not properly
      // handle the exception
      if (this.validationListener != null) {
        this.validationListener.validate(this, value);
      }

      V oldValue = this.value;
      this.value = value;

      this.publishChange(oldValue, value);
    } finally {
      this.lock.writeLock().unlock();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isValid() {
    // generally we will consider the observable invalid as long as its write lock is actively being
    // used by another thread (or possibly the same thread) as it will be lifted after the changes
    // have been published - Note that this step makes this operation slightly more expensive than
    // its non thread safe counterparts
    if (this.lock.writeLock().tryLock()) {
      this.lock.writeLock().unlock();

      return true;
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void bindTo(@NonNull ReadOnlyObservable<? extends V> observable) {
    this.lock.writeLock().lock();

    try {
      // if we are already bound to the passed observable we will simply ignore this call to avoid
      // double testing (nor is an update necessary)
      if (this.binding == observable) {
        return;
      }

      if (this.binding != null || !this.bidirectionalBinding.isEmpty()) {
        throw new IllegalStateException(
            "Cannot bind to observable: Already in another binding relationship");
      }

      // register the binding locally (to ensure it stays loaded with weak registrations) and register
      // our local change listener
      this.binding = observable;
      observable.registerListener(this.bindingListener);

      // assume the value of the passed observable as part of the registration process to emulate the
      // effects of changed values
      this.setInternal(observable.get());
    } finally {
      this.lock.writeLock().unlock();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void bindBidirectionallyTo(@NonNull Observable<V> observable) {
    this.lock.writeLock().lock();

    try {
      if (this.binding != null) {
        throw new IllegalStateException(
            "Cannot bind to observable: Already in another binding relationship");
      }

      // if we already have a binding relationship with the passed observable we will simply ignore
      // this call as we are probably dealing with the recursive call initiated by this implementation
      if (this.bidirectionalBinding.contains(observable)) {
        return;
      }

      // register the binding locally (to ensure it stays loaded with weak registrations) and register
      // our local change listener
      this.bidirectionalBinding.add(observable);
      observable.registerListener(this.bindingListener);

      // assume the value of the passed observable as part of the registration process to emulate the
      // effects of changed values if the binding was initiated by this observable
      if (!observable.isBoundBidirectionallyTo(this)) {
        this.setInternal(observable.get());
      }

      // instruct the other side to create the respective mirror of this relationship on their side
      observable.bindBidirectionallyTo(this);
    } finally {
      this.lock.writeLock().unlock();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBound() {
    this.lock.readLock().lock();

    try {
      return this.binding != null || !this.bidirectionalBinding.isEmpty();
    } finally {
      this.lock.readLock().unlock();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBoundTo(@NonNull ReadOnlyObservable<? extends V> observable) {
    this.lock.readLock().lock();

    try {
      return this.binding == observable;
    } finally {
      this.lock.readLock().unlock();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBoundBidirectionallyTo(@NonNull Observable<V> observable) {
    this.lock.readLock().lock();

    try {
      return this.binding == null && this.bidirectionalBinding.contains(observable);
    } finally {
      this.lock.readLock().unlock();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBoundBidirectionally() {
    this.lock.readLock().lock();

    try {
      return this.binding == null && !this.bidirectionalBinding.isEmpty();
    } finally {
      this.lock.readLock().unlock();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unbind() {
    this.lock.readLock().lock();

    try {
      if (this.binding == null) {
        throw new IllegalStateException(
            "Cannot unbind: No unidirectional binding relationship present");
      }

      this.binding.removeListener(this.bindingListener);
      this.binding = null;
    } finally {
      this.lock.readLock().unlock();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unbindAll() {
    this.lock.writeLock().lock();

    try {
      if (this.binding == null && this.bidirectionalBinding.isEmpty()) {
        throw new IllegalStateException("Cannot unbind: No binding relationships present");
      }

      // remove unidirectional bindings (if any) as a present binding implies that we aren't dealing
      // with any bidirectional bindings either
      if (this.binding != null) {
        this.binding.removeListener(this.bindingListener);
        this.binding = null;
      } else {
        new HashSet<>(this.bidirectionalBinding).forEach(this::unbindBidirectional);
      }
    } finally {
      this.lock.writeLock().unlock();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unbindBidirectional(@NonNull Observable<V> observable) {
    this.lock.writeLock().lock();

    try {
      observable.removeListener(this.bindingListener);
      this.bidirectionalBinding.remove(observable);

      // remove the other side of the relationship if this relationship still exists (this check is
      // performed to prevent infinite recursions and unexpected exceptions)
      if (observable.isBoundBidirectionallyTo(this)) {
        observable.unbindBidirectional(this);
      }
    } finally {
      this.lock.writeLock().unlock();
    }
  }
}
