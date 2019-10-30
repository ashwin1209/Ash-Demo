package com.target.myretail.responsebuilder;

import javax.ws.rs.core.Response;

import com.target.myretail.response.OdataResponse;
/**
 * @author Ashwin
 *
 */
public interface ResponseBuilder {
	
	public Response buildResponse(Response.StatusType statusType, OdataResponse oDataResponse);
	

}
