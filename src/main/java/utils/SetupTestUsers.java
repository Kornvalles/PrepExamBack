package utils;


import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Role;
import entities.User;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.DROP_AND_CREATE);
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("user", "test1");
    User admin = new User("admin", "test2");
    User both = new User("user_admin", "test3");

    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    
    List<Person> persons = new ArrayList();
    
    Person person1 = new Person();
    person1.setFirstName("Mikkel");
    person1.setLastName("Kornval");
    person1.setEmail("mikkel@mail.dk");
    person1.setPhone("31675555");
    
    Person person2 = new Person();
    person2.setFirstName("Nicoline");
    person2.setLastName("Laage");
    person2.setEmail("nicoline@mail.dk");
    person2.setPhone("52409489");
    
    //persons.add(person1);
    //persons.add(person2);
    
    Address address = new Address();
    address.setCity("Lyngby");
    address.setStreet("Lyngby Hovedgade");
    address.setZipcode("2800");
    
    Hobby hobby1 = new Hobby();
    hobby1.setDescription("Watching TV");
    hobby1.setName("TV");
    
    Hobby hobby2 = new Hobby();
    hobby2.setName("Chilling");
    hobby2.setDescription("Just chilling doing nothing");
    
    person1.setAddress(address);
    person2.setAddress(address);
    
    List<Hobby> hobbies = new ArrayList();
    hobbies.add(hobby1);
    hobbies.add(hobby2);
    
    person1.setHobbies(hobbies);
    //address.setPersons(persons);
    
    em.persist(hobby1);
    em.persist(hobby2);
    em.persist(person1);
    em.persist(person2);
    em.persist(address);
    
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test1"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test"));
    System.out.println("Created TEST Users");
   
  }

}
