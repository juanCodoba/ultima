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
@Table(name = "tipo_ficha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoFicha.findAll", query = "SELECT t FROM TipoFicha t"),
    @NamedQuery(name = "TipoFicha.findByIdTipoFicha", query = "SELECT t FROM TipoFicha t WHERE t.idTipoFicha = :idTipoFicha"),
    @NamedQuery(name = "TipoFicha.findByNombreFicha", query = "SELECT t FROM TipoFicha t WHERE t.nombreFicha = :nombreFicha")})
public class TipoFicha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_tipo_ficha")
    private Integer idTipoFicha;
    @Size(max = 45)
    @Column(name = "nombre_ficha")
    private String nombreFicha;
    
    @OneToMany( mappedBy = "idTipoFicha")
    private List<FtFichaTecnica> ftFichaTecnicaList;

    public TipoFicha() {
    }

    public TipoFicha(Integer idTipoFicha) {
        this.idTipoFicha = idTipoFicha;
    }

    public TipoFicha(Integer idTipoFicha, String nombreFicha, List<FtFichaTecnica> ftFichaTecnicaList) {
        this.idTipoFicha = idTipoFicha;
        this.nombreFicha = nombreFicha;
        this.ftFichaTecnicaList = ftFichaTecnicaList;
    }
    
    

    public Integer getIdTipoFicha() {
        return idTipoFicha;
    }

    public void setIdTipoFicha(Integer idTipoFicha) {
        this.idTipoFicha = idTipoFicha;
    }

    public String getNombreFicha() {
        return nombreFicha;
    }

    public void setNombreFicha(String nombreFicha) {
        this.nombreFicha = nombreFicha;
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
        hash += (idTipoFicha != null ? idTipoFicha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoFicha)) {
            return false;
        }
        TipoFicha other = (TipoFicha) object;
        if ((this.idTipoFicha == null && other.idTipoFicha != null) || (this.idTipoFicha != null && !this.idTipoFicha.equals(other.idTipoFicha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIdTipoFicha()+"-"+getNombreFicha().toUpperCase();
    }
    
}
