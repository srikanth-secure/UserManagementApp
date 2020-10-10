package com.srikanth.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class UserAccounts {
	
	private Integer userId;
	private String accStatus;
	private Integer cityId;
	private Integer countryId;
	private Date createdDate;
	private Date dateOfBirth;
	private String firstName;
	private String gender;
	private Integer stateId;
	private Date updatedDate;
	private String userEmail;
	private String lastName;
	private String userPwd;
	private String userMobile;
}