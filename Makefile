.PHONY: \
	build \
	build-yolo \
	unit-test \
	clean \

build:
	./mvnw verify -P jacoco

build-yolo:
	./mvnw verify -DskipTests

compile:
	./mvnw compile

unit-test:
	./mvnw clean test

clean:
	./mvnw clean

local-up:
	docker compose -f docker/docker-compose.yml up -d

local-down:
	docker compose -f docker/docker-compose.yml down