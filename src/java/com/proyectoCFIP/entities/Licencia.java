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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "licencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Licencia.findAll", query = "SELECT l FROM Licencia l"),
    @NamedQuery(name = "Licencia.findByIdLicencia", query = "SELECT l FROM Licencia l WHERE l.idLicencia = :idLicencia"),
    @NamedQuery(name = "Licencia.findByNombreLicencia", query = "SELECT l FROM Licencia l WHERE l.nombreLicencia = :nombreLicencia"),
    @NamedQuery(name = "Licencia.findByTipoLicencia", query = "SELECT l FROM Licencia l WHERE l.idTipoLicencia = :idTipoLicencia"),
    @NamedQuery(name = "Licencia.findByTipoLicenciaApp", query = "SELECT l FROM Licencia l WHERE l.idTipoLicencia.idTipoLicencia =2 OR l.idTipoLicencia.idTipoLicencia =3"),
    @NamedQuery(name = "Licencia.findByFechaInicioLicencia", query = "SELECT l FROM Licencia l WHERE l.fechaInicioLicencia = :fechaInicioLicencia"),
    @NamedQuery(name = "Licencia.findByFechaTerminoLicencia", query = "SELECT l FROM Licencia l WHERE l.fechaTerminoLicencia = :fechaTerminoLicencia"),
    @NamedQuery(name = "Licencia.findByLicenciaSinFin", query = "SELECT l FROM Licencia l WHERE l.licenciaSinFin = :licenciaSinFin"),
    @NamedQuery(name = "Licencia.findBySistemaOperativo", query = "SELECT l FROM Licencia l WHERE l.idSistemaOperativo = :idSistemaOperativo"),
    @NamedQuery(name = "Licencia.findByCodigoLicencia", query = "SELECT l FROM Licencia l WHERE l.codigoLicencia = :codigoLicencia"),
    @NamedQuery(name = "Licencia.findByClaveActivacion", query = "SELECT l FROM Licencia l WHERE l.claveActivacion = :claveActivacion"),
    @NamedQuery(name = "Licencia.findByTipoActivacion", query = "SELECT l FROM Licencia l WHERE l.tipoActivacion = :tipoActivacion")})
public class Licencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_licencia")
    private Integer idLicencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "nombre_licencia")
    private String nombreLicencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio_licencia")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioLicencia;
    @Column(name = "fecha_termino_licencia")
    @Temporal(TemporalType.DATE)
    private Date fechaTerminoLicencia;
    @Column(name = "licencia_sin_fin")
    private Boolean licenciaSinFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigo_licencia")
    private String codigoLicencia;
    @Size(max = 70)
    @Column(name = "clave_activacion")
    private String claveActivacion;
    @Size(max = 10)
    @Column(name = "tipo_activacion")
    private String tipoActivacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLicencia")
    private List<Aplicacion> aplicacionList;
    @OneToMany(mappedBy = "idLicencia")
    private List<Computador> computadorList;
    @JoinColumn(name = "id_fabricante_licencia", referencedColumnName = "id_fabricante_licencia")
    @ManyToOne(optional = false)
    private FabricanteLicencia idFabricanteLicencia;
    @JoinColumn(name = "id_modalidades_licencia", referencedColumnName = "id_modalidades_licencia")
    @ManyToOne(optional = false)
    private ModalidadesLicencia idModalidadesLicencia;
    @JoinColumn(name = "id_tipo_licencia", referencedColumnName = "id_tipo_licencia")
    @ManyToOne(optional = false)
    private TipoLicencia idTipoLicencia;
    @JoinColumn(name = "id_sistema_operativo", referencedColumnName = "id_sistema_operativo")
    @ManyToOne
    private SistemaOperativo idSistemaOperativo;
    public Licencia() {
    }

    public Licencia(Integer idLicencia) {
        this.idLicencia = idLicencia;
    }

    public Licencia(Integer idLicencia, String nombreLicencia, Date fechaInicioLicencia, String codigoLicencia) {
        this.idLicencia = idLicencia;
        this.nombreLicencia = nombreLicencia;
        this.fechaInicioLicencia = fechaInicioLicencia;
        this.codigoLicencia = codigoLicencia;
    }

    public Integer getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(Integer idLicencia) {
        this.idLicencia = idLicencia;
    }

    public String getNombreLicencia() {
        return nombreLicencia;
    }

    public void setNombreLicencia(String nombreLicencia) {
        this.nombreLicencia = nombreLicencia;
    }

    public Date getFechaInicioLicencia() {
        return fechaInicioLicencia;
    }

    public void setFechaInicioLicencia(Date fechaInicioLicencia) {
        this.fechaInicioLicencia = fechaInicioLicencia;
    }

    public Date getFechaTerminoLicencia() {
        return fechaTerminoLicencia;
    }

    public void setFechaTerminoLicencia(Date fechaTerminoLicencia) {
        this.fechaTerminoLicencia = fechaTerminoLicencia;
    }

    public Boolean getLicenciaSinFin() {
        return licenciaSinFin;
    }

    public void setLicenciaSinFin(Boolean licenciaSinFin) {
        this.licenciaSinFin = licenciaSinFin;
    }

    public String getCodigoLicencia() {
        return codigoLicencia;
    }

    public void setCodigoLicencia(String codigoLicencia) {
        this.codigoLicencia = codigoLicencia;
    }

    public String getClaveActivacion() {
        return claveActivacion;
    }

    public void setClaveActivacion(String claveActivacion) {
        this.claveActivacion = claveActivacion;
    }

    public String getTipoActivacion() {
        return tipoActivacion;
    }

    public void setTipoActivacion(String tipoActivacion) {
        this.tipoActivacion = tipoActivacion;
    }

    @XmlTransient
    public List<Aplicacion> getAplicacionList() {
        return aplicacionList;
    }

    public void setAplicacionList(List<Aplicacion> aplicacionList) {
        this.aplicacionList = aplicacionList;
    }

    @XmlTransient
    public List<Computador> getComputadorList() {
        return computadorList;
    }

    public void setComputadorList(List<Computador> computadorList) {
        this.computadorList = computadorList;
    }

    public FabricanteLicencia getIdFabricanteLicencia() {
        return idFabricanteLicencia;
    }

    public void setIdFabricanteLicencia(FabricanteLicencia idFabricanteLicencia) {
        this.idFabricanteLicencia = idFabricanteLicencia;
    }

    public ModalidadesLicencia getIdModalidadesLicencia() {
        return idModalidadesLicencia;
    }

    public void setIdModalidadesLicencia(ModalidadesLicencia idModalidadesLicencia) {
        this.idModalidadesLicencia = idModalidadesLicencia;
    }

    public TipoLicencia getIdTipoLicencia() {
        return idTipoLicencia;
    }

    public void setIdTipoLicencia(TipoLicencia idTipoLicencia) {
        this.idTipoLicencia = idTipoLicencia;
    }
    
    public SistemaOperativo getIdSistemaOperativo() {
        return idSistemaOperativo;
    }

    public void setIdSistemaOperativo(SistemaOperativo idSistemaOperativo) {
        this.idSistemaOperativo = idSistemaOperativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLicencia != null ? idLicencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Licencia)) {
            return false;
        }
        Licencia other = (Licencia) object;
        if ((this.idLicencia == null && other.idLicencia != null) || (this.idLicencia != null && !this.idLicencia.equals(other.idLicencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getCodigoLicencia().toUpperCase()+"   /  "+getClaveActivacion().toUpperCase()+"   /   "+ getNombreLicencia().toUpperCase()+"    /    "+getIdFabricanteLicencia().getNombreLicencia().toUpperCase();
    }
    
}
