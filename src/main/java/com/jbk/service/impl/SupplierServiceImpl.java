package com.jbk.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.SupplierDao;
import com.jbk.entity.SupplierEntity;
import com.jbk.model.Supplier;
import com.jbk.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	SupplierDao supplierDao;

	@Autowired
	ModelMapper modelMapper;

	public int addSupplier(Supplier supplier) {
		SupplierEntity entity = modelMapper.map(supplier, SupplierEntity.class);
		return supplierDao.addSupplier(entity);
	}

	public List<Supplier> getAllSupplier() {
		List<Supplier> supplierList = new ArrayList<Supplier>();
		List<SupplierEntity> allSuppler = supplierDao.getAllSuppler();
		if (!allSuppler.isEmpty()) {
			supplierList = allSuppler.stream().map(supplier -> modelMapper.map(supplier, Supplier.class))
					.collect(Collectors.toList());
		}
		return supplierList;
	}

	public Supplier getSupplierById(long supplierId) {
		SupplierEntity supplierById = supplierDao.getSupplierById(supplierId);
		if ( supplierById != null) {
			return modelMapper.map(supplierById, Supplier.class);
		} else {
			return null;
		}
	}
	public Object updateSuppiler(Supplier supplier)
	{
		SupplierEntity supplierEntity = modelMapper.map(supplier, SupplierEntity.class);
		Object updateSuppiler = supplierDao.updateSuppiler(supplierEntity);
		Supplier sup=null;
		if(updateSuppiler instanceof SupplierEntity)
		{
			SupplierEntity se=(SupplierEntity) updateSuppiler;
			sup=modelMapper.map(se, Supplier.class);
		}
		return sup;
		/*
		 * if( updateSuppiler!=null) { return modelMapper.map(updateSuppiler,
		 * Supplier.class); } else { return null; }
		 */
	}
	
	public List<Supplier> getSupplierByCitiName(String citiName)
	{
		List<SupplierEntity> supplierByCitiName = supplierDao.getSupplierByCitiName(citiName);
		if(supplierByCitiName!=null)
		{
			return supplierByCitiName.stream().map(supplier->modelMapper.map(supplier, Supplier.class)).collect(Collectors.toList());
		}
		else
		{
			return null;
		}
		
	}
}
