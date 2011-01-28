package com.webapp.web.servlet.mvc;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.web.jpa.bean.Contact;
import com.webapp.web.jpa.bean.Person;
import com.webapp.web.jpa.dao.ContactDao;

/**
 * Contact form controller
 * 
 * @author Faisal Adnan
 */
@Controller
public class ContactController {
	
	private static final String SEARCH_VIEW_KEY = "redirect:search.html";
	private static final String SEARCH_MODEL_KEY = "contacts";
	
	@Autowired
	protected ContactDao contactDao = null;
	
    /**
     * For every request for this controller, this will 
     * create a person instance for the form.
     */
    @ModelAttribute
    public Contact newRequest(@RequestParam(required=false) Integer id) {
        return (id != null ? contactDao.findContactById(id) : new Contact());
    }	
    /**
     * <p>Contact form request.</p>
     * 
     * <p>Expected HTTP GET and request '/contact/form'.</p>
     */
    @RequestMapping(value="/contact/form", method=RequestMethod.GET)
    public void form() {}    
    
    /**
     * <p>Searches for all contacts and returns them in a 
     * <code>Collection</code>.</p>
     * 
     * <p>Expected HTTP GET and request '/contact/search'.</p>
     */
    @RequestMapping(value="/contact/search", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<Contact> search() {
        return contactDao.findContacts();
    }    
    
    /**
     * <p>Saves a contact.</p>
     * 
     * <p>Expected HTTP POST and request '/contact/form'.</p>
     */
    @RequestMapping(value="/contact/form", method=RequestMethod.POST)
    public void form(Contact contact, Model model) {
        if (contact.getCreated() == null) {
        	contact.setCreated(new Date());
        }

        Contact result = contactDao.save(contact);
        
        // set id from create
        if (contact.getId() == null) {
        	contact.setId(result.getId());
        }

        model.addAttribute("statusMessageKey", "contact.form.msg.success");
    } 
    
    /**
     * <p>Deletes a contact.</p>
     * 
     * <p>Expected HTTP POST and request '/contact/delete'.</p>
     */
    @RequestMapping(value="/contact/delete", method=RequestMethod.POST)
    public String delete(Contact contact) {
        contactDao.delete(contact);

        return SEARCH_VIEW_KEY;
    }    
}
