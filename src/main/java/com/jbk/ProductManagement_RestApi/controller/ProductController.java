package com.jbk.ProductManagement_RestApi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jbk.ProductManagement_RestApi.entity.Product;
import com.jbk.ProductManagement_RestApi.service.ProductService;


@RestController
@RequestMapping(value="/product")
public class ProductController {

	@Autowired(required=true)
	private ProductService productService ;
	
	//add product
		@PostMapping("/addProduct")
		public ResponseEntity<HttpStatus> addProduct(@RequestBody Product product)
		{
			try {

				this.productService.addProduct( product);

				return new ResponseEntity<>(HttpStatus.OK);

			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
	
		@GetMapping("/allProduct")
		public ArrayList<Product> productDetails()
		{
		//	productService.productDetails();
			ArrayList<Product> productList = productService.productDetails();
			
			return productList;
		}

		
		// get user using id							
//		@GetMapping(value="/getProductByProductId/{productId}")//for to get value using @Pathvariable
//		public ResponseEntity<Product> getProductByProductId(@PathVariable String productId) {
//			try {
//			
//				this.productService.getProductByProductId(Long.parseLong(productId));
//				return new ResponseEntity<>(HttpStatus.OK); //200 request succeeded
//			 
//		}catch(Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500 error
//		}
//			
//		}
		// get user using id							
				@GetMapping(value="/getProductById")//for to get value using @Pathvariable
				public ResponseEntity<Product> getProductByProductId(@RequestParam String productId) {
					Product product= productService.getProductByProductId(productId);
					if(product!=null) {
					
					return new ResponseEntity<Product>(product,HttpStatus.OK); //200 request succeeded
					} else {
				
					return new ResponseEntity<Product>(product,HttpStatus.INTERNAL_SERVER_ERROR);//500 error
				}
					
				}
		
		// update product
		@PutMapping(value="/updateProduct")
		public Product updateProduct(@RequestBody Product product){
		
			
			return this.productService.updateProduct(product);
		
	}
		
		@PostMapping("/uploadsheet")
		public String uploadsheet(@RequestParam CommonsMultipartFile file,HttpSession session,Model model) {
			System.out.println("111");
			
			String count=	productService.uploadSheet(file,session);
			System.out.println(count);
		model.addAttribute("count",count);
			return "home";

		}
		
		
		@GetMapping(value="/generateReport/{format}")
		public ResponseEntity<String> generateReport(@PathVariable String format){
			
			String msg=productService.generateReport(format);
			return new ResponseEntity<>(msg,HttpStatus.OK);
			
		}
		
//		
//		@PostMapping("/uploadsheet")
//		public ResponseEntity<String> uploadProduct(@RequestParam ("file") MultipartFile file){
//			
//			System.out.println("File Name: "+file.getName());
//			System.out.println("File OrigionalName: "+file.getOriginalFilename());
//			System.out.println("File Size: "+file.getSize());
//			System.out.println("File Content Type: "+file.getContentType());
//			
//			
//			return ResponseEntity.ok("Working");
//			
//		}
//		
	
}

