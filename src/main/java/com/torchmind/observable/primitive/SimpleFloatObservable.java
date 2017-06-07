package com.torchmind.observable.primitive;

import com.torchmind.observable.AbstractObservable;
import com.torchmind.observable.listener.ValidationListener;
import javax.annotation.Nullable;

/**
 * Provides a observable implementation which simplifies access to float values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class SimpleFloatObservable extends AbstractObservable<Float> implements FloatObservable {

  private final float fallbackValue;

  public SimpleFloatObservable(
      @Nullable ValidationListener<Float> validationListener, Float value, float fallbackValue) {
    super(validationListener, value);
    this.fallbackValue = fallbackValue;
  }

  public SimpleFloatObservable(
      @Nullable ValidationListener<Float> validationListener, Float value) {
    this(validationListener, value, 0);
  }

  public SimpleFloatObservable(Float value) {
    this(null, value);
  }

  public SimpleFloatObservable() {
    this(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public float getValue() {
    Float value = this.get();

    if (value == null) {
      return this.fallbackValue;
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(float value) {
    this.set(value);
  }
}
