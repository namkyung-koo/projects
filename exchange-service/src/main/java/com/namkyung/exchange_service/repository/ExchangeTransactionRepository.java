package com.namkyung.exchange_service.repository;

import com.namkyung.exchange_service.domain.entity.ExchangeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeTransactionRepository extends JpaRepository<ExchangeTransaction, Integer> {}
