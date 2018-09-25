#!/bin/bash

set -e

echo "-> Login to docker hub"
echo "${DOCKER_PASSWORD}" | docker login -u "${DOCKER_USERNAME}" --password-stdin

echo "-> Pushing image heapy/tgto:b${TRAVIS_BUILD_NUMBER} to docker hub"
docker push heapy/tgto
