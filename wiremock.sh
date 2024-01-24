#!/usr/bin/env bash

# Execute Wiremock
# http://localhost:8080/__admin/recorder
# http://localhost:8080/__admin/mappings
# http://localhost:8080/__admin/requests

# --https-port 8443
# https://api.openai.com/v1/chat/completions

java -jar ~/.m2/repository/org/wiremock/wiremock-standalone/3.3.1/wiremock-standalone-3.3.1.jar --port 8089 --verbose
