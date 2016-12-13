package com.example;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectionbugApplicationTests {

    @Autowired PersonRepository personRepository;
    @PersistenceContext EntityManager em;

    @Before
    public void setUp() {
        personRepository.save(Arrays.asList(
                new Person(new LocalDate(1976, 7, 15), "Joris"),
                new Person(new LocalDate(1978, 2, 19), "Sander")
        ));
        personRepository.deleteAll();
    }

    @Test
    public void findAllBirthDates() {
        List<LocalDate> birthDates = personRepository.findAllBirthDates();
        Assert.assertEquals(0, birthDates.size());
    }
    
    @Test
    public void findAllBirthDatesNativeJpa() {
        List<LocalDate> birthDates = em.createQuery("select distinct p.birthDate from Person p order by p.birthDate", LocalDate.class).getResultList();
        Assert.assertEquals(0, birthDates.size());
    }

    @Test
    public void findMinBirthDate() {
        LocalDate birthDate = personRepository.findMinBirthDate();
        Assert.assertNull(birthDate);
    }

    @Test
    public void findMinBirthDateNativeJpa() {
        LocalDate birthDate = em.createQuery("select min(p.birthDate) from Person p", LocalDate.class).getSingleResult();
        Assert.assertNull(birthDate);
    }

}
