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
@Table(name = "historial_otro_dispositivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialOtroDispositivo.findAll", query = "SELECT h FROM HistorialOtroDispositivo h"),
    @NamedQuery(name = "HistorialOtroDispositivo.findByIdHistorialOtroDispositivo", query = "SELECT h FROM HistorialOtroDispositivo h WHERE h.idHistorialOtroDispositivo = :idHistorialOtroDispositivo"),
    @NamedQuery(name = "HistorialOtroDispositivo.findBySeccion", query = "SELECT h FROM HistorialOtroDispositivo h WHERE h.seccion = :seccion"),
    @NamedQuery(name = "HistorialOtroDispositivo.findByUsuario", query = "SELECT h FROM HistorialOtroDispositivo h WHERE h.usuario = :usuario"),
    @NamedQuery(name = "HistorialOtroDispositivo.findByConsultaDispositivo", query = "SELECT h FROM HistorialOtroDispositivo h WHERE h.idOtroDispositivo =:idOtroDispositivo"),
    @NamedQuery(name = "HistorialOtroDispositivo.findByFechaActualizacion", query = "SELECT h FROM HistorialOtroDispositivo h WHERE h.fechaActualizacion = :fechaActualizacion")})
    
public class HistorialOtroDispositivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historial_otro_dispositivo")
    private Integer idHistorialOtroDispositivo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion_actualizacion")
    private String descripcionActualizacion;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "seccion")
    private String seccion;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @JoinColumn(name = "id_otro_dispositivo", referencedColumnName = "id_otro_dispositivo")
    @ManyToOne(optional = false)
    private OtroDispositivo idOtroDispositivo;
    @JoinColumn(name = "id_tipo_actualizacion", referencedColumnName = "id_tipo_actualizacion")
    @ManyToOne(optional = false)
    private TipoActualizacion idTipoActualizacion;

    public HistorialOtroDispositivo() {
    }

    public HistorialOtroDispositivo(Integer idHistorialOtroDispositivo) {
        this.idHistorialOtroDispositivo = idHistorialOtroDispositivo;
    }

    public HistorialOtroDispositivo(Integer idHistorialOtroDispositivo, String descripcionActualizacion, String seccion, String usuario, Date fechaActualizacion) {
        this.idHistorialOtroDispositivo = idHistorialOtroDispositivo;
        this.descripcionActualizacion = descripcionActualizacion;
        this.seccion = seccion;
        this.usuario = usuario;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getIdHistorialOtroDispositivo() {
        return idHistorialOtroDispositivo;
    }

    public void setIdHistorialOtroDispositivo(Integer idHistorialOtroDispositivo) {
        this.idHistorialOtroDispositivo = idHistorialOtroDispositivo;
    }

    public String getDescripcionActualizacion() {
        return descripcionActualizacion;
    }

    public void setDescripcionActualizacion(String descripcionActualizacion) {
        this.descripcionActualizacion = descripcionActualizacion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public OtroDispositivo getIdOtroDispositivo() {
        return idOtroDispositivo;
    }

    public void setIdOtroDispositivo(OtroDispositivo idOtroDispositivo) {
        this.idOtroDispositivo = idOtroDispositivo;
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
        hash += (idHistorialOtroDispositivo != null ? idHistorialOtroDispositivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialOtroDispositivo)) {
            return false;
        }
        HistorialOtroDispositivo other = (HistorialOtroDispositivo) object;
        if ((this.idHistorialOtroDispositivo == null && other.idHistorialOtroDispositivo != null) || (this.idHistorialOtroDispositivo != null && !this.idHistorialOtroDispositivo.equals(other.idHistorialOtroDispositivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.HistorialOtroDispositivo[ idHistorialOtroDispositivo=" + idHistorialOtroDispositivo + " ]";
    }
    
}
