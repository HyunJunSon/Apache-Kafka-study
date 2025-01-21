package com.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
public class SimpleProducer {
//    private final static Logger logger = getLogger(SimpleProducer.class);
    private final static String TOPIC_NAME = "test";
    private final static String BOOTSTRAP_SERVERS = "my-kafka:9092";


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(config);
        String messageValue = "hi my name is hyunjun2";
        ProducerRecord<String, String> record_ = new ProducerRecord<>(TOPIC_NAME, "Pangyo" ,messageValue);

        Future<RecordMetadata> future = producer.send(record_);
//        logger.info("레코드는 {}", record_);
        log.info("레코드는 {}", future);

        log.info("레코드 메타데이터 {}", future.get().toString());

        producer.flush();
        producer.close();
    }
}
