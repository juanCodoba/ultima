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
import javax.persistence.Lob;
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
@Table(name = "historial_dispositivo_conectividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialDispositivoConectividad.findAll", query = "SELECT h FROM HistorialDispositivoConectividad h"),
    @NamedQuery(name = "HistorialDispositivoConectividad.findByIdHistorialDispositivoConectividad", query = "SELECT h FROM HistorialDispositivoConectividad h WHERE h.idHistorialDispositivoConectividad = :idHistorialDispositivoConectividad"),
    @NamedQuery(name = "HistorialDispositivoConectividad.findByFechaActualizacion", query = "SELECT h FROM HistorialDispositivoConectividad h WHERE h.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "HistorialDispositivoConectividad.findByUsuario", query = "SELECT h FROM HistorialDispositivoConectividad h WHERE h.usuario = :usuario"),
    @NamedQuery(name = "HistorialDispositivoConectividad.findByConsultaDispo", query = "SELECT h FROM HistorialDispositivoConectividad h WHERE h.idDispositivoConectividad =:idDispositivoConectividad"),
    @NamedQuery(name = "HistorialDispositivoConectividad.findBySeccion", query = "SELECT h FROM HistorialDispositivoConectividad h WHERE h.seccion = :seccion")})
public class HistorialDispositivoConectividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historial_dispositivo_conectividad")
    private Integer idHistorialDispositivoConectividad;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion_actualizacion")
    private String descripcionActualizacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Size(max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 45)
    @Column(name = "seccion")
    private String seccion;
    @JoinColumn(name = "id_dispositivo_conectividad", referencedColumnName = "id_dispositivo_conectividad")
    @ManyToOne(optional = false)
    private DispositivoConectividad idDispositivoConectividad;
    @JoinColumn(name = "id_tipo_actualizacion", referencedColumnName = "id_tipo_actualizacion")
    @ManyToOne(optional = false)
    private TipoActualizacion idTipoActualizacion;

    public HistorialDispositivoConectividad() {
    }

    public HistorialDispositivoConectividad(Integer idHistorialDispositivoConectividad) {
        this.idHistorialDispositivoConectividad = idHistorialDispositivoConectividad;
    }

    public HistorialDispositivoConectividad(Integer idHistorialDispositivoConectividad, String descripcionActualizacion) {
        this.idHistorialDispositivoConectividad = idHistorialDispositivoConectividad;
        this.descripcionActualizacion = descripcionActualizacion;
    }

    public Integer getIdHistorialDispositivoConectividad() {
        return idHistorialDispositivoConectividad;
    }

    public void setIdHistorialDispositivoConectividad(Integer idHistorialDispositivoConectividad) {
        this.idHistorialDispositivoConectividad = idHistorialDispositivoConectividad;
    }

    public String getDescripcionActualizacion() {
        return descripcionActualizacion;
    }

    public void setDescripcionActualizacion(String descripcionActualizacion) {
        this.descripcionActualizacion = descripcionActualizacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public DispositivoConectividad getIdDispositivoConectividad() {
        return idDispositivoConectividad;
    }

    public void setIdDispositivoConectividad(DispositivoConectividad idDispositivoConectividad) {
        this.idDispositivoConectividad = idDispositivoConectividad;
    }

    public TipoActualizacion getIdTipoActualizacion() {
        return idTipoActualizacion;
    }

    public void setIdTipoActualizacion(TipoActualizacion idTipoActualizacion) {
        this.idTipoActualizacion = idTipoActualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorialDispositivoConectividad != null ? idHistorialDispositivoConectividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialDispositivoConectividad)) {
            return false;
        }
        HistorialDispositivoConectividad other = (HistorialDispositivoConectividad) object;
        if ((this.idHistorialDispositivoConectividad == null && other.idHistorialDispositivoConectividad != null) || (this.idHistorialDispositivoConectividad != null && !this.idHistorialDispositivoConectividad.equals(other.idHistorialDispositivoConectividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.HistorialDispositivoConectividad[ idHistorialDispositivoConectividad=" + idHistorialDispositivoConectividad + " ]";
    }
    
}
