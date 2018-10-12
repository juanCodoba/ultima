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

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "libro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l"),
    @NamedQuery(name = "Libro.findByIdLibro", query = "SELECT l FROM Libro l WHERE l.idLibro = :idLibro"),
    @NamedQuery(name = "Libro.findByTituloLibro", query = "SELECT l FROM Libro l WHERE l.tituloLibro = :tituloLibro"),
    @NamedQuery(name = "Libro.findByCodigo", query = "SELECT l FROM Libro l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "Libro.findByEditorial", query = "SELECT l FROM Libro l WHERE l.editorial = :editorial"),
    @NamedQuery(name = "Libro.findByCopiasDelLibro", query = "SELECT l FROM Libro l WHERE l.copiasDelLibro = :copiasDelLibro"),
    @NamedQuery(name = "Libro.findByCostoLibro", query = "SELECT l FROM Libro l WHERE l.costoLibro = :costoLibro"),
    @NamedQuery(name = "Libro.findByAutor", query = "SELECT l FROM Libro l WHERE l.autor = :autor"),
    @NamedQuery(name = "Libro.findByActivo", query = "SELECT l FROM Libro l WHERE l.activo = 1"),
    @NamedQuery(name = "Libro.findByIdRotulo", query = "SELECT l FROM Libro l WHERE l.idRotulo = :idRotulo"),
    @NamedQuery(name = "Libro.findByConsultaCronograma", query = "SELECT l FROM Libro l WHERE l.idTipoMantenimiento = :idTipoMantenimiento AND l.idEstadoLibro = :idEstadoLibro"),
    @NamedQuery(name = "Libro.findByTipoTotalPrev", query = "SELECT l FROM Libro l WHERE l.idTipoMantenimiento.idTipoMantenimiento = 2 OR l.idTipoMantenimiento.idTipoMantenimiento= 3 ORDER BY l.idLibro DESC"),
    @NamedQuery(name = "Libro.findByTipoTotalCorre", query = "SELECT l FROM Libro l WHERE l.idTipoMantenimiento.idTipoMantenimiento = 1 ORDER BY l.idLibro DESC"),
    @NamedQuery(name = "Libro.findByTipoTotalDeBaja", query = "SELECT l FROM Libro l WHERE l.idTipoMantenimiento.idTipoMantenimiento = 4 ORDER BY l.idLibro DESC"),
    @NamedQuery(name = "Libro.findByTipoTotalReservados", query = "SELECT l FROM Libro l WHERE l.idEstadoLibro.idEstadoLibro = 4 ORDER BY l.idLibro DESC"),

    @NamedQuery(name = "Libro.findByTotalDisponibilidad", query = "SELECT l FROM Libro l WHERE l.idEstadoLibro.idEstadoLibro = 3   ORDER BY l.idLibro DESC"),
    @NamedQuery(name = "Libro.findByTotalDisponibilidadReserva", query = "SELECT l FROM Libro l WHERE l.idEstadoLibro.idEstadoLibro = 3 ORDER BY l.idLibro DESC"),

    @NamedQuery(name = "Libro.findByIdGenero", query = "SELECT l FROM Libro l WHERE l.idGenero = :idGenero")})

