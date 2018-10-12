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
 * @author junio
 */
@Entity
@Table(name = "item_componente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemComponente.findAll", query = "SELECT i FROM ItemComponente i")
    , @NamedQuery(name = "ItemComponente.findByIdItemComponente", query = "SELECT i FROM ItemComponente i WHERE i.idItemComponente = :idItemComponente")
    , @NamedQuery(name = "ItemComponente.findByNombre", query = "SELECT i FROM ItemComponente i WHERE i.nombre = :nombre")})
public class ItemComponente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_item_componente")
    private Integer idItemComponente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idItemComponente")
    private List<CronogramaComponente> cronogramaComponenteList;

    public ItemComponente() {
    }

    public ItemComponente(Integer idItemComponente) {
        this.idItemComponente = idItemComponente;
    }

    public ItemComponente(Integer idItemComponente, String nombre) {
        this.idItemComponente = idItemComponente;
        this.nombre = nombre;
    }

    public Integer getIdItemComponente() {
        return idItemComponente;
    }

    public void setIdItemComponente(Integer idItemComponente) {
        this.idItemComponente = idItemComponente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<CronogramaComponente> getCronogramaComponenteList() {
        return cronogramaComponenteList;
    }

    public void setCronogramaComponenteList(List<CronogramaComponente> cronogramaComponenteList) {
        this.cronogramaComponenteList = cronogramaComponenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItemComponente != null ? idItemComponente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemComponente)) {
            return false;
        }
        ItemComponente other = (ItemComponente) object;
        if ((this.idItemComponente == null && other.idItemComponente != null) || (this.idItemComponente != null && !this.idItemComponente.equals(other.idItemComponente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIdItemComponente()+" | "+getNombre();
    }
    
}
