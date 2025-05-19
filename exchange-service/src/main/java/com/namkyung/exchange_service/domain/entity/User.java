package com.namkyung.exchange_service.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class User {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String userId;

    private String username;
    private String password;

    private LocalDateTime createdAt;
}