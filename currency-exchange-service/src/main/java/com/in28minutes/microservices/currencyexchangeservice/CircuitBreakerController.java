package com.in28minutes.microservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse") // we set in the app.properties the maximum retry attempts to 5
    // if there's any failure in the execution of this method
    // it will retry three times, and if it will fail every time, it returns an error

//    @CircuitBreaker(name = "deafault", fallbackMethod = "hardcodedResponse")
    // if a microservice is down, the circuit breaker will return a default response back directly,
    // without calling and adding load to the falling microservice

//    @RateLimiter(name = "default")
    // in 10 seconds I want to allow only 10000 calls to the sample-api
    // we are setting a time period and during that I only want to allow a specific number of calls for this api
    // for all the API present here we can set different rate limits
    // we can have different name for each API
    // we configure the limits in the app.prop

    // we can configure how many concurrent calls are allowed
    @Bulkhead(name = "sample-api")
    // also in app.prop
    public String sampleApi(){
        logger.info("Sample API call received");
//        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
//        return forEntity.getBody();
        return "sample-api"; // for rate limiter and bulkhead
    }

    // we need Exception as a parameters because we retrieve NoSuchMethodException
    // the method should have a Throwable argument
    public String hardcodedResponse(Exception ex){
        return "fallback-response";
    }
}
