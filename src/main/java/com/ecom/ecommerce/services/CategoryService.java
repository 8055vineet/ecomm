package com.ecom.ecommerce.services;

import com.ecom.ecommerce.models.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public Category getCategoryById(int id);
    public void addCategory(Category category);
    public Category updateCategory(Category category,Long categoryId);
    public String deleteCategory(int id);


}
