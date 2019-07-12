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
@Table(name = "ft_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FtCliente.findAll", query = "SELECT f FROM FtCliente f"),
    @NamedQuery(name = "FtCliente.findByIdFtCliente", query = "SELECT f FROM FtCliente f WHERE f.idFtCliente = :idFtCliente"),
    @NamedQuery(name = "FtCliente.findByNombre", query = "SELECT f FROM FtCliente f WHERE f.nombre = :nombre")})
public class FtCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_ft_cliente")
    private Integer idFtCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idFtCliente")
    private List<FtFichaTecnica> ftFichaTecnicaList;

    @OneToMany(mappedBy = "idFtCliente")
    private List<Facturado> facturadoList;

    @OneToMany(mappedBy = "idFtCliente")
    private List<Novedad> novedadList;

    public FtCliente() {
    }

    public FtCliente(Integer idFtCliente) {
        this.idFtCliente = idFtCliente;
    }

    public FtCliente(Integer idFtCliente, String nombre) {
        this.idFtCliente = idFtCliente;
        this.nombre = nombre;
    }

    public Integer getIdFtCliente() {
        return idFtCliente;
    }

    public void setIdFtCliente(Integer idFtCliente) {
        this.idFtCliente = idFtCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<FtFichaTecnica> getFtFichaTecnicaList() {
        return ftFichaTecnicaList;
    }

    public void setFtFichaTecnicaList(List<FtFichaTecnica> ftFichaTecnicaList) {
        this.ftFichaTecnicaList = ftFichaTecnicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFtCliente != null ? idFtCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FtCliente)) {
            return false;
        }
        FtCliente other = (FtCliente) object;
        if ((this.idFtCliente == null && other.idFtCliente != null) || (this.idFtCliente != null && !this.idFtCliente.equals(other.idFtCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre().toUpperCase();
    }

    public List<Facturado> getFacturadoList() {
        return facturadoList;
    }

    public void setFacturadoList(List<Facturado> facturadoList) {
        this.facturadoList = facturadoList;
    }
    @XmlTransient
    public List<Novedad> getNovedadList() {
        return novedadList;
    }

    public void setNovedadList(List<Novedad> novedadList) {
        this.novedadList = novedadList;
    }

    
}
