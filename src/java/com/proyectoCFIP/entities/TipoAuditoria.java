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

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "tipo_auditoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAuditoria.findAll", query = "SELECT t FROM TipoAuditoria t"),
    @NamedQuery(name = "TipoAuditoria.findByIdTipoAuditoria", query = "SELECT t FROM TipoAuditoria t WHERE t.idTipoAuditoria = :idTipoAuditoria"),
    @NamedQuery(name = "TipoAuditoria.findByNombre", query = "SELECT t FROM TipoAuditoria t WHERE t.nombre = :nombre")})
public class TipoAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_tipo_auditoria")
    private Integer idTipoAuditoria;
    @Size(max = 300)
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "idTipoAuditoria")
    private List<AuPlanAuditoria> AuPlanAuditoriaList;

    public TipoAuditoria() {
    }

    public TipoAuditoria(Integer idTipoAuditoria) {
        this.idTipoAuditoria = idTipoAuditoria;
    }

    public Integer getIdTipoAuditoria() {
        return idTipoAuditoria;
    }

    public void setIdTipoAuditoria(Integer idTipoAuditoria) {
        this.idTipoAuditoria = idTipoAuditoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoAuditoria != null ? idTipoAuditoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAuditoria)) {
            return false;
        }
        TipoAuditoria other = (TipoAuditoria) object;
        if ((this.idTipoAuditoria == null && other.idTipoAuditoria != null) || (this.idTipoAuditoria != null && !this.idTipoAuditoria.equals(other.idTipoAuditoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIdTipoAuditoria()+"-"+getNombre().toUpperCase();
    }

    public List<AuPlanAuditoria> getAuPlanAuditoriaList() {
        return AuPlanAuditoriaList;
    }

    public void setAuPlanAuditoriaList(List<AuPlanAuditoria> AuPlanAuditoriaList) {
        this.AuPlanAuditoriaList = AuPlanAuditoriaList;
    }
    
    

}
