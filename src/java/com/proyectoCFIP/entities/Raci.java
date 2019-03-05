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
import javax.persistence.ManyToMany;
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
@Table(name = "raci")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Raci.findAll", query = "SELECT r FROM Raci r"),
    @NamedQuery(name = "Raci.findByIdRaci", query = "SELECT r FROM Raci r WHERE r.idRaci = :idRaci"),
    @NamedQuery(name = "Raci.findByDescripcion", query = "SELECT r FROM Raci r WHERE r.descripcion = :descripcion")})
public class Raci implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_raci")
    private Integer idRaci;
    @Size(max = 1000)
    @Column(name = "descripcion")
    private String descripcion;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "raciList")
    private List<MaeFuncion> maeFuncionList;

    public Raci() {
    }

    public Raci(Integer idRaci) {
        this.idRaci = idRaci;
    }

    public Integer getIdRaci() {
        return idRaci;
    }

    public void setIdRaci(Integer idRaci) {
        this.idRaci = idRaci;
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
        hash += (idRaci != null ? idRaci.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Raci)) {
            return false;
        }
        Raci other = (Raci) object;
        if ((this.idRaci == null && other.idRaci != null) || (this.idRaci != null && !this.idRaci.equals(other.idRaci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescripcion();
    }

    public List<MaeFuncion> getMaeFuncionList() {
        return maeFuncionList;
    }

    public void setMaeFuncionList(List<MaeFuncion> maeFuncionList) {
        this.maeFuncionList = maeFuncionList;
    }
    
    

}
