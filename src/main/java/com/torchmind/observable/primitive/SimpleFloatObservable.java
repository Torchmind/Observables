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

  public SimpleFloatObservable(
      @Nullable ValidationListener<Float> validationListener, Float value) {
    super(validationListener, value);
  }

  public SimpleFloatObservable(Float value) {
    super(value);
  }

  public SimpleFloatObservable() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public float getValue() {
    Float value = this.get();

    if (value == null) {
      return 0;
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
