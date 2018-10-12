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
@Table(name = "cronograma_mante_dis_con")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CronogramaManteDisCon.findAll", query = "SELECT c FROM CronogramaManteDisCon c"),
    @NamedQuery(name = "CronogramaManteDisCon.findByIdCronogramaManteDisCon", query = "SELECT c FROM CronogramaManteDisCon c WHERE c.idCronogramaManteDisCon = :idCronogramaManteDisCon"),
    @NamedQuery(name = "CronogramaManteDisCon.findByFechaProgMantenimientoDisCon", query = "SELECT c FROM CronogramaManteDisCon c WHERE c.fechaProgMantenimientoDisCon = :fechaProgMantenimientoDisCon"),
    @NamedQuery(name = "CronogramaManteDisCon.findByFechaInicioMantenimientoDisCon", query = "SELECT c FROM CronogramaManteDisCon c WHERE c.fechaInicioMantenimientoDisCon = :fechaInicioMantenimientoDisCon"),
    @NamedQuery(name = "CronogramaManteDisCon.findByEstadoMantenimientoDisCon", query = "SELECT c FROM CronogramaManteDisCon c WHERE c.estadoMantenimientoDisCon = :estadoMantenimientoDisCon"),
    @NamedQuery(name = "CronogramaManteDisCon.findByTipoTotalPrev", query = "SELECT c FROM CronogramaManteDisCon c WHERE c.idTipoMantenimiento.idTipoMantenimiento = 2 OR c.idTipoMantenimiento.idTipoMantenimiento= 3"),
    @NamedQuery(name = "CronogramaManteDisCon.findByUsuario", query = "SELECT c FROM CronogramaManteDisCon c WHERE c.idDispositivoConectividad.idUsuario = :idUsuario"),
    @NamedQuery(name = "CronogramaManteDisCon.findByUsuarioDocente", query = "SELECT c FROM CronogramaManteDisCon c WHERE  c.idUsuario.idUsuario = 304"),
    @NamedQuery(name = "CronogramaManteDisCon.findByTipoTotalCorre", query = "SELECT c FROM CronogramaManteDisCon c WHERE c.idTipoMantenimiento.idTipoMantenimiento = 1"),
    @NamedQuery(name = "CronogramaManteDisCon.findByEstadoTipoMantenimiento", query = "SELECT c FROM CronogramaManteDisCon c WHERE c.estadoMantenimientoDisCon = :estadoMantenimiento AND c.idTipoMantenimiento = :idTipoMantenimiento"),
    @NamedQuery(name = "CronogramaManteDisCon.findByHoraMantenimientoDisCon", query = "SELECT c FROM CronogramaManteDisCon c WHERE c.horaMantenimientoDisCon = :horaMantenimientoDisCon")})
public class CronogramaManteDisCon implements Serializable {
      private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cronograma_mante_dis_con")
    private Integer idCronogramaManteDisCon;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion_problema_dis_con")
    private String descripcionProblemaDisCon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_prog_mantenimiento_dis_con")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProgMantenimientoDisCon;
    @Column(name = "fecha_inicio_mantenimiento_dis_con")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioMantenimientoDisCon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_mantenimiento_dis_con")
    private boolean estadoMantenimientoDisCon;
    @Column(name = "hora_mantenimiento_dis_con")
    @Temporal(TemporalType.TIME)
    private Date horaMantenimientoDisCon;
    @JoinColumn(name = "id_dispositivo_conectividad", referencedColumnName = "id_dispositivo_conectividad")
    @ManyToOne(optional = false)
    private DispositivoConectividad idDispositivoConectividad;
    @JoinColumn(name = "id_tipo_mantenimiento", referencedColumnName = "id_tipo_mantenimiento")
    @ManyToOne(optional = false)
    private TipoMantenimiento idTipoMantenimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCronogramaManteDisCon")
    private List<DiagnosticoMantenimientoDisCon> diagnosticoMantenimientoDisConList;
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
    
    public CronogramaManteDisCon() {
    }

    public CronogramaManteDisCon(Integer idCronogramaManteDisCon) {
        this.idCronogramaManteDisCon = idCronogramaManteDisCon;
    }

