package br.com.casadocodigo.loja.webSockets;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint("/channel/sales")
public class SalesEndpoint {

	Logger logger = LoggerFactory.getLogger(SalesEndpoint.class);
	
	@Inject
	private ConnectedUsers remoteUsers;

	
	@OnOpen
	public void onNewUsere(Session session){
		remoteUsers.add(session);
	}
	
	@OnClose
	public void removeUser(Session session, CloseReason closeReason){
		remoteUsers.remove(session);
		logger.info("Usuário removido das notificações Websocket");
		System.out.println(closeReason.getCloseCode());
	}

}
