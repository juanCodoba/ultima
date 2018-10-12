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
@Table(name = "modalidades_licencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModalidadesLicencia.findAll", query = "SELECT m FROM ModalidadesLicencia m"),
    @NamedQuery(name = "ModalidadesLicencia.findByIdModalidadesLicencia", query = "SELECT m FROM ModalidadesLicencia m WHERE m.idModalidadesLicencia = :idModalidadesLicencia"),
    @NamedQuery(name = "ModalidadesLicencia.findByNombreModalidad", query = "SELECT m FROM ModalidadesLicencia m WHERE m.nombreModalidad = :nombreModalidad")})
public class ModalidadesLicencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_modalidades_licencia")
    private Integer idModalidadesLicencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_modalidad")
    private String nombreModalidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModalidadesLicencia")
    private List<Licencia> licenciaList;

    public ModalidadesLicencia() {
    }

    public ModalidadesLicencia(Integer idModalidadesLicencia) {
        this.idModalidadesLicencia = idModalidadesLicencia;
    }

    public ModalidadesLicencia(Integer idModalidadesLicencia, String nombreModalidad) {
        this.idModalidadesLicencia = idModalidadesLicencia;
        this.nombreModalidad = nombreModalidad;
    }

    public Integer getIdModalidadesLicencia() {
        return idModalidadesLicencia;
    }

    public void setIdModalidadesLicencia(Integer idModalidadesLicencia) {
        this.idModalidadesLicencia = idModalidadesLicencia;
    }

    public String getNombreModalidad() {
        return nombreModalidad;
    }

    public void setNombreModalidad(String nombreModalidad) {
        this.nombreModalidad = nombreModalidad;
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
        hash += (idModalidadesLicencia != null ? idModalidadesLicencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModalidadesLicencia)) {
            return false;
        }
        ModalidadesLicencia other = (ModalidadesLicencia) object;
        if ((this.idModalidadesLicencia == null && other.idModalidadesLicencia != null) || (this.idModalidadesLicencia != null && !this.idModalidadesLicencia.equals(other.idModalidadesLicencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombreModalidad().toUpperCase();
    }
    
}
