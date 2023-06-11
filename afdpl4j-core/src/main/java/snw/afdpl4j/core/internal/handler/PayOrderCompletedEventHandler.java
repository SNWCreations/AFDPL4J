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

package snw.afdpl4j.core.internal.handler;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import snw.afdpl4j.core.event.OrderCompletedEvent;
import snw.afdpl4j.core.event.bus.EventBusHolder;
import snw.afdpl4j.core.internal.Request;
import snw.afdpl4j.core.internal.gson.AFDGsonHolder;

/**
 * 将请求内容视作 {@link OrderCompletedEvent} 的请求处理器。<br>
 * 因当前爱发电仅提供这种类型的 Webhook 事件，故此处理器暂时够用。
 */
public class PayOrderCompletedEventHandler extends Handler {
    public static final PayOrderCompletedEventHandler INSTANCE = new PayOrderCompletedEventHandler();

    public void handle(Request request) throws Exception {
        final String body = request.body();
        final JsonObject bodyAsJsonObj = JsonParser.parseString(request.body()).getAsJsonObject();
        if (bodyAsJsonObj.get("ec").getAsInt() != 200) {
            request.write(400);
            return;
        }
        final OrderCompletedEvent event =
                AFDGsonHolder.GSON.fromJson(bodyAsJsonObj.get("data"), OrderCompletedEvent.class);
        request.ok();
        EventBusHolder.ORDER_COMPLETED.post(event).raise();
    }
}
