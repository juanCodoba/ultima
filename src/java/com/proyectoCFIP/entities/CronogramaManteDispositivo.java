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
@Table(name = "cronograma_mante_dispositivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CronogramaManteDispositivo.findAll", query = "SELECT c FROM CronogramaManteDispositivo c"),
    @NamedQuery(name = "CronogramaManteDispositivo.findByIdCronogramaManteDispositivo", query = "SELECT c FROM CronogramaManteDispositivo c WHERE c.idCronogramaManteDispositivo = :idCronogramaManteDispositivo"),
    @NamedQuery(name = "CronogramaManteDispositivo.findByDescripcionProblemaDis", query = "SELECT c FROM CronogramaManteDispositivo c WHERE c.descripcionProblemaDis = :descripcionProblemaDis"),
    @NamedQuery(name = "CronogramaManteDispositivo.findByFechaProgMantenimientoDis", query = "SELECT c FROM CronogramaManteDispositivo c WHERE c.fechaProgMantenimientoDis = :fechaProgMantenimientoDis"),
    @NamedQuery(name = "CronogramaManteDispositivo.findByFechaInicioMantenimientoDis", query = "SELECT c FROM CronogramaManteDispositivo c WHERE c.fechaInicioMantenimientoDis = :fechaInicioMantenimientoDis"),
    @NamedQuery(name = "CronogramaManteDispositivo.findByEstadoMantenimientoDis", query = "SELECT c FROM CronogramaManteDispositivo c WHERE c.estadoMantenimientoDis = :estadoMantenimientoDis"),
    @NamedQuery(name = "CronogramaManteDispositivo.findByUsuario", query = "SELECT c FROM CronogramaManteDispositivo c WHERE c.idOtroDispositivo.idUsuario = :idUsuario"),
    @NamedQuery(name = "CronogramaManteDispositivo.findByUsuarioDocente", query = "SELECT c FROM CronogramaManteDispositivo c WHERE c.idUsuario.idUsuario = 304"),
    @NamedQuery(name = "CronogramaManteDispositivo.findByEstadoTipoMantenimiento", query = "SELECT c FROM CronogramaManteDispositivo c WHERE c.estadoMantenimientoDis = :estadoMantenimientoDis AND c.idTipoMantenimiento = :idTipoMantenimiento"),
    @NamedQuery(name = "CronogramaManteDispositivo.findByTipoTotalPrev", query = "SELECT c FROM CronogramaManteDispositivo c WHERE c.idTipoMantenimiento.idTipoMantenimiento = 2 OR c.idTipoMantenimiento.idTipoMantenimiento= 3"),    
    @NamedQuery(name = "CronogramaManteDispositivo.findByTipoTotalCorre", query = "SELECT c FROM CronogramaManteDispositivo c WHERE c.idTipoMantenimiento.idTipoMantenimiento = 1"),
    @NamedQuery(name = "CronogramaManteDispositivo.findByHoraMantenimientoDis", query = "SELECT c FROM CronogramaManteDispositivo c WHERE c.horaMantenimientoDis = :horaMantenimientoDis")})
