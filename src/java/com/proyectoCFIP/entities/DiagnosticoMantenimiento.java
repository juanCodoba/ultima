/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "diagnostico_mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiagnosticoMantenimiento.findAll", query = "SELECT d FROM DiagnosticoMantenimiento d"),
    @NamedQuery(name = "DiagnosticoMantenimiento.findByIdMantenimiento", query = "SELECT d FROM DiagnosticoMantenimiento d WHERE d.idMantenimiento = :idMantenimiento"),
    @NamedQuery(name = "DiagnosticoMantenimiento.findByIdComputador", query = "SELECT d FROM DiagnosticoMantenimiento d WHERE d.idCronogramaMantenimientos.idComputador = :idComputador"),
    @NamedQuery(name = "DiagnosticoMantenimiento.findByFechaRevision", query = "SELECT d FROM DiagnosticoMantenimiento d WHERE d.fechaRevision = :fechaRevision"),
    @NamedQuery(name = "DiagnosticoMantenimiento.findByTicket", query = "SELECT d FROM DiagnosticoMantenimiento d WHERE d.idCronogramaMantenimientos = :idCronogramaMantenimientos"),
    @NamedQuery(name = "DiagnosticoMantenimiento.findByDiagnosticosTotales", query = "SELECT d FROM DiagnosticoMantenimiento d ORDER BY d.idCronogramaMantenimientos.fechaInicioMantenimiento DESC"),
    @NamedQuery(name = "DiagnosticoMantenimiento.findByFechaEntrega", query = "SELECT d FROM DiagnosticoMantenimiento d WHERE d.fechaEntrega = :fechaEntrega")})
public class DiagnosticoMantenimiento implements Serializable {

    @JoinColumn(name = "id_tipo_diagnostico", referencedColumnName = "id_tipo_diagnostico")
    @ManyToOne
    private TipoDiagnostico idTipoDiagnostico;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mantenimiento")
    private Integer idMantenimiento;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "revision")
    private String revision;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "diagnostico")
    private String diagnostico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_revision")
    @Temporal(TemporalType.DATE)
    private Date fechaRevision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @JoinColumn(name = "tecnico_responsable", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario tecnicoResponsable;
    @JoinColumn(name = "id_cronograma_mantenimientos", referencedColumnName = "id_cronograma_mantenimientos")
    @ManyToOne(optional = false)
    private CronogramaMantenimientos idCronogramaMantenimientos;
    @Column(name = "mantenimiento_correctivo")
    private Boolean mantenimientoCorrectivo;

    public DiagnosticoMantenimiento() {
    }

    public DiagnosticoMantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public DiagnosticoMantenimiento(Integer idMantenimiento, String revision, String diagnostico, Date fechaRevision, Date fechaEntrega) {
        this.idMantenimiento = idMantenimiento;
        this.revision = revision;
        this.diagnostico = diagnostico;
        this.fechaRevision = fechaRevision;
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Usuario getTecnicoResponsable() {
        return tecnicoResponsable;
    }

    public void setTecnicoResponsable(Usuario tecnicoResponsable) {
        this.tecnicoResponsable = tecnicoResponsable;
    }

    public CronogramaMantenimientos getIdCronogramaMantenimientos() {
        return idCronogramaMantenimientos;
    }

    public void setIdCronogramaMantenimientos(CronogramaMantenimientos idCronogramaMantenimientos) {
        this.idCronogramaMantenimientos = idCronogramaMantenimientos;
    }
    public Boolean getMantenimientoCorrectivo() {
        return mantenimientoCorrectivo;
    }

    public void setMantenimientoCorrectivo(Boolean mantenimientoCorrectivo) {
        this.mantenimientoCorrectivo = mantenimientoCorrectivo;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMantenimiento != null ? idMantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiagnosticoMantenimiento)) {
            return false;
        }
        DiagnosticoMantenimiento other = (DiagnosticoMantenimiento) object;
        if ((this.idMantenimiento == null && other.idMantenimiento != null) || (this.idMantenimiento != null && !this.idMantenimiento.equals(other.idMantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.DiagnosticoMantenimiento[ idMantenimiento=" + idMantenimiento + " ]";
    }

    public TipoDiagnostico getIdTipoDiagnostico() {
        return idTipoDiagnostico;
    }

    public void setIdTipoDiagnostico(TipoDiagnostico idTipoDiagnostico) {
        this.idTipoDiagnostico = idTipoDiagnostico;
    }
    
}
