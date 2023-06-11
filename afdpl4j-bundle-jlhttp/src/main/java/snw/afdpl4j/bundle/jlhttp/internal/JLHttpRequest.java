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

import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import net.freeutils.httpserver.HTTPServer;
import snw.afdpl4j.core.internal.Request;
import snw.afdpl4j.core.utils.LazyLoadVar;

import static cn.hutool.core.io.IoUtil.readUtf8;

public final class JLHttpRequest implements Request {
    @Getter(AccessLevel.PRIVATE)
    private final HTTPServer.Request request;
    private final HTTPServer.Response response;
    private final LazyLoadVar<String> bodyRef = new LazyLoadVar<>(() -> readUtf8(getRequest().getBody()));

    public JLHttpRequest(HTTPServer.Request request, HTTPServer.Response response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String body() {
        return bodyRef.get();
    }

    @SneakyThrows
    @Override
    public void write(int status) {
        write(status, "{\"ec\":" + status + ",\"em\":\"\"}");
    }

    @SneakyThrows
    @Override
    public void write(int status, String content) {
        response.send(status, content);
    }

    @SneakyThrows
    @Override
    public void ok() {
        write(200, "{\"ec\":200,\"em\":\"\"}");
    }

    @Override
    public void write(Exception e) {
        final JsonObject object = new JsonObject();
        object.addProperty("ec", 400);
        object.addProperty("em", e.toString());
        final String json = object.toString();
        write(400, json);
    }

    @Override
    public boolean processed() {
        return response.headersSent();
    }
}
