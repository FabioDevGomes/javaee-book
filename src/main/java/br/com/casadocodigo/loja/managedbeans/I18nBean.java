package br.com.casadocodigo.loja.managedbeans;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class I18nBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Locale locale;

	@Inject
	private FacesContext context;
	
	@PostConstruct
	private void loadLocale(){
		locale = context.getApplication().getDefaultLocale();
	}

	public String changeLocale(String language){
		locale = new Locale(language);
		return "site/index.xhtml?faces-redirect=true";
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	
}
