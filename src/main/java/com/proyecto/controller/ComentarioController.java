package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.Authorized;
import com.proyecto.Session;
import com.proyecto.modelo.AnuncioVO;
import com.proyecto.modelo.ComentarioVO;
import com.proyecto.modelo.UsuarioVO;
import com.proyecto.servicio.ServicioComentario;
import com.proyecto.servicio.ServicioUsuario;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {

	@Autowired
	ServicioComentario sco;
	@Autowired
	ServicioUsuario su;
	
	@Autowired
	private Session session;
	
	
	@RequestMapping("")
	public String mostrar(Model modelo) {
		modelo.addAttribute("comentarios", sco.findAll());
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		
		return "comentarios/mostrar";
	}
	
	@RequestMapping("/inserta")
	public String inserta(Model modelo) {
		modelo.addAttribute("comentario", new ComentarioVO());
		
		return "comentarios/formInserta";
	}
	
	
	@RequestMapping("/persistir")
	public String persistir (@ModelAttribute ComentarioVO comentario) {
		sco.save(comentario);
		return "redirect:/comentarios";
	}
	
	
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam int idComentarios) {
		sco.deleteById(idComentarios);
		return "redirect:/comentarios";
	}
	
	
	@RequestMapping("/modificar")
	public String modificar(@RequestParam int idComentarios, Model modelo) {
		modelo.addAttribute("comentario", sco.findById(idComentarios).get());
		return "comentarios/formModificar";
	}
	
//INSERTAR anuncio usuarioRegistrado
	@RequestMapping("/insertarR")
	@Authorized(roles = {Authorized.ADMIN, Authorized.REGISTRADO})
	public String insertaR(@ModelAttribute ComentarioVO comentario, @RequestParam int idProductos) {
		sco.save(comentario, idProductos);
		return "redirect:/anuncios/seleccionarR?idProductos=" + idProductos;
	}

	//INSERTAR anuncio usuAdmin
		@RequestMapping("/insertarA")
		@Authorized(roles = {Authorized.ADMIN, Authorized.REGISTRADO})
		public String insertaA(@ModelAttribute ComentarioVO comentario, @RequestParam int idProductos) {
			sco.save(comentario, idProductos);
			return "redirect:/anuncios/seleccionarA?idProductos=" + idProductos;
		}	
	
}
