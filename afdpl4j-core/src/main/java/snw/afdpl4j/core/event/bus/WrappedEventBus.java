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

import net.kyori.event.EventBus;
import net.kyori.event.EventSubscriber;
import net.kyori.event.PostResult;
import net.kyori.event.SimpleEventBus;
import snw.afdpl4j.core.utils.UncheckedConsumer;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * 对 Kyori {@link EventBus} 进行的封装，使其支持基于 {@link ConsumerOwner} 的注销操作。
 *
 * @param <T> 同 {@link EventBus} 的 E 参数
 */
public final class WrappedEventBus<T> {
    private final Class<T> clazz;
    private final EventBus<T> bus;

    public WrappedEventBus(Class<T> clazz) {
        this.clazz = clazz;
        this.bus = new SimpleEventBus<>(clazz);
    }

    /**
     * 向此事件总线注册监听器。
     *
     * @param owner 唯一标识对象
     * @param consumer 监听器对象
     */
    public void register(ConsumerOwner owner, UncheckedConsumer<T> consumer) {
        bus.register(clazz, new WrappedConsumer<>(consumer::accept, owner));
    }

    /**
     * 从此事件总线上注销所有绑定在指定的标识对象上的监听器，<b>不影响其他事件总线对象</b>。
     *
     * @param owner 唯一标识对象
     */
    public void unregister(ConsumerOwner owner) {
        bus.unregister(
                (Predicate<EventSubscriber<?>>) i -> Objects.equals(((WrappedConsumer<?>) i).getOwner(), owner)
        );
    }

    /**
     * 用给定的事件对象调用此事件总线上的所有监听器。
     *
     * @param event 事件对象
     * @return 调用结果
     */
    public PostResult post(T event) {
        return bus.post(event);
    }

    /**
     * 注销此事件总线中的所有监听器。
     */
    public void clear() {
        bus.unregisterAll();
    }
}
