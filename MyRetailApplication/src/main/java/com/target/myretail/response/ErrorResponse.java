package com.target.myretail.response;

import com.target.myretail.exceptions.BaseError;

/**
 * @author Ashwin
 *
 */
public interface ErrorResponse {
	public BaseError getError();
}
