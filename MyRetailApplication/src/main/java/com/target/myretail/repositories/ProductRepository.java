package com.target.myretail.repositories;

import org.springframework.data.repository.CrudRepository;

import com.target.myretail.domain.Product;


/**
 * @author Ashwin
 *
 */
public interface ProductRepository extends CrudRepository<Product, String> {
}
