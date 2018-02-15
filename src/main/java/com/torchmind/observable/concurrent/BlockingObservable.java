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
public class BlockingObservable<V> extends AbstractBlockingObservable<V> {

  public BlockingObservable(
      @Nullable ValidationListener<V> validationListener,
      V value, boolean fair) {
    super(validationListener, value, fair);
  }

  public BlockingObservable(
      @Nullable ValidationListener<V> validationListener,
      V value) {
    super(validationListener, value);
  }

  public BlockingObservable(V value) {
    super(value);
  }

  public BlockingObservable() {
  }
}
