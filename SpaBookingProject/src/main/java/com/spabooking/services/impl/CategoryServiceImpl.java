package com.spabooking.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.spabooking.dto.CategoryDao;
import com.spabooking.entities.Category;
import com.spabooking.exception.AppException;
import com.spabooking.repository.CategoryRepository;
import com.spabooking.services.CategoryService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	private final CategoryRepository categoryRepository;
	
	private final ModelMapper modelMapper;
	
	public Category createCategory(CategoryDao dto) {
		
		List<?> found = categoryRepository.findByNameIgnoreCase(dto.getName());
		
		if (found.size() > 0) {
			throw new AppException("Category name is existed!!!");
		}
		Category category = new Category();
		
		category = modelMapper.map(dto , Category.class);
		return categoryRepository.save(category);		
	}
}
