package com.torchmind.observable.primitive;

import com.torchmind.observable.AbstractObservable;
import com.torchmind.observable.listener.ValidationListener;
import javax.annotation.Nullable;

/**
 * Provides a observable implementation which simplifies access to byte values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class SimpleByteObservable extends AbstractObservable<Byte> implements ByteObservable {

  public SimpleByteObservable(
      @Nullable ValidationListener<Byte> validationListener, Byte value) {
    super(validationListener, value);
  }

  public SimpleByteObservable(Byte value) {
    super(value);
  }

  public SimpleByteObservable() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public byte getValue() {
    Byte value = this.get();

    if (value == null) {
      return 0;
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(byte value) {
    this.set(value);
  }
}
