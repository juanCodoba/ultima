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
@Table(name = "calidad_gestion_conocimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalidadGestionConocimiento.findAll", query = "SELECT c FROM CalidadGestionConocimiento c")
    , @NamedQuery(name = "CalidadGestionConocimiento.findByIdGestionConocimiento", query = "SELECT c FROM CalidadGestionConocimiento c WHERE c.idGestionConocimiento = :idGestionConocimiento")
    , @NamedQuery(name = "CalidadGestionConocimiento.findByFecha", query = "SELECT c FROM CalidadGestionConocimiento c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "CalidadGestionConocimiento.findByTitulo", query = "SELECT c FROM CalidadGestionConocimiento c WHERE c.titulo = :titulo")})
public class CalidadGestionConocimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_gestion_conocimiento")
    private Integer idGestionConocimiento;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "situacion_presentada")
    private String situacionPresentada;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "leccion_aprendida")
    private String leccionAprendida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titulo")
    private String titulo;
    @JoinColumn(name = "id_subproceso", referencedColumnName = "id_subproceso")
    @ManyToOne(optional = false)
    private Subprocesos idSubproceso;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public CalidadGestionConocimiento() {
    }

    public CalidadGestionConocimiento(Integer idGestionConocimiento) {
        this.idGestionConocimiento = idGestionConocimiento;
    }

    public CalidadGestionConocimiento(Integer idGestionConocimiento, String situacionPresentada, String leccionAprendida, Date fecha, String titulo) {
        this.idGestionConocimiento = idGestionConocimiento;
        this.situacionPresentada = situacionPresentada;
        this.leccionAprendida = leccionAprendida;
        this.fecha = fecha;
        this.titulo = titulo;
    }

    public Integer getIdGestionConocimiento() {
        return idGestionConocimiento;
    }

    public void setIdGestionConocimiento(Integer idGestionConocimiento) {
        this.idGestionConocimiento = idGestionConocimiento;
    }

    public String getSituacionPresentada() {
        return situacionPresentada;
    }

    public void setSituacionPresentada(String situacionPresentada) {
        this.situacionPresentada = situacionPresentada;
    }

    public String getLeccionAprendida() {
        return leccionAprendida;
    }

    public void setLeccionAprendida(String leccionAprendida) {
        this.leccionAprendida = leccionAprendida;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Subprocesos getIdSubproceso() {
        return idSubproceso;
    }

    public void setIdSubproceso(Subprocesos idSubproceso) {
        this.idSubproceso = idSubproceso;
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
        hash += (idGestionConocimiento != null ? idGestionConocimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalidadGestionConocimiento)) {
            return false;
        }
        CalidadGestionConocimiento other = (CalidadGestionConocimiento) object;
        if ((this.idGestionConocimiento == null && other.idGestionConocimiento != null) || (this.idGestionConocimiento != null && !this.idGestionConocimiento.equals(other.idGestionConocimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.CalidadGestionConocimiento[ idGestionConocimiento=" + idGestionConocimiento + " ]";
    }
    
}
