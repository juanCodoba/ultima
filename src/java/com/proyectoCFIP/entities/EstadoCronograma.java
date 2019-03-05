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
@Table(name = "estado_cronograma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCronograma.findAll", query = "SELECT e FROM EstadoCronograma e"),
    @NamedQuery(name = "EstadoCronograma.findByIdEstado", query = "SELECT e FROM EstadoCronograma e WHERE e.idEstado = :idEstado"),
    @NamedQuery(name = "EstadoCronograma.findByIdEstadoDos", query = "SELECT e FROM EstadoCronograma e WHERE e.idEstado = 3 OR e.idEstado = 4"),
    @NamedQuery(name = "EstadoCronograma.findByNombreEstado", query = "SELECT e FROM EstadoCronograma e WHERE e.nombreEstado = :nombreEstado")})
public class EstadoCronograma implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoCronograma")
    private List<CronogramaMantenimientoMaquina> cronogramaMantenimientoMaquinaList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado")
    private Integer idEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_estado")
    private String nombreEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoMantenimiento")
    private List<CronogramaMantenimientos> cronogramaMantenimientosList;

    public EstadoCronograma() {
    }

    public EstadoCronograma(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public EstadoCronograma(Integer idEstado, String nombreEstado) {
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    @XmlTransient
    public List<CronogramaMantenimientos> getCronogramaMantenimientosList() {
        return cronogramaMantenimientosList;
    }

    public void setCronogramaMantenimientosList(List<CronogramaMantenimientos> cronogramaMantenimientosList) {
        this.cronogramaMantenimientosList = cronogramaMantenimientosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCronograma)) {
            return false;
        }
        EstadoCronograma other = (EstadoCronograma) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombreEstado();
    }

    @XmlTransient
    public List<CronogramaMantenimientoMaquina> getCronogramaMantenimientoMaquinaList() {
        return cronogramaMantenimientoMaquinaList;
    }

    public void setCronogramaMantenimientoMaquinaList(List<CronogramaMantenimientoMaquina> cronogramaMantenimientoMaquinaList) {
        this.cronogramaMantenimientoMaquinaList = cronogramaMantenimientoMaquinaList;
    }
    
}
