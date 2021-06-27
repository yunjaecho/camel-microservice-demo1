#!/bin/bash

docker build -t camel-microservice-demo1 .
docker run -d -p 8080:8080 camel-microservice-demo1