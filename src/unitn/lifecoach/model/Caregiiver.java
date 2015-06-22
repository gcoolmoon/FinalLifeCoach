package unitn.lifecoach.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import unitn.lifecoach.dao.LifeCoachDao;
import unitn.lifecoach.model.Caregiiver;
import unitn.lifecoach.model.Person;

import java.util.List;


/**
 * The persistent class for the caregiiver database table.
 * 
 */
@Entity
@NamedQuery(name="Caregiiver.findAll", query="SELECT c FROM Caregiiver c")
public class Caregiiver implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int careGiverId;

	private String email;

	private String firstName;

	private String lastName;

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="caregiiver")
	@JsonManagedReference
	private List<Person> persons;

	public Caregiiver() {
	}

	public int getCareGiverId() {
		return this.careGiverId;
	}

	public void setCareGiverId(int careGiverId) {
		this.careGiverId = careGiverId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Person addPerson(Person person) {
		getPersons().add(person);
		person.setCaregiiver(this);

		return person;
	}

	public Person removePerson(Person person) {
		getPersons().remove(person);
		person.setCaregiiver(null);

		return person;
	}
	public static List<Caregiiver> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Caregiiver> list = em.createNamedQuery("Caregiiver.findAll", Caregiiver.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Caregiiver saveCaregiiver(Caregiiver c) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(c);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return c;
	}
	
	public static Caregiiver updateCaregiiver(Caregiiver c) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		c=em.merge(c);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return c;
	}
	
	public static void removeCaregiiver(Caregiiver c) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    c=em.merge(c);
	    em.remove(c);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	public static Caregiiver getCaregiverByPerson(int personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Person p = em.find(Person.class, personId);
		Caregiiver c = p.getCaregiiver();
		LifeCoachDao.instance.closeConnections(em);
		return c;
	}

	public static Caregiiver getCaregiverbyId(int caregiverId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Caregiiver c = em.find(Caregiiver.class, caregiverId);
		LifeCoachDao.instance.closeConnections(em);
		return c;
		
	}
}