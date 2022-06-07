package com.proyecto.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="anuncios")
public class AnuncioVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProductos;
	@Column(length=45)
	private String nombre;
	private int precio;
	@Column(length=10)
	private String estado;
	@Column(length=200)
	private String descripcion;
	private String categoria;
	private String urlImg;
	
	@ManyToOne
	private UsuarioVO usuario;
	
	
	@OneToMany(mappedBy="anuncio")
	private List<ChatVO> chats;
	
	@OneToMany(mappedBy="anuncio")
	private List<ComentarioVO> comentarios;
	
	@OneToMany(mappedBy="anuncio")
	private List<AnuncioUsuarioVO> likes;
	
	
}
