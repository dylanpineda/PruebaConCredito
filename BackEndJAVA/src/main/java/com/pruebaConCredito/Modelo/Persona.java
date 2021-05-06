package com.pruebaConCredito.Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "Persona")
@Entity
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public int id;
	@Column
	public String nombre;
	@Column
	public String apPaterno;
	@Column
	public String apMaterno;
	@Column
	public String calle;
	@Column
	public String colonia;
	@Column
	public String numero;
	@Column
	public String cp;
	@Column
	public String telefono;
	@Column
	public String rfc;
	@Column
	public String statusProspecto;
	@Column
	public String observaciones;
	
	public Persona() {
		super();
	}
	public Persona(int id, String nombre, String apPaterno, String apMaterno, String calle, String colonia,
			String numero, String cp, String telefono, String rfc, String statusProspecto, String observaciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.calle = calle;
		this.colonia = colonia;
		this.numero = numero;
		this.cp = cp;
		this.telefono = telefono;
		this.rfc = rfc;
		this.statusProspecto = statusProspecto;
		this.observaciones = observaciones;
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
	public String getApPaterno() {
		return apPaterno;
	}
	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}
	public String getApMaterno() {
		return apMaterno;
	}
	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getStatusProspecto() {
		return statusProspecto;
	}
	public void setStatusProspecto(String statusProspecto) {
		this.statusProspecto = statusProspecto;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}

