package com.proyecto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*Clase para manejar las sesiones en la aplicación.
 */
@Component
public class Session {

    public static final String IS_LOGGED = "isLogged";
    public static final String ID_USER = "userId";
    public static final String USER = "username";
    public static final String ID_ROL = "rolId";
    public static final String TIPO_ROL = "rolTipo";
    
    @Autowired
    private HttpServletRequest httpRequestServlet;
    
   
     //Tiempo máximo de inactividad en la session en segundos
     
    private int maxInactiveInterval;

    
     // Contructor.
     
    public Session() {
        maxInactiveInterval = 3600;
    }

    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    /*
      Función para guardar un objeto en el mapa de la sesion
     
      @param key | Clave del objeto en el mapa de la sesión.
      @param value | Objeto a guardar en el el mapa de la sesion.
     */
    public void add(String key, Object value) {
        HttpSession session = (HttpSession) httpRequestServlet.getSession(true);
        session.setAttribute(key, value);
    }

   
    /*
      Función que devuelve un objeto del mapa de la sesión pasando una clave
      por parametros.
    
      @param key | Clave del objeto a devolver.
      @return Object | Objeto almacenado en el mapa de la sesión.
     */
    public Object get(String key) {
        HttpSession session = (HttpSession) httpRequestServlet.getSession(true);
        return session.getAttribute(key);
    }

    /*
      Guarda en session el atributo que confirma que el usuario está logueado.
     */
    public void login() {
        add(IS_LOGGED, true);
    }

    /*
      Comprueba si el usuario esta logueado o no.
     
      @return Boolean | True si esta logueado y False si no esta logueado.
     */
    public boolean isLogged() {
    	Object isLogged = get(IS_LOGGED);
    	if (isLogged == null) {
    		return false;
    	}
        return Boolean.valueOf(String.valueOf(get(IS_LOGGED)));
    }
    
    /*
      Devuelve el id del usuario que esta loggeado
     
      @return Integer | Id del usuario logueado. -1 Si el usuario no esta logueado.
     */
    public Integer getUserLoggedId() {
    	Object isLogged = get(IS_LOGGED);
    	if (isLogged == null) {
    		return -1;
    	}
        return (Integer) get(Session.ID_USER);
    }

    /*
      Destruye el objeto HttpSession.
     */
    public void logout() {
        HttpSession session = (HttpSession) httpRequestServlet.getSession(true);
        add(Session.IS_LOGGED, false);
        session.invalidate();
    }

}