package com.srikanth.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "USER_ACCOUNTS")
public class UserAccountsEntity {

	@Id
	@SequenceGenerator(sequenceName = "USER_ID_SEQ", name = "U_ID_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "U_ID_SEQ", strategy = GenerationType.SEQUENCE)
	@Column(length = 10, nullable = false, name = "USER_ID")
	private Integer userId;

	@Column(length = 255, name = "ACC_STATUS")
	private String accStatus;

	@Column(length = 10, name = "CITY_ID")
	private Integer cityId;

	@Column(length = 10, name = "COUNTRY_ID")
	private Integer countryId;

	@Column(length = 10, name = "CREATED_DATE")
	private Date createdDate;

	@Column(length = 10, name = "DOB")
	private Date dateOfBirth;

	@Column(length = 255, name = "FIRST_NAME")
	private String firstName;

	@Column(length = 10, name = "GENDER")
	private String gender;

	@Column(length = 10, name = "STATE_ID")
	private Integer stateId;

	@Column(length = 10, name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(length = 255, unique = true, name = "USER_EMAIL")
	private String userEmail;

	@Column(length = 255, name = "LAST_NAME")
	private String lastName;

	@Column(length = 255, name = "USER_PWD")
	private String userPwd;

	@Column(length = 15, name = "USER_MOBILE")
	private String userMobile;
}