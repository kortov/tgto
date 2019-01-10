# Container with application
FROM openjdk:11.0.1-jre-slim-stretch
COPY /build/install/tgto /tgto
ENTRYPOINT /tgto/bin/tgto
