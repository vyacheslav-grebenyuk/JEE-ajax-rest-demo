package org.oa.ajax_rest_demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="tools")
@XmlRootElement(name = "tool")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tools extends Goods implements Serializable{
	
	private static final long serialVersionUID = 2773029531127932619L;
	
	public Tools() {
	}
	public Tools(Integer idTools, String name) {
		super();
	}
	@Override
	public int hashCode() {
		int result = super.hashCode();
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}
