package com.proyecto.enums;

public enum StateEnum {

	EN_VENTA("En venta"),
	RESERVADO("Reservado"),
	VENDIDO("Vendido");
	
	private String estado;
	
	StateEnum(String estado) {
		this.estado = estado;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
}
