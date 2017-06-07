package com.torchmind.observable.primitive;

/**
 * Provides a observable implementation which simplifies access to long values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface ReadOnlyLongObservable extends ReadOnlyNumberObservable<Long> {

  /**
   * @see #get() for a wrapped version of the value.
   */
  long getValue();
}
