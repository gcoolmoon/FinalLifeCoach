package unitn.lifecoach.model;

import java.io.Serializable;

import javax.persistence.*;

import unitn.lifecoach.model.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import unitn.lifecoach.dao.LifeCoachDao;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int personId;

	@Temporal(TemporalType.DATE)
	private Date createdDate;

	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private String userName;

	//bi-directional many-to-one association to Healthgoal
	@OneToMany(mappedBy="person")
	@JsonManagedReference
	private List<Healthgoal> healthgoals;

	//bi-directional many-to-one association to Lifestylegoal
	@OneToMany(mappedBy="person")
	@JsonManagedReference
	private List<Lifestylegoal> lifestylegoals;

	//bi-directional many-to-one association to Healthmeasurehistory
	@OneToMany(mappedBy="person")
	@JsonManagedReference
	private List<Healthmeasurehistory> healthmeasurehistories;

	//bi-directional many-to-one association to Lifestylemeasurehistory
	@OneToMany(mappedBy="person")
	@JsonManagedReference
	private List<Lifestylemeasurehistory> lifestylemeasurehistories;

	//bi-directional many-to-one association to Caregiiver
	@ManyToOne
	@JoinColumn(name="careGiverId")
	@JsonBackReference
	private Caregiiver caregiiver;

	//bi-directional many-to-one association to Reminder
	@OneToMany(mappedBy="person")
	@JsonManagedReference
	private List<Reminder> reminders;

	public Person() {
	}

	public int getPersonId() {
		return this.personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Healthmeasurehistory> getHealthmeasurehistories() {
		return this.healthmeasurehistories;
	}

	public void setHealthmeasurehistories(List<Healthmeasurehistory> healthmeasurehistories) {
		this.healthmeasurehistories = healthmeasurehistories;
	}

	public Healthmeasurehistory addHealthmeasurehistory(Healthmeasurehistory healthmeasurehistory) {
		getHealthmeasurehistories().add(healthmeasurehistory);
		healthmeasurehistory.setPerson(this);

		return healthmeasurehistory;
	}

	public Healthmeasurehistory removeHealthmeasurehistory(Healthmeasurehistory healthmeasurehistory) {
		getHealthmeasurehistories().remove(healthmeasurehistory);
		healthmeasurehistory.setPerson(null);

		return healthmeasurehistory;
	}

	public List<Lifestylemeasurehistory> getLifestylemeasurehistories() {
		return this.lifestylemeasurehistories;
	}

	public void setLifestylemeasurehistories(List<Lifestylemeasurehistory> lifestylemeasurehistories) {
		this.lifestylemeasurehistories = lifestylemeasurehistories;
	}

	public Lifestylemeasurehistory addLifestylemeasurehistory(Lifestylemeasurehistory lifestylemeasurehistory) {
		getLifestylemeasurehistories().add(lifestylemeasurehistory);
		lifestylemeasurehistory.setPerson(this);

		return lifestylemeasurehistory;
	}

	public Lifestylemeasurehistory removeLifestylemeasurehistory(Lifestylemeasurehistory lifestylemeasurehistory) {
		getLifestylemeasurehistories().remove(lifestylemeasurehistory);
		lifestylemeasurehistory.setPerson(null);

		return lifestylemeasurehistory;
	}

	public Caregiiver getCaregiiver() {
		return this.caregiiver;
	}

	public void setCaregiiver(Caregiiver caregiiver) {
		this.caregiiver = caregiiver;
	}

	public List<Reminder> getReminders() {
		return this.reminders;
	}

	public void setReminders(List<Reminder> reminders) {
		this.reminders = reminders;
	}

	public Reminder addReminder(Reminder reminder) {
		getReminders().add(reminder);
		reminder.setPerson(this);

		return reminder;
	}

	public Reminder removeReminder(Reminder reminder) {
		getReminders().remove(reminder);
		reminder.setPerson(null);

		return reminder;
	}
	public List<Healthgoal> getHealthgoals() {
		return this.healthgoals;
	}

	public void setHealthgoals(List<Healthgoal> healthgoals) {
		this.healthgoals = healthgoals;
	}

	public Healthgoal addHealthgoal(Healthgoal healthgoal) {
		getHealthgoals().add(healthgoal);
		healthgoal.setPerson(this);

		return healthgoal;
	}

	public Healthgoal removeHealthgoal(Healthgoal healthgoal) {
		getHealthgoals().remove(healthgoal);
		healthgoal.setPerson(null);

		return healthgoal;
	}

	public List<Lifestylegoal> getLifestylegoals() {
		return this.lifestylegoals;
	}

	public void setLifestylegoals(List<Lifestylegoal> lifestylegoals) {
		this.lifestylegoals = lifestylegoals;
	}

	public Lifestylegoal addLifestylegoal(Lifestylegoal lifestylegoal) {
		getLifestylegoals().add(lifestylegoal);
		lifestylegoal.setPerson(this);

		return lifestylegoal;
	}

	public Lifestylegoal removeLifestylegoal(Lifestylegoal lifestylegoal) {
		getLifestylegoals().remove(lifestylegoal);
		lifestylegoal.setPerson(null);

		return lifestylegoal;
	}
	public static Person getPersonById(int personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Person p = em.find(Person.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}

	public static List<Person> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Person> list = em.createNamedQuery("Person.findAll", Person.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Person savePerson(Person p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static Person updatePerson(Person p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removePerson(Person p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
}