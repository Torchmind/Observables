package com.torchmind.observable.binding;

import com.torchmind.observable.ReadOnlyObservable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

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
  @Nonnull
  static <V> Binding<V> create(@Nonnull Supplier<V> supplier,
      ReadOnlyObservable<?>... observables) {
    return new AbstractBinding<V>(new HashSet<>(Arrays.asList(observables))) {
      @Override
      protected V compute() {
        return supplier.get();
      }
    };
  }

  /**
   * Converts the logic of this binding into a standard Java supplier.
   */
  @Nonnull
  Supplier<V> asSupplier();

  /**
   * Retrieves a list of observables that this binding relies upon.
   */
  @Nonnull
  Set<ReadOnlyObservable<?>> getDependencies();

  /**
   * Forces this binding to re-evaluate its state based on the properties of its dependencies.
   */
  void invalidate();
}
