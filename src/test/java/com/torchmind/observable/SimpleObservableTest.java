package com.torchmind.observable;

import com.torchmind.observable.listener.ChangeListener;
import com.torchmind.observable.listener.ValidationListener;
import java.util.Objects;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * <p>Provides basic tests to evaluate the intended functionality of observables.</p>
 *
 * <p>Given that most implementation detail is provided by {@link AbstractObservable}, we do not
 * have to verify every specified type for its functionality.</p>
 *
 * TODO: Add tests to validate null compliance in primitive types
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class SimpleObservableTest {

  /**
   * Evaluates whether values are properly stored (even in respect to null values).
   */
  @Test
  public void testStorage() {
    SimpleObservable<String> observable1 = new SimpleObservable<>();
    SimpleObservable<String> observable2 = new SimpleObservable<>(null);
    SimpleObservable<String> observable3 = new SimpleObservable<>(null, "Test");

    Assert.assertNull(observable1.get());
    Assert.assertNull(observable2.get());
    Assert.assertNotNull(observable3.get());
    Assert.assertEquals("Test", observable3.get());
  }

  /**
   * Evaluates whether change listeners are called properly.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testListeners() {
    SimpleObservable<String> observable1 = new SimpleObservable<>();
    SimpleObservable<EqualityTestObject> observable2 = new SimpleObservable<>();

    {
      ChangeListener<String> listener = Mockito.mock(ChangeListener.class);
      observable1.registerListener(listener);

      observable1.set(null);
      observable1.set("Test");
      observable1.set("Test");
      observable1.set("Test2");
      observable1.set("Test2");
      observable1.set(null);

      Mockito.verify(listener, Mockito.times(1)).onChange(observable1, null, "Test");
      Mockito.verify(listener, Mockito.times(1)).onChange(observable1, "Test", "Test2");
      Mockito.verify(listener, Mockito.times(1)).onChange(observable1, "Test2", null);

      Mockito.verify(listener, Mockito.never()).onChange(observable1, null, null);
      Mockito.verify(listener, Mockito.never()).onChange(observable1, "Test", "Test");
      Mockito.verify(listener, Mockito.never()).onChange(observable1, "Test2", "Test2");
    }

    {
      ChangeListener<EqualityTestObject> listener = Mockito.mock(ChangeListener.class);
      observable2.registerListener(listener);

      observable2.set(null);
      observable2.set(new EqualityTestObject(0));
      observable2.set(new EqualityTestObject(0));
      observable2.set(new EqualityTestObject(2));
      observable2.set(new EqualityTestObject(2));
      observable2.set(null);

      Mockito.verify(listener, Mockito.times(1))
          .onChange(observable2, null, new EqualityTestObject(0));
      Mockito.verify(listener, Mockito.times(1))
          .onChange(observable2, new EqualityTestObject(0), new EqualityTestObject(2));
      Mockito.verify(listener, Mockito.times(1))
          .onChange(observable2, new EqualityTestObject(2), null);

      Mockito.verify(listener, Mockito.never()).onChange(observable2, null, null);
      Mockito.verify(listener, Mockito.never())
          .onChange(observable2, new EqualityTestObject(0), new EqualityTestObject(0));
      Mockito.verify(listener, Mockito.never())
          .onChange(observable2, new EqualityTestObject(2), new EqualityTestObject(2));
    }
  }

  /**
   * Evaluates whether unidirectional bindings relay their data as expected.
   */
  @Test
  public void testUnidirectionalBinding() {
    SimpleObservable<String> observable1 = new SimpleObservable<>();
    SimpleObservable<String> observable2 = new SimpleObservable<>(null, "Test");

    Assert.assertEquals(null, observable1.get());
    Assert.assertFalse(observable1.isBound());
    Assert.assertFalse(observable1.isBoundBidirectionally());
    Assert.assertFalse(observable1.isBoundBidirectionallyTo(observable2));
    Assert.assertEquals("Test", observable2.get());
    Assert.assertFalse(observable2.isBound());
    Assert.assertFalse(observable2.isBoundBidirectionally());
    Assert.assertFalse(observable2.isBoundBidirectionallyTo(observable1));

    observable1.bindTo(observable2);

    Assert.assertEquals("Test", observable1.get());
    Assert.assertTrue(observable1.isBound());
    Assert.assertFalse(observable1.isBoundBidirectionally());
    Assert.assertFalse(observable1.isBoundBidirectionallyTo(observable2));
    Assert.assertEquals("Test", observable2.get());
    Assert.assertFalse(observable2.isBound());
    Assert.assertFalse(observable2.isBoundBidirectionally());
    Assert.assertFalse(observable2.isBoundBidirectionallyTo(observable1));

    observable2.set("Test2");

    Assert.assertEquals("Test2", observable1.get());
    Assert.assertTrue(observable1.isBound());
    Assert.assertFalse(observable1.isBoundBidirectionally());
    Assert.assertFalse(observable1.isBoundBidirectionallyTo(observable2));
    Assert.assertEquals("Test2", observable2.get());
    Assert.assertFalse(observable2.isBound());
    Assert.assertFalse(observable2.isBoundBidirectionally());
    Assert.assertFalse(observable2.isBoundBidirectionallyTo(observable1));

    observable1.unbind();

    Assert.assertEquals("Test2", observable1.get());
    Assert.assertFalse(observable1.isBound());
    Assert.assertFalse(observable1.isBoundBidirectionally());
    Assert.assertFalse(observable1.isBoundBidirectionallyTo(observable2));
    Assert.assertEquals("Test2", observable2.get());
    Assert.assertFalse(observable2.isBound());
    Assert.assertFalse(observable2.isBoundBidirectionally());
    Assert.assertFalse(observable2.isBoundBidirectionallyTo(observable1));

    observable2.set("Test3");

    Assert.assertEquals("Test2", observable1.get());
    Assert.assertFalse(observable1.isBound());
    Assert.assertFalse(observable1.isBoundBidirectionally());
    Assert.assertFalse(observable1.isBoundBidirectionallyTo(observable2));
    Assert.assertEquals("Test3", observable2.get());
    Assert.assertFalse(observable2.isBound());
    Assert.assertFalse(observable2.isBoundBidirectionally());
    Assert.assertFalse(observable2.isBoundBidirectionallyTo(observable1));

    observable1.set("Test4");

    Assert.assertEquals("Test4", observable1.get());
    Assert.assertFalse(observable1.isBound());
    Assert.assertFalse(observable1.isBoundBidirectionally());
    Assert.assertFalse(observable1.isBoundBidirectionallyTo(observable2));
    Assert.assertEquals("Test3", observable2.get());
    Assert.assertFalse(observable2.isBound());
    Assert.assertFalse(observable2.isBoundBidirectionally());
    Assert.assertFalse(observable2.isBoundBidirectionallyTo(observable1));
  }

  /**
   * Evaluates whether the creation of unidirectional bindings causes the binding to lock its
   * internal value.
   */
  @Test(expected = IllegalStateException.class)
  public void testUnidirectionalBindingLock() {
    SimpleObservable<String> observable1 = new SimpleObservable<>();
    SimpleObservable<String> observable2 = new SimpleObservable<>();

    observable1.bindTo(observable2);
    observable1.set("Test");
  }

  /**
   * Evaluates whether bidirectional bindings relay their data as expected.
   */
  @Test
  public void testBidirectionalBinding() {
    SimpleObservable<String> observable1 = new SimpleObservable<>();
    SimpleObservable<String> observable2 = new SimpleObservable<>(null, "Test");

    Assert.assertEquals(null, observable1.get());
    Assert.assertFalse(observable1.isBound());
    Assert.assertFalse(observable1.isBoundBidirectionally());
    Assert.assertFalse(observable1.isBoundBidirectionallyTo(observable2));
    Assert.assertEquals("Test", observable2.get());
    Assert.assertFalse(observable2.isBound());
    Assert.assertFalse(observable2.isBoundBidirectionally());
    Assert.assertFalse(observable2.isBoundBidirectionallyTo(observable1));

    observable1.bindBidirectionallyTo(observable2);

    Assert.assertEquals("Test", observable1.get());
    Assert.assertTrue(observable1.isBound());
    Assert.assertTrue(observable1.isBoundBidirectionally());
    Assert.assertTrue(observable1.isBoundBidirectionallyTo(observable2));
    Assert.assertEquals("Test", observable2.get());
    Assert.assertTrue(observable2.isBound());
    Assert.assertTrue(observable2.isBoundBidirectionally());
    Assert.assertTrue(observable2.isBoundBidirectionallyTo(observable1));

    observable2.set("Test2");

    Assert.assertEquals("Test2", observable1.get());
    Assert.assertTrue(observable1.isBound());
    Assert.assertTrue(observable1.isBoundBidirectionally());
    Assert.assertTrue(observable1.isBoundBidirectionallyTo(observable2));
    Assert.assertEquals("Test2", observable2.get());
    Assert.assertTrue(observable2.isBound());
    Assert.assertTrue(observable2.isBoundBidirectionally());
    Assert.assertTrue(observable2.isBoundBidirectionallyTo(observable1));

    observable1.set("Test3");

    Assert.assertEquals("Test3", observable1.get());
    Assert.assertTrue(observable1.isBound());
    Assert.assertTrue(observable1.isBoundBidirectionally());
    Assert.assertTrue(observable1.isBoundBidirectionallyTo(observable2));
    Assert.assertEquals("Test3", observable2.get());
    Assert.assertTrue(observable2.isBound());
    Assert.assertTrue(observable2.isBoundBidirectionally());
    Assert.assertTrue(observable2.isBoundBidirectionallyTo(observable1));

    observable1.unbindBidirectional(observable2);

    Assert.assertEquals("Test3", observable1.get());
    Assert.assertFalse(observable1.isBound());
    Assert.assertFalse(observable1.isBoundBidirectionally());
    Assert.assertFalse(observable1.isBoundBidirectionallyTo(observable2));
    Assert.assertEquals("Test3", observable2.get());
    Assert.assertFalse(observable2.isBound());
    Assert.assertFalse(observable2.isBoundBidirectionally());
    Assert.assertFalse(observable2.isBoundBidirectionallyTo(observable2));

    observable1.set("Test4");

    Assert.assertEquals("Test4", observable1.get());
    Assert.assertFalse(observable1.isBound());
    Assert.assertFalse(observable1.isBoundBidirectionally());
    Assert.assertFalse(observable1.isBoundBidirectionallyTo(observable2));
    Assert.assertEquals("Test3", observable2.get());
    Assert.assertFalse(observable2.isBound());
    Assert.assertFalse(observable2.isBoundBidirectionally());
    Assert.assertFalse(observable2.isBoundBidirectionallyTo(observable2));

    observable2.set("Test5");

    Assert.assertEquals("Test4", observable1.get());
    Assert.assertFalse(observable1.isBound());
    Assert.assertFalse(observable1.isBoundBidirectionally());
    Assert.assertFalse(observable1.isBoundBidirectionallyTo(observable2));
    Assert.assertEquals("Test5", observable2.get());
    Assert.assertFalse(observable2.isBound());
    Assert.assertFalse(observable2.isBoundBidirectionally());
    Assert.assertFalse(observable2.isBoundBidirectionallyTo(observable2));
  }

  /**
   * Evaluates whether validation listeners are called and have authority over changes.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testValidation() {
    ValidationListener<String> listener = Mockito.mock(ValidationListener.class);

    SimpleObservable<String> observable1 = new SimpleObservable<>(listener, null);
    SimpleObservable<String> observable2 = new SimpleObservable<>(
        (observable, newValue) -> {
          if ("Test2".equals(newValue)) {
            return;
          }

          throw new IllegalArgumentException("Illegal value Test");
        }, null);

    observable1.set(null);
    observable1.set("Test");
    observable1.set("Test");
    observable1.set("Test2");
    observable1.set("Test2");
    observable1.set(null);

    Mockito.verify(listener, Mockito.times(2)).validate(observable1, null);
    Mockito.verify(listener, Mockito.times(2)).validate(observable1, "Test");
    Mockito.verify(listener, Mockito.times(2)).validate(observable1, "Test2");

    try {
      observable2.set("Test");
      throw new AssertionFailedError("Expected failure from validation listener");
    } catch (IllegalArgumentException ignore) {
    }

    Assert.assertEquals(null, observable2.get());

    observable2.set("Test2");

    Assert.assertEquals("Test2", observable2.get());
  }

  /**
   * Provides a test object which relies on equality to distinguish whether its internal value
   * changed.
   */
  public static class EqualityTestObject {

    private final int value;

    public EqualityTestObject(int value) {
      this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || this.getClass() != o.getClass()) {
        return false;
      }
      EqualityTestObject that = (EqualityTestObject) o;
      return this.value == that.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
      return Objects.hash(this.value);
    }
  }
}