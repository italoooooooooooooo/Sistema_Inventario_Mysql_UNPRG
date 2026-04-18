package com.sisventas.model.beans;

import java.util.Date;
import java.util.List;

public class Pedido {

    private int idPedido;
    private Date fecha;
    private Cliente cliente;
    private List<ItemPedido> itemPedido;
    private double total;
    private double subtotal;
    private double descuento;
    private double efectivo;
    private double cambio;

    public Pedido() {
    }

    public Pedido(int idPedido, Date fecha, Cliente cliente, List<ItemPedido> itemPedido, double total, double subtotal, double descuento, double efectivo, double cambio) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.cliente = cliente;
        this.itemPedido = itemPedido;
        this.total = total;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.efectivo = efectivo;
        this.cambio = cambio;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(List<ItemPedido> itemPedido) {
        this.itemPedido = itemPedido;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(double efectivo) {
        this.efectivo = efectivo;
    }

    public double getCambio() {
        return cambio;
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }
    
}
