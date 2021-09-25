package com.bank.app.repository;

import com.bank.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "Select * from user where user_name = ?",nativeQuery = true)
	User getByUserName(String user_name);

	@Query(value = "SELECT * FROM user where isactive = true",nativeQuery = true)
	List<User> getAllActiveUser();

}
