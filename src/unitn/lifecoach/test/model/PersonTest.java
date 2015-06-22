package unitn.lifecoach.test.model;

import static org.junit.Assert.*;
import unitn.lifecoach.model.*;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;   
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonTest {

    @Test
    public void readPersonListTest() {
        System.out.println("--> TEST: readPersonList");
        List<Person> list = em.createNamedQuery("Person.findAll", Person.class)
                .getResultList();
        for (Person person : list) {
            System.out.println("--> Person = " + person.getFirstName());
        }
        assertTrue(list.size()>0);
    }

    @Test
    public void addPersonWithDaoTest() {
        System.out.println("--> TEST: addPersonWithDao");

        List<Person> list = Person.getAll();
        int personOriginalCount = list.size();

        Person p = new Person();
        p.setFirstName("Tg");
        p.setLastName("Pallino");
        Calendar c = Calendar.getInstance();
        c.set(1984, 6, 21);
        p.setCreatedDate(c.getTime());

        System.out.println("--> TEST: addPersonWithDao ==> persisting person");
        Person.savePerson(p);
        assertNotNull("Id should not be null", p.getPerosnId());

        System.out.println("--> TEST: addPersonWithDao ==> getting the list");
        list = Person.getAll();
        assertEquals("Table has two entities", personOriginalCount+1, list.size());

        Person newPerson = Person.getPersonById(p.getPerosnId());

        System.out.println("--> TEST: addPersonWithDao ==> removing new person");
        Person.removePerson(newPerson);
        list = Person.getAll();
        assertEquals("Table has two entities", personOriginalCount, list.size());
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Testing JPA on lifecoach database using 'introsde-jpa' persistence unit");
        emf = Persistence.createEntityManagerFactory("LifeCoachApp");
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void afterClass() {
        em.close();
        emf.close();
    }

    @Before
    public void before() {
        tx = em.getTransaction();
    }

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private EntityTransaction tx;

}