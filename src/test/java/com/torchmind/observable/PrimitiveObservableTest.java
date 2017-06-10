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

import com.torchmind.observable.primitive.SimpleBooleanObservable;
import com.torchmind.observable.primitive.SimpleByteObservable;
import com.torchmind.observable.primitive.SimpleCharacterObservable;
import com.torchmind.observable.primitive.SimpleDoubleObservable;
import com.torchmind.observable.primitive.SimpleFloatObservable;
import com.torchmind.observable.primitive.SimpleIntegerObservable;
import com.torchmind.observable.primitive.SimpleLongObservable;
import com.torchmind.observable.primitive.SimpleShortObservable;
import org.junit.Assert;
import org.junit.Test;

/**
 * Evaluates whether primitive utility implementations of observables properly handle null values
 * in their getters.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class PrimitiveObservableTest {

  /**
   * Evaluates whether the boolean primitive handles null values correctly and stores its values.
   */
  @Test
  public void testBoolean() {
    SimpleBooleanObservable observable = new SimpleBooleanObservable();

    Assert.assertNull(observable.get());
    Assert.assertFalse(observable.getValue());

    observable.setValue(true);
    Assert.assertNotNull(observable.get());
    Assert.assertTrue(observable.getValue());
  }

  /**
   * Evaluates whether the byte primitive handles null values correctly and stores its values.
   */
  @Test
  public void testByte() {
    SimpleByteObservable observable = new SimpleByteObservable();

    Assert.assertNull(observable.get());
    Assert.assertEquals(0, observable.getValue());

    observable.setValue((byte) 42);
    Assert.assertNotNull(observable.get());
    Assert.assertEquals(42, observable.getValue());
  }

  /**
   * Evaluates whether the char primitive handles null values correctly and stores its values.
   */
  @Test
  public void testCharacter() {
    SimpleCharacterObservable observable = new SimpleCharacterObservable();

    Assert.assertNull(observable.get());
    Assert.assertEquals(0, observable.getValue());

    observable.setValue('C');
    Assert.assertNotNull(observable.get());
    Assert.assertEquals('C', observable.getValue());
  }

  /**
   * Evaluates whether the double primitive handles null values correctly and stores its values.
   */
  @Test
  public void testDouble() {
    SimpleDoubleObservable observable = new SimpleDoubleObservable();

    Assert.assertNull(observable.get());
    Assert.assertEquals(0, observable.getValue(), 0.1);

    observable.setValue(42);
    Assert.assertNotNull(observable.get());
    Assert.assertEquals(42, observable.getValue(), 0.1);
  }

  /**
   * Evaluates whether the float primitive handles null values correctly and stores its values.
   */
  @Test
  public void testFloat() {
    SimpleFloatObservable observable = new SimpleFloatObservable();

    Assert.assertNull(observable.get());
    Assert.assertEquals(0, observable.getValue(), 0.1);

    observable.setValue(42);
    Assert.assertNotNull(observable.get());
    Assert.assertEquals(42, observable.getValue(), 0.1);
  }

  /**
   * Evaluates whether the integer primitive handles null values correctly and stores its values.
   */
  @Test
  public void testInteger() {
    SimpleIntegerObservable observable = new SimpleIntegerObservable();

    Assert.assertNull(observable.get());
    Assert.assertEquals(0, observable.getValue());

    observable.setValue(42);
    Assert.assertNotNull(observable.get());
    Assert.assertEquals(42, observable.getValue());
  }

  /**
   * Evaluates whether the long primitive handles null values correctly and stores its values.
   */
  @Test
  public void testLong() {
    SimpleLongObservable observable = new SimpleLongObservable();

    Assert.assertNull(observable.get());
    Assert.assertEquals(0, observable.getValue());

    observable.setValue(42);
    Assert.assertNotNull(observable.get());
    Assert.assertEquals(42, observable.getValue());
  }

  /**
   * Evaluates whether the short primitive handles null values correctly and stores its values.
   */
  @Test
  public void testShort() {
    SimpleShortObservable observable = new SimpleShortObservable();

    Assert.assertNull(observable.get());
    Assert.assertEquals(0, observable.getValue());

    observable.setValue((short) 42);
    Assert.assertNotNull(observable.get());
    Assert.assertEquals(42, observable.getValue());
  }
}
