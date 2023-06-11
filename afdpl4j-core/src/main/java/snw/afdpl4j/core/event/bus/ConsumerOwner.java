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

package snw.afdpl4j.core.event.bus;

/**
 * 表示一个事件监听器的所有者。<br>
 * 这个对象用于标识监听器，以便于调用 {@link WrappedEventBus#unregister(ConsumerOwner)} 方法，从而一次性注销所有绑定到此对象的监听器。<br>
 * 这个接口的对象对于你的代码应该是唯一的，不可变的。
 */
public interface ConsumerOwner {
}
