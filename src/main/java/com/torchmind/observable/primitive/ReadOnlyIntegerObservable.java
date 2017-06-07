package com.torchmind.observable.primitive;

/**
 * Provides a observable implementation which simplifies access to integer values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface ReadOnlyIntegerObservable extends ReadOnlyNumberObservable<Integer> {

  /**
   * @see #get() for a wrapped version of the value.
   */
  int getValue();
}
