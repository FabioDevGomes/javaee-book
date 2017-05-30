package br.com.casadocodigo.loja.managedbeans.site;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.CheckoutDao;
import br.com.casadocodigo.loja.daos.SystemUserDao;
import br.com.casadocodigo.loja.models.Checkout;
import br.com.casadocodigo.loja.models.ShoppingCart;
import br.com.casadocodigo.loja.models.SystemUser;

@Model
public class CheckoutBean {

	private SystemUser systemUser = new SystemUser();
	@Inject
	private SystemUserDao systemUserDao;
	@Inject
	private ShoppingCart shoppingCart;
	@Inject
	private CheckoutDao checkoutDao;
	
	public SystemUser getSystemUser() {
		return systemUser;
	}
	
	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
	
	@Transactional
	public void checkout(){
		systemUserDao.save(systemUser);
		
		Checkout checkout = new Checkout(systemUser, shoppingCart);
		checkoutDao.save(checkout);
	}
	
	
}
