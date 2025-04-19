package com.namkyung.exchange_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ExchangeTransaction {

    @Id
    @GeneratedValue
    private Integer exchangeId;

    @ManyToOne
    @JoinColumn(name = "exchange_user_user_id")
    private User exchangeUser;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // 입금 or 환전

    private Long fromCurrency;

    private Long toCurrency;

    private Long amount;

    private Double rate; // 적용된 환율

    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }
}
