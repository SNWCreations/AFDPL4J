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

import snw.afdpl4j.core.internal.Request;

/**
 * 表示一个处理 爱发电 Webhook POST 请求的处理器。
 */
public abstract class Handler {

    /**
     * 尝试处理请求。
     *
     * @param request 请求
     */
    public final void tryHandle(Request request) {
        try {
            handle(request);
        } catch (Exception e) {
            handleException(e);
            if (!request.processed()) {
                request.write(e);
            }
        }
    }

    /**
     * 处理请求。
     *
     * @param request 请求对象
     * @throws Exception 任何异常
     */
    public abstract void handle(Request request) throws Exception;

    /**
     * 对于 {@link #tryHandle(Request)} 方法中捕获的异常的回调。
     *
     * @param e 异常
     */
    protected void handleException(Exception e) {
    }

}
