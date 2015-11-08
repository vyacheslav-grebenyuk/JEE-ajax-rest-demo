package org.oa.ajax_rest_demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Goods {
	@XmlElement
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @SequenceGenerator(name = "goods_id")
	private int id;
	
	@XmlElement
	@Column
	private String name;

	@XmlElement
	@Column
	private long price;
	
	@XmlElement
	@Column
	private int quantity;

	public Goods() {
		name = "";
		price = 0;
		quantity = 0;
	}	
	
	public Goods(String name, long price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int y) {
		this.quantity = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + (int) (price ^ (price >>> 32));
		result = prime * result + quantity;
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
		Goods other = (Goods) obj;
		if (id != other.id)
			return false;
		if (price != other.price)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
}

