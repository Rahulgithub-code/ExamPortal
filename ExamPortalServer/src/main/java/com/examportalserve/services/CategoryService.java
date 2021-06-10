package com.examportalserve.services;

import com.examportalserve.model.exam.Category;

import java.util.Set;

public interface CategoryService {
    public Category addCategory( Category category);
    public Category UpdateCategory(Category category);
    public void deleteCategory(Long categoryId);
    public Set<Category> getCategories();
    public Category getCategory(Long categoryId);
}
