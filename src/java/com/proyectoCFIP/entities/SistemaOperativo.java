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
import javax.persistence.ManyToOne;
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
@Table(name = "sistema_operativo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SistemaOperativo.findAll", query = "SELECT s FROM SistemaOperativo s"),
    @NamedQuery(name = "SistemaOperativo.findByIdSistemaOperativo", query = "SELECT s FROM SistemaOperativo s WHERE s.idSistemaOperativo = :idSistemaOperativo"),
    @NamedQuery(name = "SistemaOperativo.findByNombreSistemaOperativo", query = "SELECT s FROM SistemaOperativo s WHERE s.nombreSistemaOperativo = :nombreSistemaOperativo"),
    @NamedQuery(name = "SistemaOperativo.findByBits", query = "SELECT s FROM SistemaOperativo s WHERE s.bits = :bits")})
public class SistemaOperativo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sistema_operativo")
    private Integer idSistemaOperativo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_sistema_operativo")
    private String nombreSistemaOperativo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "bits")
    private String bits;
    @OneToMany(mappedBy = "idSistemaOperativo")
    private List<Computador> computadorList;
    @OneToMany(mappedBy = "idSistemaOperativo")
    private List<Licencia> licenciaList;
    
    public SistemaOperativo() {
    }

    public SistemaOperativo(Integer idSistemaOperativo) {
        this.idSistemaOperativo = idSistemaOperativo;
    }

    public SistemaOperativo(Integer idSistemaOperativo, String nombreSistemaOperativo, String bits) {
        this.idSistemaOperativo = idSistemaOperativo;
        this.nombreSistemaOperativo = nombreSistemaOperativo;
        this.bits = bits;
    }

    public Integer getIdSistemaOperativo() {
        return idSistemaOperativo;
    }

    public void setIdSistemaOperativo(Integer idSistemaOperativo) {
        this.idSistemaOperativo = idSistemaOperativo;
    }

    public String getNombreSistemaOperativo() {
        return nombreSistemaOperativo;
    }

    public void setNombreSistemaOperativo(String nombreSistemaOperativo) {
        this.nombreSistemaOperativo = nombreSistemaOperativo;
    }

    public String getBits() {
        return bits;
    }

    public void setBits(String bits) {
        this.bits = bits;
    }

    @XmlTransient
    public List<Computador> getComputadorList() {
        return computadorList;
    }

    public void setComputadorList(List<Computador> computadorList) {
        this.computadorList = computadorList;
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
        hash += (idSistemaOperativo != null ? idSistemaOperativo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SistemaOperativo)) {
            return false;
        }
        SistemaOperativo other = (SistemaOperativo) object;
        if ((this.idSistemaOperativo == null && other.idSistemaOperativo != null) || (this.idSistemaOperativo != null && !this.idSistemaOperativo.equals(other.idSistemaOperativo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombreSistemaOperativo().toUpperCase()+"/"+getBits()+"bits";
    }
    
}
