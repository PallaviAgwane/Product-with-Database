package com.jbk.dao.impl;

import java.util.ArrayList;
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

import com.jbk.dao.CategoryDao;
import com.jbk.entity.CategoryEntity;
import com.jbk.model.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int addCategory(CategoryEntity category) {
		int status = 0;
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			CategoryEntity dbCategory = session.get(CategoryEntity.class, category.getCategoryId());
			
			if (dbCategory==null) {
				session.save(category);
				transaction.commit();
				status = 1;
			} else {
				status = 2;
			}
		} 
		catch(PersistenceException e)
		{
			status=3;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public CategoryEntity getCategoryById(long categoryId) {
		CategoryEntity category = null;
		try (Session session = sessionFactory.openSession()) {
			category = session.get(CategoryEntity.class, categoryId);
			if (category != null) {
				return category;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}

	public List<CategoryEntity> getAllCategory() {
		List<CategoryEntity> list = null;
		try (Session session = sessionFactory.openSession()) {
			Criteria createCriteria = session.createCriteria(CategoryEntity.class);
			list = createCriteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public CategoryEntity getCategoryByCategoryName(String categoryName) {
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(CategoryEntity.class);
			List<CategoryEntity> list = criteria.add(Restrictions.eq("categoryName", categoryName)).list();

			for (CategoryEntity categoryEntity : list) {
				return categoryEntity;
			}

		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	// Sorted data

	public List<CategoryEntity> getDataInSortedOrder(String orderType, String parameterType) {
		List list = null;
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(CategoryEntity.class);
			if ("desc".equalsIgnoreCase(orderType)) {
				criteria.addOrder(Order.desc(parameterType)); /// parameter type
			} else {
				criteria.addOrder(Order.asc(parameterType));
			}
			list = criteria.list();
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
	}

	public List<CategoryEntity> getLastTwoCategoryAdded(String categoryName) {
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(CategoryEntity.class);
			Criteria addOrder = criteria.addOrder(Order.desc(categoryName));///////// category name is the typeof
																			///////// coulumn
			criteria.setMaxResults(2);
			List list = criteria.list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object updateCategory(CategoryEntity category) {

		CategoryEntity categoryEntity = null;
		categoryEntity = getCategoryById(category.getCategoryId());
		try (Session session = sessionFactory.openSession()) {
			Transaction tr = session.beginTransaction();

			if (categoryEntity != null) {
				session.update(category);
				tr.commit();
				return category;
			}

		} catch (PersistenceException ex) {
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//// Pending methodddddddddddd
	public List<CategoryEntity> deleteCategoryById(long categoryId) {
		List<CategoryEntity> listAll = new ArrayList<CategoryEntity>();
		//CategoryEntity categoryById = getCategoryById(categoryId);
		try (Session session = sessionFactory.openSession()) {
			CategoryEntity categoryById = session.get(CategoryEntity.class, categoryId);
			Transaction tr = session.beginTransaction();
			if (categoryById != null) {
				session.delete(categoryById);
				tr.commit();
				return getAllCategory();
			}
			return null;
		}
	}

	public List<CategoryEntity> deleteCategory(long categoryId) {
		List<CategoryEntity> allCategory = new ArrayList<CategoryEntity>();
	//	CategoryEntity categoryById = getCategoryById(categoryId);
	
		try (Session session = sessionFactory.openSession()) {
			Transaction tr = session.beginTransaction();
			CategoryEntity categoryById = session.get(CategoryEntity.class, categoryId);
			System.err.println(categoryById);
			if (categoryById != null) {
				session.delete(categoryById);
				tr.commit();
				allCategory= getAllCategory();
			}
		}
		return allCategory;

	}
}

/*
 * Category category = modelMapper.map(categoryModel, Category.class); try
 * (Session session = sessionFactory.openSession()) { Transaction transaction =
 * session.beginTransaction();
 * 
 * if (category != null) {
 * 
 * session.delete(category); System.err.println("***************"+category);
 * transaction.commit(); //allCategory= getAllCategory();
 * 
 * System.out.println("**********************8");
 * 
 * }
 * 
 * } catch (Exception e) { System.out.println(e.getMessage());
 * //e.printStackTrace(); }
 */

/*
 * private CategoryModel categoryToCategoryModel(Category category) {
 * CategoryModel categoryModel=new CategoryModel();
 * categoryModel.setCategoryId(category.getCategoryId());
 * categoryModel.setCategoryName(category.getCategoryName());
 * categoryModel.setDeliveryCharges(category.getDeliveryCharges());
 * categoryModel.setDiscount(category.getDiscount());
 * categoryModel.setGst(category.getGst());
 * categoryModel.setDiscription(category.getDiscription()); return
 * categoryModel; }
 * 
 * private Category categoryModelToCategoryl(CategoryModel categoryModel) {
 * Category category=new Category();
 * category.setCategoryId(categoryModel.getCategoryId());
 * category.setCategoryName(categoryModel.getCategoryName());
 * category.setDeliveryCharges(categoryModel.getDeliveryCharges());
 * category.setDiscount(categoryModel.getDiscount());
 * category.setGst(categoryModel.getGst());
 * category.setDiscription(categoryModel.getDiscription()); return category; }
 */
