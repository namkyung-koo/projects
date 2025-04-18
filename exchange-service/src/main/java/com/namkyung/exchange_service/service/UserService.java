package com.namkyung.exchange_service.service;

import com.namkyung.exchange_service.domain.ExchangeTransaction;
import com.namkyung.exchange_service.domain.TransactionType;
import com.namkyung.exchange_service.domain.User;
import com.namkyung.exchange_service.exception.DuplicateUserIdException;
import com.namkyung.exchange_service.repository.ExchangeTransactionRepository;
import com.namkyung.exchange_service.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Setter
public class UserService {

    private final UserRepository userRepository;
    private final ExchangeTransactionRepository transactionRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(String userId, String password, String username) {
        if (userRepository.findByUserId(userId).isPresent()) {
            throw new DuplicateUserIdException("이미 존재하는 아이디입니다.");
        }

        User user = new User();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        return userRepository.save(user);
    }

    public User deposit(Integer id, Long amount) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.deposit(amount);

        return userRepository.save(user);
    }

    public User exchange(Integer id, TransactionType currencyType, Double rate, Long amount) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

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
}
