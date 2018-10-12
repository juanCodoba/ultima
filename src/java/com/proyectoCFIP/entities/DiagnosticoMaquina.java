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
@Table(name = "diagnostico_maquina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiagnosticoMaquina.findAll", query = "SELECT d FROM DiagnosticoMaquina d"),
    @NamedQuery(name = "DiagnosticoMaquina.findByIdDiagnosticoMaquina", query = "SELECT d FROM DiagnosticoMaquina d WHERE d.idDiagnosticoMaquina = :idDiagnosticoMaquina"),
    @NamedQuery(name = "DiagnosticoMaquina.findByFechaRevision", query = "SELECT d FROM DiagnosticoMaquina d WHERE d.fechaRevision = :fechaRevision"),
    @NamedQuery(name = "DiagnosticoMaquina.findByTicket", query = "SELECT d FROM DiagnosticoMaquina d WHERE d.idCronogramaMantenimientoMaquina = :idCronogramaMantenimientoMaquina"),
    @NamedQuery(name = "DiagnosticoMaquina.findByFechaEntrega", query = "SELECT d FROM DiagnosticoMaquina d WHERE d.fechaEntrega = :fechaEntrega")})
public class DiagnosticoMaquina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_diagnostico_maquina")
    private Integer idDiagnosticoMaquina;
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
    @JoinColumn(name = "id_cronograma_mantenimiento_maquina", referencedColumnName = "id_cronograma_mantenimiento_maquina")
    @ManyToOne(optional = false)
    private CronogramaMantenimientoMaquina idCronogramaMantenimientoMaquina;
    @Column(name = "mantenimiento_cp")
    private Boolean mantenimientoCp;

    public DiagnosticoMaquina() {
    }

    public DiagnosticoMaquina(Integer idDiagnosticoMaquina) {
        this.idDiagnosticoMaquina = idDiagnosticoMaquina;
    }

    public DiagnosticoMaquina(Integer idDiagnosticoMaquina, String revision, String diagnostico, Date fechaRevision, Date fechaEntrega) {
        this.idDiagnosticoMaquina = idDiagnosticoMaquina;
        this.revision = revision;
        this.diagnostico = diagnostico;
        this.fechaRevision = fechaRevision;
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getIdDiagnosticoMaquina() {
        return idDiagnosticoMaquina;
    }

    public void setIdDiagnosticoMaquina(Integer idDiagnosticoMaquina) {
        this.idDiagnosticoMaquina = idDiagnosticoMaquina;
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

    public CronogramaMantenimientoMaquina getIdCronogramaMantenimientoMaquina() {
        return idCronogramaMantenimientoMaquina;
    }

    public void setIdCronogramaMantenimientoMaquina(CronogramaMantenimientoMaquina idCronogramaMantenimientoMaquina) {
        this.idCronogramaMantenimientoMaquina = idCronogramaMantenimientoMaquina;
    }

    public Boolean getMantenimientoCp() {
        return mantenimientoCp;
    }

    public void setMantenimientoCp(Boolean mantenimientoCp) {
        this.mantenimientoCp = mantenimientoCp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDiagnosticoMaquina != null ? idDiagnosticoMaquina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiagnosticoMaquina)) {
            return false;
        }
        DiagnosticoMaquina other = (DiagnosticoMaquina) object;
        if ((this.idDiagnosticoMaquina == null && other.idDiagnosticoMaquina != null) || (this.idDiagnosticoMaquina != null && !this.idDiagnosticoMaquina.equals(other.idDiagnosticoMaquina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.DiagnosticoMaquina[ idDiagnosticoMaquina=" + idDiagnosticoMaquina + " ]";
    }
    
}
