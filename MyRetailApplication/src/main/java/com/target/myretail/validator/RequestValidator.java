package com.target.myretail.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.target.myretail.constants.ErrorConstants;
import com.target.myretail.domain.Product;
import com.target.myretail.exceptions.BadRequestException;
import com.target.myretail.exceptions.BaseError;

/**
 * @author Ashwin
 *
 */
public class RequestValidator {
	
	/**
	 * @param id
	 */
	public static void validateId(String id) {
		Pattern pattern  = Pattern.compile("[-a-z-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(id);
		if(StringUtils.isBlank(id)) {
			throw new BadRequestException(new BaseError(Response.Status.BAD_REQUEST.getStatusCode(),
					Response.Status.BAD_REQUEST.getReasonPhrase(), ErrorConstants.EMPTY_ID_MSG,
					ErrorConstants.EMPTY_ID_CODE));
		}else if(!matcher.matches()) {
			throw new BadRequestException(new BaseError(Response.Status.BAD_REQUEST.getStatusCode(),
					Response.Status.BAD_REQUEST.getReasonPhrase(), ErrorConstants.INVALID_ID_MSG,
					ErrorConstants.INVALID_ID_CODE));
		}
	}

	public static void validateRequest(String id, Product product) {
		Pattern pattern  = Pattern.compile("[-a-z-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(product.getId());
		if(StringUtils.isBlank(product.getId())) {
			throw new BadRequestException(new BaseError(Response.Status.BAD_REQUEST.getStatusCode(),
					Response.Status.BAD_REQUEST.getReasonPhrase(), ErrorConstants.EMPTY_ID_MSG,
					ErrorConstants.EMPTY_ID_CODE));
		}else if(!matcher.matches()) {
			throw new BadRequestException(new BaseError(Response.Status.BAD_REQUEST.getStatusCode(),
					Response.Status.BAD_REQUEST.getReasonPhrase(), ErrorConstants.INVALID_ID_MSG,
					ErrorConstants.INVALID_ID_CODE));
		}else if(!id.equals(product.getId())) {
			throw new BadRequestException(new BaseError(Response.Status.BAD_REQUEST.getStatusCode(),
					Response.Status.BAD_REQUEST.getReasonPhrase(), ErrorConstants.INVALID_PRODUCT_ID_MSG,
					ErrorConstants.INVALID_PRODUCT_ID_CODE));
		}
		
	}

}
