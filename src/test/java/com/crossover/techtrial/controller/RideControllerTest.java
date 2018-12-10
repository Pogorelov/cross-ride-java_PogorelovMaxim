package com.crossover.techtrial.controller;
import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.repositories.PersonRepository;
import com.crossover.techtrial.repositories.RideRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.crossover.techtrial.constants.ConstantsTest.*;
import static com.crossover.techtrial.contants.ConstantsApi.*;

/**
 * Created by pogorelov on 12/9/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RideControllerTest {

    MockMvc mockMvc;

    @Mock
    private RideController rideController;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    RideRepository rideRepository;

    @Autowired
    PersonRepository personRepository;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(rideController).build();

        HttpEntity<Object> person = getHttpEntity(DRIVER_100);
        template.postForEntity(API_PERSON, person, Person.class);

        person = getHttpEntity(RIDER_900);
        template.postForEntity(API_PERSON, person, Person.class);

        HttpEntity<Object> ride = getHttpEntity(RIDE_1000_OK);
        template.postForEntity(API_RIDE, ride, Ride.class);
    }

    @Test
    public void shouldRegisterRider() throws Exception {
        HttpEntity<Object> ride = getHttpEntity(RIDE_100_OK);
        ResponseEntity<Ride> response = template.postForEntity(API_RIDE, ride, Ride.class);
        Assert.assertEquals(SUCCESS,response.getStatusCode().value());
    }

    @Test
    public void shouldFailRideWrongTime() throws Exception {
        HttpEntity<Object> ride = getHttpEntity(RIDE_100_WRONG_ENDTIME);
        ResponseEntity<Ride> response = template.postForEntity(API_RIDE, ride, Ride.class);

        Assert.assertEquals(ERROR_REQUEST,response.getStatusCode().value());
    }

    @Test
    public void shouldFailDriverDoesNotExist() throws Exception {
        HttpEntity<Object> ride = getHttpEntity(RIDE_200_WRONG_DRIVER_NOT_EXISTS);
        ResponseEntity<Ride> response = template.postForEntity(API_RIDE, ride, Ride.class);

        Assert.assertEquals(ERROR_REQUEST,response.getStatusCode().value());
    }

    @Test
    public void testRideWithInexistentRiderShouldFail() throws Exception {
        HttpEntity<Object> ride = getHttpEntity(RIDE_300_WRONG_RIDER_NOT_EXISTS);
        ResponseEntity<Ride> response = template.postForEntity(API_RIDE, ride, Ride.class);

        Assert.assertEquals(ERROR_REQUEST,response.getStatusCode().value());
    }

    @Test
    public void testRideWithNullFieldsShouldFail() throws Exception {
        HttpEntity<Object> ride = getHttpEntity(RIDE_400_WRONG_NULL_FIELDS);
        ResponseEntity<Ride> response = template.postForEntity(API_RIDE, ride, Ride.class);

        Assert.assertEquals(ERROR_REQUEST,response.getStatusCode().value());
    }

    @Test
    public void testGetRideById() throws Exception {

        ResponseEntity<Ride> response = template.getForEntity(API_RIDE+"/1", Ride.class);

        Assert.assertEquals(SUCCESS,response.getStatusCode().value());
        Assert.assertEquals(1L,response.getBody().getDriver().getId().longValue());
        Assert.assertEquals(10L,response.getBody().getRider().getId().longValue());
    }

    @Test
    public void testGetRideByIdReturnsNotFound() throws Exception {
        ResponseEntity<Ride> response = template.getForEntity(API_RIDE+"/9999", Ride.class);

        Assert.assertEquals(ERROR_NOT_FOUND,response.getStatusCode().value());
    }

    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<Object>(body, headers);
    }

    @After
    public void after() throws Exception {
        // Delete drivers
        if (personRepository.findById(100L).isPresent()) {
            personRepository.deleteById(100L);
        }
        if (personRepository.findById(900L).isPresent()) {
            personRepository.deleteById(900L);
        }
        // Delete rides
        if (rideRepository.findById(100L).isPresent()) {
            rideRepository.deleteById(100L);
        }
        if (rideRepository.findById(1000L).isPresent()) {
            rideRepository.deleteById(1000L);
        }
    }

}
