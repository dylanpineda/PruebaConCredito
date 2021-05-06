package com.pruebaConCredito.Servicio;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebaConCredito.Interfaces.PersonaInterface;
import com.pruebaConCredito.Modelo.Documento;
import com.pruebaConCredito.Modelo.Persona;
import com.pruebaConCredito.ModeloDAO.PersonaDAO;

@Service
public class PersonaService implements PersonaInterface {

	@Autowired
	PersonaDAO dao;
	
	@Override
	public List<Map<String, Object>> listar() {		
		return dao.listar();
	}
	@Override
	public int getMax() {		
		return dao.getMax();
	}

	@Override
	public List<Map<String, Object>> listarId(int id) {
		return dao.listarId(id);
	}

	@Override
	public int add(Persona p) {		
		return dao.add(p);
	}
	@Override
	public int setDocumentos(Documento d) {
		// TODO Auto-generated method stub
		return dao.setDocumentos(d);
	}


	@Override
	public int edit(Persona p) {
		// TODO Auto-generated method stub
		return dao.edit(p);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		
		return dao.delete(id);
	}

	@Override
	public int deleteDoc(int id) {
		// TODO Auto-generated method stub
		
		return dao.deleteDoc(id);
	}


}
