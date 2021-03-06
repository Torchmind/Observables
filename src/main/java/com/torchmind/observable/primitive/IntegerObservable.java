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

import java.util.function.IntConsumer;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Provides a observable implementation which simplifies access to integer values.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface IntegerObservable extends NumberObservable<Integer>, ReadOnlyIntegerObservable {

  /**
   * Converts this observable into a standard Java consumer.
   */
  @NonNull
  default IntConsumer asIntConsumer() {
    return this::setValue;
  }

  /**
   * @see #set(Object) for a wrapped version of the value.
   */
  void setValue(int value);
}
