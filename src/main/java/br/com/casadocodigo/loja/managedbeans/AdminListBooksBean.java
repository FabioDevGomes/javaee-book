package br.com.casadocodigo.loja.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.BookDao;
import br.com.casadocodigo.loja.models.Book;

@Model
public class AdminListBooksBean {
	
	@Inject
	private BookDao bookDao;
	private List<Book> books = new ArrayList<>();
	
	@PostConstruct
	private void loadObjects(){
		this.books = bookDao.list();
	}

	public List<Book> getBooks() {
		return books;
	}
	
}
