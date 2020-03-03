package com.bookverse.development.packapps.models;

public class User {

    private String nombre, password;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(String user, String pass) {
        setPassword(pass);
        setNombre(user);
    }
}