public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Integer idLibro;
    @Size(max = 100)
    @Column(name = "titulo_libro")
    private String tituloLibro;
    @Size(max = 45)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 45)
    @Column(name = "editorial")
    private String editorial;
    @Column(name = "copias_libro")
    private Integer copiasDelLibro;
    @Column(name = "costo_libro")
    private Integer costoLibro;
    @Size(max = 60)
    @Column(name = "autor")
    private String autor;
    @Column(name = "activo")
    private Boolean activo;

    @Size(min = 1, max = 500)
    @Column(name = "descripcion_reporte")
    private String descripcion;

    @Size(min = 1, max = 500)
    @Column(name = "descripcion_normal")
    private String descripcionNormal;

    @Column(name = "fecha_reporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporte;

    @Column(name = "fecha_modificacion",insertable = false,updatable = false )
    @Temporal(TemporalType.TIMESTAMP )
    private Date fechaModific;

    @Column(name = "hora_reporte")
    @Temporal(TemporalType.TIME)
    private Date horaReporte;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLib1")
    private List<ReservaLibrosBiblioteca> reservaLibrosBibliotecas1;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLib2")
    private List<ReservaLibrosBiblioteca> reservaLibrosBibliotecas2;

    @JoinColumn(name = "id_rotulo", referencedColumnName = "id_rotulo")
    @ManyToOne(optional = false)
    private Rotulo idRotulo;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuaroLib;

    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero")
    @ManyToOne(optional = false)
    private Genero idGenero;

    @JoinColumn(name = "id_tipo_mantenimiento", referencedColumnName = "id_tipo_mantenimiento")
    @ManyToOne(optional = false)
    private TipoMantenimiento idTipoMantenimiento;

    @JoinColumn(name = "id_estado_libro", referencedColumnName = "id_estado_libro")
    @ManyToOne(optional = false)
    private EstadoLibro idEstadoLibro;

    @Column(name = "fecha_diagnostico")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDiagnostico;

    public Libro() {
    }

    public Libro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public Libro(Integer idLibro, String tituloLibro, String codigo, String editorial, Integer copiasDelLibro, Integer costoLibro, String autor, Boolean activo, List<ReservaLibrosBiblioteca> reservaLibrosBibliotecas1, List<ReservaLibrosBiblioteca> reservaLibrosBibliotecas2, Rotulo idRotulo, Usuario idUsuaroLib, Genero idGenero) {
        this.idLibro = idLibro;
        this.tituloLibro = tituloLibro;
        this.codigo = codigo;
        this.editorial = editorial;
        this.copiasDelLibro = copiasDelLibro;
        this.costoLibro = costoLibro;
        this.autor = autor;
        this.activo = activo;
        this.reservaLibrosBibliotecas1 = reservaLibrosBibliotecas1;
        this.reservaLibrosBibliotecas2 = reservaLibrosBibliotecas2;
        this.idRotulo = idRotulo;
        this.idUsuaroLib = idUsuaroLib;
        this.idGenero = idGenero;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Integer getCopiasDelLibro() {
        return copiasDelLibro;
    }

    public void setCopiasDelLibro(Integer copiasDelLibro) {
        this.copiasDelLibro = copiasDelLibro;
    }

    public Integer getCostoLibro() {
        return costoLibro;
    }

    public void setCostoLibro(Integer costoLibro) {
        this.costoLibro = costoLibro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Rotulo getIdRotulo() {
        return idRotulo;
    }

    public void setIdRotulo(Rotulo idRotulo) {
        this.idRotulo = idRotulo;
    }

    public Genero getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Genero idGenero) {
        this.idGenero = idGenero;
    }

    public Usuario getIdUsuaroLib() {
        return idUsuaroLib;
    }

    public void setIdUsuaroLib(Usuario idUsuaroLib) {
        this.idUsuaroLib = idUsuaroLib;
    }

    public List<ReservaLibrosBiblioteca> getReservaLibrosBibliotecas1() {
        return reservaLibrosBibliotecas1;
    }

    public void setReservaLibrosBibliotecas1(List<ReservaLibrosBiblioteca> reservaLibrosBibliotecas1) {
        this.reservaLibrosBibliotecas1 = reservaLibrosBibliotecas1;
    }

    public List<ReservaLibrosBiblioteca> getReservaLibrosBibliotecas2() {
        return reservaLibrosBibliotecas2;
    }

    public void setReservaLibrosBibliotecas2(List<ReservaLibrosBiblioteca> reservaLibrosBibliotecas2) {
        this.reservaLibrosBibliotecas2 = reservaLibrosBibliotecas2;
    }

    public Date getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(Date fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public TipoMantenimiento getIdTipoMantenimiento() {
        return idTipoMantenimiento;
    }

    public void setIdTipoMantenimiento(TipoMantenimiento idTipoMantenimiento) {
        this.idTipoMantenimiento = idTipoMantenimiento;
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

    public Date getHoraReporte() {
        return horaReporte;
    }

    public void setHoraReporte(Date horaReporte) {
        this.horaReporte = horaReporte;
    }

    public EstadoLibro getIdEstadoLibro() {
        return idEstadoLibro;
    }

    public void setIdEstadoLibro(EstadoLibro idEstadoLibro) {
        this.idEstadoLibro = idEstadoLibro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLibro != null ? idLibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.idLibro == null && other.idLibro != null) || (this.idLibro != null && !this.idLibro.equals(other.idLibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getCodigo()+ "-" + getTituloLibro().toUpperCase();
    }

    public Date getFechaModific() {
        return fechaModific;
    }

    public void setFechaModific(Date fechaModific) {
        this.fechaModific = fechaModific;
    }

    public String getDescripcionNormal() {
        return descripcionNormal;
    }

    public void setDescripcionNormal(String descripcionNormal) {
        this.descripcionNormal = descripcionNormal;
    }
    
    
    

}
