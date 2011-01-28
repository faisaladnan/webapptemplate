package com.webapp.web.jpa.dao;

import java.util.Collection;

import org.springframework.security.annotation.Secured;

import com.webapp.web.jpa.bean.BlacklistedMSISDN;
import com.webapp.web.jpa.bean.Customer;

/**
 * Customer DAO interface.
 * @author Faisal Adnan
 *
 */
public interface CustomerDao {

    /**
     * Find Customer by id.
     */
    public Customer findCustomerById(Integer id);

    /**
     * Find Customers.
     */
    public Collection<Customer> findCustomers();	
    
    /**
     * Find Customers using a start index and max number of results.
     */
    public Collection<Customer> findCustomers(final int startIndex, final int maxResults);  
    
    /**
     * Saves Customers.
     */
    public Customer save(Customer customer);    
    
    /**
     * Deletes Customer.
     */
    @Secured ({"ROLE_ADMIN"})    
    public void delete(Customer customer);  
    
    /**
     * Saves blacklisted_msisdn to Customer by adding or updating record.
     */
    public Customer saveBlacklistedMSISDN(Integer id, BlacklistedMSISDN blacklistedMSISDN);  
    
    /**
     * Deletes blacklisted_msisdn.
     */
    @Secured ({"ROLE_ADMIN"})    
    public Customer deleteBlacklistedMSISDN(Integer id, Integer blacklistedMSISDNId);    
}
