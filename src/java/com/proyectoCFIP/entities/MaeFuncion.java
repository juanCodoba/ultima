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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "mae_funcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeFuncion.findAll", query = "SELECT m FROM MaeFuncion m"),
    @NamedQuery(name = "MaeFuncion.findByIdMaeFuncion", query = "SELECT m FROM MaeFuncion m WHERE m.idMaeFuncion = :idMaeFuncion"),
    @NamedQuery(name = "MaeFuncion.findByNombre", query = "SELECT m FROM MaeFuncion m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MaeFuncion.findByEstado", query = "SELECT m FROM MaeFuncion m WHERE m.estado = :estado"),
    @NamedQuery(name = "MaeFuncion.findByImportancia", query = "SELECT m FROM MaeFuncion m WHERE m.importancia = :importancia"),
    @NamedQuery(name = "MaeFuncion.findByIdTipoFuncion", query = "SELECT m FROM MaeFuncion m WHERE m.idTipoFuncion = :idTipoFuncion"),
    @NamedQuery(name = "MaeFuncion.findByIdCargo", query = "SELECT m FROM MaeFuncion m WHERE m.idCargo = :idCargo ORDER BY m.idMaeFuncion DESC")})
public class MaeFuncion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mae_funcion")
    private Integer idMaeFuncion;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "importancia")
    private Integer importancia;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "accion_funcion")
    private String accionFuncion;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "objeto_funcion")
    private String objetoFuncion;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "condicion_funcion")
    private String condicionFuncion;

    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne(optional = false)
    private Cargos idCargo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaeFuncion")
    private List<Actividad> ActividadList;

    @JoinColumn(name = "id_tipo_funcion", referencedColumnName = "id_tipo_funcion")
    @ManyToOne(optional = false)
    private TipoFuncion idTipoFuncion;
    
    @JoinTable(name = "designe_raci", joinColumns = {
        @JoinColumn(name = "id_mae_funcion", referencedColumnName = "id_mae_funcion")}, inverseJoinColumns = {
        @JoinColumn(name = "id_raci", referencedColumnName = "id_raci")})
    @ManyToMany
    private List<Raci> raciList;

    public MaeFuncion() {
    }

    public MaeFuncion(Integer idMaeFuncion) {
        this.idMaeFuncion = idMaeFuncion;
    }

    public MaeFuncion(Integer idMaeFuncion, int idTipoFuncion, int idCargo) {
        this.idMaeFuncion = idMaeFuncion;

    }

    public Integer getIdMaeFuncion() {
        return idMaeFuncion;
    }

    public void setIdMaeFuncion(Integer idMaeFuncion) {
        this.idMaeFuncion = idMaeFuncion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }



    public Integer getImportancia() {
        return importancia;
    }

    public void setImportancia(Integer importancia) {
        this.importancia = importancia;
    }

    public String getAccionFuncion() {
        return accionFuncion;
    }

    public void setAccionFuncion(String accionFuncion) {
        this.accionFuncion = accionFuncion;
    }

    public String getObjetoFuncion() {
        return objetoFuncion;
    }

    public void setObjetoFuncion(String objetoFuncion) {
        this.objetoFuncion = objetoFuncion;
    }

    public String getCondicionFuncion() {
        return condicionFuncion;
    }

    public void setCondicionFuncion(String condicionFuncion) {
        this.condicionFuncion = condicionFuncion;
    }

    public TipoFuncion getIdTipoFuncion() {
        return idTipoFuncion;
    }

    public void setIdTipoFuncion(TipoFuncion idTipoFuncion) {
        this.idTipoFuncion = idTipoFuncion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaeFuncion != null ? idMaeFuncion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeFuncion)) {
            return false;
        }
        MaeFuncion other = (MaeFuncion) object;
        if ((this.idMaeFuncion == null && other.idMaeFuncion != null) || (this.idMaeFuncion != null && !this.idMaeFuncion.equals(other.idMaeFuncion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  getAccionFuncion() + " " + getObjetoFuncion() + " " + getCondicionFuncion() ;
    }

    public Cargos getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargos idCargo) {
        this.idCargo = idCargo;
    }
    @XmlTransient
    public List<Actividad> getActividadList() {
        return ActividadList ;
    }

    public void setActividadList(List<Actividad> ActividadList) {
        this.ActividadList = ActividadList;
    }

    public List<Raci> getRaciList() {
        return raciList;
    }

    public void setRaciList(List<Raci> raciList) {
        this.raciList = raciList;
    }


    
    

}
