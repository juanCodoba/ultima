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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "formatos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formatos.findAll", query = "SELECT f FROM Formatos f ORDER BY f.codigoFormato"),
    @NamedQuery(name = "Formatos.findByIdFormato", query = "SELECT f FROM Formatos f WHERE f.idFormato = :idFormato"),
    @NamedQuery(name = "Formatos.findByCodigoFormato", query = "SELECT f FROM Formatos f WHERE f.codigoFormato = :codigoFormato"),
    @NamedQuery(name = "Formatos.findByNombreFormato", query = "SELECT f FROM Formatos f WHERE f.nombreFormato = :nombreFormato"),
    @NamedQuery(name = "Formatos.findByDiligenciado", query = "SELECT f FROM Formatos f WHERE f.diligenciado = :diligenciado"),
    @NamedQuery(name = "Formatos.findByAlmacenamiento", query = "SELECT f FROM Formatos f WHERE f.almacenamiento = :almacenamiento"),
    @NamedQuery(name = "Formatos.findByRecuperacion", query = "SELECT f FROM Formatos f WHERE f.recuperacion = :recuperacion"),
    @NamedQuery(name = "Formatos.findByTiempoRetencion", query = "SELECT f FROM Formatos f WHERE f.tiempoRetencion = :tiempoRetencion"),
    @NamedQuery(name = "Formatos.findBySubProceso", query = "SELECT f FROM Formatos f WHERE f.idSubproceso = :idSubproceso ORDER BY f.codigoFormato"),
    @NamedQuery(name = "Formatos.findByMetodo", query = "SELECT f FROM Formatos f WHERE f.metodo = :metodo")})
public class Formatos implements Serializable {

   
    @Size(max = 300)
    @Column(name = "link_formato")
    private String linkFormato;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_formato")
    private Integer idFormato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigo_formato")
    private String codigoFormato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre_formato")
    private String nombreFormato;
    @Basic(optional = false)
    @Size(min = 1, max = 250)
    @Column(name = "diligenciado")
    private String diligenciado;
    @Basic(optional = false)
    @Size(min = 1, max = 250)
    @Column(name = "almacenamiento")
    private String almacenamiento;
    @Basic(optional = false)
    @Size(min = 1, max = 250)
    @Column(name = "recuperacion")
    private String recuperacion;
    @Size(max = 100)
    @Column(name = "tiempo_retencion")
    private String tiempoRetencion;
    @Basic(optional = false)
    @Size(min = 1, max = 250)
    @Column(name = "metodo")
    private String metodo;
    @JoinColumn(name = "id_documento_instructivo", referencedColumnName = "id_documento")
    @ManyToOne
    private Documentos idDocumentoInstructivo;
    @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso")
    @ManyToOne(optional = false)
    private Procesos idProceso;
    @JoinColumn(name = "id_subproceso", referencedColumnName = "id_subproceso")
    @ManyToOne (optional = false)
    private Subprocesos idSubproceso;
    @Size(min = 1, max = 5)
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormato")
    private List<CambioRegistro> cambioRegistroList;
    @Column(name = "version")
    private Double version;
    
    public Formatos() {
    }

    public Formatos(Integer idFormato) {
        this.idFormato = idFormato;
    }
    public Formatos(Integer idFormato, String codigoFormato, String nombreFormato, String diligenciado, String almacenamiento, String recuperacion, String metodo, String tipoDocumento) {
        this.idFormato = idFormato;
        this.codigoFormato = codigoFormato;
        this.nombreFormato = nombreFormato;
        this.diligenciado = diligenciado;
        this.almacenamiento = almacenamiento;
        this.recuperacion = recuperacion;
        this.metodo = metodo;
        this.tipoDocumento = tipoDocumento;
    }
    public Integer getIdFormato() {
        return idFormato;
    }

    public void setIdFormato(Integer idFormato) {
        this.idFormato = idFormato;
    }

    public String getCodigoFormato() {
        return codigoFormato;
    }

    public void setCodigoFormato(String codigoFormato) {
        this.codigoFormato = codigoFormato;
    }

    public String getNombreFormato() {
        return nombreFormato;
    }

    public void setNombreFormato(String nombreFormato) {
        this.nombreFormato = nombreFormato;
    }

    public String getDiligenciado() {
        return diligenciado;
    }

    public void setDiligenciado(String diligenciado) {
        this.diligenciado = diligenciado;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public String getRecuperacion() {
        return recuperacion;
    }

    public void setRecuperacion(String recuperacion) {
        this.recuperacion = recuperacion;
    }

    public String getTiempoRetencion() {
        return tiempoRetencion;
    }

    public void setTiempoRetencion(String tiempoRetencion) {
        this.tiempoRetencion = tiempoRetencion;
    }
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }


    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }
    
    public Documentos getIdDocumentoInstructivo() {
        return idDocumentoInstructivo;
    }

    public void setIdDocumentoInstructivo(Documentos idDocumentoInstructivo) {
        this.idDocumentoInstructivo = idDocumentoInstructivo;
    }

   

    public Procesos getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Procesos idProceso) {
        this.idProceso = idProceso;
    }

    public Subprocesos getIdSubproceso() {
        return idSubproceso;
    }

    public void setIdSubproceso(Subprocesos idSubproceso) {
        this.idSubproceso = idSubproceso;
    }
      @XmlTransient
    public List<CambioRegistro> getCambioRegistroList() {
        return cambioRegistroList;
    }

    public void setCambioRegistroList(List<CambioRegistro> cambioRegistroList) {
        this.cambioRegistroList = cambioRegistroList;
    }

    
    
    public String getSubprocesoNombre(){
        if (idSubproceso==null){
            return "(N/A)";
        }else{
            return idSubproceso.getNombreSubproceso();
        }
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormato != null ? idFormato.hashCode() : 0);
        return hash;
    }

  @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formatos)) {
            return false;
        }
        Formatos other = (Formatos) object;
        if ((this.idFormato == null && other.idFormato != null) || (this.idFormato != null && !this.idFormato.equals(other.idFormato))) {
            return false;
        }
        return true;
    }

     @Override
    public String toString() {
        return getCodigoFormato().toUpperCase()+" / "+ getNombreFormato().toUpperCase();
    }
    public String getLinkFormato() {
        return linkFormato;
    }

    public void setLinkFormato(String linkFormato) {
        this.linkFormato = linkFormato;
    }
    
}
