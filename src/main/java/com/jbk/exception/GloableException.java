package com.jbk.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GloableException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Object> methodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, Object> errorMap = new HashMap<>();
		List<FieldError> fieldErrors = ex.getFieldErrors();

		for (FieldError fieldError : fieldErrors) {
			errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return errorMap;

	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public Map<String, String> resourceNotFound(ResourceNotFoundException ex)
	{
		Map<String, String> map=new HashMap<String, String>();
		map.put("default message",ex.getMessage());
		return map;
		
	}

	@ExceptionHandler(ResourceAlreadyExistException.class)
	public Map<String, String> resourceAlreadyExist(ResourceAlreadyExistException ex) {
		Map<String, String> map = new HashMap<>();
		map.put("Default Message", ex.getMessage());
		return map;
	}


	@ExceptionHandler(MethodArgumentTypeMismatchException.class)

	public HashMap<String, String> methodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
		String name = ex.getName();
		System.out.println("name " + name);
		Class<?> requiredType = ex.getRequiredType();
		System.out.println("re> " + requiredType);
		Object value = ex.getValue();
		System.out.println("value " + value);
		String message = String.format("%s must be number only %s not number ", name, value);
		HashMap<String, String> map = new HashMap<>();
		map.put("Default message", message);
		return map;
	}
}
