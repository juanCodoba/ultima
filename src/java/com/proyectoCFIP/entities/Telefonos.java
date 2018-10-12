/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "telefonos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telefonos.findAll", query = "SELECT t FROM Telefonos t"),
    @NamedQuery(name = "Telefonos.findByIdTelefonos", query = "SELECT t FROM Telefonos t WHERE t.idTelefonos = :idTelefonos"),
    @NamedQuery(name = "Telefonos.findBySeccion", query = "SELECT t FROM Telefonos t WHERE t.seccion = :seccion"),
    @NamedQuery(name = "Telefonos.findByExt", query = "SELECT t FROM Telefonos t WHERE t.ext = :ext")})
public class Telefonos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_telefonos")
    private Integer idTelefonos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 85)
    @Column(name = "seccion")
    private String seccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ext")
    private int ext;

    public Telefonos() {
    }

    public Telefonos(Integer idTelefonos) {
        this.idTelefonos = idTelefonos;
    }

    public Telefonos(Integer idTelefonos, String seccion, int ext) {
        this.idTelefonos = idTelefonos;
        this.seccion = seccion;
        this.ext = ext;
    }

    public Integer getIdTelefonos() {
        return idTelefonos;
    }

    public void setIdTelefonos(Integer idTelefonos) {
        this.idTelefonos = idTelefonos;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public int getExt() {
        return ext;
    }

    public void setExt(int ext) {
        this.ext = ext;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTelefonos != null ? idTelefonos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefonos)) {
            return false;
        }
        Telefonos other = (Telefonos) object;
        if ((this.idTelefonos == null && other.idTelefonos != null) || (this.idTelefonos != null && !this.idTelefonos.equals(other.idTelefonos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.Telefonos[ idTelefonos=" + idTelefonos + " ]";
    }
    
}
