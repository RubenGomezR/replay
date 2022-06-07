package com.proyecto.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="anunciosusuarios")
public class AnuncioUsuarioVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAnunciosUsuarios;
	
	@ManyToOne
	private AnuncioVO anuncio;
	
	@ManyToOne
	private UsuarioVO usuario;
}
