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

package snw.afdpl4j.core.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 表示爱发电订单成交的事件。
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class OrderCompletedEvent extends Event {
    private final String userId;
    private final String orderId;
    private final String planId;
    private final double realAmount;
    private final int productType;
    private final String remark;
    private final String customOrderId;
    private final double showAmount; // equals to show_amount in OrderCompletedEvent data!
    private final int month;
}
