package com.spabooking.services.impl;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spabooking.dto.JwtAuthenticationResponse;
import com.spabooking.dto.RefreshTokenRequest;
import com.spabooking.dto.SignInRequest;
import com.spabooking.dto.SignUpEmployeeReq;
import com.spabooking.dto.SignUpRequest;
import com.spabooking.entities.Customer;
import com.spabooking.entities.Employee;
import com.spabooking.entities.Role;
import com.spabooking.entities.User;
import com.spabooking.repository.UserRepository;
import com.spabooking.services.AuthenticationService;
import com.spabooking.services.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authenticationManager;
	
	private final JWTService jwtService;
	
	public User signupCustomer(SignUpRequest signUpRequest) {
		User user = new Customer();
		
		user.setEmail(signUpRequest.getEmail());
		user.setFirstname(signUpRequest.getFirstname());
		user.setLastname(signUpRequest.getLastname());
		user.setRole(Role.CUSTOMER);
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		
		return userRepository.save(user);
	}	
	public User signupEmployee(SignUpEmployeeReq signUpEmployeeReq) {
	    Employee employee = new Employee(); // Create an Employee object

	    // Set common fields
	    employee.setEmail(signUpEmployeeReq.getEmail());
	    employee.setFirstname(signUpEmployeeReq.getFirstname());
	    employee.setLastname(signUpEmployeeReq.getLastname());
	    employee.setRole(Role.EMPLOYEE);
	    employee.setPassword(passwordEncoder.encode(signUpEmployeeReq.getPassword()));

	    // Set additional fields specific to Employee
	    employee.setPosition(signUpEmployeeReq.getPosition());
	    employee.setPhone(signUpEmployeeReq.getPhone());

	    return userRepository.save(employee); // Save the Employee object
	}
	
	public JwtAuthenticationResponse signin(SignInRequest signInRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
		
		var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password!!!"));
		var jwt = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
		
		JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
		
		jwtAuthenticationResponse.setToken(jwt);
		jwtAuthenticationResponse.setRefreshToken(refreshToken);
		
		return jwtAuthenticationResponse;
	}
	
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
		User user = userRepository.findByEmail(userEmail).orElseThrow();
		if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
			var jwt = jwtService.generateToken(user);
			
			JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
			
			jwtAuthenticationResponse.setToken(jwt);
			jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
			return jwtAuthenticationResponse;
		}
		return null;
	}
}
