/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "calidad_accion_implementar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalidadAccionImplementar.findAll", query = "SELECT c FROM CalidadAccionImplementar c")
    , @NamedQuery(name = "CalidadAccionImplementar.findByIdAccionImplementar", query = "SELECT c FROM CalidadAccionImplementar c WHERE c.idAccionImplementar = :idAccionImplementar")
    , @NamedQuery(name = "CalidadAccionImplementar.findByResponsable", query = "SELECT c FROM CalidadAccionImplementar c WHERE c.responsable = :responsable")
    , @NamedQuery(name = "CalidadAccionImplementar.findByFecha", query = "SELECT c FROM CalidadAccionImplementar c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "CalidadAccionImplementar.findByEstadoAccion", query = "SELECT c FROM CalidadAccionImplementar c WHERE c.estadoAccion = :estadoAccion")})
public class CalidadAccionImplementar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_accion_implementar")
    private Integer idAccionImplementar;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "responsable")
    private String responsable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_accion")
    private boolean estadoAccion;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @ManyToMany(mappedBy = "calidadAccionImplementarList")
    private List<CalidadPlanAccion> calidadPlanAccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAccionImplementar")
    private List<CalidadSeguimientoAccion> calidadSeguimientoAccionList;

    public CalidadAccionImplementar() {
    }

    public CalidadAccionImplementar(Integer idAccionImplementar) {
        this.idAccionImplementar = idAccionImplementar;
    }

    public CalidadAccionImplementar(Integer idAccionImplementar, String descripcion, String responsable, Date fecha, boolean estadoAccion) {
        this.idAccionImplementar = idAccionImplementar;
        this.descripcion = descripcion;
        this.responsable = responsable;
        this.fecha = fecha;
        this.estadoAccion = estadoAccion;
    }

    public Integer getIdAccionImplementar() {
        return idAccionImplementar;
    }

    public void setIdAccionImplementar(Integer idAccionImplementar) {
        this.idAccionImplementar = idAccionImplementar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getEstadoAccion() {
        return estadoAccion;
    }

    public void setEstadoAccion(boolean estadoAccion) {
        this.estadoAccion = estadoAccion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<CalidadPlanAccion> getCalidadPlanAccionList() {
        return calidadPlanAccionList;
    }

    public void setCalidadPlanAccionList(List<CalidadPlanAccion> calidadPlanAccionList) {
        this.calidadPlanAccionList = calidadPlanAccionList;
    }

    @XmlTransient
    public List<CalidadSeguimientoAccion> getCalidadSeguimientoAccionList() {
        return calidadSeguimientoAccionList;
    }

    public void setCalidadSeguimientoAccionList(List<CalidadSeguimientoAccion> calidadSeguimientoAccionList) {
        this.calidadSeguimientoAccionList = calidadSeguimientoAccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccionImplementar != null ? idAccionImplementar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalidadAccionImplementar)) {
            return false;
        }
        CalidadAccionImplementar other = (CalidadAccionImplementar) object;
        if ((this.idAccionImplementar == null && other.idAccionImplementar != null) || (this.idAccionImplementar != null && !this.idAccionImplementar.equals(other.idAccionImplementar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescripcion() + " - "+getResponsable();
    }
    
}
