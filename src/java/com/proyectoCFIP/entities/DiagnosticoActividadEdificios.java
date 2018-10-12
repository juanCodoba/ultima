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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "diagnostico_actividad_edificios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiagnosticoActividadEdificios.findAll", query = "SELECT d FROM DiagnosticoActividadEdificios d"),
    @NamedQuery(name = "DiagnosticoActividadEdificios.findByIdDiagnosticoActividadEdificios", query = "SELECT d FROM DiagnosticoActividadEdificios d WHERE d.idDiagnosticoActividadEdificios = :idDiagnosticoActividadEdificios"),
    @NamedQuery(name = "DiagnosticoActividadEdificios.findByDescripcionDiagnostico", query = "SELECT d FROM DiagnosticoActividadEdificios d WHERE d.descripcionDiagnostico = :descripcionDiagnostico"),
    @NamedQuery(name = "DiagnosticoActividadEdificios.findByFechaDiagnostico", query = "SELECT d FROM DiagnosticoActividadEdificios d WHERE d.fechaDiagnostico = :fechaDiagnostico"),
    @NamedQuery(name = "DiagnosticoActividadEdificios.findByDescripcionRevision", query = "SELECT d FROM DiagnosticoActividadEdificios d WHERE d.descripcionRevision = :descripcionRevision"),
    @NamedQuery(name = "DiagnosticoActividadEdificios.findByTipoActividad", query = "SELECT d FROM DiagnosticoActividadEdificios d WHERE d.idCronogramaActividadesEdificios.idTipoActividad = :idTipoActividad ORDER BY d.idCronogramaActividadesEdificios ASC"),
    @NamedQuery(name = "DiagnosticoActividadEdificios.findByTicket", query = "SELECT d FROM DiagnosticoActividadEdificios d WHERE d.idCronogramaActividadesEdificios = :idCronogramaActividadesEdificios"),
    @NamedQuery(name = "DiagnosticoActividadEdificios.findByFechaRevision", query = "SELECT d FROM DiagnosticoActividadEdificios d WHERE d.fechaRevision = :fechaRevision")})
public class DiagnosticoActividadEdificios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_diagnostico_actividad_edificios")
    private Integer idDiagnosticoActividadEdificios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "descripcion_diagnostico")
    private String descripcionDiagnostico;
    @Column(name = "fecha_diagnostico")
    @Temporal(TemporalType.DATE)
    private Date fechaDiagnostico;
    @Size(max = 300)
    @Column(name = "descripcion_revision")
    private String descripcionRevision;
    @Column(name = "fecha_revision")
    @Temporal(TemporalType.DATE)
    private Date fechaRevision;
    @JoinColumn(name = "id_cronograma_actividades_edificios", referencedColumnName = "id_cronograma_actividades_edificios" )
    @ManyToOne(optional = true)
    private CronogramaActividadesEdificios idCronogramaActividadesEdificios;

    public DiagnosticoActividadEdificios() {
    }

    public DiagnosticoActividadEdificios(Integer idDiagnosticoActividadEdificios) {
        this.idDiagnosticoActividadEdificios = idDiagnosticoActividadEdificios;
    }

    public DiagnosticoActividadEdificios(Integer idDiagnosticoActividadEdificios, String descripcionDiagnostico) {
        this.idDiagnosticoActividadEdificios = idDiagnosticoActividadEdificios;
        this.descripcionDiagnostico = descripcionDiagnostico;
    }

    public Integer getIdDiagnosticoActividadEdificios() {
        return idDiagnosticoActividadEdificios;
    }

    public void setIdDiagnosticoActividadEdificios(Integer idDiagnosticoActividadEdificios) {
        this.idDiagnosticoActividadEdificios = idDiagnosticoActividadEdificios;
    }

    public String getDescripcionDiagnostico() {
        return descripcionDiagnostico;
    }

    public void setDescripcionDiagnostico(String descripcionDiagnostico) {
        this.descripcionDiagnostico = descripcionDiagnostico;
    }

    public Date getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(Date fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public String getDescripcionRevision() {
        return descripcionRevision;
    }

    public void setDescripcionRevision(String descripcionRevision) {
        this.descripcionRevision = descripcionRevision;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public CronogramaActividadesEdificios getIdCronogramaActividadesEdificios() {
        return idCronogramaActividadesEdificios;
    }

    public void setIdCronogramaActividadesEdificios(CronogramaActividadesEdificios idCronogramaActividadesEdificios) {
        this.idCronogramaActividadesEdificios = idCronogramaActividadesEdificios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDiagnosticoActividadEdificios != null ? idDiagnosticoActividadEdificios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiagnosticoActividadEdificios)) {
            return false;
        }
        DiagnosticoActividadEdificios other = (DiagnosticoActividadEdificios) object;
        if ((this.idDiagnosticoActividadEdificios == null && other.idDiagnosticoActividadEdificios != null) || (this.idDiagnosticoActividadEdificios != null && !this.idDiagnosticoActividadEdificios.equals(other.idDiagnosticoActividadEdificios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DiagnosticoActividadEdificios[ idDiagnosticoActividadEdificios=" + idDiagnosticoActividadEdificios + " ]";
    }
    
}
