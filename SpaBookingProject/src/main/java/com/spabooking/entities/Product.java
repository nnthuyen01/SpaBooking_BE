package com.spabooking.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product extends BaseEnitity{
	private String name;
	
	private BigDecimal price;
	
	private Integer quantity;
	
	private String mainImage;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private List<Image> listImages;
}
