/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "reporte_biblioteca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteBiblioteca.findAll", query = "SELECT r FROM ReporteBiblioteca r"),
    @NamedQuery(name = "ReporteBiblioteca.findByIdReporte", query = "SELECT r FROM ReporteBiblioteca r WHERE r.idReporte = :idReporte"),
    @NamedQuery(name = "ReporteBiblioteca.findByIdUsuario", query = "SELECT r FROM ReporteBiblioteca r WHERE r.idUsuario = :idUsuario"),
    @NamedQuery(name = "ReporteBiblioteca.findByIdUsuarioReportado", query = "SELECT r FROM ReporteBiblioteca r WHERE r.idUsuarioReportado = :idUsuarioReportado"),
    @NamedQuery(name = "ReporteBiblioteca.findByIdReservaLibro", query = "SELECT r FROM ReporteBiblioteca r WHERE r.idReservaLibro = :idReservaLibro")})
public class ReporteBiblioteca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reporte")
    private Integer idReporte;
    @Lob
    @Size(max = 16777215)
    @Column(name = "descripcion")
    private String descripcion;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "id_usuario_reportado", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuarioReportado;

    @JoinColumn(name = "id_reserva_libro", referencedColumnName = "id_reserva_libros")
    @ManyToOne(optional = false)
    private ReservaLibrosBiblioteca idReservaLibro;

    public ReporteBiblioteca() {
    }

    public ReporteBiblioteca(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public ReporteBiblioteca(Integer idReporte, Usuario idUsuario, Usuario idUsuarioReportado, ReservaLibrosBiblioteca idReservaLibro) {
        this.idReporte = idReporte;
        this.idUsuario = idUsuario;
        this.idUsuarioReportado = idUsuarioReportado;
        this.idReservaLibro = idReservaLibro;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReporte != null ? idReporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteBiblioteca)) {
            return false;
        }
        ReporteBiblioteca other = (ReporteBiblioteca) object;
        if ((this.idReporte == null && other.idReporte != null) || (this.idReporte != null && !this.idReporte.equals(other.idReporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.ReporteBiblioteca[ idReporte=" + idReporte + " ]";
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getIdUsuarioReportado() {
        return idUsuarioReportado;
    }

    public void setIdUsuarioReportado(Usuario idUsuarioReportado) {
        this.idUsuarioReportado = idUsuarioReportado;
    }

    public ReservaLibrosBiblioteca getIdReservaLibro() {
        return idReservaLibro;
    }

    public void setIdReservaLibro(ReservaLibrosBiblioteca idReservaLibro) {
        this.idReservaLibro = idReservaLibro;
    }
    
    

}
