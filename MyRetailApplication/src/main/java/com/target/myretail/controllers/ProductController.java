package com.target.myretail.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.target.myretail.domain.Product;
import com.target.myretail.exceptions.BadRequestException;
import com.target.myretail.exceptions.InternalServerException;
import com.target.myretail.exceptions.NoDataFoundException;
import com.target.myretail.response.BaseErrorResponse;
import com.target.myretail.response.EntityResponse;
import com.target.myretail.responsebuilder.ResponseBuilder;
import com.target.myretail.responsebuilder.ResponseBuilderFactory;
import com.target.myretail.services.ProductService;
import com.target.myretail.validator.RequestValidator;

/**
 * @author Ashwin this class defines the CRUD operations on product entity over
 *         Rest API
 */
@Path("products")
public class ProductController {
	private static final Logger logger = LogManager.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/{id}")
	public Response get(@PathParam("id") final String id) {
		logger.info("request processing for id {}" + id);
		ResponseBuilder responseBuilder = null;
		Product product = null;
		try {
			// validate the path variable Id
			RequestValidator.validateId(id);
			// retrieve product by its Id;
			product = productService.getById(id);
			if (null != product) {
				// based on the response build the response entity type.
				responseBuilder = ResponseBuilderFactory.getBuilder("EntityResponse");
				EntityResponse<Product> entityResponse = new EntityResponse<Product>();
				entityResponse.setEntityObj(product);
				// return Http Status Success with result.
				return responseBuilder.buildResponse(Response.Status.OK, entityResponse);
			}
		} catch (Exception exception) {
			logger.error("exception {}" + exception.getMessage());
			// Build exception specific error response for better consumer readability
			responseBuilder = ResponseBuilderFactory.getBuilder("errorResponse");
			if (exception instanceof NoDataFoundException) {
				NoDataFoundException badRequestException = (NoDataFoundException) exception;
				return responseBuilder.buildResponse(Response.Status.NO_CONTENT,
						new BaseErrorResponse(badRequestException.getBaseError()));
			} else if (exception instanceof BadRequestException) {
				BadRequestException badRequestException = (BadRequestException) exception;
				return responseBuilder.buildResponse(Response.Status.BAD_REQUEST,
						new BaseErrorResponse(badRequestException.getBaseError()));
			} else {
				InternalServerException internalServerException = (InternalServerException) exception;
				return responseBuilder.buildResponse(Response.Status.INTERNAL_SERVER_ERROR,
						new BaseErrorResponse(internalServerException.getBaseError()));
			}
		}
		return null;
	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response update(Product product, @PathParam("id") final String id) {
		logger.info("request processing for id {}" + id);
		ResponseBuilder responseBuilder = null;
		Product productResponse = null;
		try {
			// validate the path variable Id
			RequestValidator.validateId(id);
			// validate the requestBody
			RequestValidator.validateRequest(id, product);
			// Save or update the product
			productResponse = productService.saveOrUpdate(product);
			// Build the response entity based on result
			if (null != productResponse) {
				responseBuilder = ResponseBuilderFactory.getBuilder("EntityResponse");
				EntityResponse<Product> entityResponse = new EntityResponse<Product>();
				entityResponse.setEntityObj(productResponse);
				return responseBuilder.buildResponse(Response.Status.CREATED, entityResponse);
			}
		} catch (Exception exception) {
			// Build exception specific error response for better consumer readability
			logger.error("exception {}" + exception.getMessage());
			responseBuilder = ResponseBuilderFactory.getBuilder("errorResponse");
			if (exception instanceof NoDataFoundException) {
				NoDataFoundException badRequestException = (NoDataFoundException) exception;
				return responseBuilder.buildResponse(Response.Status.NO_CONTENT,
						new BaseErrorResponse(badRequestException.getBaseError()));
			} else if (exception instanceof BadRequestException) {
				BadRequestException badRequestException = (BadRequestException) exception;
				return responseBuilder.buildResponse(Response.Status.BAD_REQUEST,
						new BaseErrorResponse(badRequestException.getBaseError()));
			} else {
				InternalServerException internalServerException = (InternalServerException) exception;
				return responseBuilder.buildResponse(Response.Status.INTERNAL_SERVER_ERROR,
						new BaseErrorResponse(internalServerException.getBaseError()));
			}
		}
		return null;
	}

}
