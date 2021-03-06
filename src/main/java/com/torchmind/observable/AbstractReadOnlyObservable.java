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
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.WeakHashMap;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * <p>Provides the most basic implementation for observables.</p>
 *
 * <p>This type is the recommended entry point for developers who wish to create a read-only value
 * based on complex logic and houses the necessary logic for registering and calling change
 * listeners.</p>
 *
 * <p>Note that this implementation stores subscribed listeners in a weak set. As such, instances
 * which are held by no other object will automatically be removed from the list without leaking
 * any memory. Due to this implementation, manual disposal is not necessary.</p>
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public abstract class AbstractReadOnlyObservable<V> implements ReadOnlyObservable<V> {

  private final Set<ChangeListener<? super V>> listeners = Collections
      .newSetFromMap(new WeakHashMap<>());

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
  public void registerListener(@NonNull ChangeListener<? super V> listener) {
    this.listeners.add(listener);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeListener(@NonNull ChangeListener<? super V> listener) {
    this.listeners.remove(listener);
  }
}
