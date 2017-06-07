package com.torchmind.observable.primitive;

import com.torchmind.observable.Observable;

/**
 * Provides a base interface to all observables that contain numbers.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface NumberObservable<N extends Number> extends Observable<N>,
    ReadOnlyNumberObservable<N> {

}
