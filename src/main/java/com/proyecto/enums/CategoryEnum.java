package com.proyecto.enums;

public enum CategoryEnum {

	PC_GAMING("Pc gaming"),
	VIDEOJUEGOS("Videojuegos"),
	CONSOLAS("Consolas"),
	ACCESORIOS("Accesorios");
	
	private String categoria;

	CategoryEnum(String categoria) {
		this.categoria = categoria;
	} 
	
	public String getcategoria() {
		return this.categoria;
	}
	
}
