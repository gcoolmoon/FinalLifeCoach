package unitn.lifecoach.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import unitn.lifecoach.dao.LifeCoachDao;
import unitn.lifecoach.model.Dailysuggestion;


/**
 * The persistent class for the dailysuggestions database table.
 * 
 */
@Entity
@Table(name="dailysuggestions")
@NamedQuery(name="Dailysuggestion.findAll", query="SELECT d FROM Dailysuggestion d")
public class Dailysuggestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int dailysuggId;

	private int date;

	private String improvemetSuggestions;

	private String motivationalSuggestion;

	//bi-directional many-to-one association to Lifestylemeasuremnt
	@ManyToOne
	@JoinColumn(name="lifestyleDefId")
	@JsonBackReference
	private Lifestylemeasuremnt lifestylemeasuremnt;

	public Dailysuggestion() {
	}

	public int getDailysuggId() {
		return this.dailysuggId;
	}

	public void setDailysuggId(int dailysuggId) {
		this.dailysuggId = dailysuggId;
	}

	public int getDate() {
		return this.date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getImprovemetSuggestions() {
		return this.improvemetSuggestions;
	}

	public void setImprovemetSuggestions(String improvemetSuggestions) {
		this.improvemetSuggestions = improvemetSuggestions;
	}

	public String getMotivationalSuggestion() {
		return this.motivationalSuggestion;
	}

	public void setMotivationalSuggestion(String motivationalSuggestion) {
		this.motivationalSuggestion = motivationalSuggestion;
	}

	public Lifestylemeasuremnt getLifestylemeasuremnt() {
		return this.lifestylemeasuremnt;
	}

	public void setLifestylemeasuremnt(Lifestylemeasuremnt lifestylemeasuremnt) {
		this.lifestylemeasuremnt = lifestylemeasuremnt;
	}
	public static Dailysuggestion saveAwarenessinformation(Dailysuggestion d) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(d);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return d;
	}
	
	public static Dailysuggestion updateAwarenessinformation(Dailysuggestion d) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		d=em.merge(d);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return d;
	}
	
	public static void removedDailysuggestion(Dailysuggestion d) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    d=em.merge(d);
	    em.remove(d);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
}