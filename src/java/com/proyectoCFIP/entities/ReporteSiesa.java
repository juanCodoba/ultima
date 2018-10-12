/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
@Table(name = "reporte_siesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteSiesa.findAll", query = "SELECT r FROM ReporteSiesa r"),
    @NamedQuery(name = "ReporteSiesa.findByIdReporteSiesa", query = "SELECT r FROM ReporteSiesa r WHERE r.idReporteSiesa = :idReporteSiesa"),
    @NamedQuery(name = "ReporteSiesa.findByFechaReporte", query = "SELECT r FROM ReporteSiesa r WHERE r.fechaReporte = :fechaReporte"),
    @NamedQuery(name = "ReporteSiesa.findByEstadoReporte", query = "SELECT r FROM ReporteSiesa r WHERE r.estadoReporte = :estadoReporte"),
    @NamedQuery(name = "ReporteSiesa.findByEstadoTicket", query = "SELECT r FROM ReporteSiesa r WHERE r.idEstadoTicket = :idEstadoTicket"),
    @NamedQuery(name = "ReporteSiesa.findByEstadoTicketSinDiag", query = "SELECT r FROM ReporteSiesa r WHERE r.idEstadoTicket = :idEstadoTicket OR r.idEstadoTicket = :idEstadoTicket2"),
    @NamedQuery(name = "ReporteSiesa.findByValoracion", query = "SELECT r FROM ReporteSiesa r WHERE r.valoracion = :valoracion"),
    @NamedQuery(name = "ReporteSiesa.findByDescripcionValoracion", query = "SELECT r FROM ReporteSiesa r WHERE r.descripcionValoracion = :descripcionValoracion"),
    @NamedQuery(name = "ReporteSiesa.findByUsuario", query = "SELECT r FROM ReporteSiesa r WHERE r.idUsuario = :idUsuario"),
    @NamedQuery(name = "ReporteSiesa.findByFechaValoracion", query = "SELECT r FROM ReporteSiesa r WHERE r.fechaValoracion = :fechaValoracion")})
public class ReporteSiesa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reporte_siesa")
    private Integer idReporteSiesa;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion_reporte")
    private String descripcionReporte;
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_reporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_reporte")
    private boolean estadoReporte;
    @Column(name = "valoracion")
    private Integer valoracion;
    @Size(max = 300)
    @Column(name = "descripcion_valoracion")
    private String descripcionValoracion;
    @Column(name = "fecha_valoracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaValoracion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReporteSiesa")
    private List<DiagnosticoReporteSiesa> diagnosticoReporteSiesaList;
    @JoinColumn(name = "id_estado_ticket", referencedColumnName = "id_estado_ticket")
    @ManyToOne(optional = false)
    private EstadoTicket idEstadoTicket;
    @JoinColumn(name = "id_tipo_error", referencedColumnName = "id_tipo_error")
    @ManyToOne(optional = false)
    private TipoError idTipoError;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public ReporteSiesa() {
    }

    public ReporteSiesa(Integer idReporteSiesa) {
        this.idReporteSiesa = idReporteSiesa;
    }

    public ReporteSiesa(Integer idReporteSiesa, String descripcionReporte, Date fechaReporte, boolean estadoReporte) {
        this.idReporteSiesa = idReporteSiesa;
        this.descripcionReporte = descripcionReporte;
        this.fechaReporte = fechaReporte;
        this.estadoReporte = estadoReporte;
    }

    public Integer getIdReporteSiesa() {
        return idReporteSiesa;
    }

    public void setIdReporteSiesa(Integer idReporteSiesa) {
        this.idReporteSiesa = idReporteSiesa;
    }

    public String getDescripcionReporte() {
        return descripcionReporte;
    }

    public void setDescripcionReporte(String descripcionReporte) {
        this.descripcionReporte = descripcionReporte;
    }

  

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    public DefaultStreamedContent getImagen() {
        try {
             InputStream is= new ByteArrayInputStream((byte[])imagen);
             return new DefaultStreamedContent(is,"image/png");
         } catch (Exception e) {
             return null;
         }
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public boolean getEstadoReporte() {
        return estadoReporte;
    }

    public void setEstadoReporte(boolean estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    public String getDescripcionValoracion() {
        return descripcionValoracion;
    }

    public void setDescripcionValoracion(String descripcionValoracion) {
        this.descripcionValoracion = descripcionValoracion;
    }

    public Date getFechaValoracion() {
        return fechaValoracion;
    }

    public void setFechaValoracion(Date fechaValoracion) {
        this.fechaValoracion = fechaValoracion;
    }

    @XmlTransient
    public List<DiagnosticoReporteSiesa> getDiagnosticoReporteSiesaList() {
        return diagnosticoReporteSiesaList;
    }

    public void setDiagnosticoReporteSiesaList(List<DiagnosticoReporteSiesa> diagnosticoReporteSiesaList) {
        this.diagnosticoReporteSiesaList = diagnosticoReporteSiesaList;
    }

    public EstadoTicket getIdEstadoTicket() {
        return idEstadoTicket;
    }

    public void setIdEstadoTicket(EstadoTicket idEstadoTicket) {
        this.idEstadoTicket = idEstadoTicket;
    }

    public TipoError getIdTipoError() {
        return idTipoError;
    }

    public void setIdTipoError(TipoError idTipoError) {
        this.idTipoError = idTipoError;
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
        hash += (idReporteSiesa != null ? idReporteSiesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteSiesa)) {
            return false;
        }
        ReporteSiesa other = (ReporteSiesa) object;
        if ((this.idReporteSiesa == null && other.idReporteSiesa != null) || (this.idReporteSiesa != null && !this.idReporteSiesa.equals(other.idReporteSiesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ReporteSiesa[ idReporteSiesa=" + idReporteSiesa + " ]";
    }
    public String getEstado(){
        if(idEstadoTicket.equals(new EstadoTicket(1)) ){
            return "Abierto";
        }else if(idEstadoTicket.equals(new EstadoTicket(2)) ){
            return "Siesa IT";
        }else if(idEstadoTicket.equals(new EstadoTicket(3)) ){
            return "Sin valoracion";
        }else if(idEstadoTicket.equals(new EstadoTicket(4)) ){
            return "Cerrado";
        }else{
            return null;
        }
    }
     public String getFechaAñoString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("yyyy");
	convertido = fecha.format(fechaReporte);
        return convertido;
    }
    public String getFechaMesString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("MM");
	convertido = fecha.format(fechaReporte);
        return convertido;
    }
  
    
}
