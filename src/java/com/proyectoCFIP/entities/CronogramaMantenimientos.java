/*
 * To change this license header, choose License Headers in Project Properties.
 * To ch    ange this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
@Table(name = "cronograma_mantenimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CronogramaMantenimientos.findAll", query = "SELECT c FROM CronogramaMantenimientos c"),
    @NamedQuery(name = "CronogramaMantenimientos.findByIdCronogramaMantenimientos", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.idCronogramaMantenimientos = :idCronogramaMantenimientos"),
    @NamedQuery(name = "CronogramaMantenimientos.findByDescripcionProblema", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.descripcionProblema = :descripcionProblema"),
    @NamedQuery(name = "CronogramaMantenimientos.findByFechaProgMantenimiento", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.fechaProgMantenimiento = :fechaProgMantenimiento"),
    @NamedQuery(name = "CronogramaMantenimientos.findByFechaInicioMantenimiento", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.fechaInicioMantenimiento = :fechaInicioMantenimiento"),
    @NamedQuery(name = "CronogramaMantenimientos.findByEstadoMantenimiento", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.estadoMantenimiento = :estadoMantenimiento"),
    @NamedQuery(name = "CronogramaMantenimientos.findByUsuario", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.idComputador.idUsuario = :idUsuario ORDER BY c.idCronogramaMantenimientos DESC"),
    @NamedQuery(name = "CronogramaMantenimientos.findByCierreTickets", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.idUsuario = :idUsuario AND c.estadoMantenimiento.idEstado = 3 ORDER BY c.idCronogramaMantenimientos DESC"),
    @NamedQuery(name = "CronogramaMantenimientos.findByCierreTicketsSinValoracion", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.estadoMantenimiento.idEstado = 3 ORDER BY c.idCronogramaMantenimientos DESC"),
    @NamedQuery(name = "CronogramaMantenimientos.findByUsuarioDocente", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.idUsuario.idUsuario = 304 ORDER BY c.idCronogramaMantenimientos DESC"),
    @NamedQuery(name = "CronogramaMantenimientos.findByComputador", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.estadoMantenimiento =:estado AND c.idComputador = :idComputador"),
    @NamedQuery(name = "CronogramaMantenimientos.findByEstadoTipoMantenimiento", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.estadoMantenimiento = :estadoMantenimiento AND c.idTipoMantenimiento = :idTipoMantenimiento ORDER BY c.fechaInicioMantenimiento ASC"),
    @NamedQuery(name = "CronogramaMantenimientos.findByEstadoTipoMantenimientoCuatro", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.estadoMantenimiento = :estadoMantenimiento  ORDER BY c.fechaInicioMantenimiento ASC"),
    @NamedQuery(name = "CronogramaMantenimientos.findByEstadoTipoMantenimientoListado", query = "SELECT c FROM CronogramaMantenimientos c WHERE  c.estadoMantenimiento.idEstado = 4 ORDER BY c.fechaInicioMantenimiento ASC"),

    @NamedQuery(name = "CronogramaMantenimientos.findByTipoTotal", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.idTipoMantenimiento = :idTipoMantenimiento"),
    @NamedQuery(name = "CronogramaMantenimientos.findByTipoTotalPrev", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.idTipoMantenimiento.idTipoMantenimiento = 2 OR c.idTipoMantenimiento.idTipoMantenimiento= 3 ORDER BY c.idCronogramaMantenimientos DESC"),
    @NamedQuery(name = "CronogramaMantenimientos.findByParametro", query = "SELECT c.idComputador.codigoComputador FROM CronogramaMantenimientos c"),
    @NamedQuery(name = "CronogramaMantenimientos.findByTipoTotalCorre", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.idTipoMantenimiento.idTipoMantenimiento = 1 ORDER BY c.idCronogramaMantenimientos DESC"),
    @NamedQuery(name = "CronogramaMantenimientos.findByReporteCorrectivoEstrellas", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.idTipoMantenimiento.idTipoMantenimiento = 1 AND c.fechaProgMantenimiento BETWEEN :fecha1 AND :fecha2 ORDER BY c.valoracionReporte"),
    @NamedQuery(name = "CronogramaMantenimientos.findByReporteCorrectivo", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.idTipoMantenimiento.idTipoMantenimiento = 1 AND c.fechaProgMantenimiento BETWEEN :fecha1 AND :fecha2 ORDER BY c.estadoReporte"),
    @NamedQuery(name = "CronogramaMantenimientos.findByReportePreventivo", query = "SELECT c FROM CronogramaMantenimientos c WHERE (c.idTipoMantenimiento.idTipoMantenimiento = 2  OR c.idTipoMantenimiento.idTipoMantenimiento = 3)  AND c.fechaInicioMantenimiento BETWEEN :fecha1 AND :fecha2 ORDER BY c.estadoReporte"),
    @NamedQuery(name = "CronogramaMantenimientos.findBySubReporteCorrectivo", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.idTipoMantenimiento.idTipoMantenimiento = 1"),
    @NamedQuery(name = "CronogramaMantenimientos.findByReporteTiempoCorrectivo", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.idTipoMantenimiento.idTipoMantenimiento = 1 AND c.fechaProgMantenimiento BETWEEN :fecha1 AND :fecha2 ORDER BY c.fechaProgMantenimiento"),
    @NamedQuery(name = "CronogramaMantenimientos.findByHoraMantenimiento", query = "SELECT c FROM CronogramaMantenimientos c WHERE c.horaMantenimiento = :horaMantenimiento")})
public class CronogramaMantenimientos implements Serializable {

    @Lob
    @Column(name = "imagen_mantenimiento")
    private byte[] imagenMantenimiento;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "fecha_diagnostico")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDiagnostico;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cronograma_mantenimientos")
    private Integer idCronogramaMantenimientos;
    @Size(max = 2147483647)
    @Column(name = "descripcion_problema")
    private String descripcionProblema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_prog_mantenimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProgMantenimiento;
    @Column(name = "fecha_inicio_mantenimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioMantenimiento;
    @JoinColumn(name = "estado_mantenimiento", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private EstadoCronograma estadoMantenimiento;
    @Column(name = "hora_mantenimiento")
    @Temporal(TemporalType.TIME)
    private Date horaMantenimiento;
    @JoinColumn(name = "id_tipo_mantenimiento", referencedColumnName = "id_tipo_mantenimiento")
    @ManyToOne(optional = false)
    private TipoMantenimiento idTipoMantenimiento;
    @JoinColumn(name = "id_computador", referencedColumnName = "id_computador")
    @ManyToOne(optional = false)
    private Computador idComputador;
    @Column(name = "valoracion_reporte")
    private Integer valoracionReporte;
    @Column(name = "fecha_valoracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaValoracion;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion_valoracion")
    private String descripcionValoracion;
    @Size(max = 100)
    @Column(name = "nombre_usuario_reporte")
    private String nombreUsuarioReporte;
    @Size(max = 70)
    @Column(name = "correo_usuario_reporte")
    private String correoUsuarioReporte;
    @Column(name = "estado_reporte")
    private Boolean estadoReporte;
    @JoinColumn(name = "id_tipo_jornada", referencedColumnName = "id_tipo_jornada")
    @ManyToOne
    private TipoJornada idTipoJornada;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public CronogramaMantenimientos() {
    }

    public CronogramaMantenimientos(Integer idCronogramaMantenimientos) {
        this.idCronogramaMantenimientos = idCronogramaMantenimientos;
    }

    public CronogramaMantenimientos(Integer idCronogramaMantenimientos, Date fechaProgMantenimiento) {
        this.idCronogramaMantenimientos = idCronogramaMantenimientos;
        this.fechaProgMantenimiento = fechaProgMantenimiento;
    }

    public Integer getIdCronogramaMantenimientos() {
        return idCronogramaMantenimientos;
    }

    public String getIdTicket() {
        if (getIdTipoMantenimiento().getNombreTipoMantenimiento().equals("correctivo")) {
            return idCronogramaMantenimientos + "CFIPMC-C";
        } else {
            return idCronogramaMantenimientos + "CFIPMP-C";
        }
    }

    public void setIdCronogramaMantenimientos(Integer idCronogramaMantenimientos) {
        this.idCronogramaMantenimientos = idCronogramaMantenimientos;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }

    public Date getFechaProgMantenimiento() {

        return fechaProgMantenimiento;
    }

    public String getFechaAñoString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("yyyy");
        convertido = fecha.format(fechaInicioMantenimiento);
        return convertido;
    }

    public String getFechaMesString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("MM");
        convertido = fecha.format(fechaInicioMantenimiento);
        return convertido;
    }

    public void setFechaProgMantenimiento(Date fechaProgMantenimiento) {
        this.fechaProgMantenimiento = fechaProgMantenimiento;
    }

    public Date getFechaInicioMantenimiento() {
        return fechaInicioMantenimiento;
    }

    public void setFechaInicioMantenimiento(Date fechaInicioMantenimiento) {
        this.fechaInicioMantenimiento = fechaInicioMantenimiento;
    }

    public Boolean getEstadoReporte() {
        return estadoReporte;
    }

    public void setEstadoReporte(Boolean estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public EstadoCronograma getEstadoMantenimiento() {
        return estadoMantenimiento;
    }

    public void setEstadoMantenimiento(EstadoCronograma estadoMantenimiento) {
        this.estadoMantenimiento = estadoMantenimiento;
    }

    public Date getHoraMantenimiento() {
        return horaMantenimiento;
    }

    public void setHoraMantenimiento(Date horaMantenimiento) {
        this.horaMantenimiento = horaMantenimiento;
    }

    public Integer getValoracion() {
        return valoracionReporte;
    }

    public Integer getValoracionReporte() {
        return valoracionReporte;
    }

    public void setValoracionReporte(Integer valoracionReporte) {
        this.valoracionReporte = valoracionReporte;
    }

    public void setValoracion(Integer valoracionReporte) {
        this.valoracionReporte = valoracionReporte;
    }

    public Date getFechaValoracion() {
        return fechaValoracion;
    }

    public void setFechaValoracion(Date fechaValoracion) {
        this.fechaValoracion = fechaValoracion;
    }

    public TipoMantenimiento getIdTipoMantenimiento() {
        return idTipoMantenimiento;
    }

    public void setIdTipoMantenimiento(TipoMantenimiento idTipoMantenimiento) {
        this.idTipoMantenimiento = idTipoMantenimiento;
    }

    public String getDescripcionValoracion() {
        return descripcionValoracion;
    }

    public void setDescripcionValoracion(String descripcionValoracion) {
        this.descripcionValoracion = descripcionValoracion;
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

    public Computador getIdComputador() {
        return idComputador;
    }

    public void setIdComputador(Computador idComputador) {
        this.idComputador = idComputador;
    }

    public DefaultStreamedContent getImagenMantenimiento() {
        try {
            InputStream is = new ByteArrayInputStream((byte[]) imagenMantenimiento);
            return new DefaultStreamedContent(is, "image/png");
        } catch (Exception e) {
            return null;
        }
    }

    public void setImagenMantenimiento(byte[] imagenMantenimiento) {
        this.imagenMantenimiento = imagenMantenimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCronogramaMantenimientos != null ? idCronogramaMantenimientos.hashCode() : 0);
        return hash;
    }

    public String getEstado() {
        if (estadoMantenimiento.equals(new EstadoCronograma(1))) {
            return "Cerrado";
        } else if (estadoMantenimiento.equals(new EstadoCronograma(2))) {
            return "Sin Cerrar";
        } else if (estadoMantenimiento.equals(new EstadoCronograma(3))) {
            return "Sin Valoración";
        } else {
            return null;
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
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CronogramaMantenimientos)) {
            return false;
        }
        CronogramaMantenimientos other = (CronogramaMantenimientos) object;
        if ((this.idCronogramaMantenimientos == null && other.idCronogramaMantenimientos != null) || (this.idCronogramaMantenimientos != null && !this.idCronogramaMantenimientos.equals(other.idCronogramaMantenimientos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescripcionProblema();
    }

    public Date getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(Date fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getDiasValoracion() {
        int diffDays = 0;
        Calendar fecha1 = new GregorianCalendar();
        fecha1.setLenient(false);

        Calendar fecha2 = new GregorianCalendar();
        fecha2.setLenient(false);
        if (fechaDiagnostico == null || fechaValoracion == null) {
            return 0;
        } else {
            fecha1.setTime(fechaDiagnostico);
            fecha2.setTime(fechaValoracion);
        }

        if (fecha2.before(fecha1) || fecha2.equals(fecha1)) {
            diffDays = 0;
        } else {
            while (fecha1.before(fecha2) || fecha1.equals(fecha2)) {
                diffDays++;
                fecha1.add(Calendar.DATE, 1);
            }
        }
        return diffDays;
    }

    public int getDiasDiagnostico() {
        int diffDays = 0;
        Calendar fecha1 = new GregorianCalendar();
        fecha1.setLenient(false);

        Calendar fecha2 = new GregorianCalendar();
        fecha2.setLenient(false);
        if (fechaProgMantenimiento == null || fechaDiagnostico == null) {
            return 0;
        } else {
            fecha1.setTime(fechaProgMantenimiento);
            fecha2.setTime(fechaDiagnostico);
        }

        if (fecha2.before(fecha1) || fecha1.equals(fecha2)) {
            return diffDays = 0;
        } else {
            while (fecha1.before(fecha2) || fecha1.equals(fecha2)) {
                diffDays++;
                fecha1.add(Calendar.DATE, 1);
            }
            return diffDays;
        }
    }

}
