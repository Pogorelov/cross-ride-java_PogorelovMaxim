package com.crossover.techtrial.service;

import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.repositories.PersonRepository;
import com.crossover.techtrial.service.impl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by pogorelov on 12/9/18.
 */


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PersonServiceTest {

    @Mock
    PersonRepository personRepository ;

    @InjectMocks
    PersonServiceImpl personService;


    @Test
    public void shouldNotFindPerson() {
        when(personRepository.findById(1l)).thenReturn(Optional.empty());

        assertNull(personService.findById(1l));
    }

    @Test
    public void shouldFindPerson() {
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(new Person()));

        Person person = personService.findById(999L);
        assertNotNull(person);
    }

    @Test
    public void shouldGetAllEmpty(){
        when(personRepository.findAll()).thenReturn(Collections.emptyList());

        assertEquals(0,personService.getAll().size());
    }

    @Test
    public void shouldAllHasPersons(){
        List list = Arrays.asList(new Person(),new Person(),new Person(),new Person());
        when(personRepository.findAll()).thenReturn(list);

        assertEquals(4,personService.getAll().size());
    }

    @Test
    public void shouldSavePerson(){
        Person person = new Person();
        person.setEmail("person@person.com");
        person.setName("Person");
        person.setRegistrationNumber("321");

        when(personRepository.save(person)).thenReturn(person);

        assertEquals(person,personService.save(person));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSavePersonWithoutEmail(){
        Person person = new Person();
        when(personRepository.save(person)).thenThrow(new ConstraintViolationException(null));

        personService.save(person);
    }

}