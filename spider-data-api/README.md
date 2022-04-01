# 数据基础服务


## **项目结构**

```
spider-data-api
│
├─common 公共模块
│  ├─constant 通用常量类
│  ├─controller 基础控制层，主要用于过滤器异常统一跳转
│  ├─redis redis通用包
│  ├─service service通用包
│  └─enums 枚举类
│ 
├─config 配置信息
│ 
├─modules 接口模块
│  └─test  测试模块
│ 
├─DataApiApplication 项目启动类

```
<br>

## 示例
```
# 请求地址统一
http://127.0.0.1:10060/gateway

# 数据获取请求示例
{
    "sign": "bbcc493b4ed41c49b76ce15e990f3f42", 
    "method": "get.village.data", 
    "data": {
        "pageSize": 10, 
        "pageNo": 1
    }, 
    "appid": "93f83219ff", 
    "timestamp": "20210728130754"
}
```
## 打包
如果单独打包data-api按如下步骤进行打包
- 首先到spider-boot跟目录
  ```shell
    cd spider-boot/

- 执行如下打包命令,可以单独对wechat-api进行打包
  ```shell
    mvn clean package -pl spider-data-api -am -P prod，根据环境进行打包（dev,test,prod)
