package com.jbk.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.dao.ProductDao;
import com.jbk.entity.ProductEntity;
import com.jbk.model.Category;
import com.jbk.model.Product;
import com.jbk.model.Supplier;
import com.jbk.service.CategoryService;
import com.jbk.validation.ObjectValidation;

@Service
public class ProductServiceChangesOfExcelSheet {

	@Autowired
	private ProductDao dao;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	ObjectValidation objectValidation;

	List<Integer> alreadyExistRecord = new ArrayList<Integer>();
	Map<String, Object> map = new LinkedHashMap<>();
	Map<Integer, Map<String, String>> rowMap = new LinkedHashMap<Integer, Map<String, String>>();
	Map<String, String> errorMap = new LinkedHashMap<String, String>();
	int totalRecords = 0;

	public int addProduct(Product product) {

		if (product.getProductId() == 0) {

		}
		return dao.addProduct(modelMapper.map(product, ProductEntity.class));
	}

	public Product getProductByProductName(String productName) {
		ProductEntity productEntity = dao.getProductByProductName(productName);
		if (productEntity != null) {
			return modelMapper.map(productEntity, Product.class);
		} else {
			return null;
		}
	}

	public Map<String, Object> uploadFile(MultipartFile file) {
		String name = file.getOriginalFilename();
		try {
			FileOutputStream fos = new FileOutputStream("src/main/resources/" + name);
			byte[] bytesData = file.getBytes();
			fos.write(bytesData);
		} catch (Exception ex) {
			ex.getMessage();
		}
		List<Product> readExcel = readExcelSheet("src/main/resources/" + name);
		int uploadedRecord = 0;
		for (Product product : readExcel) {
			int addProduct = addProduct(product);
			if (addProduct == 1) {
				++uploadedRecord;

			}
		}
		map.put("Total record is sheet", totalRecords);
		map.put("Uploaded record in DB Count", uploadedRecord);
		map.put("Already exist record in DB count", alreadyExistRecord.size());
		map.put("already exist record in DB Rows number", alreadyExistRecord);
		map.put("Total excluded record", rowMap.size());
		map.put("Bad records", rowMap);
		return map;

	}

	public List<Product> readExcelSheet(String filePath) {
		List<Product> list = new ArrayList<>();
		try {
			Workbook workbook = new XSSFWorkbook(filePath);
			Sheet sheet = workbook.getSheetAt(0);
			totalRecords = sheet.getLastRowNum();
			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext()) {
				Row row = rows.next();
				int rowNum = row.getRowNum();
				if (rowNum == 0) {
					continue;
				}
				Product product = new Product();
				// to setProduct Id
				String productId = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
				product.setProductId(Long.parseLong(productId) + rowNum);
				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext()) {

					Cell cell = cells.next();
					int columnIndex = cell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						product.setProductName(cell.getStringCellValue());
						break;

					case 1:
						Supplier supplier = new Supplier();
						supplier.setSupplierId((long) cell.getNumericCellValue());
						product.setSupplier(supplier);
						break;
					case 2:
						Category category = new Category();
						category.setCategoryId((long) cell.getNumericCellValue());
						product.setCategory(category);
						break;
					case 3:
						product.setProductQty((int) cell.getNumericCellValue());
						break;
					case 4:
						product.setProductPrice(cell.getNumericCellValue());
						break;

					}
				}
				errorMap = objectValidation.validateProduct(product);
				if (errorMap.isEmpty()) {
					Product producttName = getProductByProductName(product.getProductName());
					if (producttName == null) {
						list.add(product);
					} else {
						alreadyExistRecord.add(row.getRowNum() + 1);
					}
				} else {
					rowMap.put(row.getRowNum() + 1, errorMap);
				}

			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return list;
	}
}

/*
 * public Map<String, Object> uploadExcelSheet(MultipartFile file) {
 * List<Product> list = new ArrayList<Product>(); // Write file String fileName
 * = file.getOriginalFilename(); try { FileOutputStream fos = new
 * FileOutputStream("src/main/resources/" + fileName); byte[] byteData =
 * file.getBytes(); fos.write(byteData); } catch (IOException ex) {
 * System.out.println(ex.getMessage()); }
 * 
 * list = readFile("src/main/resources/" + fileName); //
 * System.err.println(list.size()); Map<String, Object> map = new HashMap<>();
 * for ( Product product : list) { int addProduct = addProduct(product);
 * 
 * } map.put("total record in sheet", list.size());
 * 
 * return map; }
 */

// file reading
/*
 * public List<Product> readFile(String path) { List<Product> list = new
 * ArrayList<Product>(); try { Workbook workbook = new XSSFWorkbook(path); Sheet
 * sheet = workbook.getSheetAt(0); Iterator<Row> rows = sheet.rowIterator();
 * while (rows.hasNext()) { Row row = rows.next(); int rowNum = row.getRowNum();
 * if (rowNum == 0) { continue; } Product product = new Product(); String
 * productId = new SimpleDateFormat("yyyyMMddHHmmss").format(new
 * java.util.Date()); product.setProductId(Long.parseLong(productId) + rowNum);
 * Iterator<Cell> cells = row.cellIterator(); while (cells.hasNext()) { Cell
 * cell = cells.next(); int columnIndex = cell.getColumnIndex(); switch
 * (columnIndex) { case 0: product.setProductName(cell.getStringCellValue());
 * break; case 1: Supplier s = new Supplier(); s.setSupplierId((long)
 * cell.getNumericCellValue()); product.setProductSupplier(s); break; case 2:
 * Category c = new Category(); c.setCategoryId((long)
 * cell.getNumericCellValue()); product.setProductCategory(c); break; case 3:
 * product.setProductQty((int) cell.getNumericCellValue()); break; case 4:
 * product.setProductPrice(cell.getNumericCellValue()); break; } }
 * list.add(product); } } catch (Exception ex) {
 * System.out.println(ex.getMessage()); } list.forEach(lists ->
 * System.out.println(lists)); return list; }
 */
