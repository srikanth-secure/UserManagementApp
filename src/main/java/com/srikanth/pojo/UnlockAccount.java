package com.srikanth.pojo;

import lombok.Data;

@Data
public class UnlockAccount {

	private String tempPwd;
	private String newPwd;
	private String confirmNewPwd;
}
