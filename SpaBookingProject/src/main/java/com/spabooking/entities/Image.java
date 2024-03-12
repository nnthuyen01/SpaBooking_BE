package com.spabooking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "image")
public class Image extends BaseEnitity {

	private String path;
}
