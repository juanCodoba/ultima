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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "factores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factores.findAll", query = "SELECT f FROM Factores f"),
    @NamedQuery(name = "Factores.findByIdFactor", query = "SELECT f FROM Factores f WHERE f.idFactor = :idFactor"),
    @NamedQuery(name = "Factores.findByIdProceso", query = "SELECT f FROM Factores f WHERE f.idProceso = :idProceso"),
    @NamedQuery(name = "Factores.findByIdCategoria", query = "SELECT f FROM Factores f WHERE f.idCategoria = :idCategoria"),
    @NamedQuery(name = "Factores.findByIdImportancia", query = "SELECT f FROM Factores f WHERE f.idImportancia = :idImportancia"),
    @NamedQuery(name = "Factores.findByIdOportunidad", query = "SELECT f FROM Factores f WHERE f.idOportunidad = :idOportunidad"),
    @NamedQuery(name = "Factores.findByIdFactoresGlobal", query = "SELECT f FROM Factores f WHERE  f.idCategoria.idCategoria = 1  ORDER BY f.puntuacion DESC"),
    @NamedQuery(name = "Factores.findByIdFactoresGlobal2", query = "SELECT f FROM Factores f WHERE  f.idCategoria.idCategoria = 2  ORDER BY f.puntuacion DESC"),
    @NamedQuery(name = "Factores.findByIdFactoresGlobal3", query = "SELECT f FROM Factores f WHERE f.idCategoria.idCategoria = 3  ORDER BY f.puntuacion DESC"),
    @NamedQuery(name = "Factores.findByIdFactoresGlobal4", query = "SELECT f FROM Factores f WHERE f.idCategoria.idCategoria = 4  ORDER BY f.puntuacion DESC"),
    @NamedQuery(name = "Factores.findByIdFactoresGlobal5", query = "SELECT f FROM Factores f WHERE f.idCategoria.idCategoria = 5  ORDER BY f.puntuacion DESC"),
    @NamedQuery(name = "Factores.findByIdFactoresGlobal6", query = "SELECT f FROM Factores f WHERE f.idCategoria.idCategoria = 6  ORDER BY f.puntuacion DESC"),
    @NamedQuery(name = "Factores.findByIdFactores", query = "SELECT f FROM Factores f WHERE f.idProceso = :idProceso AND f.idCategoria.idCategoria = 1  ORDER BY f.puntuacion DESC"),
    @NamedQuery(name = "Factores.findByIdFactores2", query = "SELECT f FROM Factores f WHERE f.idProceso = :idProceso AND f.idCategoria.idCategoria = 2  ORDER BY f.puntuacion DESC"),
    @NamedQuery(name = "Factores.findByIdFactores3", query = "SELECT f FROM Factores f WHERE f.idProceso = :idProceso AND f.idCategoria.idCategoria = 3  ORDER BY f.puntuacion DESC"),
    @NamedQuery(name = "Factores.findByIdFactores4", query = "SELECT f FROM Factores f WHERE f.idProceso = :idProceso AND f.idCategoria.idCategoria = 4  ORDER BY f.puntuacion DESC"),
    @NamedQuery(name = "Factores.findByIdFactores5", query = "SELECT f FROM Factores f WHERE f.idProceso = :idProceso AND f.idCategoria.idCategoria = 5  ORDER BY f.puntuacion DESC"),
    @NamedQuery(name = "Factores.findByIdFactores6", query = "SELECT f FROM Factores f WHERE f.idProceso = :idProceso AND f.idCategoria.idCategoria = 6  ORDER BY f.puntuacion DESC"),
    @NamedQuery(name = "Factores.findByPuntuacion", query = "SELECT f FROM Factores f ORDER BY f.puntuacion DESC"),

    @NamedQuery(name = "Factores.findByIdRiesgo", query = "SELECT f FROM Factores f WHERE f.idRiesgo = :idRiesgo")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "findByIdConsultaPieChart", query = "SELECT  descripcion_factor,  sum(puntuacion) as total \n"
            + "from factores inner join categoria on factores.id_categoria = categoria.id_categoria where  "
            + " factores.id_proceso = ? group by factores.id_categoria  ;", resultClass = Factores.class)
})
public class Factores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factor")
    private Integer idFactor;

    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion_factor")
    private String descripcionFactor;

    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne(optional = true)
    private Categoria idCategoria;

    @JoinColumn(name = "id_oportunidad", referencedColumnName = "id_oportunidad")
    @ManyToOne(optional = true)
    private Oportunidad idOportunidad;

    @JoinColumn(name = "id_riesgo", referencedColumnName = "id_riesgo")
    @ManyToOne(optional = true)
    private Riesgo idRiesgo;

    @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso")
    @ManyToOne(optional = true)
    private Procesos idProceso;

    @JoinColumn(name = "id_importancia", referencedColumnName = "id_importancia")
    @ManyToOne(optional = true)
    private Importancia idImportancia;

    @JoinColumn(name = "id_items_situacion", referencedColumnName = "id_items_situacion")
    @ManyToOne(optional = true)
    private ItemSituacion idItemSituacion;

    @Column(name = "puntuacion")
    private Integer puntuacion;

    @Column(name = "fortalezas")
    private String fortalezas;
    @Column(name = "debillidades")
    private String debilidades;
    @Column(name = "oportunidades")
    private String oportunidades;
    @Column(name = "amenazas")
    private String amenazas;

    public Factores() {
    }

    public Factores(Integer idFactor) {
        this.idFactor = idFactor;
    }

    public Factores(Integer idFactor, int idProceso, int idCategoria, int idImportancia, int idOportunidad, int idRiesgo) {
        this.idFactor = idFactor;

    }

    public Integer getIdFactor() {
        return idFactor;
    }

    public void setIdFactor(Integer idFactor) {
        this.idFactor = idFactor;
    }

    public String getDescripcionFactor() {
        return descripcionFactor;
    }

    public void setDescripcionFactor(String descripcionFactor) {
        this.descripcionFactor = descripcionFactor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFactor != null ? idFactor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factores)) {
            return false;
        }
        Factores other = (Factores) object;
        if ((this.idFactor == null && other.idFactor != null) || (this.idFactor != null && !this.idFactor.equals(other.idFactor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.Factores[ idFactor=" + idFactor + " ]";
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Oportunidad getIdOportunidad() {
        return idOportunidad;
    }

    public void setIdOportunidad(Oportunidad idOportunidad) {
        this.idOportunidad = idOportunidad;
    }

    public Riesgo getIdRiesgo() {
        return idRiesgo;
    }

    public void setIdRiesgo(Riesgo idRiesgo) {
        this.idRiesgo = idRiesgo;
    }

    public Procesos getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Procesos idProceso) {
        this.idProceso = idProceso;
    }

    public Importancia getIdImportancia() {
        return idImportancia;
    }

    public void setIdImportancia(Importancia idImportancia) {
        this.idImportancia = idImportancia;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public ItemSituacion getIdItemSituacion() {
        return idItemSituacion;
    }

    public void setIdItemSituacion(ItemSituacion idItemSituacion) {
        this.idItemSituacion = idItemSituacion;
    }

    public String getFortalezas() {
        return fortalezas;
    }

    public void setFortalezas(String fortalezas) {
        this.fortalezas = fortalezas;
    }

    public String getDebilidades() {
        return debilidades;
    }

    public void setDebilidades(String debilidades) {
        this.debilidades = debilidades;
    }

    public String getOportunidades() {
        return oportunidades;
    }

    public void setOportunidades(String oportunidades) {
        this.oportunidades = oportunidades;
    }

    public String getAmenazas() {
        return amenazas;
    }

    public void setAmenazas(String amenazas) {
        this.amenazas = amenazas;
    }

}
