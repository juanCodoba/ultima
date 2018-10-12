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
@Table(name = "licencia_paquete_office")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LicenciaPaqueteOffice.findAll", query = "SELECT l FROM LicenciaPaqueteOffice l"),
    @NamedQuery(name = "LicenciaPaqueteOffice.findByIdLicenciaPaqueteOffice", query = "SELECT l FROM LicenciaPaqueteOffice l WHERE l.idLicenciaPaqueteOffice = :idLicenciaPaqueteOffice"),
    @NamedQuery(name = "LicenciaPaqueteOffice.findByNombreLic", query = "SELECT l FROM LicenciaPaqueteOffice l WHERE l.nombreLic = :nombreLic"),
    @NamedQuery(name = "LicenciaPaqueteOffice.findByFechaInicioLic", query = "SELECT l FROM LicenciaPaqueteOffice l WHERE l.fechaInicioLic = :fechaInicioLic"),
    @NamedQuery(name = "LicenciaPaqueteOffice.findByFechaTerminoLic", query = "SELECT l FROM LicenciaPaqueteOffice l WHERE l.fechaTerminoLic = :fechaTerminoLic"),
    @NamedQuery(name = "LicenciaPaqueteOffice.findByLicSinFin", query = "SELECT l FROM LicenciaPaqueteOffice l WHERE l.licSinFin = :licSinFin"),
    @NamedQuery(name = "LicenciaPaqueteOffice.findByCodigoLic", query = "SELECT l FROM LicenciaPaqueteOffice l WHERE l.codigoLic = :codigoLic"),
    @NamedQuery(name = "LicenciaPaqueteOffice.findByClaveActivacion", query = "SELECT l FROM LicenciaPaqueteOffice l WHERE l.claveActivacion = :claveActivacion"),
    @NamedQuery(name = "LicenciaPaqueteOffice.findByPaquete", query = "SELECT l FROM LicenciaPaqueteOffice l WHERE l.idPaqueteOffice = :idPaqueteOffice"),
    @NamedQuery(name = "LicenciaPaqueteOffice.findByTipoActivacion", query = "SELECT l FROM LicenciaPaqueteOffice l WHERE l.tipoActivacion = :tipoActivacion")})
public class LicenciaPaqueteOffice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_licencia_paquete_office")
    private Integer idLicenciaPaqueteOffice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "nombre_lic")
    private String nombreLic;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio_lic")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioLic;
    @Column(name = "fecha_termino_lic")
    @Temporal(TemporalType.DATE)
    private Date fechaTerminoLic;
    @Column(name = "lic_sin_fin")
    private Boolean licSinFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigo_lic")
    private String codigoLic;
    @Size(max = 70)
    @Column(name = "clave_activacion")
    private String claveActivacion;
    @Size(max = 10)
    @Column(name = "tipo_activacion")
    private String tipoActivacion;
    @OneToMany(mappedBy = "idLicenciaPaqueteOffice")
    private List<Computador> computadorList;
    @JoinColumn(name = "id_paquete_office", referencedColumnName = "id_paquete_office")
    @ManyToOne(optional = false)
    private PaqueteOffice idPaqueteOffice;

    public LicenciaPaqueteOffice() {
    }

    public LicenciaPaqueteOffice(Integer idLicenciaPaqueteOffice) {
        this.idLicenciaPaqueteOffice = idLicenciaPaqueteOffice;
    }

    public LicenciaPaqueteOffice(Integer idLicenciaPaqueteOffice, String nombreLic, Date fechaInicioLic, String codigoLic) {
        this.idLicenciaPaqueteOffice = idLicenciaPaqueteOffice;
        this.nombreLic = nombreLic;
        this.fechaInicioLic = fechaInicioLic;
        this.codigoLic = codigoLic;
    }

    public Integer getIdLicenciaPaqueteOffice() {
        return idLicenciaPaqueteOffice;
    }

    public void setIdLicenciaPaqueteOffice(Integer idLicenciaPaqueteOffice) {
        this.idLicenciaPaqueteOffice = idLicenciaPaqueteOffice;
    }

    public String getNombreLic() {
        return nombreLic;
    }

    public void setNombreLic(String nombreLic) {
        this.nombreLic = nombreLic;
    }

    public Date getFechaInicioLic() {
        return fechaInicioLic;
    }

    public void setFechaInicioLic(Date fechaInicioLic) {
        this.fechaInicioLic = fechaInicioLic;
    }

    public Date getFechaTerminoLic() {
        return fechaTerminoLic;
    }

    public void setFechaTerminoLic(Date fechaTerminoLic) {
        this.fechaTerminoLic = fechaTerminoLic;
    }

    public Boolean getLicSinFin() {
        return licSinFin;
    }

    public void setLicSinFin(Boolean licSinFin) {
        this.licSinFin = licSinFin;
    }

    public String getCodigoLic() {
        return codigoLic;
    }

    public void setCodigoLic(String codigoLic) {
        this.codigoLic = codigoLic;
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
    public List<Computador> getComputadorList() {
        return computadorList;
    }

    public void setComputadorList(List<Computador> computadorList) {
        this.computadorList = computadorList;
    }

    public PaqueteOffice getIdPaqueteOffice() {
        return idPaqueteOffice;
    }

    public void setIdPaqueteOffice(PaqueteOffice idPaqueteOffice) {
        this.idPaqueteOffice = idPaqueteOffice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLicenciaPaqueteOffice != null ? idLicenciaPaqueteOffice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicenciaPaqueteOffice)) {
            return false;
        }
        LicenciaPaqueteOffice other = (LicenciaPaqueteOffice) object;
        if ((this.idLicenciaPaqueteOffice == null && other.idLicenciaPaqueteOffice != null) || (this.idLicenciaPaqueteOffice != null && !this.idLicenciaPaqueteOffice.equals(other.idLicenciaPaqueteOffice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getCodigoLic().toUpperCase()+"   /  "+getClaveActivacion().toUpperCase()+"   /   "+ getNombreLic().toUpperCase();
    }
    
}
