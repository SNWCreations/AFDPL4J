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

package snw.afdpl4j.sample;

import snw.afdpl4j.bundle.jlhttp.internal.JLHttpServer;
import snw.afdpl4j.core.Server;
import snw.afdpl4j.core.event.bus.ConsumerOwner;
import snw.afdpl4j.core.event.bus.EventBusHolder;
import snw.afdpl4j.core.internal.Request;
import snw.afdpl4j.core.internal.handler.Handler;
import snw.afdpl4j.core.internal.handler.PayOrderCompletedEventHandler;

public final class Main {
    public static void main(String[] args) {
        int port = 1145; // 服务将使用的端口号
        Handler handler = new Handler() {
            @Override
            public void handle(Request request) throws Exception {
                PayOrderCompletedEventHandler.INSTANCE.handle(request);
            }

            @Override
            protected void handleException(Exception e) {
                // 异常处理代码
            }
        };
        final Server server = new JLHttpServer(port, handler); // 服务器对象
        final ConsumerOwner owner = new ConsumerOwner(){}; // 你可以将 ConsumerOwner 接口应用到一些单例类上？
        EventBusHolder.ORDER_COMPLETED.register(owner, System.out::println); // 注册事件监听器，这里仅将事件输出到控制台
        server.start(); // 启动服务
        System.out.println("Server is listening on port " + port);
        // main 方法结束后，JVM 进程也不会结束，因为 HTTP 服务器的线程还在运行，而且它们不是守护线程。
        // 去 https://afdian.net/p/9c65d9cc617011ed81c352540025c377 获取订单完成的 JSON 例子 (详见其 "Webhook" 一节)，
        // 在运行着这个服务的机器上使用接口测试工具 POST 到 http://localhost:1145/afd 看看效果？
    }
}
