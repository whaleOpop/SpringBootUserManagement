package com.sber.test.api.model;



import lombok.Data;
import lombok.Setter;
import org.hibernate.property.access.spi.Getter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

@Setter
@lombok.Getter
@Entity
@Table(name = "users")
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    private String fio;
    private int age;

}
