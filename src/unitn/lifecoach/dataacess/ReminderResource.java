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
public class ReminderResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public ReminderResource(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public ReminderResource(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	@SuppressWarnings("static-access")
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Reminder> getallReminder()
	{
		List<Reminder> reminder = Reminder.getAll();
		return reminder;
		
	}
	
	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Reminder getReminder() {
		Reminder reminder = this.getReminderById(id);
		if (reminder == null)
			throw new RuntimeException("Get: Reminder with " + id + " not found");
		return reminder;
	}

	

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Reminder getReminderHTML() {
		Reminder reminder = this.getReminderById(id);
		if (reminder == null)
			throw new RuntimeException("Get: Reminder with " + id + " not found");
		System.out.println("Returning reminder... " + reminder.getReminderId());
		return reminder;
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response putReminder(Reminder reminder) {
		System.out.println("--> Updating Reminder... " +this.id);
		System.out.println("--> "+reminder.toString());
		Reminder.updateReminder(reminder);
		
		Response res;
		
		Reminder existing = getReminderById(this.id);
		
		if (existing == null) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
			reminder.setReminderId(this.id);
			Reminder.updateReminder(reminder);
		}

		return res;

		
	}

	@DELETE
	public void deleteReminder() {
		Reminder r = getReminderById(id);
		if (r == null)
			throw new RuntimeException("Delete: Reminder with " + id
					+ " not found");

		Reminder.removedReminder(r);
	}

	
	private Reminder getReminderById(int reminderId) {
		// TODO Auto-generated method stub
		System.out.println("Reading remidner from DB with id: "+reminderId);
		Reminder reminder = Reminder.getReminderById(reminderId);
		System.out.println("Reminder: "+reminder.toString());
		return reminder;
		
	}
}
