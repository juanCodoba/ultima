/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "respuesta_solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespuestaSolicitud.findAll", query = "SELECT r FROM RespuestaSolicitud r"),
    @NamedQuery(name = "RespuestaSolicitud.findByIdRespuestaSolicitud", query = "SELECT r FROM RespuestaSolicitud r WHERE r.idRespuestaSolicitud = :idRespuestaSolicitud"),
    @NamedQuery(name = "RespuestaSolicitud.findByDescripcion", query = "SELECT r FROM RespuestaSolicitud r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "RespuestaSolicitud.findByFechaRespuesta", query = "SELECT r FROM RespuestaSolicitud r WHERE r.fechaRespuesta = :fechaRespuesta")})
public class RespuestaSolicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_respuesta_solicitud")
    private Integer idRespuestaSolicitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_respuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuesta;
    @JoinColumn(name = "id_solicitud_edificio", referencedColumnName = "id_solicitud_edificio")
    @ManyToOne(optional = false)
    private SolicitudEdificio idSolicitudEdificio;

    public RespuestaSolicitud() {
    }

    public RespuestaSolicitud(Integer idRespuestaSolicitud) {
        this.idRespuestaSolicitud = idRespuestaSolicitud;
    }

    public RespuestaSolicitud(Integer idRespuestaSolicitud, String descripcion, Date fechaRespuesta) {
        this.idRespuestaSolicitud = idRespuestaSolicitud;
        this.descripcion = descripcion;
        this.fechaRespuesta = fechaRespuesta;
    }

    public Integer getIdRespuestaSolicitud() {
        return idRespuestaSolicitud;
    }

    public void setIdRespuestaSolicitud(Integer idRespuestaSolicitud) {
        this.idRespuestaSolicitud = idRespuestaSolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public SolicitudEdificio getIdSolicitudEdificio() {
        return idSolicitudEdificio;
    }

    public void setIdSolicitudEdificio(SolicitudEdificio idSolicitudEdificio) {
        this.idSolicitudEdificio = idSolicitudEdificio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuestaSolicitud != null ? idRespuestaSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaSolicitud)) {
            return false;
        }
        RespuestaSolicitud other = (RespuestaSolicitud) object;
        if ((this.idRespuestaSolicitud == null && other.idRespuestaSolicitud != null) || (this.idRespuestaSolicitud != null && !this.idRespuestaSolicitud.equals(other.idRespuestaSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RespuestaSolicitud[ idRespuestaSolicitud=" + idRespuestaSolicitud + " ]";
    }
    
}
