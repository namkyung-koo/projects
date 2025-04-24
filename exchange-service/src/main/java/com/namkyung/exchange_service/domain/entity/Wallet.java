package com.namkyung.exchange_service.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Wallet {

    private Long krw;
    private Long jpy;
    private Long usd;
    private Long eur;
    private Long gbp;

    public Wallet() {
        this.krw = 0L;
        this.jpy = 0L;
        this.usd = 0L;
        this.eur = 0L;
        this.gbp = 0L;
    }

    public void deposit(Long amount) {
        this.krw += amount;
    }

    public void withdraw(Long amount) {
        if (this.krw < amount) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        this.krw -= amount;
    }
}
