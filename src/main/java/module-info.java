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

/**
 * Provides properties which may be used to observe any given change to their values or to
 * update their values when their dependencies change.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
module com.torchmind.observable {
  exports com.torchmind.observable;
  exports com.torchmind.observable.binding;
  exports com.torchmind.observable.concurrent;
  exports com.torchmind.observable.concurrent.primitive;
  exports com.torchmind.observable.listener;
  exports com.torchmind.observable.primitive;
  exports com.torchmind.observable.utility;

  requires static com.github.spotbugs.annotations;
}
