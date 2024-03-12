package com.spabooking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "category")
public class Category extends BaseEnitity {
	
	private String name;

}
