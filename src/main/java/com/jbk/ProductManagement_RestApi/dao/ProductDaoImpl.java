package com.jbk.ProductManagement_RestApi.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.ProductManagement_RestApi.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private SessionFactory sf;
	
	@Override
	public ArrayList<Product> productDetails(){
		
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		ArrayList<Product> productList = (ArrayList<Product>) criteria.list();
		
		
		return productList;
	}


	@Override
	public void addProduct(Product product) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		try {
			if(product!=null) {
				session.save(product);
				transaction.commit();
				System.out.println("Product Added");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
	}

	@Override
	public Product getProductByProductId(String productId) {
		Session session=sf.openSession();
		Product product=null;
		System.out.println("productId"+productId); // 
	//	String id=Long.toString(productId);
		try {
			
			product=session.get(Product.class,productId); //?
			System.out.println(product);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return product;
		
		
	}

	@Override
	public Product updateProduct(Product product) {
		
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		boolean isUpdated=false;
		try {
			session.update(product);
			transaction.commit();
			isUpdated=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return product;
	}

	@Override
	public String uploadproduct(List<Product> products) {
	
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
	
   //    System.out.println(products);
		int count=0;
		try {
				for (Product product : products) {
		//		System.out.println(product);
				session.save(product);
				transaction.commit();
				count++;
				}
			
			}
 catch (Exception e) {

			
		}	finally {
			session.close();
		}
		return count+" inserted product";
	}


}		
	

