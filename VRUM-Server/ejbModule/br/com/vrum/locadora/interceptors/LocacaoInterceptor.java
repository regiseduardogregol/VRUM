package br.com.vrum.locadora.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LocacaoInterceptor {
	@AroundInvoke
	public Object log(InvocationContext context) throws Exception {
	System.out.println("Atenção uma Reserva foi Solicitada");
	return context.proceed();
	}

}
