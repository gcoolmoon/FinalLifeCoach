package unitn.lifecoach.dataacess;

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
public class CareGiverResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public CareGiverResource(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public CareGiverResource(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	@SuppressWarnings("static-access")
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Caregiiver> getAvailableCaregivers()
	{
		List<Caregiiver> caregiver = Caregiiver.getAll();
		return caregiver;
	}
		
	// Get caregiver by  Id 
		@GET
		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
		public Caregiiver getCareGiverbyId() {
			Caregiiver caregiver = this.getCaregiverById(id);
			if (caregiver == null)
				throw new RuntimeException("Get: caregiver with Person" + id + " not found");
			return caregiver;
		}

		//Get caregiver by  Id for the browser
		@GET
		@Produces(MediaType.TEXT_XML)
		public Caregiiver getCaregiverHTMLbyId() {
			Caregiiver caregiver = this.getCaregiverById(id);
			if (caregiver == null)
				throw new RuntimeException("Get: caregiver with Person" + id + " not found");
			System.out.println("Returning caregiver... " + caregiver.getCareGiverId());
			return caregiver;
		}
	

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response putCaregiver(Caregiiver caregiver) {
		System.out.println("--> Updating Caregiver... " +this.id);
		System.out.println("--> "+caregiver.toString());
		Caregiiver.updateCaregiiver(caregiver);
		
		Response res;
		
		Caregiiver existing = getCaregiverById(this.id);
		
		if (existing == null) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
			caregiver.setCareGiverId(this.id);
			Caregiiver.updateCaregiiver(caregiver);
		}

		return res;

		
	}

	private Caregiiver getCaregiverById(int caregiverId) {
		System.out.println("Reading caregiver from DB id: "+caregiverId);
		Caregiiver caregiver = Caregiiver.getCaregiverbyId(id);
		System.out.println("Caregiver: "+ caregiver.toString());
		return caregiver;
		
	}

	@DELETE
	public void deletePerson() {
		Caregiiver c = getCaregiverById(id);
		if (c == null)
			throw new RuntimeException("Delete: Caregiver with " + id
					+ " not found");

		Caregiiver.removeCaregiiver(c);
	}

	
	}
