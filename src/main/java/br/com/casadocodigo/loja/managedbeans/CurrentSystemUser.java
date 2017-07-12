package br.com.casadocodigo.loja.managedbeans;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.casadocodigo.loja.daos.SystemUserDao;
import br.com.casadocodigo.loja.models.SystemUser;

@Model
public class CurrentSystemUser {

	@Inject
	private HttpServletRequest request;
	@Inject
	private SystemUser user;
	@Inject
	private SystemUserDao userDao;
	
	@PostConstruct
	public void getSystemUserLogin(){
		String email = request.getUserPrincipal().getName();
		user = userDao.findByEmail(email);
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}
	
	
}
