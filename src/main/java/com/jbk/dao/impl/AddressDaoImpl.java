package com.jbk.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.jbk.entity.AddressEntity;
import com.jbk.entity.SupplierEntity;

public class AddressDaoImpl {

	@Autowired
	SessionFactory sessionFactory;
	@SuppressWarnings("deprecation")
	public List<AddressEntity> getAllAddress() {
		List<AddressEntity> list = null;
		try (Session session = sessionFactory.openSession()) {
			Criteria createCriteria = session.createCriteria(AddressEntity.class);
			list = createCriteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int addAddress(AddressEntity address) {
		int status = 0;
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			AddressEntity dbAddress = session.get(AddressEntity.class, address.getAddressId());
			if (dbAddress == null) {
				session.save(address);
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
	
	public AddressEntity getAddressById(long addressId) {
		AddressEntity address = null;
		try (Session session = sessionFactory.openSession()) {
			address = session.get(AddressEntity.class, addressId);
			if (address != null) {
				return address;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}

}
