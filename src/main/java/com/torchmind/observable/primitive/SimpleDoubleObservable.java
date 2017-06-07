package com.torchmind.observable.primitive;

import com.torchmind.observable.AbstractObservable;
import com.torchmind.observable.listener.ValidationListener;
import javax.annotation.Nullable;

/**
 * Provides a observable implementation which simplifies access to double values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class SimpleDoubleObservable extends AbstractObservable<Double> implements DoubleObservable {

  public SimpleDoubleObservable(
      @Nullable ValidationListener<Double> validationListener, Double value) {
    super(validationListener, value);
  }

  public SimpleDoubleObservable(Double value) {
    super(value);
  }

  public SimpleDoubleObservable() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getValue() {
    Double value = this.get();

    if (value == null) {
      return 0;
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(double value) {
    this.set(value);
  }
}
