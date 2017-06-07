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

  public SimpleShortObservable(
      @Nullable ValidationListener<Short> validationListener, Short value) {
    super(validationListener, value);
  }

  public SimpleShortObservable(Short value) {
    super(value);
  }

  public SimpleShortObservable() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public short getValue() {
    Short value = this.get();

    if (value == null) {
      return 0;
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
