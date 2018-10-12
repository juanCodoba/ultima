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
import javax.persistence.Id;
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
@Table(name = "tipo_diagnostico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDiagnostico.findAll", query = "SELECT t FROM TipoDiagnostico t"),
    @NamedQuery(name = "TipoDiagnostico.findByIdTipoDiagnostico", query = "SELECT t FROM TipoDiagnostico t WHERE t.idTipoDiagnostico = :idTipoDiagnostico"),
    @NamedQuery(name = "TipoDiagnostico.findByNombre", query = "SELECT t FROM TipoDiagnostico t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoDiagnostico.findByCodigo", query = "SELECT t FROM TipoDiagnostico t WHERE t.codigo = :codigo")})
public class TipoDiagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_diagnostico")
    private Integer idTipoDiagnostico;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "codigo")
    private String codigo;

    public TipoDiagnostico() {
    }

    public TipoDiagnostico(Integer idTipoDiagnostico) {
        this.idTipoDiagnostico = idTipoDiagnostico;
    }

    public Integer getIdTipoDiagnostico() {
        return idTipoDiagnostico;
    }

    public void setIdTipoDiagnostico(Integer idTipoDiagnostico) {
        this.idTipoDiagnostico = idTipoDiagnostico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDiagnostico != null ? idTipoDiagnostico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDiagnostico)) {
            return false;
        }
        TipoDiagnostico other = (TipoDiagnostico) object;
        if ((this.idTipoDiagnostico == null && other.idTipoDiagnostico != null) || (this.idTipoDiagnostico != null && !this.idTipoDiagnostico.equals(other.idTipoDiagnostico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getCodigo()+" | "+getNombre();
    }
    
}
