package com.target.myretail.responsebuilder;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.myretail.response.BaseErrorResponse;
import com.target.myretail.response.OdataResponse;

/**
 * @author Ashwin
 *
 */
public class ErrorResponseBuilder implements ResponseBuilder{

	@Override
	public Response buildResponse(StatusType statusType, OdataResponse oDataResponse) {
		BaseErrorResponse errorReponse = (BaseErrorResponse) oDataResponse;
		ObjectMapper obj = new ObjectMapper();
		String responseString = null;
		
		if(null != errorReponse) {
			try {
				responseString = obj.writeValueAsString(errorReponse);
				
			}catch(Exception exp) {
				
			}
		}
		
		return Response.status(statusType.getStatusCode()).entity(responseString).build();
	}
	
	

}
