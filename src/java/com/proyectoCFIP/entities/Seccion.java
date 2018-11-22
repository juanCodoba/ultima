/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "seccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s"),
    @NamedQuery(name = "Seccion.findByIdSeccion", query = "SELECT s FROM Seccion s WHERE s.idSeccion = :idSeccion"),
    @NamedQuery(name = "Seccion.findByIdTipoSeccion", query = "SELECT s FROM Seccion s WHERE s.idTipoSeccion = :idTipoSeccion1 OR s.idTipoSeccion = :idTipoSeccion2 ORDER BY s.idTipoSeccion.nombre"),
    @NamedQuery(name = "Seccion.findByNombreSeccion", query = "SELECT s FROM Seccion s WHERE s.nombreSeccion = :nombreSeccion")})
public class Seccion implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeccion")
    private List<SstReporteUsuario> sstReporteUsuarioList;

    @OneToMany(cascade = CascadeType.PERSIST,fetch=FetchType.LAZY, mappedBy = "idSeccion")
    private List<CronogramaActividadesEdificios> cronogramaActividadesEdificiosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeccion")
    private List<SolicitudEdificio> solicitudEdificioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeccion")
    private List<OtroDispositivo> otroDispositivoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_seccion")
    private Integer idSeccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_seccion")
    private String nombreSeccion;
    @OneToMany(mappedBy = "idSeccion")
    private List<Computador> computadorList;
    @JoinColumn(name = "id_bloque", referencedColumnName = "id_bloque")
    @ManyToOne(optional = false)
    private Bloque idBloque;
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne(optional = false)
    private Area idArea;
    @JoinColumn(name = "id_tipo_seccion", referencedColumnName = "id_tipo_seccion")
    @ManyToOne(optional = false)
    private TipoSeccion idTipoSeccion;
    public Seccion() {
    }

    public Seccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
    }

    public Seccion(Integer idSeccion, String nombreSeccion) {
        this.idSeccion = idSeccion;
        this.nombreSeccion = nombreSeccion;
    }

    public Integer getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public void setNombreSeccion(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }

    @XmlTransient
    public List<Computador> getComputadorList() {
        return computadorList;
    }

    public void setComputadorList(List<Computador> computadorList) {
        this.computadorList = computadorList;
    }

    public Bloque getIdBloque() {
        return idBloque;
    }

    public void setIdBloque(Bloque idBloque) {
        this.idBloque = idBloque;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public TipoSeccion getIdTipoSeccion() {
        return idTipoSeccion;
    }

    public void setIdTipoSeccion(TipoSeccion idTipoSeccion) {
        this.idTipoSeccion = idTipoSeccion;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeccion != null ? idSeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.idSeccion == null && other.idSeccion != null) || (this.idSeccion != null && !this.idSeccion.equals(other.idSeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombreSeccion().toUpperCase()+" / BLOQUE: "+getIdBloque().getNombreBloque().toUpperCase();
    }

    @XmlTransient
    public List<OtroDispositivo> getOtroDispositivoList() {
        return otroDispositivoList;
    }

    public void setOtroDispositivoList(List<OtroDispositivo> otroDispositivoList) {
        this.otroDispositivoList = otroDispositivoList;
    }

    @XmlTransient
    public List<SolicitudEdificio> getSolicitudEdificioList() {
        return solicitudEdificioList;
    }

    public void setSolicitudEdificioList(List<SolicitudEdificio> solicitudEdificioList) {
        this.solicitudEdificioList = solicitudEdificioList;
    }

    @XmlTransient
    public List<CronogramaActividadesEdificios> getCronogramaActividadesEdificiosList() {
        return cronogramaActividadesEdificiosList;
    }

    public void setCronogramaActividadesEdificiosList(List<CronogramaActividadesEdificios> cronogramaActividadesEdificiosList) {
        this.cronogramaActividadesEdificiosList = cronogramaActividadesEdificiosList;
    }

    @XmlTransient
    public List<SstReporteUsuario> getSstReporteUsuarioList() {
        return sstReporteUsuarioList;
    }

    public void setSstReporteUsuarioList(List<SstReporteUsuario> sstReporteUsuarioList) {
        this.sstReporteUsuarioList = sstReporteUsuarioList;
    }
    
}
