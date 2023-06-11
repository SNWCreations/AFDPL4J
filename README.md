# AFDPL4J

AFDPL4J (全称 "AFDian.net Payment Listener for Java") ，
一个非官方的，简易的，用于监听、解析爱发电订单成交事件并提供回调的 Java 库。

此项目有如下模块:
* afdpl4j-core - 核心解析 & 事件总线
* afdpl4j-bundle-jlhttp - 基于 [JLHTTP](https://www.freeutils.net/source/jlhttp) 的完整实现
* sample - 示例应用

## 快速开始

### 监听服务

```java
import snw.afdpl4j.core.Server;
import snw.afdpl4j.core.event.bus.EventBusHolder;
import snw.afdpl4j.core.event.bus.ConsumerOwner;

int port; // 将使用的端口，提供给具体服务器实现的构造方法。
Server server; // 服务器对象，具体见完整实现的文档以了解构造方法。
ConsumerOwner owner; // 你的监听器的唯一标识对象，避免其他也使用此模块的代码与你的代码冲突导致注销监听器异常。
EventBusHolder.ORDER_COMPLETED.register(owner, event -> {
    // 具体逻辑，这里的 event 类型是 snw.afdpl4j.event.PayOrderCompletedEvent
});
server.start(); // 启动服务器
// ....
server.stop(); // 关闭服务器
```

也可以看看 [sample 模块](./sample) 的 [snw.afdpl4j.sample.Main 类](./sample/src/main/java/snw/afdpl4j/sample/Main.java)
 以了解此库的运作流程。

了解更多？见 [core 模块](./afdpl4j-core) 的
[snw.afdpl4j.core.event.OrderCompletedEvent 类](./afdpl4j-core/src/main/java/snw/afdpl4j/core/event/OrderCompletedEvent.java) 。

### 创建订单链接

见 `afdpl4j-core` 模块中 `snw.afdpl4j.core.utils.OrderUtils` 类的 `createPayLink` 方法。

## 如何引入

选择一个完整实现开始使用！以下以 `afdpl4j-bundle-jlhttp` 为例，并假设您使用 Maven 。

首先，将 [JitPack.io](https://jitpack.io) 添加到你的项目，作为一个 Maven 远程仓库，以用于下载 AFDPL4J 。
* 具体步骤可见其页面。

然后，添加模块的坐标到您的构建文件，如 Maven 的是:

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0">
    <!-- 原有内容 -->
    <dependencies>
        <dependency>
            <groupId>com.github.SNWCreations.AFDPL4J</groupId>
            <artifactId>afdpl4j-bundle-jlhttp</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>
</project>
```

刷新项目，开始使用！

## 版权

Copyright (C) 2023 AFDPL4J contributors

此项目使用 Apache 2.0 License 授权。
