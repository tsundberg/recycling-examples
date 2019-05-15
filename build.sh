#!/usr/bin/env bash

time (

    if ! ./gradlew clean; then
        exit
    fi

    if ! ./gradlew cucumber -Dseam=model -Ddatabase=in-memory; then
        exit
    fi

    if ! ./gradlew cucumber -Dseam=model -Ddatabase=sql; then
        exit
    fi

    if ! ./gradlew cucumber -Dseam=rest -Ddatabase=in-memory; then
        exit
    fi

    if ! ./gradlew cucumber -Dseam=rest -Ddatabase=sql; then
        exit
    fi

    if ! ./gradlew cucumber -Dseam=web -Ddatabase=in-memory -Dbrowser=headless; then
        exit
    fi

    if ! ./gradlew cucumber -Dseam=web -Ddatabase=in-memory -Dbrowser=firefox; then
        exit
    fi

    if ! ./gradlew cucumber -Dseam=web -Ddatabase=in-memory -Dbrowser=chrome; then
        exit
    fi
)