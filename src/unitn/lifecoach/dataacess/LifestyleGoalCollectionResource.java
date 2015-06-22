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
@Path("/lifestylegoal")
public class LifestyleGoalCollectionResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
    
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Lifestylegoal newLifestylegoal(Lifestylegoal lifegoal) throws IOException {
		System.out.println("Creating new lifestylegoal...");
		EntityManager entityManager = LifeCoachDao.instance.createEntityManager();
        try {
    		entityManager.persist(lifegoal);
    		entityManager.refresh(lifegoal);
    		return lifegoal;
        }
        finally {
            entityManager.close();
        }
		
	}
	
	
	
	@Path("{personId}")
	public LifestyleGoalResource getLifetsylegoal(@PathParam("lifestylegoalId") int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
        try {
    		System.out.println("lifestylegoalId by id..."+id);
    		return new LifestyleGoalResource(uriInfo, request, id, em);
        } finally {
           em.close();
        }

	}
	 @Path("{measuretype}/{personId}")
 	public LifestyleGoalResource getlifestylegoalBypersonmeasure(@PathParam("personId") int id, @PathParam("measureType") String measureType ) {
 		EntityManager em = LifeCoachDao.instance.createEntityManager();
         try {
     		System.out.println("Lifestylegoal by personid. and meaasure type.."+id);
     		return new LifestyleGoalResource(uriInfo, request, id, em,measureType);
         } finally {
            em.close();
         }

	}
	// get lifestylegoal by lifestylemeasureId
		
}
