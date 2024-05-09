package com.jbk.dao;

import java.util.List;

import com.jbk.entity.ProductEntity;

public interface ProductDao {

	public int addProduct(ProductEntity product);
	
	public List<ProductEntity> deleteProduct();
	public ProductEntity updateProduct();
	
	public List<ProductEntity> getAllProduct();
	
	public ProductEntity getProductById(long productId);
	
	public List<ProductEntity> getProductByCategoryId(long categoryId);
	
	public  ProductEntity getProductByProductName(String productName);
	
	public List<ProductEntity> getAllProductsInAscending();
	
	public List<ProductEntity> getRecentlyAddedTwoProducts();
}
