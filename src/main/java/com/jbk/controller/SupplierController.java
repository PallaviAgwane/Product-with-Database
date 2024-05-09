package com.jbk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourceNotFoundException;
import com.jbk.model.Supplier;
import com.jbk.service.SupplierService;

@RestController
public class SupplierController {

	@Autowired
	SupplierService service;

	@PostMapping("/add-supplier")
	public String addSupplier(@Valid @RequestBody Supplier supplier) {
		int addSupplier = service.addSupplier(supplier);
		if (addSupplier == 1) {
			return "Record inserted successfully!!!!!!!";
		} else if (addSupplier == 2) {
			throw new ResourceAlreadyExistException("This Id record is already prasent in database");
		} else {
			throw new ResourceAlreadyExistException("failed to save supplier ...Please check unique feild!!");
		}
	}

	@GetMapping("/get-all-supplier")
	public List<Supplier> getAllSupplier() {
		List<Supplier> allSupplier = service.getAllSupplier();
		if (allSupplier.isEmpty()) {
			throw new ResourceNotFoundException("Supplier data is not prasent");
		} else {
			return allSupplier;
		}
	}

	@GetMapping("/get-supplier-by-id/{supplierId}")
	Supplier getSupplierById(@PathVariable long supplierId) {
		Supplier supplier = service.getSupplierById(supplierId);
		if (supplier != null) {
			return supplier;
		} else {
			throw new ResourceNotFoundException(supplierId + " id supplier record is not prasent ");
		}
	}

	@PutMapping("/update-supplier")
	public Supplier updateSuppiler(@Valid @RequestBody Supplier supplier) {
		
		Object obj = service.updateSuppiler(supplier);

		if (obj instanceof Integer) {
			int a = (Integer) obj;
			if (a == 1) {
				throw new ResourceAlreadyExistException("This record is already preasent ..please check unique fields");
			}
		}
		if (obj != null) {
			
			return (Supplier) obj;
		} else {
			throw new ResourceNotFoundException("Category", "Id", supplier.getSupplierId());
		}

	}

	@GetMapping("get-supplier-by-citiNaame/{citiName}")
	public List<Supplier> getSupplierByCitiName(@PathVariable String citiName) {
		List<Supplier> supplierByCitiName = service.getSupplierByCitiName(citiName);
		if (supplierByCitiName.isEmpty()) {
			throw new ResourceNotFoundException(citiName + " citi supplier data are not found!!!!!!!");
		} else {
			return supplierByCitiName;
		}
	}

}
