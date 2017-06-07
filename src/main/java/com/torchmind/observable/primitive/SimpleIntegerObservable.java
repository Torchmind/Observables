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

  private final int fallbackValue;

  public SimpleIntegerObservable(
      @Nullable ValidationListener<Integer> validationListener, Integer value, int fallbackValue) {
    super(validationListener, value);
    this.fallbackValue = fallbackValue;
  }

  public SimpleIntegerObservable(
      @Nullable ValidationListener<Integer> validationListener, Integer value) {
    this(validationListener, value, 0);
  }

  public SimpleIntegerObservable(Integer value) {
    this(null, value);
  }

  public SimpleIntegerObservable() {
    this(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getValue() {
    Integer value = this.get();

    if (value == null) {
      return this.fallbackValue;
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
