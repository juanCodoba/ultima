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
@Table(name = "tipo_jornada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoJornada.findAll", query = "SELECT t FROM TipoJornada t"),
    @NamedQuery(name = "TipoJornada.findByIdTipoJornada", query = "SELECT t FROM TipoJornada t WHERE t.idTipoJornada = :idTipoJornada"),
    @NamedQuery(name = "TipoJornada.findByNombre", query = "SELECT t FROM TipoJornada t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoJornada.findByCorreo", query = "SELECT t FROM TipoJornada t WHERE t.correo = :correo")})
public class TipoJornada implements Serializable {

    @OneToMany(mappedBy = "idTipoJornada")
    private List<CronogramaActividadesEdificios> cronogramaActividadesEdificiosList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_jornada")
    private Integer idTipoJornada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "correo")
    private String correo;
    @OneToMany(mappedBy = "idTipoJornada")
    private List<CronogramaManteDispositivo> cronogramaManteDispositivoList;
    @OneToMany(mappedBy = "idTipoJornada")
    private List<CronogramaMantenimientos> cronogramaMantenimientosList;
    @OneToMany(mappedBy = "idTipoJornada")
    private List<CronogramaManteDisCon> cronogramaManteDisConList;

    public TipoJornada() {
    }

    public TipoJornada(Integer idTipoJornada) {
        this.idTipoJornada = idTipoJornada;
    }

    public TipoJornada(Integer idTipoJornada, String nombre, String correo) {
        this.idTipoJornada = idTipoJornada;
        this.nombre = nombre;
        this.correo = correo;
    }

    public Integer getIdTipoJornada() {
        return idTipoJornada;
    }

    public void setIdTipoJornada(Integer idTipoJornada) {
        this.idTipoJornada = idTipoJornada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    @XmlTransient
    public List<CronogramaManteDisCon> getCronogramaManteDisConList() {
        return cronogramaManteDisConList;
    }

    public void setCronogramaManteDisConList(List<CronogramaManteDisCon> cronogramaManteDisConList) {
        this.cronogramaManteDisConList = cronogramaManteDisConList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoJornada != null ? idTipoJornada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoJornada)) {
            return false;
        }
        TipoJornada other = (TipoJornada) object;
        if ((this.idTipoJornada == null && other.idTipoJornada != null) || (this.idTipoJornada != null && !this.idTipoJornada.equals(other.idTipoJornada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre();
    }

    @XmlTransient
    public List<CronogramaActividadesEdificios> getCronogramaActividadesEdificiosList() {
        return cronogramaActividadesEdificiosList;
    }

    public void setCronogramaActividadesEdificiosList(List<CronogramaActividadesEdificios> cronogramaActividadesEdificiosList) {
        this.cronogramaActividadesEdificiosList = cronogramaActividadesEdificiosList;
    }
    
}
