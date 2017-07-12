package br.com.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.SystemUser;

public class SystemUserDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(SystemUser systemUser) {
		this.entityManager.persist(systemUser);
	}

	public SystemUser findByEmail(String email) {
		return this.entityManager
				.createQuery("select u from SystemUser u where u.email = :email", SystemUser.class)
				.setParameter("email", email).getSingleResult();
	}

}
