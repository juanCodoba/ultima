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
@Table(name = "tipo_error")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoError.findAll", query = "SELECT t FROM TipoError t"),
    @NamedQuery(name = "TipoError.findByIdTipoError", query = "SELECT t FROM TipoError t WHERE t.idTipoError = :idTipoError"),
    @NamedQuery(name = "TipoError.findByNombreError", query = "SELECT t FROM TipoError t WHERE t.nombreError = :nombreError")})
public class TipoError implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_error")
    private Integer idTipoError;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_error")
    private String nombreError;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoError")
    private List<ReporteSiesa> reporteSiesaList;

    public TipoError() {
    }

    public TipoError(Integer idTipoError) {
        this.idTipoError = idTipoError;
    }

    public TipoError(Integer idTipoError, String nombreError) {
        this.idTipoError = idTipoError;
        this.nombreError = nombreError;
    }

    public Integer getIdTipoError() {
        return idTipoError;
    }

    public void setIdTipoError(Integer idTipoError) {
        this.idTipoError = idTipoError;
    }

    public String getNombreError() {
        return nombreError;
    }

    public void setNombreError(String nombreError) {
        this.nombreError = nombreError;
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
        hash += (idTipoError != null ? idTipoError.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoError)) {
            return false;
        }
        TipoError other = (TipoError) object;
        if ((this.idTipoError == null && other.idTipoError != null) || (this.idTipoError != null && !this.idTipoError.equals(other.idTipoError))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombreError();
    }
    
}
