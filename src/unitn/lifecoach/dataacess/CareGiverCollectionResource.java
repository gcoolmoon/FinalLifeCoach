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
@Path("/caregiver")
public class CareGiverCollectionResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
    
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Caregiiver newCaregiiver(Caregiiver caregiver) throws IOException {
		System.out.println("Creating new caregiver...");
		EntityManager entityManager = LifeCoachDao.instance.createEntityManager();
        try {
    		entityManager.persist(caregiver);
    		entityManager.refresh(caregiver);
    		return caregiver;
        }
        finally {
            entityManager.close();
        }
		
	}
	
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		System.out.println("Getting count...");
	    List<Caregiiver> caregivers = Caregiiver.getAll();
		int count = caregivers.size();
		return String.valueOf(count);
	}
	
	@Path("{caregiverId}")
	public CareGiverResource  getCaregiver(@PathParam("caregiverId") int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
        try {
    		System.out.println("Caregiver by id..."+id);
    		return new CareGiverResource(uriInfo, request, id, em);
        } finally {
           em.close();
        }

	}
	@GET
 	@Path("all")
 	@Produces(MediaType.TEXT_PLAIN)
 	public List<Caregiiver> getCaregiver() {
     	List<Caregiiver> caregiver = Caregiiver.getAll();
     	return caregiver;
         }	
		
	
}
