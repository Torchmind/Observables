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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Provides a generic binding which may be used in order to define property values based on more
 * complex logic and expressions.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface Binding<V> extends ReadOnlyObservable<V> {

  /**
   * <p>Creates a binding using the passed supplier and list of dependencies.</p>
   *
   * <p>Note that this method requires manual implementation of the respective binding logic. For
   * most cases, however, the static methods provided by this interface do suffice however and
   * require far less manually programmed logic.</p>
   */
  @NonNull
  static <V> Binding<V> create(@NonNull Supplier<V> supplier,
      ReadOnlyObservable<?>... observables) {
    return new AbstractBinding<V>(new HashSet<>(Arrays.asList(observables))) {
      @Override
      protected V compute() {
        return supplier.get();
      }
    };
  }

  /**
   * <p>Maps an original value to another using complex logic.</p>
   *
   * <p>Note that the provided mapping function must be null-safe as no attempts will be made to
   * strip or special case null values provided by the supplied observable.</p>
   */
  @NonNull
  static <I, O> Binding<O> map(@NonNull Function<I, O> function,
      @NonNull ReadOnlyObservable<I> observable) {
    return create(() -> function.apply(observable.get()), observable);
  }

  /**
   * Creates a binding which is equal in functionality to the ternary operator (e.g. when the
   * predicate evaluates to true, the first observable value is returned, otherwise the second is
   * returned).
   */
  @NonNull
  static <V> Binding<V> ternary(@NonNull BiPredicate<V, V> predicate,
      @NonNull ReadOnlyObservable<? extends V> observable1,
      @NonNull ReadOnlyObservable<? extends V> observable2) {
    return create(() -> {
      V value1 = observable1.get();
      V value2 = observable2.get();

      return predicate.test(value1, value2) ? value1 : value2;
    }, observable1, observable2);
  }

  /**
   * Creates a binding which is equal in functionality to the ternary operator (e.g. when the
   * supplier evaluates to true, the first value is returned, otherwise the second is returned).
   */
  @NonNull
  static <V> Binding<V> ternary(@NonNull BooleanSupplier supplier, V value1, V value2) {
    return create(() -> supplier.getAsBoolean() ? value1 : value2);
  }

  /**
   * Converts the logic of this binding into a standard Java supplier.
   */
  @NonNull
  Supplier<V> asSupplier();

  /**
   * Retrieves a list of observables that this binding relies upon.
   */
  @NonNull
  Set<ReadOnlyObservable<?>> getDependencies();

  /**
   * Forces this binding to re-evaluate its state based on the properties of its dependencies.
   */
  void invalidate();
}
