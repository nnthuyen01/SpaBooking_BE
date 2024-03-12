package com.spabooking.services;

import com.spabooking.dto.CategoryDao;
import com.spabooking.entities.Category;

public interface CategoryService {

	Category createCategory(CategoryDao categoryDao);
}
