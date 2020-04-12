#!/usr/bin/env bash
docker run --name pms -e POSTGRES_PASSWORD=pmsPassword -d postgres:12.2-alpine