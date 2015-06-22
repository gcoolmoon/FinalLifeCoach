package unitn.lifecoach.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the healthgoal database table.
 * 
 */
@Entity
@NamedQuery(name="Healthgoal.findAll", query="SELECT h FROM Healthgoal h")
public class Healthgoal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int goalId;

	@Temporal(TemporalType.DATE)
	private Date duration;

	private float goalValue;

	private String measuredBy;

	//bi-directional many-to-one association to Healthmeasurement
	@ManyToOne
	@JoinColumn(name="healthmeasureDefid")
	private Healthmeasurement healthmeasurement;

	public Healthgoal() {
	}

	public int getGoalId() {
		return this.goalId;
	}

	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}

	public Date getDuration() {
		return this.duration;
	}

	public void setDuration(Date duration) {
		this.duration = duration;
	}

	public float getGoalValue() {
		return this.goalValue;
	}

	public void setGoalValue(float goalValue) {
		this.goalValue = goalValue;
	}

	public String getMeasuredBy() {
		return this.measuredBy;
	}

	public void setMeasuredBy(String measuredBy) {
		this.measuredBy = measuredBy;
	}

	public Healthmeasurement getHealthmeasurement() {
		return this.healthmeasurement;
	}

	public void setHealthmeasurement(Healthmeasurement healthmeasurement) {
		this.healthmeasurement = healthmeasurement;
	}

}