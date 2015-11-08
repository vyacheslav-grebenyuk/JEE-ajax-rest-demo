package org.oa.ajax_rest_demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="food")
@XmlRootElement(name = "food")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Food extends Goods implements Serializable{
	private static final long serialVersionUID = -2396210443794675041L;

	@XmlElement
	@Column
	private Float weight;
	
	public Food() {
		weight = null;
	}
	public Food(Integer idFood, String name, Float price) {
		super();
		this.weight = price;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float price) {
		this.weight = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		Food other = (Food) obj;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}	
}
