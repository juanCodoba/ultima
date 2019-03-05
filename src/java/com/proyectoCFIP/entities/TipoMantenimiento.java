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
@Table(name = "tipo_mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMantenimiento.findAll", query = "SELECT t FROM TipoMantenimiento t"),
    @NamedQuery(name = "TipoMantenimiento.findByIdTipoMantenimiento", query = "SELECT t FROM TipoMantenimiento t WHERE t.idTipoMantenimiento = :idTipoMantenimiento"),
    @NamedQuery(name = "TipoMantenimiento.findByTipo", query = "SELECT t FROM TipoMantenimiento t WHERE t.idTipoMantenimiento =2 OR t.idTipoMantenimiento=3"),
    @NamedQuery(name = "TipoMantenimiento.findByTipoFallo", query = "SELECT t FROM TipoMantenimiento t WHERE t.idTipoMantenimiento =1"),
    @NamedQuery(name = "TipoMantenimiento.findByNombreTipoMantenimiento", query = "SELECT t FROM TipoMantenimiento t WHERE t.nombreTipoMantenimiento = :nombreTipoMantenimiento")})
public class TipoMantenimiento implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoMantenimiento")
    private List<CronogramaMantenimientoMaquina> cronogramaMantenimientoMaquinaList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_mantenimiento")
    private Integer idTipoMantenimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_tipo_mantenimiento")
    private String nombreTipoMantenimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoMantenimiento")
    private List<CronogramaManteDispositivo> cronogramaManteDispositivoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoMantenimiento")
    private List<CronogramaMantenimientos> cronogramaMantenimientosList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoMantenimiento")
    private List<Libro> libroList;

    public TipoMantenimiento() {
    }

    public TipoMantenimiento(Integer idTipoMantenimiento) {
        this.idTipoMantenimiento = idTipoMantenimiento;
    }

    public TipoMantenimiento(Integer idTipoMantenimiento, String nombreTipoMantenimiento) {
        this.idTipoMantenimiento = idTipoMantenimiento;
        this.nombreTipoMantenimiento = nombreTipoMantenimiento;
    }

    public Integer getIdTipoMantenimiento() {
        return idTipoMantenimiento;
    }

    public void setIdTipoMantenimiento(Integer idTipoMantenimiento) {
        this.idTipoMantenimiento = idTipoMantenimiento;
    }

    public String getNombreTipoMantenimiento() {
        return nombreTipoMantenimiento;
    }

    public void setNombreTipoMantenimiento(String nombreTipoMantenimiento) {
        this.nombreTipoMantenimiento = nombreTipoMantenimiento;
    }

    @XmlTransient
    public List<CronogramaManteDispositivo> getCronogramaManteDispositivoList() {
        return cronogramaManteDispositivoList;
    }

    public void setCronogramaManteDispositivoList(List<CronogramaManteDispositivo> cronogramaManteDispositivoList) {
        this.cronogramaManteDispositivoList = cronogramaManteDispositivoList;
    }

    @XmlTransient
    public List<CronogramaMantenimientos> getCronogramaMantenimientosList() {
        return cronogramaMantenimientosList;
    }

    public void setCronogramaMantenimientosList(List<CronogramaMantenimientos> cronogramaMantenimientosList) {
        this.cronogramaMantenimientosList = cronogramaMantenimientosList;
    }

    public List<Libro> getLibroList() {
        return libroList;
    }

    public void setLibroList(List<Libro> libroList) {
        this.libroList = libroList;
    }


    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMantenimiento != null ? idTipoMantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMantenimiento)) {
            return false;
        }
        TipoMantenimiento other = (TipoMantenimiento) object;
        if ((this.idTipoMantenimiento == null && other.idTipoMantenimiento != null) || (this.idTipoMantenimiento != null && !this.idTipoMantenimiento.equals(other.idTipoMantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIdTipoMantenimiento() + "-"+ getNombreTipoMantenimiento().toUpperCase();
    }

    @XmlTransient
    public List<CronogramaMantenimientoMaquina> getCronogramaMantenimientoMaquinaList() {
        return cronogramaMantenimientoMaquinaList;
    }

    public void setCronogramaMantenimientoMaquinaList(List<CronogramaMantenimientoMaquina> cronogramaMantenimientoMaquinaList) {
        this.cronogramaMantenimientoMaquinaList = cronogramaMantenimientoMaquinaList;
    }

}
