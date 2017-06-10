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

import com.torchmind.observable.primitive.ReadOnlyByteObservable;
import com.torchmind.observable.primitive.ReadOnlyDoubleObservable;
import com.torchmind.observable.primitive.ReadOnlyFloatObservable;
import com.torchmind.observable.primitive.ReadOnlyIntegerObservable;
import com.torchmind.observable.primitive.ReadOnlyLongObservable;
import com.torchmind.observable.primitive.ReadOnlyNumberObservable;
import com.torchmind.observable.primitive.ReadOnlyShortObservable;
import javax.annotation.Nonnull;

/**
 * Provides a base to number valued bindings.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public interface NumberBinding<V extends Number> extends Binding<V>, ReadOnlyNumberObservable<V> {

  // Conversion Logic

  /**
   * Creates an intermediate binding which casts a generic numeric value.
   */
  @Nonnull
  static ByteBinding toByte(@Nonnull ReadOnlyNumberObservable<?> observable) {
    return ByteBinding.create(() -> {
      Number number = observable.get();

      if (number == null) {
        return (byte) 0;
      }

      return number.byteValue();
    }, observable);
  }

  /**
   * Creates an intermediate binding which casts a generic numeric value.
   */
  @Nonnull
  static ShortBinding toShort(@Nonnull ReadOnlyNumberObservable<?> observable) {
    return ShortBinding.create(() -> {
      Number number = observable.get();

      if (number == null) {
        return (short) 0;
      }

      return number.shortValue();
    }, observable);
  }

  /**
   * Creates an intermediate binding which casts a generic numeric value.
   */
  @Nonnull
  static IntegerBinding toInteger(@Nonnull ReadOnlyNumberObservable<?> observable) {
    return IntegerBinding.create(() -> {
      Number number = observable.get();

      if (number == null) {
        return 0;
      }

      return number.intValue();
    }, observable);
  }

  /**
   * Creates an intermediate binding which casts a generic numeric value.
   */
  @Nonnull
  static LongBinding toLong(@Nonnull ReadOnlyNumberObservable<?> observable) {
    return LongBinding.create(() -> {
      Number number = observable.get();

      if (number == null) {
        return (long) 0;
      }

      return number.longValue();
    }, observable);
  }

  /**
   * Creates an intermediate binding which casts a generic numeric value.
   */
  @Nonnull
  static FloatBinding toFloat(@Nonnull ReadOnlyNumberObservable<?> observable) {
    return FloatBinding.create(() -> {
      Number number = observable.get();

      if (number == null) {
        return (float) 0;
      }

      return number.floatValue();
    }, observable);
  }

  /**
   * Creates an intermediate binding which casts a generic numeric value.
   */
  @Nonnull
  static DoubleBinding toDouble(@Nonnull ReadOnlyNumberObservable<?> observable) {
    return DoubleBinding.create(() -> {
      Number number = observable.get();

      if (number == null) {
        return (double) 0;
      }

      return number.doubleValue();
    }, observable);
  }

  // Additions

  /**
   * <p>Adds two numeric values together returning their respective value.</p>
   *
   * <p>When two values of different primitive number types are passed, the bigger or more precise
   * value is returned (for example when integer and byte are passed integer is returned).</p>
   */
  @Nonnull
  static NumberBinding<?> add(@Nonnull ReadOnlyNumberObservable<?> observable1,
      @Nonnull ReadOnlyNumberObservable<?> observable2) {
    // Floating Points
    if (observable1 instanceof ReadOnlyDoubleObservable
        || observable2 instanceof ReadOnlyDoubleObservable) {
      return DoubleBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.doubleValue() + number2.doubleValue();
      }, observable1, observable2);
    }

    if (observable1 instanceof ReadOnlyFloatObservable
        || observable2 instanceof ReadOnlyFloatObservable) {
      return FloatBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.floatValue() + number2.floatValue();
      }, observable1, observable2);
    }

    // Integers
    if (observable1 instanceof ReadOnlyLongObservable
        || observable2 instanceof ReadOnlyLongObservable) {
      return LongBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.longValue() + number2.longValue();
      });
    }

    if (observable1 instanceof ReadOnlyIntegerObservable
        || observable2 instanceof ReadOnlyIntegerObservable) {
      return IntegerBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.intValue() + number2.intValue();
      }, observable1, observable2);
    }

    if (observable1 instanceof ReadOnlyShortObservable
        || observable2 instanceof ReadOnlyShortObservable) {
      return IntegerBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.shortValue() + number2.shortValue();
      }, observable1, observable2);
    }

    if (observable1 instanceof ReadOnlyByteObservable
        || observable2 instanceof ReadOnlyByteObservable) {
      return IntegerBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.byteValue() + number2.byteValue();
      }, observable1, observable2);
    }

    throw new IllegalArgumentException(
        "Unsupported number observable implementations: " + observable1.getClass() + " and "
            + observable2.getClass());
  }

  /**
   * Adds the values of two integer properties together.
   */
  @Nonnull
  static IntegerBinding add(@Nonnull ReadOnlyByteObservable observable1,
      @Nonnull ReadOnlyByteObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() + observable2.getValue(), observable1, observable2);
  }

  /**
   * Adds the values of two short properties together.
   */
  @Nonnull
  static IntegerBinding add(@Nonnull ReadOnlyShortObservable observable1,
      @Nonnull ReadOnlyShortObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() + observable2.getValue(), observable1, observable2);
  }

  /**
   * Adds the values of two integer properties together.
   */
  @Nonnull
  static IntegerBinding add(@Nonnull ReadOnlyIntegerObservable observable1,
      @Nonnull ReadOnlyIntegerObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() + observable2.getValue(), observable1, observable2);
  }

  /**
   * Adds the values of two long properties together.
   */
  @Nonnull
  static LongBinding add(@Nonnull ReadOnlyLongObservable observable1,
      @Nonnull ReadOnlyLongObservable observable2) {
    return LongBinding
        .create(() -> observable1.getValue() + observable2.getValue(), observable1, observable2);
  }

  /**
   * Adds the values of two float properties.
   */
  @Nonnull
  static FloatBinding add(@Nonnull ReadOnlyFloatObservable observable1,
      @Nonnull ReadOnlyFloatObservable observable2) {
    return FloatBinding
        .create(() -> observable1.getValue() + observable2.getValue(), observable1, observable2);
  }

  /**
   * Adds the values of two double properties.
   */
  @Nonnull
  static DoubleBinding add(@Nonnull ReadOnlyDoubleObservable observable1,
      @Nonnull ReadOnlyDoubleObservable observable2) {
    return DoubleBinding
        .create(() -> observable1.getValue() + observable2.getValue(), observable1, observable2);
  }

  // Subtraction

  /**
   * <p>Subtracts two numeric values together returning their respective value.</p>
   *
   * <p>When two values of different primitive number types are passed, the bigger or more precise
   * value is returned (for example when integer and byte are passed integer is returned).</p>
   */
  @Nonnull
  static NumberBinding<?> subtract(@Nonnull ReadOnlyNumberObservable<?> observable1,
      @Nonnull ReadOnlyNumberObservable<?> observable2) {
    // Floating Points
    if (observable1 instanceof ReadOnlyDoubleObservable
        || observable2 instanceof ReadOnlyDoubleObservable) {
      return DoubleBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.doubleValue() - number2.doubleValue();
      }, observable1, observable2);
    }

    if (observable1 instanceof ReadOnlyFloatObservable
        || observable2 instanceof ReadOnlyFloatObservable) {
      return FloatBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.floatValue() - number2.floatValue();
      }, observable1, observable2);
    }

    // Integers
    if (observable1 instanceof ReadOnlyLongObservable
        || observable2 instanceof ReadOnlyLongObservable) {
      return LongBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.longValue() - number2.longValue();
      });
    }

    if (observable1 instanceof ReadOnlyIntegerObservable
        || observable2 instanceof ReadOnlyIntegerObservable) {
      return IntegerBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.intValue() - number2.intValue();
      }, observable1, observable2);
    }

    if (observable1 instanceof ReadOnlyShortObservable
        || observable2 instanceof ReadOnlyShortObservable) {
      return IntegerBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.shortValue() - number2.shortValue();
      }, observable1, observable2);
    }

    if (observable1 instanceof ReadOnlyByteObservable
        || observable2 instanceof ReadOnlyByteObservable) {
      return IntegerBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.byteValue() - number2.byteValue();
      }, observable1, observable2);
    }

    throw new IllegalArgumentException(
        "Unsupported number observable implementations: " + observable1.getClass() + " and "
            + observable2.getClass());
  }

  /**
   * Subtracts the values of two integer properties together.
   */
  @Nonnull
  static IntegerBinding subtract(@Nonnull ReadOnlyByteObservable observable1,
      @Nonnull ReadOnlyByteObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() - observable2.getValue(), observable1, observable2);
  }

  /**
   * Subtracts the values of two short properties together.
   */
  @Nonnull
  static IntegerBinding subtract(@Nonnull ReadOnlyShortObservable observable1,
      @Nonnull ReadOnlyShortObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() - observable2.getValue(), observable1, observable2);
  }

  /**
   * Subtracts the values of two integer properties together.
   */
  @Nonnull
  static IntegerBinding subtract(@Nonnull ReadOnlyIntegerObservable observable1,
      @Nonnull ReadOnlyIntegerObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() - observable2.getValue(), observable1, observable2);
  }

  /**
   * Subtracts the values of two long properties together.
   */
  @Nonnull
  static LongBinding subtract(@Nonnull ReadOnlyLongObservable observable1,
      @Nonnull ReadOnlyLongObservable observable2) {
    return LongBinding
        .create(() -> observable1.getValue() - observable2.getValue(), observable1, observable2);
  }

  /**
   * Subtracts the values of two float properties.
   */
  @Nonnull
  static FloatBinding subtract(@Nonnull ReadOnlyFloatObservable observable1,
      @Nonnull ReadOnlyFloatObservable observable2) {
    return FloatBinding
        .create(() -> observable1.getValue() - observable2.getValue(), observable1, observable2);
  }

  /**
   * Subtracts the values of two double properties.
   */
  @Nonnull
  static DoubleBinding subtract(@Nonnull ReadOnlyDoubleObservable observable1,
      @Nonnull ReadOnlyDoubleObservable observable2) {
    return DoubleBinding
        .create(() -> observable1.getValue() - observable2.getValue(), observable1, observable2);
  }

  // Multiply

  /**
   * <p>Multiplies two numeric values together returning their respective value.</p>
   *
   * <p>When two values of different primitive number types are passed, the bigger or more precise
   * value is returned (for example when integer and byte are passed integer is returned).</p>
   */
  @Nonnull
  static NumberBinding<?> multiply(@Nonnull ReadOnlyNumberObservable<?> observable1,
      @Nonnull ReadOnlyNumberObservable<?> observable2) {
    // Floating Points
    if (observable1 instanceof ReadOnlyDoubleObservable
        || observable2 instanceof ReadOnlyDoubleObservable) {
      return DoubleBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.doubleValue() * number2.doubleValue();
      }, observable1, observable2);
    }

    if (observable1 instanceof ReadOnlyFloatObservable
        || observable2 instanceof ReadOnlyFloatObservable) {
      return FloatBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.floatValue() * number2.floatValue();
      }, observable1, observable2);
    }

    // Integers
    if (observable1 instanceof ReadOnlyLongObservable
        || observable2 instanceof ReadOnlyLongObservable) {
      return LongBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.longValue() * number2.longValue();
      });
    }

    if (observable1 instanceof ReadOnlyIntegerObservable
        || observable2 instanceof ReadOnlyIntegerObservable) {
      return IntegerBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.intValue() * number2.intValue();
      }, observable1, observable2);
    }

    if (observable1 instanceof ReadOnlyShortObservable
        || observable2 instanceof ReadOnlyShortObservable) {
      return IntegerBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.shortValue() * number2.shortValue();
      }, observable1, observable2);
    }

    if (observable1 instanceof ReadOnlyByteObservable
        || observable2 instanceof ReadOnlyByteObservable) {
      return IntegerBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.byteValue() * number2.byteValue();
      }, observable1, observable2);
    }

    throw new IllegalArgumentException(
        "Unsupported number observable implementations: " + observable1.getClass() + " and "
            + observable2.getClass());
  }

  /**
   * Multiplies the values of two integer properties together.
   */
  @Nonnull
  static IntegerBinding multiply(@Nonnull ReadOnlyByteObservable observable1,
      @Nonnull ReadOnlyByteObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() * observable2.getValue(), observable1, observable2);
  }

  /**
   * Multiplies the values of two short properties together.
   */
  @Nonnull
  static IntegerBinding multiply(@Nonnull ReadOnlyShortObservable observable1,
      @Nonnull ReadOnlyShortObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() * observable2.getValue(), observable1, observable2);
  }

  /**
   * Multiplies the values of two integer properties together.
   */
  @Nonnull
  static IntegerBinding multiply(@Nonnull ReadOnlyIntegerObservable observable1,
      @Nonnull ReadOnlyIntegerObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() * observable2.getValue(), observable1, observable2);
  }

  /**
   * Multiplies the values of two long properties together.
   */
  @Nonnull
  static LongBinding multiply(@Nonnull ReadOnlyLongObservable observable1,
      @Nonnull ReadOnlyLongObservable observable2) {
    return LongBinding
        .create(() -> observable1.getValue() * observable2.getValue(), observable1, observable2);
  }

  /**
   * Multiplies the values of two float properties.
   */
  @Nonnull
  static FloatBinding multiply(@Nonnull ReadOnlyFloatObservable observable1,
      @Nonnull ReadOnlyFloatObservable observable2) {
    return FloatBinding
        .create(() -> observable1.getValue() * observable2.getValue(), observable1, observable2);
  }

  /**
   * Multiplies the values of two double properties.
   */
  @Nonnull
  static DoubleBinding multiply(@Nonnull ReadOnlyDoubleObservable observable1,
      @Nonnull ReadOnlyDoubleObservable observable2) {
    return DoubleBinding
        .create(() -> observable1.getValue() * observable2.getValue(), observable1, observable2);
  }

  // Divide

  /**
   * <p>Divides two numeric values together returning their respective value.</p>
   *
   * <p>When two values of different primitive number types are passed, the bigger or more precise
   * value is returned (for example when integer and byte are passed integer is returned).</p>
   */
  @Nonnull
  static NumberBinding<?> divide(@Nonnull ReadOnlyNumberObservable<?> observable1,
      @Nonnull ReadOnlyNumberObservable<?> observable2) {
    // Floating Points
    if (observable1 instanceof ReadOnlyDoubleObservable
        || observable2 instanceof ReadOnlyDoubleObservable) {
      return DoubleBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.doubleValue() / number2.doubleValue();
      }, observable1, observable2);
    }

    if (observable1 instanceof ReadOnlyFloatObservable
        || observable2 instanceof ReadOnlyFloatObservable) {
      return FloatBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.floatValue() / number2.floatValue();
      }, observable1, observable2);
    }

    // Integers
    if (observable1 instanceof ReadOnlyLongObservable
        || observable2 instanceof ReadOnlyLongObservable) {
      return LongBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.longValue() / number2.longValue();
      });
    }

    if (observable1 instanceof ReadOnlyIntegerObservable
        || observable2 instanceof ReadOnlyIntegerObservable) {
      return IntegerBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.intValue() / number2.intValue();
      }, observable1, observable2);
    }

    if (observable1 instanceof ReadOnlyShortObservable
        || observable2 instanceof ReadOnlyShortObservable) {
      return IntegerBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.shortValue() / number2.shortValue();
      }, observable1, observable2);
    }

    if (observable1 instanceof ReadOnlyByteObservable
        || observable2 instanceof ReadOnlyByteObservable) {
      return IntegerBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.byteValue() / number2.byteValue();
      }, observable1, observable2);
    }

    throw new IllegalArgumentException(
        "Unsupported number observable implementations: " + observable1.getClass() + " and "
            + observable2.getClass());
  }

  /**
   * Divides the values of two integer properties together.
   */
  @Nonnull
  static IntegerBinding divide(@Nonnull ReadOnlyByteObservable observable1,
      @Nonnull ReadOnlyByteObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() / observable2.getValue(), observable1, observable2);
  }

  /**
   * Divides the values of two short properties together.
   */
  @Nonnull
  static IntegerBinding divide(@Nonnull ReadOnlyShortObservable observable1,
      @Nonnull ReadOnlyShortObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() / observable2.getValue(), observable1, observable2);
  }

  /**
   * Divides the values of two integer properties together.
   */
  @Nonnull
  static IntegerBinding divide(@Nonnull ReadOnlyIntegerObservable observable1,
      @Nonnull ReadOnlyIntegerObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() / observable2.getValue(), observable1, observable2);
  }

  /**
   * Divides the values of two long properties together.
   */
  @Nonnull
  static LongBinding divide(@Nonnull ReadOnlyLongObservable observable1,
      @Nonnull ReadOnlyLongObservable observable2) {
    return LongBinding
        .create(() -> observable1.getValue() / observable2.getValue(), observable1, observable2);
  }

  /**
   * Divides the values of two float properties.
   */
  @Nonnull
  static FloatBinding divide(@Nonnull ReadOnlyFloatObservable observable1,
      @Nonnull ReadOnlyFloatObservable observable2) {
    return FloatBinding
        .create(() -> observable1.getValue() / observable2.getValue(), observable1, observable2);
  }

  /**
   * Divides the values of two double properties.
   */
  @Nonnull
  static DoubleBinding divide(@Nonnull ReadOnlyDoubleObservable observable1,
      @Nonnull ReadOnlyDoubleObservable observable2) {
    return DoubleBinding
        .create(() -> observable1.getValue() / observable2.getValue(), observable1, observable2);
  }

  // Modulus

  /**
   * <p>Calculates the remainder of two numeric values.</p>
   *
   * <p>When two values of different primitive number types are passed, the bigger or more precise
   * value is returned (for example when integer and byte are passed integer is returned).</p>
   */
  @Nonnull
  static NumberBinding<?> modulus(@Nonnull ReadOnlyNumberObservable<?> observable1,
      @Nonnull ReadOnlyNumberObservable<?> observable2) {
    // Floating Points
    if (observable1 instanceof ReadOnlyDoubleObservable
        || observable2 instanceof ReadOnlyDoubleObservable) {
      return DoubleBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.doubleValue() % number2.doubleValue();
      }, observable1, observable2);
    }

    if (observable1 instanceof ReadOnlyFloatObservable
        || observable2 instanceof ReadOnlyFloatObservable) {
      return FloatBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.floatValue() % number2.floatValue();
      }, observable1, observable2);
    }

    // Integers
    if (observable1 instanceof ReadOnlyLongObservable
        || observable2 instanceof ReadOnlyLongObservable) {
      return LongBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.longValue() % number2.longValue();
      });
    }

    if (observable1 instanceof ReadOnlyIntegerObservable
        || observable2 instanceof ReadOnlyIntegerObservable) {
      return IntegerBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.intValue() % number2.intValue();
      }, observable1, observable2);
    }

    if (observable1 instanceof ReadOnlyShortObservable
        || observable2 instanceof ReadOnlyShortObservable) {
      return IntegerBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.shortValue() % number2.shortValue();
      }, observable1, observable2);
    }

    if (observable1 instanceof ReadOnlyByteObservable
        || observable2 instanceof ReadOnlyByteObservable) {
      return IntegerBinding.create(() -> {
        Number number1 = observable1.get();
        Number number2 = observable2.get();

        if (number1 == null) {
          number1 = 0;
        }

        if (number2 == null) {
          number2 = 0;
        }

        return number1.byteValue() % number2.byteValue();
      }, observable1, observable2);
    }

    throw new IllegalArgumentException(
        "Unsupported number observable implementations: " + observable1.getClass() + " and "
            + observable2.getClass());
  }

  /**
   * Calculates the remainder of a division between two integer properties.
   */
  @Nonnull
  static IntegerBinding modulus(@Nonnull ReadOnlyByteObservable observable1,
      @Nonnull ReadOnlyByteObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() % observable2.getValue(), observable1, observable2);
  }

  /**
   * Calculates the remainder of a division between two short properties.
   */
  @Nonnull
  static IntegerBinding modulus(@Nonnull ReadOnlyShortObservable observable1,
      @Nonnull ReadOnlyShortObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() % observable2.getValue(), observable1, observable2);
  }

  /**
   * Calculates the remainder of a division between two integer properties.
   */
  @Nonnull
  static IntegerBinding modulus(@Nonnull ReadOnlyIntegerObservable observable1,
      @Nonnull ReadOnlyIntegerObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() % observable2.getValue(), observable1, observable2);
  }

  /**
   * Calculates the remainder of a division between two long properties.
   */
  @Nonnull
  static LongBinding modulus(@Nonnull ReadOnlyLongObservable observable1,
      @Nonnull ReadOnlyLongObservable observable2) {
    return LongBinding
        .create(() -> observable1.getValue() % observable2.getValue(), observable1, observable2);
  }

  /**
   * Calculates the remainder of a division between two float properties.
   */
  @Nonnull
  static FloatBinding modulus(@Nonnull ReadOnlyFloatObservable observable1,
      @Nonnull ReadOnlyFloatObservable observable2) {
    return FloatBinding
        .create(() -> observable1.getValue() % observable2.getValue(), observable1, observable2);
  }

  /**
   * Calculates the remainder of a division between two double properties.
   */
  @Nonnull
  static DoubleBinding modulus(@Nonnull ReadOnlyDoubleObservable observable1,
      @Nonnull ReadOnlyDoubleObservable observable2) {
    return DoubleBinding
        .create(() -> observable1.getValue() % observable2.getValue(), observable1, observable2);
  }

  // Rounding

  /**
   * Rounds off the value of the supplied float property.
   */
  @Nonnull
  static DoubleBinding floor(@Nonnull ReadOnlyFloatObservable observable) {
    return DoubleBinding.create(() -> Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied double property.
   */
  @Nonnull
  static DoubleBinding floor(@Nonnull ReadOnlyDoubleObservable observable) {
    return DoubleBinding.create(() -> Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied float property to the closest full byte value.
   */
  @Nonnull
  static ByteBinding floorToByte(@Nonnull ReadOnlyFloatObservable observable) {
    return ByteBinding.create(() -> (byte) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied double property to the closest full byte value.
   */
  @Nonnull
  static ByteBinding floorToByte(@Nonnull ReadOnlyDoubleObservable observable) {
    return ByteBinding.create(() -> (byte) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied float property to the closest full short value.
   */
  @Nonnull
  static ShortBinding floorToShort(@Nonnull ReadOnlyFloatObservable observable) {
    return ShortBinding.create(() -> (short) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied double property to the closest full short value.
   */
  @Nonnull
  static ShortBinding floorToShort(@Nonnull ReadOnlyDoubleObservable observable) {
    return ShortBinding.create(() -> (short) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied float property to the closest full integer value.
   */
  @Nonnull
  static IntegerBinding floorToInteger(@Nonnull ReadOnlyFloatObservable observable) {
    return IntegerBinding.create(() -> (int) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied double property to the closest full integer value.
   */
  @Nonnull
  static IntegerBinding floorToInteger(@Nonnull ReadOnlyDoubleObservable observable) {
    return IntegerBinding.create(() -> (int) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied float property to the closest full long value.
   */
  @Nonnull
  static LongBinding floorToLong(@Nonnull ReadOnlyFloatObservable observable) {
    return LongBinding.create(() -> (long) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied double property to the closest full long value.
   */
  @Nonnull
  static LongBinding floorToLong(@Nonnull ReadOnlyDoubleObservable observable) {
    return LongBinding.create(() -> (long) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied float property.
   */
  @Nonnull
  static DoubleBinding ceil(@Nonnull ReadOnlyFloatObservable observable) {
    return DoubleBinding.create(() -> Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied double property.
   */
  @Nonnull
  static DoubleBinding ceil(@Nonnull ReadOnlyDoubleObservable observable) {
    return DoubleBinding.create(() -> Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied float property to the closest full byte value.
   */
  @Nonnull
  static ByteBinding ceilToByte(@Nonnull ReadOnlyFloatObservable observable) {
    return ByteBinding.create(() -> (byte) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied double property to the closest full byte value.
   */
  @Nonnull
  static ByteBinding ceilToByte(@Nonnull ReadOnlyDoubleObservable observable) {
    return ByteBinding.create(() -> (byte) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied float property to the closest full short value.
   */
  @Nonnull
  static ShortBinding ceilToShort(@Nonnull ReadOnlyFloatObservable observable) {
    return ShortBinding.create(() -> (short) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied double property to the closest full short value.
   */
  @Nonnull
  static ShortBinding ceilToShort(@Nonnull ReadOnlyDoubleObservable observable) {
    return ShortBinding.create(() -> (short) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied float property to the closest full integer value.
   */
  @Nonnull
  static IntegerBinding ceilToInteger(@Nonnull ReadOnlyFloatObservable observable) {
    return IntegerBinding.create(() -> (int) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied double property to the closest full integer value.
   */
  @Nonnull
  static IntegerBinding ceilToInteger(@Nonnull ReadOnlyDoubleObservable observable) {
    return IntegerBinding.create(() -> (int) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied float property to the closest full long value.
   */
  @Nonnull
  static LongBinding ceilToLong(@Nonnull ReadOnlyFloatObservable observable) {
    return LongBinding.create(() -> (long) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied double property to the closest full long value.
   */
  @Nonnull
  static LongBinding ceilToLong(@Nonnull ReadOnlyDoubleObservable observable) {
    return LongBinding.create(() -> (long) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds the value of the supplied float property.
   */
  @Nonnull
  static IntegerBinding round(@Nonnull ReadOnlyFloatObservable observable) {
    return IntegerBinding.create(() -> Math.round(observable.getValue()), observable);
  }

  /**
   * Rounds the value of the supplied double property.
   */
  @Nonnull
  static LongBinding round(@Nonnull ReadOnlyDoubleObservable observable) {
    return LongBinding.create(() -> Math.round(observable.getValue()), observable);
  }

  /**
   * Rounds the value of the supplied float property to the closest full byte value.
   */
  @Nonnull
  static ByteBinding roundToByte(@Nonnull ReadOnlyFloatObservable observable) {
    return ByteBinding.create(() -> (byte) Math.round(observable.getValue()), observable);
  }

  /**
   * Rounds the value of the supplied double property to the closest full byte value.
   */
  @Nonnull
  static ByteBinding roundToByte(@Nonnull ReadOnlyDoubleObservable observable) {
    return ByteBinding.create(() -> (byte) Math.round(observable.getValue()), observable);
  }

  /**
   * Rounds the value of the supplied float property to the closest full short value.
   */
  @Nonnull
  static ShortBinding roundToShort(@Nonnull ReadOnlyFloatObservable observable) {
    return ShortBinding.create(() -> (short) Math.round(observable.getValue()), observable);
  }

  /**
   * Rounds the value of the supplied double property to the closest full short value.
   */
  @Nonnull
  static ShortBinding roundToShort(@Nonnull ReadOnlyDoubleObservable observable) {
    return ShortBinding.create(() -> (short) Math.round(observable.getValue()), observable);
  }

  // Min & Max

  /**
   * Creates a binding which evaluates the smallest value of all passed observables.
   */
  @Nonnull
  static ByteBinding min(@Nonnull ReadOnlyByteObservable... observables) {
    if (observables.length == 0) {
      throw new IllegalArgumentException(
          "Illegal binding configuration: Expected at least one observable");
    }

    return ByteBinding.create(() -> {
      byte value = observables[0].getValue();

      for (int i = 1; i < observables.length; ++i) {
        value = (byte) Math.min(value, observables[i].getValue());
      }

      return value;
    }, observables);
  }

  /**
   * Creates a binding which evaluates the biggest value of all passed observables.
   */
  @Nonnull
  static ByteBinding max(@Nonnull ReadOnlyByteObservable... observables) {
    if (observables.length == 0) {
      throw new IllegalArgumentException(
          "Illegal binding configuration: Expected at least one observable");
    }

    return ByteBinding.create(() -> {
      byte value = observables[0].getValue();

      for (int i = 1; i < observables.length; ++i) {
        value = (byte) Math.max(value, observables[i].getValue());
      }

      return value;
    }, observables);
  }

  /**
   * Creates a binding which evaluates the smallest value of all passed observables.
   */
  @Nonnull
  static ShortBinding min(@Nonnull ReadOnlyShortObservable... observables) {
    if (observables.length == 0) {
      throw new IllegalArgumentException(
          "Illegal binding configuration: Expected at least one observable");
    }

    return ShortBinding.create(() -> {
      short value = observables[0].getValue();

      for (int i = 1; i < observables.length; ++i) {
        value = (short) Math.min(value, observables[i].getValue());
      }

      return value;
    }, observables);
  }

  /**
   * Creates a binding which evaluates the biggest value of all passed observables.
   */
  @Nonnull
  static ShortBinding max(@Nonnull ReadOnlyShortObservable... observables) {
    if (observables.length == 0) {
      throw new IllegalArgumentException(
          "Illegal binding configuration: Expected at least one observable");
    }

    return ShortBinding.create(() -> {
      short value = observables[0].getValue();

      for (int i = 1; i < observables.length; ++i) {
        value = (short) Math.max(value, observables[i].getValue());
      }

      return value;
    }, observables);
  }

  /**
   * Creates a binding which evaluates the smallest value of all passed observables.
   */
  @Nonnull
  static IntegerBinding min(@Nonnull ReadOnlyIntegerObservable... observables) {
    if (observables.length == 0) {
      throw new IllegalArgumentException(
          "Illegal binding configuration: Expected at least one observable");
    }

    return IntegerBinding.create(() -> {
      int value = observables[0].getValue();

      for (int i = 1; i < observables.length; ++i) {
        value = Math.min(value, observables[i].getValue());
      }

      return value;
    }, observables);
  }

  /**
   * Creates a binding which evaluates the biggest value of all passed observables.
   */
  @Nonnull
  static IntegerBinding max(@Nonnull ReadOnlyIntegerObservable... observables) {
    if (observables.length == 0) {
      throw new IllegalArgumentException(
          "Illegal binding configuration: Expected at least one observable");
    }

    return IntegerBinding.create(() -> {
      int value = observables[0].getValue();

      for (int i = 1; i < observables.length; ++i) {
        value = Math.max(value, observables[i].getValue());
      }

      return value;
    }, observables);
  }

  /**
   * Creates a binding which evaluates the smallest value of all passed observables.
   */
  @Nonnull
  static LongBinding min(@Nonnull ReadOnlyLongObservable... observables) {
    if (observables.length == 0) {
      throw new IllegalArgumentException(
          "Illegal binding configuration: Expected at least one observable");
    }

    return LongBinding.create(() -> {
      long value = observables[0].getValue();

      for (int i = 1; i < observables.length; ++i) {
        value = Math.min(value, observables[i].getValue());
      }

      return value;
    }, observables);
  }

  /**
   * Creates a binding which evaluates the biggest value of all passed observables.
   */
  @Nonnull
  static LongBinding max(@Nonnull ReadOnlyLongObservable... observables) {
    if (observables.length == 0) {
      throw new IllegalArgumentException(
          "Illegal binding configuration: Expected at least one observable");
    }

    return LongBinding.create(() -> {
      long value = observables[0].getValue();

      for (int i = 1; i < observables.length; ++i) {
        value = Math.max(value, observables[i].getValue());
      }

      return value;
    }, observables);
  }

  /**
   * Creates a binding which evaluates the smallest value of all passed observables.
   */
  @Nonnull
  static FloatBinding min(@Nonnull ReadOnlyFloatObservable... observables) {
    if (observables.length == 0) {
      throw new IllegalArgumentException(
          "Illegal binding configuration: Expected at least one observable");
    }

    return FloatBinding.create(() -> {
      float value = observables[0].getValue();

      for (int i = 1; i < observables.length; ++i) {
        value = Math.min(value, observables[i].getValue());
      }

      return value;
    }, observables);
  }

  /**
   * Creates a binding which evaluates the biggest value of all passed observables.
   */
  @Nonnull
  static FloatBinding max(@Nonnull ReadOnlyFloatObservable... observables) {
    if (observables.length == 0) {
      throw new IllegalArgumentException(
          "Illegal binding configuration: Expected at least one observable");
    }

    return FloatBinding.create(() -> {
      float value = observables[0].getValue();

      for (int i = 1; i < observables.length; ++i) {
        value = Math.max(value, observables[i].getValue());
      }

      return value;
    }, observables);
  }

  /**
   * Creates a binding which evaluates the smallest value of all passed observables.
   */
  @Nonnull
  static DoubleBinding min(@Nonnull ReadOnlyDoubleObservable... observables) {
    if (observables.length == 0) {
      throw new IllegalArgumentException(
          "Illegal binding configuration: Expected at least one observable");
    }

    return DoubleBinding.create(() -> {
      double value = observables[0].getValue();

      for (int i = 1; i < observables.length; ++i) {
        value = Math.min(value, observables[i].getValue());
      }

      return value;
    }, observables);
  }

  /**
   * Creates a binding which evaluates the biggest value of all passed observables.
   */
  @Nonnull
  static DoubleBinding max(@Nonnull ReadOnlyDoubleObservable... observables) {
    if (observables.length == 0) {
      throw new IllegalArgumentException(
          "Illegal binding configuration: Expected at least one observable");
    }

    return DoubleBinding.create(() -> {
      double value = observables[0].getValue();

      for (int i = 1; i < observables.length; ++i) {
        value = Math.max(value, observables[i].getValue());
      }

      return value;
    }, observables);
  }
}
