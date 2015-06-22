package unitn.lifecoach.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the lifestylemeasurehistory database table.
 * 
 */
@Entity
@NamedQuery(name="Lifestylemeasurehistory.findAll", query="SELECT l FROM Lifestylemeasurehistory l")
public class Lifestylemeasurehistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int lifestylemeasureId;

	@Temporal(TemporalType.DATE)
	private Date date;

	private byte isCurrent;

	private float lifestylevalue;

	//bi-directional many-to-one association to Lifestylemeasuremnt
	@ManyToOne
	@JoinColumn(name="lifestyleDefid")
	private Lifestylemeasuremnt lifestylemeasuremnt;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="personId")
	private Person person;

	public Lifestylemeasurehistory() {
	}

	public int getLifestylemeasureId() {
		return this.lifestylemeasureId;
	}

	public void setLifestylemeasureId(int lifestylemeasureId) {
		this.lifestylemeasureId = lifestylemeasureId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public byte getIsCurrent() {
		return this.isCurrent;
	}

	public void setIsCurrent(byte isCurrent) {
		this.isCurrent = isCurrent;
	}

	public float getLifestylevalue() {
		return this.lifestylevalue;
	}

	public void setLifestylevalue(float lifestylevalue) {
		this.lifestylevalue = lifestylevalue;
	}

	public Lifestylemeasuremnt getLifestylemeasuremnt() {
		return this.lifestylemeasuremnt;
	}

	public void setLifestylemeasuremnt(Lifestylemeasuremnt lifestylemeasuremnt) {
		this.lifestylemeasuremnt = lifestylemeasuremnt;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}