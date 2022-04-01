# DB模块
主要用于多个module之间公用相同dao、entity而抽离出的module。
公用相同业务而抽取出来的一个DB module，该模块主要是和db交互，不需要写复杂的业务逻辑。
service命名以Db开头，避免冲突。
 
该模块的目录结构如下 

## **项目结构**

```
spider-db
│ 
├─db DB模块
│  └─sys 通用系统业务模块
│      └─dao dao包
│      └─entity 实体包
│      └─service 接口
```
<br>