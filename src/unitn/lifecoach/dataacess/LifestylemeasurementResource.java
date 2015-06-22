
package unitn.lifecoach.dataacess;
import unitn.lifecoach.model.*;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import unitn.lifecoach.dao.LifeCoachDao;
@Path("/lifestylemeasurement")
public class LifestylemeasurementResource {
@Context
UriInfo uriInfo;
@Context
Request request;


                                            
@SuppressWarnings("static-access")
@GET
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public List<Lifestylemeasuremnt> getAvailableMeasurements()
{
	List<Lifestylemeasuremnt> lMeasure=Lifestylemeasuremnt.getAll();
	return lMeasure;
}

}