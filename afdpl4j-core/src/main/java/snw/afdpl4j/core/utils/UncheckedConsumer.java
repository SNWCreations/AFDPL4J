/*
 * Copyright 2023 AFDPL4J contributors
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

package snw.afdpl4j.core.utils;

/**
 * 类似 {@link java.util.function.Consumer} ，但是此接口中的 <code>accept</code> 方法可以跑出 {@link Exception} 。
 * @param <T> {@link #accept} 方法将接受的参数类型
 */
@FunctionalInterface
public interface UncheckedConsumer<T> {

    void accept(T t) throws Throwable;
}
