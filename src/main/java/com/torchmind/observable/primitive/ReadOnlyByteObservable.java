package com.torchmind.observable.primitive;

/**
 * Provides a observable implementation which simplifies access to byte values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface ReadOnlyByteObservable extends ReadOnlyNumberObservable<Byte> {

  /**
   * @see #get() for a wrapped version of the value.
   */
  byte getValue();
}
