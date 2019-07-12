/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "novedad_facturacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NovedadFacturacion.findAll", query = "SELECT n FROM NovedadFacturacion n"),
    @NamedQuery(name = "NovedadFacturacion.findByIdNovedadFacturacion", query = "SELECT n FROM NovedadFacturacion n WHERE n.idNovedadFacturacion = :idNovedadFacturacion"),
    @NamedQuery(name = "NovedadFacturacion.findByFcrmRniff", query = "SELECT n FROM NovedadFacturacion n WHERE n.fcrmRniff = :fcrmRniff"),
    @NamedQuery(name = "NovedadFacturacion.findByFcrmNrniff", query = "SELECT n FROM NovedadFacturacion n WHERE n.fcrmNrniff = :fcrmNrniff")})
public class NovedadFacturacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_novedad_facturacion")
    private Integer idNovedadFacturacion;
    @Column(name = "fcrm_rniff")
    private BigDecimal fcrmRniff;
    @Column(name = "fcrm_nrniff")
    private BigDecimal fcrmNrniff;

    @Column(name = "total_confecciones")
    private BigDecimal totalConfecciones;

    @Column(name = "real_confecciones")
    private BigDecimal realConfecciones;

    public NovedadFacturacion() {
    }

    public NovedadFacturacion(Integer idNovedadFacturacion) {
        this.idNovedadFacturacion = idNovedadFacturacion;
    }

    public Integer getIdNovedadFacturacion() {
        return idNovedadFacturacion;
    }

    public void setIdNovedadFacturacion(Integer idNovedadFacturacion) {
        this.idNovedadFacturacion = idNovedadFacturacion;
    }

    public BigDecimal getFcrmRniff() {
        return fcrmRniff;
    }

    public void setFcrmRniff(BigDecimal fcrmRniff) {
        this.fcrmRniff = fcrmRniff;
    }

    public BigDecimal getFcrmNrniff() {
        return fcrmNrniff;
    }

    public void setFcrmNrniff(BigDecimal fcrmNrniff) {
        this.fcrmNrniff = fcrmNrniff;
    }

    public BigDecimal getTotalConfecciones() {
        return totalConfecciones;
    }

    public void setTotalConfecciones(BigDecimal totalConfecciones) {
        this.totalConfecciones = totalConfecciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNovedadFacturacion != null ? idNovedadFacturacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NovedadFacturacion)) {
            return false;
        }
        NovedadFacturacion other = (NovedadFacturacion) object;
        if ((this.idNovedadFacturacion == null && other.idNovedadFacturacion != null) || (this.idNovedadFacturacion != null && !this.idNovedadFacturacion.equals(other.idNovedadFacturacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.NovedadFacturacion[ idNovedadFacturacion=" + idNovedadFacturacion + " ]";
    }

    public BigDecimal getRealConfecciones() {
        return realConfecciones;
    }

    public void setRealConfecciones(BigDecimal realConfecciones) {
        this.realConfecciones = realConfecciones;
    }
    

}
