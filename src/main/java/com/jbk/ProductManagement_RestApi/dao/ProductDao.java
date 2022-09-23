package com.jbk.ProductManagement_RestApi.dao;

import java.util.ArrayList;
import java.util.List;

import com.jbk.ProductManagement_RestApi.entity.Product;

public interface ProductDao {

	void addProduct(Product product);

	Product getProductByProductId(String productId);

	Product updateProduct(Product product);

	public ArrayList<Product> productDetails();

	String uploadproduct(List<Product> products);



}
