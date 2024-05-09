package com.jbk.dao;

import java.util.List;

import com.jbk.entity.SupplierEntity;

public interface SupplierDao {

	int addSupplier(SupplierEntity supplier);

	List<SupplierEntity> getAllSuppler();

	SupplierEntity getSupplierById(long supplierId);

	Object updateSuppiler(SupplierEntity supplierEntity);

	List<SupplierEntity> getSupplierByCitiName(String citiName);
}
