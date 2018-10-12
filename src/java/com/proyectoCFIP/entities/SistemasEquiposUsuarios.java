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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author junio
 */
@Entity
@Table(name = "sistemas_equipos_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SistemasEquiposUsuarios.findAll", query = "SELECT s FROM SistemasEquiposUsuarios s")
    , @NamedQuery(name = "SistemasEquiposUsuarios.findByIdsistemasequiposUsuarios", query = "SELECT s FROM SistemasEquiposUsuarios s WHERE s.idsistemasequiposUsuarios = :idsistemasequiposUsuarios")
    , @NamedQuery(name = "SistemasEquiposUsuarios.findByNombres", query = "SELECT s FROM SistemasEquiposUsuarios s WHERE s.nombres = :nombres")
    , @NamedQuery(name = "SistemasEquiposUsuarios.findByApelldios", query = "SELECT s FROM SistemasEquiposUsuarios s WHERE s.apelldios = :apelldios")
    , @NamedQuery(name = "SistemasEquiposUsuarios.findByCiudad", query = "SELECT s FROM SistemasEquiposUsuarios s WHERE s.ciudad = :ciudad")
    , @NamedQuery(name = "SistemasEquiposUsuarios.findByCedula", query = "SELECT s FROM SistemasEquiposUsuarios s WHERE s.cedula = :cedula")
    , @NamedQuery(name = "SistemasEquiposUsuarios.findByModelo", query = "SELECT s FROM SistemasEquiposUsuarios s WHERE s.modelo = :modelo")
    , @NamedQuery(name = "SistemasEquiposUsuarios.findBySerie", query = "SELECT s FROM SistemasEquiposUsuarios s WHERE s.serie = :serie")
    , @NamedQuery(name = "SistemasEquiposUsuarios.findByMac", query = "SELECT s FROM SistemasEquiposUsuarios s WHERE s.mac = :mac")
    , @NamedQuery(name = "SistemasEquiposUsuarios.findByFechaPermiso", query = "SELECT s FROM SistemasEquiposUsuarios s WHERE s.fechaPermiso = :fechaPermiso")
    , @NamedQuery(name = "SistemasEquiposUsuarios.findByFechaVencimiento", query = "SELECT s FROM SistemasEquiposUsuarios s WHERE s.fechaVencimiento = :fechaVencimiento")
    , @NamedQuery(name = "SistemasEquiposUsuarios.findByEscaneo", query = "SELECT s FROM SistemasEquiposUsuarios s WHERE s.escaneo = :escaneo")
    , @NamedQuery(name = "SistemasEquiposUsuarios.findByEstado", query = "SELECT s FROM SistemasEquiposUsuarios s WHERE s.estado = :estado")})
public class SistemasEquiposUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sistemas_equiposUsuarios")
    private Integer idsistemasequiposUsuarios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "apelldios")
    private String apelldios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cedula")
    private String cedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "modelo")
    private String modelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "serie")
    private String serie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "mac")
    private String mac;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_permiso")
    @Temporal(TemporalType.DATE)
    private Date fechaPermiso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Size(max = 300)
    @Column(name = "escaneo")
    private String escaneo;
    @Column(name = "estado")
    private Boolean estado;
    @JoinColumn(name = "id_marca", referencedColumnName = "id_marca")
    @ManyToOne(optional = false)
    private Marca idMarca;
    @JoinColumn(name = "id_subproceso", referencedColumnName = "id_subproceso")
    @ManyToOne(optional = false)
    private Subprocesos idSubproceso;

    public SistemasEquiposUsuarios() {
    }

    public SistemasEquiposUsuarios(Integer idsistemasequiposUsuarios) {
        this.idsistemasequiposUsuarios = idsistemasequiposUsuarios;
    }

    public SistemasEquiposUsuarios(Integer idsistemasequiposUsuarios, String nombres, String apelldios, String ciudad, String cedula, String modelo, String serie, String mac, Date fechaPermiso, Date fechaVencimiento) {
        this.idsistemasequiposUsuarios = idsistemasequiposUsuarios;
        this.nombres = nombres;
        this.apelldios = apelldios;
        this.ciudad = ciudad;
        this.cedula = cedula;
        this.modelo = modelo;
        this.serie = serie;
        this.mac = mac;
        this.fechaPermiso = fechaPermiso;
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getIdsistemasequiposUsuarios() {
        return idsistemasequiposUsuarios;
    }

    public void setIdsistemasequiposUsuarios(Integer idsistemasequiposUsuarios) {
        this.idsistemasequiposUsuarios = idsistemasequiposUsuarios;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApelldios() {
        return apelldios;
    }

    public void setApelldios(String apelldios) {
        this.apelldios = apelldios;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Date getFechaPermiso() {
        return fechaPermiso;
    }

    public void setFechaPermiso(Date fechaPermiso) {
        this.fechaPermiso = fechaPermiso;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getEscaneo() {
        return escaneo;
    }

    public void setEscaneo(String escaneo) {
        this.escaneo = escaneo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Marca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marca idMarca) {
        this.idMarca = idMarca;
    }

    public Subprocesos getIdSubproceso() {
        return idSubproceso;
    }

    public void setIdSubproceso(Subprocesos idSubproceso) {
        this.idSubproceso = idSubproceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsistemasequiposUsuarios != null ? idsistemasequiposUsuarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SistemasEquiposUsuarios)) {
            return false;
        }
        SistemasEquiposUsuarios other = (SistemasEquiposUsuarios) object;
        if ((this.idsistemasequiposUsuarios == null && other.idsistemasequiposUsuarios != null) || (this.idsistemasequiposUsuarios != null && !this.idsistemasequiposUsuarios.equals(other.idsistemasequiposUsuarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
         return getNombres().toUpperCase()+" "+getApelldios().toUpperCase();
    }
    
}
