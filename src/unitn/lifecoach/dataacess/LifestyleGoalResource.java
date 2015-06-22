package unitn.lifecoach.dataacess;

import java.util.ArrayList;
import java.util.List;

import unitn.lifecoach.dao.*;
import unitn.lifecoach.model.*;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Stateless
@LocalBean
public class LifestyleGoalResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;
	private String measureType;

	public LifestyleGoalResource(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public LifestyleGoalResource(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	public LifestyleGoalResource(UriInfo uriInfo, Request request, int id,
			EntityManager em, String measureType) {
		// TODO Auto-generated constructor stub
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
		this.measureType = measureType;
	}

	@SuppressWarnings("static-access")
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Lifestylegoal> getAvailablegoals()
	{
		List<Lifestylegoal> lGoal=Lifestylegoal.getAll();
		return lGoal;
	}	
		
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	private List<Lifestylegoal> getLifestylegoalBypersonId()
	{
			List<Lifestylegoal> lifestylegoal = Lifestylegoal.getAll();
			List<Lifestylegoal> lifestylegoalnew = new  ArrayList<Lifestylegoal>();
			for (int i = 0; i < lifestylegoal.size(); i++) {
				if((int)lifestylegoal.get(i).getPerson().getPersonId() == (int)Person.getPersonById(id).getPersonId())
				{	
					lifestylegoalnew.add(lifestylegoal.get(i));
				
				}
			}
			return lifestylegoalnew;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	private Lifestylegoal getLifestylegoalbypersonmeasure()
	{
			List<Lifestylegoal> lifestylegoal = getLifestylegoalBypersonmeasureId(id);
			Lifestylegoal lifestylesingle = new Lifestylegoal();
			for (int i = 0; i < lifestylegoal.size(); i++) {
				if(lifestylegoal.get(i).getLifestylemeasuremnt().getLifestyleDefinition() == measureType)
				{	
					lifestylesingle = lifestylegoal.get(i);
				
				}
			}
			return lifestylesingle;
	}
	

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response putLifestyleGoal(Lifestylegoal lifestylegoal) {
		System.out.println("--> Updating LifesttyleGoal... " +this.id);
		System.out.println("--> "+lifestylegoal.toString());
		Lifestylegoal.updateLifestylegoal(lifestylegoal);
		
		Response res;
		
		Lifestylegoal existing = getLifestylegoalById(this.id);
		
		if (existing == null) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
			lifestylegoal.setGoalId(this.id);
			Lifestylegoal.updateLifestylegoal(lifestylegoal);
		}

		return res;

		
	}

	private Lifestylegoal getLifestylegoalById(int lifestyleId) {
		System.out.println("Reading lifestylegoal from DB id: "+lifestyleId);
		Lifestylegoal lifestylegoal = Lifestylegoal.getLifestylegoalbyId(id);
		System.out.println("Lifestylegoal: "+ lifestylegoal.toString());
		return lifestylegoal;
		
	}

	@DELETE
	public void deletePerson() {
		Lifestylegoal l = getLifestylegoalById(id);
		if (l == null)
			throw new RuntimeException("Delete: Lifestylegoal with " + id
					+ " not found");

		Lifestylegoal.removedLifestylegoal(l);
	}
	private List<Lifestylegoal> getLifestylegoalBypersonmeasureId(int personId)
	{
			List<Lifestylegoal> lifestylegoal = Lifestylegoal.getAll();
			List<Lifestylegoal> lifestylegoalnew = new  ArrayList<Lifestylegoal>();
			for (int i = 0; i < lifestylegoal.size(); i++) {
				if((int)lifestylegoal.get(i).getPerson().getPersonId() == (int)Person.getPersonById(personId).getPersonId())
				{	
					lifestylegoalnew.add(lifestylegoal.get(i));
				
				}
			}
			return lifestylegoalnew;
	}

	
	}
