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
import javax.persistence.Lob;
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
 * @author Junior Cabal
 */
@Entity
@Table(name = "diagnostico_ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiagnosticoTicket.findAll", query = "SELECT d FROM DiagnosticoTicket d"),
    @NamedQuery(name = "DiagnosticoTicket.findByIdDiagnosticoTicket", query = "SELECT d FROM DiagnosticoTicket d WHERE d.idDiagnosticoTicket = :idDiagnosticoTicket"),
    @NamedQuery(name = "DiagnosticoTicket.findByFechaDiagnostico", query = "SELECT d FROM DiagnosticoTicket d WHERE d.fechaDiagnostico = :fechaDiagnostico")})
public class DiagnosticoTicket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_diagnostico_ticket")
    private Integer idDiagnosticoTicket;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion_diagnostico")
    private String descripcionDiagnostico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_diagnostico")
    @Temporal(TemporalType.DATE)
    private Date fechaDiagnostico;
    @JoinColumn(name = "id_cambio_registro", referencedColumnName = "id_cambio_registro")
    @ManyToOne(optional = false)
    private CambioRegistro idCambioRegistro;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public DiagnosticoTicket() {
    }

    public DiagnosticoTicket(Integer idDiagnosticoTicket) {
        this.idDiagnosticoTicket = idDiagnosticoTicket;
    }

    public DiagnosticoTicket(Integer idDiagnosticoTicket, String descripcionDiagnostico, Date fechaDiagnostico) {
        this.idDiagnosticoTicket = idDiagnosticoTicket;
        this.descripcionDiagnostico = descripcionDiagnostico;
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public Integer getIdDiagnosticoTicket() {
        return idDiagnosticoTicket;
    }

    public void setIdDiagnosticoTicket(Integer idDiagnosticoTicket) {
        this.idDiagnosticoTicket = idDiagnosticoTicket;
    }

    public String getDescripcionDiagnostico() {
        return descripcionDiagnostico;
    }

    public void setDescripcionDiagnostico(String descripcionDiagnostico) {
        this.descripcionDiagnostico = descripcionDiagnostico;
    }

    public Date getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(Date fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public CambioRegistro getIdCambioRegistro() {
        return idCambioRegistro;
    }

    public void setIdCambioRegistro(CambioRegistro idCambioRegistro) {
        this.idCambioRegistro = idCambioRegistro;
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
        hash += (idDiagnosticoTicket != null ? idDiagnosticoTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiagnosticoTicket)) {
            return false;
        }
        DiagnosticoTicket other = (DiagnosticoTicket) object;
        if ((this.idDiagnosticoTicket == null && other.idDiagnosticoTicket != null) || (this.idDiagnosticoTicket != null && !this.idDiagnosticoTicket.equals(other.idDiagnosticoTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.DiagnosticoTicket[ idDiagnosticoTicket=" + idDiagnosticoTicket + " ]";
    }
    
}
