package br.com.casadocodigo.loja.managedbeans.site;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.BookDao;
import br.com.casadocodigo.loja.models.Book;
import br.com.casadocodigo.loja.models.ShoppingCart;
import br.com.casadocodigo.loja.models.ShoppingItem;

@Model
public class ShoppingCartBean {

	//disponível na view (@named)
	//vive durante a sessão
	@Inject
	private ShoppingCart shoppingCart;

	@Inject
	private BookDao bookDao;

	public String add(Integer id) {
		Book book = bookDao.findById(id);
		ShoppingItem item = new ShoppingItem(book);
		shoppingCart.add(item);
		System.out.println("Hash1 = "+item.hashCode());
		return "/site/carrinho?faces-redirect=true";
	}

	public String remove(Integer id) {
		Book book = bookDao.findById(id);
		ShoppingItem item = new ShoppingItem(book);
		shoppingCart.remove(item);
		System.out.println("Hash2 = "+item.hashCode());
		return "/site/carrinho?faces-redirect=true";
	}

}
