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

  public SimpleLongObservable(
      @Nullable ValidationListener<Long> validationListener, Long value) {
    super(validationListener, value);
  }

  public SimpleLongObservable(Long value) {
    super(value);
  }

  public SimpleLongObservable() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public long getValue() {
    Long value = this.get();

    if (value == null) {
      return 0;
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
