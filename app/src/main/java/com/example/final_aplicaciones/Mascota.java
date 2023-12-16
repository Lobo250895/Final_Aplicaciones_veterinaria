package com.example.final_aplicaciones;

public class Mascota {
    private long id;
    private String nombre;
    private String sexo;
    private String raza;
    private String fechaNacimiento;

    public Mascota() {

    }

    public Mascota(long id, String nombre, String sexo, String raza, String fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNombre: " + nombre + "\nSexo: " + sexo + "\nRaza: " + raza + "\nFecha de Nacimiento: " + fechaNacimiento;
    }
}
