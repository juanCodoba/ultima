/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.Date;
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
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "calidad_seguimiento_correctiva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalidadSeguimientoCorrectiva.findAll", query = "SELECT c FROM CalidadSeguimientoCorrectiva c"),
    @NamedQuery(name = "CalidadSeguimientoCorrectiva.findByIdCalidadSeguimientoCorrectiva", query = "SELECT c FROM CalidadSeguimientoCorrectiva c WHERE c.idCalidadSeguimientoCorrectiva = :idCalidadSeguimientoCorrectiva"),
    @NamedQuery(name = "CalidadSeguimientoCorrectiva.findByResultado", query = "SELECT c FROM CalidadSeguimientoCorrectiva c WHERE c.resultado = :resultado"),
    @NamedQuery(name = "CalidadSeguimientoCorrectiva.findByFecha", query = "SELECT c FROM CalidadSeguimientoCorrectiva c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "CalidadSeguimientoCorrectiva.findByAccionDos", query = "SELECT c FROM CalidadSeguimientoCorrectiva c WHERE c.IdaccionCorrectiva = :IdaccionCorrectiva"),
    @NamedQuery(name = "CalidadSeguimientoCorrectiva.findByEstado", query = "SELECT c FROM CalidadSeguimientoCorrectiva c WHERE c.estado = 'ABIERTO'")})
public class CalidadSeguimientoCorrectiva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calidad_seguimiento_correctiva")
    private Integer idCalidadSeguimientoCorrectiva;
    @NotNull
    @Size(max = 45)
    @Column(name = "resultado")
    private String resultado;
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @NotNull
    @Size(max = 2147483647)
    @Column(name = "responsable")
    private String responsable;
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_accion_correctiva", referencedColumnName = "id_accion_correctiva")
    @ManyToOne(optional = false)
    private AccionCorrectiva IdaccionCorrectiva;

    public CalidadSeguimientoCorrectiva() {
    }

    public CalidadSeguimientoCorrectiva(Integer idCalidadSeguimientoCorrectiva) {
        this.idCalidadSeguimientoCorrectiva = idCalidadSeguimientoCorrectiva;
    }

    public CalidadSeguimientoCorrectiva(Integer idCalidadSeguimientoCorrectiva, String resultado, Date fecha, String responsable, String estado) {
        this.idCalidadSeguimientoCorrectiva = idCalidadSeguimientoCorrectiva;
        this.resultado = resultado;
        this.fecha = fecha;
        this.responsable = responsable;
        this.estado = estado;
    }

    public Integer getIdCalidadSeguimientoCorrectiva() {
        return idCalidadSeguimientoCorrectiva;
    }

    public void setIdCalidadSeguimientoCorrectiva(Integer idCalidadSeguimientoCorrectiva) {
        this.idCalidadSeguimientoCorrectiva = idCalidadSeguimientoCorrectiva;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalidadSeguimientoCorrectiva != null ? idCalidadSeguimientoCorrectiva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalidadSeguimientoCorrectiva)) {
            return false;
        }
        CalidadSeguimientoCorrectiva other = (CalidadSeguimientoCorrectiva) object;
        if ((this.idCalidadSeguimientoCorrectiva == null && other.idCalidadSeguimientoCorrectiva != null) || (this.idCalidadSeguimientoCorrectiva != null && !this.idCalidadSeguimientoCorrectiva.equals(other.idCalidadSeguimientoCorrectiva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.CalidadSeguimientoCorrectiva[ idCalidadSeguimientoCorrectiva=" + idCalidadSeguimientoCorrectiva + " ]";
    }

    public AccionCorrectiva getIdaccionCorrectiva() {
        return IdaccionCorrectiva;
    }

    public void setIdaccionCorrectiva(AccionCorrectiva IdaccionCorrectiva) {
        this.IdaccionCorrectiva = IdaccionCorrectiva;
    }



}
