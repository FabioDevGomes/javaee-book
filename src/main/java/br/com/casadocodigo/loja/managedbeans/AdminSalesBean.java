package br.com.casadocodigo.loja.managedbeans;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.models.Book;
import br.com.casadocodigo.loja.models.Sale;
import br.com.casadocodigo.loja.webSockets.ConnectedUsers;

@Model
public class AdminSalesBean {
	
	private Sale sale = new Sale();
	@Inject
	ConnectedUsers connectedUsers;
	
	@PostConstruct
	private void config(){
		sale.setBook(new Book());
	}
	
	public String save(){
		connectedUsers.send(sale.toJson());
		return "/promocoes/novaPromocao.xhtml?faces-redirect=true";
	}
	
	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

}
