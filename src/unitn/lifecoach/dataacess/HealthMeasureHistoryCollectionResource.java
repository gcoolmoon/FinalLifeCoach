package unitn.lifecoach.dataacess;

import unitn.lifecoach.dao.LifeCoachDao;
import unitn.lifecoach.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.junit.runners.Parameterized.Parameters;

import sun.invoke.empty.Empty;


@Stateless
@LocalBean
@Path("/healthmeasure")
public class HealthMeasureHistoryCollectionResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@SuppressWarnings("static-access")
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Healthmeasurehistory> getAvailablehistories()
	{
		List<Healthmeasurehistory> hmh=Healthmeasurehistory.getAll();
		return hmh;
	}
    
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Healthmeasurehistory newHealthmeasurehistory(Healthmeasurehistory healthmh , int personId) throws IOException {
		System.out.println("Creating new healthmeasurehistory...");
		EntityManager entityManager = LifeCoachDao.instance.createEntityManager();
        try {
    		
        	entityManager.persist(healthmh);
    		entityManager.refresh(healthmh);
    		return healthmh;
        }
        finally {
            entityManager.close();
        }
		
	}
	
	
	
	@Path("current/{personId}")
	public HealthMeasureHistoryResource getMeasurecurrent(@PathParam("personId") int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
        try {
    		System.out.println("Healthmeasurehistory by id..."+id);
    		return new HealthMeasureHistoryResource(uriInfo, request, id, em);
        } finally {
           em.close();
        }}
        
        @Path("{personId}")
    	public HealthMeasureHistoryResource getMeasure(@PathParam("personId") int id) {
    		EntityManager em = LifeCoachDao.instance.createEntityManager();
            try {
        		System.out.println("Healthmeasurehistory by id..."+id);
        		return new HealthMeasureHistoryResource(uriInfo, request, id, em);
            } finally {
               em.close();
            }

	}
	
	// get healthmeasurehistory by healthmeasureId
		
}
