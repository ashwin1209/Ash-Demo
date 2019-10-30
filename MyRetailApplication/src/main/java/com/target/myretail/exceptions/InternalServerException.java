package com.target.myretail.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

/**
 * @author Ashwin
 *
 */
public class InternalServerException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InternalServerException() {
		this(null, null);
	}

	public InternalServerException(String message) {
		this(message, null);
	}

	public InternalServerException(Throwable cause) {
		this(null, cause);
	}

	public InternalServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public InternalServerException(BaseError error) {
		super(error);
	}

	@Override
	public StatusType getHttpStatus() {
		// TODO Auto-generated method stub
		return Response.Status.INTERNAL_SERVER_ERROR;
	}
}
