package unitn.lifecoach.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import unitn.lifecoach.dao.LifeCoachDao;
import unitn.lifecoach.model.Healthgoal;

import java.util.Date;
import java.util.List;


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
	@JsonBackReference
	private Healthmeasurement healthmeasurement;
	
	@ManyToOne
	@JoinColumn(name="personId")
	@JsonBackReference
	private Person person;

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
	
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	public static List<Healthgoal> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Healthgoal> list = em.createNamedQuery("Healthgoal.findAll", Healthgoal.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Healthgoal saveHealthgoal(Healthgoal hg) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(hg);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return hg;
	}
	
	public static Healthgoal updateHealthgoal(Healthgoal hg) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		hg=em.merge(hg);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return hg;
	}
	
	public static void removedHealthgoal(Healthgoal hg) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    hg=em.merge(hg);
	    em.remove(hg);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}

	public static Healthgoal getHealthgoalbyId(int healthgoalId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Healthgoal c = em.find(Healthgoal.class, healthgoalId);
		LifeCoachDao.instance.closeConnections(em);
		return c;
		
	}
}