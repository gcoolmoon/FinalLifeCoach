package unitn.lifecoach.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the healthmeasurehistory database table.
 * 
 */
@Entity
@NamedQuery(name="Healthmeasurehistory.findAll", query="SELECT h FROM Healthmeasurehistory h")
public class Healthmeasurehistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int healthmeasureHistoryid;

	@Temporal(TemporalType.DATE)
	private Date date;

	private int healthValue;

	private byte isCurrent;

	//bi-directional many-to-one association to Healthmeasurement
	@ManyToOne
	@JoinColumn(name="healthmeasureDefid")
	private Healthmeasurement healthmeasurement;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="personId")
	private Person person;

	public Healthmeasurehistory() {
	}

	public int getHealthmeasureHistoryid() {
		return this.healthmeasureHistoryid;
	}

	public void setHealthmeasureHistoryid(int healthmeasureHistoryid) {
		this.healthmeasureHistoryid = healthmeasureHistoryid;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getHealthValue() {
		return this.healthValue;
	}

	public void setHealthValue(int healthValue) {
		this.healthValue = healthValue;
	}

	public byte getIsCurrent() {
		return this.isCurrent;
	}

	public void setIsCurrent(byte isCurrent) {
		this.isCurrent = isCurrent;
	}

	public Healthmeasurement getHealthmeasurement() {
		return this.healthmeasurement;
	}

	public void setHealthmeasurement(Healthmeasurement healthmeasurement) {
		this.healthmeasurement = healthmeasurement;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}