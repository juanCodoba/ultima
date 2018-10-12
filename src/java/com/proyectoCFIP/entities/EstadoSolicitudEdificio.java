/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "estado_solicitud_edificio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoSolicitudEdificio.findAll", query = "SELECT e FROM EstadoSolicitudEdificio e"),
    @NamedQuery(name = "EstadoSolicitudEdificio.findByIdEstadoSolicitudEdificio", query = "SELECT e FROM EstadoSolicitudEdificio e WHERE e.idEstadoSolicitudEdificio = :idEstadoSolicitudEdificio"),
    @NamedQuery(name = "EstadoSolicitudEdificio.findByNombre", query = "SELECT e FROM EstadoSolicitudEdificio e WHERE e.nombre = :nombre")})
public class EstadoSolicitudEdificio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_solicitud_edificio")
    private Integer idEstadoSolicitudEdificio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;

    public EstadoSolicitudEdificio() {
    }

    public EstadoSolicitudEdificio(Integer idEstadoSolicitudEdificio) {
        this.idEstadoSolicitudEdificio = idEstadoSolicitudEdificio;
    }

    public EstadoSolicitudEdificio(Integer idEstadoSolicitudEdificio, String nombre) {
        this.idEstadoSolicitudEdificio = idEstadoSolicitudEdificio;
        this.nombre = nombre;
    }

    public Integer getIdEstadoSolicitudEdificio() {
        return idEstadoSolicitudEdificio;
    }

    public void setIdEstadoSolicitudEdificio(Integer idEstadoSolicitudEdificio) {
        this.idEstadoSolicitudEdificio = idEstadoSolicitudEdificio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoSolicitudEdificio != null ? idEstadoSolicitudEdificio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoSolicitudEdificio)) {
            return false;
        }
        EstadoSolicitudEdificio other = (EstadoSolicitudEdificio) object;
        if ((this.idEstadoSolicitudEdificio == null && other.idEstadoSolicitudEdificio != null) || (this.idEstadoSolicitudEdificio != null && !this.idEstadoSolicitudEdificio.equals(other.idEstadoSolicitudEdificio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre().toUpperCase();
    }
    
}
