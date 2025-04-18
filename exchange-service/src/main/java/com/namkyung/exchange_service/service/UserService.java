package com.namkyung.exchange_service.service;

import com.namkyung.exchange_service.domain.ExchangeTransaction;
import com.namkyung.exchange_service.domain.TransactionType;
import com.namkyung.exchange_service.domain.User;
import com.namkyung.exchange_service.repository.ExchangeTransactionRepository;
import com.namkyung.exchange_service.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class UserService {

    private final UserRepository userRepository;
    private final ExchangeTransactionRepository transactionRepository;

    public User createUser(String userName, Long balance) {
        User user =  new User(userName, balance);
        return userRepository.save(user);
    }

    public User deposit(Integer userId, Long amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.deposit(amount);

        return userRepository.save(user);
    }

    public User exchange(Integer userId, TransactionType currencyType, Double rate, Long amount) {
        User user = userRepository.findById(userId)
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
