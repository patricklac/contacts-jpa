package ch.heig.pl.business.dao;

import ch.heig.pl.model.Contact;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ContactDAO {

    @PersistenceContext(unitName = "ContactsPersistenceUnit")
    private EntityManager em;

    @PostConstruct
    public void init() {
        add(new Contact("Pierre",1234));
        add(new Contact("Sylvie",1111));
    }

    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        try {
            contacts = em.createQuery("SELECT c FROM Contact c", Contact.class).getResultList();
        } catch (PersistenceException e) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return contacts;
    }

    public void add(Contact contact) {
        em.persist(contact);
    }
}
