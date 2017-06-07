package com.torchmind.observable.primitive;

import com.torchmind.observable.Observable;

/**
 * Provides a observable implementation which simplifies access to boolean values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface BooleanObservable extends Observable<Boolean>, ReadOnlyBooleanObservable {

  /**
   * @see #set(Object) for a wrapped version of the value.
   */
  void setValue(boolean value);
}
