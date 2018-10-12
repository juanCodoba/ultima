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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "diagnostico_reporte_siesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiagnosticoReporteSiesa.findAll", query = "SELECT d FROM DiagnosticoReporteSiesa d"),
    @NamedQuery(name = "DiagnosticoReporteSiesa.findByIdDiagnosticoReporteSiesa", query = "SELECT d FROM DiagnosticoReporteSiesa d WHERE d.idDiagnosticoReporteSiesa = :idDiagnosticoReporteSiesa"),
    @NamedQuery(name = "DiagnosticoReporteSiesa.findByRevision", query = "SELECT d FROM DiagnosticoReporteSiesa d WHERE d.revision = :revision"),
    @NamedQuery(name = "DiagnosticoReporteSiesa.findByDiagnostico", query = "SELECT d FROM DiagnosticoReporteSiesa d WHERE d.diagnostico = :diagnostico"),
    @NamedQuery(name = "DiagnosticoReporteSiesa.findByTicket", query = "SELECT d FROM DiagnosticoReporteSiesa d WHERE d.idReporteSiesa = :idReporteSiesa"),
    @NamedQuery(name = "DiagnosticoReporteSiesa.findByFechaRevision", query = "SELECT d FROM DiagnosticoReporteSiesa d WHERE d.fechaRevision = :fechaRevision"),
    @NamedQuery(name = "DiagnosticoReporteSiesa.findByFechaDiagnostico", query = "SELECT d FROM DiagnosticoReporteSiesa d WHERE d.fechaDiagnostico = :fechaDiagnostico"),
    @NamedQuery(name = "DiagnosticoReporteSiesa.findByCodigoTicketSiesa", query = "SELECT d FROM DiagnosticoReporteSiesa d WHERE d.codigoTicketSiesa = :codigoTicketSiesa"),
    @NamedQuery(name = "DiagnosticoReporteSiesa.findByDescripcionTicketSiesa", query = "SELECT d FROM DiagnosticoReporteSiesa d WHERE d.descripcionTicketSiesa = :descripcionTicketSiesa")})
public class DiagnosticoReporteSiesa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_diagnostico_reporte_siesa")
    private Integer idDiagnosticoReporteSiesa;
    @Size(max = 300)
    @Column(name = "revision")
    private String revision;
    @Size(max = 300)
    @Column(name = "diagnostico")
    private String diagnostico;
    @Column(name = "fecha_revision")
    @Temporal(TemporalType.DATE)
    private Date fechaRevision;
    @Column(name = "fecha_diagnostico")
    @Temporal(TemporalType.DATE)
    private Date fechaDiagnostico;
    @Size(max = 45)
    @Column(name = "codigo_ticket_siesa")
    private String codigoTicketSiesa;
    @Size(max = 300)
    @Column(name = "descripcion_ticket_siesa")
    private String descripcionTicketSiesa;
    @JoinColumn(name = "id_reporte_siesa", referencedColumnName = "id_reporte_siesa")
    @ManyToOne(optional = false)
    private ReporteSiesa idReporteSiesa;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    public DiagnosticoReporteSiesa() {
    }

    public DiagnosticoReporteSiesa(Integer idDiagnosticoReporteSiesa) {
        this.idDiagnosticoReporteSiesa = idDiagnosticoReporteSiesa;
    }

    public Integer getIdDiagnosticoReporteSiesa() {
        return idDiagnosticoReporteSiesa;
    }

    public void setIdDiagnosticoReporteSiesa(Integer idDiagnosticoReporteSiesa) {
        this.idDiagnosticoReporteSiesa = idDiagnosticoReporteSiesa;
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

    public Date getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(Date fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public String getCodigoTicketSiesa() {
        return codigoTicketSiesa;
    }

    public void setCodigoTicketSiesa(String codigoTicketSiesa) {
        this.codigoTicketSiesa = codigoTicketSiesa;
    }

    public String getDescripcionTicketSiesa() {
        return descripcionTicketSiesa;
    }

    public void setDescripcionTicketSiesa(String descripcionTicketSiesa) {
        this.descripcionTicketSiesa = descripcionTicketSiesa;
    }

    public ReporteSiesa getIdReporteSiesa() {
        return idReporteSiesa;
    }

    public void setIdReporteSiesa(ReporteSiesa idReporteSiesa) {
        this.idReporteSiesa = idReporteSiesa;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDiagnosticoReporteSiesa != null ? idDiagnosticoReporteSiesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiagnosticoReporteSiesa)) {
            return false;
        }
        DiagnosticoReporteSiesa other = (DiagnosticoReporteSiesa) object;
        if ((this.idDiagnosticoReporteSiesa == null && other.idDiagnosticoReporteSiesa != null) || (this.idDiagnosticoReporteSiesa != null && !this.idDiagnosticoReporteSiesa.equals(other.idDiagnosticoReporteSiesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DiagnosticoReporteSiesa[ idDiagnosticoReporteSiesa=" + idDiagnosticoReporteSiesa + " ]";
    }
    
}
