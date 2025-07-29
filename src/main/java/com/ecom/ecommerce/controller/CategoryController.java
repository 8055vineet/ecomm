package com.ecom.ecommerce.controller;

import com.ecom.ecommerce.models.Category;
import com.ecom.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/")
public class CategoryController {
 @Autowired
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("public/categories")
   public ResponseEntity<List<Category>> getAllCategories(){

        List<Category> allCategories = categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories,HttpStatus.OK);
    }

    @PostMapping("public/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category){
       categoryService.addCategory(category);
       return new ResponseEntity<>("Category created", HttpStatus.CREATED);
    }
    @PutMapping("public/categories{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,
                                                 @PathVariable Long categoryId){
        try {
            Category savedCategory=categoryService.updateCategory(category,categoryId);
            return new ResponseEntity<>("Category updated with Id:"+categoryId, HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            //return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }

    @DeleteMapping("public/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable int categoryId){
       try {
           String status = categoryService.deleteCategory(categoryId);
           return new ResponseEntity<>(status, HttpStatus.OK);
           // return ResponseEntity.status(HttpStatus.OK).body(status);
          // return ResponseEntity.ok(status);

       }
       catch (ResponseStatusException e){
           //return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
           return new ResponseEntity<>(e.getReason(),e.getStatusCode());
       }
    }
}
