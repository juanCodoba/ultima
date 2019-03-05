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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "items_situacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemSituacion.findAll", query = "SELECT i FROM ItemSituacion i"),
    @NamedQuery(name = "ItemSituacion.findByIdItemsSituacion", query = "SELECT i FROM ItemSituacion i WHERE i.idItemsSituacion = :idItemsSituacion")})
public class ItemSituacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_items_situacion")
    private Integer idItemsSituacion;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idItemSituacion")
    private List<Factores> factoresList;

    public ItemSituacion() {
    }

    public ItemSituacion(Integer idItemsSituacion) {
        this.idItemsSituacion = idItemsSituacion;
    }

    public Integer getIdItemsSituacion() {
        return idItemsSituacion;
    }

    public void setIdItemsSituacion(Integer idItemsSituacion) {
        this.idItemsSituacion = idItemsSituacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItemsSituacion != null ? idItemsSituacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemSituacion)) {
            return false;
        }
        ItemSituacion other = (ItemSituacion) object;
        if ((this.idItemsSituacion == null && other.idItemsSituacion != null) || (this.idItemsSituacion != null && !this.idItemsSituacion.equals(other.idItemsSituacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescripcion();
    }

    public List<Factores> getFactoresList() {
        return factoresList;
    }

    public void setFactoresList(List<Factores> factoresList) {
        this.factoresList = factoresList;
    }
    
    

}
