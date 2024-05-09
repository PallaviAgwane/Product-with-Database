package com.jbk.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.jbk.entity.AddressEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Supplier {
	private long supplierId;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z ]+[a-zA-Z0-9]*$", message = "Supplier not valid")
	private String supplierName;
	
	@Pattern(regexp = "^[0-9]*$", message = "Enter only digits")
	@Size(min = 10,max = 10 ,message = "Enter only 10 digits")
	private String mobileNo;
	
	//private Address address;

	

}
