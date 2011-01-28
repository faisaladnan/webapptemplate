package com.webapp.web.jpa.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.web.jpa.bean.BlacklistedMSISDN;
import com.webapp.web.jpa.bean.Customer;

/**
 * Customer DAO implementation.
 * 
 * @author Faisal Adnan
 */
@Repository
@Transactional(readOnly = true)
public class CustomerDaoImpl implements CustomerDao {

    private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Find Customer.
     */    
	public Customer findCustomerById(Integer id) {
		return em.find(Customer.class, id);
	}

    /**
     * Find Customers.
     */
    @SuppressWarnings("unchecked")	
	public Collection<Customer> findCustomers() {
    	return em.createQuery("select c from Customer c order by c.customerName").getResultList();
	}

    /**
     * Find Customers using a start index and max number of results.
     */
    @SuppressWarnings("unchecked")	
	public Collection<Customer> findCustomers(int startIndex, int maxResults) {
        return em.createQuery("select c from Customer c order by c.customerName")
        .setFirstResult(startIndex).setMaxResults(maxResults).getResultList();
	}

    /**
     * Saves Customer.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)    
	public Customer save(Customer customer) {
    	return em.merge(customer);
	}

    /**
     * Deletes customer.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)    
	public void delete(Customer customer) {
    	em.remove(em.merge(customer));
	}

    /**
     * Saves blacklisted_msisdn to customer.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)    
	public Customer saveBlacklistedMSISDN(Integer id,
			BlacklistedMSISDN blacklistedMSISDN) {
		Customer customer = findCustomerById(id);
		if (customer.getBlacklistedMsisdns().contains(blacklistedMSISDN))
		{
			customer.getBlacklistedMsisdns().remove(blacklistedMSISDN);
		}
		customer.getBlacklistedMsisdns().add(blacklistedMSISDN);
		return save(customer);
	}

    /**
     * Deletes blacklisted_msisdn to customer.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)    
	public Customer deleteBlacklistedMSISDN(Integer id,
			Integer blacklistedMSISDNId) {
		Customer customer = findCustomerById(id);
		BlacklistedMSISDN blacklistedMSISDN = new BlacklistedMSISDN();
		blacklistedMSISDN.setId(blacklistedMSISDNId);
		
		if (customer.getBlacklistedMsisdns().contains(blacklistedMSISDN))
		{
			for (BlacklistedMSISDN b : customer.getBlacklistedMsisdns()) 
			{
				if (b.getId().equals(blacklistedMSISDNId)) {
					em.remove(b);
					customer.getBlacklistedMsisdns().remove(blacklistedMSISDN);
					break;
				}
			}
		}
		return customer;
	}


}
