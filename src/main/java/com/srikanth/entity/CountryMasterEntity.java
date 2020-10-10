package com.srikanth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "COUNTRY_MASTER")
public class CountryMasterEntity {

	@Id
	@Column(length = 10, nullable=false, name = "COUNTRY_ID")
	private Integer countryId;

	@Column(length = 255, name = "COUNTRY_CODE")
	private String countryCode;

	@Column(length = 255, name = "COUNTRY_NAME")
	private String countryName;
}