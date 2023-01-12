package com.in28minutes.microservices.currencyexchangeservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurrencyExchange {
    private long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private String environment; // to know what instance we call
}
