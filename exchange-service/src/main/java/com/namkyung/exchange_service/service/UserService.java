package com.namkyung.exchange_service.service;

import com.namkyung.exchange_service.domain.entity.ExchangeTransaction;
import com.namkyung.exchange_service.domain.TransactionType;
import com.namkyung.exchange_service.domain.entity.User;
import com.namkyung.exchange_service.exception.DuplicateUserIdException;
import com.namkyung.exchange_service.repository.ExchangeTransactionRepository;
import com.namkyung.exchange_service.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Setter
@Getter
public class UserService {

    private final UserRepository userRepository;
    private final ExchangeTransactionRepository transactionRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(String username, String userId, String password) {
        isUserIdPresent(userId);

        User user = new User();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        log.info("username={}, userId={}, password={}", username, userId, password);
        return userRepository.save(user);
    }

    public User deposit(Integer id, Long amount) {
        User user = findByid(id);
        user.deposit(amount);

        return userRepository.save(user);
    }

    public User exchange(Integer id, TransactionType currencyType, Double rate, Long amount) {
        User user = findByid(id);
        user.withdraw(amount);

        ExchangeTransaction tx = new ExchangeTransaction();
        tx.setExchangeUser(user);
        tx.setType(currencyType);
        tx.setFromCurrency(amount);
        tx.setRate(rate);
        tx.setToCurrency((long) (amount * rate));
        tx.setTimestamp(LocalDateTime.now());

        transactionRepository.save(tx);
        return userRepository.save(user);
    }

    public User findByid(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    private void isUserIdPresent(String userId) {
        if (userRepository.findByUserId(userId).isPresent()) {
            throw new DuplicateUserIdException("이미 존재하는 아이디입니다.");
        }
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
