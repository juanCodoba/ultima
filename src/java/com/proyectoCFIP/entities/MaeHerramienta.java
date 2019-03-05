/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "mae_herramienta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeHerramienta.findAll", query = "SELECT m FROM MaeHerramienta m"),
    @NamedQuery(name = "MaeHerramienta.findByIdMaeHerramienta", query = "SELECT m FROM MaeHerramienta m WHERE m.idMaeHerramienta = :idMaeHerramienta"),
    @NamedQuery(name = "MaeHerramienta.findByNombre", query = "SELECT m FROM MaeHerramienta m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MaeHerramienta.findByLlamado", query = "SELECT m FROM MaeHerramienta m WHERE m.llamado = :llamado"),
    @NamedQuery(name = "MaeHerramienta.findByEstado", query = "SELECT m FROM MaeHerramienta m WHERE m.estado = :estado"),
    @NamedQuery(name = "MaeHerramienta.findByIdMaeTipoHerramienta", query = "SELECT m FROM MaeHerramienta m WHERE m.idMaeTipoHerramienta = :idMaeTipoHerramienta")})
public class MaeHerramienta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mae_herramienta")
    private Integer idMaeHerramienta;
    @Size(max = 505)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "llamado")
    private String llamado;
    @Column(name = "estado")
    private Boolean estado;

    @ManyToMany(mappedBy = "maeHerramientaList")
    private List<Actividad> actividadList;

    @JoinColumn(name = "id_mae_tipo_herramienta", referencedColumnName = "id_mae_tipo_herramienta")
    @ManyToOne(optional = false)
    private MaeTipoHerramienta idMaeTipoHerramienta;

    public MaeHerramienta() {
    }

    public MaeHerramienta(Integer idMaeHerramienta) {
        this.idMaeHerramienta = idMaeHerramienta;
    }

    public MaeHerramienta(Integer idMaeHerramienta, int idMaeTipoHerramienta) {
        this.idMaeHerramienta = idMaeHerramienta;
    }

    public Integer getIdMaeHerramienta() {
        return idMaeHerramienta;
    }

    public void setIdMaeHerramienta(Integer idMaeHerramienta) {
        this.idMaeHerramienta = idMaeHerramienta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLlamado() {
        return llamado;
    }

    public void setLlamado(String llamado) {
        this.llamado = llamado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaeHerramienta != null ? idMaeHerramienta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeHerramienta)) {
            return false;
        }
        MaeHerramienta other = (MaeHerramienta) object;
        if ((this.idMaeHerramienta == null && other.idMaeHerramienta != null) || (this.idMaeHerramienta != null && !this.idMaeHerramienta.equals(other.idMaeHerramienta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIdMaeTipoHerramienta().getNombre()+ " - " + getNombre();
    }

    public List<Actividad> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }

    public MaeTipoHerramienta getIdMaeTipoHerramienta() {
        return idMaeTipoHerramienta;
    }

    public void setIdMaeTipoHerramienta(MaeTipoHerramienta idMaeTipoHerramienta) {
        this.idMaeTipoHerramienta = idMaeTipoHerramienta;
    }
    
    

}
