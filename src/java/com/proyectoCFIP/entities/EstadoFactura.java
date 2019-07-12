/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "estado_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoFactura.findAll", query = "SELECT e FROM EstadoFactura e"),
    @NamedQuery(name = "EstadoFactura.findByIdEstadoFactura", query = "SELECT e FROM EstadoFactura e WHERE e.idEstadoFactura = :idEstadoFactura"),
    @NamedQuery(name = "EstadoFactura.findByDescripcion", query = "SELECT e FROM EstadoFactura e WHERE e.descripcion = :descripcion")})
public class EstadoFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_factura")
    private Integer idEstadoFactura;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idEstadoFactura")
    private List<Facturado> facturadoList;

    public EstadoFactura() {
    }

    public EstadoFactura(Integer idEstadoFactura) {
        this.idEstadoFactura = idEstadoFactura;
    }

    public Integer getIdEstadoFactura() {
        return idEstadoFactura;
    }

    public void setIdEstadoFactura(Integer idEstadoFactura) {
        this.idEstadoFactura = idEstadoFactura;
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
        hash += (idEstadoFactura != null ? idEstadoFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoFactura)) {
            return false;
        }
        EstadoFactura other = (EstadoFactura) object;
        if ((this.idEstadoFactura == null && other.idEstadoFactura != null) || (this.idEstadoFactura != null && !this.idEstadoFactura.equals(other.idEstadoFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescripcion();
    }

    public List<Facturado> getFacturadoList() {
        return facturadoList;
    }

    public void setFacturadoList(List<Facturado> facturadoList) {
        this.facturadoList = facturadoList;
    }
    
    

}
