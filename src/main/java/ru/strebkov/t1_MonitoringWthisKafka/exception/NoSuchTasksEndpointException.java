package ru.strebkov.t1_MonitoringWthisKafka.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class NoSuchTasksEndpointException extends  RuntimeException {


    public NoSuchTasksEndpointException(String msg) {
        super(msg);
    }
}
