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
@Table(name = "cronograma_mantenimiento_maquina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CronogramaMantenimientoMaquina.findAll", query = "SELECT c FROM CronogramaMantenimientoMaquina c"),
    @NamedQuery(name = "CronogramaMantenimientoMaquina.findByIdCronogramaMantenimientoMaquina", query = "SELECT c FROM CronogramaMantenimientoMaquina c WHERE c.idCronogramaMantenimientoMaquina = :idCronogramaMantenimientoMaquina"),
    @NamedQuery(name = "CronogramaMantenimientoMaquina.findByFechaReporte", query = "SELECT c FROM CronogramaMantenimientoMaquina c WHERE c.fechaReporte = :fechaReporte"),
    @NamedQuery(name = "CronogramaMantenimientoMaquina.findByFechaInicioMantenimiento", query = "SELECT c FROM CronogramaMantenimientoMaquina c WHERE c.fechaInicioMantenimiento = :fechaInicioMantenimiento"),
    @NamedQuery(name = "CronogramaMantenimientoMaquina.findByHoraMantenimiento", query = "SELECT c FROM CronogramaMantenimientoMaquina c WHERE c.horaMantenimiento = :horaMantenimiento"),
    @NamedQuery(name = "CronogramaMantenimientoMaquina.findByValoracion", query = "SELECT c FROM CronogramaMantenimientoMaquina c WHERE c.valoracion = :valoracion"),
    @NamedQuery(name = "CronogramaMantenimientoMaquina.findByDescripcionValoracion", query = "SELECT c FROM CronogramaMantenimientoMaquina c WHERE c.descripcionValoracion = :descripcionValoracion"),
    @NamedQuery(name = "CronogramaMantenimientoMaquina.findByMaquina", query = "SELECT c FROM CronogramaMantenimientoMaquina c WHERE c.idEstadoCronograma =:estado AND c.idMaquinaConfecciones = :idMaquinaConfecciones"),
    @NamedQuery(name = "CronogramaMantenimientoMaquina.findByTipoMantenimientoEstado", query = "SELECT c FROM CronogramaMantenimientoMaquina c WHERE c.idTipoMantenimiento = :idTipoMantenimiento AND c.idEstadoCronograma = :idEstadoCronograma"),
    @NamedQuery(name = "CronogramaMantenimientoMaquina.findByTipoMantenimiento", query = "SELECT c FROM CronogramaMantenimientoMaquina c WHERE c.idTipoMantenimiento = :idTipoMantenimiento"),
    @NamedQuery(name = "CronogramaMantenimientoMaquina.findByTipoMantenimientoPrev", query = "SELECT c FROM CronogramaMantenimientoMaquina c WHERE c.idTipoMantenimiento = :idTipoMantenimiento1 OR c.idTipoMantenimiento = :idTipoMantenimiento2"),
    @NamedQuery(name = "CronogramaMantenimientoMaquina.findByEstado", query = "SELECT c FROM CronogramaMantenimientoMaquina c WHERE c.estado = :estado")})
