package com.bank.app.repository;

import com.bank.app.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query(value= "select * from transaction where account_no=?1",nativeQuery = true)
    public List<Transaction> getTransactionByAccountNo();
}
