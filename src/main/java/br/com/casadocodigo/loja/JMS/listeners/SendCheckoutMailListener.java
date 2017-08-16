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
import br.com.casadocodigo.loja.infra.MailSender;
import br.com.casadocodigo.loja.models.Checkout;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topics/checkoutsTopic") })
public class SendCheckoutMailListener implements MessageListener {

	private Logger logger = LoggerFactory.getLogger(SendCheckoutMailListener.class);
	@Inject
	private MailSender mailSender;
	@Inject
	private CheckoutDao checkoutDao;

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		Checkout checkout;
		try {
			checkout = checkoutDao.findByUUID(textMessage.getText());

			String mailBody = "<html><body>Olá " + checkout.getBuyer().getFirstName()
					+ ", uma nova compra foij realizada. Seu código de acompanhamento é " + checkout.getUuid() + "</body></html>";
			mailSender.send("compras@cadadocodigo.com.br", checkout.getBuyer().getEmail(), "Nova Compra - JavaEE 7", mailBody);

		} catch (JMSException e) {
			logger.error("Problema no envio do email", e);
		}
	}

}
