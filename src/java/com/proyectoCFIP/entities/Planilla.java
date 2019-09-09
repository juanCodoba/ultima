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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "planilla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planilla.findAll", query = "SELECT p FROM Planilla p"),
    @NamedQuery(name = "Planilla.findByIdPlanilla", query = "SELECT p FROM Planilla p WHERE p.idPlanilla = :idPlanilla"),
    @NamedQuery(name = "Planilla.findByPvPlanilla", query = "SELECT p FROM Planilla p WHERE p.pvPlanilla = :pvPlanilla"),
    @NamedQuery(name = "Planilla.findByFecha", query = "SELECT p FROM Planilla p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Planilla.findByIdItemPlanilla", query = "SELECT p FROM Planilla p WHERE p.idItemPlanilla = :idItemPlanilla"),
    @NamedQuery(name = "Planilla.findByTalla", query = "SELECT p FROM Planilla p WHERE p.talla = :talla"),
    @NamedQuery(name = "Planilla.findByCantidad", query = "SELECT p FROM Planilla p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Planilla.findByFechaEntrCliente", query = "SELECT p FROM Planilla p WHERE p.fechaEntrCliente = :fechaEntrCliente"),
    @NamedQuery(name = "Planilla.findByFechaProd", query = "SELECT p FROM Planilla p WHERE p.fechaProd = :fechaProd"),
    @NamedQuery(name = "Planilla.findByModuloProd", query = "SELECT p FROM Planilla p WHERE p.moduloProd = :moduloProd"),
    @NamedQuery(name = "Planilla.findByValorSubtotal", query = "SELECT p FROM Planilla p WHERE p.valorSubtotal = :valorSubtotal"),
    @NamedQuery(name = "Planilla.findByCorte", query = "SELECT p FROM Planilla p WHERE p.corte = :corte"),
    @NamedQuery(name = "Planilla.findByFechaCorte", query = "SELECT p FROM Planilla p WHERE p.fechaCorte = :fechaCorte"),
    @NamedQuery(name = "Planilla.findByEstampado", query = "SELECT p FROM Planilla p WHERE p.estampado = :estampado"),
    @NamedQuery(name = "Planilla.findByBordado", query = "SELECT p FROM Planilla p WHERE p.bordado = :bordado"),
    @NamedQuery(name = "Planilla.findByConfeccion", query = "SELECT p FROM Planilla p WHERE p.confeccion = :confeccion"),
    @NamedQuery(name = "Planilla.findByFechaConfeccion", query = "SELECT p FROM Planilla p WHERE p.fechaConfeccion = :fechaConfeccion"),
    @NamedQuery(name = "Planilla.findByUndPendientes", query = "SELECT p FROM Planilla p WHERE p.undPendientes = :undPendientes"),
    @NamedQuery(name = "Planilla.findByIdEstadoConsecPlanilla", query = "SELECT p FROM Planilla p WHERE p.idEstadoConsecPlanilla = :idEstadoConsecPlanilla"),
    @NamedQuery(name = "Planilla.findByIdOpPlanilla", query = "SELECT p FROM Planilla p WHERE p.idOpPlanilla = :idOpPlanilla"),
    @NamedQuery(name = "Planilla.findByIdUsuarioVendedor", query = "SELECT p FROM Planilla p WHERE p.idUsuarioVendedor = :idUsuarioVendedor")})
