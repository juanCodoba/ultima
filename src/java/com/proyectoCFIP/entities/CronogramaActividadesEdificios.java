/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

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

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "cronograma_actividades_edificios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CronogramaActividadesEdificios.findAll", query = "SELECT c FROM CronogramaActividadesEdificios c"),
    @NamedQuery(name = "CronogramaActividadesEdificios.findByIdCronogramaActividadesEdificios", query = "SELECT c FROM CronogramaActividadesEdificios c WHERE c.idCronogramaActividadesEdificios = :idCronogramaActividadesEdificios"),
    @NamedQuery(name = "CronogramaActividadesEdificios.findByDescripcion", query = "SELECT c FROM CronogramaActividadesEdificios c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CronogramaActividadesEdificios.findByFechaReporte", query = "SELECT c FROM CronogramaActividadesEdificios c WHERE c.fechaReporte = :fechaReporte"),
    @NamedQuery(name = "CronogramaActividadesEdificios.findByDescripcionValoracion", query = "SELECT c FROM CronogramaActividadesEdificios c WHERE c.descripcionValoracion = :descripcionValoracion"),
    @NamedQuery(name = "CronogramaActividadesEdificios.findByValoracion", query = "SELECT c FROM CronogramaActividadesEdificios c WHERE c.valoracion = :valoracion"),
    @NamedQuery(name = "CronogramaActividadesEdificios.findByEstado", query = "SELECT c FROM CronogramaActividadesEdificios c WHERE c.estado = :estado"),
    @NamedQuery(name = "CronogramaActividadesEdificios.findByConsultaTickesDaños", query = "SELECT c FROM CronogramaActividadesEdificios c WHERE c.idTipoActividad = :idTipoActividad AND c.idUsuario =:idUsuario"),
    @NamedQuery(name = "CronogramaActividadesEdificios.findByTipoActividad", query = "SELECT c FROM CronogramaActividadesEdificios c WHERE c.idTipoActividad = :idTipoActividad"),
    @NamedQuery(name = "CronogramaActividadesEdificios.findByConsultaCronograma", query = "SELECT c FROM CronogramaActividadesEdificios c WHERE c.idTipoActividad = :idTipoActividad AND c.idEstadoActividad =:idEstadoActividad"),
    @NamedQuery(name = "CronogramaActividadesEdificios.findByIndicadorDano", query = "SELECT c FROM CronogramaActividadesEdificios c WHERE c.idTipoActividad.idTipoActividad = 1 AND c.fechaReporte BETWEEN :fecha1 AND :fecha2 ORDER BY c.estado"),
    @NamedQuery(name = "CronogramaActividadesEdificios.findByHoraReporte", query = "SELECT c FROM CronogramaActividadesEdificios c WHERE c.horaReporte = :horaReporte"),
    @NamedQuery(name = "CronogramaActividadesEdificios.findByFechaValoracion", query = "SELECT c FROM CronogramaActividadesEdificios c WHERE c.fechaValoracion = :fechaValoracion")})
public class CronogramaActividadesEdificios implements Serializable {

    @Lob
    @Column(name = "imagen")
    private byte[] imagen;
    @Size(max = 100)
    @Column(name = "nombre_usuario_reporte")
    private String nombreUsuarioReporte;
    @Size(max = 75)
    @Column(name = "correo_usuario_reporte")
    private String correoUsuarioReporte;
    @JoinColumn(name = "id_tipo_jornada", referencedColumnName = "id_tipo_jornada")
    @ManyToOne
    private TipoJornada idTipoJornada;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cronograma_actividades_edificios")
    private Integer idCronogramaActividadesEdificios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_reporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporte;
    @Size(max = 300)
    @Column(name = "descripcion_valoracion")
    private String descripcionValoracion;
    @Column(name = "valoracion")
    private Integer valoracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_reporte")
    @Temporal(TemporalType.TIME)
    private Date horaReporte;
    @Column(name = "fecha_valoracion")
    @Temporal(TemporalType.DATE)
    private Date fechaValoracion;
    @JoinColumn(name = "id_estado_actividad", referencedColumnName = "id_estado_actividad")
    @ManyToOne(optional = false)
    private EstadoActividad idEstadoActividad;
    @JoinColumn(name = "id_seccion", referencedColumnName = "id_seccion")
    @ManyToOne(optional = false)
    private Seccion idSeccion;
    @JoinColumn(name = "id_tipo_actividad", referencedColumnName = "id_tipo_actividad")
    @ManyToOne(optional = false)
    private TipoActividad idTipoActividad;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCronogramaActividadesEdificios")
    private List<DiagnosticoActividadEdificios> diagnosticoActividadEdificiosList;

