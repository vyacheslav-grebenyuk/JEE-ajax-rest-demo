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
	@Id @GeneratedValue(strategy = GenerationType.AUTO) @SequenceGenerator(name = "goods_id")
	private int id;
	
	@XmlElement
	@Column
	private double price;
	
	@XmlElement
	@Column
	private int quantity;

	public Goods() {
		price = 0;
		quantity = 0;
	}	
	
	public Goods(double price, int quantity) {
		this.price = price;
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int y) {
		this.quantity = y;
	}
}
