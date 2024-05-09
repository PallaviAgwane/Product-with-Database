package com.jbk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.model.Product;
import com.jbk.service.impl.ProductServiceChangesOfExcelSheet;

@RestController
public class ProductControllerChangesOfExcelSheet {

	@Autowired
	ProductServiceChangesOfExcelSheet productService;
	
	@PostMapping("/upload-excel-sheet")
	public Map<String,Object> uploadExcelSheet(@RequestParam MultipartFile file)
	{
		Map<String,Object> map=new HashMap<>();
	map=	productService.uploadFile(file);
		//map=productService.uploadExcelSheet(file);
		//map.put("total record in sheet", list.size());
		return map;
		
	}
}
