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
@LocalBean//Will map the resource to the URL
@Path("/reminder")
public class ReminderCollectionResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;


	// Add new 
	// 
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Reminder newReminder(Reminder reminder) throws IOException {
		System.out.println("Creating new reminder...");
		//EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityManager entityManager = LifeCoachDao.instance.createEntityManager();
        try {
    		entityManager.persist(reminder);
    		entityManager.refresh(reminder);
    		return reminder;
        }
        finally {
            entityManager.close();
        }
		
	}
	

	
	@Path("{reminderId}")
	public ReminderResource getReminder(@PathParam("reminderId") int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
        try {
    		System.out.println("Reminder by id..."+id);
    		return new ReminderResource(uriInfo, request, id, em);
        } finally {
           em.close();
        }

	}
	
}
