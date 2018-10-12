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
@Table(name = "diagnostico_mantenimiento_dis_con")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiagnosticoMantenimientoDisCon.findAll", query = "SELECT d FROM DiagnosticoMantenimientoDisCon d"),
    @NamedQuery(name = "DiagnosticoMantenimientoDisCon.findByIdDiagnosticoManteDisCon", query = "SELECT d FROM DiagnosticoMantenimientoDisCon d WHERE d.idCronogramaManteDisCon = :idCronogramaManteDisCon"),
    @NamedQuery(name = "DiagnosticoMantenimientoDisCon.findByFechaRevisionDisCon", query = "SELECT d FROM DiagnosticoMantenimientoDisCon d WHERE d.fechaRevisionDisCon = :fechaRevisionDisCon"),
    @NamedQuery(name = "DiagnosticoMantenimientoDisCon.findByTicket", query = "SELECT d FROM DiagnosticoMantenimientoDisCon d WHERE d.idCronogramaManteDisCon = :idCronogramaManteDisCon"),
    @NamedQuery(name = "DiagnosticoMantenimientoDisCon.findByFechaEntragaDisCon", query = "SELECT d FROM DiagnosticoMantenimientoDisCon d WHERE d.fechaEntragaDisCon = :fechaEntragaDisCon"),
    @NamedQuery(name = "DiagnosticoMantenimientoDisCon.findByMantenimientoCorrectivo", query = "SELECT d FROM DiagnosticoMantenimientoDisCon d WHERE d.mantenimientoCorrectivo = :mantenimientoCorrectivo")})
public class DiagnosticoMantenimientoDisCon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_diagnostico_mante_dis_con")
    private Integer idDiagnosticoManteDisCon;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "revision_dis_con")
    private String revisionDisCon;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "diagnostico_dis_con")
    private String diagnosticoDisCon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_revision_dis_con")
    @Temporal(TemporalType.DATE)
    private Date fechaRevisionDisCon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_entraga_dis_con")
    @Temporal(TemporalType.DATE)
    private Date fechaEntragaDisCon;
    @Column(name = "mantenimiento_correctivo")
    private Boolean mantenimientoCorrectivo;
    @JoinColumn(name = "id_cronograma_mante_dis_con", referencedColumnName = "id_cronograma_mante_dis_con")
    @ManyToOne(optional = false)
    private CronogramaManteDisCon idCronogramaManteDisCon;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public DiagnosticoMantenimientoDisCon() {
    }

    public DiagnosticoMantenimientoDisCon(Integer idDiagnosticoManteDisCon) {
        this.idDiagnosticoManteDisCon = idDiagnosticoManteDisCon;
    }

    public DiagnosticoMantenimientoDisCon(Integer idDiagnosticoManteDisCon, String revisionDisCon, String diagnosticoDisCon, Date fechaRevisionDisCon, Date fechaEntragaDisCon) {
        this.idDiagnosticoManteDisCon = idDiagnosticoManteDisCon;
        this.revisionDisCon = revisionDisCon;
        this.diagnosticoDisCon = diagnosticoDisCon;
        this.fechaRevisionDisCon = fechaRevisionDisCon;
        this.fechaEntragaDisCon = fechaEntragaDisCon;
    }

    public Integer getIdDiagnosticoManteDisCon() {
        return idDiagnosticoManteDisCon;
    }

    public void setIdDiagnosticoManteDisCon(Integer idDiagnosticoManteDisCon) {
        this.idDiagnosticoManteDisCon = idDiagnosticoManteDisCon;
    }

    public String getRevisionDisCon() {
        return revisionDisCon;
    }

    public void setRevisionDisCon(String revisionDisCon) {
        this.revisionDisCon = revisionDisCon;
    }

    public String getDiagnosticoDisCon() {
        return diagnosticoDisCon;
    }

    public void setDiagnosticoDisCon(String diagnosticoDisCon) {
        this.diagnosticoDisCon = diagnosticoDisCon;
    }

    public Date getFechaRevisionDisCon() {
        return fechaRevisionDisCon;
    }

    public void setFechaRevisionDisCon(Date fechaRevisionDisCon) {
        this.fechaRevisionDisCon = fechaRevisionDisCon;
    }

    public Date getFechaEntragaDisCon() {
        return fechaEntragaDisCon;
    }

    public void setFechaEntragaDisCon(Date fechaEntragaDisCon) {
        this.fechaEntragaDisCon = fechaEntragaDisCon;
    }

    public Boolean getMantenimientoCorrectivo() {
        return mantenimientoCorrectivo;
    }

    public void setMantenimientoCorrectivo(Boolean mantenimientoCorrectivo) {
        this.mantenimientoCorrectivo = mantenimientoCorrectivo;
    }

    public CronogramaManteDisCon getIdCronogramaManteDisCon() {
        return idCronogramaManteDisCon;
    }

    public void setIdCronogramaManteDisCon(CronogramaManteDisCon idCronogramaManteDisCon) {
        this.idCronogramaManteDisCon = idCronogramaManteDisCon;
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
        hash += (idDiagnosticoManteDisCon != null ? idDiagnosticoManteDisCon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiagnosticoMantenimientoDisCon)) {
            return false;
        }
        DiagnosticoMantenimientoDisCon other = (DiagnosticoMantenimientoDisCon) object;
        if ((this.idDiagnosticoManteDisCon == null && other.idDiagnosticoManteDisCon != null) || (this.idDiagnosticoManteDisCon != null && !this.idDiagnosticoManteDisCon.equals(other.idDiagnosticoManteDisCon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DiagnosticoMantenimientoDisCon[ idDiagnosticoManteDisCon=" + idDiagnosticoManteDisCon + " ]";
    }
    
}
