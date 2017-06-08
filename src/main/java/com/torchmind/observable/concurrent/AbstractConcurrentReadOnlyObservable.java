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

import com.torchmind.observable.ReadOnlyObservable;
import com.torchmind.observable.listener.ChangeListener;
import com.torchmind.observable.utility.WeakCopyOnWriteSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/**
 * <p>Provides a basic thread safe implementation for observable properties.</p>
 *
 * <p>Note that this implementation relies upon {@link java.util.concurrent.CopyOnWriteArraySet} for
 * its internal registration of listeners. This implementation is the most suited for environments
 * which rarely register or remove listeners. Note, however, that other backing implementations may
 * be more suited for environments which rapidly alter the listener set.</p>
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
@ThreadSafe
public abstract class AbstractConcurrentReadOnlyObservable<V> implements ReadOnlyObservable<V> {

  private final Lock writeLock = new ReentrantLock();
  private final Set<ChangeListener<? super V>> listeners = new WeakCopyOnWriteSet<>();

  /**
   * Publishes a change to all listeners which are currently registered with this observable.
   */
  protected void publishChange(V oldValue, V newValue) {
    // in case the values are considered equal (either by being the same object in memory or by
    // being equal in value as indicated by their equals and hashCode methods), we are simply going
    // to ignore this call as third parties do not need to know about every call
    if (Objects.equals(oldValue, newValue)) {
      return;
    }

    this.listeners.forEach((l) -> l.onChange(this, oldValue, newValue));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void registerListener(@Nonnull ChangeListener<? super V> listener) {
    this.writeLock.lock();

    try {
      this.listeners.add(listener);
    } finally {
      this.writeLock.unlock();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeListener(@Nonnull ChangeListener<? super V> listener) {
    this.writeLock.lock();

    try {
      this.listeners.add(listener);
    } finally {
      this.writeLock.unlock();
    }
  }
}
