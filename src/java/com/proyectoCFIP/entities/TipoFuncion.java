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
@Table(name = "tipo_funcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoFuncion.findAll", query = "SELECT t FROM TipoFuncion t"),
    @NamedQuery(name = "TipoFuncion.findByIdTipoFuncion", query = "SELECT t FROM TipoFuncion t WHERE t.idTipoFuncion = :idTipoFuncion"),
    @NamedQuery(name = "TipoFuncion.findByNombreTipo", query = "SELECT t FROM TipoFuncion t WHERE t.nombreTipo = :nombreTipo")})
public class TipoFuncion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_funcion")
    private Integer idTipoFuncion;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombreTipo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoFuncion")
    private List<MaeFuncion> tipoFuncionList;

    public TipoFuncion() {
    }

    public TipoFuncion(Integer idTipoFuncion) {
        this.idTipoFuncion = idTipoFuncion;
    }

    public Integer getIdTipoFuncion() {
        return idTipoFuncion;
    }

    public void setIdTipoFuncion(Integer idTipoFuncion) {
        this.idTipoFuncion = idTipoFuncion;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoFuncion != null ? idTipoFuncion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoFuncion)) {
            return false;
        }
        TipoFuncion other = (TipoFuncion) object;
        if ((this.idTipoFuncion == null && other.idTipoFuncion != null) || (this.idTipoFuncion != null && !this.idTipoFuncion.equals(other.idTipoFuncion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombreTipo().toUpperCase();
    }

    public List<MaeFuncion> getTipoFuncionList() {
        return tipoFuncionList;
    }

    public void setTipoFuncionList(List<MaeFuncion> tipoFuncionList) {
        this.tipoFuncionList = tipoFuncionList;
    }

    
}
