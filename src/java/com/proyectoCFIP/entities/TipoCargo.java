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
@Table(name = "tipo_cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCargo.findAll", query = "SELECT t FROM TipoCargo t"),
    @NamedQuery(name = "TipoCargo.findByIdTipoCargo", query = "SELECT t FROM TipoCargo t WHERE t.idTipoCargo = :idTipoCargo"),
    @NamedQuery(name = "TipoCargo.findByNombre", query = "SELECT t FROM TipoCargo t WHERE t.nombre = :nombre")})
public class TipoCargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_cargo")
    private Integer idTipoCargo;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCargo")
    private List<Cargos> cargosList;

    public TipoCargo() {
    }

    public TipoCargo(Integer idTipoCargo) {
        this.idTipoCargo = idTipoCargo;
    }

    public Integer getIdTipoCargo() {
        return idTipoCargo;
    }

    public void setIdTipoCargo(Integer idTipoCargo) {
        this.idTipoCargo = idTipoCargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCargo != null ? idTipoCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCargo)) {
            return false;
        }
        TipoCargo other = (TipoCargo) object;
        if ((this.idTipoCargo == null && other.idTipoCargo != null) || (this.idTipoCargo != null && !this.idTipoCargo.equals(other.idTipoCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre().toUpperCase();
    }
    public List<Cargos> getCargosList() {
        return cargosList;
    }

    public void setCargosList(List<Cargos> cargosList) {
        this.cargosList = cargosList;
    }
    
    
}
