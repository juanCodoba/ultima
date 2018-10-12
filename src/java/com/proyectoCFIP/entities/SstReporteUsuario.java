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
 * @author junio
 */
@Entity
@Table(name = "sst_reporte_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SstReporteUsuario.findAll", query = "SELECT s FROM SstReporteUsuario s")
    , @NamedQuery(name = "SstReporteUsuario.findByIdSstIncidente", query = "SELECT s FROM SstReporteUsuario s WHERE s.idSstIncidente = :idSstIncidente")
    , @NamedQuery(name = "SstReporteUsuario.findByTipo", query = "SELECT s FROM SstReporteUsuario s WHERE s.tipo = :tipo")
    , @NamedQuery(name = "SstReporteUsuario.findByUsuario", query = "SELECT s FROM SstReporteUsuario s WHERE s.idUsuario = :idUsuario")
    , @NamedQuery(name = "SstReporteUsuario.findByObservaciones", query = "SELECT s FROM SstReporteUsuario s WHERE s.observaciones = :observaciones")})
public class SstReporteUsuario implements Serializable {

    @Size(max = 45)
    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 45)
    @Column(name = "sst_reporte_usuariocol")
    private String sstReporteUsuariocol;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sst_incidente")
    private Integer idSstIncidente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "recomendacion")
    private String recomendacion;
    @Size(max = 45)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_seccion", referencedColumnName = "id_seccion")
    @ManyToOne(optional = false)
    private Seccion idSeccion;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public SstReporteUsuario() {
    }

    public SstReporteUsuario(Integer idSstIncidente) {
        this.idSstIncidente = idSstIncidente;
    }

    public SstReporteUsuario(Integer idSstIncidente, String tipo, String descripcion, String recomendacion) {
        this.idSstIncidente = idSstIncidente;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.recomendacion = recomendacion;
    }

    public Integer getIdSstIncidente() {
        return idSstIncidente;
    }

    public void setIdSstIncidente(Integer idSstIncidente) {
        this.idSstIncidente = idSstIncidente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Seccion getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Seccion idSeccion) {
        this.idSeccion = idSeccion;
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
        hash += (idSstIncidente != null ? idSstIncidente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SstReporteUsuario)) {
            return false;
        }
        SstReporteUsuario other = (SstReporteUsuario) object;
        if ((this.idSstIncidente == null && other.idSstIncidente != null) || (this.idSstIncidente != null && !this.idSstIncidente.equals(other.idSstIncidente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.SstReporteUsuario[ idSstIncidente=" + idSstIncidente + " ]";
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getSstReporteUsuariocol() {
        return sstReporteUsuariocol;
    }

    public void setSstReporteUsuariocol(String sstReporteUsuariocol) {
        this.sstReporteUsuariocol = sstReporteUsuariocol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
