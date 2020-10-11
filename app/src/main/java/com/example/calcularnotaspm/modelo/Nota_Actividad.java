package com.example.calcularnotaspm.modelo;

public class Nota_Actividad {
    int id;
    String nombreActividad;
    float porcentaje;
    float valor;
    int numCorte;
    int idMateria;
    float valor2;

    public Nota_Actividad() {
    }

    public Nota_Actividad(int id, String nombreActividad, float porcentaje, float valor, int numCorte, int idMateria, float valor2) {
        this.id = id;
        this.nombreActividad = nombreActividad;
        this.porcentaje = porcentaje;
        this.valor = valor;
        this.numCorte = numCorte;
        this.idMateria = idMateria;
        this.valor2 = valor2;
    }

    public Nota_Actividad(String nombreActividad, float porcentaje, float valor, int numCorte, int idMateria, float valor2) {
        this.nombreActividad = nombreActividad;
        this.porcentaje = porcentaje;
        this.valor = valor;
        this.numCorte = numCorte;
        this.idMateria = idMateria;
        this.valor2 = valor2;
    }

    public Nota_Actividad(String nombreActividad, float porcentaje, float valor, int numCorte, int idMateria) {
        this.nombreActividad = nombreActividad;
        this.porcentaje = porcentaje;
        this.valor = valor;
        this.numCorte = numCorte;
        this.idMateria = idMateria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = (porcentaje/100);
    }

    public double getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getNumCorte() {
        return numCorte;
    }

    public void setNumCorte(int numCorte) {
        this.numCorte = numCorte;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public double getValor2() {
        return (this.valor*this.porcentaje);
    }
}
