package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

public class ShoppingItem {

	private Book book;
	private Integer bookId;
	
	public ShoppingItem(Book book) {
		super();
		this.book = book;
		this.bookId = bookId;
	}

	public Book getBook() {
		return book;
	}

	public Integer getBookId() {
		return bookId;
	}

	public BigDecimal getPrice(){
		return book.getPrice();
	}

	public BigDecimal getTotal(Integer quantity){
		return book.getPrice().multiply(new BigDecimal(quantity));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingItem other = (ShoppingItem) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		return true;
	}
	
	
}
