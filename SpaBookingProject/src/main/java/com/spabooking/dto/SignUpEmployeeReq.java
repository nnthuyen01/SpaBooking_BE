package com.spabooking.dto;

import lombok.Data;

@Data
public class SignUpEmployeeReq {
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String password;
	
	private String phone;
	
	private String position;
}
