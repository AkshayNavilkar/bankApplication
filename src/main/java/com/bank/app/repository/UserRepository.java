package com.bank.app.repository;

import com.bank.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "Select * FROM user where user_name = ?",nativeQuery = true)
	User getByUserName(String useName);

	@Query(value = "SELECT * FROM user where isactive = true",nativeQuery = true)
	List<User> getAllActiveUser();

	@Query(value = "SELECT * FROM user where isactive = false",nativeQuery = true)
	List<User> getAllInActiveUser();

	@Query(value = "SELECT * FROM user where user_name = ?1 and password = ?2",nativeQuery = true)
	User getByUserNameAndPassword(String userName, String password);

	@Query(value = "SELECT * FROM user where user_name = ?1 and password = ?2 and isactive = true",nativeQuery = true)
	User isActive(String userName, String password);
}
