package com.namkyung.exchange_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "'user'")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String userId;
    private String password;
    private Long balance;

    public User() {}

    public void deposit(Long amount) {
        this.balance += amount;
    }

    public void withdraw(Long amount) {
        if (this.balance < amount) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        this.balance -= amount;
    }
}