package br.com.casadocodigo.loja.webSockets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ConnectedUsers {
	
	private Set<Session> remoteUsers = new HashSet<>();
	private Logger logger = LoggerFactory.getLogger(ConnectedUsers.class);
	
	public void add(Session user){
		remoteUsers.add(user);
	}

	public void remove(Session user){
		remoteUsers.remove(user);
	}
	
	public void send(String message){
		for (Session user : remoteUsers) {
			if(user.isOpen()){
				try {
					user.getBasicRemote().sendText(message);
				} catch (IOException e) {
						logger.error("Não foi possível enviar a mensagem para um cliente {}", e);
				}
			}
		}
	}

}
