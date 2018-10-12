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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "proyecto_estado_proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProyectoEstadoProyecto.findAll", query = "SELECT p FROM ProyectoEstadoProyecto p")
    , @NamedQuery(name = "ProyectoEstadoProyecto.findByIdEstadoProyecto", query = "SELECT p FROM ProyectoEstadoProyecto p WHERE p.idEstadoProyecto = :idEstadoProyecto")
    , @NamedQuery(name = "ProyectoEstadoProyecto.findByLiderProyecto", query = "SELECT p FROM ProyectoEstadoProyecto p WHERE p.liderProyecto = :liderProyecto")
    , @NamedQuery(name = "ProyectoEstadoProyecto.findByNombreProyecto", query = "SELECT p FROM ProyectoEstadoProyecto p WHERE p.nombreProyecto = :nombreProyecto")
    , @NamedQuery(name = "ProyectoEstadoProyecto.findByPorcentajeFase5", query = "SELECT p FROM ProyectoEstadoProyecto p WHERE p.porcentajeFase5 = :porcentajeFase5")
    , @NamedQuery(name = "ProyectoEstadoProyecto.findByPorcentajeFinal", query = "SELECT p FROM ProyectoEstadoProyecto p WHERE p.porcentajeFinal = :porcentajeFinal")
    , @NamedQuery(name = "ProyectoEstadoProyecto.findByPorcentajeFase1", query = "SELECT p FROM ProyectoEstadoProyecto p WHERE p.porcentajeFase1 = :porcentajeFase1")
    , @NamedQuery(name = "ProyectoEstadoProyecto.findByPorcentajeFase2", query = "SELECT p FROM ProyectoEstadoProyecto p WHERE p.porcentajeFase2 = :porcentajeFase2")
    , @NamedQuery(name = "ProyectoEstadoProyecto.findByPorcentajeFase3", query = "SELECT p FROM ProyectoEstadoProyecto p WHERE p.porcentajeFase3 = :porcentajeFase3")
    , @NamedQuery(name = "ProyectoEstadoProyecto.findByPorcentajeFase4", query = "SELECT p FROM ProyectoEstadoProyecto p WHERE p.porcentajeFase4 = :porcentajeFase4")})
public class ProyectoEstadoProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_proyecto")
    private Integer idEstadoProyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 85)
    @Column(name = "liderProyecto")
    private String liderProyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombreProyecto")
    private String nombreProyecto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje_fase5")
    private Double porcentajeFase5;
    @Column(name = "porcentaje_final")
    private Double porcentajeFinal;
    @Column(name = "porcentaje_fase1")
    private Double porcentajeFase1;
    @Column(name = "porcentaje_fase2")
    private Double porcentajeFase2;
    @Column(name = "porcentaje_fase3")
    private Double porcentajeFase3;
    @Column(name = "porcentaje_fase4")
    private Double porcentajeFase4;

    public ProyectoEstadoProyecto() {
    }

    public ProyectoEstadoProyecto(Integer idEstadoProyecto) {
        this.idEstadoProyecto = idEstadoProyecto;
    }

    public ProyectoEstadoProyecto(Integer idEstadoProyecto, String liderProyecto, String nombreProyecto) {
        this.idEstadoProyecto = idEstadoProyecto;
        this.liderProyecto = liderProyecto;
        this.nombreProyecto = nombreProyecto;
    }

    public Integer getIdEstadoProyecto() {
        return idEstadoProyecto;
    }

    public void setIdEstadoProyecto(Integer idEstadoProyecto) {
        this.idEstadoProyecto = idEstadoProyecto;
    }

    public String getLiderProyecto() {
        return liderProyecto;
    }

    public void setLiderProyecto(String liderProyecto) {
        this.liderProyecto = liderProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Double getPorcentajeFase5() {
        return porcentajeFase5;
    }

    public void setPorcentajeFase5(Double porcentajeFase5) {
        this.porcentajeFase5 = porcentajeFase5;
    }

    public Double getPorcentajeFinal() {
        return porcentajeFinal;
    }

    public void setPorcentajeFinal(Double porcentajeFinal) {
        this.porcentajeFinal = porcentajeFinal;
    }

    public Double getPorcentajeFase1() {
        return porcentajeFase1;
    }

    public void setPorcentajeFase1(Double porcentajeFase1) {
        this.porcentajeFase1 = porcentajeFase1;
    }

    public Double getPorcentajeFase2() {
        return porcentajeFase2;
    }

    public void setPorcentajeFase2(Double porcentajeFase2) {
        this.porcentajeFase2 = porcentajeFase2;
    }

    public Double getPorcentajeFase3() {
        return porcentajeFase3;
    }

    public void setPorcentajeFase3(Double porcentajeFase3) {
        this.porcentajeFase3 = porcentajeFase3;
    }

    public Double getPorcentajeFase4() {
        return porcentajeFase4;
    }

    public void setPorcentajeFase4(Double porcentajeFase4) {
        this.porcentajeFase4 = porcentajeFase4;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoProyecto != null ? idEstadoProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyectoEstadoProyecto)) {
            return false;
        }
        ProyectoEstadoProyecto other = (ProyectoEstadoProyecto) object;
        if ((this.idEstadoProyecto == null && other.idEstadoProyecto != null) || (this.idEstadoProyecto != null && !this.idEstadoProyecto.equals(other.idEstadoProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.ProyectoEstadoProyecto[ idEstadoProyecto=" + idEstadoProyecto + " ]";
    }
    
}
