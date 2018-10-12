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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "dispositivo_conectividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DispositivoConectividad.findAll", query = "SELECT d FROM DispositivoConectividad d"),
    @NamedQuery(name = "DispositivoConectividad.findByCodigoDispositivo", query = "SELECT d FROM DispositivoConectividad d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "DispositivoConectividad.findByIdDispositivoConectividad", query = "SELECT d FROM DispositivoConectividad d WHERE d.idDispositivoConectividad = :idDispositivoConectividad"),
    @NamedQuery(name = "DispositivoConectividad.findByArea", query = "SELECT d FROM DispositivoConectividad d WHERE d.idSeccion.idArea = :idArea ORDER BY d.idSeccion"),
    @NamedQuery(name = "DispositivoConectividad.findByUsuario", query = "SELECT d FROM DispositivoConectividad d WHERE d.idUsuario = :idUsuario"),
    @NamedQuery(name = "DispositivoConectividad.findByEstadoProg", query = "SELECT d FROM DispositivoConectividad d WHERE d.estadoProgramado = :estadoProgramado"),
    @NamedQuery(name = "DispositivoConectividad.findByEstado", query = "SELECT d FROM DispositivoConectividad d WHERE d.estado = :estado")})
public class DispositivoConectividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dispositivo_conectividad")
    private Integer idDispositivoConectividad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @JoinColumn(name = "id_modelo", referencedColumnName = "id_modelo")
    @ManyToOne
    private Modelo idModelo;
    @JoinColumn(name = "id_seccion", referencedColumnName = "id_seccion")
    @ManyToOne
    private Seccion idSeccion;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
    @ManyToOne(optional = false)
    private Tipo idTipo;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDispositivoConectividad")
    private List<HistorialDispositivoConectividad> historialDispositivoConectividadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDispositivoConectividad")
    private List<CronogramaManteDisCon> cronogramaManteDisConList;
    @Size(min = 1, max = 75)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_programado")
    private boolean estadoProgramado;
    public DispositivoConectividad() {
    }

    public DispositivoConectividad(Integer idDispositivoConectividad) {
        this.idDispositivoConectividad = idDispositivoConectividad;
    }

    public DispositivoConectividad(Integer idDispositivoConectividad, boolean estado, String codigo, boolean estadoProgramado) {
        this.idDispositivoConectividad = idDispositivoConectividad;
        this.estado = estado;
        this.codigo = codigo;
        this.estadoProgramado = estadoProgramado;
    }

    public Integer getIdDispositivoConectividad() {
        return idDispositivoConectividad;
    }

    public void setIdDispositivoConectividad(Integer idDispositivoConectividad) {
        this.idDispositivoConectividad = idDispositivoConectividad;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Modelo getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Modelo idModelo) {
        this.idModelo = idModelo;
    }

    public Seccion getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Seccion idSeccion) {
        this.idSeccion = idSeccion;
    }

    public Tipo getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Tipo idTipo) {
        this.idTipo = idTipo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isEstadoProgramado() {
        return estadoProgramado;
    }

    public void setEstadoProgramado(boolean estadoProgramado) {
        this.estadoProgramado = estadoProgramado;
    }
    
    
    @XmlTransient
    public List<HistorialDispositivoConectividad> getHistorialDispositivoConectividadList() {
        return historialDispositivoConectividadList;
    }

    public void setHistorialDispositivoConectividadList(List<HistorialDispositivoConectividad> historialDispositivoConectividadList) {
        this.historialDispositivoConectividadList = historialDispositivoConectividadList;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @XmlTransient
    public List<CronogramaManteDisCon> getCronogramaManteDisConList() {
        return cronogramaManteDisConList;
    }

    public void setCronogramaManteDisConList(List<CronogramaManteDisCon> cronogramaManteDisConList) {
        this.cronogramaManteDisConList = cronogramaManteDisConList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDispositivoConectividad != null ? idDispositivoConectividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DispositivoConectividad)) {
            return false;
        }
        DispositivoConectividad other = (DispositivoConectividad) object;
        if ((this.idDispositivoConectividad == null && other.idDispositivoConectividad != null) || (this.idDispositivoConectividad != null && !this.idDispositivoConectividad.equals(other.idDispositivoConectividad))) {
            return false;
        }
        return true;
    }
    public String getCodigoNA(){
        String codigoNA;
        if(getCodigo()== null){
            return  codigoNA="N/A";
        }else{
            return codigoNA= getCodigo();
        }
    }
    public String getSeccionNA(){
        String seccion;
        if(getIdSeccion()== null){
            return  seccion="Pendiente";
        }else{
            return seccion= getIdSeccion().getNombreSeccion();
        }
    }
     public String getModeloNA(){
         String modelo;
        if(getIdModelo()== null){
            return  modelo="N/A";
        }else{
            return modelo= getIdModelo().getNombreModelo();
        }
    }
    public String getMarcaNA(){
        String marca;
        if(getIdModelo()== null){
            return  marca="N/A";
        }else{
            return marca= getIdModelo().getIdMarca().getNombreMarca().toUpperCase();
        }
    }
     
    @Override
    public String toString() {
        return "Tipo de dispositivo: "+getIdTipo().getNombreTipo().toUpperCase()+" / Ubicación: "+getSeccionNA().toUpperCase() + " / Codigo: "+getCodigoNA().toUpperCase();
    }
    
}
