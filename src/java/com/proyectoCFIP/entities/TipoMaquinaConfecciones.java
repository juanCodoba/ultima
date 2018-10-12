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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "tipo_maquina_confecciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMaquinaConfecciones.findAll", query = "SELECT t FROM TipoMaquinaConfecciones t"),
    @NamedQuery(name = "TipoMaquinaConfecciones.findByIdTipoMaquinaConfecciones", query = "SELECT t FROM TipoMaquinaConfecciones t WHERE t.idTipoMaquinaConfecciones = :idTipoMaquinaConfecciones"),
    @NamedQuery(name = "TipoMaquinaConfecciones.findByNombre", query = "SELECT t FROM TipoMaquinaConfecciones t WHERE t.nombre = :nombre")})
public class TipoMaquinaConfecciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_maquina_confecciones")
    private Integer idTipoMaquinaConfecciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoMaquinaConfecciones")
    private List<MaquinaConfecciones> maquinaConfeccionesList;

    public TipoMaquinaConfecciones() {
    }

    public TipoMaquinaConfecciones(Integer idTipoMaquinaConfecciones) {
        this.idTipoMaquinaConfecciones = idTipoMaquinaConfecciones;
    }

    public TipoMaquinaConfecciones(Integer idTipoMaquinaConfecciones, String nombre) {
        this.idTipoMaquinaConfecciones = idTipoMaquinaConfecciones;
        this.nombre = nombre;
    }

    public Integer getIdTipoMaquinaConfecciones() {
        return idTipoMaquinaConfecciones;
    }

    public void setIdTipoMaquinaConfecciones(Integer idTipoMaquinaConfecciones) {
        this.idTipoMaquinaConfecciones = idTipoMaquinaConfecciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<MaquinaConfecciones> getMaquinaConfeccionesList() {
        return maquinaConfeccionesList;
    }

    public void setMaquinaConfeccionesList(List<MaquinaConfecciones> maquinaConfeccionesList) {
        this.maquinaConfeccionesList = maquinaConfeccionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMaquinaConfecciones != null ? idTipoMaquinaConfecciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMaquinaConfecciones)) {
            return false;
        }
        TipoMaquinaConfecciones other = (TipoMaquinaConfecciones) object;
        if ((this.idTipoMaquinaConfecciones == null && other.idTipoMaquinaConfecciones != null) || (this.idTipoMaquinaConfecciones != null && !this.idTipoMaquinaConfecciones.equals(other.idTipoMaquinaConfecciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre().toUpperCase();
    }
    
}
