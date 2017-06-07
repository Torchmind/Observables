package com.torchmind.observable.primitive;

/**
 * Provides a observable implementation which simplifies access to float values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface FloatObservable extends NumberObservable<Float>, ReadOnlyFloatObservable {

  /**
   * @see #set(Object) for a wrapped version of the value.
   */
  void setValue(float value);
}
