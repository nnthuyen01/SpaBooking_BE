package com.spabooking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class Customer extends User{
	private String address;
	
	private String phone;
}
