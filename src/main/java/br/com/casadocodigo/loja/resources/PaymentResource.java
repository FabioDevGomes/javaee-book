package br.com.casadocodigo.loja.resources;

import java.math.BigDecimal;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.casadocodigo.loja.daos.CheckoutDao;
import br.com.casadocodigo.loja.models.Checkout;
import br.com.casadocodigo.loja.services.PaymentGateway;

@Path("payment")
public class PaymentResource {

	@Resource(name = "java:comp/DefaultManagedExecutorService")
	private ManagedExecutorService managedExecutorService;
//	private static ExecutorService executor = Executors.newFixedThreadPool(50);
	@Context
	private ServletContext ctx;
	@Inject
	private CheckoutDao checkoutDao;
	@Inject
	private PaymentGateway paymentGateway;

	@POST
	public void pay(@Suspended final AsyncResponse ar, @QueryParam("uuid") String uuid) {
		String contextPath = ctx.getContextPath();
		Checkout checkout = checkoutDao.findByUUID(uuid);

		managedExecutorService.submit(() -> {
			try {
				BigDecimal total = checkout.getValue();
				paymentGateway.pay(total);
				
				URI uriRedirect = UriBuilder
						.fromPath("http://localhost:8080" + contextPath + "/site/index.xhtml")
						.queryParam("msg", "Compra realizada com sucesso!").build();
				
				Response response = Response.seeOther(uriRedirect).build();
				ar.resume(response);
				
			} catch (Exception e) {
				ar.resume(new WebApplicationException(e));
			}
		});
	}

}
