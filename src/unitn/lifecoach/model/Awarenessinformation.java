package unitn.lifecoach.model;

import java.io.Serializable;

import javax.persistence.*;

import unitn.lifecoach.dao.LifeCoachDao;
import unitn.lifecoach.model.Awarenessinformation;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the awarenessinformation database table.
 * 
 */
@Entity
@NamedQuery(name="Awarenessinformation.findAll", query="SELECT a FROM Awarenessinformation a")
public class Awarenessinformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int awrenessinfoId;

	private String awarenessInformation;

	@Temporal(TemporalType.DATE)
	private Date date;

	public Awarenessinformation() {
	}

	public int getAwrenessinfoId() {
		return this.awrenessinfoId;
	}

	public void setAwrenessinfoId(int awrenessinfoId) {
		this.awrenessinfoId = awrenessinfoId;
	}

	public String getAwarenessInformation() {
		return this.awarenessInformation;
	}

	public void setAwarenessInformation(String awarenessInformation) {
		this.awarenessInformation = awarenessInformation;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public static List<Awarenessinformation> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Awarenessinformation> list = em.createNamedQuery("Awarenessinformation.findAll", Awarenessinformation.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Awarenessinformation saveAwarenessinformation(Awarenessinformation a) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(a);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return a;
	}
	
	public static Awarenessinformation updateAwarenessinformation(Awarenessinformation a) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		a=em.merge(a);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return a;
	}
	
	public static void removeAwarenessinformation(Awarenessinformation a) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    a=em.merge(a);
	    em.remove(a);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
}