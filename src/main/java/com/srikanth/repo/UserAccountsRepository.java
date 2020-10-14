package com.srikanth.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.srikanth.entity.UserAccountsEntity;

public interface UserAccountsRepository extends JpaRepository<UserAccountsEntity, Serializable> {
	
	@Query(value = "select customer_id from cus where")
	public Boolean checkEmail(String userEmail);

	public UserAccountsEntity findByUserPazzwordAndUserEmail();
}