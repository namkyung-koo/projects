package com.namkyung.exchange_service.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class UserServiceTest {

    private final UserService userService;

    @Autowired
    UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    @DisplayName("정상 이체")
    void depositTest() {
    }

    @Test
    @DisplayName("환전 시 이체")
    void exchangeTest() {
    }
}