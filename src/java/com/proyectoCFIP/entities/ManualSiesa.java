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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "manual_siesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ManualSiesa.findAll", query = "SELECT m FROM ManualSiesa m"),
    @NamedQuery(name = "ManualSiesa.findConsulta", query = "SELECT m.codigo as codigo, m.nombre as nombre fecha FROM ManualSiesa AS m"),
    @NamedQuery(name = "ManualSiesa.findByIdManualSiesa", query = "SELECT m FROM ManualSiesa m WHERE m.idManualSiesa = :idManualSiesa"),
    @NamedQuery(name = "ManualSiesa.findByCodigo", query = "SELECT m FROM ManualSiesa m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "ManualSiesa.findByNombre", query = "SELECT m FROM ManualSiesa m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "ManualSiesa.findByFechaActualizacion", query = "SELECT m FROM ManualSiesa m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "ManualSiesa.findByDescripcion", query = "SELECT m FROM ManualSiesa m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "ManualSiesa.findByVersion", query = "SELECT m FROM ManualSiesa m WHERE m.version = :version")})
public class ManualSiesa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_manual_siesa")
    private Integer idManualSiesa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "manual")
    private String manual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private double version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idManualSiesa")
    private List<HistorialManualSiesa> historialManualSiesaList;
    @JoinColumn(name = "id_suit", referencedColumnName = "id_suit")
    @ManyToOne(optional = false)
    private Suit idSuit;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "id_subproceso_suit", referencedColumnName = "id_subproceso_suit")
    @ManyToOne(optional = false)
    private SubprocesoSuit idSubprocesoSuit;

    public ManualSiesa() {
    }
    public ManualSiesa( String codigo) {
        this.codigo = codigo;
    }


    public ManualSiesa(Integer idManualSiesa) {
        this.idManualSiesa = idManualSiesa;
    }

    public ManualSiesa(Integer idManualSiesa, String codigo, String nombre, String manual, double version) {
        this.idManualSiesa = idManualSiesa;
        this.codigo = codigo;
        this.nombre = nombre;
        this.manual = manual;
        this.version = version;
    }

    public Integer getIdManualSiesa() {
        return idManualSiesa;
    }

    public void setIdManualSiesa(Integer idManualSiesa) {
        this.idManualSiesa = idManualSiesa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

//    public DefaultStreamedContent getManual() {
//        try {
//             InputStream is= new ByteArrayInputStream((byte[])manual);
//             return new DefaultStreamedContent(is,"documento/pdf",getCodigo().toUpperCase()+"-"+getNombre().toUpperCase()+".pdf");
//             }
//          catch (Exception e) {
//             return null;
//         }
//    }



    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public SubprocesoSuit getIdSubprocesoSuit() {
        return idSubprocesoSuit;
    }

    public void setIdSubprocesoSuit(SubprocesoSuit idSubprocesoSuit) {
        this.idSubprocesoSuit = idSubprocesoSuit;
    }

    
    
    @XmlTransient
    public List<HistorialManualSiesa> getHistorialManualSiesaList() {
        return historialManualSiesaList;
    }

    public void setHistorialManualSiesaList(List<HistorialManualSiesa> historialManualSiesaList) {
        this.historialManualSiesaList = historialManualSiesaList;
    }

    public Suit getIdSuit() {
        return idSuit;
    }

    public void setIdSuit(Suit idSuit) {
        this.idSuit = idSuit;
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
        hash += (idManualSiesa != null ? idManualSiesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ManualSiesa)) {
            return false;
        }
        ManualSiesa other = (ManualSiesa) object;
        if ((this.idManualSiesa == null && other.idManualSiesa != null) || (this.idManualSiesa != null && !this.idManualSiesa.equals(other.idManualSiesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ManualSiesa[ idManualSiesa=" + idManualSiesa + " ]";
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }
    
    
    
    
}
