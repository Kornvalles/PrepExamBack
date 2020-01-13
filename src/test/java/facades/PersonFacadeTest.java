package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Address;
import entities.Person;
import utils.EMF_Creator;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory EMF;
    private static PersonFacade FACADE;
    private static Set<Person> persons;
    private static Person p1;
    private static Person p2;
    private static Address a1;
    
    public PersonFacadeTest() {
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        EMF = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
        FACADE = PersonFacade.getPersonFacade(EMF);
        
        a1 = new Address();
        a1.setCity("Lyngby");
        a1.setStreet("Hovedgade");
        a1.setZipcode("2800");
       
        persons = new HashSet(); 
        p1 = new Person();
        p1.setFirstName("Mikkel");
        p1.setLastName("Kornval");
        p1.setPhone("12345678");
        p1.setEmail("mik@mail.dk");
        
        p2 = new Person();
        p2.setFirstName("Nicoline");
        p2.setLastName("Laage");
        p2.setPhone("87654321");
        p2.setEmail("nic@mail.dk");
        
        a1.setPersons(persons);
        p1.setAddress(a1);
        p2.setAddress(a1);
        persons.add(p1);
        persons.add(p2);
    }

    @AfterAll
    public static void tearDownClass() {
        // Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        // Remove any data after each test was run
    }

    @Test
    public void testGetAllPersons() {
        //Arrange
        int exp = persons.size();
        //Act
        int act = FACADE.getAllPersons().size();
        assertEquals(exp, act);
    }
    
    @Test
    public void testGetPersonByID() {
        //Arrange
        Person exp = p1;
        //Act
        Person act = FACADE.getPersonByID(1);
        assertEquals(exp.getEmail(), act.getEmail());
    }

}
