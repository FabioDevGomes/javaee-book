package br.com.casadocodigo.loja.managedbeans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.BookDao;
import br.com.casadocodigo.loja.models.Book;

@Model
public class AdminBooksBean {
	
	private Book product = new Book();
	@Inject
	private BookDao bookDao;

	public void save(){
		System.out.println("-- save() --" + product);
		bookDao.save(product);
	}

	public Book getProduct() {
		return product;
	}
	
	
	
}
