FROM java:8-jre
RUN mkdir -p /app/target && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
WORKDIR /app
COPY target /app/target
EXPOSE 8081
ENTRYPOINT ["java" ,"-jar", "/app/target/luoo-muziko-1.0.jar"]
