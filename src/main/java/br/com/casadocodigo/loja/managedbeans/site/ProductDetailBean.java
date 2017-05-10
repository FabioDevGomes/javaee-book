package br.com.casadocodigo.loja.managedbeans.site;

import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.casadocodigo.loja.daos.BookDao;
import br.com.casadocodigo.loja.models.Book;

@Model
@Stateful
public class ProductDetailBean {
	
	@Inject
	private BookDao bookDao;
	private Book book = new Book();
	private Integer id;
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	public String loadBook(){
		bookDao = new BookDao(entityManager);
		book = bookDao.findById(id);
		return null;
	}
	
	public Book getBook() {
		return book;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
