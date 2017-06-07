package com.torchmind.observable.primitive;

/**
 * Provides a observable implementation which simplifies access to double values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface DoubleObservable extends NumberObservable<Double>, ReadOnlyDoubleObservable {

  /**
   * @see #set(Object) for a wrapped version of the value.
   */
  void setValue(double value);
}
