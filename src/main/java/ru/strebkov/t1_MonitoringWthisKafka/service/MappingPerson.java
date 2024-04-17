package ru.strebkov.t1_MonitoringWthisKafka.service;

import org.springframework.stereotype.Service;
import ru.strebkov.t1_MonitoringWthisKafka.dto.PersonDto;
import ru.strebkov.t1_MonitoringWthisKafka.entity.PersonEntity;
@Service
public class MappingPerson {

    public PersonDto mapToMyTaskDto(PersonEntity entity) {
        PersonDto dto = new PersonDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setSurname(entity.getSurname());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setCityOfLiving(entity.getCityOfLiving());

        return dto;
    }


    public PersonEntity mapToProductEntity(PersonDto dto) {
        PersonEntity entity = new PersonEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setSurname(dto.getSurname());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setCityOfLiving(dto.getCityOfLiving());

        return entity;
    }
}

