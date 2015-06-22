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
public class HealthMeasureHistoryResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public HealthMeasureHistoryResource(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public HealthMeasureHistoryResource(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

		
		
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	private List<Healthmeasurehistory> getHealthmeasureBypersoncurrentId()
	{
			Person p = Person.getPersonById(id);
			List<Healthmeasurehistory> healthMeasure = Healthmeasurehistory.getAll();
			List<Healthmeasurehistory> healthcurrentMeasure = new  ArrayList<Healthmeasurehistory>();
			for (int i = 0; i < healthMeasure.size(); i++) {
				if((int)healthMeasure.get(i).getPerson().getPersonId() == (int)Person.getPersonById(id).getPersonId() &&
						healthMeasure.get(i).getIsCurrent() == 1 )
				{	
					healthcurrentMeasure.add(healthMeasure.get(i));
				
				}
			}
			return healthcurrentMeasure;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	private List<Healthmeasurehistory> getallHealthmeasureBypersonId()
	{
			Person p = Person.getPersonById(id);
			List<Healthmeasurehistory> healthMeasure = Healthmeasurehistory.getAll();
			List<Healthmeasurehistory> healthcurrentMeasure = new  ArrayList<Healthmeasurehistory>();
			for (int i = 0; i < healthMeasure.size(); i++) {
				if((int)healthMeasure.get(i).getPerson().getPersonId() == (int)Person.getPersonById(id).getPersonId())
				{	
					healthcurrentMeasure.add(healthMeasure.get(i));
				
				}
			}
			return healthcurrentMeasure;
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response putHealthMeasureHistory(Healthmeasurehistory hmh) {
		System.out.println("--> Updating HealthGoal... " +this.id);
		System.out.println("--> "+hmh.toString());
		Healthmeasurehistory.updateHealthmeasurehistory(hmh);
		
		Response res;
		
		Healthmeasurehistory existing = getHealthmeasurehistoryById(this.id);
		
		if (existing == null) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
			hmh.setHealthmeasureHistoryid(this.id);
			Healthmeasurehistory.saveHealthmeasurehistory(hmh);
		}

		return res;

		
	}

	private Healthmeasurehistory getHealthmeasurehistoryById(int healthmeasurehistoryId) {
		System.out.println("Reading healthmeasurehistory from DB id: "+healthmeasurehistoryId);
		Healthmeasurehistory hmh = Healthmeasurehistory.getHealthmeasurehistorybyId(healthmeasurehistoryId);
		System.out.println("Healthmeasurehistory: "+ hmh.toString());
		return hmh;
		
	}

	@DELETE
	public void deleteHealthmeasurehistory() {
		Healthmeasurehistory h = getHealthmeasurehistoryById(id);
		if (h == null)
			throw new RuntimeException("Delete: Healthgoal with " + id
					+ " not found");

		Healthmeasurehistory.removedHealthmeasurehistory(h);;
	}

	
	}
