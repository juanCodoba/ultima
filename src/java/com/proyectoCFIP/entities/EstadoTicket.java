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
@Table(name = "estado_ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoTicket.findAll", query = "SELECT e FROM EstadoTicket e"),
    @NamedQuery(name = "EstadoTicket.findByIdEstadoTicket", query = "SELECT e FROM EstadoTicket e WHERE e.idEstadoTicket = :idEstadoTicket"),
    @NamedQuery(name = "EstadoTicket.findByNombre", query = "SELECT e FROM EstadoTicket e WHERE e.nombre = :nombre")})
public class EstadoTicket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_ticket")
    private Integer idEstadoTicket;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoTicket")
    private List<ReporteSiesa> reporteSiesaList;

    public EstadoTicket() {
    }

    public EstadoTicket(Integer idEstadoTicket) {
        this.idEstadoTicket = idEstadoTicket;
    }

    public EstadoTicket(Integer idEstadoTicket, String nombre) {
        this.idEstadoTicket = idEstadoTicket;
        this.nombre = nombre;
    }

    public Integer getIdEstadoTicket() {
        return idEstadoTicket;
    }

    public void setIdEstadoTicket(Integer idEstadoTicket) {
        this.idEstadoTicket = idEstadoTicket;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<ReporteSiesa> getReporteSiesaList() {
        return reporteSiesaList;
    }

    public void setReporteSiesaList(List<ReporteSiesa> reporteSiesaList) {
        this.reporteSiesaList = reporteSiesaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoTicket != null ? idEstadoTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoTicket)) {
            return false;
        }
        EstadoTicket other = (EstadoTicket) object;
        if ((this.idEstadoTicket == null && other.idEstadoTicket != null) || (this.idEstadoTicket != null && !this.idEstadoTicket.equals(other.idEstadoTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre();
    }
    
}
