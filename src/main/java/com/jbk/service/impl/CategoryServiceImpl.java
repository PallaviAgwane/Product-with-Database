package com.jbk.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.CategoryDao;
import com.jbk.entity.CategoryEntity;
import com.jbk.model.Category;
import com.jbk.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;
	@Autowired
	ModelMapper modelMapper;

	public int addCategory(Category categoryModel) {
		CategoryEntity category = modelMapper.map(categoryModel, CategoryEntity.class);
		return categoryDao.addCategory(category);
	}

	public Object updateCategory(Category categoryModel) {

		CategoryEntity category = modelMapper.map(categoryModel, CategoryEntity.class);
		Object updateCategory1 = categoryDao.updateCategory(category);
		return updateCategory1;
		/*
		 * if(updateCategory!=null) { return modelMapper.map(updateCategory,
		 * Category.class); } else { return null; }
		 */
	}

	public List<Category> getAllCategory() {
		List<CategoryEntity> allCategory = categoryDao.getAllCategory();
		List<Category> list = new ArrayList<Category>();
		if (allCategory != null) {
			list = allCategory.stream().map(model -> modelMapper.map(model, Category.class))
					.collect(Collectors.toList());
			return list;
		}
		return list;
	}

	public Category getCategoryById(long categoryId) {
		CategoryEntity category = categoryDao.getCategoryById(categoryId);
		if (category != null) {
			return modelMapper.map(category, Category.class);
		} else {
			return null;
		}
	}
	//get data by category name
	public Category getCategoryByCategoryName(String categoryName) {
		CategoryEntity categoryEntity = categoryDao.getCategoryByCategoryName(categoryName);
		if(categoryEntity!=null)
		{
		return modelMapper.map(categoryEntity, Category.class);
		}
		return null;
		
	}
	
	//sorted data
	public List<Category> getDataInSortedOrder(String orderType,String parameterType)
	{
		List<CategoryEntity> categoryEntityList = categoryDao.getDataInSortedOrder(orderType, parameterType);
		if(categoryEntityList!=null) {
		return  categoryEntityList.stream().map(categoryEntity-> modelMapper.map(categoryEntity, Category.class)).collect(Collectors.toList());
		}
		else
		{
			return null;
		}
		}
	
	public List<Category> getLastTwoCategoryAdded(String name)
	{
		List<CategoryEntity> categoryEntity = categoryDao.getLastTwoCategoryAdded(name);
		if(categoryEntity!=null)
		{
			return categoryEntity.stream().map(category->modelMapper.map(category,Category.class)).collect(Collectors.toList());
		}
		else
		{
			return null;
		}
	}
	

	public List<Category> deleteCategory(long id) {
		List<Category>list=new ArrayList<Category>();
		List<CategoryEntity> deleteCategory = categoryDao.deleteCategory(id);
		if (deleteCategory!=null && !deleteCategory.isEmpty()) {
			list=  deleteCategory.stream().map(model -> modelMapper.map(model, Category.class))
					.collect(Collectors.toList());
		}
		return list;
	
	}
}
