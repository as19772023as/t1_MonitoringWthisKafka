package ru.strebkov.t1_MonitoringWthisKafka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.strebkov.t1_MonitoringWthisKafka.dto.PersonDto;
import ru.strebkov.t1_MonitoringWthisKafka.entity.PersonEntity;
import ru.strebkov.t1_MonitoringWthisKafka.exception.NoSuchTasksEndpointException;
import ru.strebkov.t1_MonitoringWthisKafka.repository.RepositoryPerson;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicePersonImpl implements ServicePerson {
    private final RepositoryPerson repository;
    private final MappingPerson mapping;

    @Transactional
    @Override
    public List<PersonDto> getAllPersons() {
        return repository.findAll().stream()
                .map(mapping::mapToMyTaskDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Optional<PersonDto> getPersonById(Long id) {
//        return Optional.ofNullable(mapping.mapToMyTaskDto(repository.findById(id)
//               .orElse(new PersonEntity())));
        return Optional.ofNullable(mapping.mapToMyTaskDto(repository.findById(id)
                .orElseThrow(() -> new NoSuchTasksEndpointException("Такой задачи нет"))));
    }

    @ExceptionHandler(NoSuchTasksEndpointException.class)
    public ResponseEntity<String> handlerNoSuchError(NoSuchTasksEndpointException e) {
        return new ResponseEntity<>("Exception: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
