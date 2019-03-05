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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "profesiograma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesiograma.findAll", query = "SELECT p FROM Profesiograma p"),
    @NamedQuery(name = "Profesiograma.findByIdProfesiograma", query = "SELECT p FROM Profesiograma p WHERE p.idProfesiograma = :idProfesiograma"),
    @NamedQuery(name = "Profesiograma.findByDescripcion", query = "SELECT p FROM Profesiograma p WHERE p.descripcion = :descripcion")
})
public class Profesiograma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_profesiograma")
    private Integer idProfesiograma;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_tipo_profesiograma", referencedColumnName = "id_tipo_profesiograma")
    @ManyToOne(optional = true)
    private TipoProfesiograma idTipoProfesiograma;

    @Size(max = 1000000)
    @Column(name = "url_profesiograma")
    private String urlProfesiograma;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProfesiograma")
    private List<Cargos> cargosList;

    public Profesiograma() {
    }

    public Profesiograma(Integer idProfesiograma) {
        this.idProfesiograma = idProfesiograma;
    }

    public Profesiograma(Integer idProfesiograma, TipoProfesiograma idTipoProfesiograma) {
        this.idProfesiograma = idProfesiograma;
        this.idTipoProfesiograma = idTipoProfesiograma;
    }

    public Integer getIdProfesiograma() {
        return idProfesiograma;
    }

    public void setIdProfesiograma(Integer idProfesiograma) {
        this.idProfesiograma = idProfesiograma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoProfesiograma getIdTipoProfesiograma() {
        return idTipoProfesiograma;
    }

    public void setIdTipoProfesiograma(TipoProfesiograma idTipoProfesiograma) {
        this.idTipoProfesiograma = idTipoProfesiograma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfesiograma != null ? idProfesiograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesiograma)) {
            return false;
        }
        Profesiograma other = (Profesiograma) object;
        if ((this.idProfesiograma == null && other.idProfesiograma != null) || (this.idProfesiograma != null && !this.idProfesiograma.equals(other.idProfesiograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescripcion();
    }

    public List<Cargos> getCargosList() {
        return cargosList;
    }

    public void setCargosList(List<Cargos> cargosList) {
        this.cargosList = cargosList;
    }

    public String getUrlProfesiograma() {
        return urlProfesiograma;
    }

    public void setUrlProfesiograma(String urlProfesiograma) {
        this.urlProfesiograma = urlProfesiograma;
    }

    
    
}
