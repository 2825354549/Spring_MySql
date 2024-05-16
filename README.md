# 文档
## 项目地址
https://github.com/2825354549/Spring_MySql
##项目展示
csdn：https://blog.csdn.net/m0_68676807/article/details/138440903
## 应用框架
spring-boot  web框架
onnxruntime 模型推理框架      
lombok Mybatis Mybatis-plus
MySQL  数据库
jdk17 java运行环境

## 注意事项
修改application.yaml里面的配置即可
数据处理需要的数据在data.properties里面
构建容器需要修改dockfile里面的几个文件路径 jar包路径和模型路径

## 项目结构
├─README.md
├─dockfile
├─pom.xml
├─logs
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─ly
│  │  │          ├─mysql
│  │  │          │  ├─domain
│  │  │          │  ├─mapper
│  │  │          │  └─service
│  │  │          │      └─impl
│  │  │          ├─springboot
│  │  │          │  ├─controller
│  │  │          │  └─service
│  │  │          │      └─impl
│  │  │          └─utils
│  │  └─resources
│  │      ├─mapper
│  │      ├─model
│  │      ├─static
│  │      └─templates
│  └─test
│      └─java
│          └─com
│              └─ly
│                  └─springboot
└─target
    ├─classes
    │  ├─com
    │  │  └─ly
    │  │      ├─mysql
    │  │      │  ├─domain
    │  │      │  ├─mapper
    │  │      │  └─service
    │  │      │      └─impl
    │  │      ├─springboot
    │  │      │  ├─controller
    │  │      │  └─service
    │  │      └─utils
    │  ├─mapper
    │  ├─META-INF
    │  └─model
    ├─generated-sources
    │  └─annotations
    ├─generated-test-sources
    │  └─test-annotations
    ├─maven-archiver
    ├─maven-status
    │  └─maven-compiler-plugin
    │      ├─compile
    │      │  └─default-compile
    │      └─testCompile
    │          └─default-testCompile
    ├─surefire-reports
    └─test-classes
    └─com
    └─ly
    └─springboot
