package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;


@Named
@SessionScoped
public class ShoppingCart implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<ShoppingItem, Integer> items = new LinkedHashMap<>();

	public void add(ShoppingItem item) {
		items.put(item, getQuantity(item) + 1);
	}

	public Integer getQuantity(ShoppingItem item) {
		if (!items.containsKey(item)) {
			items.put(item, 0);
		}
		return items.get(item);
	}

	public Integer getQuantity() {
		return items.values().stream().reduce(0, (next, accumulator) -> next + accumulator);
	}

	public Collection<ShoppingItem> getList() {
		return new ArrayList<>(items.keySet());
	}

	public BigDecimal getTotal(ShoppingItem item) {
		return item.getTotal(getQuantity(item));
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (ShoppingItem item : items.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}
	
	public void remove(ShoppingItem item) {
		//items.remove(item);
		 items.keySet().contains(item);
		 items.get(item);
		 
		items.keySet().remove(item);
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	public void clear(){
		items.clear();
	}

	public String toJson(){
		JsonArrayBuilder itens = Json.createArrayBuilder();
		for (ShoppingItem item : getList()) {
			itens.add(Json.createObjectBuilder()
					.add("title", item.getBook().getTitle())
					.add("price", item.getPrice())
					.add("quantity", getQuantity().intValue()) //testar se o autoboxing resolve isso
					.add("sum", getTotal(item)));
		}
		return itens.build().toString();
	}

}
