package com.target.myretail.responsebuilder;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.myretail.exceptions.InternalServerException;
import com.target.myretail.response.EntityResponse;
import com.target.myretail.response.OdataResponse;

/**
 * @author Ashwin
 *
 */
public class EntityResponseBuilder implements ResponseBuilder{

	@Override
	public Response buildResponse(StatusType statusType, OdataResponse oDataResponse) {
		EntityResponse<?> entityResponse = (EntityResponse<?>) oDataResponse;
		ObjectMapper obj = new ObjectMapper();
		String responseString = null;
		
		if(null != entityResponse) {
			try {
				responseString = obj.writeValueAsString(entityResponse.getEntityObj());
				return Response.ok(responseString, entityResponse.getContentType()).build();
			}catch(Exception exp) {
				throw new InternalServerException("JsonResponse parsing exception");
			}
		}
		
		return Response.ok(responseString, "").build();
	}
}
