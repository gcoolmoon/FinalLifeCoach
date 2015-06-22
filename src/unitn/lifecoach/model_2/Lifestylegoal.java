package unitn.lifecoach.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the lifestylegoal database table.
 * 
 */
@Entity
@NamedQuery(name="Lifestylegoal.findAll", query="SELECT l FROM Lifestylegoal l")
public class Lifestylegoal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int goalId;

	@Temporal(TemporalType.DATE)
	private Date duration;

	private float goalValue;

	private int lifestylemeasureDefid;

	private String measuredBy;

	public Lifestylegoal() {
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

	public int getLifestylemeasureDefid() {
		return this.lifestylemeasureDefid;
	}

	public void setLifestylemeasureDefid(int lifestylemeasureDefid) {
		this.lifestylemeasureDefid = lifestylemeasureDefid;
	}

	public String getMeasuredBy() {
		return this.measuredBy;
	}

	public void setMeasuredBy(String measuredBy) {
		this.measuredBy = measuredBy;
	}

}