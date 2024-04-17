package ru.strebkov.t1_MonitoringWthisKafka.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(schema = "t1persons", name = "person")
public class PersonEntity {
    @Id
    @Column(name = "id_person")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 150, nullable = false)
    private String surname;
    @Column(nullable = false)
    private int age;
    @Column(name = "number_phone", nullable = false, length = 30)
    private String phoneNumber;
    @Column(name = "city_of_living", nullable = false, length = 200)
    private String cityOfLiving;

}
