package com.torchmind.observable.listener;

import com.torchmind.observable.ReadOnlyObservable;
import javax.annotation.Nonnull;

/**
 * <p>Provides a base to listeners which wish to execute custom logic whenever an observed value
 * changes its state.</p>
 *
 * <p>Listeners are theoretically permitted to throw exceptions within the course of their
 * execution, however, it is not a recommended practice as it halts the process of notifying
 * other listeners of the change. Additionally, the exception will "bubble up" to the respective
 * caller which caused the update to occur.</p>
 *
 * <p>In short: Listeners are unable to reject a new value. Implementors should use invalidation
 * listeners instead for this purpose.</p>
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
@FunctionalInterface
public interface ChangeListener<V> {

  /**
   * Contains custom logic which is invoked when the observable, to which this listener is attached,
   * changes its exposed value.
   */
  void onChange(@Nonnull ReadOnlyObservable<? extends V> property, V oldValue, V newValue);
}
