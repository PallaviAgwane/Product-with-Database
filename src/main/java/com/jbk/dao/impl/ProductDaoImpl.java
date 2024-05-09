package com.jbk.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.ProductDao;
import com.jbk.entity.CategoryEntity;
import com.jbk.entity.ProductEntity;
import com.jbk.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;

	// Add product
	@Override
	public int addProduct(ProductEntity product) {
		int status = 0;
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			ProductEntity dbCategory = session.get(ProductEntity.class, product.getProductId());
			try {
				if (dbCategory == null) {
					session.save(product);
					transaction.commit();
					status = 1;
				} else {
					status = 2;
				}
			} catch (PersistenceException e) {
				status = 3;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}

	// get all product by category Id
	
	public List<ProductEntity> getProductByCategoryId(long categoryId) {
		List<ProductEntity> list = null;
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(ProductEntity.class);
			criteria.add(Restrictions.eq("categoryEntity.categoryId", categoryId));
			list = criteria.list();

		}
		return list;
	}

	// get all product
	
	public List<ProductEntity> getAllProduct() {

		List<ProductEntity> list = null;
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(ProductEntity.class);
			list = criteria.list();

		} catch (Exception e) {
			e.getMessage();
		}
		return list;

	}

	// get product by product id using get method
	public ProductEntity getProductById(long productId) {
		try (Session session = sessionFactory.openSession()) {
			ProductEntity productEntity = null;
			productEntity = session.get(ProductEntity.class, productId);
			if (productEntity != null) {
				return productEntity;
			} else {
				return productEntity;
			}
		}
	}

	// get product by product name using createria
	public ProductEntity getProductByProductName(String productName) {
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(ProductEntity.class);
			ProductEntity product = (ProductEntity) criteria.add(Restrictions.eq("productName", productName))
					.uniqueResult();
			if (product != null) {
				return product;
			}
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}
	
	public List<ProductEntity> getRecentlyAddedTwoProducts()
	{
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria=session.createCriteria(ProductEntity.class);
			criteria.addOrder(Order.desc("productId"));
			criteria.setMaxResults(2);
			List list = criteria.list();
			return list;
		}
	}
	
	//get all product in ascending order by product name;
	public List<ProductEntity> getAllProductsInAscending()
	{
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(ProductEntity.class);
			criteria.addOrder(Order.asc("productName"));
			List list = criteria.list();
			return list;
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		return null;
	}
	

	@Override
	public List<ProductEntity> deleteProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductEntity updateProduct() {
		// TODO Auto-generated method stub
		return null;
	}
}
