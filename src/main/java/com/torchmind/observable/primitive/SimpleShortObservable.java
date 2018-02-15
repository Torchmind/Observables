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

package com.torchmind.observable.primitive;

import com.torchmind.observable.AbstractObservable;
import com.torchmind.observable.listener.ValidationListener;
import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * Provides a observable implementation which simplifies access to short values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class SimpleShortObservable extends AbstractObservable<Short> implements ShortObservable {

  private final short fallbackValue;

  public SimpleShortObservable(
      @Nullable ValidationListener<Short> validationListener, Short value, short fallbackValue) {
    super(validationListener, value);
    this.fallbackValue = fallbackValue;
  }

  public SimpleShortObservable(
      @Nullable ValidationListener<Short> validationListener, Short value) {
    this(validationListener, value, (short) 0);
  }

  public SimpleShortObservable(Short value) {
    this(null, value);
  }

  public SimpleShortObservable() {
    this(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public short getValue() {
    Short value = this.get();

    if (value == null) {
      return this.fallbackValue;
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(short value) {
    this.set(value);
  }
}
