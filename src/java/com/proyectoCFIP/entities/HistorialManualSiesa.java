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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "historial_manual_siesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialManualSiesa.findAll", query = "SELECT h FROM HistorialManualSiesa h"),
    @NamedQuery(name = "HistorialManualSiesa.findByIdHistorialManualSiesa", query = "SELECT h FROM HistorialManualSiesa h WHERE h.idHistorialManualSiesa = :idHistorialManualSiesa"),
    @NamedQuery(name = "HistorialManualSiesa.findByNombre", query = "SELECT h FROM HistorialManualSiesa h WHERE h.nombre = :nombre"),
    @NamedQuery(name = "HistorialManualSiesa.findByVersion", query = "SELECT h FROM HistorialManualSiesa h WHERE h.version = :version"),
    @NamedQuery(name = "HistorialManualSiesa.findByCodigo", query = "SELECT h FROM HistorialManualSiesa h WHERE h.codigo = :codigo"),
    @NamedQuery(name = "HistorialManualSiesa.findByFechaActualizacion", query = "SELECT h FROM HistorialManualSiesa h WHERE h.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "HistorialManualSiesa.findByResponsable", query = "SELECT h FROM HistorialManualSiesa h WHERE h.responsable = :responsable")})
public class HistorialManualSiesa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historial_manual_siesa")
    private Integer idHistorialManualSiesa;
    @Size(max = 70)
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "version")
    private Double version;
    @Size(max = 10)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    @Lob
    @Column(name = "manual")
    private byte[] manual;
    @Size(max = 100)
    @Column(name = "responsable")
    private String responsable;
    @JoinColumn(name = "id_manual_siesa", referencedColumnName = "id_manual_siesa")
    @ManyToOne(optional = false)
    private ManualSiesa idManualSiesa;

    public HistorialManualSiesa() {
    }

    public HistorialManualSiesa(Integer idHistorialManualSiesa) {
        this.idHistorialManualSiesa = idHistorialManualSiesa;
    }

    public Integer getIdHistorialManualSiesa() {
        return idHistorialManualSiesa;
    }

    public void setIdHistorialManualSiesa(Integer idHistorialManualSiesa) {
        this.idHistorialManualSiesa = idHistorialManualSiesa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public byte[] getManual() {
        return manual;
    }

    public void setManual(byte[] manual) {
        this.manual = manual;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public ManualSiesa getIdManualSiesa() {
        return idManualSiesa;
    }

    public void setIdManualSiesa(ManualSiesa idManualSiesa) {
        this.idManualSiesa = idManualSiesa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorialManualSiesa != null ? idHistorialManualSiesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialManualSiesa)) {
            return false;
        }
        HistorialManualSiesa other = (HistorialManualSiesa) object;
        if ((this.idHistorialManualSiesa == null && other.idHistorialManualSiesa != null) || (this.idHistorialManualSiesa != null && !this.idHistorialManualSiesa.equals(other.idHistorialManualSiesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.HistorialManualSiesa[ idHistorialManualSiesa=" + idHistorialManualSiesa + " ]";
    }
    
}
