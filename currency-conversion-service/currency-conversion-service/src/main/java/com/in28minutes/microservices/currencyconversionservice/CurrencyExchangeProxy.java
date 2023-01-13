package com.in28minutes.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange", url = "localhost:8000")
@FeignClient(name = "currency-exchange") // Client side Load Balancing *
// the same app name from currency exchange service
// it's not ok to hardcode the url, because if we have multiple instances of microservices we should modify it everytime
// or, we can add all de ports oh those services, but in case of one of them is down we also have to modify
// !all the instances of all microservices will register with a service registry
// EXAMPLE: if the CurrencyConversion wants to talk to the CurrencyExchange,
// it will ask the ServiceRegistry(NamingServer) which are the active address of the CurrencyExchange
// and the ServiceRegistry(NamingServer) will return those address back to the CurrencyConversion
// and then the CurrencyConversion can send a request to CurrencyExchange
// LoadBalancer is doing this
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
// * Client side Load Balancing - automatically done by FeignClient and Eureka
// inside the Currency Conversion microservice there is a Loader Balancer which is talking to
// the Naming Server finding the instances and doing automatically load balancing between them
// (Currency Exchange - instance 1 and Currency Exchange - instance 2)