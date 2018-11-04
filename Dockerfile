# Container with application
FROM openjdk:11.0-jre-slim
COPY /build/install/tgto /tgto
ENTRYPOINT /tgto/bin/tgto
