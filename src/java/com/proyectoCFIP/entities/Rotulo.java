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
@Table(name = "rotulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rotulo.findAll", query = "SELECT r FROM Rotulo r"),
    @NamedQuery(name = "Rotulo.findByIdRotulo", query = "SELECT r FROM Rotulo r WHERE r.idRotulo = :idRotulo"),
    @NamedQuery(name = "Rotulo.findByColor", query = "SELECT r FROM Rotulo r WHERE r.color = :color")})
public class Rotulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rotulo")
    private Integer idRotulo;
    @Size(max = 45)
    @Column(name = "color")
    private String color;
    @Lob
    @Size(max = 16777215)
    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany( mappedBy = "idRotulo")
    private List<Libro> libroList;

    public Rotulo() {
    }

    public Rotulo(Integer idRotulo) {
        this.idRotulo = idRotulo;
    }

    public Integer getIdRotulo() {
        return idRotulo;
    }

    public void setIdRotulo(Integer idRotulo) {
        this.idRotulo = idRotulo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Libro> getLibroList() {
        return libroList;
    }

    public void setLibroList(List<Libro> libroList) {
        this.libroList = libroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRotulo != null ? idRotulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rotulo)) {
            return false;
        }
        Rotulo other = (Rotulo) object;
        if ((this.idRotulo == null && other.idRotulo != null) || (this.idRotulo != null && !this.idRotulo.equals(other.idRotulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIdRotulo() + "-" + getColor().toUpperCase();
    }

}
