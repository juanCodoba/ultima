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
@Table(name = "tipo_licencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoLicencia.findAll", query = "SELECT t FROM TipoLicencia t"),
    @NamedQuery(name = "TipoLicencia.findByIdTipoLicencia", query = "SELECT t FROM TipoLicencia t WHERE t.idTipoLicencia = :idTipoLicencia"),
    @NamedQuery(name = "TipoLicencia.findByNombreTipoLicencia", query = "SELECT t FROM TipoLicencia t WHERE t.nombreTipoLicencia = :nombreTipoLicencia")})
public class TipoLicencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_licencia")
    private Integer idTipoLicencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_tipo_licencia")
    private String nombreTipoLicencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoLicencia")
    private List<Licencia> licenciaList;

    public TipoLicencia() {
    }

    public TipoLicencia(Integer idTipoLicencia) {
        this.idTipoLicencia = idTipoLicencia;
    }

    public TipoLicencia(Integer idTipoLicencia, String nombreTipoLicencia) {
        this.idTipoLicencia = idTipoLicencia;
        this.nombreTipoLicencia = nombreTipoLicencia;
    }

    public Integer getIdTipoLicencia() {
        return idTipoLicencia;
    }

    public void setIdTipoLicencia(Integer idTipoLicencia) {
        this.idTipoLicencia = idTipoLicencia;
    }

    public String getNombreTipoLicencia() {
        return nombreTipoLicencia;
    }

    public void setNombreTipoLicencia(String nombreTipoLicencia) {
        this.nombreTipoLicencia = nombreTipoLicencia;
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
        hash += (idTipoLicencia != null ? idTipoLicencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoLicencia)) {
            return false;
        }
        TipoLicencia other = (TipoLicencia) object;
        if ((this.idTipoLicencia == null && other.idTipoLicencia != null) || (this.idTipoLicencia != null && !this.idTipoLicencia.equals(other.idTipoLicencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombreTipoLicencia().toUpperCase();
    }
    
}
