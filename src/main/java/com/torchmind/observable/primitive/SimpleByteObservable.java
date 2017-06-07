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

  private final byte fallbackValue;

  public SimpleByteObservable(
      @Nullable ValidationListener<Byte> validationListener, Byte value, byte fallbackValue) {
    super(validationListener, value);
    this.fallbackValue = fallbackValue;
  }

  public SimpleByteObservable(
      @Nullable ValidationListener<Byte> validationListener, Byte value) {
    this(validationListener, value, (byte) 0);
  }

  public SimpleByteObservable(Byte value) {
    this(null, value);
  }

  public SimpleByteObservable() {
    this(null, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public byte getValue() {
    Byte value = this.get();

    if (value == null) {
      return this.fallbackValue;
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
