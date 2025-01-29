package com.jfak.fizzbuzz.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfak.fizzbuzz.bean.FizzBuzzRequest;
import com.jfak.fizzbuzz.record.RestError;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FizzBuzzControllerTest {

	@LocalServerPort
    private int port;

    private String baseUrl;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper; 
    
    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/api/v1/fizzbuzz";
    }

    @Test
    void testFizzBuzzWithDefaultValues() {
       
        FizzBuzzRequest request = new FizzBuzzRequest();
        request.setInt1(3);
        request.setInt2(5);
        request.setStr1("Fizz");
        request.setStr2("Buzz");
        request.setLimit(15);

        // Configuration of request POST
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FizzBuzzRequest> entity = new HttpEntity<>(request, headers);

        // Request HTTP POST
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);

        // Verify expected response
        String expected = "1,2,Fizz,4,Buzz,Fizz,7,8,Fizz,Buzz,11,Fizz,13,14,FizzBuzz";
        assertEquals(expected, response.getBody());
    }

    @Test
    void testFizzBuzz2() {
        
        FizzBuzzRequest request = new FizzBuzzRequest();
        request.setInt1(2);
        request.setInt2(4);
        request.setStr1("Foo");
        request.setStr2("Bar");
        request.setLimit(10);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FizzBuzzRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);

        String expected = "1,Foo,3,FooBar,5,Foo,7,FooBar,9,Foo";
        assertEquals(expected, response.getBody());
    }

    @Test
    void testFizzBuzz3() {
      
        FizzBuzzRequest request = new FizzBuzzRequest();
        request.setInt1(3);
        request.setInt2(7);
        request.setStr1("Alpha");
        request.setStr2("Beta");
        request.setLimit(25);

         HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FizzBuzzRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);

        String expected = "1,2,Alpha,4,5,Alpha,Beta,8,Alpha,10,11,Alpha,13,Beta,Alpha,16,17,Alpha,19,20,AlphaBeta,22,23,Alpha,25";
        assertEquals(expected, response.getBody());
    }
    
    // test with missing parameter
    @Test
    void testFizzBuzzWithMissingParameter() {
      
        FizzBuzzRequest request = new FizzBuzzRequest();
        request.setInt1(3);
        request.setStr1("Alpha");
        request.setStr2("Beta");
        request.setLimit(20);

         HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FizzBuzzRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);

        HttpStatus expected = HttpStatus.BAD_REQUEST;
        assertEquals(expected, response.getStatusCode());
        
        try {
        	
        	RestError errorResponse = objectMapper.readValue(response.getBody(), RestError.class);
	      
        	assertEquals("All parameters int1, int2, limit, str1 and str2 are mandatory. Int1 and int2 can't be equal to 0.", errorResponse.detail());
        	assertEquals(400, errorResponse.status());
        
        } catch (Exception e) {
            fail("Error deserializing the response JSON: " + e.getMessage());
        }
        
    }
    
    
    // test with limit parameter too high
    @Test
    void testFizzBuzzWithLimitTooHigh() {
      
        FizzBuzzRequest request = new FizzBuzzRequest();
        request.setInt1(3);
        request.setInt2(5);
        request.setStr1("Fizz");
        request.setStr2("Buzz");
        request.setLimit(1000001);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FizzBuzzRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);

        HttpStatus expected = HttpStatus.BAD_REQUEST;
        assertEquals(expected, response.getStatusCode());
        
        try {
        	
        	RestError errorResponse = objectMapper.readValue(response.getBody(), RestError.class);
	      
        	assertEquals("Maximum limit allowed is 1.000.000", errorResponse.detail());
        	assertEquals(400, errorResponse.status());
        
        } catch (Exception e) {
            fail("Error deserializing the response JSON: " + e.getMessage());
        }
        
    }
    
}
	
