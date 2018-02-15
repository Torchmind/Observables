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

package com.torchmind.observable.concurrent;

import com.torchmind.observable.listener.ValidationListener;
import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * Provides a thread safe but blocking version of the observable specification.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class BlockingEnumObservable<E extends Enum<E>> extends AbstractBlockingObservable<E> {

  private final E fallbackValue;

  public BlockingEnumObservable(
      @Nullable ValidationListener<E> validationListener,
      E value, boolean fair, E fallbackValue) {
    super(validationListener, value, fair);
    this.fallbackValue = fallbackValue;
  }

  public BlockingEnumObservable(
      @Nullable ValidationListener<E> validationListener,
      E value, E fallbackValue) {
    super(validationListener, value);
    this.fallbackValue = fallbackValue;
  }

  public BlockingEnumObservable(E value) {
    this(null, value, null);
  }

  public BlockingEnumObservable() {
    this(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E get() {
    E value = super.get();

    if (value == null) {
      return this.fallbackValue;
    }

    return value;
  }
}
