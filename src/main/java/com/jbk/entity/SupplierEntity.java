package com.jbk.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "supplier")
@Getter
@Setter
@NoArgsConstructor
public class SupplierEntity {

	@Id
	@Column(unique = true, nullable = false)
	private long supplierId;

	@Column(unique = true, nullable = false)
	private String supplierName;

	@Column(unique = true, nullable = false)
	private String mobileNo;
	
	
	/*
	 * @OneToMany(mappedBy = "supplier") private List<AddressEntity> address;
	 */
	

}
