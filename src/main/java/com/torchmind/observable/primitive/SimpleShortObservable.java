package com.torchmind.observable.primitive;

import com.torchmind.observable.AbstractObservable;
import com.torchmind.observable.listener.ValidationListener;
import javax.annotation.Nullable;

/**
 * Provides a observable implementation which simplifies access to short values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class SimpleShortObservable extends AbstractObservable<Short> implements ShortObservable {

  private final short fallbackValue;

  public SimpleShortObservable(
      @Nullable ValidationListener<Short> validationListener, Short value, short fallbackValue) {
    super(validationListener, value);
    this.fallbackValue = fallbackValue;
  }

  public SimpleShortObservable(
      @Nullable ValidationListener<Short> validationListener, Short value) {
    this(validationListener, value, (short) 0);
  }

  public SimpleShortObservable(Short value) {
    this(null, value);
  }

  public SimpleShortObservable() {
    this(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public short getValue() {
    Short value = this.get();

    if (value == null) {
      return this.fallbackValue;
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(short value) {
    this.set(value);
  }
}
