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

import snw.afdpl4j.core.event.OrderCompletedEvent;

/**
 * 这里存放各种事件对应的事件总线。
 */
public final class EventBusHolder {
    public static final WrappedEventBus<OrderCompletedEvent> ORDER_COMPLETED
            = new WrappedEventBus<>(OrderCompletedEvent.class);

    private EventBusHolder() {}
}
