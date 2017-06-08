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

package com.torchmind.observable.utility;

import java.lang.ref.WeakReference;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nonnull;

/**
 * Provides a set which relies on weak references to store its elements.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
public class WeakCopyOnWriteSet<E> extends AbstractSet<E> {

  private final CopyOnWriteArraySet<WeakReference<E>> elements;

  public WeakCopyOnWriteSet() {
    this.elements = new CopyOnWriteArraySet<>();
  }

  public WeakCopyOnWriteSet(@Nonnull Collection<E> collection) {
    this();
    this.addAll(collection);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean add(E e) {
    if (e == null) {
      throw new IllegalArgumentException("Cannot add null value");
    }

    if (this.contains(e)) {
      return false;
    }

    WeakReference<E> reference = new WeakReference<>(e);
    return this.elements.add(reference);
  }

  /**
   * Performs a set cleanup.
   */
  private void performCleanup() {
    this.elements.removeIf(element -> element.get() == null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean contains(Object o) {
    this.performCleanup();

    for (WeakReference<E> reference : this.elements) {
      if (Objects.equals(o, reference.get())) {
        return true;
      }
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public Iterator<E> iterator() {
    this.performCleanup();

    final Iterator<WeakReference<E>> it = this.elements.iterator();

    return new Iterator<E>() {
      @Override
      public boolean hasNext() {
        return it.hasNext();
      }

      @Override
      public E next() {
        return it.next().get();
      }
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean remove(Object o) {
    this.performCleanup();
    return this.elements.removeIf((r) -> Objects.equals(o, r.get()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int size() {
    this.performCleanup();
    return this.elements.size();
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
    if (!super.equals(o)) {
      return false;
    }
    WeakCopyOnWriteSet<?> that = (WeakCopyOnWriteSet<?>) o;
    return Objects.equals(this.elements, that.elements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.elements);
  }
}
