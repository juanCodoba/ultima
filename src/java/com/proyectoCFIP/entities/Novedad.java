/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "novedad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Novedad.findAll", query = "SELECT n FROM Novedad n ORDER BY n.idNovedad DESC"),
    @NamedQuery(name = "Novedad.findByIdNovedad", query = "SELECT n FROM Novedad n WHERE n.idNovedad = :idNovedad"),
    @NamedQuery(name = "Novedad.findByFechaActual", query = "SELECT n FROM Novedad n WHERE n.fechaActual = :fechaActual"),
    @NamedQuery(name = "Novedad.findByOp", query = "SELECT n FROM Novedad n WHERE n.op = :op"),
    @NamedQuery(name = "Novedad.findByItem", query = "SELECT n FROM Novedad n WHERE n.item = :item"),
    @NamedQuery(name = "Novedad.findByIdUsuarioCreacion", query = "SELECT n FROM Novedad n WHERE n.idUsuarioCreacion = :idUsuarioCreacion"),
    @NamedQuery(name = "Novedad.findByIdUsuarioReporta", query = "SELECT n FROM Novedad n WHERE n.idUsuarioReporta = :idUsuarioReporta"),
    @NamedQuery(name = "Novedad.findByEstado", query = "SELECT n FROM Novedad n ORDER BY n.idNovedad DESC"),
    @NamedQuery(name = "Novedad.findByIdFtCliente", query = "SELECT n FROM Novedad n WHERE n.idFtCliente = :idFtCliente")})
public class Novedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_novedad")
    private Integer idNovedad;
    @Column(name = "fecha_actual",insertable = false,updatable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaActual;
    @Column(name = "op")
    private Integer op;
    @Column(name = "item")
    private Integer item;
    @Size(max = 2147483647)
    @Column(name = "evidencia_foto")
    private String evidenciaFoto;
    @Size(max = 2147483647)
    @Column(name = "descripcion_novedad")
    private String descripcionNovedad;
    @Column(name = "estado")
    private String estado;

    @JoinColumn(name = "id_usuario_creacion", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuarioCreacion;

    @JoinColumn(name = "id_usuario_reporta", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuarioReporta;

    @JoinColumn(name = "id_ft_cliente", referencedColumnName = "id_ft_cliente")
    @ManyToOne
    private FtCliente idFtCliente;

    public Novedad() {
    }

    public Novedad(Integer idNovedad) {
        this.idNovedad = idNovedad;
    }

    public Novedad(Integer idNovedad, Usuario idUsuarioCreacion, Usuario idUsuarioReporta, FtCliente idFtCliente) {
        this.idNovedad = idNovedad;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.idUsuarioReporta = idUsuarioReporta;
        this.idFtCliente = idFtCliente;
    }

    public Integer getIdNovedad() {
        return idNovedad;
    }

    public void setIdNovedad(Integer idNovedad) {
        this.idNovedad = idNovedad;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public Integer getOp() {
        return op;
    }

    public void setOp(Integer op) {
        this.op = op;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public String getEvidenciaFoto() {
        return evidenciaFoto;
    }

    public void setEvidenciaFoto(String evidenciaFoto) {
        this.evidenciaFoto = evidenciaFoto;
    }

    public String getDescripcionNovedad() {
        return descripcionNovedad;
    }

    public void setDescripcionNovedad(String descripcionNovedad) {
        this.descripcionNovedad = descripcionNovedad;
    }

    public Usuario getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(Usuario idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Usuario getIdUsuarioReporta() {
        return idUsuarioReporta;
    }

    public void setIdUsuarioReporta(Usuario idUsuarioReporta) {
        this.idUsuarioReporta = idUsuarioReporta;
    }

    public FtCliente getIdFtCliente() {
        return idFtCliente;
    }

    public void setIdFtCliente(FtCliente idFtCliente) {
        this.idFtCliente = idFtCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNovedad != null ? idNovedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Novedad)) {
            return false;
        }
        Novedad other = (Novedad) object;
        if ((this.idNovedad == null && other.idNovedad != null) || (this.idNovedad != null && !this.idNovedad.equals(other.idNovedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.Novedad[ idNovedad=" + idNovedad + " ]";
    }

}
