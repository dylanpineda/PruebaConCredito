package com.pruebaConCredito.Modelo;

import javax.persistence.Column;

public class Response {
	public int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Response(int value) {
		super();
		this.value = value;
	}

	public Response() {
		// TODO Auto-generated constructor stub
	}

}
