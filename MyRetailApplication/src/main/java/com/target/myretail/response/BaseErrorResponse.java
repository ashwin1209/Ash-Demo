package com.target.myretail.response;

import com.target.myretail.exceptions.BaseError;

/**
 * @author Ashwin
 *
 */
public class BaseErrorResponse implements OdataResponse{
	private BaseError error;

	public BaseErrorResponse(BaseError error) {
		this.error=error;
	}

	public BaseError getError() {
		return error;
	}

	public void setError(BaseError error) {
		this.error = error;
	}
	
	
	
}
