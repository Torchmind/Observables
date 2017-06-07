package com.torchmind.observable.primitive;

/**
 * Provides a observable implementation which simplifies access to byte values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface ByteObservable extends NumberObservable<Byte>, ReadOnlyByteObservable {

  /**
   * @see #set(Object) for a wrapped version of the value.
   */
  void setValue(byte value);
}
