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
@Table(name = "unid_negocio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidNegocio.findAll", query = "SELECT u FROM UnidNegocio u"),
    @NamedQuery(name = "UnidNegocio.findByIdUnidadNegocio", query = "SELECT u FROM UnidNegocio u WHERE u.idUnidadNegocio = :idUnidadNegocio")})
public class UnidNegocio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_unidad_negocio")
    private Integer idUnidadNegocio;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadNegocio")
    private List<Presupuesto> PresupuestoList;

    public UnidNegocio() {
    }

    public UnidNegocio(Integer idUnidadNegocio) {
        this.idUnidadNegocio = idUnidadNegocio;
    }

    public Integer getIdUnidadNegocio() {
        return idUnidadNegocio;
    }

    public void setIdUnidadNegocio(Integer idUnidadNegocio) {
        this.idUnidadNegocio = idUnidadNegocio;
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
        hash += (idUnidadNegocio != null ? idUnidadNegocio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidNegocio)) {
            return false;
        }
        UnidNegocio other = (UnidNegocio) object;
        if ((this.idUnidadNegocio == null && other.idUnidadNegocio != null) || (this.idUnidadNegocio != null && !this.idUnidadNegocio.equals(other.idUnidadNegocio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.UnidNegocio[ idUnidadNegocio=" + idUnidadNegocio + " ]";
    }
    
}
