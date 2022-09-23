package com.jbk.ProductManagement_RestApi.service;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jbk.ProductManagement_RestApi.entity.Product;

public interface ProductService {

	void addProduct(Product product);

    public Product getProductByProductId(String productId);

	public Product updateProduct(Product product);

	public String uploadSheet(CommonsMultipartFile file, HttpSession session);
	
	public String generateReport(String format);

	
	public ArrayList<Product> productDetails();

}
