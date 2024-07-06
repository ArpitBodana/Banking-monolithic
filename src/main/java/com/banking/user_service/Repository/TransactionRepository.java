package com.banking.user_service.Repository;


import com.banking.user_service.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions,Long> {
    List<Transactions> findByAccountId(Long accountId);
}
