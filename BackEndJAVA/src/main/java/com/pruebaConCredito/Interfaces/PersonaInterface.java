package com.pruebaConCredito.Interfaces;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.pruebaConCredito.Modelo.Documento;
import com.pruebaConCredito.Modelo.Persona;

public interface PersonaInterface{
	public List<Map<String, Object>>listar();
	public int add(Persona p);
	public int edit(Persona p);
	public int delete(int id);
	public int deleteDoc(int id);
	public int getMax();
	public int setDocumentos(Documento d);
	public List<Map<String, Object>> listarId(int id);

}
