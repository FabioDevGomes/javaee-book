package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Checkout {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private SystemUser buyer;
	
	private BigDecimal value;
	
	private String jsonCart;
	
	private String uuid;

	public Checkout() {	}

	public Checkout(SystemUser buyer, ShoppingCart cart) {
		super();
		this.buyer = buyer;
		this.value = cart.getTotal();
		this.jsonCart = cart.toJson();
	}
	
	@PrePersist
	public void PrePersist(){
		this.uuid = UUID.randomUUID().toString();
	}

	public Integer getId() {
		return id;
	}

	public SystemUser getBuyer() {
		return buyer;
	}

	public BigDecimal getValue() {
		return value;
	}

	public String getJsonCart() {
		return jsonCart;
	}

	public String getUuid() {
		return uuid;
	}
	
}
