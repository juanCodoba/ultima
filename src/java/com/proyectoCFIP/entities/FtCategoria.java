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
@Table(name = "ft_categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtCategoria.findAll", query = "SELECT f FROM FtCategoria f"),
    @NamedQuery(name = "FtCategoria.findByIdFtCategoria", query = "SELECT f FROM FtCategoria f WHERE f.idFtCategoria = :idFtCategoria"),
    @NamedQuery(name = "FtCategoria.findByDescripcion", query = "SELECT f FROM FtCategoria f WHERE f.descripcion = :descripcion")})
public class FtCategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_ft_categoria")
    private Integer idFtCategoria;
    @Size(min=4 , max = 10)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany( mappedBy = "idFtCategoria")
    private List<FtFichaTecnica> ftFichaTecnicaList;

    public FtCategoria() {
    }

    public FtCategoria(Integer idFtCategoria) {
        this.idFtCategoria = idFtCategoria;
    }
    
    
    

    public FtCategoria(Integer idFtCategoria,String descripcion) {
        this.idFtCategoria = idFtCategoria;
        this.descripcion = descripcion;
    }

    public Integer getIdFtCategoria() {
        return idFtCategoria;
    }

    public void setIdFtCategoria(Integer idFtCategoria) {
        this.idFtCategoria = idFtCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<FtFichaTecnica> getFtFichaTecnicaList() {
        return ftFichaTecnicaList;
    }

    public void setFtFichaTecnicaList(List<FtFichaTecnica> ftFichaTecnicaList) {
        this.ftFichaTecnicaList = ftFichaTecnicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFtCategoria != null ? idFtCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtCategoria)) {
            return false;
        }
        FtCategoria other = (FtCategoria) object;
        if ((this.idFtCategoria == null && other.idFtCategoria != null) || (this.idFtCategoria != null && !this.idFtCategoria.equals(other.idFtCategoria))) {
            return false;
        }
        return true;
    }


    
        @Override
    public String toString() {
        return getDescripcion().toUpperCase();
    }

}
