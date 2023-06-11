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

import cn.hutool.core.net.url.UrlBuilder;
import snw.afdpl4j.core.event.OrderCompletedEvent;

/**
 * 一些与订单有关的实用方法。
 */
public final class OrderUtils {

    /**
     * 创建一个对特定赞助方案的直达链接。
     *
     * @param planId 方案 ID
     * @param productType 方案类型，0 - 常规，1 - 售卖
     * @param customOrderId 自定义订单 ID ，在这里添加的内容最终可以通过 {@link OrderCompletedEvent#getCustomOrderId()} 获得
     * @param month 赞助月数
     * @return 直达链接
     */
    public static String createPayLink(String planId, int productType, String customOrderId, int month) {
        return UrlBuilder.ofHttp("https://afdian.net/order/create")
                .addQuery("plan_id", planId)
                .addQuery("product_type", productType)
                .addQuery("custom_order_id", customOrderId)
                .addQuery("month", month)
                .build();
    }

    /**
     * 创建一个对特定用户自选金额赞助的页面的直达链接。
     *
     * @param userId 用户 ID
     * @param price 赞助金额
     * @param month 赞助月数
     * @return 直达链接
     */
    public static String createPayLink(String userId, int price, int month) {
        return UrlBuilder.ofHttp("https://afdian.net/order/create")
                .addQuery("user_id", userId)
                .addQuery("custom_price", price)
                .addQuery("month", month)
                .build();
    }

    private OrderUtils() {}
}
