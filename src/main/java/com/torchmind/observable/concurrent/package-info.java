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
 * <p>Provides thread safe immutable implementations which ensure their local state stays valid even
 * when multiple threads access their memory at the same time without the need of outside locking or
 * synchronization operations.</p>
 *
 * <p>Please note that great care should be taken when binding to non thread safe implementations of
 * the specification as calls may come from different threads at the same time. As such, the use of
 * bidirectional bindings between thread safe and non thread safe bindings is highly
 * discouraged.</p>
 *
 * <p>Note however, that unidirectional bindings which cause thread safe implementations to be
 * called by a non thread safe implementations are generally safe as they do not violate this API
 * contract.</p>
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
package com.torchmind.observable.concurrent;
