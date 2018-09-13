#!/bin/bash

set -e

echo "-> Login to docker hub"
echo "${DOCKER_PASSWORD}" | docker login -u "${DOCKER_USERNAME}" --password-stdin

echo "-> Pushing image irus/tgto:1.0.0-dev-b${TRAVIS_BUILD_NUMBER} to docker hub"
docker push irus/tgto
