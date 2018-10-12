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
import javax.persistence.Lob;
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
@Table(name = "computador_inactivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComputadorInactivo.findAll", query = "SELECT c FROM ComputadorInactivo c"),
    @NamedQuery(name = "ComputadorInactivo.findByIdComputadorInactivo", query = "SELECT c FROM ComputadorInactivo c WHERE c.idComputadorInactivo = :idComputadorInactivo")})
public class ComputadorInactivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_computador_inactivo")
    private Integer idComputadorInactivo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_computador", referencedColumnName = "id_computador")
    @ManyToOne(optional = false)
    private Computador idComputador;

    public ComputadorInactivo() {
    }

    public ComputadorInactivo(Integer idComputadorInactivo) {
        this.idComputadorInactivo = idComputadorInactivo;
    }

    public ComputadorInactivo(Integer idComputadorInactivo, String descripcion) {
        this.idComputadorInactivo = idComputadorInactivo;
        this.descripcion = descripcion;
    }

    public Integer getIdComputadorInactivo() {
        return idComputadorInactivo;
    }

    public void setIdComputadorInactivo(Integer idComputadorInactivo) {
        this.idComputadorInactivo = idComputadorInactivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Computador getIdComputador() {
        return idComputador;
    }

    public void setIdComputador(Computador idComputador) {
        this.idComputador = idComputador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComputadorInactivo != null ? idComputadorInactivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComputadorInactivo)) {
            return false;
        }
        ComputadorInactivo other = (ComputadorInactivo) object;
        if ((this.idComputadorInactivo == null && other.idComputadorInactivo != null) || (this.idComputadorInactivo != null && !this.idComputadorInactivo.equals(other.idComputadorInactivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.ComputadorInactivo[ idComputadorInactivo=" + idComputadorInactivo + " ]";
    }
    
}
