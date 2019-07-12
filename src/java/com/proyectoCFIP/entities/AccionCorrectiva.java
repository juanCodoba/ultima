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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "accion_correctiva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccionCorrectiva.findAll", query = "SELECT a FROM AccionCorrectiva a"),
    @NamedQuery(name = "AccionCorrectiva.findByIdAccionCorrectiva", query = "SELECT a FROM AccionCorrectiva a WHERE a.idAccionCorrectiva = :idAccionCorrectiva")})
public class AccionCorrectiva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_accion_correctiva")
    private Integer idAccionCorrectiva;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 2147483647)
    @Column(name = "responsable")
    private String responsable;

    @ManyToMany(mappedBy = "accionCorrectivaList")
    private List<CalidadPlanAccion> calidadPlanAccionList;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "IdaccionCorrectiva")
    private List<CalidadSeguimientoCorrectiva> calidadSeguimientoCorrectivaList;

    public AccionCorrectiva() {
    }

    public AccionCorrectiva(Integer idAccionCorrectiva, String descripcion, String responsable, Date fecha) {
        this.idAccionCorrectiva = idAccionCorrectiva;
        this.descripcion = descripcion;
        this.responsable = responsable;
        this.fecha = fecha;
    }

    public Integer getIdAccionCorrectiva() {
        return idAccionCorrectiva;
    }

    public void setIdAccionCorrectiva(Integer idAccionCorrectiva) {
        this.idAccionCorrectiva = idAccionCorrectiva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public List<CalidadPlanAccion> getCalidadPlanAccionList() {
        return calidadPlanAccionList;
    }

    public void setCalidadPlanAccionList(List<CalidadPlanAccion> calidadPlanAccionList) {
        this.calidadPlanAccionList = calidadPlanAccionList;
    }

    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccionCorrectiva != null ? idAccionCorrectiva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccionCorrectiva)) {
            return false;
        }
        AccionCorrectiva other = (AccionCorrectiva) object;
        if ((this.idAccionCorrectiva == null && other.idAccionCorrectiva != null) || (this.idAccionCorrectiva != null && !this.idAccionCorrectiva.equals(other.idAccionCorrectiva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.AccionCorrectiva[ idAccionCorrectiva=" + idAccionCorrectiva + " ]";
    }
    @XmlTransient
    public List<CalidadSeguimientoCorrectiva> getCalidadSeguimientoCorrectivaList() {
        return calidadSeguimientoCorrectivaList;
    }

    public void setCalidadSeguimientoCorrectivaList(List<CalidadSeguimientoCorrectiva> calidadSeguimientoCorrectivaList) {
        this.calidadSeguimientoCorrectivaList = calidadSeguimientoCorrectivaList;
    }
    
    

}
