package com.torchmind.observable.primitive;

/**
 * Provides a observable implementation which simplifies access to double values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface ReadOnlyDoubleObservable extends ReadOnlyNumberObservable<Double> {

  /**
   * @see #get() for a wrapped version of the value.
   */
  double getValue();
}