public class Planilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planilla")
    private Integer idPlanilla;

    @Column(name = "pv_planilla")
    private Integer pvPlanilla;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "id_item_planilla")
    private Integer idItemPlanilla;

    @Size(max = 2147483647)
    @Column(name = "descripcion_planilla")
    private String descripcionPlanilla;

    @Size(max = 45)
    @Column(name = "talla")
    private String talla;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "cantidad_total")
    private Integer cantidadTotal;

    @Column(name = "op")
    private Integer op;

    @Size(max = 45)
    @Column(name = "cliente")
    private String cliente;

    @Size(max = 2147483647)
    @Column(name = "logo")
    private String logo;

    @Size(max = 2147483647)
    @Column(name = "oc")
    private String oc;

    @Column(name = "fecha_entr_cliente")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrCliente;

    @Column(name = "fecha_prod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProd;

    @Size(max = 45)
    @Column(name = "modulo_prod")
    private String moduloProd;

    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "valor_subtotal")
    private Integer valorSubtotal;

    @Column(name = "valor_total")
    private Integer valorTotal;

    @Column(name = "corte")
    private Integer corte;

    @Column(name = "fecha_corte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCorte;

    @Column(name = "estampado")
    private Integer estampado;
    @Column(name = "bordado")
    private Integer bordado;

    @Column(name = "confeccion")
    private Integer confeccion;

    @Column(name = "fecha_confeccion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConfeccion;

    @Column(name = "und_pendientes")
    private Integer undPendientes;

    @JoinColumn(name = "id_estado_consec_planilla", referencedColumnName = "id_estado_consec_planilla")
    @ManyToOne(optional = false)
    private EstadoConsecPlanilla idEstadoConsecPlanilla;

    @JoinColumn(name = "id_op_planilla", referencedColumnName = "id_op_planilla")
    @ManyToOne(optional = false)
    private OpPlanilla idOpPlanilla;

    @JoinColumn(name = "id_usuario_vendedor", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuarioVendedor;

    public Planilla() {
    }

    public Planilla(Integer idPlanilla) {
        this.idPlanilla = idPlanilla;
    }

    public Planilla(Integer idPlanilla, Integer pvPlanilla, Date fecha, Integer idItemPlanilla, String descripcionPlanilla, String talla, Integer cantidad, String logo, String oc, Date fechaEntrCliente, Date fechaProd, String moduloProd, String observaciones, Integer valorSubtotal, Integer corte, Date fechaCorte, Integer estampado, Integer bordado, Integer confeccion, Date fechaConfeccion, Integer undPendientes, EstadoConsecPlanilla idEstadoConsecPlanilla, List<OpPlanilla> opPlanillaList, Usuario idUsuarioVendedor) {
        this.idPlanilla = idPlanilla;
        this.pvPlanilla = pvPlanilla;
        this.fecha = fecha;
        this.idItemPlanilla = idItemPlanilla;
        this.descripcionPlanilla = descripcionPlanilla;
        this.talla = talla;
        this.cantidad = cantidad;
        this.logo = logo;
        this.oc = oc;
        this.fechaEntrCliente = fechaEntrCliente;
        this.fechaProd = fechaProd;
        this.moduloProd = moduloProd;
        this.observaciones = observaciones;
        this.valorSubtotal = valorSubtotal;
        this.corte = corte;
        this.fechaCorte = fechaCorte;
        this.estampado = estampado;
        this.bordado = bordado;
        this.confeccion = confeccion;
        this.fechaConfeccion = fechaConfeccion;
        this.undPendientes = undPendientes;
        this.idEstadoConsecPlanilla = idEstadoConsecPlanilla;
        this.idUsuarioVendedor = idUsuarioVendedor;
    }

    public Integer getOp() {
        return op;
    }

    public void setOp(Integer op) {
        this.op = op;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getIdPlanilla() {
        return idPlanilla;
    }

    public void setIdPlanilla(Integer idPlanilla) {
        this.idPlanilla = idPlanilla;
    }

    public Integer getPvPlanilla() {
        return pvPlanilla;
    }

    public void setPvPlanilla(Integer pvPlanilla) {
        this.pvPlanilla = pvPlanilla;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdItemPlanilla() {
        return idItemPlanilla;
    }

    public void setIdItemPlanilla(Integer idItemPlanilla) {
        this.idItemPlanilla = idItemPlanilla;
    }

    public String getDescripcionPlanilla() {
        return descripcionPlanilla;
    }

    public void setDescripcionPlanilla(String descripcionPlanilla) {
        this.descripcionPlanilla = descripcionPlanilla;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getOc() {
        return oc;
    }

    public void setOc(String oc) {
        this.oc = oc;
    }

    public Date getFechaEntrCliente() {
        return fechaEntrCliente;
    }

    public void setFechaEntrCliente(Date fechaEntrCliente) {
        this.fechaEntrCliente = fechaEntrCliente;
    }

    public Date getFechaProd() {
        return fechaProd;
    }

    public void setFechaProd(Date fechaProd) {
        this.fechaProd = fechaProd;
    }

    public String getModuloProd() {
        return moduloProd;
    }

    public void setModuloProd(String moduloProd) {
        this.moduloProd = moduloProd;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getValorSubtotal() {
        return valorSubtotal;
    }

    public void setValorSubtotal(Integer valorSubtotal) {
        this.valorSubtotal = valorSubtotal;
    }

    public Integer getCorte() {
        return corte;
    }

    public void setCorte(Integer corte) {
        this.corte = corte;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public Integer getEstampado() {
        return estampado;
    }

    public void setEstampado(Integer estampado) {
        this.estampado = estampado;
    }

    public Integer getBordado() {
        return bordado;
    }

    public void setBordado(Integer bordado) {
        this.bordado = bordado;
    }

    public Integer getConfeccion() {
        return confeccion;
    }

    public void setConfeccion(Integer confeccion) {
        this.confeccion = confeccion;
    }

    public Date getFechaConfeccion() {
        return fechaConfeccion;
    }

    public void setFechaConfeccion(Date fechaConfeccion) {
        this.fechaConfeccion = fechaConfeccion;
    }

    public Integer getUndPendientes() {
        return undPendientes;
    }

    public void setUndPendientes(Integer undPendientes) {
        this.undPendientes = undPendientes;
    }

    public EstadoConsecPlanilla getIdEstadoConsecPlanilla() {
        return idEstadoConsecPlanilla;
    }

    public void setIdEstadoConsecPlanilla(EstadoConsecPlanilla idEstadoConsecPlanilla) {
        this.idEstadoConsecPlanilla = idEstadoConsecPlanilla;
    }

    public Usuario getIdUsuarioVendedor() {
        return idUsuarioVendedor;
    }

    public void setIdUsuarioVendedor(Usuario idUsuarioVendedor) {
        this.idUsuarioVendedor = idUsuarioVendedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanilla != null ? idPlanilla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planilla)) {
            return false;
        }
        Planilla other = (Planilla) object;
        if ((this.idPlanilla == null && other.idPlanilla != null) || (this.idPlanilla != null && !this.idPlanilla.equals(other.idPlanilla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PV - " + pvPlanilla;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Integer getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
    }

    public OpPlanilla getIdOpPlanilla() {
        return idOpPlanilla;
    }

    public void setIdOpPlanilla(OpPlanilla idOpPlanilla) {
        this.idOpPlanilla = idOpPlanilla;
    }

}
