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

package com.torchmind.observable.binding;

import com.torchmind.observable.ReadOnlyObservable;
import com.torchmind.observable.primitive.ReadOnlyDoubleObservable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.function.DoubleSupplier;
import javax.annotation.Nonnull;

/**
 * Provides a base to double valued bindings.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface DoubleBinding extends NumberBinding<Double>, ReadOnlyDoubleObservable {

  /**
   * <p>Creates a binding using the passed supplier and list of dependencies.</p>
   *
   * <p>Note that this method requires manual implementation of the respective binding logic. For
   * most cases, however, the static methods provided by this interface do suffice however and
   * require far less manually programmed logic.</p>
   */
  @Nonnull
  static DoubleBinding create(@Nonnull DoubleSupplier supplier,
      ReadOnlyObservable<?>... observables) {
    return new AbstractDoubleBinding(new HashSet<>(Arrays.asList(observables))) {
      @Override
      protected Double compute() {
        return supplier.getAsDouble();
      }
    };
  }
}
