package com.pruebaConCredito.ModeloDAO;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pruebaConCredito.Interfaces.PersonaInterface;
import com.pruebaConCredito.Modelo.Documento;
import com.pruebaConCredito.Modelo.Persona;
import com.pruebaConCredito.Modelo.Response;

@Repository
public class PersonaDAO implements PersonaInterface {

	@Autowired
	JdbcTemplate template;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Map<String, Object>> listar() {
		List<Map<String, Object>> list = template.queryForList("select * from persona where status=1");
		return list;
	}

	@Override
	public List<Map<String, Object>> listarId(int id) {
		List<Map<String, Object>> list = template.queryForList("select * from documentos where id =?", id);
		return list;
	}

	@Override
	public int add(Persona p) {
		String sql = "insert into persona(nombre,apPaterno,apMaterno,calle,colonia,numero,cp,telefono,rfc,statusProspecto)values(?,?,?,?,?,?,?,?,?,?)";
		
		return	template.update(sql, p.getNombre(), p.getApPaterno(), p.getApMaterno(), p.getCalle(), p.getColonia(), p.getNumero(), p.getCp(), p.getTelefono(),p.getRfc(),p.getStatusProspecto());
	}
	
	@Override
	public int getMax() {
		String sql="select MAX(id) from persona";
		if(template.queryForObject(sql, int.class)==null) {
			return 0;
		}
		int r;
		r=template.queryForObject(sql, int.class);
		return  r;
	}

	@Override
	public int edit(Persona p) {
		String sql="update persona set nombre=?, apPaterno=?, apMaterno=?, calle=?, colonia=?, numero=?, cp=?, telefono=?, rfc=?, statusProspecto=?, observaciones=? where id=?";		
		return template.update(sql,p.getNombre(),p.getApPaterno(),p.getApMaterno(), p.getCalle(), p.getColonia(), p.getNumero(), p.getCp(), p.getTelefono(), p.getRfc(), p.getStatusProspecto(),p.getObservaciones(), p.getId());
	}
	@Override
	public int setDocumentos(Documento d) {
		String sql="insert into documentos(id, documento, nombre) values (?,?,?) ";		
		return template.update(sql,d.getId(), d.getDocumento(), d.getNombre());
	}


	@Override
	public int delete(int id) {
		String sql="update persona set status=0 where id=?";
		return template.update(sql,id);
	}
	@Override
	public int deleteDoc(int id) {
		String sql="delete from documentos where idDocumento=?";
		return template.update(sql,id);
	}

}
