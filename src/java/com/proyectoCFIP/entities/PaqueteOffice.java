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
@Table(name = "paquete_office")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaqueteOffice.findAll", query = "SELECT p FROM PaqueteOffice p"),
    @NamedQuery(name = "PaqueteOffice.findByIdPaqueteOffice", query = "SELECT p FROM PaqueteOffice p WHERE p.idPaqueteOffice = :idPaqueteOffice"),
    @NamedQuery(name = "PaqueteOffice.findByNombrePaquete", query = "SELECT p FROM PaqueteOffice p WHERE p.nombrePaquete = :nombrePaquete"),
    @NamedQuery(name = "PaqueteOffice.findByBits", query = "SELECT p FROM PaqueteOffice p WHERE p.bits = :bits")})
public class PaqueteOffice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_paquete_office")
    private Integer idPaqueteOffice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_paquete")
    private String nombrePaquete;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "bits")
    private String bits;
    @OneToMany(mappedBy = "idPaqueteOffice")
    private List<Computador> computadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaqueteOffice")
    private List<LicenciaPaqueteOffice> licenciaPaqueteOfficeList;

    public PaqueteOffice() {
    }

    public PaqueteOffice(Integer idPaqueteOffice) {
        this.idPaqueteOffice = idPaqueteOffice;
    }

    public PaqueteOffice(Integer idPaqueteOffice, String nombrePaquete, String bits) {
        this.idPaqueteOffice = idPaqueteOffice;
        this.nombrePaquete = nombrePaquete;
        this.bits = bits;
    }

    public Integer getIdPaqueteOffice() {
        return idPaqueteOffice;
    }

    public void setIdPaqueteOffice(Integer idPaqueteOffice) {
        this.idPaqueteOffice = idPaqueteOffice;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    public void setNombrePaquete(String nombrePaquete) {
        this.nombrePaquete = nombrePaquete;
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
    public List<LicenciaPaqueteOffice> getLicenciaPaqueteOfficeList() {
        return licenciaPaqueteOfficeList;
    }

    public void setLicenciaPaqueteOfficeList(List<LicenciaPaqueteOffice> licenciaPaqueteOfficeList) {
        this.licenciaPaqueteOfficeList = licenciaPaqueteOfficeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaqueteOffice != null ? idPaqueteOffice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaqueteOffice)) {
            return false;
        }
        PaqueteOffice other = (PaqueteOffice) object;
        if ((this.idPaqueteOffice == null && other.idPaqueteOffice != null) || (this.idPaqueteOffice != null && !this.idPaqueteOffice.equals(other.idPaqueteOffice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombrePaquete().toUpperCase()+"/"+getBits()+"bits";
    }
    
}
