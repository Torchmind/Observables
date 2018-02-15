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
import edu.umd.cs.findbugs.annotations.NonNull;

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
  @NonNull
  static ByteBinding toByte(@NonNull ReadOnlyNumberObservable<?> observable) {
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
  @NonNull
  static ShortBinding toShort(@NonNull ReadOnlyNumberObservable<?> observable) {
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
  @NonNull
  static IntegerBinding toInteger(@NonNull ReadOnlyNumberObservable<?> observable) {
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
  @NonNull
  static LongBinding toLong(@NonNull ReadOnlyNumberObservable<?> observable) {
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
  @NonNull
  static FloatBinding toFloat(@NonNull ReadOnlyNumberObservable<?> observable) {
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
  @NonNull
  static DoubleBinding toDouble(@NonNull ReadOnlyNumberObservable<?> observable) {
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
  @NonNull
  static NumberBinding<?> add(@NonNull ReadOnlyNumberObservable<?> observable1,
      @NonNull ReadOnlyNumberObservable<?> observable2) {
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
  @NonNull
  static IntegerBinding add(@NonNull ReadOnlyByteObservable observable1,
      @NonNull ReadOnlyByteObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() + observable2.getValue(), observable1, observable2);
  }

  /**
   * Adds the values of two short properties together.
   */
  @NonNull
  static IntegerBinding add(@NonNull ReadOnlyShortObservable observable1,
      @NonNull ReadOnlyShortObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() + observable2.getValue(), observable1, observable2);
  }

  /**
   * Adds the values of two integer properties together.
   */
  @NonNull
  static IntegerBinding add(@NonNull ReadOnlyIntegerObservable observable1,
      @NonNull ReadOnlyIntegerObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() + observable2.getValue(), observable1, observable2);
  }

  /**
   * Adds the values of two long properties together.
   */
  @NonNull
  static LongBinding add(@NonNull ReadOnlyLongObservable observable1,
      @NonNull ReadOnlyLongObservable observable2) {
    return LongBinding
        .create(() -> observable1.getValue() + observable2.getValue(), observable1, observable2);
  }

  /**
   * Adds the values of two float properties.
   */
  @NonNull
  static FloatBinding add(@NonNull ReadOnlyFloatObservable observable1,
      @NonNull ReadOnlyFloatObservable observable2) {
    return FloatBinding
        .create(() -> observable1.getValue() + observable2.getValue(), observable1, observable2);
  }

  /**
   * Adds the values of two double properties.
   */
  @NonNull
  static DoubleBinding add(@NonNull ReadOnlyDoubleObservable observable1,
      @NonNull ReadOnlyDoubleObservable observable2) {
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
  @NonNull
  static NumberBinding<?> subtract(@NonNull ReadOnlyNumberObservable<?> observable1,
      @NonNull ReadOnlyNumberObservable<?> observable2) {
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
  @NonNull
  static IntegerBinding subtract(@NonNull ReadOnlyByteObservable observable1,
      @NonNull ReadOnlyByteObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() - observable2.getValue(), observable1, observable2);
  }

  /**
   * Subtracts the values of two short properties together.
   */
  @NonNull
  static IntegerBinding subtract(@NonNull ReadOnlyShortObservable observable1,
      @NonNull ReadOnlyShortObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() - observable2.getValue(), observable1, observable2);
  }

  /**
   * Subtracts the values of two integer properties together.
   */
  @NonNull
  static IntegerBinding subtract(@NonNull ReadOnlyIntegerObservable observable1,
      @NonNull ReadOnlyIntegerObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() - observable2.getValue(), observable1, observable2);
  }

  /**
   * Subtracts the values of two long properties together.
   */
  @NonNull
  static LongBinding subtract(@NonNull ReadOnlyLongObservable observable1,
      @NonNull ReadOnlyLongObservable observable2) {
    return LongBinding
        .create(() -> observable1.getValue() - observable2.getValue(), observable1, observable2);
  }

  /**
   * Subtracts the values of two float properties.
   */
  @NonNull
  static FloatBinding subtract(@NonNull ReadOnlyFloatObservable observable1,
      @NonNull ReadOnlyFloatObservable observable2) {
    return FloatBinding
        .create(() -> observable1.getValue() - observable2.getValue(), observable1, observable2);
  }

  /**
   * Subtracts the values of two double properties.
   */
  @NonNull
  static DoubleBinding subtract(@NonNull ReadOnlyDoubleObservable observable1,
      @NonNull ReadOnlyDoubleObservable observable2) {
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
  @NonNull
  static NumberBinding<?> multiply(@NonNull ReadOnlyNumberObservable<?> observable1,
      @NonNull ReadOnlyNumberObservable<?> observable2) {
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
  @NonNull
  static IntegerBinding multiply(@NonNull ReadOnlyByteObservable observable1,
      @NonNull ReadOnlyByteObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() * observable2.getValue(), observable1, observable2);
  }

  /**
   * Multiplies the values of two short properties together.
   */
  @NonNull
  static IntegerBinding multiply(@NonNull ReadOnlyShortObservable observable1,
      @NonNull ReadOnlyShortObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() * observable2.getValue(), observable1, observable2);
  }

  /**
   * Multiplies the values of two integer properties together.
   */
  @NonNull
  static IntegerBinding multiply(@NonNull ReadOnlyIntegerObservable observable1,
      @NonNull ReadOnlyIntegerObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() * observable2.getValue(), observable1, observable2);
  }

  /**
   * Multiplies the values of two long properties together.
   */
  @NonNull
  static LongBinding multiply(@NonNull ReadOnlyLongObservable observable1,
      @NonNull ReadOnlyLongObservable observable2) {
    return LongBinding
        .create(() -> observable1.getValue() * observable2.getValue(), observable1, observable2);
  }

  /**
   * Multiplies the values of two float properties.
   */
  @NonNull
  static FloatBinding multiply(@NonNull ReadOnlyFloatObservable observable1,
      @NonNull ReadOnlyFloatObservable observable2) {
    return FloatBinding
        .create(() -> observable1.getValue() * observable2.getValue(), observable1, observable2);
  }

  /**
   * Multiplies the values of two double properties.
   */
  @NonNull
  static DoubleBinding multiply(@NonNull ReadOnlyDoubleObservable observable1,
      @NonNull ReadOnlyDoubleObservable observable2) {
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
  @NonNull
  static NumberBinding<?> divide(@NonNull ReadOnlyNumberObservable<?> observable1,
      @NonNull ReadOnlyNumberObservable<?> observable2) {
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
  @NonNull
  static IntegerBinding divide(@NonNull ReadOnlyByteObservable observable1,
      @NonNull ReadOnlyByteObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() / observable2.getValue(), observable1, observable2);
  }

  /**
   * Divides the values of two short properties together.
   */
  @NonNull
  static IntegerBinding divide(@NonNull ReadOnlyShortObservable observable1,
      @NonNull ReadOnlyShortObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() / observable2.getValue(), observable1, observable2);
  }

  /**
   * Divides the values of two integer properties together.
   */
  @NonNull
  static IntegerBinding divide(@NonNull ReadOnlyIntegerObservable observable1,
      @NonNull ReadOnlyIntegerObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() / observable2.getValue(), observable1, observable2);
  }

  /**
   * Divides the values of two long properties together.
   */
  @NonNull
  static LongBinding divide(@NonNull ReadOnlyLongObservable observable1,
      @NonNull ReadOnlyLongObservable observable2) {
    return LongBinding
        .create(() -> observable1.getValue() / observable2.getValue(), observable1, observable2);
  }

  /**
   * Divides the values of two float properties.
   */
  @NonNull
  static FloatBinding divide(@NonNull ReadOnlyFloatObservable observable1,
      @NonNull ReadOnlyFloatObservable observable2) {
    return FloatBinding
        .create(() -> observable1.getValue() / observable2.getValue(), observable1, observable2);
  }

  /**
   * Divides the values of two double properties.
   */
  @NonNull
  static DoubleBinding divide(@NonNull ReadOnlyDoubleObservable observable1,
      @NonNull ReadOnlyDoubleObservable observable2) {
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
  @NonNull
  static NumberBinding<?> modulus(@NonNull ReadOnlyNumberObservable<?> observable1,
      @NonNull ReadOnlyNumberObservable<?> observable2) {
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
  @NonNull
  static IntegerBinding modulus(@NonNull ReadOnlyByteObservable observable1,
      @NonNull ReadOnlyByteObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() % observable2.getValue(), observable1, observable2);
  }

  /**
   * Calculates the remainder of a division between two short properties.
   */
  @NonNull
  static IntegerBinding modulus(@NonNull ReadOnlyShortObservable observable1,
      @NonNull ReadOnlyShortObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() % observable2.getValue(), observable1, observable2);
  }

  /**
   * Calculates the remainder of a division between two integer properties.
   */
  @NonNull
  static IntegerBinding modulus(@NonNull ReadOnlyIntegerObservable observable1,
      @NonNull ReadOnlyIntegerObservable observable2) {
    return IntegerBinding
        .create(() -> observable1.getValue() % observable2.getValue(), observable1, observable2);
  }

  /**
   * Calculates the remainder of a division between two long properties.
   */
  @NonNull
  static LongBinding modulus(@NonNull ReadOnlyLongObservable observable1,
      @NonNull ReadOnlyLongObservable observable2) {
    return LongBinding
        .create(() -> observable1.getValue() % observable2.getValue(), observable1, observable2);
  }

  /**
   * Calculates the remainder of a division between two float properties.
   */
  @NonNull
  static FloatBinding modulus(@NonNull ReadOnlyFloatObservable observable1,
      @NonNull ReadOnlyFloatObservable observable2) {
    return FloatBinding
        .create(() -> observable1.getValue() % observable2.getValue(), observable1, observable2);
  }

  /**
   * Calculates the remainder of a division between two double properties.
   */
  @NonNull
  static DoubleBinding modulus(@NonNull ReadOnlyDoubleObservable observable1,
      @NonNull ReadOnlyDoubleObservable observable2) {
    return DoubleBinding
        .create(() -> observable1.getValue() % observable2.getValue(), observable1, observable2);
  }

  // Rounding

  /**
   * Rounds off the value of the supplied float property.
   */
  @NonNull
  static DoubleBinding floor(@NonNull ReadOnlyFloatObservable observable) {
    return DoubleBinding.create(() -> Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied double property.
   */
  @NonNull
  static DoubleBinding floor(@NonNull ReadOnlyDoubleObservable observable) {
    return DoubleBinding.create(() -> Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied float property to the closest full byte value.
   */
  @NonNull
  static ByteBinding floorToByte(@NonNull ReadOnlyFloatObservable observable) {
    return ByteBinding.create(() -> (byte) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied double property to the closest full byte value.
   */
  @NonNull
  static ByteBinding floorToByte(@NonNull ReadOnlyDoubleObservable observable) {
    return ByteBinding.create(() -> (byte) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied float property to the closest full short value.
   */
  @NonNull
  static ShortBinding floorToShort(@NonNull ReadOnlyFloatObservable observable) {
    return ShortBinding.create(() -> (short) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied double property to the closest full short value.
   */
  @NonNull
  static ShortBinding floorToShort(@NonNull ReadOnlyDoubleObservable observable) {
    return ShortBinding.create(() -> (short) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied float property to the closest full integer value.
   */
  @NonNull
  static IntegerBinding floorToInteger(@NonNull ReadOnlyFloatObservable observable) {
    return IntegerBinding.create(() -> (int) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied double property to the closest full integer value.
   */
  @NonNull
  static IntegerBinding floorToInteger(@NonNull ReadOnlyDoubleObservable observable) {
    return IntegerBinding.create(() -> (int) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied float property to the closest full long value.
   */
  @NonNull
  static LongBinding floorToLong(@NonNull ReadOnlyFloatObservable observable) {
    return LongBinding.create(() -> (long) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds off the value of the supplied double property to the closest full long value.
   */
  @NonNull
  static LongBinding floorToLong(@NonNull ReadOnlyDoubleObservable observable) {
    return LongBinding.create(() -> (long) Math.floor(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied float property.
   */
  @NonNull
  static DoubleBinding ceil(@NonNull ReadOnlyFloatObservable observable) {
    return DoubleBinding.create(() -> Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied double property.
   */
  @NonNull
  static DoubleBinding ceil(@NonNull ReadOnlyDoubleObservable observable) {
    return DoubleBinding.create(() -> Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied float property to the closest full byte value.
   */
  @NonNull
  static ByteBinding ceilToByte(@NonNull ReadOnlyFloatObservable observable) {
    return ByteBinding.create(() -> (byte) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied double property to the closest full byte value.
   */
  @NonNull
  static ByteBinding ceilToByte(@NonNull ReadOnlyDoubleObservable observable) {
    return ByteBinding.create(() -> (byte) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied float property to the closest full short value.
   */
  @NonNull
  static ShortBinding ceilToShort(@NonNull ReadOnlyFloatObservable observable) {
    return ShortBinding.create(() -> (short) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied double property to the closest full short value.
   */
  @NonNull
  static ShortBinding ceilToShort(@NonNull ReadOnlyDoubleObservable observable) {
    return ShortBinding.create(() -> (short) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied float property to the closest full integer value.
   */
  @NonNull
  static IntegerBinding ceilToInteger(@NonNull ReadOnlyFloatObservable observable) {
    return IntegerBinding.create(() -> (int) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied double property to the closest full integer value.
   */
  @NonNull
  static IntegerBinding ceilToInteger(@NonNull ReadOnlyDoubleObservable observable) {
    return IntegerBinding.create(() -> (int) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied float property to the closest full long value.
   */
  @NonNull
  static LongBinding ceilToLong(@NonNull ReadOnlyFloatObservable observable) {
    return LongBinding.create(() -> (long) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds up the value of the supplied double property to the closest full long value.
   */
  @NonNull
  static LongBinding ceilToLong(@NonNull ReadOnlyDoubleObservable observable) {
    return LongBinding.create(() -> (long) Math.ceil(observable.getValue()), observable);
  }

  /**
   * Rounds the value of the supplied float property.
   */
  @NonNull
  static IntegerBinding round(@NonNull ReadOnlyFloatObservable observable) {
    return IntegerBinding.create(() -> Math.round(observable.getValue()), observable);
  }

  /**
   * Rounds the value of the supplied double property.
   */
  @NonNull
  static LongBinding round(@NonNull ReadOnlyDoubleObservable observable) {
    return LongBinding.create(() -> Math.round(observable.getValue()), observable);
  }

  /**
   * Rounds the value of the supplied float property to the closest full byte value.
   */
  @NonNull
  static ByteBinding roundToByte(@NonNull ReadOnlyFloatObservable observable) {
    return ByteBinding.create(() -> (byte) Math.round(observable.getValue()), observable);
  }

  /**
   * Rounds the value of the supplied double property to the closest full byte value.
   */
  @NonNull
  static ByteBinding roundToByte(@NonNull ReadOnlyDoubleObservable observable) {
    return ByteBinding.create(() -> (byte) Math.round(observable.getValue()), observable);
  }

  /**
   * Rounds the value of the supplied float property to the closest full short value.
   */
  @NonNull
  static ShortBinding roundToShort(@NonNull ReadOnlyFloatObservable observable) {
    return ShortBinding.create(() -> (short) Math.round(observable.getValue()), observable);
  }

  /**
   * Rounds the value of the supplied double property to the closest full short value.
   */
  @NonNull
  static ShortBinding roundToShort(@NonNull ReadOnlyDoubleObservable observable) {
    return ShortBinding.create(() -> (short) Math.round(observable.getValue()), observable);
  }

  // Min & Max

  /**
   * Creates a binding which evaluates the smallest value of all passed observables.
   */
  @NonNull
  static ByteBinding min(@NonNull ReadOnlyByteObservable... observables) {
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
  @NonNull
  static ByteBinding max(@NonNull ReadOnlyByteObservable... observables) {
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
  @NonNull
  static ShortBinding min(@NonNull ReadOnlyShortObservable... observables) {
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
  @NonNull
  static ShortBinding max(@NonNull ReadOnlyShortObservable... observables) {
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
  @NonNull
  static IntegerBinding min(@NonNull ReadOnlyIntegerObservable... observables) {
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
  @NonNull
  static IntegerBinding max(@NonNull ReadOnlyIntegerObservable... observables) {
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
  @NonNull
  static LongBinding min(@NonNull ReadOnlyLongObservable... observables) {
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
  @NonNull
  static LongBinding max(@NonNull ReadOnlyLongObservable... observables) {
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
  @NonNull
  static FloatBinding min(@NonNull ReadOnlyFloatObservable... observables) {
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
  @NonNull
  static FloatBinding max(@NonNull ReadOnlyFloatObservable... observables) {
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
  @NonNull
  static DoubleBinding min(@NonNull ReadOnlyDoubleObservable... observables) {
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
  @NonNull
  static DoubleBinding max(@NonNull ReadOnlyDoubleObservable... observables) {
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
