package com.crossover.techtrial.repository;

import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.repositories.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by pogorelov on 12/9/18.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    PersonRepository personRepository;

    private Long personID ;

    @Before
    public void addPerson(){
        Person person = new Person();
        person.setEmail("person@person.com");
        person.setName("person");
        person.setRegistrationNumber("4321");
        person = entityManager.persist(person);
        personID = person.getId();
    }

    @Test
    public void shouldFindById() {
        Optional<Person> personOpt = personRepository.findById(personID);
        assertTrue(personOpt.isPresent());
        Person person = personOpt.get();
        assertEquals(person.getName(),"person");
        assertEquals(person.getRegistrationNumber(),"4321");
        assertEquals(person.getEmail(),"person@person.com");
    }


    @Test
    public void shouldNotFindById() {
        Optional<Person> personOpt = personRepository.findById(999L);
        assertFalse(personOpt.isPresent());
    }

}