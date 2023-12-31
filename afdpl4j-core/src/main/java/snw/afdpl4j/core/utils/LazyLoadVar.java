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

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * 表示一个遵循懒加载模式的对象引用。
 *
 * @param <T> 对象类型
 */
public final class LazyLoadVar<T> {
    private final AtomicReference<T> ref;
    private final Supplier<T> supplier;

    public LazyLoadVar(Supplier<T> supplier) {
        this.supplier = supplier;
        this.ref = new AtomicReference<>();
    }

    public T get() {
        return this.ref.updateAndGet(i -> {
            if (i != null) {
                return i;
            }
            return supplier.get();
        });
    }
}
