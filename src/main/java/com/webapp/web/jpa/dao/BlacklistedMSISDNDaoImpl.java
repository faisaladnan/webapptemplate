package com.webapp.web.jpa.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.web.jpa.bean.BlacklistedMSISDN;
import com.webapp.web.jpa.bean.Customer;
import com.webapp.web.jpa.bean.Person;

/**
 * BlacklistedMSISDN DAO implementation.
 * 
 * @author Faisal Adnan
 */
@Repository
@Transactional(readOnly = true)
public class BlacklistedMSISDNDaoImpl implements BlacklistedMSISDNDao {

    private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    /**
     * Find BlacklistedMSISDN.
     */    
	public BlacklistedMSISDN findBlacklistedMSISDNById(Integer id) {
		return em.find(BlacklistedMSISDN.class, id);
	}

    /**
     * Find BlacklistedMSISDNs.
     */ 
    @SuppressWarnings("unchecked")	
	public Collection<BlacklistedMSISDN> findBlacklistedMSISDNs() {
    	return em.createQuery("select b from BlacklistedMSISDN b order by b.id").getResultList();
	}

	public Collection<BlacklistedMSISDN> findBlacklistedMSISDNsByCustomerID(Integer customerID) {
		String query = "from BlacklistedMSISDN b join b.customer as c";
//		Query query = em.createQuery(query);
		List results = em.createQuery(query).getResultList();
		displayObjectsList(results);
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Saves BlacklistedMSISDN.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)	
	public BlacklistedMSISDN save(BlacklistedMSISDN blacklistedMSISDN,
			Customer customer) {
    	customer.getBlacklistedMsisdns().add(blacklistedMSISDN);
    	return em.merge(blacklistedMSISDN);
	}

	public void delete(BlacklistedMSISDN blacklistedMSISDN) {
		// TODO Auto-generated method stub
		
	}

    static public void displayObjectsList(List list)
    {
        Iterator iter = list.iterator();
        if (!iter.hasNext())
        {
            System.out.println("No objects to display.");
            return;
        }
        while (iter.hasNext())
        {
            System.out.println("New object");
            Object[] obj = (Object[]) iter.next();
            for (int i=0;i<obj.length;i++)
            {
                System.out.println(obj[i]);
            }
            
            
        }
    } 	
}
