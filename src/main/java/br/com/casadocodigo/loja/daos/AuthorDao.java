package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.models.Author;
import br.com.casadocodigo.loja.models.Book;

public class AuthorDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Author> list(){
		return entityManager.createQuery("select a from Author a order by a.name asc", Author.class).getResultList();
	}
}
