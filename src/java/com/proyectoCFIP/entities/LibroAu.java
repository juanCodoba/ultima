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
import javax.persistence.Lob;
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
@Table(name = "libro_au")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LibroAu.findAll", query = "SELECT l FROM LibroAu l"),
    @NamedQuery(name = "LibroAu.findByIdLibroAu", query = "SELECT l FROM LibroAu l WHERE l.idLibroAu = :idLibroAu"),
    @NamedQuery(name = "LibroAu.findByTituloOld", query = "SELECT l FROM LibroAu l WHERE l.tituloOld = :tituloOld"),
    @NamedQuery(name = "LibroAu.findByCodigoOld", query = "SELECT l FROM LibroAu l WHERE l.codigoOld = :codigoOld"),
    @NamedQuery(name = "LibroAu.findByEditorialOld", query = "SELECT l FROM LibroAu l WHERE l.editorialOld = :editorialOld"),
    @NamedQuery(name = "LibroAu.findByCopiasLibroOld", query = "SELECT l FROM LibroAu l WHERE l.copiasLibroOld = :copiasLibroOld"),
    @NamedQuery(name = "LibroAu.findByCostoLibroOld", query = "SELECT l FROM LibroAu l WHERE l.costoLibroOld = :costoLibroOld"),
    @NamedQuery(name = "LibroAu.findByAutorOld", query = "SELECT l FROM LibroAu l WHERE l.autorOld = :autorOld"),
    @NamedQuery(name = "LibroAu.findByActivoOld", query = "SELECT l FROM LibroAu l WHERE l.activoOld = :activoOld"),
    @NamedQuery(name = "LibroAu.findByIdRotuloOld", query = "SELECT l FROM LibroAu l WHERE l.idRotuloOld = :idRotuloOld"),
    @NamedQuery(name = "LibroAu.findByIdGeneroOld", query = "SELECT l FROM LibroAu l WHERE l.idGeneroOld = :idGeneroOld"),
    @NamedQuery(name = "LibroAu.findByIdUsuarioOld", query = "SELECT l FROM LibroAu l WHERE l.idUsuarioOld = :idUsuarioOld"),
    @NamedQuery(name = "LibroAu.findByFechaDiagnosticoOld", query = "SELECT l FROM LibroAu l WHERE l.fechaDiagnosticoOld = :fechaDiagnosticoOld"),
    @NamedQuery(name = "LibroAu.findByFechaReporteOld", query = "SELECT l FROM LibroAu l WHERE l.fechaReporteOld = :fechaReporteOld"),
    @NamedQuery(name = "LibroAu.findByHoraReporteOld", query = "SELECT l FROM LibroAu l WHERE l.horaReporteOld = :horaReporteOld"),
    @NamedQuery(name = "LibroAu.findByIdTipoMantenimientoOld", query = "SELECT l FROM LibroAu l WHERE l.idTipoMantenimientoOld = :idTipoMantenimientoOld"),
    @NamedQuery(name = "LibroAu.findByIdEstadoLibroOld", query = "SELECT l FROM LibroAu l WHERE l.idEstadoLibroOld = :idEstadoLibroOld"),
    @NamedQuery(name = "LibroAu.findByTituloNew", query = "SELECT l FROM LibroAu l WHERE l.tituloNew = :tituloNew"),
    @NamedQuery(name = "LibroAu.findByCodigoNew", query = "SELECT l FROM LibroAu l WHERE l.codigoNew = :codigoNew"),
    @NamedQuery(name = "LibroAu.findByEditorialNew", query = "SELECT l FROM LibroAu l WHERE l.editorialNew = :editorialNew"),
    @NamedQuery(name = "LibroAu.findByCopiasLibroNew", query = "SELECT l FROM LibroAu l WHERE l.copiasLibroNew = :copiasLibroNew"),
    @NamedQuery(name = "LibroAu.findByCostoLibroNew", query = "SELECT l FROM LibroAu l WHERE l.costoLibroNew = :costoLibroNew"),
    @NamedQuery(name = "LibroAu.findByAutorNew", query = "SELECT l FROM LibroAu l WHERE l.autorNew = :autorNew"),
    @NamedQuery(name = "LibroAu.findByActivoNew", query = "SELECT l FROM LibroAu l WHERE l.activoNew = :activoNew"),
    @NamedQuery(name = "LibroAu.findByIdRotuloNew", query = "SELECT l FROM LibroAu l WHERE l.idRotuloNew = :idRotuloNew"),
    @NamedQuery(name = "LibroAu.findByIdGeneroNew", query = "SELECT l FROM LibroAu l WHERE l.idGeneroNew = :idGeneroNew"),
    @NamedQuery(name = "LibroAu.findByIdUsuarioNew", query = "SELECT l FROM LibroAu l WHERE l.idUsuarioNew = :idUsuarioNew"),
    @NamedQuery(name = "LibroAu.findByFechaDiagnosticoNew", query = "SELECT l FROM LibroAu l WHERE l.fechaDiagnosticoNew = :fechaDiagnosticoNew"),
    @NamedQuery(name = "LibroAu.findByFechaReporteNew", query = "SELECT l FROM LibroAu l WHERE l.fechaReporteNew = :fechaReporteNew"),
    @NamedQuery(name = "LibroAu.findByHoraReporteNew", query = "SELECT l FROM LibroAu l WHERE l.horaReporteNew = :horaReporteNew"),
    @NamedQuery(name = "LibroAu.findByIdTipoMantenimientoNew", query = "SELECT l FROM LibroAu l WHERE l.idTipoMantenimientoNew = :idTipoMantenimientoNew"),
    @NamedQuery(name = "LibroAu.findByIdEstadoLibroNew", query = "SELECT l FROM LibroAu l WHERE l.idEstadoLibroNew = :idEstadoLibroNew"),
    @NamedQuery(name = "LibroAu.findByFechaCambio", query = "SELECT l FROM LibroAu l WHERE l.fechaCambio = :fechaCambio")})
