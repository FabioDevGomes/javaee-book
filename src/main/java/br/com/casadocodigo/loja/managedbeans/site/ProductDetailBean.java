package br.com.casadocodigo.loja.managedbeans.site;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.BookDao;
import br.com.casadocodigo.loja.models.Book;

@Model
public class ProductDetailBean {
	
	@Inject
	private BookDao bookDao;
	private Book book;
	
	@PostConstruct
	public void loadBook(){
//		bookDao.
	}
	
	public Book getBook() {
		return book;
	}

}
