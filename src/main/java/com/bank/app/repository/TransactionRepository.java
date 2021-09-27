package com.bank.app.repository;

import com.bank.app.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query(value= "select * from transaction where account_no=?1",nativeQuery = true)
    public Transaction getTransactionByAccountNo(Integer accountNo);
    @Query(value = "SELECT a.balance from transaction t join account a on t.account_no=a.account_no where t.account_no=?1 ;",nativeQuery = true)
    public Float getBalance(Integer accountNo);

}
