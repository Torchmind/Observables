package com.torchmind.observable.primitive;

import com.torchmind.observable.AbstractObservable;
import com.torchmind.observable.listener.ValidationListener;
import javax.annotation.Nullable;

/**
 * Provides a observable implementation which simplifies access to integer values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class SimpleIntegerObservable extends AbstractObservable<Integer> implements
    IntegerObservable {

  public SimpleIntegerObservable(
      @Nullable ValidationListener<Integer> validationListener, Integer value) {
    super(validationListener, value);
  }

  public SimpleIntegerObservable(Integer value) {
    super(value);
  }

  public SimpleIntegerObservable() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getValue() {
    Integer value = this.get();

    if (value == null) {
      return 0;
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(int value) {
    this.set(value);
  }
}
