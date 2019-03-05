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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "nivel_habilidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelHabilidad.findAll", query = "SELECT n FROM NivelHabilidad n"),
    @NamedQuery(name = "NivelHabilidad.findByIdNivelHabilidad", query = "SELECT n FROM NivelHabilidad n WHERE n.idNivelHabilidad = :idNivelHabilidad"),
    @NamedQuery(name = "NivelHabilidad.findByNivel", query = "SELECT n FROM NivelHabilidad n WHERE n.nivel = :nivel")})
public class NivelHabilidad implements Serializable {



    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nivel_habilidad")
    private Integer idNivelHabilidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nivel")
    private String nivel;
    
            @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNivelHabilidad")
    private List<Habilidad> habilidadList;

    
    
    public NivelHabilidad() {
    }

    public NivelHabilidad(Integer idNivelHabilidad) {
        this.idNivelHabilidad = idNivelHabilidad;
    }

    public NivelHabilidad(Integer idNivelHabilidad, String nivel) {
        this.idNivelHabilidad = idNivelHabilidad;
        this.nivel = nivel;
    }

    public Integer getIdNivelHabilidad() {
        return idNivelHabilidad;
    }

    public void setIdNivelHabilidad(Integer idNivelHabilidad) {
        this.idNivelHabilidad = idNivelHabilidad;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNivelHabilidad != null ? idNivelHabilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelHabilidad)) {
            return false;
        }
        NivelHabilidad other = (NivelHabilidad) object;
        if ((this.idNivelHabilidad == null && other.idNivelHabilidad != null) || (this.idNivelHabilidad != null && !this.idNivelHabilidad.equals(other.idNivelHabilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNivel().toUpperCase();
    }

    public List<Habilidad> getHabilidadList() {
        return habilidadList;
    }

    public void setHabilidadList(List<Habilidad> habilidadList) {
        this.habilidadList = habilidadList;
    }


    


    
}
