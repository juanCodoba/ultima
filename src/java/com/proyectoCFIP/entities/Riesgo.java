/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "riesgo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Riesgo.findAll", query = "SELECT r FROM Riesgo r"),
    @NamedQuery(name = "Riesgo.findByIdRiesgo", query = "SELECT r FROM Riesgo r WHERE r.idRiesgo = :idRiesgo"),
    @NamedQuery(name = "Riesgo.findByDescripcionRiesgo", query = "SELECT r FROM Riesgo r WHERE r.descripcionRiesgo = :descripcionRiesgo")})
public class Riesgo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_riesgo")
    private Integer idRiesgo;
    @Size(max = 45)
    @Column(name = "descripcion_riesgo")
    private String descripcionRiesgo;
    @Column(name = "total")
    private Integer total;
    
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRiesgo")
    private List<Factores> FactoresList;

    public Riesgo() {
    }

    public Riesgo(Integer idRiesgo) {
        this.idRiesgo = idRiesgo;
    }

    public Integer getIdRiesgo() {
        return idRiesgo;
    }

    public void setIdRiesgo(Integer idRiesgo) {
        this.idRiesgo = idRiesgo;
    }

    public String getDescripcionRiesgo() {
        return descripcionRiesgo;
    }

    public void setDescripcionRiesgo(String descripcionRiesgo) {
        this.descripcionRiesgo = descripcionRiesgo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRiesgo != null ? idRiesgo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Riesgo)) {
            return false;
        }
        Riesgo other = (Riesgo) object;
        if ((this.idRiesgo == null && other.idRiesgo != null) || (this.idRiesgo != null && !this.idRiesgo.equals(other.idRiesgo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescripcionRiesgo();
    }

    public List<Factores> getFactoresList() {
        return FactoresList;
    }

    public void setFactoresList(List<Factores> FactoresList) {
        this.FactoresList = FactoresList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
    
    
    
}
