package com.srikanth.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.srikanth.entity.StatesMasterEntity;

public interface StatesMasterRepository extends JpaRepository<StatesMasterEntity, Serializable> {
	public List<StatesMasterEntity> findByCountryId(Integer id);
}
