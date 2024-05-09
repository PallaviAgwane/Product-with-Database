package com.jbk.service;

import java.util.List;

import com.jbk.entity.SupplierEntity;
import com.jbk.model.Supplier;

public interface SupplierService {

	int addSupplier(Supplier supplier);

	List<Supplier> getAllSupplier();

	Object updateSuppiler(Supplier supplier);

	Supplier getSupplierById(long supplierId);

	List<Supplier> getSupplierByCitiName(String citiName);
}
