package com.example.apirest.Model;

import android.telecom.Call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Persona {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("nombre")
    @Expose
    public String nombre;

    @SerializedName("apPaterno")
    @Expose
    public String apPaterno;

    @SerializedName("apMaterno")
    @Expose
    public String apMaterno;

    @SerializedName("calle")
    @Expose
    public String calle;

    @SerializedName("colonia")
    @Expose
    public String colonia;

    @SerializedName("numero")
    @Expose
    public String numero;

    @SerializedName("cp")
    @Expose
    public String cp;

    @SerializedName("telefono")
    @Expose
    public String telefono;

    @SerializedName("rfc")
    @Expose
    public String rfc;

    @SerializedName("statusProspecto")
    @Expose
    public String statusProspecto;
    @SerializedName("observaciones")
    @Expose
    public String observaciones;


    public  Persona(){
    }
    public Persona(int id, String nombres, String apPaterno, String apMaterno, String calle, String numero, String colonia, String cp, String telefono, String rfc, String statusProspecto, String observaciones ) {
        this.id = id;
        this.nombre = nombres;
        this.apPaterno = apPaterno;
        this.apMaterno=apMaterno;
        this.calle= calle;
        this.numero=numero;
        this.colonia= colonia;
        this.cp=cp;
        this.telefono=telefono;
        this.rfc=rfc;
        this.statusProspecto=statusProspecto;
        this.observaciones=observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getStatusProspecto() {
        return statusProspecto;
    }

    public void setStatusProspecto(String statusProspecto) {
        this.statusProspecto = statusProspecto;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
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

}
