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
@Table(name = "cronograma_anual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CronogramaAnual.findAll", query = "SELECT c FROM CronogramaAnual c"),
    @NamedQuery(name = "CronogramaAnual.findByIdCronogramaAnual", query = "SELECT c FROM CronogramaAnual c WHERE c.idCronogramaAnual = :idCronogramaAnual"),
    @NamedQuery(name = "CronogramaAnual.findByNombreActividad", query = "SELECT c FROM CronogramaAnual c WHERE c.nombreActividad = :nombreActividad"),
    @NamedQuery(name = "CronogramaAnual.findByEne", query = "SELECT c FROM CronogramaAnual c WHERE c.ene = :ene"),
    @NamedQuery(name = "CronogramaAnual.findByFeb", query = "SELECT c FROM CronogramaAnual c WHERE c.feb = :feb"),
    @NamedQuery(name = "CronogramaAnual.findByMar", query = "SELECT c FROM CronogramaAnual c WHERE c.mar = :mar"),
    @NamedQuery(name = "CronogramaAnual.findByAbr", query = "SELECT c FROM CronogramaAnual c WHERE c.abr = :abr"),
    @NamedQuery(name = "CronogramaAnual.findByMay", query = "SELECT c FROM CronogramaAnual c WHERE c.may = :may"),
    @NamedQuery(name = "CronogramaAnual.findByJun", query = "SELECT c FROM CronogramaAnual c WHERE c.jun = :jun"),
    @NamedQuery(name = "CronogramaAnual.findByJul", query = "SELECT c FROM CronogramaAnual c WHERE c.jul = :jul"),
    @NamedQuery(name = "CronogramaAnual.findBySep", query = "SELECT c FROM CronogramaAnual c WHERE c.sep = :sep"),
    @NamedQuery(name = "CronogramaAnual.findByAgo", query = "SELECT c FROM CronogramaAnual c WHERE c.ago = :ago"),
    @NamedQuery(name = "CronogramaAnual.findByOct", query = "SELECT c FROM CronogramaAnual c WHERE c.oct = :oct"),
    @NamedQuery(name = "CronogramaAnual.findByNov", query = "SELECT c FROM CronogramaAnual c WHERE c.nov = :nov"),
    @NamedQuery(name = "CronogramaAnual.findByDic", query = "SELECT c FROM CronogramaAnual c WHERE c.dic = :dic"),
    @NamedQuery(name = "CronogramaAnual.findByEstado", query = "SELECT c FROM CronogramaAnual c WHERE c.estado = :estado")})
public class CronogramaAnual implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cronograma_anual")
    private Integer idCronogramaAnual;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "nombre_actividad")
    private String nombreActividad;
    @Column(name = "ene")
    private Boolean ene;
    @Column(name = "feb")
    private Boolean feb;
    @Column(name = "mar")
    private Boolean mar;
    @Column(name = "abr")
    private Boolean abr;
    @Column(name = "may")
    private Boolean may;
    @Column(name = "jun")
    private Boolean jun;
    @Column(name = "jul")
    private Boolean jul;
    @Column(name = "sep")
    private Boolean sep;
    @Column(name = "ago")
    private Boolean ago;
    @Column(name = "oct")
    private Boolean oct;
    @Column(name = "nov")
    private Boolean nov;
    @Column(name = "dic")
    private Boolean dic;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public CronogramaAnual() {
    }

    public CronogramaAnual(Integer idCronogramaAnual) {
        this.idCronogramaAnual = idCronogramaAnual;
    }

    public CronogramaAnual(Integer idCronogramaAnual, String nombreActividad, boolean estado) {
        this.idCronogramaAnual = idCronogramaAnual;
        this.nombreActividad = nombreActividad;
        this.estado = estado;
    }

    public Integer getIdCronogramaAnual() {
        return idCronogramaAnual;
    }

    public void setIdCronogramaAnual(Integer idCronogramaAnual) {
        this.idCronogramaAnual = idCronogramaAnual;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public Boolean getEne() {
        return ene;
    }

    public void setEne(Boolean ene) {
        this.ene = ene;
    }

    public Boolean getFeb() {
        return feb;
    }

    public void setFeb(Boolean feb) {
        this.feb = feb;
    }

    public Boolean getMar() {
        return mar;
    }

    public void setMar(Boolean mar) {
        this.mar = mar;
    }

    public Boolean getAbr() {
        return abr;
    }

    public void setAbr(Boolean abr) {
        this.abr = abr;
    }

    public Boolean getMay() {
        return may;
    }

    public void setMay(Boolean may) {
        this.may = may;
    }

    public Boolean getJun() {
        return jun;
    }

    public void setJun(Boolean jun) {
        this.jun = jun;
    }

    public Boolean getJul() {
        return jul;
    }

    public void setJul(Boolean jul) {
        this.jul = jul;
    }

    public Boolean getSep() {
        return sep;
    }

    public void setSep(Boolean sep) {
        this.sep = sep;
    }

    public Boolean getAgo() {
        return ago;
    }

    public void setAgo(Boolean ago) {
        this.ago = ago;
    }

    public Boolean getOct() {
        return oct;
    }

    public void setOct(Boolean oct) {
        this.oct = oct;
    }

    public Boolean getNov() {
        return nov;
    }

    public void setNov(Boolean nov) {
        this.nov = nov;
    }

    public Boolean getDic() {
        return dic;
    }

    public void setDic(Boolean dic) {
        this.dic = dic;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCronogramaAnual != null ? idCronogramaAnual.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CronogramaAnual)) {
            return false;
        }
        CronogramaAnual other = (CronogramaAnual) object;
        if ((this.idCronogramaAnual == null && other.idCronogramaAnual != null) || (this.idCronogramaAnual != null && !this.idCronogramaAnual.equals(other.idCronogramaAnual))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.CronogramaAnual[ idCronogramaAnual=" + idCronogramaAnual + " ]";
    }
    public String getEnero(){
        if(ene == false){
            return " ";
        }else{
            return "X";
        }
    }
    public String getFebrero(){
        if(feb == false){
            return " ";
        }else{
            return "X";
        }
    } 
    public String getMarzo(){
        if(mar == false){
            return " ";
        }else{
            return "X";
        }
    } 
    public String getAbril(){
        if(abr == false){
            return " ";
        }else{
            return "X";
        }
    } 
    public String getMayo(){
        if(may == false){
            return " ";
        }else{
            return "X";
        }
    } 
    public String getJunio(){
        if(jun == false){
            return " ";
        }else{
            return "X";
        }
    } 
    public String getJulio(){
        if(jul == false){
            return " ";
        }else{
            return "X";
        }
    } 
    public String getAgosto(){
        if(ago == false){
            return " ";
        }else{
            return "X";
        }
    } 
    public String getSeptiembre(){
        if(sep == false){
            return " ";
        }else{
            return "X";
        }
    } 
    public String getOctubre(){
        if(oct == false){
            return " ";
        }else{
            return "X";
        }
    } 
    public String getNoviembre(){
        if(nov == false){
            return " ";
        }else{
            return "X";
        }
    } 
    public String getDiciembre(){
        if(dic == false){
            return " ";
        }else{
            return "X";
        }
    } 
    public String getEstadoString(){
        if(estado == true){
            return "Terminado";
        }else{
            return "Sin terminar";
        }
    } 

    
}
