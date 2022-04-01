# 数据转换服务
第一次启动，需要先启动spider-data-converter服务，启动过程中会创建对应队列。
再启动spider-receiver进行消息监听。（如果直接第一次直接启动消息监听会抛队列不存在异常）

注：rabbitmq队列创建完成后启动顺序就没有关系了

## **项目结构**

```
spider-data-converter
│ 
├─config 配置信息
│ 
├─modules 接口模块
│  └─test mq测试接口
│ 
├─DataApplication 项目启动类

```
<br>

## RabbitMq 安装（Docker版）

RabbitMQ 版本使用 `3.7.7-management`，使用 docker 运行，下面是所有步骤：

1. 下载镜像：`docker pull rabbitmq:3.7.7-management`

2. 运行容器：`docker run -d -p 5671:5671-p 5672:5672 -p 4369:4369 -p 15671:15671 -p 15672:15672 -p 25672:25672 --name rabbit-3.7.7 rabbitmq:3.7.7-management`

3. 进入容器：`docker exec -it rabbit-3.7.7 /bin/bash`

4. 更新`apt-get update`

5. 给容器安装 下载工具 wget：`apt-get install -y wget`

6. 下载插件包，因为我们的 `RabbitMQ` 版本为 `3.7.7` 所以我们安装 `3.7.x` 版本的延迟队列插件,可以在github找延迟队列插件下载地址`https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/releases/tag/v3.8.0`

   ```
   root@d2a4f47e4640:/# cd plugins
   root@f72ac937f2be:/plugins# wget https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/releases/download/v3.8.0/rabbitmq_delayed_message_exchange-3.8.0.ez
   ```

7. 启动延迟队列插件

   ```
   root@d2a4f47e4640:/plugins# rabbitmq-plugins enable rabbitmq_delayed_message_exchange
   The following plugins have been configured:
     rabbitmq_delayed_message_exchange
     rabbitmq_management
     rabbitmq_management_agent
     rabbitmq_web_dispatch
   Applying plugin configuration to rabbit@d2a4f47e4640...
   The following plugins have been enabled:
     rabbitmq_delayed_message_exchange
   ```

8. 退出容器：`exit`

9. 停止容器：`docker stop rabbit-3.7.7`

10. 启动容器：`docker start rabbit-3.7.7`


### rabbitmq管理界面
`http://127.0.0.1:15672/`
<br>
`guest/guest`

## MQ测试
本地需要启动rabbitmq，启动服务
```
# 测试直接模式发送 
http://127.0.0.1:10030/base/sendDirect?message=direct message

#测试主题模式发送1 
http://127.0.0.1:10030/base/sendTopic1?message=topic message 1

#测试主题模式发送2 
http://127.0.0.1:10030/base/sendTopic2?message=topic message 2

#测试延迟队列发送
http://127.0.0.1:10030/base/sendDelay?message=delay message, delay 5s
```


## 部署
- 打包 mvn clean package -P prod，根据环境进行打包（dev,test,prod)