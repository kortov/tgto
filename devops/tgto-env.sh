#!/bin/bash

export TGTO_ENV="prod"
export TGTO_BOT_TOKEN=""
export TGTO_DATA=""
export TGTO_SENTRY_DSN=""
export TGTO_BOT_NAME="ToRssBot"
export TGTO_SYSTEM_USER="$(id -u):$(id -g)"
export TGTO_OPTS="-Xmx256m"
