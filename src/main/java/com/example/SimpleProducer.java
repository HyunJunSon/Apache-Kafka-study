package com.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleProducer {
    private final static String TOPIC_NAME = "test";
    private final static String BOOTSTRAP_SERVERS = "my-kafka:9092";
}
