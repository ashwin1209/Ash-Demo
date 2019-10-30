package com.target.myretail.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

/**
 * @author Ashwin
 *
 */
public class NoDataFoundException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoDataFoundException() {
		this(null, null);
	}

	public NoDataFoundException(String message) {
		this(message, null);
	}

	public NoDataFoundException(Throwable cause) {
		this(null, cause);
	}

	public NoDataFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoDataFoundException(BaseError error) {
		super(error);
	}

	@Override
	public StatusType getHttpStatus() {
		// TODO Auto-generated method stub
		return Response.Status.OK;
	}
}
