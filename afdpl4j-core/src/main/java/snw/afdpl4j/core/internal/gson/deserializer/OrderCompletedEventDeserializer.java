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

package snw.afdpl4j.core.internal.gson.deserializer;

import com.google.gson.*;
import snw.afdpl4j.core.event.OrderCompletedEvent;

import java.lang.reflect.Type;

/**
 * 解析爱发电 Webhook POST 内容为 {@link OrderCompletedEvent} 的解析器。
 */
public final class OrderCompletedEventDeserializer implements JsonDeserializer<OrderCompletedEvent> {
    public static final OrderCompletedEventDeserializer INSTANCE = new OrderCompletedEventDeserializer();

    private OrderCompletedEventDeserializer() {}

    @Override
    public OrderCompletedEvent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject data = jsonElement.getAsJsonObject();
        final String eventType = data.get("type").getAsString();
        if ("order".equals(eventType)) {
            final JsonObject orderData = data.get("order").getAsJsonObject();

            final String orderId = orderData.get("out_trade_no").getAsString();
            final String planId = orderData.get("plan_id").getAsString();
            final int productType = orderData.get("product_type").getAsInt();
            final String remark = orderData.get("remark").getAsString();
            final String customOrderId = orderData.get("custom_order_id").getAsString();
            final double showAmount = orderData.get("show_amount").getAsDouble();
            final double totalAmount = orderData.get("total_amount").getAsDouble();
            final int month = orderData.get("month").getAsInt();

            final String userId = orderData.get("user_id").getAsString();
            return new OrderCompletedEvent
                    (userId, orderId, planId, totalAmount, productType, remark, customOrderId, showAmount, month);
        }
        throw new JsonParseException("Illegal argument: not a pay order completed event, raw event type: " + eventType);
    }
}
