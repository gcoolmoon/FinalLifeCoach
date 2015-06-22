package unitn.lifecoach.model;

import java.io.Serializable;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonBackReference;

import unitn.lifecoach.dao.LifeCoachDao;
import unitn.lifecoach.model.*;

import java.util.Date;
import java.util.List;


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

	private String measuredBy;

	//bi-directional many-to-one association to Lifestylemeasuremnt
	@ManyToOne
	@JoinColumn(name="lifestylemeasureDefid")
	@JsonBackReference
	private Lifestylemeasuremnt lifestylemeasuremnt;
	
	@ManyToOne
	@JoinColumn(name="personId")
	@JsonBackReference
	private Person person;
	


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

	public String getMeasuredBy() {
		return this.measuredBy;
	}

	public void setMeasuredBy(String measuredBy) {
		this.measuredBy = measuredBy;
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
	public static List<Lifestylegoal> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Lifestylegoal> list = em.createNamedQuery("Lifestylegoal.findAll", Lifestylegoal.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Lifestylegoal saveLifestylegoal(Lifestylegoal lf) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(lf);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return lf;
	}
	
	public static Lifestylegoal updateLifestylegoal(Lifestylegoal lf) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		lf=em.merge(lf);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return lf;
	}
	
	public static void removedLifestylegoal(Lifestylegoal lf) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    lf=em.merge(lf);
	    em.remove(lf);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}

	public static Lifestylegoal getLifestylegoalbyId(int lifestyleId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Lifestylegoal c = em.find(Lifestylegoal.class, lifestyleId);
		LifeCoachDao.instance.closeConnections(em);
		return c;
	}
}