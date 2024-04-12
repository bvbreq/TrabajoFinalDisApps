package com.dam.practicapersistencia;

public class Salas {

    private int sala;
    private String nombre;
    private String mensaje;


    public Salas(int sala, String nombre, String mensaje) {
        this.sala = sala;
        this.nombre = nombre;
        this.mensaje = mensaje;
    }


    public int getSALA() {
        return sala;
    }

    public void setSALA(int sala) {
        this.sala = sala;
    }


    public String getNOMBRE() {
        return nombre;
    }

    public void setNOMBRE(String nombre) {
        this.nombre = nombre;
    }


    public String getMENSAJE() {
        return mensaje;
    }

    public void setMENSAJE(String mensaje) {
        this.mensaje = mensaje;
    }
}


