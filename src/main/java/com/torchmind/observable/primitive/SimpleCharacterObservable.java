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

  private final char fallbackValue;

  public SimpleCharacterObservable(
      @Nullable ValidationListener<Character> validationListener, Character value,
      char fallbackValue) {
    super(validationListener, value);
    this.fallbackValue = fallbackValue;
  }

  public SimpleCharacterObservable(
      @Nullable ValidationListener<Character> validationListener, Character value) {
    this(validationListener, value, (char) 0);
  }

  public SimpleCharacterObservable(Character value) {
    this(null, value);
  }

  public SimpleCharacterObservable() {
    this(null, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public char getValue() {
    Character value = this.get();

    if (value == null) {
      return this.fallbackValue;
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
