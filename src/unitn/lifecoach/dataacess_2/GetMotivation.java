package unitn.lifecoach.dataacess;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.client.ClientConfig;

import unitn.lifecoach.model.*;

//gets  a motivational quote from theysaidso 
@Path("/motivation")
public class GetMotivation {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	static javax.ws.rs.core.Response resp = null;
	static ClientConfig clientConfig = new ClientConfig();
	static javax.ws.rs.client.Client client = ClientBuilder.newClient(clientConfig);	
	static WebTarget service = client.target("http://api.theysaidso.com/");
	
	
		 // This method is called if TEXT_PLAIN is request
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response getMotivation() {
	    	resp =service.path("qod.json").request().accept(MediaType.APPLICATION_JSON).get();
			
			//System.out.println("ciao");
	      return resp;
	    }
	
	
}
