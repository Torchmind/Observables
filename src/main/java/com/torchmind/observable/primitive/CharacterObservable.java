package com.torchmind.observable.primitive;

import com.torchmind.observable.Observable;

/**
 * Provides a observable implementation which simplifies access to char values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface CharacterObservable extends Observable<Character>, ReadOnlyCharacterObservable {

  /**
   * @see #set(Object) for a wrapped version of the value.
   */
  void setValue(char value);
}
