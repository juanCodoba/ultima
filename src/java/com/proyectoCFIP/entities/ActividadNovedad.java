/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
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
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "actividad_novedad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActividadNovedad.findAll", query = "SELECT a FROM ActividadNovedad a ORDER BY a.idActividadNovedad DESC"),
    @NamedQuery(name = "ActividadNovedad.findByIdActividadNovedad", query = "SELECT a FROM ActividadNovedad a WHERE a.idActividadNovedad = :idActividadNovedad"),
    @NamedQuery(name = "ActividadNovedad.findByFechaActual", query = "SELECT a FROM ActividadNovedad a WHERE a.fechaActual = :fechaActual"),
    @NamedQuery(name = "ActividadNovedad.findByFechaCorrecion", query = "SELECT a FROM ActividadNovedad a WHERE a.fechaCorrecion = :fechaCorrecion"),
    @NamedQuery(name = "ActividadNovedad.findByIdNovedad", query = "SELECT a FROM ActividadNovedad a WHERE a.idNovedad = :idNovedad")})
public class ActividadNovedad implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad_novedad")
    private Integer idActividadNovedad;
    
    @Column(name = "fecha_actual")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActual;
    
    @Column(name = "fecha_correcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCorrecion;
    
    @Size(max = 2147483647)
    @NotNull
    @Column(name = "descripcion")
    private String descripcion;
    
    @Size(max = 2147483647)
    @Column(name = "responsable")
    private String responsable;
    
    @Size(max = 2147483647)
    @Column(name = "estado_no_conforme_diseno")
    private String estadoNoConformeDiseño;
    
    @Size(max = 2147483647)
    @Column(name = "validacion_ingenieria")
    private String validacionIngenieria;
    
    @Size(max = 2147483647)
    @Column(name = "verificacion_calidad")
    private String verificacionCalidad;

    @JoinColumn(name = "id_novedad", referencedColumnName = "id_novedad")
    @ManyToOne
    private Novedad idNovedad;

    public ActividadNovedad() {
    }

    public ActividadNovedad(Integer idActividadNovedad) {
        this.idActividadNovedad = idActividadNovedad;
    }

    public ActividadNovedad(Integer idActividadNovedad, Date fechaActual, Date fechaCorrecion, String descripcion, String responsable, String estadoNoConformeDiseño, String validacionIngenieria, String verificacionCalidad, Novedad idNovedad) {
        this.idActividadNovedad = idActividadNovedad;
        this.fechaActual = fechaActual;
        this.fechaCorrecion = fechaCorrecion;
        this.descripcion = descripcion;
        this.responsable = responsable;
        this.estadoNoConformeDiseño = estadoNoConformeDiseño;
        this.validacionIngenieria = validacionIngenieria;
        this.verificacionCalidad = verificacionCalidad;
        this.idNovedad = idNovedad;
    }



    public Integer getIdActividadNovedad() {
        return idActividadNovedad;
    }

    public void setIdActividadNovedad(Integer idActividadNovedad) {
        this.idActividadNovedad = idActividadNovedad;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public Date getFechaCorrecion() {
        return fechaCorrecion;
    }

    public void setFechaCorrecion(Date fechaCorrecion) {
        this.fechaCorrecion = fechaCorrecion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadoNoConformeDiseño() {
        return estadoNoConformeDiseño;
    }

    public void setEstadoNoConformeDiseño(String estadoNoConformeDiseño) {
        this.estadoNoConformeDiseño = estadoNoConformeDiseño;
    }

    public String getValidacionIngenieria() {
        return validacionIngenieria;
    }

    public void setValidacionIngenieria(String validacionIngenieria) {
        this.validacionIngenieria = validacionIngenieria;
    }

    public String getVerificacionCalidad() {
        return verificacionCalidad;
    }

    public void setVerificacionCalidad(String verificacionCalidad) {
        this.verificacionCalidad = verificacionCalidad;
    }

    public Novedad getIdNovedad() {
        return idNovedad;
    }

    public void setIdNovedad(Novedad idNovedad) {
        this.idNovedad = idNovedad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividadNovedad != null ? idActividadNovedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadNovedad)) {
            return false;
        }
        ActividadNovedad other = (ActividadNovedad) object;
        if ((this.idActividadNovedad == null && other.idActividadNovedad != null) || (this.idActividadNovedad != null && !this.idActividadNovedad.equals(other.idActividadNovedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.ActividadNovedad[ idActividadNovedad=" + idActividadNovedad + " ]";
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
    
    

}
