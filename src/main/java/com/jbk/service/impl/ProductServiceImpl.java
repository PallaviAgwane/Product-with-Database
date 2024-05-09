package com.jbk.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.ProductDao;
import com.jbk.entity.ProductEntity;
import com.jbk.model.Product;
import com.jbk.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{	
	
	@Autowired
	ProductDao dao;
	@Autowired
	ModelMapper modelMapper;
	public int addCategory(Product product) {
		
		ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
		
	return 	dao.addProduct(productEntity);
	
		
	}
	
	public List<Product> getAllProduct(){
		List<ProductEntity> allProduct = dao.getAllProduct();
		List<Product>list=new ArrayList<Product>();
		if(allProduct!=null)
		{
			list = allProduct.stream().map(product->modelMapper.map(product, Product.class)).collect(Collectors.toList());
			return list;
		}
		return list;
	}
	 public Product getProductById(long productId)
	 {
		 Product product=null;
		 ProductEntity productById = dao.getProductById(productId);
		 if(productById!=null)
		 {
			 product= modelMapper.map(productById, Product.class);
			 return product;
		 }
		 else
		 {
			 return product;
		 }
	 }
	 
	 public List<Product> getProductByCategoryId(long categoryId)
	 {
		 List<ProductEntity> list = dao.getProductByCategoryId(categoryId);
		 if(list!=null)
		 {
			 return list.stream().map(product->modelMapper.map(product, Product.class)).collect(Collectors.toList());
		 }
		 return null;
		 
	 }
	 
	 public List<Product> getRecentlyAddedTwoProducts()
	 {
		 List<ProductEntity> list = dao.getRecentlyAddedTwoProducts();
		 return list.stream().map(product->modelMapper.map(product, Product.class)).collect(Collectors.toList());
	 }
	 @Override
	 public List<Product> getAllProductsInAscending()
	 {
		 List<ProductEntity> list = dao.getAllProductsInAscending();
		 if(list!=null)
		 {
			 return list.stream().map(product->modelMapper.map(product, Product.class)).collect(Collectors.toList());
		 }
		 return null;
	 }
	 
	public  Product getProductByProductName(String productName)
	 {
		ProductEntity product = dao.getProductByProductName(productName);
		if(product!=null)
		{
			return modelMapper.map(product, Product.class);
		}
		else
		{
			return null;
		}
	 }
}
