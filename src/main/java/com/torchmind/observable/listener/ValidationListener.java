package com.torchmind.observable.listener;

import com.torchmind.observable.Observable;
import javax.annotation.Nonnull;

/**
 * Provides a listener for the purposes of validating new values within standard property
 * implementations.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
@FunctionalInterface
public interface ValidationListener<V> {

  /**
   * Validates a new value before it is stored in the backing field.
   */
  void validate(@Nonnull Observable<V> observable, V newValue);
}
