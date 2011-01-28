package com.webapp.web.jpa.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Annotation configured customer bean.
 * 
 * @author Faisal Adnan
 */
@Entity
@Table(name="CUSTOMER")
public class Customer implements Serializable {

	private static final long serialVersionUID = -255109406201139441L;

	private Integer id = null;
    private String customerName = null;
    private String address = null;	
        
	private Set<BlacklistedMSISDN> blacklistedMsisdns = null;
    
    private Date created = null;
    
    /**
     * Gets id (primary key).
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    /**
     * Sets id (primary key).
     */
    public void setId(Integer id) {
        this.id = id;
    }  
    
    /**
     * Gets customer name.
     */
    @Column(name="CUSTOMER_NAME")
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets customer name.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }    
    
    /**
     * Gets address.
     */
    @Column(name="ADDRESS")
    public String getAddress() {
        return address;
    }

    /**
     * Sets customer name.
     */
    public void setAddress(String address) {
        this.address = address;
    }      


    
    /**
     * Gets date created.
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Sets date created.
     */
    public void setCreated(Date created) {
        this.created = created;
    }
 
    /**
     * Sets list of <code>BlacklistedMSISDN</code>es.
     */      
	public void setBlacklistedMsisdns(Set<BlacklistedMSISDN> blacklistedMsisdns) {
		this.blacklistedMsisdns = blacklistedMsisdns;
	}

    /**
     * Gets list of <code>BlacklistedMSISDN</code>es.
     */  	
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="CUSTOMER_ID", nullable=false) 	
	public Set<BlacklistedMSISDN> getBlacklistedMsisdns() {
		return blacklistedMsisdns;
	}    
    
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getName() + "-");
        sb.append("  id=" + id);
        sb.append("  customer_name=" + customerName);
        sb.append("  address=" + address);
        sb.append("  created=" + created);        

        return sb.toString();
    }

    /**
     * Returns a hash code value for the object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }

    /**
     * Indicates whether some other object is equal to this one.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Customer other = (Customer) obj;

        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id)) {
            return false;
        }

        return true;
    }	    
}
