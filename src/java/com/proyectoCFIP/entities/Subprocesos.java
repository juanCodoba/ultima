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
import javax.persistence.ManyToMany;
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
@Table(name = "subprocesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subprocesos.findAll", query = "SELECT s FROM Subprocesos s"),
    @NamedQuery(name = "Subprocesos.findByIdSubproceso", query = "SELECT s FROM Subprocesos s WHERE s.idSubproceso = :idSubproceso"),
    @NamedQuery(name = "Subprocesos.findByIdProceso", query = "SELECT s FROM Subprocesos s WHERE s.idProceso = :idProceso"),
    @NamedQuery(name = "Subprocesos.findByNombreSubproceso", query = "SELECT s FROM Subprocesos s WHERE s.nombreSubproceso = :nombreSubproceso")})
public class Subprocesos implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubproceso")
    private List<CalidadGestionConocimiento> calidadGestionConocimientoList;

    @OneToMany(mappedBy = "idSubproceso")
    private List<AuProcesoEvaluado> auProcesoEvaluadoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubproceso")
    private List<SistemasEquiposUsuarios> sistemasEquiposUsuariosList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubproceso")
    private List<CalidadPlanAccion> calidadPlanAccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubprocesoApoyoUno")
    private List<CalidadPlanAccion> calidadPlanAccionUnoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubprocesoApoyoDos")
    private List<CalidadPlanAccion> calidadPlanAccionDosList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubproceso")
    private List<Cargos> cargosList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_subproceso")
    private Integer idSubproceso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_subproceso")
    private String nombreSubproceso;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubproceso")
    private List<Documentos> documentosList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubproceso")
    private List<Formatos> formatosList;

    @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso")
    @ManyToOne(optional = false)
    private Procesos idProceso;



    public Subprocesos() {
    }

    public Subprocesos(Integer idSubproceso) {
        this.idSubproceso = idSubproceso;
    }

    public Subprocesos(Integer idSubproceso, String nombreSubproceso) {
        this.idSubproceso = idSubproceso;
        this.nombreSubproceso = nombreSubproceso;
    }

    public Integer getIdSubproceso() {
        return idSubproceso;
    }

    public void setIdSubproceso(Integer idSubproceso) {
        this.idSubproceso = idSubproceso;
    }

    public String getNombreSubproceso() {
        return nombreSubproceso;
    }

    public void setNombreSubproceso(String nombreSubproceso) {
        this.nombreSubproceso = nombreSubproceso;
    }

    @XmlTransient
    public List<Documentos> getDocumentosList() {
        return documentosList;
    }

    public void setDocumentosList(List<Documentos> documentosList) {
        this.documentosList = documentosList;
    }

    @XmlTransient
    public List<Formatos> getFormatosList() {
        return formatosList;
    }

    public void setFormatosList(List<Formatos> formatosList) {
        this.formatosList = formatosList;
    }

    public Procesos getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Procesos idProceso) {
        this.idProceso = idProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubproceso != null ? idSubproceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subprocesos)) {
            return false;
        }
        Subprocesos other = (Subprocesos) object;
        if ((this.idSubproceso == null && other.idSubproceso != null) || (this.idSubproceso != null && !this.idSubproceso.equals(other.idSubproceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombreSubproceso().toUpperCase();
    }

    @XmlTransient
    public List<CalidadPlanAccion> getCalidadPlanAccionList() {
        return calidadPlanAccionList;
    }

    public void setCalidadPlanAccionList(List<CalidadPlanAccion> calidadPlanAccionList) {
        this.calidadPlanAccionList = calidadPlanAccionList;
    }

    @XmlTransient
    public List<SistemasEquiposUsuarios> getSistemasEquiposUsuariosList() {
        return sistemasEquiposUsuariosList;
    }

    public void setSistemasEquiposUsuariosList(List<SistemasEquiposUsuarios> sistemasEquiposUsuariosList) {
        this.sistemasEquiposUsuariosList = sistemasEquiposUsuariosList;
    }

    @XmlTransient
    public List<AuProcesoEvaluado> getAuProcesoEvaluadoList() {
        return auProcesoEvaluadoList;
    }

    public void setAuProcesoEvaluadoList(List<AuProcesoEvaluado> auProcesoEvaluadoList) {
        this.auProcesoEvaluadoList = auProcesoEvaluadoList;
    }

    @XmlTransient
    public List<CalidadGestionConocimiento> getCalidadGestionConocimientoList() {
        return calidadGestionConocimientoList;
    }

    public void setCalidadGestionConocimientoList(List<CalidadGestionConocimiento> calidadGestionConocimientoList) {
        this.calidadGestionConocimientoList = calidadGestionConocimientoList;
    }

    public List<Cargos> getCargosList() {
        return cargosList;
    }

    public void setCargosList(List<Cargos> cargosList) {
        this.cargosList = cargosList;
    }



    public List<CalidadPlanAccion> getCalidadPlanAccionUnoList() {
        return calidadPlanAccionUnoList;
    }

    public void setCalidadPlanAccionUnoList(List<CalidadPlanAccion> calidadPlanAccionUnoList) {
        this.calidadPlanAccionUnoList = calidadPlanAccionUnoList;
    }

    public List<CalidadPlanAccion> getCalidadPlanAccionDosList() {
        return calidadPlanAccionDosList;
    }

    public void setCalidadPlanAccionDosList(List<CalidadPlanAccion> calidadPlanAccionDosList) {
        this.calidadPlanAccionDosList = calidadPlanAccionDosList;
    }
    

}