public class LibroAu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_libro_au")
    private Integer idLibroAu;
    @Size(max = 45)
    @Column(name = "titulo_old")
    private String tituloOld;
    @Size(max = 45)
    @Column(name = "codigo_old")
    private String codigoOld;
    @Size(max = 45)
    @Column(name = "editorial_old")
    private String editorialOld;
    @Column(name = "copias_libro_old")
    private Integer copiasLibroOld;
    @Column(name = "costo_libro_old")
    private Integer costoLibroOld;
    @Size(max = 60)
    @Column(name = "autor_old")
    private String autorOld;
    @Column(name = "activo_old")
    private Short activoOld;
    @Column(name = "id_rotulo_old")
    private Integer idRotuloOld;
    @Column(name = "id_genero_old")
    private Integer idGeneroOld;
    @Column(name = "id_usuario_old")
    private Integer idUsuarioOld;
    @Column(name = "fecha_diagnostico_old")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDiagnosticoOld;
    @Column(name = "fecha_reporte_old")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporteOld;
    @Column(name = "hora_reporte_old")
    @Temporal(TemporalType.TIME)
    private Date horaReporteOld;
    @Lob
    @Size(max = 16777215)
    @Column(name = "descripcion_reporte_old")
    private String descripcionReporteOld;
    @Column(name = "id_tipo_mantenimiento_old")
    private Integer idTipoMantenimientoOld;
    @Column(name = "id_estado_libro_old")
    private Integer idEstadoLibroOld;
    @Size(max = 45)
    @Column(name = "titulo_new")
    private String tituloNew;
    @Size(max = 45)
    @Column(name = "codigo_new")
    private String codigoNew;
    @Size(max = 45)
    @Column(name = "editorial_new")
    private String editorialNew;
    @Column(name = "copias_libro_new")
    private Integer copiasLibroNew;
    @Column(name = "costo_libro_new")
    private Integer costoLibroNew;
    @Size(max = 65)
    @Column(name = "autor_new")
    private String autorNew;
    @Column(name = "activo_new")
    private Short activoNew;
    @Column(name = "id_rotulo_new")
    private Integer idRotuloNew;
    @Column(name = "id_genero_new")
    private Integer idGeneroNew;
    @Column(name = "id_usuario_new")
    private Integer idUsuarioNew;
    @Column(name = "fecha_diagnostico_new")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDiagnosticoNew;
    @Column(name = "fecha_reporte_new")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporteNew;
    @Column(name = "hora_reporte_new")
    @Temporal(TemporalType.TIME)
    private Date horaReporteNew;
    @Lob
    @Size(max = 16777215)
    @Column(name = "descripcion_reporte_new")
    private String descripcionReporteNew;
    @Column(name = "id_tipo_mantenimiento_new")
    private Integer idTipoMantenimientoNew;
    @Column(name = "id_estado_libro_new")
    private Integer idEstadoLibroNew;
    @Column(name = "fecha_cambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambio;

    public LibroAu() {
    }

    public LibroAu(Integer idLibroAu) {
        this.idLibroAu = idLibroAu;
    }

    public Integer getIdLibroAu() {
        return idLibroAu;
    }

    public void setIdLibroAu(Integer idLibroAu) {
        this.idLibroAu = idLibroAu;
    }

    public String getTituloOld() {
        return tituloOld;
    }

    public void setTituloOld(String tituloOld) {
        this.tituloOld = tituloOld;
    }

    public String getCodigoOld() {
        return codigoOld;
    }

    public void setCodigoOld(String codigoOld) {
        this.codigoOld = codigoOld;
    }

    public String getEditorialOld() {
        return editorialOld;
    }

    public void setEditorialOld(String editorialOld) {
        this.editorialOld = editorialOld;
    }

    public Integer getCopiasLibroOld() {
        return copiasLibroOld;
    }

    public void setCopiasLibroOld(Integer copiasLibroOld) {
        this.copiasLibroOld = copiasLibroOld;
    }

    public Integer getCostoLibroOld() {
        return costoLibroOld;
    }

    public void setCostoLibroOld(Integer costoLibroOld) {
        this.costoLibroOld = costoLibroOld;
    }

    public String getAutorOld() {
        return autorOld;
    }

    public void setAutorOld(String autorOld) {
        this.autorOld = autorOld;
    }

    public Short getActivoOld() {
        return activoOld;
    }

    public void setActivoOld(Short activoOld) {
        this.activoOld = activoOld;
    }

    public Integer getIdRotuloOld() {
        return idRotuloOld;
    }

    public void setIdRotuloOld(Integer idRotuloOld) {
        this.idRotuloOld = idRotuloOld;
    }

    public Integer getIdGeneroOld() {
        return idGeneroOld;
    }

    public void setIdGeneroOld(Integer idGeneroOld) {
        this.idGeneroOld = idGeneroOld;
    }

    public Integer getIdUsuarioOld() {
        return idUsuarioOld;
    }

    public void setIdUsuarioOld(Integer idUsuarioOld) {
        this.idUsuarioOld = idUsuarioOld;
    }

    public Date getFechaDiagnosticoOld() {
        return fechaDiagnosticoOld;
    }

    public void setFechaDiagnosticoOld(Date fechaDiagnosticoOld) {
        this.fechaDiagnosticoOld = fechaDiagnosticoOld;
    }

    public Date getFechaReporteOld() {
        return fechaReporteOld;
    }

    public void setFechaReporteOld(Date fechaReporteOld) {
        this.fechaReporteOld = fechaReporteOld;
    }

    public Date getHoraReporteOld() {
        return horaReporteOld;
    }

    public void setHoraReporteOld(Date horaReporteOld) {
        this.horaReporteOld = horaReporteOld;
    }

    public String getDescripcionReporteOld() {
        return descripcionReporteOld;
    }

    public void setDescripcionReporteOld(String descripcionReporteOld) {
        this.descripcionReporteOld = descripcionReporteOld;
    }

    public Integer getIdTipoMantenimientoOld() {
        return idTipoMantenimientoOld;
    }

    public void setIdTipoMantenimientoOld(Integer idTipoMantenimientoOld) {
        this.idTipoMantenimientoOld = idTipoMantenimientoOld;
    }

    public Integer getIdEstadoLibroOld() {
        return idEstadoLibroOld;
    }

    public void setIdEstadoLibroOld(Integer idEstadoLibroOld) {
        this.idEstadoLibroOld = idEstadoLibroOld;
    }

    public String getTituloNew() {
        return tituloNew;
    }

    public void setTituloNew(String tituloNew) {
        this.tituloNew = tituloNew;
    }

    public String getCodigoNew() {
        return codigoNew;
    }

    public void setCodigoNew(String codigoNew) {
        this.codigoNew = codigoNew;
    }

    public String getEditorialNew() {
        return editorialNew;
    }

    public void setEditorialNew(String editorialNew) {
        this.editorialNew = editorialNew;
    }

    public Integer getCopiasLibroNew() {
        return copiasLibroNew;
    }

    public void setCopiasLibroNew(Integer copiasLibroNew) {
        this.copiasLibroNew = copiasLibroNew;
    }

    public Integer getCostoLibroNew() {
        return costoLibroNew;
    }

    public void setCostoLibroNew(Integer costoLibroNew) {
        this.costoLibroNew = costoLibroNew;
    }

    public String getAutorNew() {
        return autorNew;
    }

    public void setAutorNew(String autorNew) {
        this.autorNew = autorNew;
    }

    public Short getActivoNew() {
        return activoNew;
    }

    public void setActivoNew(Short activoNew) {
        this.activoNew = activoNew;
    }

    public Integer getIdRotuloNew() {
        return idRotuloNew;
    }

    public void setIdRotuloNew(Integer idRotuloNew) {
        this.idRotuloNew = idRotuloNew;
    }

    public Integer getIdGeneroNew() {
        return idGeneroNew;
    }

    public void setIdGeneroNew(Integer idGeneroNew) {
        this.idGeneroNew = idGeneroNew;
    }

    public Integer getIdUsuarioNew() {
        return idUsuarioNew;
    }

    public void setIdUsuarioNew(Integer idUsuarioNew) {
        this.idUsuarioNew = idUsuarioNew;
    }

    public Date getFechaDiagnosticoNew() {
        return fechaDiagnosticoNew;
    }

    public void setFechaDiagnosticoNew(Date fechaDiagnosticoNew) {
        this.fechaDiagnosticoNew = fechaDiagnosticoNew;
    }

    public Date getFechaReporteNew() {
        return fechaReporteNew;
    }

    public void setFechaReporteNew(Date fechaReporteNew) {
        this.fechaReporteNew = fechaReporteNew;
    }

    public Date getHoraReporteNew() {
        return horaReporteNew;
    }

    public void setHoraReporteNew(Date horaReporteNew) {
        this.horaReporteNew = horaReporteNew;
    }

    public String getDescripcionReporteNew() {
        return descripcionReporteNew;
    }

    public void setDescripcionReporteNew(String descripcionReporteNew) {
        this.descripcionReporteNew = descripcionReporteNew;
    }

    public Integer getIdTipoMantenimientoNew() {
        return idTipoMantenimientoNew;
    }

    public void setIdTipoMantenimientoNew(Integer idTipoMantenimientoNew) {
        this.idTipoMantenimientoNew = idTipoMantenimientoNew;
    }

    public Integer getIdEstadoLibroNew() {
        return idEstadoLibroNew;
    }

    public void setIdEstadoLibroNew(Integer idEstadoLibroNew) {
        this.idEstadoLibroNew = idEstadoLibroNew;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLibroAu != null ? idLibroAu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LibroAu)) {
            return false;
        }
        LibroAu other = (LibroAu) object;
        if ((this.idLibroAu == null && other.idLibroAu != null) || (this.idLibroAu != null && !this.idLibroAu.equals(other.idLibroAu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.LibroAu[ idLibroAu=" + idLibroAu + " ]";
    }
    
}
