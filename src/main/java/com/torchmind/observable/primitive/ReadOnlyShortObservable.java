package com.torchmind.observable.primitive;

/**
 * Provides a observable implementation which simplifies access to short values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface ReadOnlyShortObservable extends ReadOnlyNumberObservable<Short> {

  /**
   * @see #get() for a wrapped version of the value.
   */
  short getValue();
}
