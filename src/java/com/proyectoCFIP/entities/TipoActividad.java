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
import javax.persistence.FetchType;
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
@Table(name = "tipo_actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoActividad.findAll", query = "SELECT t FROM TipoActividad t"),
    @NamedQuery(name = "TipoActividad.findByIdTipoActividad", query = "SELECT t FROM TipoActividad t WHERE t.idTipoActividad = :idTipoActividad"),
    @NamedQuery(name = "TipoActividad.findByNombre", query = "SELECT t FROM TipoActividad t WHERE t.nombre = :nombre")})
public class TipoActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_actividad")
    private Integer idTipoActividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoActividad")
    private List<CronogramaActividadesEdificios> cronogramaActividadesEdificiosList;

    public TipoActividad() {
    }

    public TipoActividad(Integer idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public TipoActividad(Integer idTipoActividad, String nombre) {
        this.idTipoActividad = idTipoActividad;
        this.nombre = nombre;
    }

    public Integer getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(Integer idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<CronogramaActividadesEdificios> getCronogramaActividadesEdificiosList() {
        return cronogramaActividadesEdificiosList;
    }

    public void setCronogramaActividadesEdificiosList(List<CronogramaActividadesEdificios> cronogramaActividadesEdificiosList) {
        this.cronogramaActividadesEdificiosList = cronogramaActividadesEdificiosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoActividad != null ? idTipoActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoActividad)) {
            return false;
        }
        TipoActividad other = (TipoActividad) object;
        if ((this.idTipoActividad == null && other.idTipoActividad != null) || (this.idTipoActividad != null && !this.idTipoActividad.equals(other.idTipoActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TipoActividad[ idTipoActividad=" + idTipoActividad + " ]";
    }

}
