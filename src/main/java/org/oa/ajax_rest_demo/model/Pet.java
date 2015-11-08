package org.oa.ajax_rest_demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="pets")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement(name = "pet")
public class Pet extends Goods implements Serializable{
	private static final long serialVersionUID = -8282881969216927365L;

	@XmlElement
	@Column
	private int age;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="food_pet",
			   joinColumns = {@JoinColumn(name = "id_pet") },
			   inverseJoinColumns = { @JoinColumn(name = "id_food") })	
	private List<Food> foods = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="tool_pet",
			   joinColumns = {@JoinColumn(name = "id_pet") },
			   inverseJoinColumns = { @JoinColumn(name = "id_tool") })
	private List<Tools> tools = new ArrayList<>();
	
	public Pet(int age) {
		this.age = age;
	}
	public Pet() {
		age = 0;
	}
	
	public List<Tools> getTools() {
		return tools;
	}
	public void setTools(List<Tools> tools) {
		this.tools = tools;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<Food> getFoods() {
		return foods;
	}
	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + age;
		result = prime * result + ((foods == null) ? 0 : foods.hashCode());
		result = prime * result + ((tools == null) ? 0 : tools.hashCode());
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
		Pet other = (Pet) obj;
		if (age != other.age)
			return false;
		if (foods == null) {
			if (other.foods != null)
				return false;
		} else if (!foods.equals(other.foods))
			return false;
		if (tools == null) {
			if (other.tools != null)
				return false;
		} else if (!tools.equals(other.tools))
			return false;
		return true;
	}
}