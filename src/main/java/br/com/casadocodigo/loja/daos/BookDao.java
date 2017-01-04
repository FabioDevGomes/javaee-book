package br.com.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Book;

public class BookDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(Book book){
		entityManager.persist(book);
	}
}
