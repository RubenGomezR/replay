package com.proyecto.modelo;

import javax.persistence.Column;
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
@Table(name="chats")
public class ChatVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idChats;
	@Column(length=50)
	private String mensajes;
	
	@ManyToOne
	private AnuncioVO anuncio;
	
}
