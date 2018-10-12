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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author junio
 */
@Entity
@Table(name = "cronograma_componente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CronogramaComponente.findAll", query = "SELECT c FROM CronogramaComponente c")
    , @NamedQuery(name = "CronogramaComponente.findByIdComponente", query = "SELECT c FROM CronogramaComponente c WHERE c.idComponente = :idComponente")
    , @NamedQuery(name = "CronogramaComponente.findByFechaCompra", query = "SELECT c FROM CronogramaComponente c WHERE c.fechaCompra = :fechaCompra")
    , @NamedQuery(name = "CronogramaComponente.findByFechaCambio", query = "SELECT c FROM CronogramaComponente c WHERE c.fechaCambio IS NOT NULL")
    , @NamedQuery(name = "CronogramaComponente.findByComputador", query = "SELECT c FROM CronogramaComponente c WHERE c.idComputador = :idComputador")
    , @NamedQuery(name = "CronogramaComponente.findByCosto", query = "SELECT c FROM CronogramaComponente c WHERE c.costo = :costo")})
public class CronogramaComponente implements Serializable {

    @Size(max = 300)
    @Column(name = "descripcion")
    private String descripcion;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_componente")
    private Integer idComponente;
    @Column(name = "fecha_compra")
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    @Column(name = "fecha_cambio")
    @Temporal(TemporalType.DATE)
    private Date fechaCambio;
    @Column(name = "costo")
    private Integer costo;
    @JoinColumn(name = "id_computador", referencedColumnName = "id_computador")
    @ManyToOne(optional = false)
    private Computador idComputador;
    @JoinColumn(name = "id_item_componente", referencedColumnName = "id_item_componente")
    @ManyToOne(optional = false)
    private ItemComponente idItemComponente;

    public CronogramaComponente() {
    }

    public CronogramaComponente(Integer idComponente) {
        this.idComponente = idComponente;
    }

    public Integer getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Integer idComponente) {
        this.idComponente = idComponente;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Computador getIdComputador() {
        return idComputador;
    }

    public void setIdComputador(Computador idComputador) {
        this.idComputador = idComputador;
    }

    public ItemComponente getIdItemComponente() {
        return idItemComponente;
    }

    public void setIdItemComponente(ItemComponente idItemComponente) {
        this.idItemComponente = idItemComponente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComponente != null ? idComponente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CronogramaComponente)) {
            return false;
        }
        CronogramaComponente other = (CronogramaComponente) object;
        if ((this.idComponente == null && other.idComponente != null) || (this.idComponente != null && !this.idComponente.equals(other.idComponente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.CronogramaComponente[ idComponente=" + idComponente + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
