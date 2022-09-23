package com.jbk.ProductManagement_RestApi.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
public class Product {
	@Id
	private String productId;
	private String productName;
	private float productPrice;
	private Date productExpiryDate;
	
	
	public Product() {
		// TODO Auto-generated constructor stub
	}


	public Product(String productId, String productName, float productPrice, Date productExpiryDate) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productExpiryDate = productExpiryDate;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public float getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double d) {
		this.productPrice = (float)d;
	}


	public Date getProductExpiryDate() {
		return productExpiryDate;
	}


	public void setProductExpiryDate(Date string) {
		this.productExpiryDate = string;
	}



	
	
	

}
