package com.pruebaConCredito.Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "documentos")
@Entity
public class Documento{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public int idDocumento;
	@Column
	public int id;
	@Column(name = "documento", length = 100000000)
	public byte[] documento;
	@Column
	public String nombre;
	public Documento(int idDocumento, int id, byte[] documento, String nombre) {
		super();
		this.idDocumento = idDocumento;
		this.id = id;
		this.documento = documento;
		this.nombre = nombre;
	}
	public byte[] getDocumento() {
		return documento;
	}
	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}
	public Documento() {
		
	}
	public int getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}