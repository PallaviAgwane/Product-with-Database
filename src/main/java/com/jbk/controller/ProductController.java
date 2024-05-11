package com.jbk.controller;

import java.util.List;

import javax.validation.Valid;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourceNotFoundException;
import com.jbk.model.Product;
import com.jbk.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/add-product")
	public String addProduct(@RequestBody @Valid Product product) {

		int status = productService.addCategory(product);
		if (status == 1) {
			return "addedd successfully";
		} else if (status == 2) {
			throw new ResourceAlreadyExistException("This id  Product is already prasent");
		}
		else if(status==3)
		{
			throw new ResourceAlreadyExistException("This Product is already prasent Please check unique feilds");
		}
		else
		{
			return "Something is wrong";
		}
	}
	
	@GetMapping("/get-product-by-categoyid/{categoryId}")
	public List<Product> getProductByCategoryId(@PathVariable long categoryId)
	{
		List<Product> list = productService.getProductByCategoryId(categoryId);
		if(list!=null)
		{
			return list;
		}
		else
		{
			throw new ResourceNotFoundException("Category","Id",categoryId);
		}
		
	}
	
	@GetMapping("/get-products-in-asceding-order-by-productname")
	 public List<Product> getAllProductsInAscending()
	 {
		 List<Product> list = productService.getAllProductsInAscending();
		 if(list!=null)
		 {
			 return list;
		 }
		 else
		 {
			 throw new ResourceNotFoundException("Product data does not exist");
		 }
	 }
	
	@GetMapping("/get-last-two-added-product")
	 public List<Product> getRecentlyAddedTwoProducts()
	 {
		return productService.getRecentlyAddedTwoProducts();
	 }
	
	@GetMapping("/get-product-by-product-name")
	Product getProductByProductName(@RequestParam String productName)
	{
		Product product = productService.getProductByProductName(productName);
		if(product!=null)
		{
			return product;
		}
		else
		{
			throw new ResourceNotFoundException("Product not fount of name "+productName);
		}
	}
	@GetMapping("/get-all-product")
	public List<Product> getAllProduct()
	{
		List<Product> allProduct = productService.getAllProduct();
		if(allProduct!=null)
		{
			return allProduct;
		}
		else
		{
			throw new ResourceNotFoundException("Products does not exist");
		}
	}

	@GetMapping("/get-product-by-id/{productId}")
	public Product getProductById(@PathVariable long productId) {
		Product productById = productService.getProductById(productId);
		if (productById != null) {
			return productById;
		} else {

			throw new ResourceNotFoundException("Product", "Id", productId);
		}

	}
}
