package com.example.parcial2progra1.Modelo;

public class Inmueble {
    private Tipo tipo;
    private String ciudad;
    private String numHabitaciones;
    private  String numPisos;
    private double precio;

    public Inmueble(){

    }

    public Inmueble(Tipo tipo, String ciudad, String numHabitaciones, String numPisos, double precio) {
        this.tipo = tipo;
        this.ciudad = ciudad;
        this.numHabitaciones = numHabitaciones;
        this.numPisos = numPisos;
        this.precio = precio;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(String numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public String getNumPisos() {
        return numPisos;
    }

    public void setNumPisos(String numPisos) {
        this.numPisos = numPisos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Inmueble{" +
                "tipo=" + tipo +
                ", ciudad='" + ciudad + '\'' +
                ", numHabitaciones='" + numHabitaciones + '\'' +
                ", numPisos='" + numPisos + '\'' +
                ", precio=" + precio +
                '}';
    }
}
