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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "subproceso_suit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubprocesoSuit.findAll", query = "SELECT s FROM SubprocesoSuit s"),
    @NamedQuery(name = "SubprocesoSuit.findByIdSubprocesoSuit", query = "SELECT s FROM SubprocesoSuit s WHERE s.idSubprocesoSuit = :idSubprocesoSuit"),
    @NamedQuery(name = "SubprocesoSuit.findByIdSuit", query = "SELECT s FROM SubprocesoSuit s WHERE s.idSuit = :idSuit"),
    @NamedQuery(name = "SubprocesoSuit.findByNombreSubprocesoSuit", query = "SELECT s FROM SubprocesoSuit s WHERE s.nombreSubprocesoSuit = :nombreSubprocesoSuit")})
public class SubprocesoSuit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_subproceso_suit")
    private Integer idSubprocesoSuit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "nombre_subproceso_suit")
    private String nombreSubprocesoSuit;
    @JoinColumn(name = "id_suit", referencedColumnName = "id_suit")
    @ManyToOne(optional = false)
    private Suit idSuit;

    public SubprocesoSuit() {
    }

    public SubprocesoSuit(Integer idSubprocesoSuit) {
        this.idSubprocesoSuit = idSubprocesoSuit;
    }

    public SubprocesoSuit(Integer idSubprocesoSuit, String nombreSubprocesoSuit) {
        this.idSubprocesoSuit = idSubprocesoSuit;
        this.nombreSubprocesoSuit = nombreSubprocesoSuit;
    }

    public Integer getIdSubprocesoSuit() {
        return idSubprocesoSuit;
    }

    public void setIdSubprocesoSuit(Integer idSubprocesoSuit) {
        this.idSubprocesoSuit = idSubprocesoSuit;
    }

    public String getNombreSubprocesoSuit() {
        return nombreSubprocesoSuit;
    }

    public void setNombreSubprocesoSuit(String nombreSubprocesoSuit) {
        this.nombreSubprocesoSuit = nombreSubprocesoSuit;
    }

    public Suit getIdSuit() {
        return idSuit;
    }

    public void setIdSuit(Suit idSuit) {
        this.idSuit = idSuit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubprocesoSuit != null ? idSubprocesoSuit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubprocesoSuit)) {
            return false;
        }
        SubprocesoSuit other = (SubprocesoSuit) object;
        if ((this.idSubprocesoSuit == null && other.idSubprocesoSuit != null) || (this.idSubprocesoSuit != null && !this.idSubprocesoSuit.equals(other.idSubprocesoSuit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombreSubprocesoSuit().toUpperCase();
    }

}
