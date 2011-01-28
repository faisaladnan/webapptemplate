package com.webapp.web.jpa.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.web.jpa.bean.Contact;

/**
 * Contact DAO implementation
 * 
 * @author Faisal Adnan
 */
@Repository
@Transactional(readOnly = true)
public class ContactDaoImpl implements ContactDao {
	private EntityManager em = null;
	
	/**
	 * Sets the Entity Manager
	 */
	@PersistenceContext
	public void setEntityManger(EntityManager em)
	{
		this.em = em;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.webapp.web.jpa.dao.ContactDao#findContactById(java.lang.Integer)
	 */
	public Contact findContactById(Integer id) {
		return em.find(Contact.class, id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.webapp.web.jpa.dao.ContactDao#findContacts()
	 */
	@SuppressWarnings("unchecked")
	public Collection<Contact> findContacts() {
		return em.createQuery("select c from Contact c order by c.lastName, c.firstName").getResultList();
	}

	/*
	 * (non-Javadoc)
	 * @see com.webapp.web.jpa.dao.ContactDao#findContacts(int, int)
	 */
	@SuppressWarnings("unchecked")
	public Collection<Contact> findContacts(final int startIndex, final int maxResult) {
		return em.createQuery("select c from Contact c order by c.lastName, c.firstName").setFirstResult(startIndex).setMaxResults(maxResult).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * @see com.webapp.web.jpa.dao.ContactDao#findContactsByLastName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Collection<Contact> findContactsByLastName(String lastName) {
		return em.createQuery("select c from Contact c where c.lastName = :lastName order by c.lastName, c.firstName")
			.setParameter("", lastName).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * @see com.webapp.web.jpa.dao.ContactDao#save(com.webapp.web.jpa.bean.Contact)
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Contact save(Contact contact) {
		return em.merge(contact);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.webapp.web.jpa.dao.ContactDao#delete(com.webapp.web.jpa.bean.Contact)
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void delete(Contact contact) {
		em.remove(em.merge(contact));
		
	}

}
