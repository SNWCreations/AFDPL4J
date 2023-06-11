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

package snw.afdpl4j.bundle.jlhttp.internal;

import lombok.Getter;
import lombok.SneakyThrows;
import net.freeutils.httpserver.HTTPServer;
import snw.afdpl4j.bundle.jlhttp.internal.handler.JLHttpHandler;
import snw.afdpl4j.core.Server;
import snw.afdpl4j.core.internal.handler.Handler;
import snw.afdpl4j.core.internal.handler.PayOrderCompletedEventHandler;

public final class JLHttpServer implements Server {
    @Getter
    private final Handler handler;
    private final HTTPServer realServer;
    private final int port;

    public JLHttpServer(int port) {
        this(port, PayOrderCompletedEventHandler.INSTANCE);
    }

    public JLHttpServer(int port, Handler handler) {
        this.realServer = new HTTPServer(port);
        this.realServer
                .getVirtualHost(null)
                .addContext("/afd", new JLHttpHandler(handler), "POST");
        this.port = port;
        this.handler = handler;
    }

    @Override
    public int getPort() {
        return port;
    }

    @SneakyThrows
    @Override
    public void start() {
        this.realServer.start();
    }

    @Override
    public void stop() {
        this.realServer.stop();
    }
}
