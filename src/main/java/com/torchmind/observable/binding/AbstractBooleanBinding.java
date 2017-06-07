package com.torchmind.observable.binding;

import com.torchmind.observable.ReadOnlyObservable;
import java.util.Set;
import javax.annotation.Nonnull;

/**
 * Provides an abstract boolean binding implementation.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
abstract class AbstractBooleanBinding extends AbstractBinding<Boolean> implements BooleanBinding {

  public AbstractBooleanBinding(
      @Nonnull Set<ReadOnlyObservable<?>> dependencies) {
    super(dependencies);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean getValue() {
    Boolean value = this.get();

    if (value == null) {
      return false;
    }

    return value;
  }
}
