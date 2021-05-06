package com.pruebaConCredito.Controlador;

import java.awt.PageAttributes.MediaType;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.pruebaConCredito.Modelo.Documento;
import com.pruebaConCredito.Modelo.Persona;
import com.pruebaConCredito.Modelo.Response;
import com.pruebaConCredito.Servicio.PersonaService;

import net.bytebuddy.implementation.bytecode.Throw;

@RestController
@RequestMapping("/personas")
public class PersonaControler {
	
	@Autowired
	private PersonaService service;
	
	
	@GetMapping("/listar")
	public List<Map<String, Object>> listar(Model model) {
		return service.listar();
	}
	
	@GetMapping("/listarDocs/{id}")
	public List<Map<String, Object>> getDocs(@PathVariable int id,Model model) {
		return service.listarId(id);	
	}
	
	@PostMapping("/setDocumentos")
	public int saveDocs(@RequestBody Documento d,Model model) {
		return service.setDocumentos(d);	
	}
	
		
	@GetMapping("/getMaxID")
	public Response get(Model model) {
		int r;
		r =service.getMax();
		Response response = new Response();
		response.setValue(r);
		return response;
	}
	
	@PostMapping("/agregar")
	public Response save(@RequestBody Persona p,Model model) {
		Response response= new Response();
		int r;
		try {
			r = service.add(p);
			//System.out.println(r);
		}catch(Exception e) {
			r=0;
			System.out.println(e.toString());
		}
		response.setValue(r);
		return response;
	}
	
	@PostMapping("/actualizar/{id}")
	public int save(@RequestBody Persona p,@PathVariable int id,Model model) {
		p.setId(id);
		return service.edit(p);	
	}
	
	@PostMapping("/eliminar/{id}")
	public Response delete(@PathVariable int id,Model model) {
		int r;
		r=service.delete(id);
		Response response= new Response();
		response.setValue(r);
		return response;
	}
	@PostMapping("/eliminarDoc/{id}")
	public Response deleteDoc(@PathVariable int id,Model model) {
		int r;
		r=service.deleteDoc(id);
		Response response= new Response();
		response.setValue(r);
		return response;
	}	
}
