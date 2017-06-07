package com.torchmind.observable.primitive;

/**
 * Provides a observable implementation which simplifies access to long values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface LongObservable extends NumberObservable<Long>, ReadOnlyLongObservable {

  /**
   * @see #set(Object) for a wrapped version of the value.
   */
  void setValue(long value);
}
