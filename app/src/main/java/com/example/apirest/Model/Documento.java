package com.example.apirest.Model;

public class Documento {
    private int idDocumento;
    private String nombre;
    private int id;
    private String documento;

    public Documento() {
    }

    public Documento(int idDocumento, String nombre, int id, String documento) {
        this.idDocumento=idDocumento;
        this.nombre = nombre;
        this.id = id;
        this.documento = documento;

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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}

