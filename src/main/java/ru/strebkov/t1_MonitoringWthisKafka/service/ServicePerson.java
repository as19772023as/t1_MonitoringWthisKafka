package ru.strebkov.t1_MonitoringWthisKafka.service;

import ru.strebkov.t1_MonitoringWthisKafka.dto.PersonDto;

import java.util.List;
import java.util.Optional;

public interface ServicePerson {
    List<PersonDto> getAllPersons();

    Optional<PersonDto> getPersonById(Long id);
}
