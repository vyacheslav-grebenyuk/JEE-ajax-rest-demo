package org.oa.ajax_rest_demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="user_roles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement(name = "user_roles")
public class UserRoles implements Serializable {
	private static final long serialVersionUID = 7461898044980553758L;

	@XmlElement
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @SequenceGenerator(name = "user_roles_id")
	@Column
	private int id;
	
	@XmlElement
	@Column
	private UserRoleType role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserRoleType getRole() {
		return role;
	}

	public void setRole(UserRoleType role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		UserRoles other = (UserRoles) obj;
		if (id != other.id)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
}