public class CronogramaMantenimientoMaquina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cronograma_mantenimiento_maquina")
    private Integer idCronogramaMantenimientoMaquina;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion_problema")
    private String descripcionProblema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_reporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporte;
    @Column(name = "fecha_inicio_mantenimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioMantenimiento;
    @Column(name = "hora_mantenimiento")
    @Temporal(TemporalType.TIME)
    private Date horaMantenimiento;
    @Column(name = "valoracion")
    private Integer valoracion;
    @Size(max = 75)
    @Column(name = "descripcion_valoracion")
    private String descripcionValoracion;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCronogramaMantenimientoMaquina")
    private List<DiagnosticoMaquina> diagnosticoMaquinaList;
    @JoinColumn(name = "id_estado_cronograma", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private EstadoCronograma idEstadoCronograma;
    @JoinColumn(name = "id_maquina_confecciones", referencedColumnName = "id_maquina_confecciones")
    @ManyToOne(optional = false)
    private MaquinaConfecciones idMaquinaConfecciones;
    @JoinColumn(name = "id_tipo_mantenimiento", referencedColumnName = "id_tipo_mantenimiento")
    @ManyToOne(optional = false)
    private TipoMantenimiento idTipoMantenimiento;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public CronogramaMantenimientoMaquina() {
    }

    public CronogramaMantenimientoMaquina(Integer idCronogramaMantenimientoMaquina) {
        this.idCronogramaMantenimientoMaquina = idCronogramaMantenimientoMaquina;
    }

    public CronogramaMantenimientoMaquina(Integer idCronogramaMantenimientoMaquina, Date fechaReporte) {
        this.idCronogramaMantenimientoMaquina = idCronogramaMantenimientoMaquina;
        this.fechaReporte = fechaReporte;
    }

    public Integer getIdCronogramaMantenimientoMaquina() {
        return idCronogramaMantenimientoMaquina;
    }

    public void setIdCronogramaMantenimientoMaquina(Integer idCronogramaMantenimientoMaquina) {
        this.idCronogramaMantenimientoMaquina = idCronogramaMantenimientoMaquina;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public Date getFechaInicioMantenimiento() {
        return fechaInicioMantenimiento;
    }

    public void setFechaInicioMantenimiento(Date fechaInicioMantenimiento) {
        this.fechaInicioMantenimiento = fechaInicioMantenimiento;
    }

    public Date getHoraMantenimiento() {
        return horaMantenimiento;
    }

    public void setHoraMantenimiento(Date horaMantenimiento) {
        this.horaMantenimiento = horaMantenimiento;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<DiagnosticoMaquina> getDiagnosticoMaquinaList() {
        return diagnosticoMaquinaList;
    }

    public void setDiagnosticoMaquinaList(List<DiagnosticoMaquina> diagnosticoMaquinaList) {
        this.diagnosticoMaquinaList = diagnosticoMaquinaList;
    }

    public EstadoCronograma getIdEstadoCronograma() {
        return idEstadoCronograma;
    }

    public void setIdEstadoCronograma(EstadoCronograma idEstadoCronograma) {
        this.idEstadoCronograma = idEstadoCronograma;
    }

    public MaquinaConfecciones getIdMaquinaConfecciones() {
        return idMaquinaConfecciones;
    }

    public void setIdMaquinaConfecciones(MaquinaConfecciones idMaquinaConfecciones) {
        this.idMaquinaConfecciones = idMaquinaConfecciones;
    }

    public TipoMantenimiento getIdTipoMantenimiento() {
        return idTipoMantenimiento;
    }

    public void setIdTipoMantenimiento(TipoMantenimiento idTipoMantenimiento) {
        this.idTipoMantenimiento = idTipoMantenimiento;
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
        hash += (idCronogramaMantenimientoMaquina != null ? idCronogramaMantenimientoMaquina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CronogramaMantenimientoMaquina)) {
            return false;
        }
        CronogramaMantenimientoMaquina other = (CronogramaMantenimientoMaquina) object;
        if ((this.idCronogramaMantenimientoMaquina == null && other.idCronogramaMantenimientoMaquina != null) || (this.idCronogramaMantenimientoMaquina != null && !this.idCronogramaMantenimientoMaquina.equals(other.idCronogramaMantenimientoMaquina))) {
            return false;
        }
        return true;
    }
    public int getIdTicket(){
        return idCronogramaMantenimientoMaquina;
    } 
    public String getDescripcion(){
        if(getDescripcionProblema() == null){
            return "NO APLICA";
        }else{
            return getDescripcionProblema();
        }
    } 
    public String getUsuario(){
        if(getIdUsuario()== null){
            return "NO APLICA";
        }else{
            return getIdUsuario().toString().toUpperCase();
        }
    } 
    public String getEstadoTicket(){
        if(idEstadoCronograma.equals(new EstadoCronograma(1)) ){
            return "Cerrado";
        }else if(idEstadoCronograma.equals(new EstadoCronograma(2)) ){
            return "Sin Cerrar";
        }else if(idEstadoCronograma.equals(new EstadoCronograma(3)) ){
            return "Sin Valoración";
        }else{
            return null;
        }
    }
    public String getFechaAñoString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("yyyy");
	convertido = fecha.format(fechaInicioMantenimiento);
        return convertido;
    }
    public String getFechaMesString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("MMMM");
	convertido = fecha.format(fechaInicioMantenimiento);
        return convertido;
    }
    
    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.CronogramaMantenimientoMaquina[ idCronogramaMantenimientoMaquina=" + idCronogramaMantenimientoMaquina + " ]";
    }
    
}
