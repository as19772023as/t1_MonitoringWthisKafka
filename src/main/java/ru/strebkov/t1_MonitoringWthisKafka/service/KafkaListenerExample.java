package ru.strebkov.t1_MonitoringWthisKafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

//@Service
@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaListenerExample {

    // private final KafkaTemplate<String, String> template;
   // @RetryableTopic(attempts = "5", backoff = @Backoff(delay = 2000, maxDelay = 10000, multiplier = 2))
    @KafkaListener(topics = "topic1", groupId = "groupId1")
    void listener1(String data) {
        log.info("Received message [{}] in groupId1", data);
    }

    @KafkaListener(topics = "topic2", groupId = "groupId2")
    void listener2(String data) {
        log.info("Received message [{}] in groupId2", data);
    }

    @KafkaListener(topics = "topic2", groupId = "group2")
    void listener(@Payload String data,
                  @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                  @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Received message [{}] from group1, partition-{} with offset-{}",
                data,
                partition,
                offset);
    }

}
