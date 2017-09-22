package br.com.casadocodigo.loja.teste.AOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.casadocodigo.loja.daos.BookDao;


public class App {
	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				new String[] { "/applicationContext.xml" });

		BookDao dao = (BookDao) appContext
				.getBean("daoBookProxyApp");

		System.out.println("*************************");
		//dao.printAlgo();
		System.out.println("*************************");
		//dao.printAlgo();
		System.out.println("*************************");

	}
}
