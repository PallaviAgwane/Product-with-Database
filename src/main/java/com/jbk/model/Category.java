package com.jbk.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	private long categoryId;

	@NotBlank(message = "categoryName cannot be empty.")
	@Pattern(regexp = "^[a-z A-Z]+$", message = "categoryName should contains only alphabetic characters.")
	private String categoryName;

	@NotBlank(message = "category description cannot be empty.")
	@Pattern(regexp = "^[a-z A-Z]+$", message = "Description should contains only alphabetic characters.")
	private String discription;

	@Min(value = 0, message = "discount should not be less than 0%.")
	@Max(value = 50, message = "discount should not be more than 50%.")
	private int discount;

	@Min(value = 0, message = "gst should not be less than 0%.")
	@Max(value = 18, message = "gst should not be more than 18%.")
	private int gst;

	@Min(value = 0, message = "deliveryCharges should not be less than 0% of product price.")
	@Max(value = 10, message = "deliveryCharges should not be more than 10% of product price.")
	private double deliveryCharges;

	

}
