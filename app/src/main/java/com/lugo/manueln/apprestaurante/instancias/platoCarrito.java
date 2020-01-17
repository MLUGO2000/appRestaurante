package com.lugo.manueln.apprestaurante.instancias;

public class platoCarrito {
    public Boolean getPlatoExistente() {
        return platoExistente;
    }

    public void setPlatoExistente(Boolean platoExistente) {
        this.platoExistente = platoExistente;
    }

    Boolean platoExistente;
    String nombre,urlImageCarrito;
    int id,cantidad;
    double priceTotal;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getUrlImageCarrito() {
        return urlImageCarrito;
    }

    public void setUrlImageCarrito(String urlImageCarrito) {
        this.urlImageCarrito = urlImageCarrito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
