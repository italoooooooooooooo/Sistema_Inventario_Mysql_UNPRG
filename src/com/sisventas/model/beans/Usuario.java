package com.sisventas.model.beans;

public class Usuario {

    private int idUsuario;
    private String username;
    private String pasword;
    private String nombre;
    private String correo;
    private String rol;

    public Usuario() {
    }

    public Usuario(int idUsuario, String username, String pasword, String nombre, String correo, String rol) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.pasword = pasword;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    
}
