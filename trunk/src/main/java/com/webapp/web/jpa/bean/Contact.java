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
 * Annotation configured contact bean
 * 
 * @author Faisal Adnan
 */
@Entity
@Table(name="CONTACTS")
public class Contact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5147450677441818171L;
	
	private Integer id = null;
	private String firstName = null;
	private String lastName = null;
	private String telephone = null;
	private String email = null;
	private Date created = null;
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the firstName
	 */
	@Column(name="firstname")
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the lastName
	 */
	@Column(name="lastname")
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

    /**
     * Returns a string representation of the object.
     */	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName() + "-");
		sb.append(" id=" + id);
		sb.append(" firstName=" + firstName);
		sb.append(" lastName=" + lastName);
		sb.append(" telephone=" + telephone);
		sb.append(" email=" + email);
		sb.append(" created=" + created);
		return sb.toString();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = 1;
		final int prime = 31;
		result = prime*result+((id == null) ? 0 : id.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final Contact other = (Contact) obj;
		if (id == null)
		{
			if (other.id != null)
			{
				return false;
			}
		} else if (!id.equals(other.id)) 
		{
			return false;
		}
		return true;
	}
	
	
}
