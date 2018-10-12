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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author junio
 */
@Entity
@Table(name = "ft_modificaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtModificaciones.findAll", query = "SELECT f FROM FtModificaciones f")
    , @NamedQuery(name = "FtModificaciones.findByTodas", query = "SELECT f FROM FtModificaciones f ORDER BY f.fecha DESC")
    , @NamedQuery(name = "FtModificaciones.findByIdModificaciones", query = "SELECT f FROM FtModificaciones f WHERE f.idModificaciones = :idModificaciones")
    , @NamedQuery(name = "FtModificaciones.findByPqs", query = "SELECT f FROM FtModificaciones f WHERE f.pqs = :pqs")
    , @NamedQuery(name = "FtModificaciones.findByIdFicha", query = "SELECT f FROM FtModificaciones f WHERE f.ftFichaTecnica = :ficha ORDER BY f.fecha DESC")
    , @NamedQuery(name = "FtModificaciones.findByEstado", query = "SELECT f FROM FtModificaciones f WHERE f.estado = :estado")})
public class FtModificaciones implements Serializable {

    @Size(max = 150)
    @Column(name = "usuario")
    private String usuario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_modificaciones")
    private Integer idModificaciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pqs")
    private String pqs;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "ft_ficha_tecnica", referencedColumnName = "id_ft_ficha_tecnica")
    @ManyToOne(optional = false)
    private FtFichaTecnica ftFichaTecnica;

    public FtModificaciones() {
    }

    public FtModificaciones(Integer idModificaciones) {
        this.idModificaciones = idModificaciones;
    }

    public FtModificaciones(Integer idModificaciones, String pqs, String descripcion) {
        this.idModificaciones = idModificaciones;
        this.pqs = pqs;
        this.descripcion = descripcion;
    }

    public Integer getIdModificaciones() {
        return idModificaciones;
    }

    public void setIdModificaciones(Integer idModificaciones) {
        this.idModificaciones = idModificaciones;
    }

    public String getPqs() {
        return pqs;
    }

    public void setPqs(String pqs) {
        this.pqs = pqs;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public FtFichaTecnica getFtFichaTecnica() {
        return ftFichaTecnica;
    }

    public void setFtFichaTecnica(FtFichaTecnica ftFichaTecnica) {
        this.ftFichaTecnica = ftFichaTecnica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModificaciones != null ? idModificaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtModificaciones)) {
            return false;
        }
        FtModificaciones other = (FtModificaciones) object;
        if ((this.idModificaciones == null && other.idModificaciones != null) || (this.idModificaciones != null && !this.idModificaciones.equals(other.idModificaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.FtModificaciones[ idModificaciones=" + idModificaciones + " ]";
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
