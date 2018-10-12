/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "estado_solicitud_refrigerio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoSolicitudRefrigerio.findAll", query = "SELECT e FROM EstadoSolicitudRefrigerio e"),
    @NamedQuery(name = "EstadoSolicitudRefrigerio.findByIdEstadoSolicitudRefrigerio", query = "SELECT e FROM EstadoSolicitudRefrigerio e WHERE e.idEstadoSolicitudRefrigerio = :idEstadoSolicitudRefrigerio"),
    @NamedQuery(name = "EstadoSolicitudRefrigerio.findByNombre", query = "SELECT e FROM EstadoSolicitudRefrigerio e WHERE e.nombre = :nombre")})
public class EstadoSolicitudRefrigerio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_solicitud_refrigerio")
    private Integer idEstadoSolicitudRefrigerio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoSolicitudRefrigerio")
    private List<SolicitudEdificio> solicitudEdificioList;

    public EstadoSolicitudRefrigerio() {
    }

    public EstadoSolicitudRefrigerio(Integer idEstadoSolicitudRefrigerio) {
        this.idEstadoSolicitudRefrigerio = idEstadoSolicitudRefrigerio;
    }

    public EstadoSolicitudRefrigerio(Integer idEstadoSolicitudRefrigerio, String nombre) {
        this.idEstadoSolicitudRefrigerio = idEstadoSolicitudRefrigerio;
        this.nombre = nombre;
    }

    public Integer getIdEstadoSolicitudRefrigerio() {
        return idEstadoSolicitudRefrigerio;
    }

    public void setIdEstadoSolicitudRefrigerio(Integer idEstadoSolicitudRefrigerio) {
        this.idEstadoSolicitudRefrigerio = idEstadoSolicitudRefrigerio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<SolicitudEdificio> getSolicitudEdificioList() {
        return solicitudEdificioList;
    }

    public void setSolicitudEdificioList(List<SolicitudEdificio> solicitudEdificioList) {
        this.solicitudEdificioList = solicitudEdificioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoSolicitudRefrigerio != null ? idEstadoSolicitudRefrigerio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoSolicitudRefrigerio)) {
            return false;
        }
        EstadoSolicitudRefrigerio other = (EstadoSolicitudRefrigerio) object;
        if ((this.idEstadoSolicitudRefrigerio == null && other.idEstadoSolicitudRefrigerio != null) || (this.idEstadoSolicitudRefrigerio != null && !this.idEstadoSolicitudRefrigerio.equals(other.idEstadoSolicitudRefrigerio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre().toUpperCase();
    }
    
}
