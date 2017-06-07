package com.torchmind.observable.primitive;

/**
 * Provides a observable implementation which simplifies access to integer values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface IntegerObservable extends NumberObservable<Integer>, ReadOnlyIntegerObservable {

  /**
   * @see #set(Object) for a wrapped version of the value.
   */
  void setValue(int value);
}
