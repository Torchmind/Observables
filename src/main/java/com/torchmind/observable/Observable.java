/*
 * Copyright 2017 Johannes Donath <johannesd@torchmind.com>
 * and other copyright owners as documented in the project's IP log.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.torchmind.observable;

import javax.annotation.Nonnull;

/**
 * Represents a property of a complex (or possibly wrapped) type which is capable of inheriting its
 * value from another property as well as notifying third parties of property changes.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface Observable<V> extends ReadOnlyObservable<V> {

  /**
   * <p>Updates the internal value of this observable using the passed value.</p>
   *
   * @throws IllegalArgumentException when the backing invalidation listener deems the passed value
   * to be invalid.
   * @throws IllegalStateException when a unidirectional binding prevents the caller from changing
   * this observable value.
   */
  void set(V value);

  /**
   * <p>Evaluates whether this observable is in a valid state.</p>
   *
   * <p>This method returns true as long as no prior call to {@link #set(Object)}} has changed the
   * validity and will return back to true when all heirs and listeners have been notified of this
   * change.</p>
   */
  boolean isValid();

  /**
   * <p>Binds the value of this observable to the value of the supplied observable (e.g. it makes
   * this observable a heir to the value of the provided observable) through a unidirectional
   * binding.</p>
   *
   * <p>When this method is called, the observable will automatically be invalidated to take over
   * its new value. Access to {@link #set(Object)} will be prevented until {@link #unbind()} is
   * called.</p>
   *
   * @throws IllegalStateException when this method is currently part of another binding.
   */
  void bindTo(@Nonnull ReadOnlyObservable<? extends V> observable);

  /**
   * <p>Enables sharing of the very same value on this and the supplied observable.</p>
   *
   * <p>When this method is called, the observable will automatically be invalidated to take over
   * its new value (as dictated by the newly bound property).</p>
   *
   * @throws IllegalStateException when this method is currently part of another unidirectional
   * binding.
   */
  void bindBidirectionallyTo(@Nonnull Observable<V> observable);

  /**
   * Evaluates whether this observable is bound to another observable either uni- or
   * bidirectionally.
   */
  boolean isBound();

  /**
   * Evaluates whether this observable is bound to the supplied observable (e.g. it is a heir of the
   * supplied observable).
   */
  boolean isBoundTo(@Nonnull ReadOnlyObservable<? extends V> observable);

  /**
   * Evaluates whether this observable is bound to the supplied observable bidirectionally.
   */
  boolean isBoundBidirectionallyTo(@Nonnull Observable<V> observable);

  /**
   * Evaluates whether this observable is bound to one or more observables using a bidirectional (
   * e.g. shared value) binding.
   */
  boolean isBoundBidirectionally();

  /**
   * <p>Removes a unidirectional binding from this observable.</p>
   *
   * <p>When this method is called, the last known value is retained for this observable and access
   * to {@link #set(Object)} will be restored.</p>
   *
   * @throws IllegalStateException when no unidirectional binding is present.
   */
  void unbind();

  /**
   * <p>Unbinds this binding from all bidirectional and unidirectional bindings.</p>
   *
   * <p>When this method is called, the last known value is retained for this observable and access
   * to {@link #set(Object)} will be restored (if previously unavailable).</p>
   *
   * @throws IllegalStateException when no bindings are present.
   */
  void unbindAll();

  /**
   * <p>Unbinds a specific bidirectional relation from this observable.</p>
   *
   * <p>When this method is called, the last known value is retained for this observable even when
   * the last bidirectional binding is removed.</p>
   *
   * @throws IllegalStateException when no matching binding is present.
   */
  void unbindBidirectional(@Nonnull Observable<V> observable);
}
