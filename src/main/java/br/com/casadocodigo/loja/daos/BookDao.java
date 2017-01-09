package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Book;

public class BookDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(Book book){
		entityManager.persist(book);
	}
	
	public List<Book> list(){
		return entityManager.createQuery("select distinct(b) from Book b join fetch b.authors", Book.class)
				.getResultList();
	}
}
