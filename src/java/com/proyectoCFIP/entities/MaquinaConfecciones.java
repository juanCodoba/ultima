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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "maquina_confecciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaquinaConfecciones.findAll", query = "SELECT m FROM MaquinaConfecciones m"),
    @NamedQuery(name = "MaquinaConfecciones.findByIdMaquinaConfecciones", query = "SELECT m FROM MaquinaConfecciones m WHERE m.idMaquinaConfecciones = :idMaquinaConfecciones"),
    @NamedQuery(name = "MaquinaConfecciones.findByCodigo", query = "SELECT m FROM MaquinaConfecciones m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MaquinaConfecciones.findBySerial", query = "SELECT m FROM MaquinaConfecciones m WHERE m.serial = :serial"),
    @NamedQuery(name = "MaquinaConfecciones.findByEstado", query = "SELECT m FROM MaquinaConfecciones m WHERE m.estado = :estado")})
public class MaquinaConfecciones implements Serializable {
    @Column(name = "especial")
    private Boolean especial;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_maquina_confecciones")
    private Integer idMaquinaConfecciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "serial")
    private String serial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @JoinColumn(name = "id_marca", referencedColumnName = "id_marca")
    @ManyToOne
    private Marca idMarca;
    @JoinColumn(name = "id_modelo", referencedColumnName = "id_modelo")
    @ManyToOne
    private Modelo idModelo;
    @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo")
    @ManyToOne(optional = false)
    private Modulo idModulo;
    @JoinColumn(name = "id_tipo_maquina_confecciones", referencedColumnName = "id_tipo_maquina_confecciones")
    @ManyToOne(optional = false)
    private TipoMaquinaConfecciones idTipoMaquinaConfecciones;

    public MaquinaConfecciones() {
    }

    public MaquinaConfecciones(Integer idMaquinaConfecciones) {
        this.idMaquinaConfecciones = idMaquinaConfecciones;
    }

    public MaquinaConfecciones(Integer idMaquinaConfecciones, String codigo, String serial, boolean estado) {
        this.idMaquinaConfecciones = idMaquinaConfecciones;
        this.codigo = codigo;
        this.serial = serial;
        this.estado = estado;
    }

    public Integer getIdMaquinaConfecciones() {
        return idMaquinaConfecciones;
    }

    public void setIdMaquinaConfecciones(Integer idMaquinaConfecciones) {
        this.idMaquinaConfecciones = idMaquinaConfecciones;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Marca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marca idMarca) {
        this.idMarca = idMarca;
    }

    public Modelo getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Modelo idModelo) {
        this.idModelo = idModelo;
    }

    public Modulo getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Modulo idModulo) {
        this.idModulo = idModulo;
    }

    public TipoMaquinaConfecciones getIdTipoMaquinaConfecciones() {
        return idTipoMaquinaConfecciones;
    }

    public void setIdTipoMaquinaConfecciones(TipoMaquinaConfecciones idTipoMaquinaConfecciones) {
        this.idTipoMaquinaConfecciones = idTipoMaquinaConfecciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaquinaConfecciones != null ? idMaquinaConfecciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaquinaConfecciones)) {
            return false;
        }
        MaquinaConfecciones other = (MaquinaConfecciones) object;
        if ((this.idMaquinaConfecciones == null && other.idMaquinaConfecciones != null) || (this.idMaquinaConfecciones != null && !this.idMaquinaConfecciones.equals(other.idMaquinaConfecciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Codigo: "+getCodigo()+ " Serial: "+getSerial()+" Modulo: "+getIdModulo().getNombre().toUpperCase();
    }
    
    public String getStringEspecial(){
        if(especial == true){
            return "Si";
        }else{
            return "No";
        }
    }
    public Boolean getEspecial() {
        return especial;
    }

    public void setEspecial(Boolean especial) {
        this.especial = especial;
    }
    
}
