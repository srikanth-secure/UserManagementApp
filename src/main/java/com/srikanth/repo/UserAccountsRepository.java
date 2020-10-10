package com.srikanth.repo;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.srikanth.entity.UserAccountsEntity;

public interface UserAccountsRepository extends JpaRepository<UserAccountsEntity, Serializable> {

}