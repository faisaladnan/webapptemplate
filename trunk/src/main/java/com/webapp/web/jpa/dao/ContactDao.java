package com.webapp.web.jpa.dao;

import java.util.Collection;

import org.springframework.security.annotation.Secured;

import com.webapp.web.jpa.bean.Contact;

public interface ContactDao {

    /**
     * Find Contact by id.
     */	
	public Contact findContactById(Integer id);
	
    /**
     * Find Contacts.
     */
	public Collection<Contact> findContacts();
	
    /**
     * Find Contact using start index and max number of results.
     */	
	public Collection<Contact> findContacts(final int startIndex, final int maxResult);
	
    /**
     * Find Contact by Last Name.
     */
	public Collection<Contact> findContactsByLastName(String lastName);
	
    /**
     * Saves Contact.
     */	
	public Contact save(Contact contact);
	
    /**
     * Deletes Contact.
     */	
    @Secured ({"ROLE_ADMIN"})    	
	public void delete(Contact contact);

}
