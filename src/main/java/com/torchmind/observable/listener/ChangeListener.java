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

package com.torchmind.observable.listener;

import com.torchmind.observable.ReadOnlyObservable;
import javax.annotation.Nonnull;

/**
 * <p>Provides a base to listeners which wish to execute custom logic whenever an observed value
 * changes its state.</p>
 *
 * <p>Listeners are theoretically permitted to throw exceptions within the course of their
 * execution, however, it is not a recommended practice as it halts the process of notifying
 * other listeners of the change. Additionally, the exception will "bubble up" to the respective
 * caller which caused the update to occur.</p>
 *
 * <p>In short: Listeners are unable to reject a new value. Implementors should use invalidation
 * listeners instead for this purpose.</p>
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
@FunctionalInterface
public interface ChangeListener<V> {

  /**
   * Contains custom logic which is invoked when the observable, to which this listener is attached,
   * changes its exposed value.
   */
  void onChange(@Nonnull ReadOnlyObservable<? extends V> property, V oldValue, V newValue);
}
