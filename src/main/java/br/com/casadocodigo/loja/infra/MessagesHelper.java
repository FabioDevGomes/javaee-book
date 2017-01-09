package br.com.casadocodigo.loja.infra;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class MessagesHelper {
	
	@Inject
	private FacesContext context;

	public void addFlash(FacesMessage facesMessage){
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, facesMessage);
	}
}
