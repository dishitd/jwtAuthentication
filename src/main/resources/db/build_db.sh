#!/usr/bin/env bash
docker rm postgres
docker build -t postgres:12.2-alpine .
docker run --name postgres -e POSTGRES_PASSWORD=pmsPassword -d postgres:12.2-alpine