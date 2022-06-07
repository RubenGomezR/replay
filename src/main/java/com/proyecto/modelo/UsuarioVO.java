package com.proyecto.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="usuarios")
public class UsuarioVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsuarios;
	@Column(length=45)
	private String nombre;
	@Column(length=45,unique=true)
	private String correo;
	@Column(length= 9)
	private String telefono;
	
	private String contraseña;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fechaCreacion;
	
	@OneToMany(mappedBy="usuario")
	private List<AnuncioVO> anuncios;
	
	/*@OneToMany(mappedBy="usuario")
	private List<RolVO> roles; */
	
	@OneToOne
	@JoinColumn(name = "id_roles")
	private RolVO rol;
	
	@OneToMany(mappedBy="usuario")
	private List<AnuncioUsuarioVO> likes;
	
	@OneToMany(mappedBy="usuario")
	private List<ComentarioVO> comentarios;
	
}
