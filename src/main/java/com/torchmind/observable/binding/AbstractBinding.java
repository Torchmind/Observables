package com.torchmind.observable.binding;

import com.torchmind.observable.AbstractObservable;
import com.torchmind.observable.ReadOnlyObservable;
import com.torchmind.observable.listener.ChangeListener;
import java.util.Collections;
import java.util.Set;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

/**
 * Provides an abstract implementation for bindings.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
abstract class AbstractBinding<V> extends AbstractObservable<V> implements Binding<V> {

  private final Set<ReadOnlyObservable<?>> dependencies;

  private final ChangeListener<Object> changeListener = (property, oldValue, newValue) -> this
      .invalidate();

  AbstractBinding(@Nonnull Set<ReadOnlyObservable<?>> dependencies) {
    this.dependencies = dependencies;
    dependencies.forEach((d) -> d.registerListener(this.changeListener));
  }

  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public Supplier<V> asSupplier() {
    return this::get;
  }

  /**
   * Computes the output value of this binding.
   */
  protected abstract V compute();

  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public Set<ReadOnlyObservable<?>> getDependencies() {
    return Collections.unmodifiableSet(this.dependencies);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void invalidate() {
    V value = this.compute();
    this.set(value);
  }
}
