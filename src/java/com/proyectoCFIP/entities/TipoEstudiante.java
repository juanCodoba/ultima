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
import javax.persistence.Lob;
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
@Table(name = "tipo_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEstudiante.findAll", query = "SELECT t FROM TipoEstudiante t"),
    @NamedQuery(name = "TipoEstudiante.findByIdTipoEstudiante", query = "SELECT t FROM TipoEstudiante t WHERE t.idTipoEstudiante = :idTipoEstudiante"),
    @NamedQuery(name = "TipoEstudiante.findByDescripcion", query = "SELECT t FROM TipoEstudiante t WHERE t.descripcion = :descripcion")})
public class TipoEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_tipo_estudiante")
    private Integer idTipoEstudiante;
    @Lob
    @Size(max = 95)
    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "idTipoEstudiante")
    private List<ReservaLibrosBiblioteca> ReservaLibrosBibliotecaList;
   


    public TipoEstudiante() {
    }

    public TipoEstudiante(Integer idTipoEstudiante) {
        this.idTipoEstudiante = idTipoEstudiante;
    }

    public Integer getIdTipoEstudiante() {
        return idTipoEstudiante;
    }

    public void setIdTipoEstudiante(Integer idTipoEstudiante) {
        this.idTipoEstudiante = idTipoEstudiante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEstudiante != null ? idTipoEstudiante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEstudiante)) {
            return false;
        }
        TipoEstudiante other = (TipoEstudiante) object;
        if ((this.idTipoEstudiante == null && other.idTipoEstudiante != null) || (this.idTipoEstudiante != null && !this.idTipoEstudiante.equals(other.idTipoEstudiante))) {
            return false;
        }
        return true;
    }

    public List<ReservaLibrosBiblioteca> getReservaLibrosBibliotecaList() {
        return ReservaLibrosBibliotecaList;
    }

    public void setReservaLibrosBibliotecaList(List<ReservaLibrosBiblioteca> ReservaLibrosBibliotecaList) {
        this.ReservaLibrosBibliotecaList = ReservaLibrosBibliotecaList;
    }

    @Override
    public String toString() {
        return getIdTipoEstudiante() + "-" + getDescripcion().toUpperCase();
    }


    
    

}
