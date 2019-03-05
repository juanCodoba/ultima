/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "habilidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Habilidad.findAll", query = "SELECT m FROM Habilidad m"),
    @NamedQuery(name = "Habilidad.findByIdHabilidad", query = "SELECT m FROM Habilidad m WHERE m.idHabilidad = :idHabilidad"),
    @NamedQuery(name = "Habilidad.findByIdCargo", query = "SELECT m FROM Habilidad m WHERE m.idCargo = :idCargo"),

    @NamedQuery(name = "Habilidad.findByNombre", query = "SELECT m FROM Habilidad m WHERE m.nombre = :nombre")})
public class Habilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habilidad")
    private Integer idHabilidad;
    @Column(name = "nombre_habilidades")
    private String nombre;

    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne(optional = true)
    private Cargos idCargo;

    @JoinColumn(name = "id_nivel_habilidad", referencedColumnName = "id_nivel_habilidad")
    @ManyToOne(optional = true)
    private NivelHabilidad idNivelHabilidad;

    public Habilidad() {
    }

    public Habilidad(Integer idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public Habilidad(Integer idHabilidad, String nombre, boolean estado) {
        this.idHabilidad = idHabilidad;
        this.nombre = nombre;
    }

    public Integer getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(Integer idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHabilidad != null ? idHabilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Habilidad)) {
            return false;
        }
        Habilidad other = (Habilidad) object;
        if ((this.idHabilidad == null && other.idHabilidad != null) || (this.idHabilidad != null && !this.idHabilidad.equals(other.idHabilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIdHabilidad() + "-" + getNombre().toUpperCase();
    }

    public Cargos getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargos idCargo) {
        this.idCargo = idCargo;
    }

    public NivelHabilidad getIdNivelHabilidad() {
        return idNivelHabilidad;
    }

    public void setIdNivelHabilidad(NivelHabilidad idNivelHabilidad) {
        this.idNivelHabilidad = idNivelHabilidad;
    }

}
