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

package snw.afdpl4j.core;

import snw.afdpl4j.core.internal.handler.Handler;

/**
 * 表示一个监听服务器。
 */
public interface Server {

    /**
     * 获取处理 POST 请求的处理器。
     *
     * @return 处理器
     */
    Handler getHandler();

    /**
     * 获取此服务器绑定的本地端口。
     *
     * @return 端口
     */
    int getPort();

    /**
     * 启动此服务器。若已经启动过，则什么也不做。
     */
    void start();

    /**
     * 关闭此服务器。若已经关闭，则什么也不做。
     */
    void stop();
}
