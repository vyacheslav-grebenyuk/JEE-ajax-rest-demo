package org.oa.ajax_rest_demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement(name = "users")
public class Users implements Serializable {
	private static final long serialVersionUID = -7986510851534557861L;

	@XmlElement
	@Id
	@Column
	private String username;

	@XmlElement
	@Column
	private String password;
	
	@XmlElement
	@Column
	private boolean enabled;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USERS_USER_ROLES", 
    		joinColumns = { @JoinColumn(name = "USERS_ID") }, 
    		inverseJoinColumns = { @JoinColumn(name = "USER_ROLES_ID") })
	private Set<UserRoles> roles = new HashSet<>();


	public Set<UserRoles> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRoles> tools) {
		this.roles = tools;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
