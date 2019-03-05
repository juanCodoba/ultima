/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "reserva_libros_biblioteca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservaLibrosBiblioteca.findAll", query = "SELECT r FROM ReservaLibrosBiblioteca r ORDER BY  r.idReservaLibros  DESC"),
    @NamedQuery(name = "ReservaLibrosBiblioteca.findByIdReservaLibros", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.idReservaLibros = :idReservaLibros ORDER BY  r.idReservaLibros  ASC"),
    @NamedQuery(name = "ReservaLibrosBiblioteca.findByFechaInicioPrestamo", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.fechaInicioPrestamo = :fechaInicioPrestamo ORDER BY  r.idReservaLibros  ASC"),
    @NamedQuery(name = "ReservaLibrosBiblioteca.findByFechaFinPrestamo", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.fechaFinPrestamo = :fechaFinPrestamo ORDER BY  r.idReservaLibros  ASC"),
    @NamedQuery(name = "ReservaLibrosBiblioteca.findByUsuario", query = "SELECT COUNT(r.idUsuarioPrestamo) FROM ReservaLibrosBiblioteca r WHERE r.fechaInicioPrestamo BETWEEN :fecha1 AND :fecha2  ORDER BY  r.idReservaLibros  ASC "),
//    @NamedQuery(name = "ReservaLibrosBiblioteca.estado", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE  r.idLib1.idEstadoLibro.idEstadoLibro  <=3 AND r.idLib2.idEstadoLibro.idEstadoLibro <=3 ORDER BY  r.idReservaLibros  ASC"),
    @NamedQuery(name = "ReservaLibrosBiblioteca.estadoTrue", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.activo = true ORDER BY  r.idReservaLibros  ASC"),
    @NamedQuery(name = "ReservaLibrosBiblioteca.estadoFalse", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.activo = false ORDER BY  r.idReservaLibros  ASC"),
    @NamedQuery(name = "ReservaLibrosBiblioteca.findByIdTipoEstudiante", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.idTipoEstudiante = :idTipoEstudiante order by r.idReservaLibros ASC "),
    @NamedQuery(name = "ReservaLibrosBiblioteca.findByIdTipoEgresdo", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.idTipoEstudiante.idTipoEstudiante = 2 AND r.fechaInicioPrestamo BETWEEN :fecha1 AND :fecha2 order by r.idReservaLibros ASC "),
    @NamedQuery(name = "ReservaLibrosBiblioteca.findByIdTipoTrabajador", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.idTipoEstudiante.idTipoEstudiante = 4   AND r.fechaInicioPrestamo BETWEEN :fecha1 AND :fecha2 order by r.idReservaLibros ASC "),
    @NamedQuery(name = "ReservaLibrosBiblioteca.findByIdTipoDocente", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.idTipoEstudiante.idTipoEstudiante = 6 AND r.fechaInicioPrestamo BETWEEN :fecha1 AND :fecha2 order by r.idReservaLibros ASC "),
    @NamedQuery(name = "ReservaLibrosBiblioteca.findByIdTipo1Es", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.idTipoEstudiante.idTipoEstudiante = 1 AND r.fechaInicioPrestamo BETWEEN :fecha1 AND :fecha2 order by r.idReservaLibros ASC "),
//    @NamedQuery(name = "ReservaLibrosBiblioteca.findByIdLib1", query = "SELECT r.idLib1 FROM ReservaLibrosBiblioteca r where r.fechaInicioPrestamo BETWEEN :fecha1 AND :fecha2  order by r.idReservaLibros  ASC "),
//    @NamedQuery(name = "ReservaLibrosBiblioteca.findByIdLib2", query = "SELECT r.idLib2 FROM ReservaLibrosBiblioteca r where r.fechaInicioPrestamo BETWEEN :fecha1 AND :fecha2 AND r.idLib2 != null  order by r.idReservaLibros ASC  "),
    @NamedQuery(name = "ReservaLibrosBiblioteca.finbyIndicador", query = "SELECT r FROM ReservaLibrosBiblioteca r where r.fechaInicioPrestamo BETWEEN :fecha1 AND :fecha2 order by r.idReservaLibros ASC"),
    @NamedQuery(name = "ReservaLibrosBiblioteca.finbyIndicador1", query = "SELECT r.idUsuarioPrestamo.nombreUsuario,r.idUsuarioPrestamo.apellidoUsuario  FROM ReservaLibrosBiblioteca r where r.fechaInicioPrestamo BETWEEN :fecha1 AND :fecha2 order by r.idReservaLibros ASC"),

    @NamedQuery(name = "ReservaLibrosBiblioteca.findByIdUsuarioPrestamo", query = "SELECT r FROM ReservaLibrosBiblioteca r where  r.fechaInicioPrestamo BETWEEN :fecha1 AND :fecha2  GROUP BY r.idUsuarioPrestamo "),
    @NamedQuery(name = "ReservaLibrosBiblioteca.findByIdUsuarioPrestamo1", query = "SELECT COUNT(r) as total FROM ReservaLibrosBiblioteca r where r.fechaInicioPrestamo BETWEEN :fecha1 AND :fecha2 GROUP BY r.idUsuarioPrestamo "),

    @NamedQuery(name = "ReservaLibrosBiblioteca.findByBibliotecario", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.idBibliotecario.idUsuario = 223 ORDER BY  r.idReservaLibros  ASC")})

@NamedNativeQueries({
    @NamedNativeQuery(
            name = "ReservaLibrosBiblioteca.totalPrestamoPorPers",
            query = "select nombre_usuario,apellido_usuario,id_usuario_prestamo, COUNT(id_usuario_prestamo) from reserva_libros_biblioteca inner join usuario on reserva_libros_biblioteca.id_usuario_prestamo = usuario.id_usuario AND reserva_libros_biblioteca.fecha_inicio_prestamo BETWEEN #fecha1 AND #fecha2 GROUP BY id_usuario_prestamo ;"
    )

})

public class ReservaLibrosBiblioteca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reserva_libros")
    private Integer idReservaLibros;
    @Basic(optional = false)

    @Column(name = "fecha_inicio_prestamo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioPrestamo;
    @Column(name = "fecha_fin_prestamo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinPrestamo;

    @JoinColumn(name = "id_bibliotecario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idBibliotecario;

    @JoinColumn(name = "id_usuario_prestamo", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuarioPrestamo;

    @JoinTable(name = "reserva_libros_biblioteca_has_libro", joinColumns = {
        @JoinColumn(name = "id_reserva_libros", referencedColumnName = "id_reserva_libros")}, inverseJoinColumns = {
        @JoinColumn(name = "id_libro", referencedColumnName = "id_libro")})
    @ManyToMany
    private List<Libro> libroList;

    @Lob
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observacionesLib;
    @Lob
    @Size(max = 100)
    @Column(name = "grado_estudiante")
    private String gradoEstudiante;

    @Column(name = "activo")
    private Boolean activo;

    @JoinColumn(name = "id_tipo_estudiante", referencedColumnName = "id_tipo_estudiante")
    @ManyToOne(optional = true)
    private TipoEstudiante idTipoEstudiante;

    @JoinColumn(name = "id_grado", referencedColumnName = "id_grado")
    @ManyToOne(optional = true)
    private Grado idGrado;

    @Lob
    @Size(max = 100)
    @Column(name = "nombre_egresado")
    private String nombreEgresado;
    @Lob
    @Size(max = 100)
    @Column(name = "apellido_egresado")
    private String apellidoEgresado;
    @Lob
    @Size(max = 100)
    @Column(name = "año_egresado")
    private String añoEgresado;

    @Lob
    @Size(max = 100)
    @Column(name = "area")
    private String area;

    public ReservaLibrosBiblioteca() {
    }

    public ReservaLibrosBiblioteca(Integer idReservaLibros) {
        this.idReservaLibros = idReservaLibros;
    }

    public ReservaLibrosBiblioteca(Integer idReservaLibros, Date fechaInicioPrestamo, Date fechaFinPrestamo, Usuario idBibliotecario, Usuario idUsuarioPrestamo, Libro idLib1, Libro idLib2, String observacionesLib, String gradoEstudiante, Boolean activo, Boolean estadoUsuarioReservas, Boolean estadoUsuarioReservasCompletMe, Boolean sinPreserva, TipoEstudiante idTipoEstudiante, String nombreEgresado, String apellidoEgresado, String añoEgresado) {
        this.idReservaLibros = idReservaLibros;
        this.fechaInicioPrestamo = fechaInicioPrestamo;
        this.fechaFinPrestamo = fechaFinPrestamo;
        this.idBibliotecario = idBibliotecario;
        this.idUsuarioPrestamo = idUsuarioPrestamo;

        this.observacionesLib = observacionesLib;
        this.gradoEstudiante = gradoEstudiante;
        this.activo = activo;

        this.idTipoEstudiante = idTipoEstudiante;
        this.nombreEgresado = nombreEgresado;
        this.apellidoEgresado = apellidoEgresado;
        this.añoEgresado = añoEgresado;
    }

    public Integer getIdReservaLibros() {
        return idReservaLibros;
    }

    public void setIdReservaLibros(Integer idReservaLibros) {
        this.idReservaLibros = idReservaLibros;
    }

    public Date getFechaInicioPrestamo() {
        return fechaInicioPrestamo;
    }

    public void setFechaInicioPrestamo(Date fechaInicioPrestamo) {
        this.fechaInicioPrestamo = fechaInicioPrestamo;
    }

    public Date getFechaFinPrestamo() {
        return fechaFinPrestamo;
    }

    public void setFechaFinPrestamo(Date fechaFinPrestamo) {
        this.fechaFinPrestamo = fechaFinPrestamo;
    }

    public Usuario getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(Usuario idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public Usuario getIdUsuarioPrestamo() {
        return idUsuarioPrestamo;
    }

    public void setIdUsuarioPrestamo(Usuario idUsuarioPrestamo) {
        this.idUsuarioPrestamo = idUsuarioPrestamo;
    }

    public String getObservacionesLib() {
        return observacionesLib;
    }

    public void setObservacionesLib(String observacionesLib) {
        this.observacionesLib = observacionesLib;
    }

    public String getGradoEstudiante() {
        return gradoEstudiante;
    }

    public void setGradoEstudiante(String gradoEstudiante) {
        this.gradoEstudiante = gradoEstudiante;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public TipoEstudiante getIdTipoEstudiante() {
        return idTipoEstudiante;
    }

    public void setIdTipoEstudiante(TipoEstudiante idTipoEstudiante) {
        this.idTipoEstudiante = idTipoEstudiante;
    }

    public String getNombreEgresado() {
        return nombreEgresado;
    }

    public void setNombreEgresado(String nombreEgresado) {
        this.nombreEgresado = nombreEgresado;
    }

    public String getApellidoEgresado() {
        return apellidoEgresado;
    }

    public void setApellidoEgresado(String apellidoEgresado) {
        this.apellidoEgresado = apellidoEgresado;
    }

    public String getAñoEgresado() {
        return añoEgresado;
    }

    public void setAñoEgresado(String añoEgresado) {
        this.añoEgresado = añoEgresado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReservaLibros != null ? idReservaLibros.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaLibrosBiblioteca)) {
            return false;
        }
        ReservaLibrosBiblioteca other = (ReservaLibrosBiblioteca) object;
        if ((this.idReservaLibros == null && other.idReservaLibros != null) || (this.idReservaLibros != null && !this.idReservaLibros.equals(other.idReservaLibros))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.ReservaLibrosBiblioteca[ idReservaLibros=" + idReservaLibros + " ]";
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFechaAñoString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("yyyy");
        convertido = fecha.format(fechaInicioPrestamo);
        return convertido;
    }

    public String getFechaMesString() {
        String convertido;
        DateFormat fecha = new SimpleDateFormat("MM");
        convertido = fecha.format(fechaInicioPrestamo);
        return convertido;
    }

    public int getDiasValoracion() {
        int diffDays = 0;
        Calendar fecha1 = new GregorianCalendar();
        fecha1.setLenient(false);

        Calendar fecha2 = new GregorianCalendar();
        fecha2.setLenient(false);
        if (fechaInicioPrestamo == null || fechaInicioPrestamo == null) {
            return 0;
        } else {
            fecha1.setTime(fechaInicioPrestamo);
            fecha2.setTime(fechaInicioPrestamo);
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

    public long getTotal() {
        long total = 0;
        return total;
    }

    public List<Libro> getLibroList() {
        return libroList;
    }

    public void setLibroList(List<Libro> libroList) {
        this.libroList = libroList;
    }

    public Grado getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Grado idGrado) {
        this.idGrado = idGrado;
    }
    
    

}
