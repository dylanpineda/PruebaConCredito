package com.example.apirest.Utils;

import com.example.apirest.Model.Documento;
import com.example.apirest.Model.Persona;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PersonaService {
    @GET("listar")
    Call<List<Persona>> getPersonas();

    @GET("listarDocs/{id}")
    Call<List<Documento>>getDocs(@Path("id") int id);

    @GET("getMaxID")
    Call<String>getMax();

    @POST("setDocumentos")
    Call<String>setDocumento(@Body Documento documento);

    @POST("agregar")
    Call<String>addPersona(@Body Persona persona);

    @POST("actualizar/{id}")
    Call<Persona>updatePersona(@Body Persona persona,@Path("id") int id);

    @POST("eliminar/{id}")
    Call<String>deletePersona(@Path("id")int id);

    @POST("eliminarDoc/{id}")
    Call<String>deleteDoc(@Path("id")int id);

}
