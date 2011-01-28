package com.webapp.web.jpa.dao;

import java.util.Collection;

import org.springframework.security.annotation.Secured;

import com.webapp.web.jpa.bean.BlacklistedMSISDN;
import com.webapp.web.jpa.bean.Customer;

/**
 * BlacklistedMSISDN DAO interface.
 * 
 * @author Faisal Adnan
 */
public interface BlacklistedMSISDNDao {

    /**
     * Find BlacklistedMSISDN by id.
     */
    public BlacklistedMSISDN findBlacklistedMSISDNById(Integer id);
    
    /**
     * Find BlacklistedMSISDNs.
     */
    public Collection<BlacklistedMSISDN> findBlacklistedMSISDNs();
    
    /**
     * Find BlacklistedMSISDNs by Customer ID.
     */
    public Collection<BlacklistedMSISDN> findBlacklistedMSISDNsByCustomerID(Integer customerID);     
    
    /**
     * Saves BlacklistedMSISDN.
     */
    public BlacklistedMSISDN save(BlacklistedMSISDN blacklistedMSISDN, Customer customer);   
    
    /**
     * Deletes BlacklistedMSISDN.
     */
    @Secured ({"ROLE_ADMIN"})    
    public void delete(BlacklistedMSISDN blacklistedMSISDN);    
}
