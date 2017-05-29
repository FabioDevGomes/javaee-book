package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Checkout {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private SystemUser buyer;
	
	private BigDecimal value;
	
	private String jsonCart;

	public Checkout() {	}

	public Checkout(SystemUser buyer, ShoppingCart cart) {
		super();
		this.buyer = buyer;
		this.value = cart.getTotal();
		this.jsonCart = cart.toJson();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SystemUser getBuyer() {
		return buyer;
	}

	public void setBuyer(SystemUser buyer) {
		this.buyer = buyer;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getJsonCart() {
		return jsonCart;
	}

	public void setJsonCart(String jsonCart) {
		this.jsonCart = jsonCart;
	}
	
}
