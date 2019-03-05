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
@Table(name = "oportunidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oportunidad.findAll", query = "SELECT o FROM Oportunidad o"),
    @NamedQuery(name = "Oportunidad.findByIdOportunidad", query = "SELECT o FROM Oportunidad o WHERE o.idOportunidad = :idOportunidad"),
    @NamedQuery(name = "Oportunidad.findByDescOprtunidad", query = "SELECT o FROM Oportunidad o WHERE o.descOprtunidad = :descOprtunidad")})
public class Oportunidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_oportunidad")
    private Integer idOportunidad;
    @Size(max = 45)
    @Column(name = "desc_oprtunidad")
    private String descOprtunidad;
    @Column(name = "total")
    private Integer total;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOportunidad")
    private List<Factores> FactoresList;

    public Oportunidad() {
    }

    public Oportunidad(Integer idOportunidad) {
        this.idOportunidad = idOportunidad;
    }

    public Integer getIdOportunidad() {
        return idOportunidad;
    }

    public void setIdOportunidad(Integer idOportunidad) {
        this.idOportunidad = idOportunidad;
    }

    public String getDescOprtunidad() {
        return descOprtunidad;
    }

    public void setDescOprtunidad(String descOprtunidad) {
        this.descOprtunidad = descOprtunidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOportunidad != null ? idOportunidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oportunidad)) {
            return false;
        }
        Oportunidad other = (Oportunidad) object;
        if ((this.idOportunidad == null && other.idOportunidad != null) || (this.idOportunidad != null && !this.idOportunidad.equals(other.idOportunidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescOprtunidad();
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
