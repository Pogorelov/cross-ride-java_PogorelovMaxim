/**
 * 
 */
package com.crossover.techtrial.controller;

import com.crossover.techtrial.dto.PersonDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.repositories.PersonRepository;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author kshah
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {
  
  MockMvc mockMvc;
  
  @Mock
  private PersonController personController;
  
  @Autowired
  private TestRestTemplate template;
  
  @Autowired
  PersonRepository personRepository;

  private Long registeredPersonId;
  
  @Before
  public void setup() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    registerPerson();
  }

  private void registerPerson() {
    Person person = new Person();
    person.setName("Maxim");
    person.setEmail("mpogorelovm23@gmail.com");
    personRepository.save(person);
    registeredPersonId = person.getId();
  }
  
  @Test
  public void testPanelShouldBeRegistered() throws Exception {
    HttpEntity<Object> person = getHttpEntity(
        "{\"name\": \"test 1\", \"email\": \"test10000000000001@gmail.com\"," 
            + " \"registrationNumber\": \"41DCT\",\"registrationDate\":\"2018-08-08T12:12:12\" }");
    ResponseEntity<Person> response = template.postForEntity(
        "/api/person", person, Person.class);
    //Delete this user
    personRepository.deleteById(response.getBody().getId());
    Assert.assertEquals("test 1", response.getBody().getName());
    Assert.assertEquals(200,response.getStatusCode().value());
  }

  @Test
  public void shouldNotBeRegistered() throws Exception {
    HttpEntity<Object> person = getHttpEntity(
            "{\"unknown\": \"test 1\" }");
    ResponseEntity<Person> response = template.postForEntity(
            "/api/person", person, Person.class);
    //Delete this user
//    personRepository.deleteById(response.getBody().getId());
    Assert.assertEquals(null, response.getBody().getId());
  }

  @Test
  public void shouldFindPersonById() throws Exception {
    ResponseEntity<PersonDTO> response = template.getForEntity("/api/person/"+registeredPersonId, PersonDTO.class);
    assertEquals("mpogorelovm23@gmail.com",response.getBody().getEmail());
  }

  @Test
  public void shouldNotFindPersonById() throws Exception {
    ResponseEntity<PersonDTO> response = template.getForEntity("/api/person/9999", PersonDTO.class);
    assertNull(response.getBody());
  }

  @Test
  public void shouldNotFindWrongPersonById() throws Exception {
    ResponseEntity<PersonDTO> response = template.getForEntity("/api/person/sdksd", PersonDTO.class);
    assertEquals(null, response.getBody().getId());
  }

  @Test
  public void shouldFindAllPersons() throws Exception {
    ResponseEntity<List> response = template.getForEntity("/api/person", List.class);
    assertTrue(response.getBody().size() > 0);
  }

  private HttpEntity<Object> getHttpEntity(Object body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<Object>(body, headers);
  }

}
