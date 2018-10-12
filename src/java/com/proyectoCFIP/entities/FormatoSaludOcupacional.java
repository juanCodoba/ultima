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
@Table(name = "formato_salud_ocupacional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormatoSaludOcupacional.findAll", query = "SELECT f FROM FormatoSaludOcupacional f"),
    @NamedQuery(name = "FormatoSaludOcupacional.findByIdFormatoSaludOcu", query = "SELECT f FROM FormatoSaludOcupacional f WHERE f.idFormatoSaludOcu = :idFormatoSaludOcu"),
    @NamedQuery(name = "FormatoSaludOcupacional.findByNombre", query = "SELECT f FROM FormatoSaludOcupacional f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "FormatoSaludOcupacional.findByFecha", query = "SELECT f FROM FormatoSaludOcupacional f WHERE f.fecha = :fecha"),
    @NamedQuery(name = "FormatoSaludOcupacional.findByVersion", query = "SELECT f FROM FormatoSaludOcupacional f WHERE f.version = :version")})
public class FormatoSaludOcupacional implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "formato")
    private byte[] formato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo")
    private String codigo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_formato_salud_ocu")
    private Integer idFormatoSaludOcu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 85)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "version")
    private Integer version;

    public FormatoSaludOcupacional() {
    }

    public FormatoSaludOcupacional(Integer idFormatoSaludOcu) {
        this.idFormatoSaludOcu = idFormatoSaludOcu;
    }

    public FormatoSaludOcupacional(Integer idFormatoSaludOcu, String nombre, byte[] formato) {
        this.idFormatoSaludOcu = idFormatoSaludOcu;
        this.nombre = nombre;
        this.formato = formato;
    }

    public Integer getIdFormatoSaludOcu() {
        return idFormatoSaludOcu;
    }

    public void setIdFormatoSaludOcu(Integer idFormatoSaludOcu) {
        this.idFormatoSaludOcu = idFormatoSaludOcu;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormatoSaludOcu != null ? idFormatoSaludOcu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormatoSaludOcupacional)) {
            return false;
        }
        FormatoSaludOcupacional other = (FormatoSaludOcupacional) object;
        if ((this.idFormatoSaludOcu == null && other.idFormatoSaludOcu != null) || (this.idFormatoSaludOcu != null && !this.idFormatoSaludOcu.equals(other.idFormatoSaludOcu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.FormatoSaludOcupacional[ idFormatoSaludOcu=" + idFormatoSaludOcu + " ]";
    }

    public DefaultStreamedContent getFormato() {
        try {
             InputStream is= new ByteArrayInputStream((byte[])formato);
             return new DefaultStreamedContent(is,".pdf",getCodigo()+"-"+getNombre().toUpperCase()+".pdf");
         } catch (Exception e) {
             return null;
         }
    }

    public void setFormato(byte[] formato) {
        this.formato = formato;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}

