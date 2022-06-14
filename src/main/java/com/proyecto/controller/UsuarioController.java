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

import com.proyecto.Authorized;
import com.proyecto.Session;
import com.proyecto.enums.RolEnum;
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
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		
		return "usuarios/mostrar";
	}
	
	@RequestMapping("/inserta")
	public String inserta(Model modelo) {
		modelo.addAttribute("usuario", new UsuarioVO());
		
		return "usuarios/registro"; //formInserta o indexY
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
		return "usuarios/formModificar";
	}
	
	@RequestMapping("/seleccionar")
	public String seleccionar(@RequestParam int idUsuarios, Model modelo) {
		modelo.addAttribute("usuario", su.findById(idUsuarios).get());
		return "usuarios/seleccion";
	}
	
	@RequestMapping("/login")
	public String login(Model modelo) {
		modelo.addAttribute("usuario", new UsuarioVO());
		return "usuarios/login";
	}
	
	@RequestMapping("/accede")
	public String accede(@RequestParam String correo, String contrasena, Model modelo) {
		
		String redirect = "usuarios/login";
		
		if (!su.login(correo, contrasena)) {
			modelo.addAttribute("Error", "403");
		} else {
			redirect = (session.get(Session.ID_ROL) == RolEnum.ADMINISTRADOR.getRol()) ? "redirect:/Home/A" : "redirect:/Home/R";
		}
		
		
		return redirect;
	}
	
	@RequestMapping("/logout")
	public String logout(Model modelo) {
		su.logout();
		return "redirect:/Home";
	}
	
	
	//seleccionar usuario buscador anonimo
	@RequestMapping("/seleccionarU")
	public String seleccionarU(@RequestParam int idUsuarios, Model modelo) {
		modelo.addAttribute("usuario", su.findById(idUsuarios).get());
		modelo.addAttribute("anuncios", sa.findByIdUsuario(idUsuarios));
		return "usuAnonimo/seleccionU";
	}
	
	
	//seleccionar usuario buscador registrado
		@RequestMapping("/seleccionarUR")
		public String seleccionarUR(@RequestParam int idUsuarios, Model modelo) {
			modelo.addAttribute("usuario", su.findById(idUsuarios).get());
			modelo.addAttribute("anuncios", sa.findByIdUsuario(idUsuarios));
			return "usuRegistrado/seleccionU";
		}

	
	
	//Mostrar los anuncios de un usuario usuRegistrado
	@RequestMapping("/perfilR")
	public String perfilUsu(Model modelo) {
		modelo.addAttribute("anuncios", sa.findByUsuario());
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "usuRegistrado/perfil";
	}
	
	//MODIFICAR perfil usuarioRegistrado
		@RequestMapping("/modificarUsuR")
		@Authorized(roles = {Authorized.ADMIN, Authorized.REGISTRADO})
		public String modificarUsuP(Model modelo) {
			modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
			return "usuRegistrado/formModificarUsu";
		}
	
	@RequestMapping("/persistirR")
	public String persistirR (@ModelAttribute UsuarioVO usuario) {
		su.save(usuario);
		return "redirect:/usuarios/perfilR";
	}
	
	
	//USU ADMIN
	//seleccionar usuario buscador admin
			@RequestMapping("/seleccionarUA")
			public String seleccionarUA(@RequestParam int idUsuarios, Model modelo) {
				modelo.addAttribute("usuario", su.findById(idUsuarios).get());
				modelo.addAttribute("anuncios", sa.findByIdUsuario(idUsuarios));
				return "usuAdmin/seleccionU";
			}

		
		
		//Mostrar los anuncios de un usuario usuAdmin
		@RequestMapping("/perfilA")
		public String perfilUsuA(Model modelo) {
			modelo.addAttribute("anuncios", sa.findByUsuario());
			modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
			return "usuAdmin/perfil";
		}
		
		//MODIFICAR perfil usuAdmin
			@RequestMapping("/modificarUsuA")
			@Authorized(roles = {Authorized.ADMIN, Authorized.REGISTRADO})
			public String modificarUsuA(Model modelo) {
				modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
				return "usuAdmin/formModificarUsu";
			}
		
		@RequestMapping("/persistirA")
		public String persistirA (@ModelAttribute UsuarioVO usuario) {
			su.save(usuario);
			return "redirect:/usuarios/perfilA";
		}
		
	//CRUD ADMIN
		@RequestMapping("/insertaCrudA")
		public String insertaCrudA(Model modelo) {
			modelo.addAttribute("usuario", new UsuarioVO());
			
			return "usuarios/registro2"; //formInserta o indexY
		}
		
		@RequestMapping("/persistirCrudA")
		public String persistirCrudA (@ModelAttribute UsuarioVO usuario) {
			su.save(usuario);
			return "redirect:/usuarios/";
		}

			
		//MODIFICAR usu en crud Admin
				@RequestMapping("/modificarCrudA")
				@Authorized(roles = {Authorized.ADMIN, Authorized.REGISTRADO})
				public String modificarCrudA(@RequestParam int idUsuarios, Model modelo) {
					modelo.addAttribute("usuario", su.findById(idUsuarios).get());
					return "usuarios/formModificar";
				}
}
