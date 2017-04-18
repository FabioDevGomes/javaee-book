package br.com.casadocodigo.loja.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.AuthorDao;
import br.com.casadocodigo.loja.daos.BookDao;
import br.com.casadocodigo.loja.infra.MessagesHelper;
import br.com.casadocodigo.loja.models.Author;
import br.com.casadocodigo.loja.models.Book;

@Model
public class AdminBooksBean {
	
	private Book product = new Book();
	private List<Author> autors = new ArrayList<>();
	private List<Integer> selectAuthorsIds = new ArrayList<>();
	private BookDao bookDao;
	private AuthorDao authorDao;
	@Inject
	private MessagesHelper messagesHelper;
	private Part sumary;
	
	public AdminBooksBean(){}
	
	@Inject
	public AdminBooksBean(BookDao bookDao, AuthorDao authorDao){
		this.bookDao = bookDao;
		this.authorDao = authorDao;
	}
	
	@PostConstruct
	public void loadObjects(){
		this.autors = authorDao.list();
	}

	@Transactional
	public String save(){
		bookDao.save(product);
		messagesHelper.addFlash(new FacesMessage("Livro adicionado com sucesso!"));
		return "/livros/lista?faces-redirect=true";
	}
	
	public Book getProduct() {
		return product;
	}

	public List<Integer> getSelectAuthorsIds() {
		return selectAuthorsIds;
	}

	public void setSelectAuthorsIds(List<Integer> selectAuthorsIds) {
		this.selectAuthorsIds = selectAuthorsIds;
	}

	public List<Author> getAutors() {
		return autors;
	}

	public void setAutors(List<Author> autors) {
		this.autors = autors;
	}
}
