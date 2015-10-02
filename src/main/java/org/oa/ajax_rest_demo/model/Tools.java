package org.oa.ajax_rest_demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="tools")
@XmlRootElement(name = "tool")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tools {
	
	@XmlElement
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="idtools")
	private Integer idTools;
	
	@XmlElement
	@Column(name="name")
	private String name;
	
	public Tools() {
		idTools = null;
		name = null;
	}
	public Tools(Integer idTools, String name) {
		super();
		this.idTools = idTools;
		this.name = name;
	}
	public Integer getIdTools() {
		return idTools;
	}
	public void setIdTools(Integer idtools) {
		this.idTools = idtools;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTools == null) ? 0 : idTools.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Tools other = (Tools) obj;
		if (idTools == null) {
			if (other.idTools != null)
				return false;
		} else if (!idTools.equals(other.idTools))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
