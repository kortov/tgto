# Container with application
FROM amazoncorretto:11.0.3
COPY /build/install/tgto /tgto
ENTRYPOINT /tgto/bin/tgto
