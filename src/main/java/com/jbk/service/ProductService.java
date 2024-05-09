package com.jbk.service;

import java.util.List;

import com.jbk.entity.ProductEntity;
import com.jbk.model.Product;

public interface ProductService {

	int addCategory(Product product);

	List<Product> getAllProduct();

	public List<Product> getProductByCategoryId(long categoryId);

	Product getProductById(long productId);

	Product getProductByProductName(String productName);

	public List<Product> getRecentlyAddedTwoProducts();
	
	public List<Product> getAllProductsInAscending();
}
