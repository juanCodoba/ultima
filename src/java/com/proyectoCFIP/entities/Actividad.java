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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a"),
    @NamedQuery(name = "Actividad.findByIdActividad", query = "SELECT a FROM Actividad a WHERE a.idActividad = :idActividad"),
    @NamedQuery(name = "Actividad.findByNombre", query = "SELECT a FROM Actividad a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Actividad.findByOrden", query = "SELECT a FROM Actividad a WHERE a.orden = :orden"),
    @NamedQuery(name = "Actividad.findByEstado", query = "SELECT a FROM Actividad a WHERE a.estado = :estado"),
    @NamedQuery(name = "Actividad.findByIdMaeFuncion", query = "SELECT a FROM Actividad a WHERE a.idMaeFuncion = :idMaeFuncion AND a.idMaeFuncion.idCargo = :idCargo"),
    @NamedQuery(name = "Actividad.findByIdMaePeriodicidad", query = "SELECT a FROM Actividad a WHERE a.idMaePeriodicidad = :idMaePeriodicidad"),
    @NamedQuery(name = "Actividad.findByIdCargo", query = "SELECT a FROM Actividad a WHERE a.idCargo = :idCargo")})
public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_actividad")
    private Integer idActividad;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "accion")
    private String accion;
    @Column(name = "orden")
    private Integer orden;
    @Column(name = "estado")
    private Boolean estado;

    @JoinColumn(name = "id_mae_periodicidad", referencedColumnName = "id_mae_periodicidad")
    @ManyToOne(optional = false)
    private MaePeriodicidad idMaePeriodicidad;

    @JoinColumn(name = "id_mae_funcion", referencedColumnName = "id_mae_funcion")
    @ManyToOne(optional = false)
    private MaeFuncion idMaeFuncion;

    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne(optional = false)
    private Cargos idCargo;

    @JoinTable(name = "actividad_has_mae_herramienta", joinColumns = {
        @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad")}, inverseJoinColumns = {
        @JoinColumn(name = "id_mae_herramienta", referencedColumnName = "id_mae_herramienta")})
    @ManyToMany
    private List<MaeHerramienta> maeHerramientaList;

    @Lob
    @Size(max = 2147483647)
    @Column(name = "proposito_actividad")
    private String propositoActividad;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "condicion_actividad")
    private String condicionActividad;

    public Actividad() {
    }

    public Actividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Actividad(Integer idActividad, String nombre, String accion, Integer orden, Boolean estado, MaePeriodicidad idMaePeriodicidad, MaeFuncion idMaeFuncion, List<MaeHerramienta> maeHerramientaList, String propositoActividad, String condicionActividad) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.accion = accion;
        this.orden = orden;
        this.estado = estado;
        this.idMaePeriodicidad = idMaePeriodicidad;
        this.idMaeFuncion = idMaeFuncion;
        this.maeHerramientaList = maeHerramientaList;
        this.propositoActividad = propositoActividad;
        this.condicionActividad = condicionActividad;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public MaePeriodicidad getIdPeriodicidad() {
        return idMaePeriodicidad;
    }

    public void setIdPeriodicidad(MaePeriodicidad idMaePeriodicidad) {
        this.idMaePeriodicidad = idMaePeriodicidad;
    }

    public MaeFuncion getIdMaeFuncion() {
        return idMaeFuncion;
    }

    public void setIdMaeFuncion(MaeFuncion idMaeFuncion) {
        this.idMaeFuncion = idMaeFuncion;
    }

    public String getPropositoActividad() {
        return propositoActividad;
    }

    public void setPropositoActividad(String propositoActividad) {
        this.propositoActividad = propositoActividad;
    }

    public String getCondicionActividad() {
        return condicionActividad;
    }

    public void setCondicionActividad(String condicionActividad) {
        this.condicionActividad = condicionActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividad != null ? idActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.idActividad == null && other.idActividad != null) || (this.idActividad != null && !this.idActividad.equals(other.idActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getAccion() + " " + getPropositoActividad() + " " + getCondicionActividad();
    }
    @XmlTransient
    public List<MaeHerramienta> getMaeHerramientaList() {
        return maeHerramientaList;
    }

    public void setMaeHerramientaList(List<MaeHerramienta> maeHerramientaList) {
        this.maeHerramientaList = maeHerramientaList;
    }

    public MaePeriodicidad getIdMaePeriodicidad() {
        return idMaePeriodicidad;
    }

    public void setIdMaePeriodicidad(MaePeriodicidad idMaePeriodicidad) {
        this.idMaePeriodicidad = idMaePeriodicidad;
    }

    public Cargos getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargos idCargo) {
        this.idCargo = idCargo;
    }
    
    


}
