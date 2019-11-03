package com.target.myretail.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.myretail.constants.ErrorConstants;
import com.target.myretail.domain.Product;
import com.target.myretail.exceptions.BaseError;
import com.target.myretail.exceptions.InternalServerException;
import com.target.myretail.exceptions.NoDataFoundException;
import com.target.myretail.repositories.ProductRepository;

import javassist.expr.Instanceof;

/**
 * @author Ashwin
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

	static final String product_details_url = "https://redsky.target.com/v2/pdp/tcin/";
	@Autowired
	private RestTemplate restTemplate;

	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * this method returns the product list;
	 */
	@Override
	public List<Product> listAll() {
		logger.info("begin list ALL");
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		logger.info("end list ALL with response {} ", products);
		return products;
	}

	/**
	 * this method returns the product based on Id values passed.
	 */
	@Override
	public Product getById(String id) {
		logger.info("begin processing in getById() for id {} ", id);
		Product product = null;
		try {
			ResponseEntity<String> result = restTemplate.getForEntity(product_details_url + id, String.class);
			if (null != result) {
				JsonNode productNode = new ObjectMapper().readTree(result.getBody());

				if (null != productNode && null != productNode.get("product")) {
					product = new Product();
					product.setId(id);
					product.setName(
							productNode.get("product").get("item").get("product_description").get("title").textValue());
				}
			}
			Product product1 = productRepository.findById(id).orElse(null);

			if (null != product1) {
				product.setCurrentPrice(product1.getCurrentPrice());
			}

		} catch (Exception exp) {
			logger.error("exception in getById {} for id {}", exp, id);
			if (exp instanceof HttpClientErrorException) {
				throw new NoDataFoundException(new BaseError(Response.Status.BAD_REQUEST.getStatusCode(),
						Response.Status.BAD_REQUEST.getReasonPhrase(), ErrorConstants.NO_DATA_FOUND_MSG,
						ErrorConstants.NO_DATA_FOUND));
			}
			throw new InternalServerException(exp);
		}
		if (null == product) {
			logger.error("No Data found for the id {}", id);
			throw new NoDataFoundException(new BaseError(Response.Status.BAD_REQUEST.getStatusCode(),
					Response.Status.BAD_REQUEST.getReasonPhrase(), ErrorConstants.NO_DATA_FOUND_MSG,
					ErrorConstants.NO_DATA_FOUND));
		}
		logger.info("end processing in getById() for id {}", id);
		return product;
	}

	/**
	 * this method is used for saving or updating the product in underlying DB.
	 */
	@Override
	public Product saveOrUpdate(Product product) {
		logger.info("begin processing in saveOrUpdate() for product {}", product);
		try {
			ResponseEntity<String> result = restTemplate.getForEntity(product_details_url + product.getId(),
					String.class);
			if (null != result) {
				JsonNode productNode = new ObjectMapper().readTree(result.getBody());
				if (null == productNode) {
					logger.error("No Data found for the id {}", product.getId());
					throw new NoDataFoundException(new BaseError(Response.Status.BAD_REQUEST.getStatusCode(),
							Response.Status.BAD_REQUEST.getReasonPhrase(), ErrorConstants.NO_DATA_FOUND_MSG,
							ErrorConstants.NO_DATA_FOUND));
				}
			}
		} catch (Exception exp) {
			logger.error("exception in getById {} for id {}", exp, product.getId());
			if (exp instanceof HttpClientErrorException || exp instanceof NoDataFoundException) {
				throw new NoDataFoundException(new BaseError(Response.Status.BAD_REQUEST.getStatusCode(),
						Response.Status.BAD_REQUEST.getReasonPhrase(), ErrorConstants.NO_DATA_FOUND_MSG,
						ErrorConstants.NO_DATA_FOUND));
			}
			throw new InternalServerException(exp);
		}

		try {
			productRepository.save(product);
		} catch (Exception exp) {
			throw new InternalServerException(exp);
		}
		logger.info("end processing in saveOrUpdate() for product {}", product);
		return product;
	}

	@Override
	public void delete(String id) {
		productRepository.deleteById(id);
	}

}
