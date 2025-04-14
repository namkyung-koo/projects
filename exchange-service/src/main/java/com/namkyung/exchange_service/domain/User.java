package com.namkyung.exchange_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class User {
    @Id
    @GeneratedValue
    private Integer userId;
    private final String userName;
    private final Long balance; // 원화를 기준으로 한 잔액(단위: 원)


    public User(String userName, Long balance) {
        this.userName = userName;
        this.balance = balance;
    }
}
