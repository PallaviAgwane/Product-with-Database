package com.jbk.service;

import java.util.List;

import com.jbk.entity.CategoryEntity;
import com.jbk.model.Category;

public interface CategoryService {

	
	int addCategory(Category categoryModel);
	Object updateCategory(Category categoryModel );
	List<Category> getAllCategory();
	Category getCategoryById(long categoryId);
	List<Category> deleteCategory(long id);
	Category getCategoryByCategoryName(String categoryName);
	List<Category> getDataInSortedOrder(String orderType,String parameterType);
	List<Category> getLastTwoCategoryAdded(String name);
}
