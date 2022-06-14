package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.Authorized;
import com.proyecto.Session;
import com.proyecto.enums.CategoryEnum;
import com.proyecto.enums.StateEnum;
import com.proyecto.modelo.AnuncioUsuarioVO;
import com.proyecto.modelo.AnuncioVO;
import com.proyecto.modelo.ComentarioVO;
import com.proyecto.servicio.ServicioAnuncio;
import com.proyecto.servicio.ServicioAnuncioUsuario;
import com.proyecto.servicio.ServicioChat;
import com.proyecto.servicio.ServicioComentario;
import com.proyecto.servicio.ServicioUsuario;

@Controller
@RequestMapping("/anuncios")
public class AnuncioController {

	@Autowired
	ServicioAnuncio sa;
	
	@Autowired
	ServicioAnuncioUsuario sau;
	
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
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		
		return "anuncios/mostrar";
	}
	
	
	@RequestMapping("/inserta")
	@Authorized(roles = {Authorized.ADMIN, Authorized.REGISTRADO})
	public String inserta(Model modelo) {
		modelo.addAttribute("anuncio", new AnuncioVO());
		
		return "anuncios/formInserta";
	}
	
	
	@RequestMapping("/persistir")
	@Authorized(roles = {Authorized.ADMIN, Authorized.REGISTRADO})
	public String persistir (@RequestParam("file") MultipartFile file, @ModelAttribute AnuncioVO anuncio) {
		sa.save(anuncio, file);
		return "redirect:/anuncios";
	}
	
	
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam int idProductos) {
		sa.deleteById(idProductos);
		return "redirect:/anuncios";
	}
	
	
	@RequestMapping("/modificar")
	public String modificar(@RequestParam int idProductos, Model modelo) {
		modelo.addAttribute("anuncio", sa.findById(idProductos));
		return "anuncios/formModificar";
	}
	
	
	
	/*USUANONIMO*/
	/* seleccionar usuAnonimo */
	@RequestMapping("/seleccionar")
	public String seleccionar(@RequestParam int idProductos, Model modelo) {
		modelo.addAttribute("anuncio", sa.findById(idProductos));
		modelo.addAttribute("comentarios", sco.getByIdAnuncio(idProductos));
		return "usuAnonimo/seleccion";
		}
	
	/*buscar usuAnonimo*/
	@GetMapping("/buscar")
	public String buscar2(@RequestParam String query, Model modelo) {
		modelo.addAttribute("anuncios", sa.findByAnuncio(query));
		modelo.addAttribute("usuarios", su.findByNombre(query));
		return "usuAnonimo/buscar2";
		}
	
	
	/*categorias usuAnonimo*/
	//Les pasamos las constantes declaradas en CategoryEnum
	
	@RequestMapping("/Videojuegos")
	public String buscarCategoriaV(Model modelo) {
		modelo.addAttribute("anuncios", sa.findByCategoria(CategoryEnum.VIDEOJUEGOS.getcategoria()));
		return "usuAnonimo/paginaCategoriaEspc";
		}
	
	@RequestMapping("/Consolas")
	public String buscarCategoriaC(Model modelo) {
		modelo.addAttribute("anuncios", sa.findByCategoria(CategoryEnum.CONSOLAS.getcategoria()));
		return "usuAnonimo/paginaCategoriaEspc";
		}
	
	@RequestMapping("/PC_Gaming")
	public String buscarCategoriaP(Model modelo) {
		modelo.addAttribute("anuncios", sa.findByCategoria(CategoryEnum.PC_GAMING.getcategoria()));
		return "usuAnonimo/paginaCategoriaEspc";
		}
	
	@RequestMapping("/Accesorios")
	public String buscarCategoriaA(Model modelo) {
		modelo.addAttribute("anuncios", sa.findByCategoria(CategoryEnum.ACCESORIOS.getcategoria()));
		return "usuAnonimo/paginaCategoriaEspc";
		}
	
	/*USUREGISTRADO*/
	/* seleccionar usuRegistrado */
	@RequestMapping("/seleccionarR")
	@Authorized(roles = {Authorized.REGISTRADO})
	public String seleccionarR(@RequestParam int idProductos, Model modelo) {
		modelo.addAttribute("anuncio", sa.findById(idProductos));
		modelo.addAttribute("comentarios", sco.getByIdAnuncio(idProductos));
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "usuRegistrado/seleccion";
		}
	
	/*buscar usuRegistrado*/
	@GetMapping("/buscarR")
	@Authorized(roles = {Authorized.REGISTRADO})
	public String buscar2R(@RequestParam String query, Model modelo) {
		modelo.addAttribute("anuncios", sa.findByAnuncio(query));
		modelo.addAttribute("usuarios", su.findByNombre(query));
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "usuRegistrado/buscar2";
		}
	
	
	/*categorias usuRegistrado*/
	//Les pasamos las constantes declaradas en CategoryEnum
	
	@RequestMapping("/VideojuegosR")
	@Authorized(roles = {Authorized.REGISTRADO})
	public String buscarCategoriaVR(Model modelo) {
		modelo.addAttribute("anuncios", sa.findByCategoria(CategoryEnum.VIDEOJUEGOS.getcategoria()));
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "usuRegistrado/paginaCategoriaEspc";
		}
	
	@RequestMapping("/ConsolasR")
	@Authorized(roles = {Authorized.REGISTRADO})
	public String buscarCategoriaCR(Model modelo) {
		modelo.addAttribute("anuncios", sa.findByCategoria(CategoryEnum.CONSOLAS.getcategoria()));
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "usuRegistrado/paginaCategoriaEspc";
		}
	
	@RequestMapping("/PC GamingR")
	@Authorized(roles = {Authorized.REGISTRADO})
	public String buscarCategoriaPR(Model modelo) {
		modelo.addAttribute("anuncios", sa.findByCategoria(CategoryEnum.PC_GAMING.getcategoria()));
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "usuRegistrado/paginaCategoriaEspc";
		}
	
	@RequestMapping("/AccesoriosR")
	@Authorized(roles = {Authorized.REGISTRADO})
	public String buscarCategoriaAR(Model modelo) {
		modelo.addAttribute("anuncios", sa.findByCategoria(CategoryEnum.ACCESORIOS.getcategoria()));
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "usuRegistrado/paginaCategoriaEspc";
		}
	
	//INSERTAR anuncio usuarioRegistrado
	@RequestMapping("/insertaR")
	@Authorized(roles = {Authorized.REGISTRADO})
	public String insertaR(Model modelo) {
		modelo.addAttribute("anuncio", new AnuncioVO());
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		
		return "usuRegistrado/formInserta";
	}
	
	//MODIFICAR anuncio usuarioRegistrado
	@RequestMapping("/modificarR")
	@Authorized(roles = {Authorized.REGISTRADO})
	public String modificarP(@RequestParam int idProductos, Model modelo) {
		modelo.addAttribute("anuncio", sa.findById(idProductos));
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "usuRegistrado/formModificar";
	}
	
	
	
	
	
	@RequestMapping("/persistirR")
	@Authorized(roles = {Authorized.REGISTRADO})
	public String persistirP (@RequestParam("file") MultipartFile file, @ModelAttribute AnuncioVO anuncio) {
		sa.save(anuncio, file);
		return "redirect:/usuarios/perfilR";
	}
	
	//ELIMINAR usuarioRegistrado
	@RequestMapping("/eliminarR")
	public String eliminarP(@RequestParam int idProductos) {
		sa.deleteById(idProductos);
		return "redirect:/usuarios/perfilR";
	}
	
	
	//AÑADIR A LIKES usuarioRegistrado
	@RequestMapping("/like")
	@Authorized(roles = {Authorized.REGISTRADO})
	public String like(@RequestParam int idProductos, Model model) {
		sau.saveLike(idProductos);
		return "redirect:/Home/R";
	}
	
	//Ir PaginaLikes usuarioRegistrado
	@RequestMapping("/paginaLikesR")
	@Authorized(roles = {Authorized.REGISTRADO})
	public String paginaLikesR(Model modelo) {
		modelo.addAttribute("anuncios", sa.findAllLikesR());
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "usuRegistrado/paginaLikesR";
	}
	
	
	
	//USU ADMIN
	/*categorias usuRegistrado*/
	//Les pasamos las constantes declaradas en CategoryEnum
	
	@RequestMapping("/VideojuegosA")
	@Authorized(roles = {Authorized.ADMIN})
	public String buscarCategoriaVA(Model modelo) {
		modelo.addAttribute("anuncios", sa.findByCategoria(CategoryEnum.VIDEOJUEGOS.getcategoria()));
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "usuAdmin/paginaCategoriaEspc";
		}
	
	@RequestMapping("/ConsolasA")
	@Authorized(roles = {Authorized.ADMIN})
	public String buscarCategoriaCA(Model modelo) {
		modelo.addAttribute("anuncios", sa.findByCategoria(CategoryEnum.CONSOLAS.getcategoria()));
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "usuAdmin/paginaCategoriaEspc";
		}
	
	@RequestMapping("/PC GamingA")
	@Authorized(roles = {Authorized.ADMIN})
	public String buscarCategoriaPA(Model modelo) {
		modelo.addAttribute("anuncios", sa.findByCategoria(CategoryEnum.PC_GAMING.getcategoria()));
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "usuAdmin/paginaCategoriaEspc";
		}
	
	@RequestMapping("/AccesoriosA")
	@Authorized(roles = {Authorized.ADMIN})
	public String buscarCategoriaAA(Model modelo) {
		modelo.addAttribute("anuncios", sa.findByCategoria(CategoryEnum.ACCESORIOS.getcategoria()));
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "usuAdmin/paginaCategoriaEspc";
		}
	
	/*buscar usuAdmin*/
	@GetMapping("/buscarA")
	@Authorized(roles = {Authorized.ADMIN})
	public String buscar2A(@RequestParam String query, Model modelo) {
		modelo.addAttribute("anuncios", sa.findByAnuncio(query));
		modelo.addAttribute("usuarios", su.findByNombre(query));
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "usuAdmin/buscar2";
		}
	
	/* seleccionar usuAdmin*/
	@RequestMapping("/seleccionarA")
	@Authorized(roles = {Authorized.ADMIN})
	public String seleccionarA(@RequestParam int idProductos, Model modelo) {
		modelo.addAttribute("anuncio", sa.findById(idProductos));
		modelo.addAttribute("comentarios", sco.getByIdAnuncio(idProductos));
		modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
		return "usuAdmin/seleccion";
		}
	
	
	//Ir PaginaLikes usuAdmin
		@RequestMapping("/paginaLikesA")
		@Authorized(roles = {Authorized.ADMIN})
		public String paginaLikesA(Model modelo) {
			modelo.addAttribute("anuncios", sa.findAllLikesR());
			modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
			return "usuAdmin/paginaLikesR";
		}
		
		//INSERTAR anuncio usuAdmin
		@RequestMapping("/insertaA")
		@Authorized(roles = {Authorized.ADMIN})
		public String insertaA(Model modelo) {
			modelo.addAttribute("anuncio", new AnuncioVO());
			modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
			
			return "usuAdmin/formInserta";
		}
		
		//MODIFICAR anuncio usuAdmin
		@RequestMapping("/modificarA")
		@Authorized(roles = {Authorized.ADMIN})
		public String modificarA(@RequestParam int idProductos, Model modelo) {
			modelo.addAttribute("anuncio", sa.findById(idProductos));
			modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
			return "usuAdmin/formModificar";
		}
		
		
		
		@RequestMapping("/persistirA")
		@Authorized(roles = {Authorized.ADMIN})
		public String persistirPA (@RequestParam("file") MultipartFile file, @ModelAttribute AnuncioVO anuncio) {
			sa.save(anuncio, file);
			return "redirect:/usuarios/perfilA";
		}
		
		//ELIMINAR usuarioRegistrado
		@RequestMapping("/eliminarA")
		public String eliminarPA(@RequestParam int idProductos) {
			sa.deleteById(idProductos);
			return "redirect:/usuarios/perfilA";
		}
		
		
		//AÑADIR A LIKES usuarioRegistrado
		@RequestMapping("/likeA")
		@Authorized(roles = {Authorized.ADMIN})
		public String likeA(@RequestParam int idProductos, Model model) {
			sau.saveLike(idProductos);
			return "redirect:/Home/A";
		}
		
		
		//crud

		//añadir
		//INSERTAR 
		@RequestMapping("/insertaCrudA")
		@Authorized(roles = {Authorized.ADMIN})
		public String insertaCrudA(Model modelo) {
			modelo.addAttribute("anuncio", new AnuncioVO());
			modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
					return "anuncios/formInserta2";
		}
		
		//MODIFICAR anuncio usuarioRegistrado
		@RequestMapping("/modificarCrudA")
		@Authorized(roles = {Authorized.ADMIN})
		public String modificarCrudA(@RequestParam int idProductos, Model modelo) {
			modelo.addAttribute("anuncio", sa.findById(idProductos));
			modelo.addAttribute("usuario", su.findById(session.getUserLoggedId()).get());
			return "anuncios/formModificar2";
		}
		
		//persistir
		@RequestMapping("/persistirCrudA")
		@Authorized(roles = {Authorized.ADMIN})
		public String persistirCrudA (@RequestParam("file") MultipartFile file, @ModelAttribute AnuncioVO anuncio) {
			sa.save(anuncio, file);
			return "redirect:/anuncios";
		}
}
