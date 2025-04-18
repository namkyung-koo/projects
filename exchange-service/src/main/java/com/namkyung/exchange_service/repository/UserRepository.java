package com.namkyung.exchange_service.repository;

import com.namkyung.exchange_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {}