package com.srikanth.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import com.srikanth.entity.CountryMasterEntity;

public interface CountryMasterRepository extends JpaRepository<CountryMasterEntity, Serializable> {

}
