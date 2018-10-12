/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

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

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "otro_dispositivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OtroDispositivo.findAll", query = "SELECT o FROM OtroDispositivo o"),
    @NamedQuery(name = "OtroDispositivo.findByIdOtroDispositivo", query = "SELECT o FROM OtroDispositivo o WHERE o.idOtroDispositivo = :idOtroDispositivo"),
    @NamedQuery(name = "OtroDispositivo.findByCodigoDispositivo", query = "SELECT o FROM OtroDispositivo o WHERE o.codigoDispositivo = :codigoDispositivo"),
    @NamedQuery(name = "OtroDispositivo.findByCostoDispositivo", query = "SELECT o FROM OtroDispositivo o WHERE o.costoDispositivo = :costoDispositivo"),
    @NamedQuery(name = "OtroDispositivo.findByArea", query = "SELECT o FROM OtroDispositivo o WHERE o.idSeccion.idArea = :idArea ORDER BY o.idSeccion.nombreSeccion"),
    @NamedQuery(name = "OtroDispositivo.findByUsuario", query = "SELECT c FROM OtroDispositivo c WHERE c.idUsuario = :idUsuario"),
    @NamedQuery(name = "OtroDispositivo.findByEstadoProgramadoDis", query = "SELECT o FROM OtroDispositivo o WHERE o.estadoProgramadoDis = :estadoProgramadoDis"),
    @NamedQuery(name = "OtroDispositivo.findByEstadoDispositivo", query = "SELECT o FROM OtroDispositivo o WHERE o.estadoDispositivo = :estadoDispositivo")})
public class OtroDispositivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_otro_dispositivo")
    private Integer idOtroDispositivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "codigo_dispositivo")
    private String codigoDispositivo;
    @Column(name = "costo_dispositivo")
    private Integer costoDispositivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_dispositivo")
    private boolean estadoDispositivo;
    @Column(name = "fecha_cambio_repuesto")
    @Temporal(TemporalType.DATE)
    private Date fechaCambioRepuesto;
    @JoinColumn(name = "id_modelo", referencedColumnName = "id_modelo")
    @ManyToOne(optional = false)
    private Modelo idModelo;
    @JoinColumn(name = "id_seccion", referencedColumnName = "id_seccion")
    @ManyToOne
    private Seccion idSeccion;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOtroDispositivo")
    private List<HistorialOtroDispositivo> historialOtroDispositivoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOtroDispositivo")
    private List<CronogramaManteDispositivo> cronogramaManteDispositivoList;
    @NotNull
    @Column(name = "estado_programado_dis")
    private boolean estadoProgramadoDis;
    @Size(max = 45)
    @Column(name = "caracteristica")
    private String caracteristica;
    public OtroDispositivo() {
    }

    public OtroDispositivo(Integer idOtroDispositivo) {
        this.idOtroDispositivo = idOtroDispositivo;
    }

    public OtroDispositivo(Integer idOtroDispositivo, String codigoDispositivo, boolean estadoDispositivo, boolean estadoProgramadoDis) {
        this.idOtroDispositivo = idOtroDispositivo;
        this.codigoDispositivo = codigoDispositivo;
        this.estadoDispositivo = estadoDispositivo;
        this.estadoProgramadoDis = estadoProgramadoDis;
    }

    public Integer getIdOtroDispositivo() {
        return idOtroDispositivo;
    }

    public void setIdOtroDispositivo(Integer idOtroDispositivo) {
        this.idOtroDispositivo = idOtroDispositivo;
    }

    public String getCodigoDispositivo() {
        return codigoDispositivo;
    }

    public void setCodigoDispositivo(String codigoDispositivo) {
        this.codigoDispositivo = codigoDispositivo;
    }

    public Integer getCostoDispositivo() {
        return costoDispositivo;
    }

    public void setCostoDispositivo(Integer costoDispositivo) {
        this.costoDispositivo = costoDispositivo;
    }

    public boolean getEstadoDispositivo() {
        return estadoDispositivo;
    }

    public void setEstadoDispositivo(boolean estadoDispositivo) {
        this.estadoDispositivo = estadoDispositivo;
    }
    
     public Date getFechaCambioRepuesto() {
        return fechaCambioRepuesto;
    }

    public void setFechaCambioRepuesto(Date fechaCambioRepuesto) {
        this.fechaCambioRepuesto = fechaCambioRepuesto;
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

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public boolean getEstadoProgramadoDis() {
        return estadoProgramadoDis;
    }

    public void setEstadoProgramadoDis(boolean estadoProgramadoDis) {
        this.estadoProgramadoDis = estadoProgramadoDis;
    }
    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }
    
    @XmlTransient
    public List<HistorialOtroDispositivo> getHistorialOtroDispositivoList() {
        return historialOtroDispositivoList;
    }

    public void setHistorialOtroDispositivoList(List<HistorialOtroDispositivo> historialOtroDispositivoList) {
        this.historialOtroDispositivoList = historialOtroDispositivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOtroDispositivo != null ? idOtroDispositivo.hashCode() : 0);
        return hash;
    }
   @XmlTransient
    public List<CronogramaManteDispositivo> getCronogramaManteDispositivoList() {
        return cronogramaManteDispositivoList;
    }

    public void setCronogramaManteDispositivoList(List<CronogramaManteDispositivo> cronogramaManteDispositivoList) {
        this.cronogramaManteDispositivoList = cronogramaManteDispositivoList;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OtroDispositivo)) {
            return false;
        }
        OtroDispositivo other = (OtroDispositivo) object;
        if ((this.idOtroDispositivo == null && other.idOtroDispositivo != null) || (this.idOtroDispositivo != null && !this.idOtroDispositivo.equals(other.idOtroDispositivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lugar: "+getIdSeccion().getNombreSeccion().toUpperCase()+" / Codigo del dispositivo: "+ getCodigoDispositivo().toUpperCase();
    }
    
}

