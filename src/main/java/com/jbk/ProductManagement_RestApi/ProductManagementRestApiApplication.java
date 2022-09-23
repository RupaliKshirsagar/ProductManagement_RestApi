package com.jbk.ProductManagement_RestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
@EnableEurekaClient
public class ProductManagementRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementRestApiApplication.class, args);
	}
	
	@Bean
	public CommonsMultipartResolver commonsMultipartResolver() {
		return new  CommonsMultipartResolver();
	}


}
//http://localhost:9191/product/getProductById?productId=20220805122300683