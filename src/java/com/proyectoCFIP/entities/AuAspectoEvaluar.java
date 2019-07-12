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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
 * @author junio
 */
@Entity
@Table(name = "au_aspecto_evaluar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAspectoEvaluar.findAll", query = "SELECT a FROM AuAspectoEvaluar a"),
    @NamedQuery(name = "AuAspectoEvaluar.findByIdAuAspectoEvaluar", query = "SELECT a FROM AuAspectoEvaluar a WHERE a.idAuAspectoEvaluar = :idAuAspectoEvaluar"),
    @NamedQuery(name = "AuAspectoEvaluar.findByRequisito", query = "SELECT a FROM AuAspectoEvaluar a WHERE a.requisito = :requisito"),
    @NamedQuery(name = "AuAspectoEvaluar.findByAspecto", query = "SELECT a FROM AuAspectoEvaluar a WHERE a.aspecto = :aspecto"),
    @NamedQuery(name = "AuAspectoEvaluar.findByCumplimiento", query = "SELECT a FROM AuAspectoEvaluar a WHERE a.cumplimiento = :cumplimiento"),
    @NamedQuery(name = "AuAspectoEvaluar.findByProcesoEvaluado", query = "SELECT a FROM AuAspectoEvaluar a WHERE a.idAuProcesoEvaluado = :idAuProcesoEvaluado"),
    @NamedQuery(name = "AuAspectoEvaluar.findByComentario", query = "SELECT a FROM AuAspectoEvaluar a WHERE a.comentario = :comentario"),
    @NamedQuery(name = "AuAspectoEvaluar.findByEstado", query = "SELECT a FROM AuAspectoEvaluar a WHERE a.estado = :estado")})
public class AuAspectoEvaluar implements Serializable {

    @Size(max = 111111)
    @Column(name = "recomendacion")
    private String recomendacion;
    @Size(max = 111111)
    @Column(name = "evidencia")
    private String evidencia;

    @OneToMany(mappedBy = "idAuAspectoEvaluar")
    private List<CalidadPlanAccion> calidadPlanAccionList;

    @JoinColumn(name = "id_au_proceso_evaluado", referencedColumnName = "id_au_proceso_evaluado")
    @ManyToOne
    private AuProcesoEvaluado idAuProcesoEvaluado;



    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_au_aspecto_evaluar")
    private Integer idAuAspectoEvaluar;
    @Size(max = 111111)
    @Column(name = "requisito")
    private String requisito;
    @Size(max = 111111)
    @Column(name = "aspecto")
    private String aspecto;
    @Size(max = 111111)
    @Column(name = "cumplimiento")
    private String cumplimiento;
    @Size(max = 111111)
    @Column(name = "comentario")
    private String comentario;
    @Size(max = 111111)
    @Column(name = "estado")
    private String estado;
//    @JoinTable(name = "aspecto_evaluar_has_proceso_evaluado", joinColumns = {
//        @JoinColumn(name = "id_au_aspecto_evaluar", referencedColumnName = "id_au_aspecto_evaluar")}, inverseJoinColumns = {
//        @JoinColumn(name = "id_au_proceso_evaluado", referencedColumnName = "id_au_proceso_evaluado")})
//    @ManyToMany
//    private List<AuProcesoEvaluado> auProcesoEvaluadoList;

    public AuAspectoEvaluar() {
    }

    public AuAspectoEvaluar(Integer idAuAspectoEvaluar) {
        this.idAuAspectoEvaluar = idAuAspectoEvaluar;
    }

    public Integer getIdAuAspectoEvaluar() {
        return idAuAspectoEvaluar;
    }

    public void setIdAuAspectoEvaluar(Integer idAuAspectoEvaluar) {
        this.idAuAspectoEvaluar = idAuAspectoEvaluar;
    }

    public String getRequisito() {
        return requisito;
    }

    public void setRequisito(String requisito) {
        this.requisito = requisito;
    }

    public String getAspecto() {
        return aspecto;
    }

    public void setAspecto(String aspecto) {
        this.aspecto = aspecto;
    }

    public String getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(String cumplimiento) {
        this.cumplimiento = cumplimiento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

//    @XmlTransient
//    public List<AuProcesoEvaluado> getAuProcesoEvaluadoList() {
//        return auProcesoEvaluadoList;
//    }
//
//    public void setAuProcesoEvaluadoList(List<AuProcesoEvaluado> auProcesoEvaluadoList) {
//        this.auProcesoEvaluadoList = auProcesoEvaluadoList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuAspectoEvaluar != null ? idAuAspectoEvaluar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuAspectoEvaluar)) {
            return false;
        }
        AuAspectoEvaluar other = (AuAspectoEvaluar) object;
        if ((this.idAuAspectoEvaluar == null && other.idAuAspectoEvaluar != null) || (this.idAuAspectoEvaluar != null && !this.idAuAspectoEvaluar.equals(other.idAuAspectoEvaluar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.AuAspectoEvaluar[ idAuAspectoEvaluar=" + idAuAspectoEvaluar + " ]";
    }

    public AuProcesoEvaluado getIdAuProcesoEvaluado() {
        return idAuProcesoEvaluado;
    }

    public void setIdAuProcesoEvaluado(AuProcesoEvaluado idAuProcesoEvaluado) {
        this.idAuProcesoEvaluado = idAuProcesoEvaluado;
    }

    @XmlTransient
    public List<CalidadPlanAccion> getCalidadPlanAccionList() {
        return calidadPlanAccionList;
    }

    public void setCalidadPlanAccionList(List<CalidadPlanAccion> calidadPlanAccionList) {
        this.calidadPlanAccionList = calidadPlanAccionList;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }


}
