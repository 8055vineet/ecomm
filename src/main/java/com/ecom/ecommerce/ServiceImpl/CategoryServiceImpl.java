package com.ecom.ecommerce.ServiceImpl;

import com.ecom.ecommerce.models.Category;
import com.ecom.ecommerce.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    List<Category> categories=new ArrayList<>();
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public Category getCategoryById(int id) {
        return new Category(1L,"grocery");
    }

    @Override
    public void addCategory(Category category) {
        categories.add(category);

    }

    @Override
    public Category updateCategory(Category category,Long id) {
        Optional<Category> optionalCategory=categories.stream()
                .filter(c->c.getCategoryId()==id)
                .findFirst();
        if(optionalCategory.isPresent()){
            Category existingCategory=optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return existingCategory;
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public String deleteCategory(int id) {
        Category category=categories.stream()
                .filter(c->c.getCategoryId()==id)
                .findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found")) ;
        if (category==null)
            return "Category not found";
        categories.remove(category);
        return "Categgory ID"+category.getCategoryId()+" deleted successfulyy";


    }

}
