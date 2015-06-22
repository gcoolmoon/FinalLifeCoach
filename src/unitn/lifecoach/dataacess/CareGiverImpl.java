package unitn.lifecoach.dataacess;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import unitn.lifecoach.model.*;


@Path("/caregiver")
public class CareGiverImpl {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	
	
		 // This method is called if TEXT_PLAIN is request
	    @GET
	    @Produces(MediaType.TEXT_PLAIN)
	    public String sayPlainTextHello() {

			System.out.println("ciao");
	      return "Hello Jersey";
	    }
	
	
}
