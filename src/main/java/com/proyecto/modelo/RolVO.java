package com.proyecto.modelo;

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
@Table(name="roles")
public class RolVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRoles;
	private String tipo;
	
	/*@ManyToOne
	private UsuarioVO usuario; */
	
}
