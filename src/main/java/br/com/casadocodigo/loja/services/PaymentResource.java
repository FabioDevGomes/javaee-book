package br.com.casadocodigo.loja.services;

import java.math.BigDecimal;
import java.net.URI;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.casadocodigo.loja.daos.CheckoutDao;
import br.com.casadocodigo.loja.models.Checkout;

@Path("payment")
public class PaymentResource {

	@Context
	private ServletContext ctx;
	@Inject
	private CheckoutDao checkoutDao;
	@Inject
	private PaymentGateway paymentGateway;
	
	@POST
	public Response pay(@QueryParam("uuid") String uuid){
		String contextPath = ctx.getContextPath();
		Checkout checkout = checkoutDao.findByUUID(uuid);
		
		BigDecimal total = checkout.getValue();
		paymentGateway.pay(total);
		
		URI uriRedirect = UriBuilder.fromPath(""+ contextPath +"").build();
		
		Response response = Response.seeOther(uriRedirect).build();
		return response;
	}
	
}
