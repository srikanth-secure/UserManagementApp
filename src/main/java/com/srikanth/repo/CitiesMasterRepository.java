package com.srikanth.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.srikanth.entity.CitiesMasterEntity;

public interface CitiesMasterRepository extends JpaRepository<CitiesMasterEntity, Serializable> {
	public List<CitiesMasterEntity> findByStateId(Integer id);
}