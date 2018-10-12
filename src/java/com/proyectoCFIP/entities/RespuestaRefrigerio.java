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
@Table(name = "respuesta_refrigerio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespuestaRefrigerio.findAll", query = "SELECT r FROM RespuestaRefrigerio r"),
    @NamedQuery(name = "RespuestaRefrigerio.findByIdRespuestaRefrigerio", query = "SELECT r FROM RespuestaRefrigerio r WHERE r.idRespuestaRefrigerio = :idRespuestaRefrigerio"),
    @NamedQuery(name = "RespuestaRefrigerio.findByDescripcion", query = "SELECT r FROM RespuestaRefrigerio r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "RespuestaRefrigerio.findByFechaRespuesta", query = "SELECT r FROM RespuestaRefrigerio r WHERE r.fechaRespuesta = :fechaRespuesta")})
public class RespuestaRefrigerio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_respuesta_refrigerio")
    private Integer idRespuestaRefrigerio;
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

    public RespuestaRefrigerio() {
    }

    public RespuestaRefrigerio(Integer idRespuestaRefrigerio) {
        this.idRespuestaRefrigerio = idRespuestaRefrigerio;
    }

    public RespuestaRefrigerio(Integer idRespuestaRefrigerio, String descripcion, Date fechaRespuesta) {
        this.idRespuestaRefrigerio = idRespuestaRefrigerio;
        this.descripcion = descripcion;
        this.fechaRespuesta = fechaRespuesta;
    }

    public Integer getIdRespuestaRefrigerio() {
        return idRespuestaRefrigerio;
    }

    public void setIdRespuestaRefrigerio(Integer idRespuestaRefrigerio) {
        this.idRespuestaRefrigerio = idRespuestaRefrigerio;
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
        hash += (idRespuestaRefrigerio != null ? idRespuestaRefrigerio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaRefrigerio)) {
            return false;
        }
        RespuestaRefrigerio other = (RespuestaRefrigerio) object;
        if ((this.idRespuestaRefrigerio == null && other.idRespuestaRefrigerio != null) || (this.idRespuestaRefrigerio != null && !this.idRespuestaRefrigerio.equals(other.idRespuestaRefrigerio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.RespuestaRefrigerio[ idRespuestaRefrigerio=" + idRespuestaRefrigerio + " ]";
    }
    
}
