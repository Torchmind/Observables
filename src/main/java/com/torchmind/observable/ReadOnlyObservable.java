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
import javax.annotation.Nonnull;

/**
 * Represents a property of a certain complex (or possibly wrapped) type which is capable of
 * inheriting or share its value with another property as well as notifying third parties of changes
 * to this property.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface ReadOnlyObservable<V> {

  /**
   * Returns the value exposed by this observable.
   *
   * @throws IllegalStateException when the state of this observable does not permit the retrieval
   * of its value.
   */
  V get();

  /**
   * <p>Registers a new listener with this observable which is invoked whenever the value exposed
   * through this observable changes.</p>
   *
   * <p>When the passed listener is already registered with this observable at the time of the
   * method call, the call will be ignored and cause no modification of the observable state.</p>
   */
  void registerListener(@Nonnull ChangeListener<? super V> listener);

  /**
   * <p>Removes a previously registered from this observable and thus prevents it from receiving
   * future updates from this observable when its exposed value changes.</p>
   *
   * <p>When the passed listener is not yet registered with this observable, the call will be
   * ignored and cause no modification to the observable state.</p>
   */
  void removeListener(@Nonnull ChangeListener<? super V> listener);
}
