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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Carlos Cabal
 */
@Entity
@Table(name = "facturado_au")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacturadoAu.findAll", query = "SELECT f FROM FacturadoAu f"),
    @NamedQuery(name = "FacturadoAu.findByIdfacturadoAU", query = "SELECT f FROM FacturadoAu f WHERE f.idfacturadoAU = :idfacturadoAU"),
    @NamedQuery(name = "FacturadoAu.findByFechaEntregaEmpaqueOld", query = "SELECT f FROM FacturadoAu f WHERE f.fechaEntregaEmpaqueOld = :fechaEntregaEmpaqueOld"),
    @NamedQuery(name = "FacturadoAu.findByFechaDespachoOld", query = "SELECT f FROM FacturadoAu f WHERE f.fechaDespachoOld = :fechaDespachoOld"),
    @NamedQuery(name = "FacturadoAu.findByNCajasOld", query = "SELECT f FROM FacturadoAu f WHERE f.nCajasOld = :nCajasOld"),
    @NamedQuery(name = "FacturadoAu.findByNPaquetesOld", query = "SELECT f FROM FacturadoAu f WHERE f.nPaquetesOld = :nPaquetesOld"),
    @NamedQuery(name = "FacturadoAu.findByCantidadCajaEnviadaOld", query = "SELECT f FROM FacturadoAu f WHERE f.cantidadCajaEnviadaOld = :cantidadCajaEnviadaOld"),
    @NamedQuery(name = "FacturadoAu.findByDireccionEntregaOld", query = "SELECT f FROM FacturadoAu f WHERE f.direccionEntregaOld = :direccionEntregaOld"),
    @NamedQuery(name = "FacturadoAu.findByIdUsuarioOld", query = "SELECT f FROM FacturadoAu f WHERE f.idUsuarioOld = :idUsuarioOld"),
    @NamedQuery(name = "FacturadoAu.findByIdFtClienteOld", query = "SELECT f FROM FacturadoAu f WHERE f.idFtClienteOld = :idFtClienteOld"),
    @NamedQuery(name = "FacturadoAu.findByFechaEntregaEmpaqueNew", query = "SELECT f FROM FacturadoAu f WHERE f.fechaEntregaEmpaqueNew = :fechaEntregaEmpaqueNew"),
    @NamedQuery(name = "FacturadoAu.findByFechaDespachoNew", query = "SELECT f FROM FacturadoAu f WHERE f.fechaDespachoNew = :fechaDespachoNew"),
    @NamedQuery(name = "FacturadoAu.findByNCajasNew", query = "SELECT f FROM FacturadoAu f WHERE f.nCajasNew = :nCajasNew"),
    @NamedQuery(name = "FacturadoAu.findByNPaquetesNew", query = "SELECT f FROM FacturadoAu f WHERE f.nPaquetesNew = :nPaquetesNew"),
    @NamedQuery(name = "FacturadoAu.findByCantidadCajaEnviadaNew", query = "SELECT f FROM FacturadoAu f WHERE f.cantidadCajaEnviadaNew = :cantidadCajaEnviadaNew"),
    @NamedQuery(name = "FacturadoAu.findByIdUsuarioNew", query = "SELECT f FROM FacturadoAu f WHERE f.idUsuarioNew = :idUsuarioNew"),
    @NamedQuery(name = "FacturadoAu.findByIdFtCliente", query = "SELECT f FROM FacturadoAu f WHERE f.idFtCliente = :idFtCliente"),
    @NamedQuery(name = "FacturadoAu.findByFechaCambio", query = "SELECT f FROM FacturadoAu f WHERE f.fechaCambio = :fechaCambio"),
    @NamedQuery(name = "FacturadoAu.findByIdFacturado", query = "SELECT f FROM FacturadoAu f WHERE f.idFacturado = :idFacturado  ORDER BY f.idfacturadoAU DESC"),
    @NamedQuery(name = "FacturadoAu.findByDireccionEntregaNew", query = "SELECT f FROM FacturadoAu f WHERE f.direccionEntregaNew = :direccionEntregaNew")})
