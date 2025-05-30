package com.namkyung.exchange_service.domain.entity;

import com.namkyung.exchange_service.domain.CurrencyType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class ExchangeRate {

    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private CurrencyType fromCurrency; // 예시. KRW

    @Enumerated(EnumType.STRING)
    private CurrencyType toCurrency; // 예시. USD

    private BigDecimal rate;

    private LocalDateTime fetchedAt;
}
