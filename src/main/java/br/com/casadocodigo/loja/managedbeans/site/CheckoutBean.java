package br.com.casadocodigo.loja.managedbeans.site;

import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.CheckoutDao;
import br.com.casadocodigo.loja.daos.SystemUserDao;
import br.com.casadocodigo.loja.models.Checkout;
import br.com.casadocodigo.loja.models.ShoppingCart;
import br.com.casadocodigo.loja.models.ShoppingItem;
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
	@Inject
	private FacesContext facesContext;
	
	public SystemUser getSystemUser() {
		return systemUser;
	}
	
	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
	
	@Transactional
	public void checkout(){
		checkUser();
		
		Checkout checkout = new Checkout(systemUser, shoppingCart);
		checkoutDao.save(checkout);
		shoppingCart.clear();
		
		String contextName = facesContext.getExternalContext().getContextName();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		
		response.setStatus(307);
		response.setHeader("Location", "/"+ contextName +"/services/payment?uuid="+ checkout.getUuid());
	}

	private void checkUser() {
		SystemUser userExist = systemUserDao.findByEmail(systemUser.getEmail());
		if(userExist == null){
			systemUserDao.save(systemUser);
		}else{
			systemUser = userExist;
		}
	}
	
}
