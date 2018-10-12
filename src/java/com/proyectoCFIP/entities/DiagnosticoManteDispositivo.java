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
@Table(name = "diagnostico_mante_dispositivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiagnosticoManteDispositivo.findAll", query = "SELECT d FROM DiagnosticoManteDispositivo d"),
    @NamedQuery(name = "DiagnosticoManteDispositivo.findByIdDiagnosticoManteDispositivo", query = "SELECT d FROM DiagnosticoManteDispositivo d WHERE d.idDiagnosticoManteDispositivo = :idDiagnosticoManteDispositivo"),
    @NamedQuery(name = "DiagnosticoManteDispositivo.findByFechaRevisionDis", query = "SELECT d FROM DiagnosticoManteDispositivo d WHERE d.fechaRevisionDis = :fechaRevisionDis"),   
    @NamedQuery(name = "DiagnosticoManteDispositivo.findByTicket", query = "SELECT d FROM DiagnosticoManteDispositivo d WHERE d.idCronogramaManteDispositivo = :idCronogramaManteDispositivo"),
    @NamedQuery(name = "DiagnosticoManteDispositivo.findByIdOtroDispositivo", query = "SELECT d FROM DiagnosticoManteDispositivo d WHERE d.idCronogramaManteDispositivo.idOtroDispositivo = :idOtroDispositivo"),
    @NamedQuery(name = "DiagnosticoManteDispositivo.findByFechaEntregaDis", query = "SELECT d FROM DiagnosticoManteDispositivo d WHERE d.fechaEntregaDis = :fechaEntregaDis"),
    @NamedQuery(name = "DiagnosticoManteDispositivo.findByCambioRepuesto", query = "SELECT d FROM DiagnosticoManteDispositivo d WHERE d.cambioRepuesto = :cambioRepuesto")})
public class DiagnosticoManteDispositivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_diagnostico_mante_dispositivo")
    private Integer idDiagnosticoManteDispositivo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "revision_dis")
    private String revisionDis;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "diagnostico_dis")
    private String diagnosticoDis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_revision_dis")
    @Temporal(TemporalType.DATE)
    private Date fechaRevisionDis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_entrega_dis")
    @Temporal(TemporalType.DATE)
    private Date fechaEntregaDis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cambio_repuesto")
    private boolean cambioRepuesto;
    @JoinColumn(name = "id_cronograma_mante_dispositivo", referencedColumnName = "id_cronograma_mante_dispositivo")
    @ManyToOne(optional = false)
    private CronogramaManteDispositivo idCronogramaManteDispositivo;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @Column(name = "mantenimiento_correctivo")
    private Boolean mantenimientoCorrectivo;
    
    public DiagnosticoManteDispositivo() {
    }

    public DiagnosticoManteDispositivo(Integer idDiagnosticoManteDispositivo) {
        this.idDiagnosticoManteDispositivo = idDiagnosticoManteDispositivo;
    }

    public DiagnosticoManteDispositivo(Integer idDiagnosticoManteDispositivo, String revisionDis, String diagnosticoDis, Date fechaRevisionDis, Date fechaEntregaDis, boolean cambioRepuesto) {
        this.idDiagnosticoManteDispositivo = idDiagnosticoManteDispositivo;
        this.revisionDis = revisionDis;
        this.diagnosticoDis = diagnosticoDis;
        this.fechaRevisionDis = fechaRevisionDis;
        this.fechaEntregaDis = fechaEntregaDis;
        this.cambioRepuesto = cambioRepuesto;
    }

    public Integer getIdDiagnosticoManteDispositivo() {
        return idDiagnosticoManteDispositivo;
    }

    public void setIdDiagnosticoManteDispositivo(Integer idDiagnosticoManteDispositivo) {
        this.idDiagnosticoManteDispositivo = idDiagnosticoManteDispositivo;
    }

    public String getRevisionDis() {
        return revisionDis;
    }

    public void setRevisionDis(String revisionDis) {
        this.revisionDis = revisionDis;
    }

    public String getDiagnosticoDis() {
        return diagnosticoDis;
    }

    public void setDiagnosticoDis(String diagnosticoDis) {
        this.diagnosticoDis = diagnosticoDis;
    }

    public Date getFechaRevisionDis() {
        return fechaRevisionDis;
    }

    public void setFechaRevisionDis(Date fechaRevisionDis) {
        this.fechaRevisionDis = fechaRevisionDis;
    }

    public Date getFechaEntregaDis() {
        return fechaEntregaDis;
    }

    public void setFechaEntregaDis(Date fechaEntregaDis) {
        this.fechaEntregaDis = fechaEntregaDis;
    }
    public Boolean getMantenimientoCorrectivo() {
        return mantenimientoCorrectivo;
    }

    public void setMantenimientoCorrectivo(Boolean mantenimientoCorrectivo) {
        this.mantenimientoCorrectivo = mantenimientoCorrectivo;
    }

    public boolean getCambioRepuesto() {
        return cambioRepuesto;
    }
    public String getCambioIdioma(){
        if (cambioRepuesto==true){
            return "SI";
        }else{
            return "NO";
        }
    }

    public void setCambioRepuesto(boolean cambioRepuesto) {
        this.cambioRepuesto = cambioRepuesto;
    }

    public CronogramaManteDispositivo getIdCronogramaManteDispositivo() {
        return idCronogramaManteDispositivo;
    }

    public void setIdCronogramaManteDispositivo(CronogramaManteDispositivo idCronogramaManteDispositivo) {
        this.idCronogramaManteDispositivo = idCronogramaManteDispositivo;
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
        hash += (idDiagnosticoManteDispositivo != null ? idDiagnosticoManteDispositivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiagnosticoManteDispositivo)) {
            return false;
        }
        DiagnosticoManteDispositivo other = (DiagnosticoManteDispositivo) object;
        if ((this.idDiagnosticoManteDispositivo == null && other.idDiagnosticoManteDispositivo != null) || (this.idDiagnosticoManteDispositivo != null && !this.idDiagnosticoManteDispositivo.equals(other.idDiagnosticoManteDispositivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.DiagnosticoManteDispositivo[ idDiagnosticoManteDispositivo=" + idDiagnosticoManteDispositivo + " ]";
    }
    
}
