package com.torchmind.observable.primitive;

/**
 * Provides a observable implementation which simplifies access to float values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface ReadOnlyFloatObservable extends ReadOnlyNumberObservable<Float> {

  /**
   * @see #get() for a wrapped version of the value.
   */
  float getValue();
}
