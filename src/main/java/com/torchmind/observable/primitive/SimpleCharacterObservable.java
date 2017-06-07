package com.torchmind.observable.primitive;

import com.torchmind.observable.AbstractObservable;
import com.torchmind.observable.listener.ValidationListener;
import javax.annotation.Nullable;

/**
 * Provides a observable implementation which simplifies access to character values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class SimpleCharacterObservable extends AbstractObservable<Character> implements
    CharacterObservable {

  public SimpleCharacterObservable(
      @Nullable ValidationListener<Character> validationListener, Character value) {
    super(validationListener, value);
  }

  public SimpleCharacterObservable(Character value) {
    super(value);
  }

  public SimpleCharacterObservable() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public char getValue() {
    Character value = this.get();

    if (value == null) {
      return 0;
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(char value) {
    this.set(value);
  }
}
