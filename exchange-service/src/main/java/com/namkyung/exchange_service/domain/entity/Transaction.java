package com.namkyung.exchange_service.domain.entity;

import com.namkyung.exchange_service.domain.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Wallet wallet;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // DEPOSIT, TRANSFER, EXCHANGE

    private BigDecimal amount;

    private LocalDateTime createdAt;
}
