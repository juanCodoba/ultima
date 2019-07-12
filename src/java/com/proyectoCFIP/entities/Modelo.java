/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
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
@Table(name = "modelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modelo.findAll", query = "SELECT m FROM Modelo m"),
    @NamedQuery(name = "Modelo.findByIdModelo", query = "SELECT m FROM Modelo m WHERE m.idModelo = :idModelo"),
    @NamedQuery(name = "Modelo.findByTipo", query = "SELECT m FROM Modelo m WHERE m.idTipo = :idTipo"),
    @NamedQuery(name = "Modelo.findByTipoCompu", query = "SELECT m FROM Modelo m WHERE m.idTipo.idTipo = 1 OR m.idTipo.idTipo = 2"),
    @NamedQuery(name = "Modelo.findByTipoDispo", query = "SELECT m FROM Modelo m WHERE m.idTipo.idTipo = 3 OR m.idTipo.idTipo = 4"),
    @NamedQuery(name = "Modelo.findByTipoDispoConec", query = "SELECT m FROM Modelo m WHERE m.idTipo.idTipo = 5"),
    @NamedQuery(name = "Modelo.findByNombreModelo", query = "SELECT m FROM Modelo m WHERE m.nombreModelo = :nombreModelo")})
public class Modelo implements Serializable {
    @Lob
    @Column(name = "imagen_modelo")
    private byte[] imagenModelo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModelo")
    private List<MaquinaConfecciones> maquinaConfeccionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModelo")
    private List<OtroDispositivo> otroDispositivoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_modelo")
    private Integer idModelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_modelo")
    private String nombreModelo;
    @JoinColumn(name = "id_marca", referencedColumnName = "id_marca")
    @ManyToOne(optional = false)
    private Marca idMarca;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
    @ManyToOne(optional = false)
    private Tipo idTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModelo")
    private List<Computador> computadorList;

    public Modelo() {
    }

    public Modelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public Modelo(Integer idModelo, String nombreModelo, byte[] imagenModelo) {
        this.idModelo = idModelo;
        this.nombreModelo = nombreModelo;
        this.imagenModelo = imagenModelo;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }
    public byte[] getImagenModelo() {
        return imagenModelo;
    }
    public void setImagenModelo(byte[] imagenModelo) {
        this.imagenModelo = imagenModelo;
    }

    public Marca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marca idMarca) {
        this.idMarca = idMarca;
    }
    public Tipo getIdTipo() {
        return idTipo;
    }

    public void setidTipo(Tipo tipoIdTipo) {
        this.idTipo = tipoIdTipo;
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
        hash += (idModelo != null ? idModelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modelo)) {
            return false;
        }
        Modelo other = (Modelo) object;
        if ((this.idModelo == null && other.idModelo != null) || (this.idModelo != null && !this.idModelo.equals(other.idModelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombreModelo().toUpperCase()+"/"+getIdMarca().getNombreMarca().toUpperCase();
    }



    @XmlTransient
    public List<OtroDispositivo> getOtroDispositivoList() {
        return otroDispositivoList;
    }

    public void setOtroDispositivoList(List<OtroDispositivo> otroDispositivoList) {
        this.otroDispositivoList = otroDispositivoList;
    }

    @XmlTransient
    public List<MaquinaConfecciones> getMaquinaConfeccionesList() {
        return maquinaConfeccionesList;
    }

    public void setMaquinaConfeccionesList(List<MaquinaConfecciones> maquinaConfeccionesList) {
        this.maquinaConfeccionesList = maquinaConfeccionesList;
    }   

}
