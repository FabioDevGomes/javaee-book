package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Book;

public class BookDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public BookDao() {
	}

	public BookDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Book book) {
		entityManager.persist(book);
	}

	public List<Book> list() {
		return entityManager
				.createQuery("select distinct(b) from Book b join fetch b.authors", Book.class)
				.getResultList();
	}

	public List<Book> lastReleases() {
		return entityManager
				.createQuery("select b from Book b where b.releaseDate <= now() order by b.id desc",
						Book.class)
				.setMaxResults(3).getResultList();
	}

	public List<Book> olderBooks() {
		return entityManager.createQuery("select b from Book b", Book.class).setMaxResults(20)
				.getResultList();
	}
	
	public Book findById(Integer id){
		return entityManager.find(Book.class, id);
	}
}
