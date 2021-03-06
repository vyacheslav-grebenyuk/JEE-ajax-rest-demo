package org.oa.ajax_rest_demo.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pet")
public class Pet {
	
	@XmlElement
	private Integer idPets;
	@XmlElement
	private String name;
	@XmlElement
	private int age;
	private List<Food> foods;
	private List<Tools> tools;
	
	public Pet(String name, int age) {
		idPets = null;
		this.name = name;
		this.age = age;
		foods = null;
		tools = null;
	}
	public Pet() {
		idPets = null;
		name = null;
		age = 0;
		foods = null;
		tools = null;
	}
	
	public List<Tools> getTools() {
		return tools;
	}
	public void setTools(List<Tools> tools) {
		this.tools = tools;
	}
	public Integer getIdPets() {
		return idPets;
	}
	public void setIdPets(Integer id) {
		this.idPets = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((foods == null) ? 0 : foods.hashCode());
		result = prime * result + ((idPets == null) ? 0 : idPets.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tools == null) ? 0 : tools.hashCode());
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
		Pet other = (Pet) obj;
		if (age != other.age)
			return false;
		if (foods == null) {
			if (other.foods != null)
				return false;
		} else if (!foods.equals(other.foods))
			return false;
		if (idPets == null) {
			if (other.idPets != null)
				return false;
		} else if (!idPets.equals(other.idPets))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tools == null) {
			if (other.tools != null)
				return false;
		} else if (!tools.equals(other.tools))
			return false;
		return true;
	}
	
}
