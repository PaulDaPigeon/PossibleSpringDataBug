package com.example;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person extends AbstractPersistable<Long> {
    private LocalDate birthDate;
    private String name;

    public Person(LocalDate birthDate, String name) {
        this.birthDate = birthDate;
        this.name = name;
    }

    protected Person() {}

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }
}
