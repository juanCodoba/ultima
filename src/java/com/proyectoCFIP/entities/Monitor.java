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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Junior Cabal
 */
@Entity
@Table(name = "monitor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monitor.findAll", query = "SELECT m FROM Monitor m"),
    @NamedQuery(name = "Monitor.findByIdMonitor", query = "SELECT m FROM Monitor m WHERE m.idMonitor = :idMonitor"),
    @NamedQuery(name = "Monitor.findByMarcaMonitor", query = "SELECT m FROM Monitor m WHERE m.marcaMonitor = :marcaMonitor"),
    @NamedQuery(name = "Monitor.findByPulgadasMonitor", query = "SELECT m FROM Monitor m WHERE m.pulgadasMonitor = :pulgadasMonitor")})
public class Monitor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_monitor")
    private Integer idMonitor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "marca_monitor")
    private String marcaMonitor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pulgadas_monitor")
    private int pulgadasMonitor;
    @OneToMany(mappedBy = "idMonitor")
    private List<Computador> computadorList;

    public Monitor() {
    }

    public Monitor(Integer idMonitor) {
        this.idMonitor = idMonitor;
    }

    public Monitor(Integer idMonitor, String marcaMonitor, int pulgadasMonitor) {
        this.idMonitor = idMonitor;
        this.marcaMonitor = marcaMonitor;
        this.pulgadasMonitor = pulgadasMonitor;
    }

    public Integer getIdMonitor() {
        return idMonitor;
    }

    public void setIdMonitor(Integer idMonitor) {
        this.idMonitor = idMonitor;
    }

    public String getMarcaMonitor() {
        return marcaMonitor;
    }

    public void setMarcaMonitor(String marcaMonitor) {
        this.marcaMonitor = marcaMonitor;
    }

    public int getPulgadasMonitor() {
        return pulgadasMonitor;
    }

    public void setPulgadasMonitor(int pulgadasMonitor) {
        this.pulgadasMonitor = pulgadasMonitor;
    }

    @XmlTransient
    public List<Computador> getComputadorList() {
        return computadorList;
    }

    public void setComputadorList(List<Computador> computadorList) {
        this.computadorList = computadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMonitor != null ? idMonitor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monitor)) {
            return false;
        }
        Monitor other = (Monitor) object;
        if ((this.idMonitor == null && other.idMonitor != null) || (this.idMonitor != null && !this.idMonitor.equals(other.idMonitor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getMarcaMonitor().toUpperCase()+"/"+getPulgadasMonitor()+'"';
    }

    
}
