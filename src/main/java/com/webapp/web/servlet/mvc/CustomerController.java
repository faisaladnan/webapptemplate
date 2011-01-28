package com.webapp.web.servlet.mvc;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.web.jpa.bean.BlacklistedMSISDN;
import com.webapp.web.jpa.bean.Customer;
import com.webapp.web.jpa.dao.CustomerDao;

/**
 * Customer form controller.
 * 
 * @author Faisal Adnan
 */
@Controller
public class CustomerController {

    private static final String SEARCH_VIEW_KEY = "redirect:search.html";
    private static final String SEARCH_MODEL_KEY = "customers";
    
    @Autowired
    protected CustomerDao customerDao = null;	
    
    /**
     * For every request for this controller, this will 
     * create a customer instance for the form.
     */
    @ModelAttribute
    public Customer newRequest(@RequestParam(required=false) Integer id) {
        return (id != null ? customerDao.findCustomerById(id) : new Customer());
    }   
    
    /**
     * <p>Customer form request.</p>
     * 
     * <p>Expected HTTP GET and request '/customer/form'.</p>
     */
    @RequestMapping(value="/customer/form", method=RequestMethod.GET)
    public void form() {}    
    
    /**
     * <p>Saves a Customer.</p>
     * 
     * <p>Expected HTTP POST and request '/customer/form'.</p>
     */
    @RequestMapping(value="/customer/form", method=RequestMethod.POST)
    public void form(Customer customer, Model model) {
        if (customer.getCreated() == null) {
        	customer.setCreated(new Date());
        }

        Customer result = customerDao.save(customer);
        
        // set id from create
        if (customer.getId() == null) {
        	customer.setId(result.getId());
        }

        model.addAttribute("statusMessageKey", "customer.form.msg.success");
    }    
    
    /**
     * <p>Deletes a customer.</p>
     * 
     * <p>Expected HTTP POST and request '/customer/delete'.</p>
     */
    @RequestMapping(value="/customer/delete", method=RequestMethod.POST)
    public String delete(Customer customer) {
        customerDao.delete(customer);

        return SEARCH_VIEW_KEY;
    }    
    
    /**
     * <p>Searches for all customers and returns them in a 
     * <code>Collection</code>.</p>
     * 
     * <p>Expected HTTP GET and request '/customer/search'.</p>
     */
    @RequestMapping(value="/customer/search", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<Customer> search() {
        return customerDao.findCustomers();
    }    
    
    /**
     * <p>blacklistedmsisdn form request.</p>
     * 
     * <p>Expected HTTP GET and request '/customer/blacklistedmsisdnform'.</p>
     */
    @RequestMapping(value="/customer/blacklistedmsisdnform", method=RequestMethod.GET)
    public void blacklistedmsisdnform() {}          
    
    /**
     * <p>Create a blacklistedmsisdn.</p>
     * 
     * <p>Expected HTTP POST and request '/customer/newblacklistedmsisdn'.</p>
     */
    @RequestMapping(value="/customer/newblacklistedmsisdn", method=RequestMethod.POST)
    public String blacklistedmsisdnform(Customer customer, @RequestParam("id") String id, @RequestParam("msisdn") String msisdn) {
    	Integer customerId = Integer.parseInt(id);
    	customer = customerDao.findCustomerById(customerId);
    	if (customer.getId() != null)
    	{
    		BlacklistedMSISDN blacklistedMSISDN = new BlacklistedMSISDN();
    		blacklistedMSISDN.setMsisdn(msisdn);
    		customerDao.saveBlacklistedMSISDN(customer.getId(), blacklistedMSISDN);
    		
    	}
          	
        return "redirect:search.html";
    }     
    
    /**
     * <p>Search blacklistedmsisdn per Customer.</p>
     * 
     * <p>Expected HTTP POST and request '/customer/blacklistedmsisdnlist'.</p>
     */
    @RequestMapping(value="/customer/blacklistedmsisdnlist", method=RequestMethod.GET)
    public void blacklistedmsisdnlist() {}   
    
    /**
     * <p>Delete a blacklistedmsisdn.</p>
     * 
     * <p>Expected HTTP POST and request '/customer/deleteblacklistedmsisdn'.</p>
     */
    @RequestMapping(value="/customer/deleteblacklistedmsisdn", method=RequestMethod.POST)
    public String blacklistedmsisdndelete(Customer customer, @RequestParam("customerId") String customerId, @RequestParam("blacklistedMSISDNId") String blacklistedMSISDNId) {
    	Integer customerIdInt = Integer.parseInt(customerId);
    	Integer blacklistedMSISDNIdInt = Integer.parseInt(blacklistedMSISDNId);
    	customer = customerDao.deleteBlacklistedMSISDN(customerIdInt, blacklistedMSISDNIdInt);

    	return "redirect:search.html";
    }
    
    /**
     * <p>Web Service Send SMS.</p>
     * 
     * <p>Expected HTTP GET and request '/webservice/sendsms'.</p>
     */
    @RequestMapping(value="/webservice/sendsms", method=RequestMethod.GET)
    public void sendsms(@RequestParam("clientID") String clientID, 
    		@RequestParam("message") String message,
    		@RequestParam("destination") String destination,
    		Model model) {
    	model.addAttribute("isWebService", "Y");
    	boolean isRejected = false;
    	Customer customer = customerDao.findCustomerById(Integer.parseInt(clientID));
    	if (customer != null)
    	{
    		Set<BlacklistedMSISDN> list = customer.getBlacklistedMsisdns();
    		Iterator<BlacklistedMSISDN> iter = list.iterator();
    		while (iter.hasNext())
    		{
    			BlacklistedMSISDN msisdn = iter.next();
    			if (msisdn.getMsisdn().equals(destination))
    			{
    				isRejected = true;
    				break;
    			}
    		}
    		if (isRejected)
    		{
				model.addAttribute("status", "REJECTED");
    		} else 
    		{
    			model.addAttribute("status", "OK");
    		}
    	} else
    	{
        	model.addAttribute("status", "CLIENTID_REQUIRED");
    	}

    	
    }       
}
