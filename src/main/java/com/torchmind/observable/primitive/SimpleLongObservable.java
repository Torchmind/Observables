package com.torchmind.observable.primitive;

import com.torchmind.observable.AbstractObservable;
import com.torchmind.observable.listener.ValidationListener;
import javax.annotation.Nullable;

/**
 * Provides a observable implementation which simplifies access to long values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class SimpleLongObservable extends AbstractObservable<Long> implements LongObservable {

  private final long fallbackValue;

  public SimpleLongObservable(
      @Nullable ValidationListener<Long> validationListener, Long value, long fallbackValue) {
    super(validationListener, value);
    this.fallbackValue = fallbackValue;
  }

  public SimpleLongObservable(
      @Nullable ValidationListener<Long> validationListener, Long value) {
    this(validationListener, value, 0);
  }

  public SimpleLongObservable(Long value) {
    this(null, value);
  }

  public SimpleLongObservable() {
    this(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public long getValue() {
    Long value = this.get();

    if (value == null) {
      return this.fallbackValue;
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(long value) {
    this.set(value);
  }
}
