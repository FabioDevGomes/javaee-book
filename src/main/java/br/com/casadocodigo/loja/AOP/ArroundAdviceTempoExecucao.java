package br.com.casadocodigo.loja.AOP;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ArroundAdviceTempoExecucao implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		invocation.proceed();
		return null;
	}

}
