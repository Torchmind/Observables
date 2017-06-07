package com.torchmind.observable.primitive;

import com.torchmind.observable.ReadOnlyObservable;

/**
 * Provides a observable implementation which simplifies access to boolean values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface ReadOnlyBooleanObservable extends ReadOnlyObservable<Boolean> {

  /**
   * @see #get() for a wrapped version of the value.
   */
  boolean getValue();
}
