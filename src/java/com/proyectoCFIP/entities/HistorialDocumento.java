/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "historial_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialDocumento.findAll", query = "SELECT h FROM HistorialDocumento h"),
    @NamedQuery(name = "HistorialDocumento.findByIdHistorialDocumento", query = "SELECT h FROM HistorialDocumento h WHERE h.idHistorialDocumento = :idHistorialDocumento"),
    @NamedQuery(name = "HistorialDocumento.findByNombreDocumento", query = "SELECT h FROM HistorialDocumento h WHERE h.nombreDocumento = :nombreDocumento"),
    @NamedQuery(name = "HistorialDocumento.findByVersion", query = "SELECT h FROM HistorialDocumento h WHERE h.version = :version"),
    @NamedQuery(name = "HistorialDocumento.findByFecha", query = "SELECT h FROM HistorialDocumento h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "HistorialDocumento.findByFechaHistorial", query = "SELECT h FROM HistorialDocumento h WHERE h.fechaHistorial = :fechaHistorial"),
    @NamedQuery(name = "HistorialDocumento.findByConsultaDocu", query = "SELECT h FROM HistorialDocumento h WHERE h.idDocumento =:idDocumento"),
    @NamedQuery(name = "HistorialDocumento.findByProceso", query = "SELECT h FROM HistorialDocumento h WHERE h.proceso = :proceso"),
    @NamedQuery(name = "HistorialDocumento.findBySubproceso", query = "SELECT h FROM HistorialDocumento h WHERE h.subproceso = :subproceso")})
public class HistorialDocumento implements Serializable {

    @Lob
    @Column(name = "documento")
    private byte[] documento;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historial_documento")
    private Integer idHistorialDocumento;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 70)
    @Column(name = "nombre_documento")
    private String nombreDocumento;
    @Column(name = "version")
    private Short version;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "fecha_historial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHistorial;
    @Size(max = 45)
    @Column(name = "proceso")
    private String proceso;
    @Size(max = 45)
    @Column(name = "subproceso")
    private String subproceso;
    @JoinColumn(name = "id_documento", referencedColumnName = "id_documento")
    @ManyToOne(optional = false)
    private Documentos idDocumento;

    public HistorialDocumento() {
    }

    public HistorialDocumento(Integer idHistorialDocumento) {
        this.idHistorialDocumento = idHistorialDocumento;
    }

    public HistorialDocumento(Integer idHistorialDocumento, String descripcion) {
        this.idHistorialDocumento = idHistorialDocumento;
        this.descripcion = descripcion;
    }

    public Integer getIdHistorialDocumento() {
        return idHistorialDocumento;
    }

    public void setIdHistorialDocumento(Integer idHistorialDocumento) {
        this.idHistorialDocumento = idHistorialDocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaHistorial() {
        return fechaHistorial;
    }

    public void setFechaHistorial(Date fechaHistorial) {
        this.fechaHistorial = fechaHistorial;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getSubproceso() {
        return subproceso;
    }

    public void setSubproceso(String subproceso) {
        this.subproceso = subproceso;
    }


    public Documentos getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Documentos idDocumento) {
        this.idDocumento = idDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorialDocumento != null ? idHistorialDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialDocumento)) {
            return false;
        }
        HistorialDocumento other = (HistorialDocumento) object;
        if ((this.idHistorialDocumento == null && other.idHistorialDocumento != null) || (this.idHistorialDocumento != null && !this.idHistorialDocumento.equals(other.idHistorialDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.HistorialDocumento[ idHistorialDocumento=" + idHistorialDocumento + " ]";
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }
    
}
