package br.com.casadocodigo.loja.security;

import org.jboss.security.Base64Encoder;

public class PassGenerator {

	public static void main(String[] args) throws Exception {
		Base64Encoder.main(new String[]{"12345", "SHA-256"});
	}
	
}