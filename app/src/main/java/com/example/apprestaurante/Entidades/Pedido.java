package com.example.apprestaurante.Entidades;

import java.util.Date;

public class Pedido {
    private int id;
    private int clienteId;
    private int comidaId;
    private int cantidad;
    private Date fecha;
    private double total;
    private String estado;

    public Pedido() {
    }

    public Pedido(int id, int clienteId, int comidaId, int cantidad, Date fecha, double total, String estado) {
        this.id = id;
        this.clienteId = clienteId;
        this.comidaId = comidaId;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getComidaId() {
        return comidaId;
    }

    public void setComidaId(int comidaId) {
        this.comidaId = comidaId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}