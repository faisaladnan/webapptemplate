package com.webapp.web.jpa.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Annotation configured blacklisted_msisdn bean.
 * @author Faisal Adnan
 *
 */
@Entity
@Table(name="BLACKLISTED_MSISDN")
public class BlacklistedMSISDN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095370870367220074L;
    private Integer id = null;
    private String msisdn = null;
    private Date created = null;
    
    /**
     * Sets id (primary key).
     */    
	public void setId(Integer id) {
		this.id = id;
	}
    /**
     * Gets id (primary key).
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)	
	public Integer getId() {
		return id;
	}
    
    /**
     * Sets msisdn.
     */    
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	
    /**
     * Gets msisdn.
     */	
	@Column(name = "MSISDN")
	public String getMsisdn() {
		return msisdn;
	}
	
    /**
     * Sets date created.
     */	
	public void setCreated(Date created) {
		this.created = created;
	}
	
    /**
     * Gets date created.
     */	
	public Date getCreated() {
		return created;
	}
	
    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getName() + "-");
        sb.append("  id=" + id);
        sb.append("  msisdn=" + msisdn);
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

        final BlacklistedMSISDN other = (BlacklistedMSISDN) obj;

        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id)) {
            return false;
        }

        return true;
    }
	
}