    public CronogramaManteDisCon(Integer idCronogramaManteDisCon, Date fechaProgMantenimientoDisCon, boolean estadoMantenimientoDisCon) {
        this.idCronogramaManteDisCon = idCronogramaManteDisCon;
        this.fechaProgMantenimientoDisCon = fechaProgMantenimientoDisCon;
        this.estadoMantenimientoDisCon = estadoMantenimientoDisCon;
    }

    public Integer getIdCronogramaManteDisCon() {
        return idCronogramaManteDisCon;
    }

    public void setIdCronogramaManteDisCon(Integer idCronogramaManteDisCon) {
        this.idCronogramaManteDisCon = idCronogramaManteDisCon;
    }

    public String getDescripcionProblemaDisCon() {
        return descripcionProblemaDisCon;
    }

    public void setDescripcionProblemaDisCon(String descripcionProblemaDisCon) {
        this.descripcionProblemaDisCon = descripcionProblemaDisCon;
    }

    public Date getFechaProgMantenimientoDisCon() {
        return fechaProgMantenimientoDisCon;
    }

    public void setFechaProgMantenimientoDisCon(Date fechaProgMantenimientoDisCon) {
        this.fechaProgMantenimientoDisCon = fechaProgMantenimientoDisCon;
    }

    public Date getFechaInicioMantenimientoDisCon() {
        return fechaInicioMantenimientoDisCon;
    }

    public void setFechaInicioMantenimientoDisCon(Date fechaInicioMantenimientoDisCon) {
        this.fechaInicioMantenimientoDisCon = fechaInicioMantenimientoDisCon;
    }

    public boolean getEstadoMantenimientoDisCon() {
        return estadoMantenimientoDisCon;
    }

    public void setEstadoMantenimientoDisCon(boolean estadoMantenimientoDisCon) {
        this.estadoMantenimientoDisCon = estadoMantenimientoDisCon;
    }

    public Date getHoraMantenimientoDisCon() {
        return horaMantenimientoDisCon;
    }

    public void setHoraMantenimientoDisCon(Date horaMantenimientoDisCon) {
        this.horaMantenimientoDisCon = horaMantenimientoDisCon;
    }

    public DispositivoConectividad getIdDispositivoConectividad() {
        return idDispositivoConectividad;
    }

    public void setIdDispositivoConectividad(DispositivoConectividad idDispositivoConectividad) {
        this.idDispositivoConectividad = idDispositivoConectividad;
    }

    public TipoMantenimiento getIdTipoMantenimiento() {
        return idTipoMantenimiento;
    }

    public void setIdTipoMantenimiento(TipoMantenimiento idTipoMantenimiento) {
        this.idTipoMantenimiento = idTipoMantenimiento;
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

    
    
    
    @XmlTransient
    public List<DiagnosticoMantenimientoDisCon> getDiagnosticoMantenimientoDisConList() {
        return diagnosticoMantenimientoDisConList;
    }

    public void setDiagnosticoMantenimientoDisConList(List<DiagnosticoMantenimientoDisCon> diagnosticoMantenimientoDisConList) {
        this.diagnosticoMantenimientoDisConList = diagnosticoMantenimientoDisConList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCronogramaManteDisCon != null ? idCronogramaManteDisCon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CronogramaManteDisCon)) {
            return false;
        }
        CronogramaManteDisCon other = (CronogramaManteDisCon) object;
        if ((this.idCronogramaManteDisCon == null && other.idCronogramaManteDisCon != null) || (this.idCronogramaManteDisCon != null && !this.idCronogramaManteDisCon.equals(other.idCronogramaManteDisCon))) {
            return false;
        }
        return true;
    }

   
    
    
    
    public String getEstado(){
        if(estadoMantenimientoDisCon== true){
            return "Diagnosticado";
        }else{
            return "Pendiente";
        }
    }
    public String getFechaAñoString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("yyyy");
	convertido = fecha.format(fechaInicioMantenimientoDisCon);
        return convertido;
    }
    public String getFechaMesString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("MM");
	convertido = fecha.format(fechaInicioMantenimientoDisCon);
        return convertido;
    }
    
    public String getIdTicket(){
        if(getIdTipoMantenimiento().getNombreTipoMantenimiento().equals("correctivo")){
            return idCronogramaManteDisCon+"CFIPMC-DC";
        }else{
            return idCronogramaManteDisCon+"CFIPMP-DC";
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
        return "entities.CronogramaManteDisCon[ idCronogramaManteDisCon=" + idCronogramaManteDisCon + " ]";
    }
    
}
