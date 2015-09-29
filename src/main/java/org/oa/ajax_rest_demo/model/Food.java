package org.oa.ajax_rest_demo.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "food")
public class Food {
	@XmlElement
	Integer idFood;
	@XmlElement
	String name;
	@XmlElement
	Float price;
	
	public Food() {
		idFood = null;
		name = null;
		price = null;
	}
	public Food(Integer idFood, String name, Float price) {
		super();
		this.idFood = idFood;
		this.name = name;
		this.price = price;
	}
	public Integer getIdFood() {
		return idFood;
	}
	public void setIdFood(Integer id) {
		this.idFood = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFood == null) ? 0 : idFood.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		Food other = (Food) obj;
		if (idFood == null) {
			if (other.idFood != null)
				return false;
		} else if (!idFood.equals(other.idFood))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}	
}
