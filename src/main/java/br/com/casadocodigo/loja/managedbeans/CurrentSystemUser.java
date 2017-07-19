package br.com.casadocodigo.loja.managedbeans;

import java.security.Principal;

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
	private SystemUserDao userDao;
	private SystemUser user;
	
	@PostConstruct
	public void getSystemUserLogin(){
		Principal principal = request.getUserPrincipal();
		if(principal != null){
			String email = principal.getName();
			user = userDao.findByEmail(email);
		}
	}
	
	public boolean hasHole(String role){
		return request.isUserInRole(role);
	}

	public SystemUser getUser() {
		return user;
	}
	
}
