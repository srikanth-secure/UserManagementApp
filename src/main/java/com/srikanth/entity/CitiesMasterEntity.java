package com.srikanth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "CITIES_MASTER")
public class CitiesMasterEntity {

	@Id
	@Column(length = 10, nullable=false, name = "CITY_ID")
	private Integer cityId;

	@Column(length = 255, name = "CITY_NAME")
	private String cityName;

	@Column(length = 10, name = "STATE_ID")
	private Integer stateId;
}