public class FacturadoAu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_facturado_AU")
    private Integer idfacturadoAU;
    @Column(name = "fecha_entrega_empaque_old")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntregaEmpaqueOld;
    @Column(name = "fecha_despacho_old")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDespachoOld;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "entregado_por_old")
    private String entregadoPorOld;
    @Column(name = "n_cajas_old")
    private Integer nCajasOld;
    @Column(name = "n_paquetes_old")
    private Integer nPaquetesOld;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "num_factura_old")
    private String numFacturaOld;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "pv_old")
    private String pvOld;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "op_old")
    private String opOld;
    @Column(name = "cantidad_caja_enviada_old")
    private Integer cantidadCajaEnviadaOld;
    @Size(max = 100)
    @Column(name = "direccion_entrega_old")
    private String direccionEntregaOld;
    @Column(name = "id_usuario_old")
    private Integer idUsuarioOld;
    @Column(name = "id_ft_cliente_old")
    private Integer idFtClienteOld;

    @Column(name = "xs_old")
    private Integer xsOld;

    @Column(name = "s_old")
    private Integer tsOld;

    @Column(name = "m_old")
    private Integer tmOld;

    @Column(name = "l_old")
    private Integer tlOld;

    @Column(name = "xl_old")
    private Integer xlOld;

    @Column(name = "xxl_old")
    private Integer xxlOld;

    @Column(name = "xxxl_old")
    private Integer xxxlOld;

    @Column(name = "fecha_entrega_empaque_new")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntregaEmpaqueNew;
    @Column(name = "fecha_despacho_new")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDespachoNew;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "entregado_por_new")
    private String entregadoPorNew;
    @Column(name = "n_cajas_new")
    private Integer nCajasNew;
    @Column(name = "n_paquetes_new")
    private Integer nPaquetesNew;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "num_factura_new")
    private String numFacturaNew;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "pv_new")
    private String pvNew;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "op_new")
    private String opNew;
    @Column(name = "cantidad_caja_enviada_new")
    private Integer cantidadCajaEnviadaNew;
    @JoinColumn(name = "id_facturado", referencedColumnName = "id_facturado")
    @ManyToOne
    private Facturado idFacturado;
    @Column(name = "id_usuario_new")
    private Integer idUsuarioNew;
    @Column(name = "id_ft_cliente")
    private Integer idFtCliente;
    @Column(name = "fecha_cambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambio;
    @Size(max = 100)
    @Column(name = "direccion_entrega_new")
    private String direccionEntregaNew;

    @Column(name = "xs_new")
    private Integer xsNew;

    @Column(name = "s_new")
    private Integer tsNew;

    @Column(name = "m_new")
    private Integer tmNew;

    @Column(name = "l_new")
    private Integer tlNew;

    @Column(name = "xl_new")
    private Integer txlNew;

    @Column(name = "xxl_new")
    private Integer txxlNew;

    @Column(name = "xxxl_new")
    private Integer txxxlNew;
    @Column(name = "id_estado_factura_old")
    private Boolean activo;
        @Column(name = "id_estado_factura_new")
    private Boolean activoNew;

    public FacturadoAu() {
    }

    public FacturadoAu(Integer idfacturadoAU) {
        this.idfacturadoAU = idfacturadoAU;
    }

    public Integer getIdfacturadoAU() {
        return idfacturadoAU;
    }

    public void setIdfacturadoAU(Integer idfacturadoAU) {
        this.idfacturadoAU = idfacturadoAU;
    }

    public Date getFechaEntregaEmpaqueOld() {
        return fechaEntregaEmpaqueOld;
    }

    public void setFechaEntregaEmpaqueOld(Date fechaEntregaEmpaqueOld) {
        this.fechaEntregaEmpaqueOld = fechaEntregaEmpaqueOld;
    }

    public Date getFechaDespachoOld() {
        return fechaDespachoOld;
    }

    public void setFechaDespachoOld(Date fechaDespachoOld) {
        this.fechaDespachoOld = fechaDespachoOld;
    }

    public String getEntregadoPorOld() {
        return entregadoPorOld;
    }

    public void setEntregadoPorOld(String entregadoPorOld) {
        this.entregadoPorOld = entregadoPorOld;
    }

    public Integer getNCajasOld() {
        return nCajasOld;
    }

    public void setNCajasOld(Integer nCajasOld) {
        this.nCajasOld = nCajasOld;
    }

    public Integer getNPaquetesOld() {
        return nPaquetesOld;
    }

    public void setNPaquetesOld(Integer nPaquetesOld) {
        this.nPaquetesOld = nPaquetesOld;
    }

    public String getNumFacturaOld() {
        return numFacturaOld;
    }

    public void setNumFacturaOld(String numFacturaOld) {
        this.numFacturaOld = numFacturaOld;
    }

    public String getPvOld() {
        return pvOld;
    }

    public void setPvOld(String pvOld) {
        this.pvOld = pvOld;
    }

    public String getOpOld() {
        return opOld;
    }

    public void setOpOld(String opOld) {
        this.opOld = opOld;
    }

    public Integer getCantidadCajaEnviadaOld() {
        return cantidadCajaEnviadaOld;
    }

    public void setCantidadCajaEnviadaOld(Integer cantidadCajaEnviadaOld) {
        this.cantidadCajaEnviadaOld = cantidadCajaEnviadaOld;
    }

    public String getDireccionEntregaOld() {
        return direccionEntregaOld;
    }

    public void setDireccionEntregaOld(String direccionEntregaOld) {
        this.direccionEntregaOld = direccionEntregaOld;
    }

    public Integer getIdUsuarioOld() {
        return idUsuarioOld;
    }

    public void setIdUsuarioOld(Integer idUsuarioOld) {
        this.idUsuarioOld = idUsuarioOld;
    }

    public Integer getIdFtClienteOld() {
        return idFtClienteOld;
    }

    public void setIdFtClienteOld(Integer idFtClienteOld) {
        this.idFtClienteOld = idFtClienteOld;
    }

    public Date getFechaEntregaEmpaqueNew() {
        return fechaEntregaEmpaqueNew;
    }

    public void setFechaEntregaEmpaqueNew(Date fechaEntregaEmpaqueNew) {
        this.fechaEntregaEmpaqueNew = fechaEntregaEmpaqueNew;
    }

    public Date getFechaDespachoNew() {
        return fechaDespachoNew;
    }

    public void setFechaDespachoNew(Date fechaDespachoNew) {
        this.fechaDespachoNew = fechaDespachoNew;
    }

    public String getEntregadoPorNew() {
        return entregadoPorNew;
    }

    public void setEntregadoPorNew(String entregadoPorNew) {
        this.entregadoPorNew = entregadoPorNew;
    }

    public Integer getNCajasNew() {
        return nCajasNew;
    }

    public void setNCajasNew(Integer nCajasNew) {
        this.nCajasNew = nCajasNew;
    }

    public Integer getNPaquetesNew() {
        return nPaquetesNew;
    }

    public void setNPaquetesNew(Integer nPaquetesNew) {
        this.nPaquetesNew = nPaquetesNew;
    }

    public String getNumFacturaNew() {
        return numFacturaNew;
    }

    public void setNumFacturaNew(String numFacturaNew) {
        this.numFacturaNew = numFacturaNew;
    }

    public String getPvNew() {
        return pvNew;
    }

    public void setPvNew(String pvNew) {
        this.pvNew = pvNew;
    }

    public String getOpNew() {
        return opNew;
    }

    public void setOpNew(String opNew) {
        this.opNew = opNew;
    }

    public Integer getCantidadCajaEnviadaNew() {
        return cantidadCajaEnviadaNew;
    }

    public void setCantidadCajaEnviadaNew(Integer cantidadCajaEnviadaNew) {
        this.cantidadCajaEnviadaNew = cantidadCajaEnviadaNew;
    }

    public Integer getnCajasOld() {
        return nCajasOld;
    }

    public void setnCajasOld(Integer nCajasOld) {
        this.nCajasOld = nCajasOld;
    }

    public Integer getnPaquetesOld() {
        return nPaquetesOld;
    }

    public void setnPaquetesOld(Integer nPaquetesOld) {
        this.nPaquetesOld = nPaquetesOld;
    }

    public Integer getnCajasNew() {
        return nCajasNew;
    }

    public void setnCajasNew(Integer nCajasNew) {
        this.nCajasNew = nCajasNew;
    }

    public Integer getnPaquetesNew() {
        return nPaquetesNew;
    }

    public void setnPaquetesNew(Integer nPaquetesNew) {
        this.nPaquetesNew = nPaquetesNew;
    }

    public Facturado getIdFacturado() {
        return idFacturado;
    }

    public void setIdFacturado(Facturado idFacturado) {
        this.idFacturado = idFacturado;
    }

    public Integer getIdUsuarioNew() {
        return idUsuarioNew;
    }

    public void setIdUsuarioNew(Integer idUsuarioNew) {
        this.idUsuarioNew = idUsuarioNew;
    }

    public Integer getIdFtCliente() {
        return idFtCliente;
    }

    public void setIdFtCliente(Integer idFtCliente) {
        this.idFtCliente = idFtCliente;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getDireccionEntregaNew() {
        return direccionEntregaNew;
    }

    public void setDireccionEntregaNew(String direccionEntregaNew) {
        this.direccionEntregaNew = direccionEntregaNew;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfacturadoAU != null ? idfacturadoAU.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturadoAu)) {
            return false;
        }
        FacturadoAu other = (FacturadoAu) object;
        if ((this.idfacturadoAU == null && other.idfacturadoAU != null) || (this.idfacturadoAU != null && !this.idfacturadoAU.equals(other.idfacturadoAU))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoCFIP.entities.FacturadoAu[ idfacturadoAU=" + idfacturadoAU + " ]";
    }

    public Integer getXsOld() {
        return xsOld;
    }

    public void setXsOld(Integer xsOld) {
        this.xsOld = xsOld;
    }

    public Integer getXlOld() {
        return xlOld;
    }

    public void setXlOld(Integer xlOld) {
        this.xlOld = xlOld;
    }

    public Integer getXxlOld() {
        return xxlOld;
    }

    public void setXxlOld(Integer xxlOld) {
        this.xxlOld = xxlOld;
    }

    public Integer getXxxlOld() {
        return xxxlOld;
    }

    public void setXxxlOld(Integer xxxlOld) {
        this.xxxlOld = xxxlOld;
    }

    public Integer getXsNew() {
        return xsNew;
    }

    public void setXsNew(Integer xsNew) {
        this.xsNew = xsNew;
    }

    public Integer getTsOld() {
        return tsOld;
    }

    public void setTsOld(Integer tsOld) {
        this.tsOld = tsOld;
    }

    public Integer getTmOld() {
        return tmOld;
    }

    public void setTmOld(Integer tmOld) {
        this.tmOld = tmOld;
    }

    public Integer getTlOld() {
        return tlOld;
    }

    public void setTlOld(Integer tlOld) {
        this.tlOld = tlOld;
    }

    public Integer getTsNew() {
        return tsNew;
    }

    public void setTsNew(Integer tsNew) {
        this.tsNew = tsNew;
    }

    public Integer getTmNew() {
        return tmNew;
    }

    public void setTmNew(Integer tmNew) {
        this.tmNew = tmNew;
    }

    public Integer getTlNew() {
        return tlNew;
    }

    public void setTlNew(Integer tlNew) {
        this.tlNew = tlNew;
    }

    public Integer getTxlNew() {
        return txlNew;
    }

    public void setTxlNew(Integer txlNew) {
        this.txlNew = txlNew;
    }

    public Integer getTxxlNew() {
        return txxlNew;
    }

    public void setTxxlNew(Integer txxlNew) {
        this.txxlNew = txxlNew;
    }

    public Integer getTxxxlNew() {
        return txxxlNew;
    }

    public void setTxxxlNew(Integer txxxlNew) {
        this.txxxlNew = txxxlNew;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getActivoNew() {
        return activoNew;
    }

    public void setActivoNew(Boolean activoNew) {
        this.activoNew = activoNew;
    }
    

    
}
