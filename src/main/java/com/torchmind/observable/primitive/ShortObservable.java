package com.torchmind.observable.primitive;

import com.torchmind.observable.Observable;

/**
 * Provides a observable implementation which simplifies access to short values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface ShortObservable extends Observable<Short>, ReadOnlyShortObservable {

  /**
   * @see #set(Object) for a wrapped version of the value.
   */
  void setValue(short value);
}
