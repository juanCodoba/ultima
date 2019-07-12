/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
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
@Table(name = "facturado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facturado.findAll", query = "SELECT f FROM Facturado f ORDER BY f.idFacturado DESC"),
    @NamedQuery(name = "Facturado.findByIdFacturado", query = "SELECT f FROM Facturado f WHERE f.idFacturado = :idFacturado"),
    @NamedQuery(name = "Facturado.findByFechaEntregaEmpaque", query = "SELECT f FROM Facturado f WHERE f.fechaEntregaEmpaque = :fechaEntregaEmpaque"),
    @NamedQuery(name = "Facturado.findByFechaDespacho", query = "SELECT f FROM Facturado f WHERE f.fechaDespacho = :fechaDespacho"),
    @NamedQuery(name = "Facturado.findByNCajas", query = "SELECT f FROM Facturado f WHERE f.nCajas = :nCajas"),
    @NamedQuery(name = "Facturado.findByNPaquetes", query = "SELECT f FROM Facturado f WHERE f.nPaquetes = :nPaquetes"),
    @NamedQuery(name = "Facturado.findByCantidadCaja", query = "SELECT f FROM Facturado f WHERE f.cantidadCaja = :cantidadCaja"),
    @NamedQuery(name = "Facturado.findByDireccionEntrega", query = "SELECT f FROM Facturado f WHERE f.direccionEntrega = :direccionEntrega"),
    @NamedQuery(name = "Facturado.findByIdUsuario", query = "SELECT f FROM Facturado f WHERE f.idUsuario = :idUsuario"),
    @NamedQuery(name = "Facturado.findByReporteTiempoCorrectivo", query = "SELECT f FROM Facturado f WHERE  f.fechaDespacho BETWEEN :fecha1 AND :fecha2 ORDER BY f.idFacturado DESC"),

    @NamedQuery(name = "Facturado.findByIdFtCliente", query = "SELECT f FROM Facturado f WHERE f.idFtCliente = :idFtCliente")})
public class Facturado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_facturado")
    private Integer idFacturado;
    @Column(name = "fecha_entrega_empaque")
    @Temporal(TemporalType.DATE)
    private Date fechaEntregaEmpaque;
    @Column(name = "fecha_despacho")
    @Temporal(TemporalType.DATE)
    private Date fechaDespacho;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "entregado_por")
    private String entregadoPor;
    @Column(name = "n_cajas")
    private Integer nCajas;
    @Column(name = "n_paquetes")
    private Integer nPaquetes;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "num_factura")
    private String numFactura;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "pv")
    private String pv;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "op")
    private String op;

    @Column(name = "xs")
    private Integer xs;

    @Column(name = "s")
    private Integer s;

    @Column(name = "m")
    private Integer m;

    @Column(name = "l")
    private Integer l;

    @Column(name = "xl")
    private Integer xl;

    @Column(name = "xxl")
    private Integer xxl;

    @Column(name = "xxxl")
    private Integer xxxl;
    @Column(name = "cantidad_caja_enviada")
    private Integer cantidadCaja;
    @Size(max = 100)
    @Column(name = "direccion_entrega")
    private String direccionEntrega;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "id_ft_cliente", referencedColumnName = "id_ft_cliente")
    @ManyToOne
    private FtCliente idFtCliente;
    @OneToMany(mappedBy = "idFacturado")
    private List<FacturadoAu> facturadoAuList;

    @JoinColumn(name = "id_tipo_carga", referencedColumnName = "id_tipo_carga")
    @ManyToOne(optional = false)
    private TipoCarga idTipoCarga;

    @JoinColumn(name = "id_estado_factura", referencedColumnName = "id_estado_factura")
    @ManyToOne
    private EstadoFactura idEstadoFactura;

    public Facturado() {
    }

    public Facturado(Integer idFacturado) {
        this.idFacturado = idFacturado;
    }

    public Facturado(Integer idFacturado, int idUsuario) {
        this.idFacturado = idFacturado;
    }

    public Integer getIdFacturado() {
        return idFacturado;
    }

    public void setIdFacturado(Integer idFacturado) {
        this.idFacturado = idFacturado;
    }

    public Date getFechaEntregaEmpaque() {
        return fechaEntregaEmpaque;
    }

    public String getFechaEntregaEmpaqueMos() {
        return fechaEntregaEmpaque.toString();
    }

    public void setFechaEntregaEmpaque(Date fechaEntregaEmpaque) {
        this.fechaEntregaEmpaque = fechaEntregaEmpaque;
    }

    public Date getFechaDespacho() {

        return fechaDespacho;
    }

    public String getFechaDespachoMos() {

        return fechaDespacho.toString();
    }

    public void setFechaDespacho(Date fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public String getEntregadoPor() {
        return entregadoPor;
    }

    public void setEntregadoPor(String entregadoPor) {
        this.entregadoPor = entregadoPor;
    }

    public Integer getNCajas() {
        return nCajas;
    }

    public void setNCajas(Integer nCajas) {
        this.nCajas = nCajas;
    }

    public Integer getNPaquetes() {
        return nPaquetes;
    }

    public void setNPaquetes(Integer nPaquetes) {
        this.nPaquetes = nPaquetes;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Integer getCantidadCaja() {
        return cantidadCaja;
    }

    public void setCantidadCaja(Integer cantidadCaja) {
        this.cantidadCaja = cantidadCaja;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public Integer getnCajas() {
        return nCajas;
    }

    public void setnCajas(Integer nCajas) {
        this.nCajas = nCajas;
    }

    public Integer getnPaquetes() {
        return nPaquetes;
    }

    public void setnPaquetes(Integer nPaquetes) {
        this.nPaquetes = nPaquetes;
    }

    public FtCliente getIdFtCliente() {
        return idFtCliente;
    }

    public void setIdFtCliente(FtCliente idFtCliente) {
        this.idFtCliente = idFtCliente;
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
        hash += (idFacturado != null ? idFacturado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturado)) {
            return false;
        }
        Facturado other = (Facturado) object;
        if ((this.idFacturado == null && other.idFacturado != null) || (this.idFacturado != null && !this.idFacturado.equals(other.idFacturado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return +getIdFacturado() + "-FAC";
    }

    public List<FacturadoAu> getFacturadoAuList() {
        return facturadoAuList;
    }

    public void setFacturadoAuList(List<FacturadoAu> facturadoAuList) {
        this.facturadoAuList = facturadoAuList;
    }

    public EstadoFactura getIdEstadoFactura() {
        return idEstadoFactura;
    }

    public void setIdEstadoFactura(EstadoFactura idEstadoFactura) {
        this.idEstadoFactura = idEstadoFactura;
    }

    public Integer getXs() {
        return xs;
    }

    public void setXs(Integer xs) {
        this.xs = xs;
    }

    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }

    public Integer getM() {
        return m;
    }

    public void setM(Integer m) {
        this.m = m;
    }

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    public Integer getXl() {
        return xl;
    }

    public void setXl(Integer xl) {
        this.xl = xl;
    }

    public Integer getXxl() {
        return xxl;
    }

    public void setXxl(Integer xxl) {
        this.xxl = xxl;
    }

    public Integer getXxxl() {
        return xxxl;
    }

    public void setXxxl(Integer xxxl) {
        this.xxxl = xxxl;
    }

    public TipoCarga getIdTipoCarga() {
        return idTipoCarga;
    }

    public void setIdTipoCarga(TipoCarga idTipoCarga) {
        this.idTipoCarga = idTipoCarga;
    }

}
