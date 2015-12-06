package org.oa.ajax_rest_demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="basket")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement(name = "basket")
public class Basket {
	@XmlElement
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @SequenceGenerator(name = "basket_id")
	private int id;
	
	@XmlElement
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users client;

	@XmlElement
	@ManyToOne
	@JoinColumn(name="good_id")
	private Goods good;
	
	@XmlElement
	@Column
	private int quantity;

	public Basket() {
		client = null;
		good = null;
		quantity = 0;
	}	
	
	public Basket(Users client, Goods good, int quantity) {
		this.client = client;
		this.good = good;
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Goods getGood() {
		return good;
	}
	
	public void setGood(Goods good) {
		this.good = good;
	}
	
	public Users getClient() {
		return client;
	}
	
	public void setClient(Users name) {
		this.client = name;
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
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((good == null) ? 0 : good.hashCode());
		result = prime * result + id;
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
		Basket other = (Basket) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (good == null) {
			if (other.good != null)
				return false;
		} else if (!good.equals(other.good))
			return false;
		if (id != other.id)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

}

