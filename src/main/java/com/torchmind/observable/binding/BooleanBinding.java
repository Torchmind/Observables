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
import com.torchmind.observable.primitive.ReadOnlyBooleanObservable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.function.BooleanSupplier;
import javax.annotation.Nonnull;

/**
 * Provides a boolean based binding.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface BooleanBinding extends Binding<Boolean>, ReadOnlyBooleanObservable {

  /**
   * <p>Creates a binding using the passed supplier and list of dependencies.</p>
   *
   * <p>Note that this method requires manual implementation of the respective binding logic. For
   * most cases, however, the static methods provided by this interface do suffice however and
   * require far less manually programmed logic.</p>
   */
  @Nonnull
  static BooleanBinding create(@Nonnull BooleanSupplier supplier,
      ReadOnlyObservable<?>... observables) {
    return new AbstractBooleanBinding(new HashSet<>(Arrays.asList(observables))) {
      @Override
      protected Boolean compute() {
        return supplier.getAsBoolean();
      }
    };
  }

  /**
   * Combines the two supplied boolean bindings using a binary and operation.
   */
  @Nonnull
  static BooleanBinding and(@Nonnull BooleanBinding a, @Nonnull BooleanBinding b) {
    return new AbstractBooleanBinding(new HashSet<>(Arrays.asList(a, b))) {
      @Override
      protected Boolean compute() {
        return a.getValue() && b.getValue();
      }
    };
  }

  /**
   * Combines the two supplied boolean bindings using a binary not operation.
   */
  @Nonnull
  static BooleanBinding not(@Nonnull BooleanBinding a) {
    return new AbstractBooleanBinding(Collections.singleton(a)) {
      @Override
      protected Boolean compute() {
        return !a.getValue();
      }
    };
  }

  /**
   * Combines the two supplied boolean bindings using a binary or operation.
   */
  @Nonnull
  static BooleanBinding or(@Nonnull BooleanBinding a, @Nonnull BooleanBinding b) {
    return new AbstractBooleanBinding(new HashSet<>(Arrays.asList(a, b))) {
      @Override
      protected Boolean compute() {
        return a.getValue() || b.getValue();
      }
    };
  }

  /**
   * Creates a boolean binding with a static return value.
   */
  @Nonnull
  static BooleanBinding staticValue(boolean value) {
    return new AbstractBooleanBinding(Collections.emptySet()) {
      @Override
      protected Boolean compute() {
        return value;
      }
    };
  }

  /**
   * Combines the two supplied boolean bindings using a binary xor operation.
   */
  @Nonnull
  static BooleanBinding xor(@Nonnull BooleanBinding a, @Nonnull BooleanBinding b) {
    return new AbstractBooleanBinding(new HashSet<>(Arrays.asList(a, b))) {
      @Override
      protected Boolean compute() {
        return a.getValue() != b.getValue();
      }
    };
  }

  /**
   * Creates a boolean binding which evaluates whether the supplied observable is not set to null.
   */
  @Nonnull
  static BooleanBinding notNull(@Nonnull ReadOnlyObservable<?> observable) {
    return new AbstractBooleanBinding(Collections.singleton(observable)) {
      @Override
      protected Boolean compute() {
        return observable.get() != null;
      }
    };
  }

  /**
   * Creates a boolean binding which evaluates whether the supplied observable is set to null.
   */
  @Nonnull
  static BooleanBinding isNull(@Nonnull ReadOnlyObservable<?> observable) {
    return new AbstractBooleanBinding(Collections.singleton(observable)) {
      @Override
      protected Boolean compute() {
        return observable.get() == null;
      }
    };
  }

  /**
   * Wraps a binding in order to convert it into its "primitive" form (e.g. into the more specific
   * interface definition).
   */
  @Nonnull
  static BooleanBinding toPrimitive(@Nonnull Binding<Boolean> binding) {
    return new AbstractBooleanBinding(Collections.singleton(binding)) {
      @Override
      protected Boolean compute() {
        Boolean value = binding.get();

        if (value == null) {
          return false;
        }

        return value;
      }
    };
  }

  /**
   * Combines the outcome of this binding and the supplied binding using a logical and operation.
   */
  @Nonnull
  default BooleanBinding and(@Nonnull BooleanBinding binding) {
    return and(this, binding);
  }

  /**
   * Negates the outcome of this binding.
   */
  @Nonnull
  default BooleanBinding not() {
    return not(this);
  }

  /**
   * Combines the outcome of this binding and the supplied binding using a logical or operation.
   */
  @Nonnull
  default BooleanBinding or(@Nonnull BooleanBinding binding) {
    return or(this, binding);
  }

  /**
   * Combines the outcome of this binding and the supplied binding using a logical xor operation.
   */
  @Nonnull
  default BooleanBinding xor(@Nonnull BooleanBinding binding) {
    return xor(this, binding);
  }
}
