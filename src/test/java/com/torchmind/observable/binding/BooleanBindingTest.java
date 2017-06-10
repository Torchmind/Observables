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

import org.junit.Assert;
import org.junit.Test;

/**
 * Provides tests which evaluate whether boolean bindings behave as designed.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class BooleanBindingTest {

  /**
   * Evaluates whether the logical binding combinations work as intended.
   */
  @Test
  public void testLogic() {
    BooleanBinding trueBinding = BooleanBinding.staticValue(true);
    BooleanBinding falseBinding = BooleanBinding.staticValue(false);

    Assert.assertTrue(trueBinding.getValue());
    Assert.assertFalse(falseBinding.getValue());

    Assert.assertFalse(BooleanBinding.and(falseBinding, falseBinding).getValue());
    Assert.assertFalse(BooleanBinding.and(falseBinding, trueBinding).getValue());
    Assert.assertFalse(BooleanBinding.and(trueBinding, falseBinding).getValue());
    Assert.assertTrue(BooleanBinding.and(trueBinding, trueBinding).getValue());

    Assert.assertFalse(BooleanBinding.or(falseBinding, falseBinding).getValue());
    Assert.assertTrue(BooleanBinding.or(falseBinding, trueBinding).getValue());
    Assert.assertTrue(BooleanBinding.or(trueBinding, falseBinding).getValue());
    Assert.assertTrue(BooleanBinding.or(trueBinding, trueBinding).getValue());

    Assert.assertFalse(BooleanBinding.xor(falseBinding, falseBinding).getValue());
    Assert.assertTrue(BooleanBinding.xor(falseBinding, trueBinding).getValue());
    Assert.assertTrue(BooleanBinding.xor(trueBinding, falseBinding).getValue());
    Assert.assertFalse(BooleanBinding.xor(trueBinding, trueBinding).getValue());

    Assert.assertTrue(BooleanBinding.not(falseBinding).getValue());
    Assert.assertFalse(BooleanBinding.not(trueBinding).getValue());
  }
}
