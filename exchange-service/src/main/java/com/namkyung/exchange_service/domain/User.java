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
    private Integer userId;
    private String userName;
    private Long balance; // 원화를 기준으로 한 잔액(단위: 원)


    public User(String userName, Long balance) {
        if (balance < 0) throw new IllegalStateException("초기 잔액은 0 이상이어야 합니다.");
        this.userName = userName;
        this.balance = balance;
    }

    public void deposit(Long amount) {
        this.balance += amount;
    }

    public void withdraw(Long amount) {
        if (this.balance < amount) throw new IllegalStateException("잔액 부족");
        this.balance -= amount;
    }
}