    public CronogramaActividadesEdificios() {
    }

    public CronogramaActividadesEdificios(Integer idCronogramaActividadesEdificios) {
        this.idCronogramaActividadesEdificios = idCronogramaActividadesEdificios;
    }

    public CronogramaActividadesEdificios(Integer idCronogramaActividadesEdificios, String descripcion, Date fechaReporte, boolean estado, Date horaReporte) {
        this.idCronogramaActividadesEdificios = idCronogramaActividadesEdificios;
        this.descripcion = descripcion;
        this.fechaReporte = fechaReporte;
        this.estado = estado;
        this.horaReporte = horaReporte;
    }
    

    public Integer getIdCronogramaActividadesEdificios() {
        return idCronogramaActividadesEdificios;
    }

    public void setIdCronogramaActividadesEdificios(Integer idCronogramaActividadesEdificios) {
        this.idCronogramaActividadesEdificios = idCronogramaActividadesEdificios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getDescripcionValoracion() {
        return descripcionValoracion;
    }

    public void setDescripcionValoracion(String descripcionValoracion) {
        this.descripcionValoracion = descripcionValoracion;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getHoraReporte() {
        return horaReporte;
    }

    public void setHoraReporte(Date horaReporte) {
        this.horaReporte = horaReporte;
    }

    public Date getFechaValoracion() {
        return fechaValoracion;
    }

    public void setFechaValoracion(Date fechaValoracion) {
        this.fechaValoracion = fechaValoracion;
    }


    public EstadoActividad getIdEstadoActividad() {
        return idEstadoActividad;
    }

    public void setIdEstadoActividad(EstadoActividad idEstadoActividad) {
        this.idEstadoActividad = idEstadoActividad;
    }

    public Seccion getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Seccion idSeccion) {
        this.idSeccion = idSeccion;
    }

    public TipoActividad getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(TipoActividad idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getFechaAñoString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("yyyy");
	convertido = fecha.format(fechaReporte);
        return convertido;
    }
    public String getFechaMesString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("MMMM");
	convertido = fecha.format(fechaReporte);
        return convertido;
    }
    @XmlTransient
    public List<DiagnosticoActividadEdificios> getDiagnosticoActividadEdificiosList() {
        return diagnosticoActividadEdificiosList;
    }

    public void setDiagnosticoActividadEdificiosList(List<DiagnosticoActividadEdificios> diagnosticoActividadEdificiosList) {
        this.diagnosticoActividadEdificiosList = diagnosticoActividadEdificiosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCronogramaActividadesEdificios != null ? idCronogramaActividadesEdificios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CronogramaActividadesEdificios)) {
            return false;
        }
        CronogramaActividadesEdificios other = (CronogramaActividadesEdificios) object;
        if ((this.idCronogramaActividadesEdificios == null && other.idCronogramaActividadesEdificios != null) || (this.idCronogramaActividadesEdificios != null && !this.idCronogramaActividadesEdificios.equals(other.idCronogramaActividadesEdificios))) {
            return false;
        }
        return true;
    }
   
    
    public String getEstadoActividad(){
        if(getIdEstadoActividad().equals(new EstadoActividad(1)) ){
            return "Abierto";
        }else if(getIdEstadoActividad().equals(new EstadoActividad(2)) ){
            return "Sin valoracion";
        }else if(getIdEstadoActividad().equals(new EstadoActividad(3)) ){
            return "Cerrado";
        }else{
            return null;
        }
    }
    @Override
    public String toString() {
        return "entities.CronogramaActividadesEdificios[ idCronogramaActividadesEdificios=" + idCronogramaActividadesEdificios + " ]";
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getNombreUsuarioReporte() {
        return nombreUsuarioReporte;
    }

    public void setNombreUsuarioReporte(String nombreUsuarioReporte) {
        this.nombreUsuarioReporte = nombreUsuarioReporte;
    }

    public String getCorreoUsuarioReporte() {
        return correoUsuarioReporte;
    }

    public void setCorreoUsuarioReporte(String correoUsuarioReporte) {
        this.correoUsuarioReporte = correoUsuarioReporte;
    }

    public TipoJornada getIdTipoJornada() {
        return idTipoJornada;
    }

    public void setIdTipoJornada(TipoJornada idTipoJornada) {
        this.idTipoJornada = idTipoJornada;
    }

    public boolean isEstado() {
        return estado;
    }
    
    
    
}
