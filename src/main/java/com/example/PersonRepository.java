package com.example;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("select distinct p.birthDate from Person p order by p.birthDate")
    List<LocalDate> findAllBirthDates();

    @Query("select min(p.birthDate) from Person p")
    LocalDate findMinBirthDate();
}
