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
import javax.persistence.Lob;
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
@Table(name = "cambio_registro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CambioRegistro.findAll", query = "SELECT c FROM CambioRegistro c"),
    @NamedQuery(name = "CambioRegistro.findByIdCambioRegistro", query = "SELECT c FROM CambioRegistro c WHERE c.idCambioRegistro = :idCambioRegistro"),
    @NamedQuery(name = "CambioRegistro.findByFechaCambio", query = "SELECT c FROM CambioRegistro c WHERE c.fechaCambio = :fechaCambio"),
    @NamedQuery(name = "CambioRegistro.findByEstadoCambio", query = "SELECT c FROM CambioRegistro c WHERE c.estadoCambio = :estadoCambio")})
public class CambioRegistro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cambio_registro")
    private Integer idCambioRegistro;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion_cambio")
    private String descripcionCambio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_cambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_cambio")
    private boolean estadoCambio;
    @JoinColumn(name = "id_formato", referencedColumnName = "id_formato")
    @ManyToOne(optional = false)
    private Formatos idFormato;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCambioRegistro")
    private List<DiagnosticoTicket> diagnosticoTicketList;
    @Lob
    @Column(name = "documento_demo")
    private byte[] documentoDemo;

    public CambioRegistro() {
    }

    public CambioRegistro(Integer idCambioRegistro) {
        this.idCambioRegistro = idCambioRegistro;
    }

    public CambioRegistro(Integer idCambioRegistro, String descripcionCambio, Date fechaCambio, boolean estadoCambio) {
        this.idCambioRegistro = idCambioRegistro;
        this.descripcionCambio = descripcionCambio;
        this.fechaCambio = fechaCambio;
        this.estadoCambio = estadoCambio;
    }

    public Integer getIdCambioRegistro() {
        return idCambioRegistro;
    }

    public void setIdCambioRegistro(Integer idCambioRegistro) {
        this.idCambioRegistro = idCambioRegistro;
    }

    public String getDescripcionCambio() {
        return descripcionCambio;
    }

    public void setDescripcionCambio(String descripcionCambio) {
        this.descripcionCambio = descripcionCambio;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public boolean getEstadoCambio() {
        return estadoCambio;
    }

    public void setEstadoCambio(boolean estadoCambio) {
        this.estadoCambio = estadoCambio;
    }

    public Formatos getIdFormato() {
        return idFormato;
    }

    public void setIdFormato(Formatos idFormato) {
        this.idFormato = idFormato;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
     public byte[] getDocumentoDemo() {
        return documentoDemo;
    }

    public void setDocumentoDemo(byte[] documentoDemo) {
        this.documentoDemo = documentoDemo;
    }
    @XmlTransient
    public List<DiagnosticoTicket> getDiagnosticoTicketList() {
        return diagnosticoTicketList;
    }

    public void setDiagnosticoTicketList(List<DiagnosticoTicket> diagnosticoTicketList) {
        this.diagnosticoTicketList = diagnosticoTicketList;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCambioRegistro != null ? idCambioRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CambioRegistro)) {
            return false;
        }
        CambioRegistro other = (CambioRegistro) object;
        if ((this.idCambioRegistro == null && other.idCambioRegistro != null) || (this.idCambioRegistro != null && !this.idCambioRegistro.equals(other.idCambioRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CambioRegistro[ idCambioRegistro=" + idCambioRegistro + " ]";
    }
    
}
