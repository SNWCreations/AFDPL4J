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

package snw.afdpl4j.core.internal;

/**
 * 表示一个 HTTP POST 请求。
 */
public interface Request {

    /**
     * 获取表示请求内容的，以 UTF-8 编码的字符串。
     *
     * @return 请求内容
     */
    String body();

    /**
     * 发送回复，但只发送提供的 HTTP 状态码。
     * @param status 状态码
     */
    void write(int status);

    /**
     * 发送回复。
     * @param status 状态码
     * @param content 内容
     */
    void write(int status, String content);

    /**
     * 发送 HTTP 200 OK 。
     */
    void ok();

    /**
     * 发送 HTTP 400 回复，并附上异常信息。
     *
     * @param e 异常对象
     */
    void write(Exception e);

    /**
     * 如果此请求已被处理，返回 <code>true</code>。
     *
     * @return 请求是否被处理
     */
    boolean processed();
}
