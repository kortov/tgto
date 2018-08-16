# Container with application
FROM openjdk:10-jre-slim
COPY /build/install/tgto /tgto
ENTRYPOINT /tgto/bin/tgto
