package com.jbk.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.SupplierDao;
import com.jbk.entity.SupplierEntity;

@Repository
public class SupplierDaoImpl implements SupplierDao {

	@Autowired
	SessionFactory sessionFactory;

	public int addSupplier(SupplierEntity supplier) {
		int status = 0;
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			SupplierEntity dbSupplier = session.get(SupplierEntity.class, supplier.getSupplierId());
			if (dbSupplier == null) {
				session.save(supplier);
				transaction.commit();
				status = 1;
			} else {
				status = 2;
			}
		} catch (PersistenceException px) {
			status = 3;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public List<SupplierEntity> getAllSuppler() {
		List<SupplierEntity> list = null;
		try (Session session = sessionFactory.openSession()) {
			Criteria createCriteria = session.createCriteria(SupplierEntity.class);
			list = createCriteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public SupplierEntity getSupplierById(long categoryId) {
		SupplierEntity supplier = null;
		try (Session session = sessionFactory.openSession()) {
			supplier = session.get(SupplierEntity.class, categoryId);
			if (supplier != null) {
				return supplier;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supplier;
	}

	public Object updateSuppiler(SupplierEntity supplierEntity) {
		SupplierEntity supplier = null;
		supplier = getSupplierById(supplierEntity.getSupplierId());
		try (Session session = sessionFactory.openSession()) {
			Transaction tr = session.beginTransaction();

			if (supplier != null) {
				session.update(supplierEntity);
				tr.commit();
				System.out.println("HHHHHHH");
				return supplierEntity;
			}

		} catch (PersistenceException ex) {
			return 1;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<SupplierEntity> getSupplierByCitiName(String citiName) {
		List list = null;
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(SupplierEntity.class);
			Criteria add = criteria.add(Restrictions.eq("city", citiName));
			list = add.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}