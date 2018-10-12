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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "fabricante_licencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FabricanteLicencia.findAll", query = "SELECT f FROM FabricanteLicencia f"),
    @NamedQuery(name = "FabricanteLicencia.findByIdFabricanteLicencia", query = "SELECT f FROM FabricanteLicencia f WHERE f.idFabricanteLicencia = :idFabricanteLicencia"),
    @NamedQuery(name = "FabricanteLicencia.findByNombreLicencia", query = "SELECT f FROM FabricanteLicencia f WHERE f.nombreLicencia = :nombreLicencia")})
public class FabricanteLicencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fabricante_licencia")
    private Integer idFabricanteLicencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "nombre_licencia")
    private String nombreLicencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFabricanteLicencia")
    private List<Licencia> licenciaList;

    public FabricanteLicencia() {
    }

    public FabricanteLicencia(Integer idFabricanteLicencia) {
        this.idFabricanteLicencia = idFabricanteLicencia;
    }

    public FabricanteLicencia(Integer idFabricanteLicencia, String nombreLicencia) {
        this.idFabricanteLicencia = idFabricanteLicencia;
        this.nombreLicencia = nombreLicencia;
    }

    public Integer getIdFabricanteLicencia() {
        return idFabricanteLicencia;
    }

    public void setIdFabricanteLicencia(Integer idFabricanteLicencia) {
        this.idFabricanteLicencia = idFabricanteLicencia;
    }

    public String getNombreLicencia() {
        return nombreLicencia;
    }

    public void setNombreLicencia(String nombreLicencia) {
        this.nombreLicencia = nombreLicencia;
    }

    @XmlTransient
    public List<Licencia> getLicenciaList() {
        return licenciaList;
    }

    public void setLicenciaList(List<Licencia> licenciaList) {
        this.licenciaList = licenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFabricanteLicencia != null ? idFabricanteLicencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FabricanteLicencia)) {
            return false;
        }
        FabricanteLicencia other = (FabricanteLicencia) object;
        if ((this.idFabricanteLicencia == null && other.idFabricanteLicencia != null) || (this.idFabricanteLicencia != null && !this.idFabricanteLicencia.equals(other.idFabricanteLicencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombreLicencia().toUpperCase();
    }
    
}
