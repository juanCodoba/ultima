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
import javax.persistence.Lob;
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
@Table(name = "url_planilla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UrlPlanilla.findAll", query = "SELECT u FROM UrlPlanilla u"),
    @NamedQuery(name = "UrlPlanilla.findByIdUrlPlanilla", query = "SELECT u FROM UrlPlanilla u WHERE u.idUrlPlanilla = :idUrlPlanilla")})
public class UrlPlanilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_url_planilla")
    private Integer idUrlPlanilla;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "url")
    private String url;

    public UrlPlanilla() {
    }

    public UrlPlanilla(Integer idUrlPlanilla) {
        this.idUrlPlanilla = idUrlPlanilla;
    }

    public Integer getIdUrlPlanilla() {
        return idUrlPlanilla;
    }

    public void setIdUrlPlanilla(Integer idUrlPlanilla) {
        this.idUrlPlanilla = idUrlPlanilla;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUrlPlanilla != null ? idUrlPlanilla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UrlPlanilla)) {
            return false;
        }
        UrlPlanilla other = (UrlPlanilla) object;
        if ((this.idUrlPlanilla == null && other.idUrlPlanilla != null) || (this.idUrlPlanilla != null && !this.idUrlPlanilla.equals(other.idUrlPlanilla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.UrlPlanilla[ idUrlPlanilla=" + idUrlPlanilla + " ]";
    }
    
}
