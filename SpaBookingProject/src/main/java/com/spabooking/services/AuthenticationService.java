package com.spabooking.services;

import com.spabooking.dto.JwtAuthenticationResponse;
import com.spabooking.dto.RefreshTokenRequest;
import com.spabooking.dto.SignInRequest;
import com.spabooking.dto.SignUpEmployeeReq;
import com.spabooking.dto.SignUpRequest;
import com.spabooking.entities.User;

public interface AuthenticationService {
	User signupCustomer(SignUpRequest signUpRequest);
	
	 User signupEmployee(SignUpEmployeeReq signUpEmployeeReq);
	
	JwtAuthenticationResponse signin(SignInRequest signInRequest);
	
	JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest); 
}
