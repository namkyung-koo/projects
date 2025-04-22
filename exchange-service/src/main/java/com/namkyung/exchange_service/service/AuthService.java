package com.namkyung.exchange_service.service;

import com.namkyung.exchange_service.domain.entity.User;
import com.namkyung.exchange_service.exception.AbsentUserIdException;
import com.namkyung.exchange_service.exception.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    public User login(String userId, String password) {
        User user = userService.findByUserId(userId)
                .orElseThrow(() -> new AbsentUserIdException("존재하지 않는 아이디 입니다."));

        if (!userService.matches(password, user.getPassword()))
            throw new WrongPasswordException("잘못된 비밀번호 입니다.");
        
        return user;
    }
}
