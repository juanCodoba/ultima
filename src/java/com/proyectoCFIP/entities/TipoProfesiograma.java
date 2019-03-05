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
@Table(name = "tipo_profesiograma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoProfesiograma.findAll", query = "SELECT t FROM TipoProfesiograma t"),
    @NamedQuery(name = "TipoProfesiograma.findByIdTipoProfesiograma", query = "SELECT t FROM TipoProfesiograma t WHERE t.idTipoProfesiograma = :idTipoProfesiograma"),
    @NamedQuery(name = "TipoProfesiograma.findByNombre", query = "SELECT t FROM TipoProfesiograma t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoProfesiograma.findByMacroTipos", query = "SELECT t FROM TipoProfesiograma t WHERE t.macroTipos = :macroTipos")})
public class TipoProfesiograma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_profesiograma")
    private Integer idTipoProfesiograma;
    @Size(max = 85)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "macro_tipos")
    private String macroTipos;
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoProfesiograma")
    private List<Profesiograma> profesiogramaList;
    

    public TipoProfesiograma() {
    }

    public TipoProfesiograma(Integer idTipoProfesiograma) {
        this.idTipoProfesiograma = idTipoProfesiograma;
    }

    public Integer getIdTipoProfesiograma() {
        return idTipoProfesiograma;
    }

    public void setIdTipoProfesiograma(Integer idTipoProfesiograma) {
        this.idTipoProfesiograma = idTipoProfesiograma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMacroTipos() {
        return macroTipos;
    }

    public void setMacroTipos(String macroTipos) {
        this.macroTipos = macroTipos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoProfesiograma != null ? idTipoProfesiograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoProfesiograma)) {
            return false;
        }
        TipoProfesiograma other = (TipoProfesiograma) object;
        if ((this.idTipoProfesiograma == null && other.idTipoProfesiograma != null) || (this.idTipoProfesiograma != null && !this.idTipoProfesiograma.equals(other.idTipoProfesiograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre();
    }

    public List<Profesiograma> getProfesiogramaList() {
        return profesiogramaList;
    }

    public void setProfesiogramaList(List<Profesiograma> profesiogramaList) {
        this.profesiogramaList = profesiogramaList;
    }


    

    
    
}
