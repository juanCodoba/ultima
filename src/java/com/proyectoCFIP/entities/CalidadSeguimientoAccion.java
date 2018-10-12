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
@Table(name = "calidad_seguimiento_accion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalidadSeguimientoAccion.findAll", query = "SELECT c FROM CalidadSeguimientoAccion c")
    , @NamedQuery(name = "CalidadSeguimientoAccion.findByIdSeguimientoAccion", query = "SELECT c FROM CalidadSeguimientoAccion c WHERE c.idSeguimientoAccion = :idSeguimientoAccion")
    , @NamedQuery(name = "CalidadSeguimientoAccion.findByFecha", query = "SELECT c FROM CalidadSeguimientoAccion c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "CalidadSeguimientoAccion.findByResponsable", query = "SELECT c FROM CalidadSeguimientoAccion c WHERE c.responsable = :responsable")
    , @NamedQuery(name = "CalidadSeguimientoAccion.findByAccion", query = "SELECT c FROM CalidadSeguimientoAccion c WHERE c.idAccionImplementar = :accion")
    , @NamedQuery(name = "CalidadSeguimientoAccion.findByEstado", query = "SELECT c FROM CalidadSeguimientoAccion c WHERE c.estado = :estado")})
public class CalidadSeguimientoAccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_seguimiento_accion")
    private Integer idSeguimientoAccion;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "resultado")
    private String resultado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "responsable")
    private String responsable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_accion_implementar", referencedColumnName = "id_accion_implementar")
    @ManyToOne(optional = false)
    private CalidadAccionImplementar idAccionImplementar;

    public CalidadSeguimientoAccion() {
    }

    public CalidadSeguimientoAccion(Integer idSeguimientoAccion) {
        this.idSeguimientoAccion = idSeguimientoAccion;
    }

    public CalidadSeguimientoAccion(Integer idSeguimientoAccion, Date fecha, String responsable, String estado) {
        this.idSeguimientoAccion = idSeguimientoAccion;
        this.fecha = fecha;
        this.responsable = responsable;
        this.estado = estado;
    }

    public Integer getIdSeguimientoAccion() {
        return idSeguimientoAccion;
    }

    public void setIdSeguimientoAccion(Integer idSeguimientoAccion) {
        this.idSeguimientoAccion = idSeguimientoAccion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public CalidadAccionImplementar getIdAccionImplementar() {
        return idAccionImplementar;
    }

    public void setIdAccionImplementar(CalidadAccionImplementar idAccionImplementar) {
        this.idAccionImplementar = idAccionImplementar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeguimientoAccion != null ? idSeguimientoAccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalidadSeguimientoAccion)) {
            return false;
        }
        CalidadSeguimientoAccion other = (CalidadSeguimientoAccion) object;
        if ((this.idSeguimientoAccion == null && other.idSeguimientoAccion != null) || (this.idSeguimientoAccion != null && !this.idSeguimientoAccion.equals(other.idSeguimientoAccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cod";
    }
    
}
