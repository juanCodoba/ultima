/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

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

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "solicitud_edificio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudEdificio.findAll", query = "SELECT s FROM SolicitudEdificio s"),
    @NamedQuery(name = "SolicitudEdificio.findByIdSolicitudEdificio", query = "SELECT s FROM SolicitudEdificio s WHERE s.idEstadoSolicitudEdificio = :idEstadoSolicitudEdificio"),
    @NamedQuery(name = "SolicitudEdificio.findByDescripcion", query = "SELECT s FROM SolicitudEdificio s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "SolicitudEdificio.findByIdUsuario", query = "SELECT s FROM SolicitudEdificio s WHERE s.idUsuario = :idUsuario"),
    @NamedQuery(name = "SolicitudEdificio.findByFechaAlquiler", query = "SELECT s FROM SolicitudEdificio s WHERE s.fechaAlquiler = :fechaAlquiler"),
    @NamedQuery(name = "SolicitudEdificio.findByHoraAlquiler", query = "SELECT s FROM SolicitudEdificio s WHERE s.horaInicioAlquiler = :horaInicioAlquiler"),
    @NamedQuery(name = "SolicitudEdificio.findByIdEstadoSolicitud", query = "SELECT s FROM SolicitudEdificio s WHERE s.idEstadoSolicitudEdificio = :idEstadoSolicitudEdificio"),
    @NamedQuery(name = "SolicitudEdificio.findByEstadoRefrigerio", query = "SELECT s FROM SolicitudEdificio s WHERE s.idEstadoSolicitudRefrigerio = :idEstadoSolicitudRefrigerio AND s.idEstadoSolicitudEdificio.idEstadoSolicitudEdificio = 1"),
    @NamedQuery(name = "SolicitudEdificio.findByEstadoDia", query = "SELECT s FROM SolicitudEdificio s WHERE s.idEstadoSolicitudEdificio.idEstadoSolicitudEdificio = 1 AND s.fechaAlquiler >= :fechaAlquiler ORDER BY s.fechaAlquiler"),
    @NamedQuery(name = "SolicitudEdificio.findByValidacionFecha", query = "SELECT s FROM SolicitudEdificio s WHERE s.idSeccion =:idSeccion AND s.fechaAlquiler =:fechaAlquiler AND s.horaInicioAlquiler <:horaFinAlquiler AND s.horaFinAlquiler >:horaInicioAlquiler AND s.idEstadoSolicitudEdificio.idEstadoSolicitudEdificio <= 2 ORDER BY s.idSolicitudEdificio"),
    @NamedQuery(name = "SolicitudEdificio.findByValidacionAudiovisuales", query = "SELECT s FROM SolicitudEdificio s WHERE s.idSeccion =:idSeccion AND s.fechaAlquiler =:fechaAlquiler AND s.horaInicioAlquiler <:horaFinAlquiler AND s.horaFinAlquiler >:horaInicioAlquiler AND s.idEstadoSolicitudEdificio.idEstadoSolicitudEdificio <= 2 ORDER BY s.idSolicitudEdificio "),
    @NamedQuery(name = "SolicitudEdificio.findByFechaSolicitud", query = "SELECT s FROM SolicitudEdificio s WHERE s.fechaSolicitud = :fechaSolicitud")})
public class SolicitudEdificio implements Serializable {

    @JoinColumn(name = "id_estado_solicitud_refrigerio", referencedColumnName = "id_estado_solicitud_refrigerio")
    @ManyToOne(optional = false)
    private EstadoSolicitudRefrigerio idEstadoSolicitudRefrigerio;
    @JoinColumn(name = "id_estado_solicitud_edificio", referencedColumnName = "id_estado_solicitud_edificio")
    @ManyToOne(optional = false)
    private EstadoSolicitudEdificio idEstadoSolicitudEdificio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSolicitudEdificio")
    private List<RespuestaRefrigerio> respuestaRefrigerioList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicitud_edificio")
    private Integer idSolicitudEdificio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_alquiler")
    @Temporal(TemporalType.DATE)
    private Date fechaAlquiler;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_inicio_alquiler")
    @Temporal(TemporalType.TIME)
    private Date horaInicioAlquiler;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_fin_alquiler")
    @Temporal(TemporalType.TIME)
    private Date horaFinAlquiler;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @JoinColumn(name = "id_seccion", referencedColumnName = "id_seccion")
    @ManyToOne(optional = false)
    private Seccion idSeccion;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @Column(name = "refrigerio")
    private Boolean refrigerio;
    @Column(name = "estado_refrigerio")
    private Boolean estadoRefrigerio;
    @Size(max = 300)
    @Column(name = "desc_refrigerio")
    private String descRefrigerio;
    @Basic(optional = false)
    @Column(name = "fecha_fin_alquiler")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinAlquiler;
    
    
    public SolicitudEdificio() {
    }

