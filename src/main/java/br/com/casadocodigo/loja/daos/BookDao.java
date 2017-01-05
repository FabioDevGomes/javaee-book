package br.com.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.models.Book;

public class BookDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void save(Book book){
		entityManager.persist(book);
	}
}
