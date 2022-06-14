package com.proyecto;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class FilterSecurity implements Filter  {
	
	@Autowired
	private Session session;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		 
		
		//Cojemos el valor de si esta logeado o no (True o False)
		boolean isLogged = session.isLogged();
		
		//Comprobamos si esta logeado (True) y si tiene los permisos básicos
		if (isLogged || checkPermissionsUri((HttpServletRequest) request)) {
			chain.doFilter(request, response);  //accede
		} else {
			httpResponse.sendRedirect("/usuarios/login"); //sino le llevamos a login
		}
	}
	
	private boolean checkPermissionsUri(HttpServletRequest httpServletRequest) {
		//permisos otorgados a usuario anonimo (sin rol)
		return httpServletRequest.getRequestURI().equals("") || httpServletRequest.getRequestURI().equals("/") || httpServletRequest.getRequestURI().equals("/Home") || httpServletRequest.getRequestURI().equals("/usuarios/accede") ||
				httpServletRequest.getRequestURI().equals("/usuarios/login") || httpServletRequest.getRequestURI().equals("/usuarios/persistir")
				|| httpServletRequest.getRequestURI().endsWith(".css") 
				 || httpServletRequest.getRequestURI().endsWith(".png")  || httpServletRequest.getRequestURI().endsWith(".jpg")
				 || httpServletRequest.getRequestURI().endsWith(".jpge")  || httpServletRequest.getRequestURI().endsWith(".js") 
				|| httpServletRequest.getRequestURI().endsWith(".woff2") || httpServletRequest.getRequestURI().endsWith(".ttf") 
				|| httpServletRequest.getRequestURI().equals("/usuarios/inserta") || httpServletRequest.getRequestURI().equals("/anuncios/Videojuegos") 
				|| httpServletRequest.getRequestURI().equals("/anuncios/Consolas") || httpServletRequest.getRequestURI().equals("/anuncios/PC_Gaming")
				|| httpServletRequest.getRequestURI().equals("/anuncios/Accesorios") || httpServletRequest.getRequestURI().equals("/anuncios/buscar") 
				|| httpServletRequest.getRequestURI().equals("/anuncios/seleccionar") || httpServletRequest.getRequestURI().equals("/usuarios/seleccionarU");
	}

	
}
