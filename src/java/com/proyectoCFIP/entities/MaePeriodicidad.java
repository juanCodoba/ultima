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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "mae_periodicidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaePeriodicidad.findAll", query = "SELECT m FROM MaePeriodicidad m"),
    @NamedQuery(name = "MaePeriodicidad.findByIdMaePeriodicidad", query = "SELECT m FROM MaePeriodicidad m WHERE m.idMaePeriodicidad = :idMaePeriodicidad"),
    @NamedQuery(name = "MaePeriodicidad.findByNombre", query = "SELECT m FROM MaePeriodicidad m WHERE m.nombre = :nombre")})
public class MaePeriodicidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mae_periodicidad")
    private Integer idMaePeriodicidad;
    @Size(max = 85)
    @Column(name = "nombre")
    private String nombre;

        @OneToMany(mappedBy = "idMaePeriodicidad")
    private List<Actividad> ActividadList;
    public MaePeriodicidad() {
    }

    public MaePeriodicidad(Integer idMaePeriodicidad) {
        this.idMaePeriodicidad = idMaePeriodicidad;
    }

    public Integer getIdMaePeriodicidad() {
        return idMaePeriodicidad;
    }

    public void setIdMaePeriodicidad(Integer idMaePeriodicidad) {
        this.idMaePeriodicidad = idMaePeriodicidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Actividad> getActividadList() {
        return ActividadList;
    }

    public void setActividadList(List<Actividad> ActividadList) {
        this.ActividadList = ActividadList;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaePeriodicidad != null ? idMaePeriodicidad.hashCode() : 0);
        return hash;
    }
    
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaePeriodicidad)) {
            return false;
        }
        MaePeriodicidad other = (MaePeriodicidad) object;
        if ((this.idMaePeriodicidad == null && other.idMaePeriodicidad != null) || (this.idMaePeriodicidad != null && !this.idMaePeriodicidad.equals(other.idMaePeriodicidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIdMaePeriodicidad() + "-" + getNombre();
    }
    
}
