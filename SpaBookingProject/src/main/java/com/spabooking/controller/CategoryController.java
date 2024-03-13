package com.spabooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spabooking.dto.CategoryDao;
import com.spabooking.dto.ResponseDTO;
import com.spabooking.services.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor

public class CategoryController {
	private final CategoryService categoryService;
	
	@PostMapping("/admin/category/add")
	public ResponseEntity<?> create(@RequestBody CategoryDao dto){
		return ResponseEntity.ok(new ResponseDTO(true,"success",categoryService.createCategory(dto)));
	}
	@GetMapping("/category/list")
	public ResponseEntity<?> listCategories(){
		return ResponseEntity.ok(new ResponseDTO(true,"success",categoryService.findAll()));
	}
}
