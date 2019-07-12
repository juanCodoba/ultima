/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "calidad_plan_accion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalidadPlanAccion.findAll", query = "SELECT c FROM CalidadPlanAccion c ORDER BY c.idPlanAccion DESC"),
    @NamedQuery(name = "CalidadPlanAccion.findByIdPlanAccion", query = "SELECT c FROM CalidadPlanAccion c WHERE c.idPlanAccion = :idPlanAccion"),
    @NamedQuery(name = "CalidadPlanAccion.findByResponsable", query = "SELECT c FROM CalidadPlanAccion c WHERE c.responsable = :responsable"),
    @NamedQuery(name = "CalidadPlanAccion.findByFechaAnalisis", query = "SELECT c FROM CalidadPlanAccion c WHERE c.fechaAnalisis = :fechaAnalisis"),
    @NamedQuery(name = "CalidadPlanAccion.findByTipoPlan", query = "SELECT c FROM CalidadPlanAccion c WHERE c.tipoPlan = :tipoPlan"),
    @NamedQuery(name = "CalidadPlanAccion.findByOrigenAccion", query = "SELECT c FROM CalidadPlanAccion c WHERE c.origenAccion = :origenAccion"),
    @NamedQuery(name = "CalidadPlanAccion.findByObservacionGeneral", query = "SELECT c FROM CalidadPlanAccion c WHERE c.observacionGeneral = :observacionGeneral"),
    @NamedQuery(name = "CalidadPlanAccion.findByEstadoPlan", query = "SELECT c FROM CalidadPlanAccion c WHERE c.estadoPlan = :estadoPlan"),
    @NamedQuery(name = "CalidadPlanAccion.findByCriterio1", query = "SELECT c FROM CalidadPlanAccion c WHERE c.criterio1 = :criterio1"),
    @NamedQuery(name = "CalidadPlanAccion.findByCriterio2", query = "SELECT c FROM CalidadPlanAccion c WHERE c.criterio2 = :criterio2"),
    @NamedQuery(name = "CalidadPlanAccion.findByAccionEficaz", query = "SELECT c FROM CalidadPlanAccion c WHERE c.accionEficaz = :accionEficaz")})
public class CalidadPlanAccion implements Serializable {

    @JoinColumn(name = "id_au_aspecto_evaluar", referencedColumnName = "id_au_aspecto_evaluar")
    @ManyToOne
    private AuAspectoEvaluar idAuAspectoEvaluar;
    @JoinColumn(name = "id_plan_auditoria", referencedColumnName = "id_plan_auditoria")
    @ManyToOne
    private AuPlanAuditoria idPlanAuditoria;

    @Size(max = 100)
    @Column(name = "titulo_plan")
    private String tituloPlan;

