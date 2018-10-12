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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "aplicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aplicacion.findAll", query = "SELECT a FROM Aplicacion a"),
    @NamedQuery(name = "Aplicacion.findByIdAplicacion", query = "SELECT a FROM Aplicacion a WHERE a.idAplicacion = :idAplicacion"),
    @NamedQuery(name = "Aplicacion.findByNombreAplicacion", query = "SELECT a FROM Aplicacion a WHERE a.nombreAplicacion = :nombreAplicacion"),
    @NamedQuery(name = "Aplicacion.findByFechaInicioUso", query = "SELECT a FROM Aplicacion a WHERE a.fechaInicioUso = :fechaInicioUso"),
    @NamedQuery(name = "Aplicacion.findByFechaFinUso", query = "SELECT a FROM Aplicacion a WHERE a.fechaFinUso = :fechaFinUso"),
    @NamedQuery(name = "Aplicacion.findByDescripcionFinUso", query = "SELECT a FROM Aplicacion a WHERE a.descripcionFinUso = :descripcionFinUso"),
    @NamedQuery(name = "Aplicacion.findByVersionAplicacion", query = "SELECT a FROM Aplicacion a WHERE a.versionAplicacion = :versionAplicacion")})
public class Aplicacion implements Serializable {

   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_aplicacion")
    private Integer idAplicacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "nombre_aplicacion")
    private String nombreAplicacion;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion_aplicacion")
    private String descripcionAplicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio_uso")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioUso;
    @Column(name = "fecha_fin_uso")
    @Temporal(TemporalType.DATE)
    private Date fechaFinUso;
    @Size(max = 75)
    @Column(name = "descripcion_fin_uso")
    private String descripcionFinUso;
    @Size(max = 75)
    @Column(name = "version_aplicacion")
    private String versionAplicacion;
    @JoinColumn(name = "id_licencia", referencedColumnName = "id_licencia")
    @ManyToOne(optional = false)
    private Licencia idLicencia;
    @ManyToMany(mappedBy = "aplicacionList")
    private List<Computador> computadorList;
    public Aplicacion() {
    }

    public Aplicacion(Integer idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public Aplicacion(Integer idAplicacion, String nombreAplicacion, Date fechaInicioUso) {
        this.idAplicacion = idAplicacion;
        this.nombreAplicacion = nombreAplicacion;
        this.fechaInicioUso = fechaInicioUso;
    }

    public Integer getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(Integer idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

    public String getDescripcionAplicacion() {
        return descripcionAplicacion;
    }

    public void setDescripcionAplicacion(String descripcionAplicacion) {
        this.descripcionAplicacion = descripcionAplicacion;
    }

    public Date getFechaInicioUso() {
        return fechaInicioUso;
    }

    public void setFechaInicioUso(Date fechaInicioUso) {
        this.fechaInicioUso = fechaInicioUso;
    }

    public Date getFechaFinUso() {
        return fechaFinUso;
    }

    public void setFechaFinUso(Date fechaFinUso) {
        this.fechaFinUso = fechaFinUso;
    }

    public String getDescripcionFinUso() {
        return descripcionFinUso;
    }

    public void setDescripcionFinUso(String descripcionFinUso) {
        this.descripcionFinUso = descripcionFinUso;
    }

    public String getVersionAplicacion() {
        return versionAplicacion;
    }

    public void setVersionAplicacion(String versionAplicacion) {
        this.versionAplicacion = versionAplicacion;
    }

    public Licencia getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(Licencia idLicencia) {
        this.idLicencia = idLicencia;
    }
    @XmlTransient
    public List<Computador> getComputadorList() {
        return computadorList;
    }

    public void setComputadorList(List<Computador> computadorList) {
        this.computadorList = computadorList;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAplicacion != null ? idAplicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aplicacion)) {
            return false;
        }
        Aplicacion other = (Aplicacion) object;
        if ((this.idAplicacion == null && other.idAplicacion != null) || (this.idAplicacion != null && !this.idAplicacion.equals(other.idAplicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (versionAplicacion == null){
            versionAplicacion ="No tiene";
        }
        return getNombreAplicacion().toUpperCase()+" / Versión: "+versionAplicacion.toUpperCase();
    }

    
}
