package com.torchmind.observable.primitive;

import com.torchmind.observable.AbstractObservable;
import com.torchmind.observable.listener.ValidationListener;
import javax.annotation.Nullable;

/**
 * Provides a observable implementation which simplifies access to boolean values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class SimpleBooleanObservable extends AbstractObservable<Boolean> implements
    BooleanObservable {

  private final boolean fallbackValue;

  public SimpleBooleanObservable(
      @Nullable ValidationListener<Boolean> validationListener, Boolean value,
      boolean fallbackValue) {
    super(validationListener, value);
    this.fallbackValue = fallbackValue;
  }

  public SimpleBooleanObservable(
      @Nullable ValidationListener<Boolean> validationListener, Boolean value) {
    this(validationListener, value, false);
  }

  public SimpleBooleanObservable(Boolean value) {
    this(null, value);
  }

  public SimpleBooleanObservable() {
    this(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean getValue() {
    Boolean value = this.get();

    if (value == null) {
      return this.fallbackValue;
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(boolean value) {
    this.set(value);
  }
}
