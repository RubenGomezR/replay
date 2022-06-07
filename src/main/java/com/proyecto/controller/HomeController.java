package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.Authorized;
import com.proyecto.Session;
import com.proyecto.servicio.ServicioAnuncio;
import com.proyecto.servicio.ServicioChat;
import com.proyecto.servicio.ServicioComentario;
import com.proyecto.servicio.ServicioUsuario;

@Controller
@RequestMapping("/Home")
public class HomeController {

	@Autowired
	ServicioAnuncio sa;
	@Autowired
	ServicioChat sc;
	@Autowired
	ServicioComentario sco;
	@Autowired
	ServicioUsuario su;
	
	
	@Autowired
	private Session session;
	
	@RequestMapping("")
	public String mostrar(Model modelo) {
		modelo.addAttribute("anuncios", sa.findAll());
		
		return "usuAnonimo/pagina";
	}
	
	@RequestMapping("/R")
	@Authorized(roles = {Authorized.REGISTRADO})
	public String mostrarR(Model modelo) {
		modelo.addAttribute("anuncios", sa.findAllAnunciosAjenos()); 
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		//no mostramos los anuncios del usuario cnoectado
		
		return "usuRegistrado/pagina";
	}
	
	@RequestMapping("/A")
	@Authorized(roles = {Authorized.ADMIN})
	public String mostrarA(Model modelo) {
		modelo.addAttribute("anuncios", sa.findAllAnunciosAjenos()); 
		//no mostramos los anuncios del usuario cnoectado
		
		return "usuAdmin/pagina";
	}
	
}
