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
@Table(name = "cumpleanos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cumpleanos.findAll", query = "SELECT c FROM Cumpleanos c"),
    @NamedQuery(name = "Cumpleanos.findByIdCumpleanos", query = "SELECT c FROM Cumpleanos c WHERE c.idCumpleanos = :idCumpleanos"),
    @NamedQuery(name = "Cumpleanos.findByNombre", query = "SELECT c FROM Cumpleanos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cumpleanos.findByFecha", query = "SELECT c FROM Cumpleanos c WHERE c.fecha = :fecha")})
public class Cumpleanos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cumpleanos")
    private Integer idCumpleanos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 85)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Cumpleanos() {
    }

    public Cumpleanos(Integer idCumpleanos) {
        this.idCumpleanos = idCumpleanos;
    }

    public Cumpleanos(Integer idCumpleanos, String nombre, Date fecha) {
        this.idCumpleanos = idCumpleanos;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public Integer getIdCumpleanos() {
        return idCumpleanos;
    }

    public void setIdCumpleanos(Integer idCumpleanos) {
        this.idCumpleanos = idCumpleanos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idCumpleanos != null ? idCumpleanos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cumpleanos)) {
            return false;
        }
        Cumpleanos other = (Cumpleanos) object;
        if ((this.idCumpleanos == null && other.idCumpleanos != null) || (this.idCumpleanos != null && !this.idCumpleanos.equals(other.idCumpleanos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.Cumpleanos[ idCumpleanos=" + idCumpleanos + " ]";
    }
    
}
