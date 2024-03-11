package com.spabooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spabooking.dto.JwtAuthenticationResponse;
import com.spabooking.dto.RefreshTokenRequest;
import com.spabooking.dto.SignInRequest;
import com.spabooking.dto.SignUpEmployeeReq;
import com.spabooking.dto.SignUpRequest;
import com.spabooking.entities.User;
import com.spabooking.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	@PostMapping("/signupCustomer")
	public ResponseEntity<User> signupCustomer(@RequestBody SignUpRequest signUpRequest){
		return ResponseEntity.ok(authenticationService.signupCustomer(signUpRequest));
	}
	
	@PostMapping("/signupEmployee")
	public ResponseEntity<User> signupEmployee(@RequestBody SignUpEmployeeReq signUpEmployeeReq){
		return ResponseEntity.ok(authenticationService.signupEmployee(signUpEmployeeReq));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
		return ResponseEntity.ok(authenticationService.signin(signInRequest));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
		return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
	}
}
