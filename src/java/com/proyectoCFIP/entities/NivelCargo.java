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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "nivel_cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelCargo.findAll", query = "SELECT n FROM NivelCargo n"),
    @NamedQuery(name = "NivelCargo.findByIdNivelCargo", query = "SELECT n FROM NivelCargo n WHERE n.idNivelCargo = :idNivelCargo"),
    @NamedQuery(name = "NivelCargo.findByNivelTipoCargo", query = "SELECT n FROM NivelCargo n WHERE n.nivelTipoCargo = :nivelTipoCargo")})
public class NivelCargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel_cargo")
    private Integer idNivelCargo;
    @Column(name = "nivel_tipo_cargo")
    private String nivelTipoCargo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNivelCargo")
    private List<Cargos> cargosList;

    public NivelCargo() {
    }

    public NivelCargo(Integer idNivelCargo) {
        this.idNivelCargo = idNivelCargo;
    }

    public Integer getIdNivelCargo() {
        return idNivelCargo;
    }

    public void setIdNivelCargo(Integer idNivelCargo) {
        this.idNivelCargo = idNivelCargo;
    }

    public String getNivelTipoCargo() {
        return nivelTipoCargo;
    }

    public void setNivelTipoCargo(String nivelTipoCargo) {
        this.nivelTipoCargo = nivelTipoCargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNivelCargo != null ? idNivelCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelCargo)) {
            return false;
        }
        NivelCargo other = (NivelCargo) object;
        if ((this.idNivelCargo == null && other.idNivelCargo != null) || (this.idNivelCargo != null && !this.idNivelCargo.equals(other.idNivelCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNivelTipoCargo().toUpperCase();
    }

    public List<Cargos> getCargosList() {
        return cargosList;
    }

    public void setCargosList(List<Cargos> cargosList) {
        this.cargosList = cargosList;
    }
    
    

}
