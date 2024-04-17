package ru.strebkov.t1_MonitoringWthisKafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.strebkov.t1_MonitoringWthisKafka.entity.PersonEntity;

@Repository
public interface RepositoryPerson extends JpaRepository<PersonEntity, Long> {
}
