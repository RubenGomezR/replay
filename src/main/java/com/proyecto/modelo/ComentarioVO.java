package com.proyecto.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="comentarios")
public class ComentarioVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idComentarios;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fecha;

	private String texto;
	
	@ManyToOne
	private AnuncioVO anuncio;
	
	@ManyToOne
	private UsuarioVO usuario;
	
}
