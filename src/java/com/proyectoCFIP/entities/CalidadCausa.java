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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "calidad_causa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalidadCausa.findAll", query = "SELECT c FROM CalidadCausa c")
    , @NamedQuery(name = "CalidadCausa.findByIdCausa", query = "SELECT c FROM CalidadCausa c WHERE c.idCausa = :idCausa")
    , @NamedQuery(name = "CalidadCausa.findByDescripcion", query = "SELECT c FROM CalidadCausa c WHERE c.descripcion = :descripcion")})
public class CalidadCausa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_causa")
    private Integer idCausa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descripcion")
    private String descripcion;
    @ManyToMany(mappedBy = "calidadCausaList")
    private List<CalidadPlanAccion> calidadPlanAccionList;

    public CalidadCausa() {
    }

    public CalidadCausa(Integer idCausa) {
        this.idCausa = idCausa;
    }

    public CalidadCausa(Integer idCausa, String descripcion) {
        this.idCausa = idCausa;
        this.descripcion = descripcion;
    }

    public Integer getIdCausa() {
        return idCausa;
    }

    public void setIdCausa(Integer idCausa) {
        this.idCausa = idCausa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<CalidadPlanAccion> getCalidadPlanAccionList() {
        return calidadPlanAccionList;
    }

    public void setCalidadPlanAccionList(List<CalidadPlanAccion> calidadPlanAccionList) {
        this.calidadPlanAccionList = calidadPlanAccionList;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCausa != null ? idCausa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalidadCausa)) {
            return false;
        }
        CalidadCausa other = (CalidadCausa) object;
        if ((this.idCausa == null && other.idCausa != null) || (this.idCausa != null && !this.idCausa.equals(other.idCausa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.CalidadCausa[ idCausa=" + idCausa + " ]";
    }
    
}
