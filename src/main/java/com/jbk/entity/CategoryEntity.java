package com.jbk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="Category")
public class CategoryEntity {

	@Id
	private long categoryId;

	@Column(unique = true, nullable = false)
	private String categoryName;

	private String discription;

	private int discount;

	private int gst;

	private double deliveryCharges;
	
	/*
	 * @OneToOne(mappedBy = "productCategory") ProductEntity productEntity;
	 */
	public CategoryEntity() {
		// TODO Auto-generated constructor stub
	}

	public CategoryEntity(long categoryId, String categoryName, String discription, int discount, int gst,
			double deliveryCharges) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.discription = discription;
		this.discount = discount;
		this.gst = gst;
		this.deliveryCharges = deliveryCharges;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getGst() {
		return gst;
	}

	public void setGst(int gst) {
		this.gst = gst;
	}

	public double getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(double deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", discription=" + discription
				+ ", discount=" + discount + ", gst=" + gst + ", deliveryCharges=" + deliveryCharges + "]";
	}

}
