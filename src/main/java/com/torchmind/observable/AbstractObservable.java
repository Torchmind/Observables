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

package com.torchmind.observable;

import com.torchmind.observable.listener.ChangeListener;
import com.torchmind.observable.listener.ValidationListener;
import java.util.HashSet;
import java.util.Set;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * Provides a basic writable observable implementation which handles all the necessary logic for
 * bindings and value storage.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public abstract class AbstractObservable<V> extends AbstractReadOnlyObservable<V> implements
    Observable<V> {

  private final ValidationListener<V> validationListener;
  private final Set<Observable<V>> bidirectionalBinding = new HashSet<>();
  private V value;
  private boolean invalidated;
  private final ChangeListener<V> bindingListener = (ChangeListener<V>) (property, oldValue, newValue) -> {
    // in case our invalidation flag is set, we have already received this update and probably
    // discovered a circular reference between this observable and the caller and thus do not need
    // to actually perform this update
    if (this.invalidated) {
      return;
    }

    // otherwise we'll simply set the invalidation flag, update the value and call our subscribers
    // before turning off the invalidation flag once again to complete the cycle
    this.setInternal(newValue);
  };
  private ReadOnlyObservable<? extends V> binding;

  public AbstractObservable(
      @Nullable ValidationListener<V> validationListener, V value) {
    this.validationListener = validationListener;
    this.value = value;
  }

  public AbstractObservable(V value) {
    this(null, value);
  }

  public AbstractObservable() {
    this(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public V get() {
    return this.value;
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
    // even when we receive updates from a binding, we'll validate whether this value is valid
    // Note, however, that this may cause unexpected behavior as the initial caller may not properly
    // handle the exception
    if (this.validationListener != null) {
      this.validationListener.validate(this, value);
    }

    // enable the invalidation flag to prevent infinite recursion in bidirectional calls and
    // actually perform the update
    this.invalidated = true;

    V oldValue = this.value;
    this.value = value;

    try {
      this.publishChange(oldValue, value);
    } finally {
      // ensure our local state is changed regardless of the call outcome so we do not enter an
      // entirely invalid state when people fail to respect the JavaDoc notes on this topic
      this.invalidated = false;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isValid() {
    return !this.invalidated;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void bindTo(@NonNull ReadOnlyObservable<? extends V> observable) {
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
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void bindBidirectionallyTo(@NonNull Observable<V> observable) {
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
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBound() {
    return this.binding != null || !this.bidirectionalBinding.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBoundTo(@NonNull ReadOnlyObservable<? extends V> observable) {
    return this.binding == observable;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBoundBidirectionallyTo(@NonNull Observable<V> observable) {
    return this.binding == null && this.bidirectionalBinding.contains(observable);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBoundBidirectionally() {
    return this.binding == null && !this.bidirectionalBinding.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unbind() {
    if (this.binding == null) {
      throw new IllegalStateException(
          "Cannot unbind: No unidirectional binding relationship present");
    }

    this.binding.removeListener(this.bindingListener);
    this.binding = null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unbindAll() {
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
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unbindBidirectional(@NonNull Observable<V> observable) {
    observable.removeListener(this.bindingListener);
    this.bidirectionalBinding.remove(observable);

    // remove the other side of the relationship if this relationship still exists (this check is
    // performed to prevent infinite recursions and unexpected exceptions)
    if (observable.isBoundBidirectionallyTo(this)) {
      observable.unbindBidirectional(this);
    }
  }
}
