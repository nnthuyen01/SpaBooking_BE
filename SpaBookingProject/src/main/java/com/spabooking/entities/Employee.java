package com.spabooking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee  extends User{
	
	private String position;
	
	private String phone;

}
