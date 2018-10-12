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
@Table(name = "suit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suit.findAll", query = "SELECT s FROM Suit s"),
    @NamedQuery(name = "Suit.findByIdSuit", query = "SELECT s FROM Suit s WHERE s.idSuit = :idSuit"),
    @NamedQuery(name = "Suit.findByNombreSuit", query = "SELECT s FROM Suit s WHERE s.nombreSuit = :nombreSuit")})
public class Suit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_suit")
    private Integer idSuit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "nombre_suit")
    private String nombreSuit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSuit")
    private List<ManualSiesa> manualSiesaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSuit")
    private List<SubprocesoSuit> subprocesoSuitList;
    
    public Suit() {
    }

    public Suit(Integer idSuit) {
        this.idSuit = idSuit;
    }

    public Suit(Integer idSuit, String nombreSuit) {
        this.idSuit = idSuit;
        this.nombreSuit = nombreSuit;
    }

    public Integer getIdSuit() {
        return idSuit;
    }

    public void setIdSuit(Integer idSuit) {
        this.idSuit = idSuit;
    }

    public String getNombreSuit() {
        return nombreSuit;
    }

    public void setNombreSuit(String nombreSuit) {
        this.nombreSuit = nombreSuit;
    }

    public List<SubprocesoSuit> getSubprocesoSuitList() {
        return subprocesoSuitList;
    }

    public void setSubprocesoSuitList(List<SubprocesoSuit> subprocesoSuitList) {
        this.subprocesoSuitList = subprocesoSuitList;
    }

 
    
    

    @XmlTransient
    public List<ManualSiesa> getManualSiesaList() {
        return manualSiesaList;
    }

    public void setManualSiesaList(List<ManualSiesa> manualSiesaList) {
        this.manualSiesaList = manualSiesaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSuit != null ? idSuit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suit)) {
            return false;
        }
        Suit other = (Suit) object;
        if ((this.idSuit == null && other.idSuit != null) || (this.idSuit != null && !this.idSuit.equals(other.idSuit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombreSuit().toUpperCase();
    }
    
}
