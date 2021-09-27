package com.bank.app.repository;

import com.bank.app.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query(value= "select * from account  where user_id=?1",nativeQuery = true)
    Account getAllAccountByUserId(Integer userId);

    @Query(value= "select balance from account  where user_id=?1 and account_no=?2",nativeQuery = true)
    Float getBalanceOfUser(Integer userId,Integer accountNumber);

    @Query(value = "Select * from account a where a.user_id = ?", nativeQuery = true)
    Account getAccountDetailsAfterLogin(Integer userid);

}
