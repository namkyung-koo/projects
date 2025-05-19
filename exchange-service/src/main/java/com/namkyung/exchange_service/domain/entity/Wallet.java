package com.namkyung.exchange_service.domain.entity;

import com.namkyung.exchange_service.domain.CurrencyType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class Wallet {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private CurrencyType currency; // 예시. KRW, USD, JPY, EUR, GBP, CNY

    private BigDecimal balance;
}
