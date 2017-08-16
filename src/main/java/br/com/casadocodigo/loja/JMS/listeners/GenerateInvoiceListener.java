package br.com.casadocodigo.loja.JMS.listeners;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.casadocodigo.loja.daos.CheckoutDao;
import br.com.casadocodigo.loja.models.Checkout;
import br.com.casadocodigo.loja.services.InvoiceGenerator;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destinationLookup",
				propertyValue = "java:/jms/topics/checkoutsTopic")
})
public class GenerateInvoiceListener implements MessageListener{
	private Logger logger = LoggerFactory.getLogger(SendCheckoutMailListener.class);
	@Inject
	private CheckoutDao checkoutDao;
	@Inject
	private InvoiceGenerator invoiceGenerator;
	
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		Checkout checkout;
		try {
			checkout = checkoutDao.findByUUID(textMessage.getText());
			invoiceGenerator.invoiceFor(checkout);
			
		} catch (JMSException e) {
			logger.error("Problema na geração da nota fiscal", e);
		}
	}
	
}