    @JoinColumn(name = "responsable", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario responsable;
    @Size(max = 80)
    @Column(name = "revisor")
    private String revisor;
    @Column(name = "fecha_cierre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierre;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_plan_accion")
    private Integer idPlanAccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_analisis")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnalisis;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_plan")
    private String tipoPlan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "origen_accion")
    private String origenAccion;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 2147483647)
    @Column(name = "observacion_general")
    private String observacionGeneral;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "estado_plan")
    private String estadoPlan;
    @Size(max = 2)
    @Column(name = "criterio1")
    private String criterio1;
    @Size(max = 2)
    @Column(name = "criterio2")
    private String criterio2;
    @Size(max = 2)
    @Column(name = "accion_eficaz")
    private String accionEficaz;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "comentarios")
    private String comentarios;
    @JoinTable(name = "plan_accion_has_accion_implementar", joinColumns = {
        @JoinColumn(name = "id_plan_accion", referencedColumnName = "id_plan_accion")}, inverseJoinColumns = {
        @JoinColumn(name = "id_accion_implementar", referencedColumnName = "id_accion_implementar")})
    @ManyToMany
    private List<CalidadAccionImplementar> calidadAccionImplementarList;

    @JoinTable(name = "calidad_plan_accion_correctiva", joinColumns = {
        @JoinColumn(name = "id_plan_accion", referencedColumnName = "id_plan_accion")}, inverseJoinColumns = {
        @JoinColumn(name = "id_accion_correctiva", referencedColumnName = "id_accion_correctiva")})
    @ManyToMany
    private List<AccionCorrectiva> accionCorrectivaList;

    @JoinColumn(name = "id_subproceso", referencedColumnName = "id_subproceso")
    @ManyToOne(optional = false)
    private Subprocesos idSubproceso;

    @JoinColumn(name = "id_subproceso_apoyo_uno", referencedColumnName = "id_subproceso")
    @ManyToOne(optional = false)
    private Subprocesos idSubprocesoApoyoUno;

    @JoinColumn(name = "id_subproceso_apoyo_dos", referencedColumnName = "id_subproceso")
    @ManyToOne(optional = false)
    private Subprocesos idSubprocesoApoyoDos;

    @JoinTable(name = "plan_accion_has_causa", joinColumns = {
        @JoinColumn(name = "id_plan_accion", referencedColumnName = "id_plan_accion")}, inverseJoinColumns = {
        @JoinColumn(name = "id_causa", referencedColumnName = "id_causa")})
    @ManyToMany
    private List<CalidadCausa> calidadCausaList;


    public CalidadPlanAccion() {
    }

    public CalidadPlanAccion(Integer idPlanAccion) {
        this.idPlanAccion = idPlanAccion;
    }

    public CalidadPlanAccion(Integer idPlanAccion, String responsable, Date fechaAnalisis, String tipoPlan, String origenAccion, String fechaCierrePlan, String estadoPlan) {
        this.idPlanAccion = idPlanAccion;
        this.fechaAnalisis = fechaAnalisis;
        this.tipoPlan = tipoPlan;
        this.origenAccion = origenAccion;
        this.estadoPlan = estadoPlan;
    }

    public Integer getIdPlanAccion() {
        return idPlanAccion;
    }

    public void setIdPlanAccion(Integer idPlanAccion) {
        this.idPlanAccion = idPlanAccion;
    }

    public Date getFechaAnalisis() {
        return fechaAnalisis;
    }

    public void setFechaAnalisis(Date fechaAnalisis) {
        this.fechaAnalisis = fechaAnalisis;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public String getOrigenAccion() {
        return origenAccion;
    }

    public void setOrigenAccion(String origenAccion) {
        this.origenAccion = origenAccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacionGeneral() {
        return observacionGeneral;
    }

    public void setObservacionGeneral(String observacionGeneral) {
        this.observacionGeneral = observacionGeneral;
    }

    public String getEstadoPlan() {
        return estadoPlan;
    }

    public void setEstadoPlan(String estadoPlan) {
        this.estadoPlan = estadoPlan;
    }

    public String getCriterio1() {
        return criterio1;
    }

    public void setCriterio1(String criterio1) {
        this.criterio1 = criterio1;
    }

    public String getCriterio2() {
        return criterio2;
    }

    public void setCriterio2(String criterio2) {
        this.criterio2 = criterio2;
    }

    public String getAccionEficaz() {
        return accionEficaz;
    }

    public void setAccionEficaz(String accionEficaz) {
        this.accionEficaz = accionEficaz;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @XmlTransient
    public List<CalidadAccionImplementar> getCalidadAccionImplementarList() {
        return calidadAccionImplementarList;
    }

    public void setCalidadAccionImplementarList(List<CalidadAccionImplementar> calidadAccionImplementarList) {
        this.calidadAccionImplementarList = calidadAccionImplementarList;
    }

    @XmlTransient
    public List<CalidadCausa> getCalidadCausaList() {
        return calidadCausaList;
    }

    public void setCalidadCausaList(List<CalidadCausa> calidadCausaList) {
        this.calidadCausaList = calidadCausaList;
    }

    public Subprocesos getIdSubproceso() {
        return idSubproceso;
    }

    public void setIdSubproceso(Subprocesos idSubproceso) {
        this.idSubproceso = idSubproceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanAccion != null ? idPlanAccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalidadPlanAccion)) {
            return false;
        }
        CalidadPlanAccion other = (CalidadPlanAccion) object;
        if ((this.idPlanAccion == null && other.idPlanAccion != null) || (this.idPlanAccion != null && !this.idPlanAccion.equals(other.idPlanAccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIdPlanAccion() + " | " + getTituloPlan();
    }

    public String getRevisor() {
        return revisor;
    }

    public void setRevisor(String revisor) {
        this.revisor = revisor;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }

    public String getTituloPlan() {
        return tituloPlan;
    }

    public void setTituloPlan(String tituloPlan) {
        this.tituloPlan = tituloPlan;
    }

    public String getFechaAñoString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("yyyy");
        convertido = fecha.format(fechaAnalisis);
        return convertido;
    }

    public String getFechaMesString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("MMMM");
        convertido = fecha.format(fechaAnalisis);
        return convertido;
    }

    public AuAspectoEvaluar getIdAuAspectoEvaluar() {
        return idAuAspectoEvaluar;
    }

    public void setIdAuAspectoEvaluar(AuAspectoEvaluar idAuAspectoEvaluar) {
        this.idAuAspectoEvaluar = idAuAspectoEvaluar;
    }

    public AuPlanAuditoria getIdPlanAuditoria() {
        return idPlanAuditoria;
    }

    public void setIdPlanAuditoria(AuPlanAuditoria idPlanAuditoria) {
        this.idPlanAuditoria = idPlanAuditoria;
    }

    public Subprocesos getIdSubprocesoApoyoUno() {
        return idSubprocesoApoyoUno;
    }

    public void setIdSubprocesoApoyoUno(Subprocesos idSubprocesoApoyoUno) {
        this.idSubprocesoApoyoUno = idSubprocesoApoyoUno;
    }

    public Subprocesos getIdSubprocesoApoyoDos() {
        return idSubprocesoApoyoDos;
    }

    public void setIdSubprocesoApoyoDos(Subprocesos idSubprocesoApoyoDos) {
        this.idSubprocesoApoyoDos = idSubprocesoApoyoDos;
    }

    @XmlTransient
    public List<AccionCorrectiva> getAccionCorrectivaList() {
        return accionCorrectivaList;
    }

    public void setAccionCorrectivaList(List<AccionCorrectiva> accionCorrectivaList) {
        this.accionCorrectivaList = accionCorrectivaList;
    }

}
