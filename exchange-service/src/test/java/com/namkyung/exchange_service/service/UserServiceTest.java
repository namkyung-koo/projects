package com.namkyung.exchange_service.service;

import static com.namkyung.exchange_service.domain.TransactionType.EXCHANGE;
import static org.assertj.core.api.Assertions.*;

import com.namkyung.exchange_service.domain.User;
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
        User user = userService.createUser("Rice", 1_000_000_000L);
        User updatedUser = userService.deposit(user.getUserId(), 1_000_000_000L);

        assertThat(updatedUser.getBalance()).isEqualTo(2_000_000_000L);
    }

    @Test
    @DisplayName("환전 시 이체")
    void exchangeTest() {
        User user = userService.createUser("Koo", 1_000_000_000L);

        User exchangedUser = userService.exchange(user.getUserId(), EXCHANGE, 3.3, 50_000_000L);
        assertThat(exchangedUser.getBalance()).isEqualTo(950_000_000L);
    }
}