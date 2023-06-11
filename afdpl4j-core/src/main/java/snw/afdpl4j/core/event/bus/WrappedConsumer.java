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

package snw.afdpl4j.core.event.bus;

import lombok.Data;
import net.kyori.event.EventSubscriber;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * 用于存放 {@link ConsumerOwner} 的事件监听器包装。
 *
 * @param <T> 同 {@link EventSubscriber} 的 E 参数
 */
@Data
final class WrappedConsumer<T> implements EventSubscriber<T> {
    private final EventSubscriber<T> delegate;
    private final ConsumerOwner owner;

    @Override
    public void invoke(@NonNull T event) throws Throwable {
        delegate.invoke(event);
    }
}