    public SolicitudEdificio(Integer idSolicitudEdificio) {
        this.idSolicitudEdificio = idSolicitudEdificio;
    }

    public SolicitudEdificio(Integer idSolicitudEdificio, String descripcion, Date fechaAlquiler, Date horaInicioAlquiler, Date horaFinAlquiler, Date fechaSolicitud, Seccion idSeccion, Usuario idUsuario, Date fechaFinAlquiler) {
        this.idSolicitudEdificio = idSolicitudEdificio;
        this.descripcion = descripcion;
        this.fechaAlquiler = fechaAlquiler;
        this.horaInicioAlquiler = horaInicioAlquiler;
        this.horaFinAlquiler = horaFinAlquiler;
        this.fechaSolicitud = fechaSolicitud;
        this.idSeccion = idSeccion;
        this.idUsuario = idUsuario;
        this.fechaFinAlquiler = fechaFinAlquiler;
    }



    public Integer getIdSolicitudEdificio() {
        return idSolicitudEdificio;
    }

    public void setIdSolicitudEdificio(Integer idSolicitudEdificio) {
        this.idSolicitudEdificio = idSolicitudEdificio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public Date getHoraInicioAlquiler() {
        return horaInicioAlquiler;
    }

    public void setHoraInicioAlquiler(Date horaInicioAlquiler) {
        this.horaInicioAlquiler = horaInicioAlquiler;
    }

    public Date getHoraFinAlquiler() {
        return horaFinAlquiler;
    }

    public void setHoraFinAlquiler(Date horaFinAlquiler) {
        this.horaFinAlquiler = horaFinAlquiler;
    }

 

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
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

    public Boolean getRefrigerio() {
        return refrigerio;
    }

    public void setRefrigerio(Boolean refrigerio) {
        this.refrigerio = refrigerio;
    }

    public Boolean getEstadoRefrigerio() {
        return estadoRefrigerio;
    }

    public void setEstadoRefrigerio(Boolean estadoRefrigerio) {
        this.estadoRefrigerio = estadoRefrigerio;
    }

    public Date getFechaFinAlquiler() {
        return fechaFinAlquiler;
    }

    public void setFechaFinAlquiler(Date fechaFinAlquiler) {
        this.fechaFinAlquiler = fechaFinAlquiler;
    }
    
    

    public String getDescRefrigerio() {
        if(descRefrigerio == null){
            return "";
        }else{
            return descRefrigerio;
        }
    }

    public void setDescRefrigerio(String descRefrigerio) {
        this.descRefrigerio = descRefrigerio;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitudEdificio != null ? idSolicitudEdificio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudEdificio)) {
            return false;
        }
        SolicitudEdificio other = (SolicitudEdificio) object;
        if ((this.idSolicitudEdificio == null && other.idSolicitudEdificio != null) || (this.idSolicitudEdificio != null && !this.idSolicitudEdificio.equals(other.idSolicitudEdificio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.SolicitudEdificio[ idSolicitudEdificio=" + idSolicitudEdificio + " ]";
    }

    public EstadoSolicitudEdificio getIdEstadoSolicitudEdificio() {
        return idEstadoSolicitudEdificio;
    }

    public void setIdEstadoSolicitudEdificio(EstadoSolicitudEdificio idEstadoSolicitudEdificio) {
        this.idEstadoSolicitudEdificio = idEstadoSolicitudEdificio;
    }

    @XmlTransient
    public List<RespuestaRefrigerio> getRespuestaRefrigerioList() {
        return respuestaRefrigerioList;
    }

    public void setRespuestaRefrigerioList(List<RespuestaRefrigerio> respuestaRefrigerioList) {
        this.respuestaRefrigerioList = respuestaRefrigerioList;
    }

    public EstadoSolicitudRefrigerio getIdEstadoSolicitudRefrigerio() {
        return idEstadoSolicitudRefrigerio;
    }

    public void setIdEstadoSolicitudRefrigerio(EstadoSolicitudRefrigerio idEstadoSolicitudRefrigerio) {
        this.idEstadoSolicitudRefrigerio = idEstadoSolicitudRefrigerio;
    }
    //Estado de reservas
    public String getEstado(){
        if(getIdEstadoSolicitudEdificio().equals(new EstadoSolicitudEdificio(1, "Reservado")) ){
            return "Reservado";
        }else if(getIdEstadoSolicitudEdificio().equals(new EstadoSolicitudEdificio(2, "Solicitado")) ){
            return "Solicitado";
        }else if(getIdEstadoSolicitudEdificio().equals(new EstadoSolicitudEdificio(3, "Rechazado")) ){
            return "Rechazado";
        }else{
            return null;
        }
    }
    
}
