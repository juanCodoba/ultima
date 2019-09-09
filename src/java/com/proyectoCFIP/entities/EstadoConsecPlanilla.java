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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "estado_consec_planilla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoConsecPlanilla.findAll", query = "SELECT e FROM EstadoConsecPlanilla e"),
    @NamedQuery(name = "EstadoConsecPlanilla.findByIdEstadoConsecPlanilla", query = "SELECT e FROM EstadoConsecPlanilla e WHERE e.idEstadoConsecPlanilla = :idEstadoConsecPlanilla"),
    @NamedQuery(name = "EstadoConsecPlanilla.findByDescripcion", query = "SELECT e FROM EstadoConsecPlanilla e WHERE e.descripcion = :descripcion")})
public class EstadoConsecPlanilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_estado_consec_planilla")
    private Integer idEstadoConsecPlanilla;
    @Size(max = 145)
    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "idEstadoConsecPlanilla")
    private List<Planilla> planillaList;

    public EstadoConsecPlanilla() {
    }

    public EstadoConsecPlanilla(Integer idEstadoConsecPlanilla) {
        this.idEstadoConsecPlanilla = idEstadoConsecPlanilla;
    }

    public Integer getIdEstadoConsecPlanilla() {
        return idEstadoConsecPlanilla;
    }

    public void setIdEstadoConsecPlanilla(Integer idEstadoConsecPlanilla) {
        this.idEstadoConsecPlanilla = idEstadoConsecPlanilla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoConsecPlanilla != null ? idEstadoConsecPlanilla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoConsecPlanilla)) {
            return false;
        }
        EstadoConsecPlanilla other = (EstadoConsecPlanilla) object;
        if ((this.idEstadoConsecPlanilla == null && other.idEstadoConsecPlanilla != null) || (this.idEstadoConsecPlanilla != null && !this.idEstadoConsecPlanilla.equals(other.idEstadoConsecPlanilla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    public List<Planilla> getPlanillaList() {
        return planillaList;
    }

    public void setPlanillaList(List<Planilla> planillaList) {
        this.planillaList = planillaList;
    }
    

}
