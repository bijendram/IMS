package com.ims.categorypolicyservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ims.categorypolicyservice.Service.CategorySerivce;
import com.ims.categorypolicyservice.entity.Category;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategorySerivce categorySerivce;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> allCategory = categorySerivce.getAllCategory();
		return new ResponseEntity<>(allCategory, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		Category addCategory = categorySerivce.addCategory(category);
		return new ResponseEntity<>(addCategory, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<Void> deleteCategory(@PathVariable String catId){
		categorySerivce.deleteCategory(catId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<Category> updateCategory(@PathVariable String catId, @RequestBody Category category){
		category.setCatId(catId);
		Category updateCategory = categorySerivce.updateCategory(category);
		if(updateCategory != null) {
			return new ResponseEntity<>(updateCategory, HttpStatus.OK);
			
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	

}
