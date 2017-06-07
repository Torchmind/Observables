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

  private final double fallbackValue;

  public SimpleDoubleObservable(
      @Nullable ValidationListener<Double> validationListener, Double value, double fallbackValue) {
    super(validationListener, value);
    this.fallbackValue = fallbackValue;
  }

  public SimpleDoubleObservable(
      @Nullable ValidationListener<Double> validationListener, Double value) {
    this(validationListener, value, 0);
  }

  public SimpleDoubleObservable(Double value) {
    this(null, value);
  }

  public SimpleDoubleObservable() {
    this(null, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getValue() {
    Double value = this.get();

    if (value == null) {
      return this.fallbackValue;
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
