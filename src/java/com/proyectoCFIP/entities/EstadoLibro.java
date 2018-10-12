/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "estado_libro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoLibro.findAll", query = "SELECT e FROM EstadoLibro e"),
    @NamedQuery(name = "EstadoLibro.findByIdEstadoLibro", query = "SELECT e FROM EstadoLibro e WHERE e.idEstadoLibro = :idEstadoLibro"),
    @NamedQuery(name = "EstadoLibro.findByNombreEstado", query = "SELECT e FROM EstadoLibro e WHERE e.nombreEstado = :nombreEstado")})
public class EstadoLibro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_estado_libro")
    private Integer idEstadoLibro;
    @Size(max = 45)
    @Column(name = "nombre_estado")
    private String nombreEstado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoLibro")
    private List<Libro> LibrosList;

    public EstadoLibro() {
    }

    public EstadoLibro(Integer idEstadoLibro) {
        this.idEstadoLibro = idEstadoLibro;
    }

    public EstadoLibro(Integer idEstadoLibro, String nombreEstado) {
        this.idEstadoLibro = idEstadoLibro;
        this.nombreEstado = nombreEstado;

    }

    public List<Libro> getLibrosList() {
        return LibrosList;
    }

    public void setLibrosList(List<Libro> LibrosList) {
        this.LibrosList = LibrosList;
    }
        

    public Integer getIdEstadoLibro() {
        return idEstadoLibro;
    }

    public void setIdEstadoLibro(Integer idEstadoLibro) {
        this.idEstadoLibro = idEstadoLibro;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }




    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoLibro != null ? idEstadoLibro.hashCode() : 0);
        return hash;
    }


    
    
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoLibro)) {
            return false;
        }
        EstadoLibro other = (EstadoLibro) object;
        if ((this.idEstadoLibro == null && other.idEstadoLibro != null) || (this.idEstadoLibro != null && !this.idEstadoLibro.equals(other.idEstadoLibro))) {
            return false;
        }
        return true;
    }

     @Override
    public String toString() {
        return getIdEstadoLibro()+ "-" + getNombreEstado().toUpperCase();
    }
}
