package br.com.casadocodigo.loja.resources;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import br.com.casadocodigo.loja.daos.BookDao;
import br.com.casadocodigo.loja.models.Book;

@Path("books")
@Stateful
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class BooksResources {

	private BookDao bookDao;
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@PostConstruct
	private void LoadDAO(){
		bookDao = new BookDao(entityManager);
	}
	
	@GET
	public List<Book> lastBookJson(){
		return bookDao.lastReleases();
	}

	@GET
	@Wrapped(element="books")
	public List<Book> lastBookXml(){
		return bookDao.lastReleases();
	}
	
}
