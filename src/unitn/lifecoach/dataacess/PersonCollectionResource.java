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
@Path("/person")
public class PersonCollectionResource {

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
	public Person newPerson(Person person) throws IOException {
		System.out.println("Creating new person...");
		//EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityManager entityManager = LifeCoachDao.instance.createEntityManager();
        try {
    		entityManager.persist(person);
    		entityManager.refresh(person);
    		return person;
        }
        finally {
            entityManager.close();
        }
		
	}
	
	// retuns the number of people
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		System.out.println("Getting count...");
	    //List<Person> list = entityManager.createNamedQuery("Person.findAll", Person.class).getResultList();
	    List<Person> people = Person.getAll();
		int count = people.size();
		return String.valueOf(count);
	}
	
	@Path("{personId}")
	public PersonResource getPerson(@PathParam("personId") int id) {
		//EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityManager em = LifeCoachDao.instance.createEntityManager();
        try {
    		System.out.println("Person by id..."+id);
    		return new PersonResource(uriInfo, request, id, em);
        } finally {
           em.close();
        }

       
	}
	
	@GET
 	@Path("all")
 	@Produces(MediaType.TEXT_PLAIN)
 	public List<Person> getPeople() {
     	List<Person> people = Person.getAll();
     	return people;
         }	
}
