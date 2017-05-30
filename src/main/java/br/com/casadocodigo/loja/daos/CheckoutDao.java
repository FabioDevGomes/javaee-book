package br.com.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Checkout;
import br.com.casadocodigo.loja.models.SystemUser;

public class CheckoutDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(Checkout checkout){
		this.entityManager.persist(checkout);
	}

}
