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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "op_planilla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpPlanilla.findAll", query = "SELECT o FROM OpPlanilla o"),
    @NamedQuery(name = "OpPlanilla.findByIdOpPlanilla", query = "SELECT o FROM OpPlanilla o WHERE o.idOpPlanilla = :idOpPlanilla"),
    @NamedQuery(name = "OpPlanilla.findByOpPlanilla", query = "SELECT o FROM OpPlanilla o WHERE o.opPlanilla = :opPlanilla"),
    @NamedQuery(name = "OpPlanilla.findByIdFtCliente", query = "SELECT o FROM OpPlanilla o WHERE o.idFtCliente = :idFtCliente")})
public class OpPlanilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_op_planilla")
    private Integer idOpPlanilla;
    @NotNull
    @Column(name = "op_planilla")
    private Integer opPlanilla;
    @JoinColumn(name = "id_ft_cliente", referencedColumnName = "id_ft_cliente")
    @ManyToOne(optional = false)
    private FtCliente idFtCliente;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuarioAsig;


    @OneToMany(mappedBy = "idOpPlanilla", cascade = CascadeType.PERSIST)
    private List<Planilla> planillaList;

    public OpPlanilla() {
    }

    public OpPlanilla(Integer idOpPlanilla) {
        this.idOpPlanilla = idOpPlanilla;
    }

    public OpPlanilla(Integer idOpPlanilla, Integer opPlanilla) {
        this.idOpPlanilla = idOpPlanilla;
        this.opPlanilla = opPlanilla;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIdOpPlanilla() {
        return idOpPlanilla;
    }

    public void setIdOpPlanilla(Integer idOpPlanilla) {
        this.idOpPlanilla = idOpPlanilla;
    }

    public Integer getOpPlanilla() {
        return opPlanilla;
    }

    public void setOpPlanilla(Integer opPlanilla) {
        this.opPlanilla = opPlanilla;
    }

    public FtCliente getIdFtCliente() {
        return idFtCliente;
    }

    public void setIdFtCliente(FtCliente idFtCliente) {
        this.idFtCliente = idFtCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpPlanilla != null ? idOpPlanilla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpPlanilla)) {
            return false;
        }
        OpPlanilla other = (OpPlanilla) object;
        if ((this.idOpPlanilla == null && other.idOpPlanilla != null) || (this.idOpPlanilla != null && !this.idOpPlanilla.equals(other.idOpPlanilla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.OpPlanilla[ idOpPlanilla=" + idOpPlanilla + " ]";
    }

    public List<Planilla> getPlanillaList() {
        return planillaList;
    }

    public void setPlanillaList(List<Planilla> planillaList) {
        this.planillaList = planillaList;
    }

    public Usuario getIdUsuarioAsig() {
        return idUsuarioAsig;
    }

    public void setIdUsuarioAsig(Usuario idUsuarioAsig) {
        this.idUsuarioAsig = idUsuarioAsig;
    }
    
    

}
