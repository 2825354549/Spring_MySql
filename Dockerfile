FROM openjdk:17
MAINTAINER ly <125727523+2825354549@users.noreply.github.com>

# 设置环境变量
ENV LANG C.UTF-8
ENV LANGUAGE zh_CN.UTF-8
ENV LC_ALL C.UTF-8

# 创建目录
RUN mkdir -p /opt/model

# 设置数据卷
VOLUME /tmp

# 添加应用
ADD ./target/Spring_MySql-0.0.1-SNAPSHOT.jar Spring_MySql.jar
# 从本地复制iInformer.onnx文件到镜像内的/opt/model目录
COPY ./src/main/resources/iInformer.onnx /opt/model/iInformer.onnx
# 暴露端口
EXPOSE 58098

# 更新Spring_MySql.jar文件的时间戳
RUN bash -c 'touch Spring_MySql.jar'
# 更新iInformer.onnx文件的时间戳
RUN bash -c 'touch /opt/model/iInformer.onnx'
# 设置启动命令
ENTRYPOINT ["java","-jar","/Spring_MySql.jar","--model.path=/opt/model/iInformer.onnx"]
