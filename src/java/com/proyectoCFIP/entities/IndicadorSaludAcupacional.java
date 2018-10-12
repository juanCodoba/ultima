/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "indicador_salud_acupacional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IndicadorSaludAcupacional.findAll", query = "SELECT i FROM IndicadorSaludAcupacional i"),
    @NamedQuery(name = "IndicadorSaludAcupacional.findByIdIndicadorSaludOcu", query = "SELECT i FROM IndicadorSaludAcupacional i WHERE i.idIndicadorSaludOcu = :idIndicadorSaludOcu"),
    @NamedQuery(name = "IndicadorSaludAcupacional.findByNombre", query = "SELECT i FROM IndicadorSaludAcupacional i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "IndicadorSaludAcupacional.findByFecha", query = "SELECT i FROM IndicadorSaludAcupacional i WHERE i.fecha = :fecha")})
public class IndicadorSaludAcupacional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_indicador_salud_ocu")
    private Integer idIndicadorSaludOcu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "indicador")
    private byte[] indicador;

    public IndicadorSaludAcupacional() {
    }

    public IndicadorSaludAcupacional(Integer idIndicadorSaludOcu) {
        this.idIndicadorSaludOcu = idIndicadorSaludOcu;
    }

    public IndicadorSaludAcupacional(Integer idIndicadorSaludOcu, String nombre, byte[] indicador) {
        this.idIndicadorSaludOcu = idIndicadorSaludOcu;
        this.nombre = nombre;
        this.indicador = indicador;
    }

    public Integer getIdIndicadorSaludOcu() {
        return idIndicadorSaludOcu;
    }

    public void setIdIndicadorSaludOcu(Integer idIndicadorSaludOcu) {
        this.idIndicadorSaludOcu = idIndicadorSaludOcu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

      public DefaultStreamedContent getIndicador() {
        try {
             InputStream is= new ByteArrayInputStream((byte[])indicador);
             return new DefaultStreamedContent(is,".pdf",getNombre().toUpperCase()+".pdf");
         } catch (Exception e) {
             return null;
         }
    }

    public void setIndicador(byte[] indicador) {
        this.indicador = indicador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIndicadorSaludOcu != null ? idIndicadorSaludOcu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndicadorSaludAcupacional)) {
            return false;
        }
        IndicadorSaludAcupacional other = (IndicadorSaludAcupacional) object;
        if ((this.idIndicadorSaludOcu == null && other.idIndicadorSaludOcu != null) || (this.idIndicadorSaludOcu != null && !this.idIndicadorSaludOcu.equals(other.idIndicadorSaludOcu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.IndicadorSaludAcupacional[ idIndicadorSaludOcu=" + idIndicadorSaludOcu + " ]";
    }
    
}
