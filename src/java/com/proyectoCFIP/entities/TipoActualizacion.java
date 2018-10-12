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
@Table(name = "tipo_actualizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoActualizacion.findAll", query = "SELECT t FROM TipoActualizacion t"),
    @NamedQuery(name = "TipoActualizacion.findByIdTipoActualizacion", query = "SELECT t FROM TipoActualizacion t WHERE t.idTipoActualizacion = :idTipoActualizacion"),
    @NamedQuery(name = "TipoActualizacion.findByConsultaTipoActualizacion", query = "SELECT t FROM TipoActualizacion t WHERE t.idTipoActualizacion = 1 OR t.idTipoActualizacion = 2 OR t.idTipoActualizacion = 3"),
    @NamedQuery(name = "TipoActualizacion.findByNombreTipoActualizacion", query = "SELECT t FROM TipoActualizacion t WHERE t.nombreTipoActualizacion = :nombreTipoActualizacion")})
public class TipoActualizacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_actualizacion")
    private Integer idTipoActualizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_tipo_actualizacion")
    private String nombreTipoActualizacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoActualizacion")
    private List<HistorialComputador> historialComputadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoActualizacion")
    private List<HistorialOtroDispositivo> historialOtroDispositivoList;

    public TipoActualizacion() {
    }

    public TipoActualizacion(Integer idTipoActualizacion) {
        this.idTipoActualizacion = idTipoActualizacion;
    }

    public TipoActualizacion(Integer idTipoActualizacion, String nombreTipoActualizacion) {
        this.idTipoActualizacion = idTipoActualizacion;
        this.nombreTipoActualizacion = nombreTipoActualizacion;
    }

    public Integer getIdTipoActualizacion() {
        return idTipoActualizacion;
    }

    public void setIdTipoActualizacion(Integer idTipoActualizacion) {
        this.idTipoActualizacion = idTipoActualizacion;
    }

    public String getNombreTipoActualizacion() {
        return nombreTipoActualizacion;
    }

    public void setNombreTipoActualizacion(String nombreTipoActualizacion) {
        this.nombreTipoActualizacion = nombreTipoActualizacion;
    }

    @XmlTransient
    public List<HistorialComputador> getHistorialComputadorList() {
        return historialComputadorList;
    }

    public void setHistorialComputadorList(List<HistorialComputador> historialComputadorList) {
        this.historialComputadorList = historialComputadorList;
    }
    @XmlTransient
    public List<HistorialOtroDispositivo> getHistorialOtroDispositivoList() {
        return historialOtroDispositivoList;
    }

    public void setHistorialOtroDispositivoList(List<HistorialOtroDispositivo> historialOtroDispositivoList) {
        this.historialOtroDispositivoList = historialOtroDispositivoList;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoActualizacion != null ? idTipoActualizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoActualizacion)) {
            return false;
        }
        TipoActualizacion other = (TipoActualizacion) object;
        if ((this.idTipoActualizacion == null && other.idTipoActualizacion != null) || (this.idTipoActualizacion != null && !this.idTipoActualizacion.equals(other.idTipoActualizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombreTipoActualizacion().toUpperCase();
    }
    
}
