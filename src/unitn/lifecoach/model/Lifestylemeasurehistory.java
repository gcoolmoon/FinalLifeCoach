package unitn.lifecoach.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import unitn.lifecoach.dao.LifeCoachDao;
import unitn.lifecoach.model.Lifestylemeasurehistory;

import java.util.Date;
import java.util.List;


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
	@JsonBackReference
	private Lifestylemeasuremnt lifestylemeasuremnt;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="personId")
	@JsonBackReference
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
	public static List<Lifestylemeasurehistory> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Lifestylemeasurehistory> list = em.createNamedQuery("Lifestylemeasurehistory.findAll", Lifestylemeasurehistory.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Lifestylemeasurehistory saveLifestylemeasurehistory(Lifestylemeasurehistory lmh) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(lmh);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return lmh;
	}
	
	public static Lifestylemeasurehistory updateLifestylemeasurehistory(Lifestylemeasurehistory lmh) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		lmh=em.merge(lmh);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return lmh;
	}
	
	public static void removedLifestylemeasurehistory(Lifestylemeasurehistory lmh) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    lmh=em.merge(lmh);
	    em.remove(lmh);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}

	public static Lifestylemeasurehistory getLifestylemeasurehistorybyId(
			int lifestylemeasurehistoryId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Lifestylemeasurehistory lmh = em.find(Lifestylemeasurehistory.class, lifestylemeasurehistoryId);
		LifeCoachDao.instance.closeConnections(em);
		return lmh;
	}
}