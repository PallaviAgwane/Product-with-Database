package com.jbk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.entity.CategoryEntity;
import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourceNotFoundException;
import com.jbk.model.Category;
import com.jbk.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PostMapping("/add-category")
	public String addCategory(@Valid @RequestBody Category categoryModel) {
		int status = categoryService.addCategory(categoryModel);
		if (status == 1) {
			return "addedd successfully";
		} else if (status == 2) {
			throw new ResourceAlreadyExistException("This id  Category is already prasent");
		}
		else
		{
			throw new ResourceAlreadyExistException("This Category is already prasent Please check unique feilds");
		}
	}

	@GetMapping("/get-category-by-id/{categoryId}")
	public Category getCategoryById(@PathVariable long categoryId) {

		Category categoryModel = categoryService.getCategoryById(categoryId);
		if (categoryModel != null) {
			return categoryModel;
		} else {
			throw new ResourceNotFoundException("Category", "Id", categoryId);
		}

	}

	@PutMapping("/update-category")
	public Object updateCategory(@Valid @RequestBody Category categoryModel) {
		Object obj = categoryService.updateCategory(categoryModel);

		if (obj instanceof Integer) {
			int a = (Integer) obj;
			if (a == 1) {
				throw new ResourceAlreadyExistException("This record is already preasent ..please check unique fields");
			}
		}
		if (obj != null) {
			return obj;
		} else {
			throw new ResourceNotFoundException("Category", "Id", categoryModel.getCategoryId());
		}

	}

	@GetMapping("/get-all-category")
	public List<Category> getAllCategory() {
		List<Category> list = categoryService.getAllCategory();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new ResourceNotFoundException("Catagory data are not found");
		}
	}

	@DeleteMapping("/delete-category")
	public List<Category> deleteCategory(@RequestParam long id) {
		List<Category> list = categoryService.deleteCategory(id);
		if(!list.isEmpty())
		{
			return list;
		}
		
		else
		{ 
			throw new ResourceNotFoundException("Category id not found or category data is empty");
		}
	}
	// retrive data by category name
	@GetMapping("get Category by categoryName/{categoryName}")
	public Category getCategoryById(@PathVariable String categoryName) {
		
		 Category categoryByCategoryName = categoryService.getCategoryByCategoryName(categoryName);
		 if(categoryByCategoryName!=null)
		 {
			 return categoryByCategoryName;
		 }
		 else
		 {
			 throw new ResourceNotFoundException(categoryName+" Catagory data are not found");
		 }
		
	}
	
	// retrive the data in ascending or descending order as per customer requinment
	@GetMapping("/get-category-in-sorted-order-as-per-requirment/{orderType}/{parameterType}")
	public List<Category> getCategoryInSortedOrder(@PathVariable String orderType,@PathVariable String parameterType)
	{
		List<Category> list = categoryService.getDataInSortedOrder(orderType, parameterType);
		 if(list!=null)
		 {
			 return list;
		 }
		 else
		 {
			 throw new ResourceNotFoundException(parameterType+" Catagory data are not found");
		 }
		
	}
	
	// retrive 2 catagory which are added recently
	// sort the data in desc order as per category id ..and set the max result
	@GetMapping("/get-last-two-cotegory/{CategoryId}")
	public List<Category> getLastTwoCategoryAdded(@PathVariable String CategoryId)
	{
		List<Category> list = categoryService.getLastTwoCategoryAdded(CategoryId);
		if(list!=null)
		 {
			 return list;
		 }
		 else
		 {
			 throw new ResourceNotFoundException(CategoryId+" Catagory data are not found");
		 }
	}
	
	}