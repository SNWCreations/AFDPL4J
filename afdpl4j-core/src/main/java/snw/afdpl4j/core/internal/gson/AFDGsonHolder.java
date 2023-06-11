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

package snw.afdpl4j.core.internal.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import snw.afdpl4j.core.event.OrderCompletedEvent;
import snw.afdpl4j.core.internal.gson.deserializer.OrderCompletedEventDeserializer;

/**
 * @see #GSON
 */
public final class AFDGsonHolder {
    /**
     * 用于解析事件内容的 {@link Gson} 对象。
     */
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(OrderCompletedEvent.class, OrderCompletedEventDeserializer.INSTANCE)
            .disableHtmlEscaping()
            .create();

    private AFDGsonHolder() {}
}
