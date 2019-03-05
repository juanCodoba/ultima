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
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "cargos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargos.findAll", query = "SELECT c FROM Cargos c ORDER BY c.idCargo DESC"),
    @NamedQuery(name = "Cargos.findByIdCargo", query = "SELECT c FROM Cargos c WHERE c.idCargo = :idCargo ORDER BY c.idCargo DESC"),
    @NamedQuery(name = "Cargos.findByIdUsuario", query = "SELECT c FROM Cargos c WHERE c.idResponsableCargo = :idResponsableCargo"),
    @NamedQuery(name = "Cargos.findByNombreCargo", query = "SELECT c FROM Cargos c WHERE c.nombreCargo = :nombreCargo ORDER BY c.idCargo DESC")})
public class Cargos implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCargo")
    private List<Usuario> usuarioList;

    @Id
    @Column(name = "id_cargo")
    private Integer idCargo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 85)
    @Column(name = "nombre_cargo")
    private String nombreCargo;

    @NotNull
    @Size(min = 1, max = 85)
    @Column(name = "n_persona_en_cargo")
    private String usuarioEnCargo;

    @NotNull
    @Size(min = 1, max = 85)
    @Column(name = "n_persona_a_cargo")
    private String usuarioAcargo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCargo")
    private List<Requisito> requisitoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCargo")
    private List<MaeFuncion> maeFuncionList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCargo")
    private List<Habilidad> habilidadList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCargoResponsable")
    private List<Cargos> cargosList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCargo")
    private List<Actividad> actividadList;

    @JoinColumn(name = "id_cargo_responsable", referencedColumnName = "id_cargo")
    @ManyToOne(optional = true)
    private Cargos idCargoResponsable;
        
    @JoinColumn(name = "responsable_cargo", referencedColumnName = "id_usuario")
    @ManyToOne(optional = true)
    private Usuario idResponsableCargo;

    @JoinColumn(name = "id_subproceso", referencedColumnName = "id_subproceso")
    @ManyToOne(optional = true)
    private Subprocesos idSubproceso;

    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne(optional = true)
    private Area idArea;

    @JoinColumn(name = "id_tipo_cargo", referencedColumnName = "id_tipo_cargo")
    @ManyToOne(optional = true)
    private TipoCargo idTipoCargo;

    @JoinColumn(name = "id_nivel_cargo", referencedColumnName = "id_nivel_cargo")
    @ManyToOne(optional = true)
    private NivelCargo idNivelCargo;

    @JoinColumn(name = "id_profesiograma", referencedColumnName = "id_profesiograma")
    @ManyToOne(optional = true)
    private Profesiograma idProfesiograma;

    public Cargos() {
    }

    public Cargos(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public Cargos(Integer idCargo, String nombreCargo) {
        this.idCargo = idCargo;
        this.nombreCargo = nombreCargo;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCargo != null ? idCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargos)) {
            return false;
        }
        Cargos other = (Cargos) object;
        if ((this.idCargo == null && other.idCargo != null) || (this.idCargo != null && !this.idCargo.equals(other.idCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombreCargo().toUpperCase();
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public List<Requisito> getRequisitoList() {
        return requisitoList;
    }

    public void setRequisitoList(List<Requisito> requisitoList) {
        this.requisitoList = requisitoList;
    }

    public List<Cargos> getCargosList() {
        return cargosList;
    }

    public void setCargosList(List<Cargos> cargosList) {
        this.cargosList = cargosList;
    }

    public Cargos getIdCargoResponsable() {
        return idCargoResponsable;
    }

    public void setIdCargoResponsable(Cargos idCargoResponsable) {
        this.idCargoResponsable = idCargoResponsable;
    }

    public Subprocesos getIdSubproceso() {
        return idSubproceso;
    }

    public void setIdSubproceso(Subprocesos idSubproceso) {
        this.idSubproceso = idSubproceso;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public TipoCargo getIdTipoCargo() {
        return idTipoCargo;
    }

    public void setIdTipoCargo(TipoCargo idTipoCargo) {
        this.idTipoCargo = idTipoCargo;
    }

    public NivelCargo getIdNivelCargo() {
        return idNivelCargo;
    }

    public void setIdNivelCargo(NivelCargo idNivelCargo) {
        this.idNivelCargo = idNivelCargo;
    }

    public List<MaeFuncion> getMaeFuncionList() {
        return maeFuncionList;
    }

    public void setMaeFuncionList(List<MaeFuncion> maeFuncionList) {
        this.maeFuncionList = maeFuncionList;
    }

    public Profesiograma getIdProfesiograma() {
        return idProfesiograma;
    }

    public void setIdProfesiograma(Profesiograma idProfesiograma) {
        this.idProfesiograma = idProfesiograma;
    }

    public List<Actividad> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }

    public List<Habilidad> getHabilidadList() {
        return habilidadList;
    }

    public void setHabilidadList(List<Habilidad> habilidadList) {
        this.habilidadList = habilidadList;
    }

    public String getUsuarioEnCargo() {
        return usuarioEnCargo;
    }

    public void setUsuarioEnCargo(String usuarioEnCargo) {
        this.usuarioEnCargo = usuarioEnCargo;
    }

    public String getUsuarioAcargo() {
        return usuarioAcargo;
    }

    public void setUsuarioAcargo(String usuarioAcargo) {
        this.usuarioAcargo = usuarioAcargo;
    }

    public Usuario getIdResponsableCargo() {
        return idResponsableCargo;
    }

    public void setIdResponsableCargo(Usuario idResponsableCargo) {
        this.idResponsableCargo = idResponsableCargo;
    }
    
    
    

}
