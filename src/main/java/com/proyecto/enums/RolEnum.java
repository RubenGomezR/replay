package com.proyecto.enums;

public enum RolEnum {

	REGISTRADO(2),
	ADMINISTRADOR(1);
	
	private Integer rol;
	
	RolEnum(Integer rol) {
		this.rol = rol;
	}
	
	public Integer getRol() {
		return this.rol;
	}
	
}
