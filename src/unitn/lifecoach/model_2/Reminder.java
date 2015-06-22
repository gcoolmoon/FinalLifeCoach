package unitn.lifecoach.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reminder database table.
 * 
 */
@Entity
@NamedQuery(name="Reminder.findAll", query="SELECT r FROM Reminder r")
public class Reminder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int reminderId;

	private String appointmentDescription;

	private String appointmentType;

	@Temporal(TemporalType.DATE)
	private Date date;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="personId")
	private Person person;

	public Reminder() {
	}

	public int getReminderId() {
		return this.reminderId;
	}

	public void setReminderId(int reminderId) {
		this.reminderId = reminderId;
	}

	public String getAppointmentDescription() {
		return this.appointmentDescription;
	}

	public void setAppointmentDescription(String appointmentDescription) {
		this.appointmentDescription = appointmentDescription;
	}

	public String getAppointmentType() {
		return this.appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}