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
@Table(name = "tipo_carga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCarga.findAll", query = "SELECT t FROM TipoCarga t"),
    @NamedQuery(name = "TipoCarga.findByIdTipoCarga", query = "SELECT t FROM TipoCarga t WHERE t.idTipoCarga = :idTipoCarga")})
public class TipoCarga implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_carga")
    private Integer idTipoCarga;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "idTipoCarga")
    private List<Facturado> facturadoList;

    public TipoCarga() {
    }

    public TipoCarga(Integer idTipoCarga) {
        this.idTipoCarga = idTipoCarga;
    }

    public Integer getIdTipoCarga() {
        return idTipoCarga;
    }

    public void setIdTipoCarga(Integer idTipoCarga) {
        this.idTipoCarga = idTipoCarga;
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
        hash += (idTipoCarga != null ? idTipoCarga.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCarga)) {
            return false;
        }
        TipoCarga other = (TipoCarga) object;
        if ((this.idTipoCarga == null && other.idTipoCarga != null) || (this.idTipoCarga != null && !this.idTipoCarga.equals(other.idTipoCarga))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion
                ;
    }

    public List<Facturado> getFacturadoList() {
        return facturadoList;
    }

    public void setFacturadoList(List<Facturado> facturadoList) {
        this.facturadoList = facturadoList;
    }
    

}
