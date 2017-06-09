package br.com.casadocodigo.loja.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.casadocodigo.loja.daos.BookDao;
import br.com.casadocodigo.loja.models.Book;

@Path("books")
public class BooksResources {

	@Inject
	private BookDao bookDao;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("json")
	public List<Book> lastBookJson(){
		return bookDao.lastReleases();
	}
	
}
