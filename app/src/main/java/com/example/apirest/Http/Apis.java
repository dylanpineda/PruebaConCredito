package com.example.apirest.Http;

import com.example.apirest.Utils.PersonaService;

public class Apis {

    public static final String URL_001="http://192.168.0.8:8080/personas/";

    public static PersonaService getPersonaService(){
        return  Cliente.getCliente(URL_001).create(PersonaService.class);
    }
}
