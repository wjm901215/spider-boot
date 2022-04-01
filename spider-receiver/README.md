# 数据监听服务
第一次启动，需要先启动spider-data-converter服务，启动过程中会创建对应队列。
再启动spider-receiver进行消息监听。（如果直接第一次直接启动消息监听会抛队列不存在异常）

注：rabbitmq队列创建完成后启动顺序就没有关系了

## **项目结构**

```
spider-receiver
│ 
├─handler 消息监听
│ 
├─modules 接口模块
│  └─test mq测试接口
│ 
├─ReceiverApplication 项目启动类

```
<br>

## MQ测试
本地需要启动rabbitmq，启动spider-data-converter服务
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