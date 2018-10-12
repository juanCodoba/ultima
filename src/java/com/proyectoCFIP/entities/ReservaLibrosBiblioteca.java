/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
    @NamedQuery(name = "ReservaLibrosBiblioteca.findByUsuario", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.idUsuarioPrestamo = :idUsuarioPrestamo ORDER BY  r.idReservaLibros  ASC "),
    @NamedQuery(name = "ReservaLibrosBiblioteca.estado", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE  r.idLib1.idEstadoLibro.idEstadoLibro  <=3 AND r.idLib2.idEstadoLibro.idEstadoLibro <=3 ORDER BY  r.idReservaLibros  ASC"),
    @NamedQuery(name = "ReservaLibrosBiblioteca.estadoTrue", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.activo = true ORDER BY  r.idReservaLibros  ASC"),
    @NamedQuery(name = "ReservaLibrosBiblioteca.estadoFalse", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.activo = false ORDER BY  r.idReservaLibros  ASC"),
    @NamedQuery(name = "ReservaLibrosBiblioteca.findByIdTipoEstudiante", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.idTipoEstudiante = :idTipoEstudiante order by r.idReservaLibros ASC "),

    @NamedQuery(name = "ReservaLibrosBiblioteca.findByBibliotecario", query = "SELECT r FROM ReservaLibrosBiblioteca r WHERE r.idBibliotecario.idUsuario = 223 ORDER BY  r.idReservaLibros  ASC")})

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

    @JoinColumn(name = "id_libro_1", referencedColumnName = "id_libro")
    @ManyToOne
    private Libro idLib1;

    @JoinColumn(name = "id_libro_2", referencedColumnName = "id_libro")
    @ManyToOne
    private Libro idLib2;

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

    @Column(name = "estado_libro_uno")
    private Boolean estadoUsuarioReservas;

    @Column(name = "estado_libro_dos")
    private Boolean estadoUsuarioReservasCompletMe;

    @Column(name = "estado_libro_tres")
    private Boolean sinPreserva;

    @JoinColumn(name = "id_tipo_estudiante", referencedColumnName = "id_tipo_estudiante")
    @ManyToOne(optional = true)
    private TipoEstudiante idTipoEstudiante;

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
        this.idLib1 = idLib1;
        this.idLib2 = idLib2;
        this.observacionesLib = observacionesLib;
        this.gradoEstudiante = gradoEstudiante;
        this.activo = activo;
        this.estadoUsuarioReservas = estadoUsuarioReservas;
        this.estadoUsuarioReservasCompletMe = estadoUsuarioReservasCompletMe;
        this.sinPreserva = sinPreserva;
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

    public Libro getIdLib1() {
        return idLib1;
    }

    public void setIdLib1(Libro idLib1) {
        this.idLib1 = idLib1;
    }

    public Libro getIdLib2() {
        return idLib2;
    }

    public void setIdLib2(Libro idLib2) {
        this.idLib2 = idLib2;
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

    public Boolean getEstadoUsuarioReservas() {
        return estadoUsuarioReservas;
    }

    public void setEstadoUsuarioReservas(Boolean estadoUsuarioReservas) {
        this.estadoUsuarioReservas = estadoUsuarioReservas;
    }

    public Boolean getEstadoUsuarioReservasCompletMe() {
        return estadoUsuarioReservasCompletMe;
    }

    public void setEstadoUsuarioReservasCompletMe(Boolean estadoUsuarioReservasCompletMe) {
        this.estadoUsuarioReservasCompletMe = estadoUsuarioReservasCompletMe;
    }

    public Boolean getSinPreserva() {
        return sinPreserva;
    }

    public void setSinPreserva(Boolean sinPreserva) {
        this.sinPreserva = sinPreserva;
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

    
    

}
