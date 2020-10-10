package com.srikanth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "STATES_MASTER")
public class StatesMasterEntity {

	@Id
	@Column(length = 10, nullable=false, name = "STATE_ID")
	private Integer stateId;

	@Column(length = 255, name = "STATE_NAME")
	private String stateName;

	@Column(length = 10, name = "COUNTRY_ID")
	private Integer countryId;
}