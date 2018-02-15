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

package com.torchmind.observable.binding;

import com.torchmind.observable.ReadOnlyObservable;
import java.util.Set;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Provides an abstract double binding implementation.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
abstract class AbstractDoubleBinding extends AbstractBinding<Double> implements DoubleBinding {

  public AbstractDoubleBinding(
      @NonNull Set<ReadOnlyObservable<?>> dependencies) {
    super(dependencies);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getValue() {
    Double value = this.get();

    if (value == null) {
      return 0;
    }

    return value;
  }
}
