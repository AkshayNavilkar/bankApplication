package com.bank.app.repository;

import com.bank.app.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query(value= "select * from account  where user_id=?1",nativeQuery = true)
    public Account getAllAccountByUserId(Integer userId);

    @Query(value= "select balance from account  where user_id=?1 and account_no=?2",nativeQuery = true)
    public float getBalanceOfUser(Integer userId,Integer accountNumber);

}
