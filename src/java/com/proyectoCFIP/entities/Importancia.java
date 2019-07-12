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
@Table(name = "importancia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Importancia.findAll", query = "SELECT i FROM Importancia i"),
    @NamedQuery(name = "Importancia.findByIdImportancia", query = "SELECT i FROM Importancia i WHERE i.idImportancia = :idImportancia"),
    @NamedQuery(name = "Importancia.findByDescripcionImportancia", query = "SELECT i FROM Importancia i WHERE i.descripcionImportancia = :descripcionImportancia")})
public class Importancia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_importancia")
    private Integer idImportancia;
    @Size(max = 45)
    @Column(name = "descripcion_importancia")
    private String descripcionImportancia;

    @Column(name = "total")
    private Integer total;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idImportancia")
    private List<Factores> FactoresList;

    public Importancia() {
    }

    public Importancia(Integer idImportancia) {
        this.idImportancia = idImportancia;
    }

    public Integer getIdImportancia() {
        return idImportancia;
    }

    public void setIdImportancia(Integer idImportancia) {
        this.idImportancia = idImportancia;
    }

    public String getDescripcionImportancia() {
        return descripcionImportancia;
    }

    public void setDescripcionImportancia(String descripcionImportancia) {
        this.descripcionImportancia = descripcionImportancia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImportancia != null ? idImportancia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Importancia)) {
            return false;
        }
        Importancia other = (Importancia) object;
        if ((this.idImportancia == null && other.idImportancia != null) || (this.idImportancia != null && !this.idImportancia.equals(other.idImportancia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getTotal()+" - "+getDescripcionImportancia();
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
