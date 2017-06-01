package br.com.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Book;
import br.com.casadocodigo.loja.models.Checkout;
import br.com.casadocodigo.loja.models.SystemUser;

public class CheckoutDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(Checkout checkout){
		this.entityManager.persist(checkout);
	}
	
	public void finfByUUID(String uuid){
		
	}
	
	public Checkout findByUUID(String uuid){
		return entityManager.createQuery("selec b from Book b where b.uuid = ?1", Checkout.class).setParameter(1, uuid).getSingleResult();
	}
	
}
