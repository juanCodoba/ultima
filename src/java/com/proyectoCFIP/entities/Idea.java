/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "idea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Idea.findAll", query = "SELECT i FROM Idea i"),
    @NamedQuery(name = "Idea.findByIdIdea", query = "SELECT i FROM Idea i WHERE i.idIdea = :idIdea"),
    @NamedQuery(name = "Idea.findByDescripcion", query = "SELECT i FROM Idea i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "Idea.findByFecha", query = "SELECT i FROM Idea i WHERE i.fecha = :fecha"),
    @NamedQuery(name = "Idea.findByEstado", query = "SELECT i FROM Idea i WHERE i.estado = :estado")})
public class Idea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_idea")
    private Integer idIdea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Idea() {
    }

    public Idea(Integer idIdea) {
        this.idIdea = idIdea;
    }

    public Idea(Integer idIdea, String descripcion, Date fecha, String estado) {
        this.idIdea = idIdea;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Integer getIdIdea() {
        return idIdea;
    }

    public void setIdIdea(Integer idIdea) {
        this.idIdea = idIdea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIdea != null ? idIdea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idea)) {
            return false;
        }
        Idea other = (Idea) object;
        if ((this.idIdea == null && other.idIdea != null) || (this.idIdea != null && !this.idIdea.equals(other.idIdea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.Idea[ idIdea=" + idIdea + " ]";
    }
    
}
