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
public class HealthGoalResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;
	private String measureType;

	public HealthGoalResource(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public HealthGoalResource(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

		
	public HealthGoalResource(UriInfo uriInfo, Request request, int id,
			EntityManager em, String measureType) {
		
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
		this.measureType = measureType;
		// TODO Auto-generated constructor stub
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	private List<Healthgoal> getHealthgoalBypersonId()
	{
			List<Healthgoal> healthgoal = Healthgoal.getAll();
			List<Healthgoal> healthgoalnew = new  ArrayList<Healthgoal>();
			for (int i = 0; i < healthgoal.size(); i++) {
				if((int)healthgoal.get(i).getPerson().getPersonId() == (int)Person.getPersonById(id).getPersonId())
				{	
					healthgoalnew.add(healthgoal.get(i));
				
				}
			}
			return healthgoalnew;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	private Healthgoal getHealthgoalBypersonmeasureId()
	{
			List<Healthgoal> healthgoal = getHealthgoalBypersonmeasure(id, measureType);
			Healthgoal healthgoalsingle = new Healthgoal();
			for (int i = 0; i < healthgoal.size(); i++) {
				if(healthgoal.get(i).getHealthmeasurement().getHealthmeasureDefinition() == measureType)
				{	
					healthgoalsingle = healthgoal.get(i);
				
				}
			}
			return healthgoalsingle;
	}
	

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response putHealthGoal(Healthgoal healthgoal) {
		System.out.println("--> Updating HealthGoal... " +this.id);
		System.out.println("--> "+healthgoal.toString());
		Healthgoal.updateHealthgoal(healthgoal);
		
		Response res;
		
		Healthgoal existing = getHealthgoalById(this.id);
		
		if (existing == null) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
			healthgoal.setGoalId(this.id);
			Healthgoal.updateHealthgoal(healthgoal);
		}

		return res;

		
	}

	private Healthgoal getHealthgoalById(int healthgoalId) {
		System.out.println("Reading healthgoal from DB id: "+healthgoalId);
		Healthgoal healthgoal = Healthgoal.getHealthgoalbyId(id);
		System.out.println("Healthgoal: "+ healthgoal.toString());
		return healthgoal;
		
	}

	@DELETE
	public void deletePerson() {
		Healthgoal h = getHealthgoalById(id);
		if (h == null)
			throw new RuntimeException("Delete: Healthgoal with " + id
					+ " not found");

		Healthgoal.removedHealthgoal(h);
	}
	private List<Healthgoal> getHealthgoalBypersonmeasure(int personId, String measureType)
	{
			List<Healthgoal> healthgoal = Healthgoal.getAll();
			List<Healthgoal> healthgoalnew = new  ArrayList<Healthgoal>();
			for (int i = 0; i < healthgoal.size(); i++) {
				if((int)healthgoal.get(i).getPerson().getPersonId() == (int)Person.getPersonById(personId).getPersonId())
				{	
					healthgoalnew.add(healthgoal.get(i));
				
				}
			}
			return healthgoalnew;
	}
	
	}
