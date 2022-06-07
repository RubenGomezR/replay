package com.proyecto.controller;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proyecto.Session;
import com.proyecto.modelo.UsuarioVO;
import com.proyecto.servicio.ServicioAnuncio;
import com.proyecto.servicio.ServicioUsuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	
	@Autowired
	ServicioUsuario su;
	@Autowired
	ServicioAnuncio sa;
	
	@Autowired
	private Session session;
	
	
	@RequestMapping("")
	public String mostrar(Model modelo) {
		modelo.addAttribute("usuarios", su.findAll());
		
		return "/usuarios/mostrar";
	}
	
	@RequestMapping("/inserta")
	public String inserta(Model modelo) {
		modelo.addAttribute("usuario", new UsuarioVO());
		
		return "/usuarios/registro"; //formInserta o indexY
	}
	
	
	@RequestMapping("/persistir")
	public String persistir (@ModelAttribute UsuarioVO usuario) {
		su.save(usuario);
		return "redirect:/usuarios/login";
	}
	
	
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam int idUsuarios) {
		su.deleteById(idUsuarios);
		return "redirect:/usuarios";
	}
	
	
	@RequestMapping("/modificar")
	public String modificar(@RequestParam int idUsuarios, Model modelo) {
		modelo.addAttribute("usuario", su.findById(idUsuarios).get());
		return "/usuarios/formModificar";
	}
	
	@RequestMapping("/seleccionar")
	public String seleccionar(@RequestParam int idUsuarios, Model modelo) {
		modelo.addAttribute("usuario", su.findById(idUsuarios).get());
		return "/usuarios/seleccion";
	}
	
	@RequestMapping("/login")
	public String login(Model modelo) {
		modelo.addAttribute("usuario", new UsuarioVO());
		return "/usuarios/login";
	}
	
	@RequestMapping("/accede")
	public String accede(@RequestParam String correo, String contrasena, Model modelo) {
		
		if (!su.login(correo, contrasena)) {
			modelo.addAttribute("Error", "403");
		}
		
		return "redirect:/Home/R";
	}
	
	@RequestMapping("/logout")
	public String logout(Model modelo) {
		su.logout();
		return "redirect:/Home";
	}

	
	
	//Mostrar los anuncios de un usuario usuRegistrado
	@RequestMapping("/perfilR")
	public String perfilUsu(Model modelo) {
		modelo.addAttribute("anuncios", sa.findByUsuario());
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "/usuRegistrado/perfil";
	}
	
	//Modificar datos usuario Registrado
	@RequestMapping("/modificarR")
	public String modificarR(@RequestParam int idUsuarios, Model modelo) {
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "/usuRegistrado/formModificar";
	}
	
	@RequestMapping("/persistirR")
	public String persistirR (@ModelAttribute UsuarioVO usuario) {
		su.save(usuario);
		return "/usuRegistrado/perfil";
	}
}
