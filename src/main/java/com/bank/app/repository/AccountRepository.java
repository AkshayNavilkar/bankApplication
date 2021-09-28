package com.bank.app.repository;

import com.bank.app.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "Select * from account a where a.user_name = ?", nativeQuery = true)
    Optional<Account> getAccountDetailsAfterLogin(String user_name);
}
