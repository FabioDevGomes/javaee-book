package br.com.casadocodigo.loja.managedbeans.site;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.BookDao;
import br.com.casadocodigo.loja.models.Book;

@Model
public class ProductDetailBean {
	
	@Inject
	private BookDao bookDao;
	private Book book = new Book();
	private Integer id;
	
	public String loadBook(){
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
