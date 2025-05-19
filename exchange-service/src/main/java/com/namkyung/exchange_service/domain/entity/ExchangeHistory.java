package com.namkyung.exchange_service.domain.entity;

import com.namkyung.exchange_service.domain.CurrencyType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class ExchangeHistory {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Transaction transaction;

    private BigDecimal sourceAmount;
    private BigDecimal targetAmount;

    @Enumerated(EnumType.STRING)
    private CurrencyType fromCurrency;

    @Enumerated(EnumType.STRING)
    private CurrencyType toCurrency;
}
