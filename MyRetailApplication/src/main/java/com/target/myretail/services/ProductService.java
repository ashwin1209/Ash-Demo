package com.target.myretail.services;

import java.util.List;

import com.target.myretail.domain.Product;


/**
 * @author Ashwin
 * 
 */
public interface ProductService {

	/**
	 * @return
	 */
	List<Product> listAll();

	/**
	 * @param id
	 * @return
	 */
	Product getById(String id);

	/**
	 * @param product
	 * @return
	 */
	Product saveOrUpdate(Product product);

	/**
	 * @param id
	 */
	void delete(String id);

}
