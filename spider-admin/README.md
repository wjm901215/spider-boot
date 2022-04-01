#**项目说明** 

##后台系统
### **项目结构** 

```
spider-admin
├─db  项目SQL语句
│
├─common 公共模块
│  ├─aspect 系统日志
│  ├─exception 异常处理
│  ├─validator 后台校验
│  └─xss XSS过滤
│ 
├─config 配置信息
│ 
├─modules 功能模块
│  ├─job  定时任务模块
│  ├─oss  文件服务模块
│  └─sys  系统管理
│ 
├─AdminApplication 项目启动类
│  
├──resources 
│  ├─mapper SQL对应的XML文件
│  └─static 静态资源

```
<br> 

### **技术选型：**

- 核心框架：Spring Boot 2.0
- 安全框架：Apache Shiro 1.4
- 视图框架：Spring MVC 4.3
- 持久层框架：MyBatis 3.3
- 定时器：Quartz 2.3
- 数据库连接池：Druid 1.0
- 日志管理：SLF4J 1.7、Log4j
- 页面交互：Vue2.x 

<br>

###  **后端部署**

- 创建数据库，数据库编码为UTF-8
- 执行db/mysql.sql文件，初始化数据
- 修改application-dev.yml，更新DB账号和密码
- Eclipse、IDEA运行 ***Application.java，则可启动项目
- 打包 mvn clean package -P prod，根据环境进行打包（dev,test,prod)

<br>

###  **前端部署**

- 终端或CMD进入项目根路径
- 执行npm install 或者 cnpm install
- 执行npm run dev 启动项目 或 npm run build 打包项目

