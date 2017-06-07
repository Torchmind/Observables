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

  public SimpleBooleanObservable(
      @Nullable ValidationListener<Boolean> validationListener, Boolean value) {
    super(validationListener, value);
  }

  public SimpleBooleanObservable(Boolean value) {
    super(value);
  }

  public SimpleBooleanObservable() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean getValue() {
    Boolean value = this.get();

    if (value == null) {
      return false;
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
