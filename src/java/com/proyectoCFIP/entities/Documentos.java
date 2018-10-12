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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "documentos")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Documentos.findAll", query = "SELECT d FROM Documentos d"),
    @NamedQuery(name = "Documentos.findByIdDocumento", query = "SELECT d FROM Documentos d WHERE d.idDocumento = :idDocumento"),
    @NamedQuery(name = "Documentos.findByCodigoDocumento", query = "SELECT d FROM Documentos d WHERE d.codigoDocumento = :codigoDocumento"),
    @NamedQuery(name = "Documentos.findByNombreDocumento", query = "SELECT d FROM Documentos d WHERE d.nombreDocumento = :nombreDocumento"),
    @NamedQuery(name = "Documentos.findByVersion", query = "SELECT d FROM Documentos d WHERE d.version = :version"),
    @NamedQuery(name = "Documentos.findBySubProceso", query = "SELECT d FROM Documentos d WHERE d.idProceso = :idProceso ORDER BY d.codigoDocumento"),
    @NamedQuery(name = "Documentos.findByProceso", query = "SELECT d FROM Documentos d WHERE d.idProceso = :idProceso AND d.idTipoCalidad =:idTipoCalidad"),
    @NamedQuery(name = "Documentos.findByFecha", query = "SELECT d FROM Documentos d WHERE d.fecha = :fecha")})
public class Documentos implements Serializable {

   
    @Size(max = 350)
    @Column(name = "link_documento")
    private String linkDocumento;

    @Column(name = "version")
    private Short version;
    @Column(name = "id_tipo_calidad")
    private Integer idTipoCalidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocumento")
    private List<HistorialDocumento> historialDocumentoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_documento")
    private Integer idDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigo_documento")
    private String codigoDocumento;
    @Size(max = 70)
    @Column(name = "nombre_documento")
    private String nombreDocumento;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso")
    @ManyToOne(optional = false)
    private Procesos idProceso;
    @JoinColumn(name = "id_subproceso", referencedColumnName = "id_subproceso")
    @ManyToOne (optional = false)
    private Subprocesos idSubproceso;
    @OneToMany(mappedBy = "idDocumentoInstructivo")
    private List<Formatos> formatosList;
    @JoinTable(name = "usuario_has_documentos", joinColumns = {
        @JoinColumn(name = "id_documento", referencedColumnName = "id_documento")}, inverseJoinColumns = {
        @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")})
    @ManyToMany
    private List<Usuario> usuarioList;
  
    public Documentos() {
    }

    public Documentos(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Documentos(Integer idDocumento, String codigoDocumento, short version, Date fecha) {
        this.idDocumento = idDocumento;
        this.codigoDocumento = codigoDocumento;
        this.version = version;
        this.fecha = fecha;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }


    public String getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(String codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }



    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String getSubprocesoNombre(){
        if (idSubproceso==null){
            return "(N/A)";
        }else{
            return idSubproceso.getNombreSubproceso();
        }
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
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }
   

    @XmlTransient
    public List<Formatos> getFormatosList() {
        return formatosList;
    }

    public void setFormatosList(List<Formatos> formatosList) {
        this.formatosList = formatosList;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumento != null ? idDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documentos)) {
            return false;
        }
        Documentos other = (Documentos) object;
        if ((this.idDocumento == null && other.idDocumento != null) || (this.idDocumento != null && !this.idDocumento.equals(other.idDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getCodigoDocumento()+"-"+getNombreDocumento().toUpperCase();
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }


    public Integer getIdTipoCalidad() {
        return idTipoCalidad;
    }

    public void setIdTipoCalidad(Integer idTipoCalidad) {
        this.idTipoCalidad = idTipoCalidad;
    }

    @XmlTransient
    public List<HistorialDocumento> getHistorialDocumentoList() {
        return historialDocumentoList;
    }

    public void setHistorialDocumentoList(List<HistorialDocumento> historialDocumentoList) {
        this.historialDocumentoList = historialDocumentoList;
    }
    

    public String getLinkDocumento() {
        return linkDocumento;
    }

    public void setLinkDocumento(String linkDocumento) {
        this.linkDocumento = linkDocumento;
    }
    
}
