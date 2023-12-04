package com.aninfo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long proyectoId;
    private String descripcion;
    private String estado;
    private Long horasEstimadas;
    private Long recursoAsignado;
    @ManyToMany(mappedBy = "tareas")
    private List<Ticket> tickets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Long proyectoId) {
        this.proyectoId = proyectoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(Long horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    public Long getRecursoAsignado() {
        return recursoAsignado;
    }

    public void setRecursoAsignado(Long recursoAsignado) {
        this.recursoAsignado = recursoAsignado;
    }
}
