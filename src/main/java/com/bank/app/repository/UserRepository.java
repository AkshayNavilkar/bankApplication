package com.bank.app.repository;

import com.bank.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "SELECT * FROM user where isactive = true",nativeQuery = true)
	List<User> getAllActiveUser();

	@Query(value = "SELECT * FROM user where user_name = ?1 and isactive = false",nativeQuery = true)
	Optional<User> getOptValidation(String userName) ;

	@Query(value = "SELECT * FROM user where user_name = ?1 and otp =?2",nativeQuery = true)
	Optional<User> validationOtp(String userName, String otp);

	@Query(value = "SELECT * FROM user where isactive = false",nativeQuery = true)
	List<User> getAllInActiveUser();

	@Query(value = "SELECT * FROM user where user_name = ?1 and password = ?2",nativeQuery = true)
	Optional<User> getByUserNameAndPassword(String userName, String password);

	@Query(value = "SELECT * FROM user where user_name = ?1 and password = ?2 and isactive = true",nativeQuery = true)
	Optional<User> isActive(String userName, String password) ;
}
