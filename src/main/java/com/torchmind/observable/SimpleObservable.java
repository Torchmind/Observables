package com.torchmind.observable;

import com.torchmind.observable.listener.ValidationListener;
import javax.annotation.Nullable;

/**
 * Provides a rather simply version of the observable system.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class SimpleObservable<V> extends AbstractObservable<V> {

  public SimpleObservable(
      @Nullable ValidationListener<V> validationListener, V value) {
    super(validationListener, value);
  }

  public SimpleObservable(V value) {
    super(value);
  }

  public SimpleObservable() {
  }
}
