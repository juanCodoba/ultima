/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author junio
 */
@Entity
@Table(name = "au_proceso_evaluado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuProcesoEvaluado.findAll", query = "SELECT a FROM AuProcesoEvaluado a")
    , @NamedQuery(name = "AuProcesoEvaluado.findByIdAuProcesoEvaluado", query = "SELECT a FROM AuProcesoEvaluado a WHERE a.idAuProcesoEvaluado = :idAuProcesoEvaluado")
    , @NamedQuery(name = "AuProcesoEvaluado.findByIdAuPlanEvaluado", query = "SELECT a FROM AuProcesoEvaluado a WHERE a.idPlanAuditoria = :idPlanAuditoria")
    , @NamedQuery(name = "AuProcesoEvaluado.findByFecha", query = "SELECT a FROM AuProcesoEvaluado a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "AuProcesoEvaluado.findByHora", query = "SELECT a FROM AuProcesoEvaluado a WHERE a.hora = :hora")
    , @NamedQuery(name = "AuProcesoEvaluado.findByAuditado", query = "SELECT a FROM AuProcesoEvaluado a WHERE a.idAuditado = :idAuditado")})
public class AuProcesoEvaluado implements Serializable {

    @Lob
    @Size(max = 2147483647)
    @Column(name = "fortalezas")
    private String fortalezas;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "oportunidad_mejora")
    private String oportunidadMejora;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "no_conformidades")
    private String noConformidades;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @JoinColumn(name = "id_auditado", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idAuditado;

    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;

    @JoinColumn(name = "id_plan_auditoria", referencedColumnName = "id_plan_auditoria")
    @ManyToOne
    private AuPlanAuditoria idPlanAuditoria;

    @JoinColumn(name = "id_subproceso", referencedColumnName = "id_subproceso")
    @ManyToOne
    private Subprocesos idSubproceso;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_au_proceso_evaluado")
    private Integer idAuProcesoEvaluado;
    @JoinTable(name = "usuario_has_au_proceso_evaluado", joinColumns = {
        @JoinColumn(name = "id_au_proceso_evaluado", referencedColumnName = "id_au_proceso_evaluado")}, inverseJoinColumns = {
        @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")})
    @ManyToMany
    private List<Usuario> usuarioList;
    @ManyToMany(mappedBy = "auProcesoEvaluadoList")
    private List<AuAspectoEvaluar> auAspectoEvaluarList;

    public AuProcesoEvaluado() {
    }

    public AuProcesoEvaluado(Integer idAuProcesoEvaluado) {
        this.idAuProcesoEvaluado = idAuProcesoEvaluado;
    }

    public Integer getIdAuProcesoEvaluado() {
        return idAuProcesoEvaluado;
    }

    public void setIdAuProcesoEvaluado(Integer idAuProcesoEvaluado) {
        this.idAuProcesoEvaluado = idAuProcesoEvaluado;
    }


    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<AuAspectoEvaluar> getAuAspectoEvaluarList() {
        return auAspectoEvaluarList;
    }

    public void setAuAspectoEvaluarList(List<AuAspectoEvaluar> auAspectoEvaluarList) {
        this.auAspectoEvaluarList = auAspectoEvaluarList;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuProcesoEvaluado != null ? idAuProcesoEvaluado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuProcesoEvaluado)) {
            return false;
        }
        AuProcesoEvaluado other = (AuProcesoEvaluado) object;
        if ((this.idAuProcesoEvaluado == null && other.idAuProcesoEvaluado != null) || (this.idAuProcesoEvaluado != null && !this.idAuProcesoEvaluado.equals(other.idAuProcesoEvaluado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.AuProcesoEvaluado[ idAuProcesoEvaluado=" + idAuProcesoEvaluado + " ]";
    }


    public Subprocesos getIdSubproceso() {
        return idSubproceso;
    }

    public void setIdSubproceso(Subprocesos idSubproceso) {
        this.idSubproceso = idSubproceso;
    }

    public AuPlanAuditoria getIdPlanAuditoria() {
        return idPlanAuditoria;
    }

    public void setIdPlanAuditoria(AuPlanAuditoria idPlanAuditoria) {
        this.idPlanAuditoria = idPlanAuditoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Usuario getIdAuditado() {
        return idAuditado;
    }

    public void setIdAuditado(Usuario idAuditado) {
        this.idAuditado = idAuditado;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFortalezas() {
        return fortalezas;
    }

    public void setFortalezas(String fortalezas) {
        this.fortalezas = fortalezas;
    }

    public String getOportunidadMejora() {
        return oportunidadMejora;
    }

    public void setOportunidadMejora(String oportunidadMejora) {
        this.oportunidadMejora = oportunidadMejora;
    }

    public String getNoConformidades() {
        return noConformidades;
    }

    public void setNoConformidades(String noConformidades) {
        this.noConformidades = noConformidades;
    }
    
}
