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
import java.util.Objects;
import java.util.function.BooleanSupplier;
import edu.umd.cs.findbugs.annotations.NonNull;

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
  @NonNull
  static BooleanBinding create(@NonNull BooleanSupplier supplier,
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
  @NonNull
  static BooleanBinding and(@NonNull BooleanBinding a, @NonNull BooleanBinding b) {
    return new AbstractBooleanBinding(new HashSet<>(Arrays.asList(a, b))) {
      @Override
      protected Boolean compute() {
        return a.getValue() && b.getValue();
      }
    };
  }

  /**
   * Evaluates whether the two supplied observables are equal in value.
   */
  @NonNull
  static BooleanBinding equals(@NonNull ReadOnlyObservable<?> observable1,
      @NonNull ReadOnlyObservable observable2) {
    return create(() -> Objects.equals(observable1.get(), observable2.get()), observable1,
        observable2);
  }

  /**
   * Combines the two supplied boolean bindings using a binary not operation.
   */
  @NonNull
  static BooleanBinding not(@NonNull BooleanBinding a) {
    return new AbstractBooleanBinding(Collections.singleton(a)) {
      @Override
      protected Boolean compute() {
        return !a.getValue();
      }
    };
  }

  /**
   * Evaluates whether the two supplied observables are not equal in value.
   */
  @NonNull
  static BooleanBinding notEquals(@NonNull ReadOnlyObservable<?> observable1,
      @NonNull ReadOnlyObservable<?> observable2) {
    return create(() -> !Objects.equals(observable1.get(), observable2.get()), observable1,
        observable2);
  }

  /**
   * Combines the two supplied boolean bindings using a binary or operation.
   */
  @NonNull
  static BooleanBinding or(@NonNull BooleanBinding a, @NonNull BooleanBinding b) {
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
  @NonNull
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
  @NonNull
  static BooleanBinding xor(@NonNull BooleanBinding a, @NonNull BooleanBinding b) {
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
  @NonNull
  static BooleanBinding notNull(@NonNull ReadOnlyObservable<?> observable) {
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
  @NonNull
  static BooleanBinding isNull(@NonNull ReadOnlyObservable<?> observable) {
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
  @NonNull
  static BooleanBinding toPrimitive(@NonNull Binding<Boolean> binding) {
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
  @NonNull
  default BooleanBinding and(@NonNull BooleanBinding binding) {
    return and(this, binding);
  }

  /**
   * Negates the outcome of this binding.
   */
  @NonNull
  default BooleanBinding not() {
    return not(this);
  }

  /**
   * Combines the outcome of this binding and the supplied binding using a logical or operation.
   */
  @NonNull
  default BooleanBinding or(@NonNull BooleanBinding binding) {
    return or(this, binding);
  }

  /**
   * Combines the outcome of this binding and the supplied binding using a logical xor operation.
   */
  @NonNull
  default BooleanBinding xor(@NonNull BooleanBinding binding) {
    return xor(this, binding);
  }
}
