package com.jbk.ProductManagement_RestApi.service;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import com.jbk.ProductManagement_RestApi.dao.ProductDao;
import com.jbk.ProductManagement_RestApi.entity.Product;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public  class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Override
	public ArrayList<Product> productDetails(){
		
		return productDao.productDetails();
		
	}
	

	@Override
	public void addProduct(Product product) {
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
		product.setProductId(timeStamp);

		productDao.addProduct(product);
	}

	@Override
	public Product getProductByProductId(String productId) {
		
		return productDao.getProductByProductId(productId);
	}

	@Override
	public Product updateProduct(Product product) {
	
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
		product.setProductId(timeStamp);
		return productDao.updateProduct(product);
	}

	@Override
	public String uploadSheet(CommonsMultipartFile file, HttpSession session) {
		String filePath = session.getServletContext().getRealPath("/WEB-INF/Uploaded");
		System.out.println(filePath);
		String fileName = file.getOriginalFilename();
		byte[] data = file.getBytes();
		String count = null;
		try {
			FileOutputStream fos = new FileOutputStream(new File(filePath + File.separator + fileName));
			fos.write(data);

			List<Product> products = readExcel(filePath + File.separator + fileName);
			
			for (Product prod : products) {
				System.out.println(prod);
			}
			count = productDao.uploadproduct(products);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;

	}

	private List<Product> readExcel(String path)throws IOException {
		 List<Product> productList=new ArrayList<>();
		
		 Product product = null;
		try {
			FileInputStream fis = new FileInputStream(new File(path));

			Workbook workbook = new XSSFWorkbook(fis);
			
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();

			

			while (rows.hasNext()) {
				product = new Product();
				Row row = rows.next();
				Iterator<Cell> cell = row.cellIterator();

				while (cell.hasNext()) {
					String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
					product.setProductId(timeStamp);

					
					Cell cells = cell.next();

		//			 System.out.print(cells.getStringCellValue()+"\t");
				int col = cells.getColumnIndex();
				switch (col) {
				case 0:{
					product.setProductName(cells.getStringCellValue());
					
					break;
				        }
				
				case 1:{
					product.setProductPrice(cells.getNumericCellValue());
					break;
				      }
				case 2:{
					product.setProductExpiryDate(cells.getDateCellValue());
					break;
				      }
				
				
				     }
				
				     }
				
			}productList.add(product);
		//	System.out.println(productList);

		} catch (FileNotFoundException  e) 
		{
			e.printStackTrace();
		}
		return productList;
	}
	
	
	




	
	@Override
	public String generateReport(String format) {
		List<Product> productList=productDao.productDetails();
		String destination="C:\\GenerateDocument";
		try {
			// Locate File
			File file=ResourceUtils.getFile("classpath:product1.jrxml");
			
		// Compile file
		JasperReport jasperReport =	JasperCompileManager.compileReport(file.getAbsolutePath());
		
		//pass Datasourse
		JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(productList);
		
		// fill report
	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null ,dataSource);
		
		//Export file
	
	if(format.equalsIgnoreCase("pdf")) {
		JasperExportManager.exportReportToPdfFile(jasperPrint, destination+"\\ProductList.pdf");
		
		destination=" ProductList.pdf file Generated at "+destination;
		
	}
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		
		
		
		
		return destination;
	
	

	}
	}





