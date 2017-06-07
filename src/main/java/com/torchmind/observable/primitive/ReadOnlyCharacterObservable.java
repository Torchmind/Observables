package com.torchmind.observable.primitive;

import com.torchmind.observable.ReadOnlyObservable;

/**
 * Provides a observable implementation which simplifies access to char values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface ReadOnlyCharacterObservable extends ReadOnlyObservable<Character> {

  /**
   * @see #get() for a wrapped version of the value.
   */
  char getValue();
}
