package facades;

import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class PersonFacade {

    private static PersonFacade FACADE;
    private static EntityManagerFactory EMF;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (FACADE == null) {
            EMF = _emf;
            FACADE = new PersonFacade();
        }
        return FACADE;
    }

    private EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }

    public List<Person> getAllPersons() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            System.out.println("Fetching list of persons from database...");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Person getPersonByID(int id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> query = em.createNamedQuery("Person.getById", Person.class);
            System.out.println("Fetching person by id from database...");
            return query.setParameter("id", id).getSingleResult();
        } finally {
            em.close();
        }
    }
    
}
