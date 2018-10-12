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
@Table(name = "historial_computador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialComputador.findAll", query = "SELECT h FROM HistorialComputador h"),
    @NamedQuery(name = "HistorialComputador.findByIdHistorialComputador", query = "SELECT h FROM HistorialComputador h WHERE h.idHistorialComputador = :idHistorialComputador"),
    @NamedQuery(name = "HistorialComputador.findByFechaHistorial", query = "SELECT h FROM HistorialComputador h WHERE h.fechaHistorial = :fechaHistorial"),
    @NamedQuery(name = "HistorialComputador.findByDiscoDuro", query = "SELECT h FROM HistorialComputador h WHERE h.discoDuro = :discoDuro"),
    @NamedQuery(name = "HistorialComputador.findByMonitor", query = "SELECT h FROM HistorialComputador h WHERE h.monitor = :monitor"),
    @NamedQuery(name = "HistorialComputador.findBySeccion", query = "SELECT h FROM HistorialComputador h WHERE h.seccion = :seccion"),
    @NamedQuery(name = "HistorialComputador.findByMemoriaRam", query = "SELECT h FROM HistorialComputador h WHERE h.memoriaRam = :memoriaRam"),
    @NamedQuery(name = "HistorialComputador.findByCosto", query = "SELECT h FROM HistorialComputador h WHERE h.costo = :costo"),
    @NamedQuery(name = "HistorialComputador.findByConsultaCompu", query = "SELECT h FROM HistorialComputador h WHERE h.idComputador =:idComputador"),
    @NamedQuery(name = "HistorialComputador.findBySistemaOperativo", query = "SELECT h FROM HistorialComputador h WHERE h.sistemaOperativo = :sistemaOperativo"),
    @NamedQuery(name = "HistorialComputador.findByIdLam", query = "SELECT h FROM HistorialComputador h WHERE h.idLam = :idLam"),
    @NamedQuery(name = "HistorialComputador.findByResponsable", query = "SELECT h FROM HistorialComputador h WHERE h.responsable = :responsable")})
public class HistorialComputador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historial_computador")
    private Integer idHistorialComputador;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_historial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHistorial;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "disco_duro")
    private Double discoDuro;
    @Size(max = 45)
    @Column(name = "monitor")
    private String monitor;
    @Size(max = 45)
    @Column(name = "seccion")
    private String seccion;
    @Column(name = "memoria_ram")
    private Double memoriaRam;
    @Column(name = "costo")
    private Integer costo;
    @Size(max = 45)
    @Column(name = "sistema_operativo")
    private String sistemaOperativo;
    @Size(max = 45)
    @Column(name = "id_lam")
    private String idLam;
    @Size(max = 45)
    @Column(name = "responsable")
    private String responsable;
    @JoinTable(name = "historial_computador_has_usuario", joinColumns = {
        @JoinColumn(name = "id_historial_computador", referencedColumnName = "id_historial_computador")}, inverseJoinColumns = {
        @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")})
    @ManyToMany
    private List<Usuario> usuarioList;
    @JoinColumn(name = "id_computador", referencedColumnName = "id_computador")
    @ManyToOne(optional = false)
    private Computador idComputador;
    @JoinColumn(name = "id_tipo_actualizacion", referencedColumnName = "id_tipo_actualizacion")
    @ManyToOne(optional = false)
    private TipoActualizacion idTipoActualizacion;

    public HistorialComputador() {
    }

    public HistorialComputador(Integer idHistorialComputador) {
        this.idHistorialComputador = idHistorialComputador;
    }

    public HistorialComputador(Integer idHistorialComputador, String descripcion, Date fechaHistorial) {
        this.idHistorialComputador = idHistorialComputador;
        this.descripcion = descripcion;
        this.fechaHistorial = fechaHistorial;
    }

    public Integer getIdHistorialComputador() {
        return idHistorialComputador;
    }

    public void setIdHistorialComputador(Integer idHistorialComputador) {
        this.idHistorialComputador = idHistorialComputador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaHistorial() {
        return fechaHistorial;
    }

    public void setFechaHistorial(Date fechaHistorial) {
        this.fechaHistorial = fechaHistorial;
    }

    public Double getDiscoDuro() {
        return discoDuro;
    }

    public void setDiscoDuro(Double discoDuro) {
        this.discoDuro = discoDuro;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Double getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(Double memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getIdLam() {
        return idLam;
    }

    public void setIdLam(String idLam) {
        this.idLam = idLam;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Computador getIdComputador() {
        return idComputador;
    }

    public void setIdComputador(Computador idComputador) {
        this.idComputador = idComputador;
    }

    public TipoActualizacion getIdTipoActualizacion() {
        return idTipoActualizacion;
    }

    public void setIdTipoActualizacion(TipoActualizacion idTipoActualizacion) {
        this.idTipoActualizacion = idTipoActualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorialComputador != null ? idHistorialComputador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialComputador)) {
            return false;
        }
        HistorialComputador other = (HistorialComputador) object;
        if ((this.idHistorialComputador == null && other.idHistorialComputador != null) || (this.idHistorialComputador != null && !this.idHistorialComputador.equals(other.idHistorialComputador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.HistorialComputador[ idHistorialComputador=" + idHistorialComputador + " ]";
    }
    
}
