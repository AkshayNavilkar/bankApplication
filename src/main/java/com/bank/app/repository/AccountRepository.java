package com.bank.app.repository;

import com.bank.app.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query(value= "select * from account  where user_name=?1",nativeQuery = true)
    Account getAllAccountByUserName(String userName);

    @Query(value= "select balance from account  where user_name=?1 and account_no=?2",nativeQuery = true)
    Float getBalanceOfUser(String userName,Integer accountNumber);

    @Query(value = "Select * from account a where a.user_name = ?", nativeQuery = true)
    Optional<Account> getAccountDetailsAfterLogin(String userName);

}