public class CronogramaManteDispositivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cronograma_mante_dispositivo")
    private Integer idCronogramaManteDispositivo;
    @Size(max = 2147483647)
    @Column(name = "descripcion_problema_dis")
    private String descripcionProblemaDis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_prog_mantenimiento_dis")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProgMantenimientoDis;
    @Column(name = "fecha_inicio_mantenimiento_dis")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioMantenimientoDis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_mantenimiento_dis")
    private boolean estadoMantenimientoDis;
    @Column(name = "hora_mantenimiento_dis")
    @Temporal(TemporalType.TIME)
    private Date horaMantenimientoDis;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCronogramaManteDispositivo")
    private List<DiagnosticoManteDispositivo> diagnosticoManteDispositivoList;
    @JoinColumn(name = "id_otro_dispositivo", referencedColumnName = "id_otro_dispositivo")
    @ManyToOne(optional = false)
    private OtroDispositivo idOtroDispositivo;
    @JoinColumn(name = "id_tipo_mantenimiento", referencedColumnName = "id_tipo_mantenimiento")
    @ManyToOne(optional = false)
    private TipoMantenimiento idTipoMantenimiento;
    @Size(max = 100)
    @Column(name = "nombre_usuario_reporte")
    private String nombreUsuarioReporte;
    @Size(max = 70)
    @Column(name = "correo_usuario_reporte")
    private String correoUsuarioReporte;
    @JoinColumn(name = "id_tipo_jornada", referencedColumnName = "id_tipo_jornada")
    @ManyToOne
    private TipoJornada idTipoJornada;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public CronogramaManteDispositivo() {
    }

    public CronogramaManteDispositivo(Integer idCronogramaManteDispositivo) {
        this.idCronogramaManteDispositivo = idCronogramaManteDispositivo;
    }

    public CronogramaManteDispositivo(Integer idCronogramaManteDispositivo, Date fechaProgMantenimientoDis, boolean estadoMantenimientoDis) {
        this.idCronogramaManteDispositivo = idCronogramaManteDispositivo;
        this.fechaProgMantenimientoDis = fechaProgMantenimientoDis;
        this.estadoMantenimientoDis = estadoMantenimientoDis;
    }

    public Integer getIdCronogramaManteDispositivo() {
        return idCronogramaManteDispositivo;
    }

    public void setIdCronogramaManteDispositivo(Integer idCronogramaManteDispositivo) {
        this.idCronogramaManteDispositivo = idCronogramaManteDispositivo;
    }

    public String getDescripcionProblemaDis() {
        return descripcionProblemaDis;
    }

    public void setDescripcionProblemaDis(String descripcionProblemaDis) {
        this.descripcionProblemaDis = descripcionProblemaDis;
    }

    public Date getFechaProgMantenimientoDis() {
        return fechaProgMantenimientoDis;
    }

    public void setFechaProgMantenimientoDis(Date fechaProgMantenimientoDis) {
        this.fechaProgMantenimientoDis = fechaProgMantenimientoDis;
    }

    public Date getFechaInicioMantenimientoDis() {
        return fechaInicioMantenimientoDis;
    }

    public void setFechaInicioMantenimientoDis(Date fechaInicioMantenimientoDis) {
        this.fechaInicioMantenimientoDis = fechaInicioMantenimientoDis;
    }

    public boolean getEstadoMantenimientoDis() {
        return estadoMantenimientoDis;
    }

    public void setEstadoMantenimientoDis(boolean estadoMantenimientoDis) {
        this.estadoMantenimientoDis = estadoMantenimientoDis;
    }

    public Date getHoraMantenimientoDis() {
        return horaMantenimientoDis;
    }

    public void setHoraMantenimientoDis(Date horaMantenimientoDis) {
        this.horaMantenimientoDis = horaMantenimientoDis;
    }
    @XmlTransient
    public List<DiagnosticoManteDispositivo> getDiagnosticoManteDispositivoList() {
        return diagnosticoManteDispositivoList;
    }

    public void setDiagnosticoManteDispositivoList(List<DiagnosticoManteDispositivo> diagnosticoManteDispositivoList) {
        this.diagnosticoManteDispositivoList = diagnosticoManteDispositivoList;
    }
    public OtroDispositivo getIdOtroDispositivo() {
        return idOtroDispositivo;
    }

    public void setIdOtroDispositivo(OtroDispositivo idOtroDispositivo) {
        this.idOtroDispositivo = idOtroDispositivo;
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

    
    
    public TipoMantenimiento getIdTipoMantenimiento() {
        return idTipoMantenimiento;
    }

    public void setIdTipoMantenimiento(TipoMantenimiento idTipoMantenimiento) {
        this.idTipoMantenimiento = idTipoMantenimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCronogramaManteDispositivo != null ? idCronogramaManteDispositivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CronogramaManteDispositivo)) {
            return false;
        }
        CronogramaManteDispositivo other = (CronogramaManteDispositivo) object;
        if ((this.idCronogramaManteDispositivo == null && other.idCronogramaManteDispositivo != null) || (this.idCronogramaManteDispositivo != null && !this.idCronogramaManteDispositivo.equals(other.idCronogramaManteDispositivo))) {
            return false;
        }
        return true;
    }
    
    public String getFechaAñoString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("yyyy");
	convertido = fecha.format(fechaInicioMantenimientoDis);
        return convertido;
    }
    public String getFechaMesString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("MM");
	convertido = fecha.format(fechaInicioMantenimientoDis);
        return convertido;
    }
    
    public String getEstado(){
        if(estadoMantenimientoDis== true){
            return "Diagnosticado";
        }else{
            return "Pendiente";
        }
    }
    public String getIdTicket(){
        if(getIdTipoMantenimiento().getNombreTipoMantenimiento().equals("correctivo")){
            return idCronogramaManteDispositivo+"CFIPMC-DIS";
        }else{
            return idCronogramaManteDispositivo+"CFIPMP-DIS";
        }
    } 

    public TipoJornada getIdTipoJornada() {
        return idTipoJornada;
    }

    public void setIdTipoJornada(TipoJornada idTipoJornada) {
        this.idTipoJornada = idTipoJornada;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

   
    
    
    @Override
    public String toString() {
        return "entites.CronogramaManteDispositivo[ idCronogramaManteDispositivo=" + idCronogramaManteDispositivo + " ]";
    }
    
}
