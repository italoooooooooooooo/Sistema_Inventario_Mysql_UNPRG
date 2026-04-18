
package com.sisventas.model.beans;

public class ItemPedido {
    private int cantidad;     
    private double precioVenta;    
    private Producto producto;

    public ItemPedido() {
    }
    
    public ItemPedido(int cantidad, double precioVenta, Producto producto) {
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;        
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
