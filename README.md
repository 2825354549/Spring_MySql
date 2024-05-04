# 文档
## 项目地址
https://github.com/2825354549/Spring_MySql

## 应用框架
spring-boot     web框架
onnxruntime 模型推理框架      
lombok Mybatis Mybatis-plus
MySQL  数据库
jdk17 java运行环境

## 注意事项
修改application.yaml里面的配置即可
数据处理需要的数据在data.properties里面
构建容器需要修改dockfile里面的几个文件路径 jar包路径和模型路径

## 项目结构
.
├── README.md
├── dockfile
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── ly
│   │   │           ├── SpringMySqlApplication.java
│   │   │           ├── mysql
│   │   │           ├── springboot
│   │   └── resources