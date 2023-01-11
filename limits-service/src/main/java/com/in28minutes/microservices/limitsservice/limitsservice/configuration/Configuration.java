package com.in28minutes.microservices.limitsservice.limitsservice.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// to map the values from app.properties with the fields in this class
// we use this annotation
@ConfigurationProperties("limits-service")
@Getter
@Setter
@Component
public class Configuration {
    private int minimum;
    private int maximum;
}
