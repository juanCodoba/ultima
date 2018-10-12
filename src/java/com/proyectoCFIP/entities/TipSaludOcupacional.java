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
import javax.persistence.Lob;
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
@Table(name = "tip_salud_ocupacional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipSaludOcupacional.findAll", query = "SELECT t FROM TipSaludOcupacional t"),
    @NamedQuery(name = "TipSaludOcupacional.findConsulta", query = "SELECT t FROM TipSaludOcupacional t ORDER BY t.idTipSaludOcupacional DESC"),
    @NamedQuery(name = "TipSaludOcupacional.findByIdTipSaludOcupacional", query = "SELECT t FROM TipSaludOcupacional t WHERE t.idTipSaludOcupacional = :idTipSaludOcupacional"),
    @NamedQuery(name = "TipSaludOcupacional.findByTitulo", query = "SELECT t FROM TipSaludOcupacional t WHERE t.titulo = :titulo"),
    @NamedQuery(name = "TipSaludOcupacional.findByFecha", query = "SELECT t FROM TipSaludOcupacional t WHERE t.fecha = :fecha")})
public class TipSaludOcupacional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tip_salud_ocupacional")
    private Integer idTipSaludOcupacional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public TipSaludOcupacional() {
    }

    public TipSaludOcupacional(Integer idTipSaludOcupacional) {
        this.idTipSaludOcupacional = idTipSaludOcupacional;
    }

    public TipSaludOcupacional(Integer idTipSaludOcupacional, String titulo, String descripcion, Date fecha) {
        this.idTipSaludOcupacional = idTipSaludOcupacional;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Integer getIdTipSaludOcupacional() {
        return idTipSaludOcupacional;
    }

    public void setIdTipSaludOcupacional(Integer idTipSaludOcupacional) {
        this.idTipSaludOcupacional = idTipSaludOcupacional;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipSaludOcupacional != null ? idTipSaludOcupacional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipSaludOcupacional)) {
            return false;
        }
        TipSaludOcupacional other = (TipSaludOcupacional) object;
        if ((this.idTipSaludOcupacional == null && other.idTipSaludOcupacional != null) || (this.idTipSaludOcupacional != null && !this.idTipSaludOcupacional.equals(other.idTipSaludOcupacional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.TipSaludOcupacional[ idTipSaludOcupacional=" + idTipSaludOcupacional + " ]";
    }
    
}
