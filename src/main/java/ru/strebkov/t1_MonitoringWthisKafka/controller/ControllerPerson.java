package ru.strebkov.t1_MonitoringWthisKafka.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.strebkov.t1_MonitoringWthisKafka.dto.PersonDto;
import ru.strebkov.t1_MonitoringWthisKafka.service.ServicePerson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
@Slf4j
public class ControllerPerson {

    private final ServicePerson service;

    private final KafkaTemplate<String, String> kafkaTemplate;


    @GetMapping("/all") //localhost:8080/persons/all
    public ResponseEntity<List<PersonDto>> getAllPersons() {
        List<PersonDto> listPerson = new ArrayList<PersonDto>(service.getAllPersons());
        if (listPerson.isEmpty()) {
           // kafkaTemplate.send("topic1", "Привет: NO_CONTENT");
            sendMessage("topic1", "sends asn Привет: NOT_Cont");
            log.info("Sending : {}", "Not-all");
            log.info("--------------------------------");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("Sending : {}", "OK-all");
        log.info("--------------------------------");
        //kafkaTemplate.send("topic1", "Привет: OK");
        sendMessage("topic1", "sends asn Привет: OK");
        return new ResponseEntity<>(listPerson, HttpStatus.OK);
    }

    @GetMapping("/{id}") //localhost:8080/persons/id
    public ResponseEntity<Optional<PersonDto>> getPersonById(@PathVariable(value = "id", required = false) Long id) {
        Optional<PersonDto> person = service.getPersonById(id);
        if (person.isPresent()) {
            log.info("Sending : {}", "OK-id");
            log.info("--------------------------------");
           // kafkaTemplate.send("topic2", "Привет: OK");
            sendMessage("topic2", "Привет: sends asn ok-id");
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
        log.info("Sending : {}", "NO-id");
        log.info("--------------------------------");
        //kafkaTemplate.send("topic2", "Привет: NOT_FOUND");
        sendMessage("topic2", "sends asn Привет: NOT_FOUND");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public void sendMessage(String topicName, String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }

}
