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
@Table(name = "estado_actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoActividad.findAll", query = "SELECT e FROM EstadoActividad e"),
    @NamedQuery(name = "EstadoActividad.findByIdEstadoActividad", query = "SELECT e FROM EstadoActividad e WHERE e.idEstadoActividad = :idEstadoActividad"),
    @NamedQuery(name = "EstadoActividad.findByNombre", query = "SELECT e FROM EstadoActividad e WHERE e.nombre = :nombre")})
public class EstadoActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_actividad")
    private Integer idEstadoActividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoActividad")

    private List<CronogramaActividadesEdificios> cronogramaActividadesEdificiosList;

    public EstadoActividad() {
    }

    public EstadoActividad(Integer idEstadoActividad) {
        this.idEstadoActividad = idEstadoActividad;
    }

    public EstadoActividad(Integer idEstadoActividad, String nombre) {
        this.idEstadoActividad = idEstadoActividad;
        this.nombre = nombre;
    }

    public Integer getIdEstadoActividad() {
        return idEstadoActividad;
    }

    public void setIdEstadoActividad(Integer idEstadoActividad) {
        this.idEstadoActividad = idEstadoActividad;
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
        hash += (idEstadoActividad != null ? idEstadoActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoActividad)) {
            return false;
        }
        EstadoActividad other = (EstadoActividad) object;
        if ((this.idEstadoActividad == null && other.idEstadoActividad != null) || (this.idEstadoActividad != null && !this.idEstadoActividad.equals(other.idEstadoActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre();
    }

}
