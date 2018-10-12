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
 * @author junio
 */
@Entity
@Table(name = "au_periodo_plan_auditoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuPeriodoPlanAuditoria.findAll", query = "SELECT a FROM AuPeriodoPlanAuditoria a")
    , @NamedQuery(name = "AuPeriodoPlanAuditoria.findByIdAuPeriodoPlanAuditoria", query = "SELECT a FROM AuPeriodoPlanAuditoria a WHERE a.idAuPeriodoPlanAuditoria = :idAuPeriodoPlanAuditoria")
    , @NamedQuery(name = "AuPeriodoPlanAuditoria.findByAnio", query = "SELECT a FROM AuPeriodoPlanAuditoria a WHERE a.anio = :anio")
    , @NamedQuery(name = "AuPeriodoPlanAuditoria.findByLinkPresentacion", query = "SELECT a FROM AuPeriodoPlanAuditoria a WHERE a.linkPresentacion = :linkPresentacion")
    , @NamedQuery(name = "AuPeriodoPlanAuditoria.findByLinkAnexo", query = "SELECT a FROM AuPeriodoPlanAuditoria a WHERE a.linkAnexo = :linkAnexo")})
public class AuPeriodoPlanAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_au_periodo_plan_auditoria")
    private Integer idAuPeriodoPlanAuditoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "anio")
    private String anio;
    @Size(max = 150)
    @Column(name = "link_presentacion")
    private String linkPresentacion;
    @Size(max = 150)
    @Column(name = "link_anexo")
    private String linkAnexo;
    @OneToMany(mappedBy = "idAuPeriodoPlanAuditoria")
    private List<AuPlanAuditoria> auPlanAuditoriaList;

    public AuPeriodoPlanAuditoria() {
    }

    public AuPeriodoPlanAuditoria(Integer idAuPeriodoPlanAuditoria) {
        this.idAuPeriodoPlanAuditoria = idAuPeriodoPlanAuditoria;
    }

    public AuPeriodoPlanAuditoria(Integer idAuPeriodoPlanAuditoria, String anio) {
        this.idAuPeriodoPlanAuditoria = idAuPeriodoPlanAuditoria;
        this.anio = anio;
    }

    public Integer getIdAuPeriodoPlanAuditoria() {
        return idAuPeriodoPlanAuditoria;
    }

    public void setIdAuPeriodoPlanAuditoria(Integer idAuPeriodoPlanAuditoria) {
        this.idAuPeriodoPlanAuditoria = idAuPeriodoPlanAuditoria;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getLinkPresentacion() {
        return linkPresentacion;
    }

    public void setLinkPresentacion(String linkPresentacion) {
        this.linkPresentacion = linkPresentacion;
    }

    public String getLinkAnexo() {
        return linkAnexo;
    }

    public void setLinkAnexo(String linkAnexo) {
        this.linkAnexo = linkAnexo;
    }

    @XmlTransient
    public List<AuPlanAuditoria> getAuPlanAuditoriaList() {
        return auPlanAuditoriaList;
    }

    public void setAuPlanAuditoriaList(List<AuPlanAuditoria> auPlanAuditoriaList) {
        this.auPlanAuditoriaList = auPlanAuditoriaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuPeriodoPlanAuditoria != null ? idAuPeriodoPlanAuditoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuPeriodoPlanAuditoria)) {
            return false;
        }
        AuPeriodoPlanAuditoria other = (AuPeriodoPlanAuditoria) object;
        if ((this.idAuPeriodoPlanAuditoria == null && other.idAuPeriodoPlanAuditoria != null) || (this.idAuPeriodoPlanAuditoria != null && !this.idAuPeriodoPlanAuditoria.equals(other.idAuPeriodoPlanAuditoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return anio;
    }
    
}
