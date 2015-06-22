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
public class LifestyleMeasureHistoryResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public LifestyleMeasureHistoryResource(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public LifestyleMeasureHistoryResource(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

		
		
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	private List<Lifestylemeasurehistory> getcurrentlifestylemeasureBypersonId()
	{
			
			List<Lifestylemeasurehistory> lifestyleMeasure = Lifestylemeasurehistory.getAll();
			List<Lifestylemeasurehistory> lifestylecurrentMeasure = new  ArrayList<Lifestylemeasurehistory>();
			for (int i = 0; i < lifestyleMeasure.size(); i++) {
				if((int)lifestyleMeasure.get(i).getPerson().getPersonId() == (int)Person.getPersonById(id).getPersonId() &&
						lifestyleMeasure.get(i).getIsCurrent() == 1 )
				{	
					lifestylecurrentMeasure.add(lifestyleMeasure.get(i));
				
				}
			}
			return lifestylecurrentMeasure;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	private List<Lifestylemeasurehistory> getlifestylemeasureBypersonId()
	{
			
			List<Lifestylemeasurehistory> lifestyleMeasure = Lifestylemeasurehistory.getAll();
			List<Lifestylemeasurehistory> lifestylecurrentMeasure = new  ArrayList<Lifestylemeasurehistory>();
			for (int i = 0; i < lifestyleMeasure.size(); i++) {
				if((int)lifestyleMeasure.get(i).getPerson().getPersonId() == (int)Person.getPersonById(id).getPersonId())
				{	
					lifestylecurrentMeasure.add(lifestyleMeasure.get(i));
				
				}
			}
			return lifestylecurrentMeasure;
	}
	

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response putlifestyleMeasureHistory(Lifestylemeasurehistory lmh) {
		System.out.println("--> Updating lifestyleGoal... " +this.id);
		System.out.println("--> "+lmh.toString());
		Lifestylemeasurehistory.updateLifestylemeasurehistory(lmh);
		
		Response res;
		
		Lifestylemeasurehistory existing = getlifestylemeasurehistoryById(this.id);
		
		if (existing == null) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
			lmh.setLifestylemeasureId(this.id);
			Lifestylemeasurehistory.updateLifestylemeasurehistory(lmh);
		}

		return res;

		
	}

	private Lifestylemeasurehistory getlifestylemeasurehistoryById(int lifestylemeasurehistoryId) {
			System.out.println("Reading lifestylemeasurehistory from DB id: "+lifestylemeasurehistoryId);
			Lifestylemeasurehistory lmh = Lifestylemeasurehistory.getLifestylemeasurehistorybyId(lifestylemeasurehistoryId);
			System.out.println("lifestylemeasurehistory: "+ lmh.toString());
			return lmh;
			
		}
		


	@DELETE
	public void deletelifestylemeasurehistory() {
		Lifestylemeasurehistory lmh = getlifestylemeasurehistoryById(id);
		if (lmh == null)
			throw new RuntimeException("Delete: lifestylegoal with " + id
					+ " not found");

		Lifestylemeasurehistory.removedLifestylemeasurehistory(lmh);
	}

	
	}
