package com.jbk.dao;

import java.util.List;

import com.jbk.entity.CategoryEntity;
import com.jbk.model.Category;

public interface CategoryDao {

	public int addCategory(CategoryEntity category);

	public CategoryEntity getCategoryById(long categoryId);

	public List<CategoryEntity> getAllCategory();

	public List<CategoryEntity> deleteCategory(long categoryId);

	Object updateCategory(CategoryEntity category);

	CategoryEntity getCategoryByCategoryName(String categoryName);

	List<CategoryEntity> getDataInSortedOrder(String orderType, String parameterType);

	List<CategoryEntity> getLastTwoCategoryAdded(String name);

}
