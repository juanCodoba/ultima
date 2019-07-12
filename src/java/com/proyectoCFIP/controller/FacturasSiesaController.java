/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.ActividadNovedad;
import com.proyectoCFIP.entities.Factores;
import com.proyectoCFIP.entities.NovedadFacturacion;
import com.proyectoCFIP.entities.Presupuesto;
import com.proyectoCFIP.entities.Procesos;
import com.proyectoCFIP.sessions.NovedadFacturacionFacade;
import com.proyectoCFIP.sessions.PresupuestoFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.push.annotation.PushEndpoint;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class FacturasSiesaController implements Serializable {

    public Presupuesto presupuestoActual;
    public PresupuestoFacade presupuestoFacade;
    public List<Presupuesto> presupuestoList;

    public NovedadFacturacion novedadFacturacionActual;
    public NovedadFacturacionFacade novedadFacturacionFacade;
    public List<FacturasSiesaController> novedadFacturacionList;
    public List<FacturasSiesaController> novedadFacturacionRedondeoList;
    public List<FacturasSiesaController> novedadFacturacionGraficaList;

    //listado por seleccion de mes 
    public List<FacturasSiesaController> listaT350Prueba;
    public List<FacturasSiesaController> listaT350PruebatREC;
    //
    public List<FacturasSiesaController> listaT350PruebaDos;
    public List<FacturasSiesaController> listaT350Prueba23;

    public List<FacturasSiesaController> listaT350PruebatREC23;
    public List<FacturasSiesaController> listaT350Anual;
    public List<FacturasSiesaController> listaT350PruebaDos23;
    public List<FacturasSiesaController> listaT350PruebatREC24;
    //Diario Lista
    public List<FacturasSiesaController> listaT350ReporteDiario;
    public List<FacturasSiesaController> listaT350ReporteDiarioGrafica;
    public List<FacturasSiesaController> listaT350ReporteMensual;
    public List<FacturasSiesaController> listaT350ReporteMensualTotal;

    //Diario Lista Extension
    public List<FacturasSiesaController> listaT350ReporteDiarioExtension;
    public List<FacturasSiesaController> listaT350ReporteDiarioExtensionGrafica;
    public List<FacturasSiesaController> listaT350ReporteDiarioExtensionGraficap;
    public List<FacturasSiesaController> listaT350ReporteAnualGraficas;
    public List<FacturasSiesaController> listaT350ReporteDiaAnterior;
    public List<FacturasSiesaController> listaT350ReporteAnualExt;
    public List<FacturasSiesaController> listaT350ReporteActualExt;
    public List<FacturasSiesaController> listaT350ReporteAnteriorExt;
    public List<FacturasSiesaController> listaT350ReporteAnteriorExtClientes;
    public List<FacturasSiesaController> listaT350ReporteAnteriorExtClientesTotal;

    //Diario Lista Confecciones 
    public List<FacturasSiesaController> listaT350ReporteDiarioConfecciones;
    public List<FacturasSiesaController> listaT350ReporteDiarioConfeccionesGraficas;
    public List<FacturasSiesaController> listaT350ReporteDiarioConfeccionesClientes;
    public List<FacturasSiesaController> listaT350ReporteDiarioConfeccionesClientesTotal;
    public List<FacturasSiesaController> listaTranscursoConfecciones;
    public List<FacturasSiesaController> listaTranscursoConfeccionesGrafica;

    //Diario Lista BigBag 
    public List<FacturasSiesaController> listaT350ReporteDiarioBigBag;
    public List<FacturasSiesaController> listaT350ReporteDiarioBigBagGraficas;
    public List<FacturasSiesaController> listaT350ReporteDiarioBigBagClientes;
    public List<FacturasSiesaController> listaT350ReporteDiarioBigBagClientesTotal;
    public List<FacturasSiesaController> listaTranscursoBigbag;
    public List<FacturasSiesaController> listaTranscursoBigBagGrafica;

    //Diario Lista FTDH 
    public List<FacturasSiesaController> listaT350ReporteDiarioFTDH;
    public List<FacturasSiesaController> listaT350ReporteDiarioFTDHGraficas;
    public List<FacturasSiesaController> listaT350ReporteAnualFTDH;
    public List<FacturasSiesaController> listaT350ReporteActualFTDH;
    public List<FacturasSiesaController> listaT350ReporteAnteriorFTDH;
    public List<FacturasSiesaController> listaT350ReporteAnteriorFTDHClientes;
    public List<FacturasSiesaController> listaT350ReporteAnteriorFTDHClientesTotal;

    //Diario talleres industriales lista
    public List<FacturasSiesaController> listaT350ReporteAnteriorTallerIndustrialClientes;
    public List<FacturasSiesaController> listaT350ReporteAnteriorTallerIndustrialClientesTotal;

    //Dia actual Lista
    public List<FacturasSiesaController> listaT350ReporteDiaActual;
    public List<FacturasSiesaController> listaT350ReporteDiaActualGrafica;

    //Dia anterior remisiones
    public List<FacturasSiesaController> listaT350ReporteDiaAnteriorRemi;
    public List<FacturasSiesaController> listaT350ReporteDiaActualRemi;

    //Pendientes
    public List<FacturasSiesaController> listaPendientes;
    public List<FacturasSiesaController> listaPendientesFiltro;
    public List<FacturasSiesaController> listaPendientesTotal;

    FacturasSiesaController itemF350coDoctoContableActual;

    private Date fecha3 = new java.util.Date();
    private Date fecha4 = new java.util.Date();
    private Date fecha5;

    public Integer totalExtencion = 86400000;
    public Integer totalFTDH = 136989022;
    public Integer totalBigBag = 83158498;
    public Integer totalConfecciones = 1900000000;

    private Integer f350Rowid;

    private int f350ConsecDocto;
    private String f350OrdenCompra;

    private String f350Prefijo;

    private String f350IdSucursal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    private Integer Atrazo;

    private String cliente;
    private String descrip;
    private Integer consecutivo;
    private Date fecha;

    private String meses;

    private String notas;
    private BigDecimal total;
    private String Bodega;

    private Date fecha1;
    private Date fecha2;
    private BigDecimal f350TotalCr;

    private short f350IndEstado;

    private short f350IndTransmit;

    private Date f350FechaTsCreacion;

    private Date f350FechaTsActualizacion;

    private Date f350FechaTsAprobacion;

    private Date f350FechaTsAnulacion;

    private String f350UsuarioCreacion;

    private String f350UsuarioActualizacion;

    private String f350UsuarioAprobacion;

    private String f350UsuarioAnulacion;

    private BigDecimal f350TotalBaseGravable;

    private short f350IndImpresion;

    private short f350NroImpresiones;

    private Date f350FechaTsHabilitaImp;

    private String f350UsuarioHabilitaImp;

    private String f350Notas;

    private String f350Referencia;

    private Integer f350RowidMovtoEntidad;

    private short f350IndFormaConv;

    private BigDecimal f350TasaConv;

    private short f350IndFormaLocal;

    private BigDecimal f350TasaLocal;

    private short f350IndCfd;

    private String f350UsuarioImpresion;

    private Date f350FechaTsImpresion;

    private BigDecimal f350TotalDb2;

    private BigDecimal f350TotalCr2;

    private BigDecimal f350TotalDb3;

    private BigDecimal f350TotalCr3;

    private Integer id;

    private BigDecimal novedadReNiff;

    private BigDecimal novedadNoReNiff;

    private BigDecimal ValorReConfecciones;

    private BigDecimal ValorVisConfecciones;

    private short f350IndImptoAsumido;

    private short f350IndTipoOrigen;

    private short f350IndDifCambioForma;

    public FacturasSiesaController() {

    }

    public BigDecimal getValorVisConfecciones() {
        return ValorVisConfecciones;
    }

    public void setValorVisConfecciones(BigDecimal ValorVisConfecciones) {
        this.ValorVisConfecciones = ValorVisConfecciones;
    }

    public List<FacturasSiesaController> getNovedadFacturacionRedondeoList() {
        return novedadFacturacionRedondeoList;
    }

    public void setNovedadFacturacionRedondeoList(List<FacturasSiesaController> novedadFacturacionRedondeoList) {
        this.novedadFacturacionRedondeoList = novedadFacturacionRedondeoList;
    }

    public List<FacturasSiesaController> getNovedadFacturacionGraficaList() {
        return novedadFacturacionGraficaList;
    }

    public void setNovedadFacturacionGraficaList(List<FacturasSiesaController> novedadFacturacionGraficaList) {
        this.novedadFacturacionGraficaList = novedadFacturacionGraficaList;
    }

    public BigDecimal getValorReConfecciones() {
        return ValorReConfecciones;
    }

    public void setValorReConfecciones(BigDecimal ValorReConfecciones) {
        this.ValorReConfecciones = ValorReConfecciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getNovedadReNiff() {
        return novedadReNiff;
    }

    public void setNovedadReNiff(BigDecimal novedadReNiff) {
        this.novedadReNiff = novedadReNiff;
    }

    public BigDecimal getNovedadNoReNiff() {
        return novedadNoReNiff;
    }

    public void setNovedadNoReNiff(BigDecimal novedadNoReNiff) {
        this.novedadNoReNiff = novedadNoReNiff;
    }

    public NovedadFacturacion getNovedadFacturacionActual() {
        return novedadFacturacionActual;
    }

    public void setNovedadFacturacionActual(NovedadFacturacion novedadFacturacionActual) {
        this.novedadFacturacionActual = novedadFacturacionActual;
    }

    public NovedadFacturacionFacade getNovedadFacturacionFacade() {
        return novedadFacturacionFacade;
    }

    public void setNovedadFacturacionFacade(NovedadFacturacionFacade novedadFacturacionFacade) {
        this.novedadFacturacionFacade = novedadFacturacionFacade;
    }

    public List<FacturasSiesaController> getNovedadFacturacionList() {
        return novedadFacturacionList;
    }

    public void setNovedadFacturacionList(List<FacturasSiesaController> novedadFacturacionList) {
        this.novedadFacturacionList = novedadFacturacionList;
    }

    public List<FacturasSiesaController> getListaT350Anual() {
        return listaT350Anual;
    }

    public void setListaT350Anual(List<FacturasSiesaController> listaT350Anual) {
        this.listaT350Anual = listaT350Anual;
    }

    public String getMeses() {
        return meses;
    }

    public void setMeses(String meses) {
        this.meses = meses;
    }

    public List<FacturasSiesaController> getListaTranscursoConfeccionesGrafica() {
        return listaTranscursoConfeccionesGrafica;
    }

    public void setListaTranscursoConfeccionesGrafica(List<FacturasSiesaController> listaTranscursoConfeccionesGrafica) {
        this.listaTranscursoConfeccionesGrafica = listaTranscursoConfeccionesGrafica;
    }

    public List<FacturasSiesaController> getListaTranscursoConfecciones() {
        return listaTranscursoConfecciones;
    }

    public void setListaTranscursoConfecciones(List<FacturasSiesaController> listaTranscursoConfecciones) {
        this.listaTranscursoConfecciones = listaTranscursoConfecciones;
    }

    public List<FacturasSiesaController> getListaPendientesFiltro() {
        return listaPendientesFiltro;
    }

    public void setListaPendientesFiltro(List<FacturasSiesaController> listaPendientesFiltro) {
        this.listaPendientesFiltro = listaPendientesFiltro;
    }

    public String getF350OrdenCompra() {
        return f350OrdenCompra;
    }

    public void setF350OrdenCompra(String f350OrdenCompra) {
        this.f350OrdenCompra = f350OrdenCompra;
    }

    public Calendar getFecha6() {
        Calendar fecha6 = Calendar.getInstance();
        fecha6.add(Calendar.HOUR_OF_DAY, -2);
        return fecha6;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiaActualRemi() {
        return listaT350ReporteDiaActualRemi;
    }

    public void setListaT350ReporteDiaActualRemi(List<FacturasSiesaController> listaT350ReporteDiaActualRemi) {
        this.listaT350ReporteDiaActualRemi = listaT350ReporteDiaActualRemi;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiaAnteriorRemi() {
        return listaT350ReporteDiaAnteriorRemi;
    }

    public void setListaT350ReporteDiaAnteriorRemi(List<FacturasSiesaController> listaT350ReporteDiaAnteriorRemi) {
        this.listaT350ReporteDiaAnteriorRemi = listaT350ReporteDiaAnteriorRemi;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiarioConfeccionesClientes() {
        return listaT350ReporteDiarioConfeccionesClientes;
    }

    public void setListaT350ReporteDiarioConfeccionesClientes(List<FacturasSiesaController> listaT350ReporteDiarioConfeccionesClientes) {
        this.listaT350ReporteDiarioConfeccionesClientes = listaT350ReporteDiarioConfeccionesClientes;
    }

    public void setTotalFTDH(Integer totalFTDH) {
        this.totalFTDH = totalFTDH;
    }

    public Integer getTotalBigBag() {
        return totalBigBag;
    }

    public void setTotalBigBag(Integer totalBigBag) {
        this.totalBigBag = totalBigBag;
    }

    public Integer getTotalConfecciones() {
        return totalConfecciones;
    }

    public void setTotalConfecciones(Integer totalConfecciones) {
        this.totalConfecciones = totalConfecciones;
    }

    public Integer getTotalExtencion() {

        return totalExtencion;
    }

    public Presupuesto getPresupuestoActual() {
        return presupuestoActual;
    }

    public void setPresupuestoActual(Presupuesto presupuestoActual) {
        this.presupuestoActual = presupuestoActual;
    }

    public void setTotalExtencion(Integer totalExtencion) {
        this.totalExtencion = totalExtencion;
    }

    public PresupuestoFacade getPresupuestoFacade() {
        return presupuestoFacade;
    }

    public void setPresupuestoFacade(PresupuestoFacade presupuestoFacade) {
        this.presupuestoFacade = presupuestoFacade;
    }

    public List<Presupuesto> getPresupuestoList() {
        return presupuestoList;
    }

    public void setPresupuestoList(List<Presupuesto> presupuestoList) {
        this.presupuestoList = presupuestoList;
    }

    public Date getFecha4() {
        return fecha4;
    }

    public void setFecha4(Date fecha4) {
        this.fecha4 = fecha4;
    }

    public Date getFecha3() {
        return fecha3;
    }

    public void setFecha3(Date fecha3) {
        this.fecha3 = fecha3;
    }

    public Date getFecha5() {
        fecha5 = new Date(fecha5.getTime() + TimeUnit.DAYS.toMillis(-1));
        return fecha5;
    }

    public void setFecha5(Date fecha5) {
        this.fecha5 = fecha5;
    }

    public List<FacturasSiesaController> getListaT350ReporteMensual() {
        return listaT350ReporteMensual;
    }

    public void setListaT350ReporteMensual(List<FacturasSiesaController> listaT350ReporteMensual) {
        this.listaT350ReporteMensual = listaT350ReporteMensual;
    }

    public List<FacturasSiesaController> getListaT350PruebatREC24() {
        return listaT350PruebatREC24;
    }

    public void setListaT350PruebatREC24(List<FacturasSiesaController> listaT350PruebatREC24) {
        this.listaT350PruebatREC24 = listaT350PruebatREC24;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiaActual() {
        return listaT350ReporteDiaActual;
    }

    public void setListaT350ReporteDiaActual(List<FacturasSiesaController> listaT350ReporteDiaActual) {
        this.listaT350ReporteDiaActual = listaT350ReporteDiaActual;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiarioExtensionGraficap() {
        return listaT350ReporteDiarioExtensionGraficap;
    }

    public void setListaT350ReporteDiarioExtensionGraficap(List<FacturasSiesaController> listaT350ReporteDiarioExtensionGraficap) {
        this.listaT350ReporteDiarioExtensionGraficap = listaT350ReporteDiarioExtensionGraficap;
    }

    public List<FacturasSiesaController> getListaT350ReporteAnualGraficas() {
        return listaT350ReporteAnualGraficas;
    }

    public void setListaT350ReporteAnualGraficas(List<FacturasSiesaController> listaT350ReporteAnualGraficas) {
        this.listaT350ReporteAnualGraficas = listaT350ReporteAnualGraficas;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiarioExtension() {
        return listaT350ReporteDiarioExtension;
    }

    public void setListaT350ReporteDiarioExtension(List<FacturasSiesaController> listaT350ReporteDiarioExtension) {
        this.listaT350ReporteDiarioExtension = listaT350ReporteDiarioExtension;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiarioExtensionGrafica() {
        return listaT350ReporteDiarioExtensionGrafica;
    }

    public void setListaT350ReporteDiarioExtensionGrafica(List<FacturasSiesaController> listaT350ReporteDiarioExtensionGrafica) {
        this.listaT350ReporteDiarioExtensionGrafica = listaT350ReporteDiarioExtensionGrafica;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiarioGrafica() {
        return listaT350ReporteDiarioGrafica;
    }

    public void setListaT350ReporteDiarioGrafica(List<FacturasSiesaController> listaT350ReporteDiarioGrafica) {
        this.listaT350ReporteDiarioGrafica = listaT350ReporteDiarioGrafica;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiario() {
        return listaT350ReporteDiario;
    }

    public void setListaT350ReporteDiario(List<FacturasSiesaController> listaT350ReporteDiario) {
        this.listaT350ReporteDiario = listaT350ReporteDiario;
    }

    public java.sql.Date convertir() {
        return new java.sql.Date(fecha1.getTime());
    }

    public java.sql.Date convertir2() {
        return new java.sql.Date(fecha2.getTime());
    }

    public String getBodega() {
        return Bodega;
    }

    public void setBodega(String Bodega) {
        this.Bodega = Bodega;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    public Integer getF350Rowid() {
        return f350Rowid;
    }

    public void setF350Rowid(Integer f350Rowid) {
        this.f350Rowid = f350Rowid;
    }

    public int getF350ConsecDocto() {
        return f350ConsecDocto;
    }

    public void setF350ConsecDocto(int f350ConsecDocto) {
        this.f350ConsecDocto = f350ConsecDocto;
    }

    public String getF350Prefijo() {
        return f350Prefijo;
    }

    public void setF350Prefijo(String f350Prefijo) {
        this.f350Prefijo = f350Prefijo;
    }

    public String getF350IdSucursal() {
        return f350IdSucursal;
    }

    public void setF350IdSucursal(String f350IdSucursal) {
        this.f350IdSucursal = f350IdSucursal;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public BigDecimal getF350TotalCr() {
        return f350TotalCr;
    }

    public void setF350TotalCr(BigDecimal f350TotalCr) {
        this.f350TotalCr = f350TotalCr;
    }

    public short getF350IndEstado() {
        return f350IndEstado;
    }

    public void setF350IndEstado(short f350IndEstado) {
        this.f350IndEstado = f350IndEstado;
    }

    public short getF350IndTransmit() {
        return f350IndTransmit;
    }

    public void setF350IndTransmit(short f350IndTransmit) {
        this.f350IndTransmit = f350IndTransmit;
    }

    public Date getF350FechaTsCreacion() {
        return f350FechaTsCreacion;
    }

    public void setF350FechaTsCreacion(Date f350FechaTsCreacion) {
        this.f350FechaTsCreacion = f350FechaTsCreacion;
    }

    public Date getF350FechaTsActualizacion() {
        return f350FechaTsActualizacion;
    }

    public void setF350FechaTsActualizacion(Date f350FechaTsActualizacion) {
        this.f350FechaTsActualizacion = f350FechaTsActualizacion;
    }

    public Date getF350FechaTsAprobacion() {
        return f350FechaTsAprobacion;
    }

    public void setF350FechaTsAprobacion(Date f350FechaTsAprobacion) {
        this.f350FechaTsAprobacion = f350FechaTsAprobacion;
    }

    public Date getF350FechaTsAnulacion() {
        return f350FechaTsAnulacion;
    }

    public void setF350FechaTsAnulacion(Date f350FechaTsAnulacion) {
        this.f350FechaTsAnulacion = f350FechaTsAnulacion;
    }

    public String getF350UsuarioCreacion() {
        return f350UsuarioCreacion;
    }

    public void setF350UsuarioCreacion(String f350UsuarioCreacion) {
        this.f350UsuarioCreacion = f350UsuarioCreacion;
    }

    public String getF350UsuarioActualizacion() {
        return f350UsuarioActualizacion;
    }

    public void setF350UsuarioActualizacion(String f350UsuarioActualizacion) {
        this.f350UsuarioActualizacion = f350UsuarioActualizacion;
    }

    public String getF350UsuarioAprobacion() {
        return f350UsuarioAprobacion;
    }

    public void setF350UsuarioAprobacion(String f350UsuarioAprobacion) {
        this.f350UsuarioAprobacion = f350UsuarioAprobacion;
    }

    public String getF350UsuarioAnulacion() {
        return f350UsuarioAnulacion;
    }

    public void setF350UsuarioAnulacion(String f350UsuarioAnulacion) {
        this.f350UsuarioAnulacion = f350UsuarioAnulacion;
    }

    public BigDecimal getF350TotalBaseGravable() {
        return f350TotalBaseGravable;
    }

    public void setF350TotalBaseGravable(BigDecimal f350TotalBaseGravable) {
        this.f350TotalBaseGravable = f350TotalBaseGravable;
    }

    public short getF350IndImpresion() {
        return f350IndImpresion;
    }

    public void setF350IndImpresion(short f350IndImpresion) {
        this.f350IndImpresion = f350IndImpresion;
    }

    public short getF350NroImpresiones() {
        return f350NroImpresiones;
    }

    public void setF350NroImpresiones(short f350NroImpresiones) {
        this.f350NroImpresiones = f350NroImpresiones;
    }

    public Date getF350FechaTsHabilitaImp() {
        return f350FechaTsHabilitaImp;
    }

    public void setF350FechaTsHabilitaImp(Date f350FechaTsHabilitaImp) {
        this.f350FechaTsHabilitaImp = f350FechaTsHabilitaImp;
    }

    public String getF350UsuarioHabilitaImp() {
        return f350UsuarioHabilitaImp;
    }

    public void setF350UsuarioHabilitaImp(String f350UsuarioHabilitaImp) {
        this.f350UsuarioHabilitaImp = f350UsuarioHabilitaImp;
    }

    public String getF350Notas() {
        return f350Notas;
    }

    public void setF350Notas(String f350Notas) {
        this.f350Notas = f350Notas;
    }

    public String getF350Referencia() {
        return f350Referencia;
    }

    public void setF350Referencia(String f350Referencia) {
        this.f350Referencia = f350Referencia;
    }

    public Integer getF350RowidMovtoEntidad() {
        return f350RowidMovtoEntidad;
    }

    public void setF350RowidMovtoEntidad(Integer f350RowidMovtoEntidad) {
        this.f350RowidMovtoEntidad = f350RowidMovtoEntidad;
    }

    public short getF350IndFormaConv() {
        return f350IndFormaConv;
    }

    public void setF350IndFormaConv(short f350IndFormaConv) {
        this.f350IndFormaConv = f350IndFormaConv;
    }

    public BigDecimal getF350TasaConv() {
        return f350TasaConv;
    }

    public void setF350TasaConv(BigDecimal f350TasaConv) {
        this.f350TasaConv = f350TasaConv;
    }

    public short getF350IndFormaLocal() {
        return f350IndFormaLocal;
    }

    public void setF350IndFormaLocal(short f350IndFormaLocal) {
        this.f350IndFormaLocal = f350IndFormaLocal;
    }

    public BigDecimal getF350TasaLocal() {
        return f350TasaLocal;
    }

    public void setF350TasaLocal(BigDecimal f350TasaLocal) {
        this.f350TasaLocal = f350TasaLocal;
    }

    public short getF350IndCfd() {
        return f350IndCfd;
    }

    public void setF350IndCfd(short f350IndCfd) {
        this.f350IndCfd = f350IndCfd;
    }

    public String getF350UsuarioImpresion() {
        return f350UsuarioImpresion;
    }

    public void setF350UsuarioImpresion(String f350UsuarioImpresion) {
        this.f350UsuarioImpresion = f350UsuarioImpresion;
    }

    public Date getF350FechaTsImpresion() {
        return f350FechaTsImpresion;
    }

    public void setF350FechaTsImpresion(Date f350FechaTsImpresion) {
        this.f350FechaTsImpresion = f350FechaTsImpresion;
    }

    public BigDecimal getF350TotalDb2() {
        return f350TotalDb2;
    }

    public void setF350TotalDb2(BigDecimal f350TotalDb2) {
        this.f350TotalDb2 = f350TotalDb2;
    }

    public BigDecimal getF350TotalCr2() {
        return f350TotalCr2;
    }

    public void setF350TotalCr2(BigDecimal f350TotalCr2) {
        this.f350TotalCr2 = f350TotalCr2;
    }

    public BigDecimal getF350TotalDb3() {
        return f350TotalDb3;
    }

    public void setF350TotalDb3(BigDecimal f350TotalDb3) {
        this.f350TotalDb3 = f350TotalDb3;
    }

    public BigDecimal getF350TotalCr3() {
        return f350TotalCr3;
    }

    public void setF350TotalCr3(BigDecimal f350TotalCr3) {
        this.f350TotalCr3 = f350TotalCr3;
    }

    public short getF350IndImptoAsumido() {
        return f350IndImptoAsumido;
    }

    public void setF350IndImptoAsumido(short f350IndImptoAsumido) {
        this.f350IndImptoAsumido = f350IndImptoAsumido;
    }

    public short getF350IndTipoOrigen() {
        return f350IndTipoOrigen;
    }

    public void setF350IndTipoOrigen(short f350IndTipoOrigen) {
        this.f350IndTipoOrigen = f350IndTipoOrigen;
    }

    public short getF350IndDifCambioForma() {
        return f350IndDifCambioForma;
    }

    public void setF350IndDifCambioForma(short f350IndDifCambioForma) {
        this.f350IndDifCambioForma = f350IndDifCambioForma;
    }

    public Integer getAtrazo() {
        return Atrazo;
    }

    public void setAtrazo(Integer Atrazo) {
        this.Atrazo = Atrazo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<FacturasSiesaController> getListaT350PruebaDos() {
        return listaT350PruebaDos;
    }

    public void setListaT350PruebaDos(List<FacturasSiesaController> listaT350PruebaDos) {
        this.listaT350PruebaDos = listaT350PruebaDos;
    }

    public List<FacturasSiesaController> getListaT350PruebatREC() {
        return listaT350PruebatREC;
    }

    public void setListaT350PruebatREC(List<FacturasSiesaController> listaT350PruebatREC) {
        this.listaT350PruebatREC = listaT350PruebatREC;
    }

    public List<FacturasSiesaController> getListaT350Prueba23() {
        return listaT350Prueba23;
    }

    public void setListaT350Prueba23(List<FacturasSiesaController> listaT350Prueba23) {
        this.listaT350Prueba23 = listaT350Prueba23;
    }

    public List<FacturasSiesaController> getListaT350PruebatREC23() {
        return listaT350PruebatREC23;
    }

    public void setListaT350PruebatREC23(List<FacturasSiesaController> listaT350PruebatREC23) {
        this.listaT350PruebatREC23 = listaT350PruebatREC23;
    }

    public FacturasSiesaController getItemF350coDoctoContableActual() {
        return itemF350coDoctoContableActual;
    }

    public void setItemF350coDoctoContableActual(FacturasSiesaController itemF350coDoctoContableActual) {
        this.itemF350coDoctoContableActual = itemF350coDoctoContableActual;
    }

    public List<FacturasSiesaController> getListaT350PruebaDos23() {
        return listaT350PruebaDos23;
    }

    public void setListaT350PruebaDos23(List<FacturasSiesaController> listaT350PruebaDos23) {
        this.listaT350PruebaDos23 = listaT350PruebaDos23;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiarioConfecciones() {
        return listaT350ReporteDiarioConfecciones;
    }

    public void setListaT350ReporteDiarioConfecciones(List<FacturasSiesaController> listaT350ReporteDiarioConfecciones) {
        this.listaT350ReporteDiarioConfecciones = listaT350ReporteDiarioConfecciones;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiarioConfeccionesGraficas() {
        return listaT350ReporteDiarioConfeccionesGraficas;
    }

    public void setListaT350ReporteDiarioConfeccionesGraficas(List<FacturasSiesaController> listaT350ReporteDiarioConfeccionesGraficas) {
        this.listaT350ReporteDiarioConfeccionesGraficas = listaT350ReporteDiarioConfeccionesGraficas;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiaAnterior() {
        return listaT350ReporteDiaAnterior;
    }

    public void setListaT350ReporteDiaAnterior(List<FacturasSiesaController> listaT350ReporteDiaAnterior) {
        this.listaT350ReporteDiaAnterior = listaT350ReporteDiaAnterior;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiarioBigBag() {
        return listaT350ReporteDiarioBigBag;
    }

    public void setListaT350ReporteDiarioBigBag(List<FacturasSiesaController> listaT350ReporteDiarioBigBag) {
        this.listaT350ReporteDiarioBigBag = listaT350ReporteDiarioBigBag;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiarioBigBagGraficas() {
        return listaT350ReporteDiarioBigBagGraficas;
    }

    public void setListaT350ReporteDiarioBigBagGraficas(List<FacturasSiesaController> listaT350ReporteDiarioBigBagGraficas) {
        this.listaT350ReporteDiarioBigBagGraficas = listaT350ReporteDiarioBigBagGraficas;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiarioFTDH() {
        return listaT350ReporteDiarioFTDH;
    }

    public void setListaT350ReporteDiarioFTDH(List<FacturasSiesaController> listaT350ReporteDiarioFTDH) {
        this.listaT350ReporteDiarioFTDH = listaT350ReporteDiarioFTDH;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiarioFTDHGraficas() {
        return listaT350ReporteDiarioFTDHGraficas;
    }

    public void setListaT350ReporteDiarioFTDHGraficas(List<FacturasSiesaController> listaT350ReporteDiarioFTDHGraficas) {
        this.listaT350ReporteDiarioFTDHGraficas = listaT350ReporteDiarioFTDHGraficas;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiaActualGrafica() {
        return listaT350ReporteDiaActualGrafica;
    }

    public void setListaT350ReporteDiaActualGrafica(List<FacturasSiesaController> listaT350ReporteDiaActualGrafica) {
        this.listaT350ReporteDiaActualGrafica = listaT350ReporteDiaActualGrafica;
    }

    public List<FacturasSiesaController> getListaT350ReporteAnualFTDH() {
        return listaT350ReporteAnualFTDH;
    }

    public void setListaT350ReporteAnualFTDH(List<FacturasSiesaController> listaT350ReporteAnualFTDH) {
        this.listaT350ReporteAnualFTDH = listaT350ReporteAnualFTDH;
    }

    public List<FacturasSiesaController> getListaT350ReporteAnualExt() {
        return listaT350ReporteAnualExt;
    }

    public void setListaT350ReporteAnualExt(List<FacturasSiesaController> listaT350ReporteAnualExt) {
        this.listaT350ReporteAnualExt = listaT350ReporteAnualExt;
    }

    public List<FacturasSiesaController> getListaT350ReporteActualExt() {
        return listaT350ReporteActualExt;
    }

    public void setListaT350ReporteActualExt(List<FacturasSiesaController> listaT350ReporteActualExt) {
        this.listaT350ReporteActualExt = listaT350ReporteActualExt;
    }

    public List<FacturasSiesaController> getListaT350ReporteAnteriorExt() {
        return listaT350ReporteAnteriorExt;
    }

    public void setListaT350ReporteAnteriorExt(List<FacturasSiesaController> listaT350ReporteAnteriorExt) {
        this.listaT350ReporteAnteriorExt = listaT350ReporteAnteriorExt;
    }

    public List<FacturasSiesaController> getListaT350ReporteActualFTDH() {
        return listaT350ReporteActualFTDH;
    }

    public void setListaT350ReporteActualFTDH(List<FacturasSiesaController> listaT350ReporteActualFTDH) {
        this.listaT350ReporteActualFTDH = listaT350ReporteActualFTDH;
    }

    public List<FacturasSiesaController> getListaT350ReporteAnteriorFTDH() {
        return listaT350ReporteAnteriorFTDH;
    }

    public void setListaT350ReporteAnteriorFTDH(List<FacturasSiesaController> listaT350ReporteAnteriorFTDH) {
        this.listaT350ReporteAnteriorFTDH = listaT350ReporteAnteriorFTDH;
    }

    public List<FacturasSiesaController> getListaT350ReporteMensualTotal() {
        return listaT350ReporteMensualTotal;
    }

    public void setListaT350ReporteMensualTotal(List<FacturasSiesaController> listaT350ReporteMensualTotal) {
        this.listaT350ReporteMensualTotal = listaT350ReporteMensualTotal;
    }

    public List<FacturasSiesaController> getListaT350ReporteAnteriorExtClientes() {
        return listaT350ReporteAnteriorExtClientes;
    }

    public void setListaT350ReporteAnteriorExtClientes(List<FacturasSiesaController> listaT350ReporteAnteriorExtClientes) {
        this.listaT350ReporteAnteriorExtClientes = listaT350ReporteAnteriorExtClientes;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiarioBigBagClientes() {
        return listaT350ReporteDiarioBigBagClientes;
    }

    public void setListaT350ReporteDiarioBigBagClientes(List<FacturasSiesaController> listaT350ReporteDiarioBigBagClientes) {
        this.listaT350ReporteDiarioBigBagClientes = listaT350ReporteDiarioBigBagClientes;
    }

    public List<FacturasSiesaController> getListaT350ReporteAnteriorFTDHClientes() {
        return listaT350ReporteAnteriorFTDHClientes;
    }

    public void setListaT350ReporteAnteriorFTDHClientes(List<FacturasSiesaController> listaT350ReporteAnteriorFTDHClientes) {
        this.listaT350ReporteAnteriorFTDHClientes = listaT350ReporteAnteriorFTDHClientes;
    }

    public List<FacturasSiesaController> getListaT350ReporteAnteriorTallerIndustrialClientes() {
        return listaT350ReporteAnteriorTallerIndustrialClientes;
    }

    public void setListaT350ReporteAnteriorTallerIndustrialClientes(List<FacturasSiesaController> listaT350ReporteAnteriorTallerIndustrialClientes) {
        this.listaT350ReporteAnteriorTallerIndustrialClientes = listaT350ReporteAnteriorTallerIndustrialClientes;
    }

    public List<FacturasSiesaController> getListaT350ReporteAnteriorExtClientesTotal() {
        return listaT350ReporteAnteriorExtClientesTotal;
    }

    public void setListaT350ReporteAnteriorExtClientesTotal(List<FacturasSiesaController> listaT350ReporteAnteriorExtClientesTotal) {
        this.listaT350ReporteAnteriorExtClientesTotal = listaT350ReporteAnteriorExtClientesTotal;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiarioConfeccionesClientesTotal() {
        return listaT350ReporteDiarioConfeccionesClientesTotal;
    }

    public void setListaT350ReporteDiarioConfeccionesClientesTotal(List<FacturasSiesaController> listaT350ReporteDiarioConfeccionesClientesTotal) {
        this.listaT350ReporteDiarioConfeccionesClientesTotal = listaT350ReporteDiarioConfeccionesClientesTotal;
    }

    public List<FacturasSiesaController> getListaT350ReporteDiarioBigBagClientesTotal() {
        return listaT350ReporteDiarioBigBagClientesTotal;
    }

    public void setListaT350ReporteDiarioBigBagClientesTotal(List<FacturasSiesaController> listaT350ReporteDiarioBigBagClientesTotal) {
        this.listaT350ReporteDiarioBigBagClientesTotal = listaT350ReporteDiarioBigBagClientesTotal;
    }

    public List<FacturasSiesaController> getListaT350ReporteAnteriorFTDHClientesTotal() {
        return listaT350ReporteAnteriorFTDHClientesTotal;
    }

    public void setListaT350ReporteAnteriorFTDHClientesTotal(List<FacturasSiesaController> listaT350ReporteAnteriorFTDHClientesTotal) {
        this.listaT350ReporteAnteriorFTDHClientesTotal = listaT350ReporteAnteriorFTDHClientesTotal;
    }

    public List<FacturasSiesaController> getListaT350ReporteAnteriorTallerIndustrialClientesTotal() {
        return listaT350ReporteAnteriorTallerIndustrialClientesTotal;
    }

    public void setListaT350ReporteAnteriorTallerIndustrialClientesTotal(List<FacturasSiesaController> listaT350ReporteAnteriorTallerIndustrialClientesTotal) {
        this.listaT350ReporteAnteriorTallerIndustrialClientesTotal = listaT350ReporteAnteriorTallerIndustrialClientesTotal;
    }

    public List<FacturasSiesaController> getListaPendientes() {
        return listaPendientes;
    }

    public void setListaPendientes(List<FacturasSiesaController> listaPendientes) {
        this.listaPendientes = listaPendientes;
    }

    public List<FacturasSiesaController> getListaPendientesTotal() {
        return listaPendientesTotal;
    }

    public void setListaPendientesTotal(List<FacturasSiesaController> listaPendientesTotal) {
        this.listaPendientesTotal = listaPendientesTotal;
    }

    public List<FacturasSiesaController> getListaTranscursoBigbag() {
        return listaTranscursoBigbag;
    }

    public void setListaTranscursoBigbag(List<FacturasSiesaController> listaTranscursoBigbag) {
        this.listaTranscursoBigbag = listaTranscursoBigbag;
    }

    public List<FacturasSiesaController> getListaTranscursoBigBagGrafica() {
        return listaTranscursoBigBagGrafica;
    }

    public void setListaTranscursoBigBagGrafica(List<FacturasSiesaController> listaTranscursoBigBagGrafica) {
        this.listaTranscursoBigBagGrafica = listaTranscursoBigBagGrafica;
    }

    public void prepareAspecto() {
        novedadFacturacionActual = new NovedadFacturacion();

    }

    public String prepareList() throws SQLException {
        recargarDatos(getFecha1(), getFecha2());
        recargarDatosGrafica(getFecha1(), getFecha2());
        recargarDatosEmpresa(getFecha1(), getFecha2());
        recargarDatosGraficaAnual();
        recargarDatosClientConfe();
        recargarDatosClientBiBag();
        recargarDatosClientExt();
        listarGlobal();
        recargarDatosClientConfeTotal();
        recargarDatosClientTalleresIndustrialesTotal();
        recargarDatosClientBiBagTotal();
        recargarDatosClientFTDH();
        recargarDatosDiaAnteriorFTDH();
        recargarDatosDiaAnteriorExt();
        recargarTranscursoMensualConfecci();
        return "Facturado";
    }

    public String prepareListPendiente() throws SQLException, IOException {
        recargarDatosPendientes();
        recargarDatosPendientesTotal();
        recargarDatosRemicionDiaActual();
        recargarDatosRemicionDia();

        return "pendienteFacturar";
    }

    public String prepareDiario() throws SQLException, IOException {

        recargarDatosDiario();
        recargarDatosGraficaAnual();
        recargarDatosExten();
        recargarDatosAnualGraficas();
        recargarDatosDiaActual();
        recargarDatosGraficaAnualSuma();
        recargarDatosDiaActualGrafica();
        recargarDatosConfecci();
        recargarDatosDiaAnterior();
        recargarDatosBigBag();
        recargarDatosFTDH();
        redondedoConfecciones();
        novedades();
        listarModal();
        recargarDatosDiaAnualFTDH();
        recargarDatosDiaAnualExt();
        recargarDatosDiaAnteriorExt();
        recargarDatosDiaAnteriorFTDH();
        recargarDatosDiaActualFTDH();
        recargarDatosDiaActuallExt();
        recargarDatosMenUni();
        recargarDatosMenUniTo();
        recargarDatosRemicionDia();
        recargarTranscursoMensualConfecci();
        recargarTranscursoMensualBigBag();

        return "/usuario/desayuno/reporte_diario";

    }

    public String prepareDiaActual() throws SQLException, IOException {

        recargarDatosDiario();
        recargarDatosGraficaAnual();
        recargarDatosExten();
        recargarDatosAnualGraficas();
        recargarDatosDiaActual();
        recargarDatosGraficaAnualSuma();
        recargarDatosDiaActualGrafica();
        recargarDatosConfecci();
        recargarDatosDiaAnterior();
        recargarDatosBigBag();
        recargarDatosFTDH();
        novedades();
        redondedoConfecciones();
        listarModal();
        recargarDatosDiaAnualFTDH();
        recargarDatosDiaAnualExt();
        recargarDatosDiaAnteriorExt();
        recargarDatosDiaAnteriorFTDH();
        recargarDatosDiaActualFTDH();
        recargarDatosDiaActuallExt();
        recargarDatosMenUni();
        recargarDatosMenUniTo();
        recargarDatosRemicionDia();
        recargarTranscursoMensualConfecci();
        recargarTranscursoMensualBigBag();

        return "/usuario/desayuno/reporteDiaActual";

    }

    public List<FacturasSiesaController> novedades() throws SQLException {
        try {

            Statement stmt = null;

            ResultSet rs;
            String user = "root";
            String password = "cfiprovidencia1";
            String con = "jdbc:mysql://localhost:3306/proyectocfip?user=" + user + "&password=" + password;
            Connection conn = DriverManager.getConnection(con);

            PreparedStatement st = conn.prepareStatement("SELECT novedad_facturacion.id_novedad_facturacion,novedad_facturacion.fcrm_rniff,novedad_facturacion.fcrm_nrniff,novedad_facturacion.total_confecciones,novedad_facturacion.real_confecciones from proyectocfip.novedad_facturacion;");
            novedadFacturacionList = new ArrayList();
            rs = st.executeQuery();
            while (rs.next()) {
                FacturasSiesaController novedades = new FacturasSiesaController();
                novedades.setId(rs.getInt("id_novedad_facturacion"));
                novedades.setNovedadReNiff(rs.getBigDecimal("fcrm_rniff"));
                novedades.setNovedadNoReNiff(rs.getBigDecimal("fcrm_nrniff"));
                novedades.setValorReConfecciones(rs.getBigDecimal("total_confecciones"));
                novedades.setValorVisConfecciones(rs.getBigDecimal("real_confecciones"));

                novedadFacturacionList.add(novedades);

            }

        } catch (Exception e) {
            System.out.println("error" + e);
        }

        return novedadFacturacionList;
    }

    public List<FacturasSiesaController> redondedoConfecciones() throws SQLException {
        try {
            Statement stmt = null;
            ResultSet rs;
            String user = "root";
            String password = "cfiprovidencia1";
            String con = "jdbc:mysql://localhost:3306/proyectocfip?user=" + user + "&password=" + password;
            Connection conn = DriverManager.getConnection(con);
            //Integer id = idProceso.getIdProceso();

            //Factores factor = new Factores();
            //String proceso = idProceso.getIdProceso().toString();
            PreparedStatement st = conn.prepareStatement("SELECT novedad_facturacion.real_confecciones from proyectocfip.novedad_facturacion;");

            novedadFacturacionRedondeoList = new ArrayList<>();
            rs = st.executeQuery();

            while (rs.next()) {
                FacturasSiesaController novedadesTo = new FacturasSiesaController();
                novedadesTo.setValorVisConfecciones(rs.getBigDecimal("real_confecciones"));

                novedadFacturacionRedondeoList.add(novedadesTo);

            }

        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return novedadFacturacionRedondeoList;

    }
///Datos pagina reporte mensual lista 1

    public void recargarDatos(Date fecha1, Date fecha2) throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT\n"
                    + "SUM(f470_vlr_bruto) AS total,\n"
                    + "\n"
                    + "f281_descripcion\n"
                    + "FROM dbo.t350_co_docto_contable \n"
                    + "inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                    + "inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                    + "inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                    + "inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                    + "inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                    + "where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV ' and f281_descripcion  != 'ACADEMICAS' \n"
                    + "and\n"
                    + " dbo.t350_co_docto_contable.f350_fecha BETWEEN ? AND ?\n"
                    + "group by \n"
                    + "\n"
                    + "f281_descripcion");
            st.setDate(1, convertir());
            st.setDate(2, convertir2());
            listaT350Prueba = new ArrayList<>();
            rs = st.executeQuery();

            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable = new FacturasSiesaController();
                itemF350coDoctoContable.setCliente(rs.getString("f281_descripcion"));
                itemF350coDoctoContable.setTotal(rs.getBigDecimal("total"));
                listaT350Prueba.add(itemF350coDoctoContable);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

///datos pagina reporte mensual grafica 2
    public void recargarDatosGrafica(Date fecha1, Date fecha2) throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT\n"
                    + "SUM(f470_vlr_bruto) AS total,\n"
                    + "\n"
                    + "f281_descripcion\n"
                    + "FROM dbo.t350_co_docto_contable \n"
                    + "inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                    + "inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                    + "inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                    + "inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                    + "inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                    + "where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV ' and f281_descripcion  != 'ACADEMICAS' \n"
                    + "and\n"
                    + " dbo.t350_co_docto_contable.f350_fecha BETWEEN ? AND ? \n"
                    + "group by \n"
                    + "\n"
                    + "f281_descripcion;");
            st.setDate(1, convertir());
            st.setDate(2, convertir2());
            listaT350PruebatREC = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable2 = new FacturasSiesaController();
                itemF350coDoctoContable2.setDescrip(rs.getString("f281_descripcion"));
                itemF350coDoctoContable2.setTotal(rs.getBigDecimal("total"));
                listaT350PruebatREC.add(itemF350coDoctoContable2);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

///datos pagina reporte mensual lista 3
    public void recargarDatosEmpresa(Date fecha1, Date fecha2) throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT\n"
                    + "SUM(f470_vlr_bruto) AS total,\n"
                    + "f200_razon_social,\n"
                    + "f281_descripcion\n"
                    + "FROM dbo.t350_co_docto_contable \n"
                    + "inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                    + "inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                    + "inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                    + "inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                    + "inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                    + "where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV 'and f281_descripcion  != 'ACADEMICAS' \n"
                    + "and\n"
                    + " dbo.t350_co_docto_contable.f350_fecha BETWEEN ? AND ? \n"
                    + "group by \n"
                    + "f200_razon_social,\n"
                    + "f281_descripcion\n"
                    + ";");
            st.setDate(1, convertir());
            st.setDate(2, convertir2());
            listaT350Prueba23 = new ArrayList<>();
            rs = st.executeQuery();

            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable3 = new FacturasSiesaController();
                itemF350coDoctoContable3.setDescrip(rs.getString("f281_descripcion"));
                itemF350coDoctoContable3.setCliente(rs.getString("f200_razon_social"));
                itemF350coDoctoContable3.setTotal(rs.getBigDecimal("total"));
                listaT350Prueba23.add(itemF350coDoctoContable3);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

///datos pagina reporte anual grafica y lista 4
    public List<FacturasSiesaController> recargarDatosGraficaAnual() throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT\n"
                    + "               SUM(CASE dbo.t350_co_docto_contable.f350_id_tipo_docto WHEN 'FV' THEN f470_vlr_bruto else 0 end )\n"
                    + "              -SUM(CASE dbo.t350_co_docto_contable.f350_id_tipo_docto WHEN 'NCC' THEN f470_vlr_bruto else 0 end ) AS CORRECTO,\n"
                    + "               \n"
                    + "f281_descripcion\n"
                    + "                FROM dbo.t350_co_docto_contable \n"
                    + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                    + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                    + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                    + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                    + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                    + "                where f281_descripcion != 'EXT. ACADEMICAS' and  \n"
                    + "                dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(yy,DATEDIFF(yy,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(yy,0,DATEADD(yy,DATEDIFF(yy,0,GETDATE())+1,0)))\n"
                    + "                \n"
                    + "                GROUP by f281_descripcion ");
            listaT350Anual = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable40 = new FacturasSiesaController();
                itemF350coDoctoContable40.setTotal(rs.getBigDecimal("CORRECTO"));
                itemF350coDoctoContable40.setDescrip(rs.getString("f281_descripcion"));

                listaT350Anual.add(itemF350coDoctoContable40);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return listaT350Anual;
    }

//Datos pendiente facturar
    public List<FacturasSiesaController> recargarDatosPendientes() throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT \n"
                    + "f430_consec_docto,f430_num_docto_referencia,\n"
                    + "f201_descripcion_sucursal,\n"
                    + "f431_fecha_entrega,\n"
                    + "SUM(f431_vlr_bruto) as total,\n"
                    + "DATEDIFF(DAY , getdate(),f431_fecha_entrega) as atrazo\n"
                    + "FROM dbo.t431_cm_pv_movto  \n"
                    + "inner join dbo.t430_cm_pv_docto on dbo.t431_cm_pv_movto.f431_rowid_pv_docto = dbo.t430_cm_pv_docto.f430_rowid\n"
                    + "inner join dbo.t201_mm_clientes on dbo.t430_cm_pv_docto.f430_rowid_tercero_fact = dbo.t201_mm_clientes.f201_rowid_tercero\n"
                    + "where\n"
                    + "f430_ind_estado = 2 and f431_ind_estado != 4 and\n"
                    + "dbo.t431_cm_pv_movto.f431_fecha_entrega BETWEEN  DATEADD(yy,DATEDIFF(yy,0,GETDATE()),0) and DATEADD(dd,DATEDIFF(dd,0,GETDATE()),0)\n"
                    + "GROUP BY \n"
                    + "f430_consec_docto,f430_num_docto_referencia,\n"
                    + "f201_descripcion_sucursal,f431_fecha_entrega order by f431_fecha_entrega ASC;");

            listaPendientes = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable30 = new FacturasSiesaController();
                itemF350coDoctoContable30.setF350ConsecDocto(rs.getInt("f430_consec_docto"));
                itemF350coDoctoContable30.setF350OrdenCompra(rs.getString("f430_num_docto_referencia"));
                itemF350coDoctoContable30.setCliente(rs.getString("f201_descripcion_sucursal"));
                itemF350coDoctoContable30.setFecha(rs.getDate("f431_fecha_entrega"));
                itemF350coDoctoContable30.setAtrazo(rs.getInt("atrazo"));
                itemF350coDoctoContable30.setTotal(rs.getBigDecimal("total"));
                listaPendientes.add(itemF350coDoctoContable30);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return listaPendientes;
    }
//Datos pendiente facturar

//Datos pendiente facturar TOTAL
    public List<FacturasSiesaController> recargarDatosPendientesTotal() throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT \n"
                    + "\n"
                    + "SUM(f431_vlr_bruto) as total\n"
                    + "FROM dbo.t431_cm_pv_movto  \n"
                    + "inner join dbo.t430_cm_pv_docto on dbo.t431_cm_pv_movto.f431_rowid_pv_docto = dbo.t430_cm_pv_docto.f430_rowid\n"
                    + "inner join dbo.t201_mm_clientes on dbo.t430_cm_pv_docto.f430_rowid_tercero_fact = dbo.t201_mm_clientes.f201_rowid_tercero\n"
                    + "where\n"
                    + "f430_ind_estado = 2 and  f431_ind_estado != 4 and\n"
                    + "dbo.t431_cm_pv_movto.f431_fecha_entrega BETWEEN  DATEADD(yy,DATEDIFF(yy,0,GETDATE()),0) and DATEADD(dd,DATEDIFF(dd,0,GETDATE()),0)\n"
                    + ";");

            listaPendientesTotal = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable31 = new FacturasSiesaController();
                itemF350coDoctoContable31.setTotal(rs.getBigDecimal("total"));
                listaPendientesTotal.add(itemF350coDoctoContable31);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return listaPendientesTotal;
    }
//Datos pendiente facturar TOTAL    

///datos diario clientes confecciones  23
    public List<FacturasSiesaController> recargarDatosClientConfe() throws SQLException {
        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT          SUM(f470_vlr_bruto)  as total,f200_razon_social\n"
                + "                FROM dbo.t350_co_docto_contable \n"
                + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and f281_descripcion =   'TALLER DE CONFECCION' and\n"
                + "                 dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(d,-2,GETDATE()) AND DATEADD(d,-1,GETDATE())\n"
                + "                group by f200_razon_social;";
        listaT350ReporteDiarioConfeccionesClientes = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable23 = new FacturasSiesaController();
                itemF350coDoctoContable23.setCliente(rs.getString("f200_razon_social"));
                itemF350coDoctoContable23.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteDiarioConfeccionesClientes.add(itemF350coDoctoContable23);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteDiarioConfeccionesClientes;
    }

///datos diario clientes bigbag 24
    public List<FacturasSiesaController> recargarDatosClientBiBag() throws SQLException {
        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT          SUM(f470_vlr_bruto)  as total,f200_razon_social\n"
                + "                FROM dbo.t350_co_docto_contable \n"
                + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and f281_descripcion =   'SERVICIOS BIG BAG' and\n"
                + "                 dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(d,-2,GETDATE()) AND DATEADD(d,-1,GETDATE())\n"
                + "                group by f200_razon_social;";
        listaT350ReporteDiarioBigBagClientes = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable24 = new FacturasSiesaController();
                itemF350coDoctoContable24.setCliente(rs.getString("f200_razon_social"));
                itemF350coDoctoContable24.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteDiarioBigBagClientes.add(itemF350coDoctoContable24);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteDiarioBigBagClientes;
    }

///datos diario clientes Extension academica 25
    public List<FacturasSiesaController> recargarDatosClientExt() throws SQLException {
        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT          SUM(f470_vlr_bruto)  as total,f200_razon_social\n"
                + "                FROM dbo.t350_co_docto_contable \n"
                + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and f284_descripcion =   'CURSOS DE EXTENSION ACADEMICA' and\n"
                + "                 dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(d,-2,GETDATE()) AND DATEADD(d,-1,GETDATE())\n"
                + "                group by f200_razon_social;";
        listaT350ReporteAnteriorExtClientes = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable24 = new FacturasSiesaController();
                itemF350coDoctoContable24.setCliente(rs.getString("f200_razon_social"));
                itemF350coDoctoContable24.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteAnteriorExtClientes.add(itemF350coDoctoContable24);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteAnteriorExtClientes;
    }

///datos diario clientes FTDH 26
    public List<FacturasSiesaController> recargarDatosClientFTDH() throws SQLException {
        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT          SUM(f470_vlr_bruto)  as total,f200_razon_social\n"
                + "                FROM dbo.t350_co_docto_contable \n"
                + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and f284_descripcion LIKE   'EDUCACI%' and\n"
                + "                 dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(d,-2,GETDATE()) AND DATEADD(d,-1,GETDATE())\n"
                + "                group by f200_razon_social;";
        listaT350ReporteAnteriorFTDHClientes = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable26 = new FacturasSiesaController();
                itemF350coDoctoContable26.setCliente(rs.getString("f200_razon_social"));
                itemF350coDoctoContable26.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteAnteriorFTDHClientes.add(itemF350coDoctoContable26);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteAnteriorFTDHClientes;
    }

    ///datos diario clientes Talleres industriales 27
    public List<FacturasSiesaController> recargarDatosClientTalleresIndustriales() throws SQLException {
        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT          SUM(f470_vlr_bruto)  as total,f200_razon_social\n"
                + "                FROM dbo.t350_co_docto_contable \n"
                + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV ' and f281_descripcion =   'TALLERES INDUSTRIALES'  and\n"
                + "                 dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(d,-2,GETDATE()) AND DATEADD(d,-1,GETDATE())\n"
                + "                group by f200_razon_social;";
        listaT350ReporteAnteriorTallerIndustrialClientes = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable26 = new FacturasSiesaController();
                itemF350coDoctoContable26.setCliente(rs.getString("f200_razon_social"));
                itemF350coDoctoContable26.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteAnteriorTallerIndustrialClientes.add(itemF350coDoctoContable26);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteAnteriorTallerIndustrialClientes;
    }

    ///datos diario clientes confecciones  29
    public List<FacturasSiesaController> recargarDatosClientConfeTotal() throws SQLException {
        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT       SUM(f470_vlr_bruto)  as total\n"
                + "                FROM dbo.t350_co_docto_contable \n"
                + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and f281_descripcion =   'TALLER DE CONFECCION' and\n"
                + "                dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(d,-2,GETDATE()) AND DATEADD(d,-1,GETDATE());";
        listaT350ReporteDiarioConfeccionesClientesTotal = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable29 = new FacturasSiesaController();
                itemF350coDoctoContable29.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteDiarioConfeccionesClientesTotal.add(itemF350coDoctoContable29);
            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteDiarioConfeccionesClientesTotal;
    }

    ///datos diario clientes bigbag 30
    public List<FacturasSiesaController> recargarDatosClientBiBagTotal() throws SQLException {
        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT       SUM(f470_vlr_bruto)  as total\n"
                + "                FROM dbo.t350_co_docto_contable \n"
                + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and f281_descripcion =   'SERVICIOS BIG BAG' and\n"
                + "                 dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(d,-2,GETDATE()) AND DATEADD(d,-1,GETDATE())\n"
                + "                ;";
        listaT350ReporteDiarioBigBagClientesTotal = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable24 = new FacturasSiesaController();
                itemF350coDoctoContable24.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteDiarioBigBagClientesTotal.add(itemF350coDoctoContable24);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteDiarioBigBagClientesTotal;
    }

    ///datos diario clientes bigbag 31
    public List<FacturasSiesaController> recargarDatosClientTalleresIndustrialesTotal() throws SQLException {
        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT          SUM(f470_vlr_bruto)  as total\n"
                + "                FROM dbo.t350_co_docto_contable \n"
                + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV ' and f281_descripcion =   'TALLERES INDUSTRIALES'  and\n"
                + "                 dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(d,-2,GETDATE()) AND DATEADD(d,-1,GETDATE())\n"
                + "                ;";
        listaT350ReporteAnteriorTallerIndustrialClientesTotal = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable26 = new FacturasSiesaController();
                itemF350coDoctoContable26.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteAnteriorTallerIndustrialClientesTotal.add(itemF350coDoctoContable26);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteAnteriorTallerIndustrialClientes;
    }

///////////////////////////////////Fin consultas pagina facturado///////////////////////////////////////////
///////////////////////////////////Incio pagina reporte diario /////////////////////////////////////////////
///datos modal pagina y grafica 5 reporte dia anterior
    public List<FacturasSiesaController> recargarDatosDiario() throws SQLException, IOException {

        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";

        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT\n"
                + "SUM(f470_vlr_bruto) AS total,\n"
                + "\n"
                + "f281_descripcion\n"
                + "FROM dbo.t350_co_docto_contable \n"
                + "inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "\n"
                + "where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and f281_descripcion  != 'EXT. ACADEMICAS'\n"
                + "and\n"
                + " dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(d,-2,GETDATE()) AND DATEADD(d,-1,GETDATE())  \n"
                + "group by \n"
                + "f281_descripcion\n"
                + ";";
        listaT350ReporteDiario = new ArrayList<>();
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable5 = new FacturasSiesaController();
                itemF350coDoctoContable5.setDescrip(rs.getString("f281_descripcion"));
                itemF350coDoctoContable5.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteDiario.add(itemF350coDoctoContable5);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteDiario;
    }

///datos modal pagina y grafica 6 extension academica
    public List<FacturasSiesaController> recargarDatosExten() throws SQLException, IOException {

        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";

        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT\n"
                + "SUM(f470_vlr_bruto) AS total,\n"
                + "f284_descripcion\n"
                + "FROM dbo.t350_co_docto_contable \n"
                + "inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '   and f284_descripcion = 'CURSOS DE EXTENSION ACADEMICA' \n"
                + "and \n"
                + " dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(mm,DATEDIFF(mm,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(mm,0,DATEADD(mm,DATEDIFF(mm,0,GETDATE())+1,0))) \n"
                + "group by \n"
                + "f284_descripcion;";
        listaT350ReporteDiarioExtension = new ArrayList<>();
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable6 = new FacturasSiesaController();
                itemF350coDoctoContable6.setDescrip(rs.getString("f284_descripcion"));
                itemF350coDoctoContable6.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteDiarioExtension.add(itemF350coDoctoContable6);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteDiarioExtension;
    }

///datos modal pagina y grafica 7
    public void recargarDatosAnualGraficas() throws SQLException, IOException {

        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";

        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT\n"
                + "  SUM(f470_vlr_bruto)  / 22833061320 *100 as total    \n"
                + "\n"
                + "FROM dbo.t350_co_docto_contable \n"
                + "inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '     \n"
                + "and \n"
                + " dbo.t350_co_docto_contable.f350_fecha BETWEEN  DATEADD(yy,DATEDIFF(yy,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(yy,0,DATEADD(yy,DATEDIFF(yy,0,GETDATE())+1,0))) ";
        listaT350ReporteAnualGraficas = new ArrayList<>();
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable7 = new FacturasSiesaController();
                itemF350coDoctoContable7.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteAnualGraficas.add(itemF350coDoctoContable7);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

///datos del dia actual/pagina de reporte diario 8
    public void recargarDatosDiaActual() throws SQLException, IOException {

        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";

        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT\n"
                + "SUM(f470_vlr_bruto)  as total,f281_descripcion\n"
                + "FROM dbo.t350_co_docto_contable \n"
                + "inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and f281_descripcion != 'EXT. ACADEMICAS'   \n"
                + "and \n"
                + " dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(dd,DATEDIFF(dd,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(dd,DATEDIFF(dd,0,GETDATE()),1)) \n"
                + "group by f281_descripcion";
        listaT350ReporteDiaActual = new ArrayList<>();
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable8 = new FacturasSiesaController();
                itemF350coDoctoContable8.setDescrip(rs.getString("f281_descripcion"));
                itemF350coDoctoContable8.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteDiaActual.add(itemF350coDoctoContable8);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

///datos pagina suma total/pagina de reporte diario 9
    public List<FacturasSiesaController> recargarDatosGraficaAnualSuma() throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT SUM(f470_vlr_bruto) AS total "
                    + "FROM dbo.t350_co_docto_contable inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto"
                    + " inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact"
                    + " inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid "
                    + "inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV ' "
                    + " and dbo.t350_co_docto_contable.f350_fecha BETWEEN '2019-01-01' AND '2019-12-31';");

            listaT350PruebatREC24 = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable9 = new FacturasSiesaController();
                itemF350coDoctoContable9.setTotal(rs.getBigDecimal("total"));
                listaT350PruebatREC24.add(itemF350coDoctoContable9);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return listaT350PruebatREC24;
    }

///datos del dia actual/pagina de reporte diario 10
    public void recargarDatosDiaActualGrafica() throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT\n"
                    + "                SUM(f470_vlr_bruto) as total\n"
                    + "                FROM dbo.t350_co_docto_contable \n"
                    + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                    + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                    + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                    + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                    + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                    + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '   \n"
                    + "                and dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(dd,DATEDIFF(dd,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(dd,DATEDIFF(dd,0,GETDATE()),1));");

            listaT350ReporteDiaActualGrafica = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable10 = new FacturasSiesaController();
                itemF350coDoctoContable10.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteDiaActualGrafica.add(itemF350coDoctoContable10);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

///datos confecciones pagina reporte diario 11
    public List<FacturasSiesaController> recargarDatosConfecci() throws SQLException, IOException {
        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT\n"
                + "SUM(f470_vlr_bruto) as total,f281_descripcion\n"
                + "FROM dbo.t350_co_docto_contable \n"
                + "inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and f281_descripcion = 'TALLER DE CONFECCION'\n"
                + "and \n"
                + " dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(mm,DATEDIFF(mm,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(mm,0,DATEADD(mm,DATEDIFF(mm,0,GETDATE())+1,0)))\n"
                + "GROUP by f281_descripcion";
        listaT350ReporteDiarioConfecciones = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable11 = new FacturasSiesaController();
                itemF350coDoctoContable11.setDescrip(rs.getString("f281_descripcion"));
                itemF350coDoctoContable11.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteDiarioConfecciones.add(itemF350coDoctoContable11);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteDiarioConfecciones;
    }

///datos modal total dia anterior 12   
    public List<FacturasSiesaController> recargarDatosDiaAnterior() throws SQLException, IOException {

        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";

        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT\n"
                + "SUM(f470_vlr_bruto) AS total\n"
                + "\n"
                + "\n"
                + "FROM dbo.t350_co_docto_contable \n"
                + "inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "\n"
                + "where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '"
                + "and\n"
                + " dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(d,-2,GETDATE()) AND DATEADD(d,-1,GETDATE());";
        listaT350ReporteDiaAnterior = new ArrayList<>();
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable12 = new FacturasSiesaController();
                itemF350coDoctoContable12.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteDiaAnterior.add(itemF350coDoctoContable12);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteDiaAnterior;
    }

///datos BiBag pagina reporte diario 13
    public List<FacturasSiesaController> recargarDatosBigBag() throws SQLException, IOException {
        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT\n"
                + "SUM(f470_vlr_bruto) as total,f281_descripcion\n"
                + "FROM dbo.t350_co_docto_contable \n"
                + "inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and f281_descripcion = 'SERVICIOS BIG BAG'\n"
                + "and \n"
                + " dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(mm,DATEDIFF(mm,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(mm,0,DATEADD(mm,DATEDIFF(mm,0,GETDATE())+1,0)))\n"
                + "GROUP by f281_descripcion";
        listaT350ReporteDiarioBigBag = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable13 = new FacturasSiesaController();
                itemF350coDoctoContable13.setDescrip(rs.getString("f281_descripcion"));
                itemF350coDoctoContable13.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteDiarioBigBag.add(itemF350coDoctoContable13);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteDiarioBigBag;
    }

///datos modal pagina y grafica 14 extension academica
    public List<FacturasSiesaController> recargarDatosFTDH() throws SQLException, IOException {

        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";

        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT\n"
                + "SUM(f470_vlr_bruto) as total\n"
                + "FROM dbo.t350_co_docto_contable \n"
                + "inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  AND f284_descripcion LIKE 'EDUCACIÓN PARA%' \n"
                + "and \n"
                + " dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(mm,DATEDIFF(mm,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(mm,0,DATEADD(mm,DATEDIFF(mm,0,GETDATE())+1,0)))";
        listaT350ReporteDiarioFTDH = new ArrayList<>();
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable14 = new FacturasSiesaController();
                itemF350coDoctoContable14.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteDiarioFTDH.add(itemF350coDoctoContable14);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteDiarioFTDH;
    }

///datos modal pagina y grafica 15 extension academica
    public void recargarDatosDiaAnualFTDH() throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT          SUM(f470_vlr_bruto)  as total\n"
                    + "                FROM dbo.t350_co_docto_contable \n"
                    + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                    + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                    + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                    + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                    + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                    + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and     f284_descripcion like 'EDUCACIÓN PARA EL TRA%'  and\n"
                    + "                 dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(yy,DATEDIFF(yy,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(yy,0,DATEADD(yy,DATEDIFF(yy,0,GETDATE())+1,0)))\n"
                    + "                ;");

            listaT350ReporteAnualFTDH = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable15 = new FacturasSiesaController();
                itemF350coDoctoContable15.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteAnualFTDH.add(itemF350coDoctoContable15);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

///datos modal pagina y grafica 16 extension academica
    public void recargarDatosDiaAnualExt() throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT          SUM(f470_vlr_bruto)  as total\n"
                    + "                FROM dbo.t350_co_docto_contable \n"
                    + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                    + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                    + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                    + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                    + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                    + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and f284_descripcion = 'CURSOS DE EXTENSION ACADEMICA'  and\n"
                    + "                 dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(yy,DATEDIFF(yy,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(yy,0,DATEADD(yy,DATEDIFF(yy,0,GETDATE())+1,0)))\n"
                    + "                ;");

            listaT350ReporteAnualExt = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable16 = new FacturasSiesaController();
                itemF350coDoctoContable16.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteAnualExt.add(itemF350coDoctoContable16);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

///datos modal pagina y grafica 17 extension academica
    public void recargarDatosDiaActualFTDH() throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT          SUM(f470_vlr_bruto)  as total\n"
                    + "                FROM dbo.t350_co_docto_contable \n"
                    + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                    + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                    + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                    + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                    + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                    + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and     f284_descripcion like 'EDUCACIÓN PARA EL TRA%'  and\n"
                    + "                 dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(dd,DATEDIFF(dd,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(dd,DATEDIFF(dd,0,GETDATE()),1))\n"
                    + "                ;");

            listaT350ReporteActualFTDH = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable17 = new FacturasSiesaController();
                itemF350coDoctoContable17.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteActualFTDH.add(itemF350coDoctoContable17);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

///datos modal pagina y grafica 18 extension academica
    public void recargarDatosDiaActuallExt() throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT          SUM(f470_vlr_bruto)  as total\n"
                    + "                FROM dbo.t350_co_docto_contable \n"
                    + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                    + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                    + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                    + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                    + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                    + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and f284_descripcion = 'CURSOS DE EXTENSION ACADEMICA'  and\n"
                    + "                 dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(dd,DATEDIFF(dd,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(dd,DATEDIFF(dd,0,GETDATE()),1))\n"
                    + "                ;");

            listaT350ReporteActualExt = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable18 = new FacturasSiesaController();
                itemF350coDoctoContable18.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteActualExt.add(itemF350coDoctoContable18);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

///datos modal pagina y grafica 19 extension academica
    public void recargarDatosDiaAnteriorFTDH() throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT          SUM(f470_vlr_bruto)  as total\n"
                    + "                FROM dbo.t350_co_docto_contable \n"
                    + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                    + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                    + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                    + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                    + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                    + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and     f284_descripcion like 'EDUCACIÓN PARA EL TRA%'  and\n"
                    + "                 dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(d,-2,GETDATE()) AND DATEADD(d,-1,GETDATE())\n"
                    + "                ;");

            listaT350ReporteAnteriorFTDH = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable19 = new FacturasSiesaController();
                itemF350coDoctoContable19.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteAnteriorFTDH.add(itemF350coDoctoContable19);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

///datos modal pagina y grafica 20 extension academica
    public void recargarDatosDiaAnteriorExt() throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT          SUM(f470_vlr_bruto)  as total\n"
                    + "                FROM dbo.t350_co_docto_contable \n"
                    + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                    + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                    + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                    + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                    + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                    + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and f284_descripcion = 'CURSOS DE EXTENSION ACADEMICA'  and\n"
                    + "                 dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(d,-2,GETDATE()) AND DATEADD(d,-1,GETDATE())\n"
                    + "                ;");

            listaT350ReporteAnteriorExt = new ArrayList<>();
            rs = st.executeQuery();
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable20 = new FacturasSiesaController();
                itemF350coDoctoContable20.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteAnteriorExt.add(itemF350coDoctoContable20);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

///datos mensual por unidad de negocio 21
    public List<FacturasSiesaController> recargarDatosMenUni() throws SQLException, IOException {
        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT          SUM(f470_vlr_bruto)  as total,f281_descripcion\n"
                + "                FROM dbo.t350_co_docto_contable \n"
                + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and  f281_descripcion !='EXT. ACADEMICAS' AND\n"
                + "                 dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(mm,DATEDIFF(mm,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(mm,0,DATEADD(mm,DATEDIFF(mm,0,GETDATE())+1,0)))\n"
                + "                GROUP by f281_descripcion;";
        listaT350ReporteMensual = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable21 = new FacturasSiesaController();
                itemF350coDoctoContable21.setDescrip(rs.getString("f281_descripcion"));
                itemF350coDoctoContable21.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteMensual.add(itemF350coDoctoContable21);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteMensual;
    }

///datos mensual por unidad de negocio 22
    public List<FacturasSiesaController> recargarDatosMenUniTo() throws SQLException, IOException {
        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT          SUM(f470_vlr_bruto)  as total\n"
                + "                FROM dbo.t350_co_docto_contable \n"
                + "                inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                + "                inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                + "                inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                + "                inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "                where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV ' and\n"
                + "                 dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(mm,DATEDIFF(mm,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(mm,0,DATEADD(mm,DATEDIFF(mm,0,GETDATE())+1,0)));";
        listaT350ReporteMensualTotal = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable22 = new FacturasSiesaController();
                itemF350coDoctoContable22.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteMensualTotal.add(itemF350coDoctoContable22);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteMensual;
    }

///datos remisiones por terceros unidad de negocio 27
    public List<FacturasSiesaController> recargarDatosRemicionDia() throws SQLException, IOException {
        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT          SUM(f470_vlr_bruto)  as total,f200_razon_social,f470_ind_estado_cm\n"
                + "                FROM dbo.t470_cm_movto_invent\n"
                + "                inner join dbo.t350_co_docto_contable on  dbo.t470_cm_movto_invent.f470_rowid_docto = dbo.t350_co_docto_contable.f350_rowid \n"
                + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "                where dbo.t470_cm_movto_invent.f470_ind_estado_cm = 3 AND f470_rowid_bodega = 3 and f470_id_motivo = 01 and f200_razon_social != 'CENTRO DE FORMACION INTEGRAL PROVIDENCIA' and  \n"
                + "              dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(d,-2,GETDATE()) AND DATEADD(d,-1,GETDATE()) \n"
                + "                group by f200_razon_social,f470_ind_estado_cm;";
        listaT350ReporteDiaAnteriorRemi = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable27 = new FacturasSiesaController();
                itemF350coDoctoContable27.setCliente(rs.getString("f200_razon_social"));
                itemF350coDoctoContable27.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteDiaAnteriorRemi.add(itemF350coDoctoContable27);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteDiaAnteriorRemi;
    }

    ///datos remisiones por terceros unidad de negocio 28
    public List<FacturasSiesaController> recargarDatosRemicionDiaActual() throws SQLException, IOException {
        String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
        Connection conn = DriverManager.getConnection(con);
        Statement stmt = null;
        String sql = "SELECT          SUM(f470_vlr_bruto)  as total,f200_razon_social,f470_ind_estado_cm\n"
                + "                FROM dbo.t470_cm_movto_invent\n"
                + "                inner join dbo.t350_co_docto_contable on  dbo.t470_cm_movto_invent.f470_rowid_docto = dbo.t350_co_docto_contable.f350_rowid \n"
                + "                inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                + "                where dbo.t470_cm_movto_invent.f470_ind_estado_cm = 3 AND f470_rowid_bodega = 3 and f470_id_motivo = 01 and f200_razon_social != 'CENTRO DE FORMACION INTEGRAL PROVIDENCIA' and  \n"
                + "              dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(dd,DATEDIFF(dd,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(dd,DATEDIFF(dd,0,GETDATE()),1))\n"
                + "                group by f200_razon_social,f470_ind_estado_cm;";
        listaT350ReporteDiaActualRemi = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable28 = new FacturasSiesaController();
                itemF350coDoctoContable28.setCliente(rs.getString("f200_razon_social"));
                itemF350coDoctoContable28.setTotal(rs.getBigDecimal("total"));
                listaT350ReporteDiaActualRemi.add(itemF350coDoctoContable28);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return listaT350ReporteDiaActualRemi;
    }
///////////////////////////////////Fin consultas pagina reporte diario///////////////////////////////////////////

/////////////////////////////////// Transcurso mensual///////////////////////////////////////////////////////////
    public void recargarTranscursoMensualConfecci() throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT  \n"
                    + "DATEPART(month, f350_fecha) as mes,\n"
                    + "SUM(f470_vlr_bruto) as total,f281_descripcion\n"
                    + "FROM dbo.t350_co_docto_contable \n"
                    + "inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                    + "inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                    + "inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                    + "inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                    + "inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                    + "where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and f281_descripcion = 'TALLER DE CONFECCION'\n"
                    + "and dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(yy,DATEDIFF(yy,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(yy,0,DATEADD(yy,DATEDIFF(yy,0,GETDATE())+1,0)))\n"
                    + "GROUP by DATEPART(month, f350_fecha),  f281_descripcion");

            listaTranscursoConfecciones = new ArrayList<>();
            rs = st.executeQuery();

            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable34 = new FacturasSiesaController();
                itemF350coDoctoContable34.setMeses(rs.getString("mes"));
                itemF350coDoctoContable34.setTotal(rs.getBigDecimal("total"));
                itemF350coDoctoContable34.setDescrip(rs.getString("f281_descripcion"));

                listaTranscursoConfecciones.add(itemF350coDoctoContable34);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

    public void recargarTranscursoMensualBigBag() throws SQLException {
        try {
            String con = "jdbc:sqlserver://169.55.122.37:20446;databaseName=UnoEE_CFIProvi_Real;User=cfiprovidencia;Password=Cfiprovidencia$12$%";
            Connection conn = DriverManager.getConnection(con);
            Statement stmt = null;
            ResultSet rs;
            PreparedStatement st = conn.prepareStatement("SELECT  \n"
                    + "DATEPART(month, f350_fecha) as mes,\n"
                    + "SUM(f470_vlr_bruto) as total,f281_descripcion\n"
                    + "FROM dbo.t350_co_docto_contable \n"
                    + "inner join dbo.t461_cm_docto_factura_venta on dbo.t350_co_docto_contable.f350_rowid = dbo.t461_cm_docto_factura_venta.f461_rowid_docto\n"
                    + "inner join dbo.t470_cm_movto_invent on  dbo.t461_cm_docto_factura_venta.f461_rowid_docto = dbo.t470_cm_movto_invent.f470_rowid_docto_fact\n"
                    + "inner join dbo.t284_co_ccosto on dbo.t470_cm_movto_invent.f470_rowid_ccosto_movto = dbo.t284_co_ccosto.f284_rowid\n"
                    + "inner join dbo.t281_co_unidades_negocio on dbo.t284_co_ccosto.f284_id_un = dbo.t281_co_unidades_negocio.f281_id\n"
                    + "inner join dbo.t200_mm_terceros on dbo.t350_co_docto_contable.f350_rowid_tercero = dbo.t200_mm_terceros.f200_rowid\n"
                    + "where dbo.t350_co_docto_contable.f350_id_tipo_docto = 'FV '  and f281_descripcion = 'SERVICIOS BIG BAG'\n"
                    + "and dbo.t350_co_docto_contable.f350_fecha BETWEEN DATEADD(yy,DATEDIFF(yy,0,GETDATE()),0) and DATEADD(ms,-3,DATEADD(yy,0,DATEADD(yy,DATEDIFF(yy,0,GETDATE())+1,0)))\n"
                    + "GROUP by DATEPART(month, f350_fecha),  f281_descripcion");

            listaTranscursoBigbag = new ArrayList<>();
            rs = st.executeQuery();

            while (rs.next()) {
                FacturasSiesaController itemF350coDoctoContable34 = new FacturasSiesaController();
                itemF350coDoctoContable34.setMeses(rs.getString("mes"));
                itemF350coDoctoContable34.setTotal(rs.getBigDecimal("total"));
                itemF350coDoctoContable34.setDescrip(rs.getString("f281_descripcion"));

                listaTranscursoBigbag.add(itemF350coDoctoContable34);

            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }
    //////////////////////////fin /////////////////////////////////////

    public void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public List<FacturasSiesaController> getListaT350Prueba() {
        return listaT350Prueba;
    }

    public void setListaT350Prueba(List<FacturasSiesaController> listaT350Prueba) {
        this.listaT350Prueba = listaT350Prueba;
    }

    private PieChartModel pieModel;
    private PieChartModel pieModel23;

    private BarChartModel barModel;
    private BarChartModel barModel2;
    private BarChartModel barBigBag;
    private BarChartModel barFTDH;

    private HorizontalBarChartModel horizontalBarChartModel;
    private PieChartModel pieModelGlobal;
    private HorizontalBarChartModel horizontalBarChartModelGlobal;
    private HorizontalBarChartModel horizontalBarChartModelItemGlobal;
    private HorizontalBarChartModel horizontalBarChartModelItemP;
    private HorizontalBarChartModel horizontalBarChartModelItemPModal;
    private HorizontalBarChartModel horizontalBarChartModelExtension;
    private DonutChartModel donutChartModel;
    private DonutChartModel donutModel2;
    private MeterGaugeChartModel meterGaugeModel1;
    private LineChartModel lineModel2;
    private LineChartModel lineModelBigBag;

    public LineChartModel getLineModelBigBag() {
        return lineModelBigBag;
    }

    public void setLineModelBigBag(LineChartModel lineModelBigBag) {
        this.lineModelBigBag = lineModelBigBag;
    }

    public LineChartModel getLineModel2() {
        return lineModel2;
    }

    public void setLineModel2(LineChartModel lineModel2) {
        this.lineModel2 = lineModel2;
    }

    public BarChartModel getBarFTDH() {
        return barFTDH;
    }

    public void setBarFTDH(BarChartModel barFTDH) {
        this.barFTDH = barFTDH;
    }

    public BarChartModel getBarBigBag() {
        return barBigBag;
    }

    public void setBarBigBag(BarChartModel barBigBag) {
        this.barBigBag = barBigBag;
    }

    public BarChartModel getBarModel2() {
        return barModel2;
    }

    public void setBarModel2(BarChartModel barModel2) {
        this.barModel2 = barModel2;
    }

    public HorizontalBarChartModel getHorizontalBarChartModelExtension() {
        return horizontalBarChartModelExtension;
    }

    public void setHorizontalBarChartModelExtension(HorizontalBarChartModel horizontalBarChartModelExtension) {
        this.horizontalBarChartModelExtension = horizontalBarChartModelExtension;
    }

    public DonutChartModel getDonutChartModel() {
        return donutChartModel;
    }

    public void setDonutChartModel(DonutChartModel donutChartModel) {
        this.donutChartModel = donutChartModel;
    }

    public DonutChartModel getDonutModel2() {
        return donutModel2;
    }

    public void setDonutModel2(DonutChartModel donutModel2) {
        this.donutModel2 = donutModel2;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public HorizontalBarChartModel getHorizontalBarChartModelItemPModal() {
        return horizontalBarChartModelItemPModal;
    }

    public void setHorizontalBarChartModelItemPModal(HorizontalBarChartModel horizontalBarChartModelItemPModal) {
        this.horizontalBarChartModelItemPModal = horizontalBarChartModelItemPModal;
    }

    public PieChartModel getPieModel23() {
        return pieModel23;
    }

    public void setPieModel23(PieChartModel pieModel23) {
        this.pieModel23 = pieModel23;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public HorizontalBarChartModel getHorizontalBarChartModel() {
        return horizontalBarChartModel;
    }

    public void setHorizontalBarChartModel(HorizontalBarChartModel horizontalBarChartModel) {
        this.horizontalBarChartModel = horizontalBarChartModel;
    }

    public PieChartModel getPieModelGlobal() {
        return pieModelGlobal;
    }

    public void setPieModelGlobal(PieChartModel pieModelGlobal) {
        this.pieModelGlobal = pieModelGlobal;
    }

    public HorizontalBarChartModel getHorizontalBarChartModelGlobal() {
        return horizontalBarChartModelGlobal;
    }

    public void setHorizontalBarChartModelGlobal(HorizontalBarChartModel horizontalBarChartModelGlobal) {
        this.horizontalBarChartModelGlobal = horizontalBarChartModelGlobal;
    }

    public HorizontalBarChartModel getHorizontalBarChartModelItemGlobal() {
        return horizontalBarChartModelItemGlobal;
    }

    public void setHorizontalBarChartModelItemGlobal(HorizontalBarChartModel horizontalBarChartModelItemGlobal) {
        this.horizontalBarChartModelItemGlobal = horizontalBarChartModelItemGlobal;
    }

    public HorizontalBarChartModel getHorizontalBarChartModelItemP() {
        return horizontalBarChartModelItemP;
    }

    public void setHorizontalBarChartModelItemP(HorizontalBarChartModel horizontalBarChartModelItemP) {
        this.horizontalBarChartModelItemP = horizontalBarChartModelItemP;
    }

    public MeterGaugeChartModel getMeterGaugeModel1() {
        return meterGaugeModel1;
    }

    public void setMeterGaugeModel1(MeterGaugeChartModel meterGaugeModel1) {
        this.meterGaugeModel1 = meterGaugeModel1;
    }
///pagina 

    public void listarGlobal() {

        try {
            itemF350coDoctoContableActual = new FacturasSiesaController();
            listaT350PruebaDos = listaT350PruebatREC;
            initBarModelItem();
            listaT350PruebaDos23 = listaT350PruebatREC23;
            graficarGolbal23();
//            recargarLista();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
////modal

    public void listarModal() {

        try {
            itemF350coDoctoContableActual = new FacturasSiesaController();

            //grafica de medida
            listaT350ReporteDiarioExtensionGraficap = listaT350ReporteAnualGraficas;
            initMeterGaugeModel();

            novedadFacturacionGraficaList = novedadFacturacionRedondeoList;
            //listaT350ReporteDiarioConfeccionesGraficas = listaT350ReporteDiarioConfecciones;
            initBarModelItem();
            ///grafica diaria

            listaT350ReporteDiarioExtensionGrafica = listaT350ReporteDiarioExtension;
            graficarExtensión();
            //grafica global
//            listaT350ReporteDiaActualGrafica = listaT350ReporteDiaActual;
//            graficarGolbal23();

            //Grafica BigBag
            listaT350ReporteDiarioBigBagGraficas = listaT350ReporteDiarioBigBag;
            initBarBigBag();
            //Grafica FTDH

            listaT350ReporteDiarioFTDHGraficas = listaT350ReporteDiarioFTDH;
            initBarFTDH();

            listaTranscursoConfeccionesGrafica = listaTranscursoConfecciones;
            createLineModels();

            listaTranscursoBigBagGrafica = listaTranscursoBigbag;
            ModelBigBag();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //grafica anual en procentaje
    private MeterGaugeChartModel initMeterGaugeModel() {
        meterGaugeModel1 = new MeterGaugeChartModel();
        for (FacturasSiesaController fact6 : listaT350ReporteDiarioExtensionGraficap) {
            meterGaugeModel1.setValue(fact6.getTotal().toBigInteger());
            meterGaugeModel1.setMouseoverHighlight(true);
            meterGaugeModel1.setLabelHeightAdjust(0);
//            meterGaugeModel1.setTitle("EL PROCENTAJE ES:" + fact6.getTotal().toBigInteger()+ "%");
            meterGaugeModel1.setTitle("" + fact6.getTotal().toBigInteger() + "%");
            meterGaugeModel1.setIntervals(Arrays.asList(10, 40, 60, 100));
            meterGaugeModel1.setSeriesColors("cc6666,E7E658,93b75f,66cc66");
            meterGaugeModel1.setGaugeLabel(" ");
            meterGaugeModel1.setGaugeLabelPosition("bottom");

        }
        return meterGaugeModel1;
    }

//grafica confecciones meta mensual
    private BarChartModel initBarModelItem() {
        barModel2 = new BarChartModel();
        for (FacturasSiesaController fact : novedadFacturacionGraficaList) {
            ChartSeries serie = new ChartSeries();
            Axis yAxis = barModel2.getAxis(AxisType.Y);
            yAxis.setMin(0);
            yAxis.setTickFormat("% 'd");
            serie.setLabel("FACTURACION REAL");
            serie.set(" ", fact.getValorVisConfecciones());
            ChartSeries serie2 = new ChartSeries();
            serie2.setLabel("PRESUPUESTADO");
            serie2.set(" ", getTotalConfecciones());
            barModel2.addSeries(serie);
            barModel2.addSeries(serie2);
            barModel2.setLegendPosition("se");
            barModel2.setShowPointLabels(true);
            barModel2.setAnimate(true);
            //horizontalBarChartModelItemP.setExtender("removeLegend");

        }
        return barModel2;
    }

//grafica extención academica meta mensual
    public BarChartModel graficarExtensión() {
        barModel = new BarChartModel();
        for (FacturasSiesaController fact4 : listaT350ReporteDiarioExtensionGrafica) {
            ChartSeries serie1 = new ChartSeries();
            Axis yAxis = barModel.getAxis(AxisType.Y);
            yAxis.setMin(0);
            yAxis.setTickFormat("% 'd");
            serie1.setLabel(fact4.getDescrip());
            serie1.set(" ", fact4.getTotal());
            ChartSeries serie2 = new ChartSeries();
            serie2.setLabel("PRESUPUESTADO");
            serie2.set(" ", getTotalExtencion());
            barModel.addSeries(serie1);
            barModel.addSeries(serie2);
            barModel.setLegendPosition("se");
            barModel.setShowPointLabels(true);
            barModel.setAnimate(true);

        }
        return barModel;

    }

//grafica BigBag meta mensual
    private BarChartModel initBarBigBag() {
        barBigBag = new BarChartModel();
        for (FacturasSiesaController fact : listaT350ReporteDiarioBigBagGraficas) {
            ChartSeries serie = new ChartSeries();
            Axis yAxis = barBigBag.getAxis(AxisType.Y);
            yAxis.setMin(0);
            yAxis.setTickFormat("% 'd");
            serie.setLabel(fact.getDescrip());
            serie.set(" ", fact.getTotal());
            ChartSeries serie2 = new ChartSeries();
            serie2.setLabel("PRESUPUESTADO");
            serie2.set(" ", totalBigBag);
            barBigBag.addSeries(serie);
            barBigBag.addSeries(serie2);
            barBigBag.setLegendPosition("se");
            barBigBag.setShowPointLabels(true);
            barBigBag.setAnimate(true);
            //horizontalBarChartModelItemP.setExtender("removeLegend");

        }

        return barBigBag;
    }

//grafica FTDH meta mensual
    private BarChartModel initBarFTDH() {
        barFTDH = new BarChartModel();
        for (FacturasSiesaController fact : listaT350ReporteDiarioFTDHGraficas) {
            ChartSeries serie = new ChartSeries();
            Axis yAxis = barFTDH.getAxis(AxisType.Y);
            yAxis.setMin(0);
            yAxis.setTickFormat("% 'd");
            serie.setLabel("FTDH");
            serie.set(" ", fact.getTotal());
            ChartSeries serie2 = new ChartSeries();
            serie2.setLabel("PRESUPUESTADO");
            serie2.set(" ", totalFTDH);
            barFTDH.addSeries(serie);
            barFTDH.addSeries(serie2);
            barFTDH.setLegendPosition("se");
            barFTDH.setShowPointLabels(true);
            barFTDH.setAnimate(true);

            //horizontalBarChartModelItemP.setExtender("removeLegend");
        }

        return barFTDH;
    }

    private BarChartModel initBarModelItemModal() {
        horizontalBarChartModelItemPModal = new HorizontalBarChartModel();
        for (FacturasSiesaController fact3 : listaT350ReporteDiarioGrafica) {
            ChartSeries serie = new ChartSeries();
            serie.setLabel(fact3.getDescrip());
            serie.set("", fact3.getTotal());
            horizontalBarChartModelItemPModal.addSeries(serie);
            horizontalBarChartModelItemPModal.setLegendPosition("se");
            horizontalBarChartModelItemPModal.setShowPointLabels(true);
            horizontalBarChartModelItemPModal.setAnimate(true);
            //horizontalBarChartModelItemP.setExtender("removeLegend");

        }

        return horizontalBarChartModelItemPModal;
    }

    public HorizontalBarChartModel graficarGolbal23() {
        horizontalBarChartModel = new HorizontalBarChartModel();
        for (FacturasSiesaController fact2 : listaT350PruebaDos23) {
            ChartSeries serie = new ChartSeries();
            Axis yAxis = horizontalBarChartModel.getAxis(AxisType.Y);
            yAxis.setMin(0);
            yAxis.setTickFormat("% 'd");
            serie.setLabel(fact2.getDescrip());
            serie.set("", fact2.getTotal());
            horizontalBarChartModel.addSeries(serie);
            horizontalBarChartModel.setLegendPosition("se");
            horizontalBarChartModel.setShowPointLabels(true);
            horizontalBarChartModel.setAnimate(true);

        }
        return horizontalBarChartModel;

    }
// recorrido mensual confecciones

    private LineChartModel initCategoryModel() {
        lineModel2 = new LineChartModel();

        for (FacturasSiesaController factor : listaTranscursoConfeccionesGrafica) {
            LineChartSeries Facturado = new LineChartSeries();
            Facturado.setLabel("FACTURADO");
            Facturado.set(factor.getMeses(), factor.getTotal());
            Facturado.setXaxis(AxisType.X);
            lineModel2.addSeries(Facturado);
        }

        LineChartSeries Presupuesto = new LineChartSeries();
        Presupuesto.setLabel("PRESUPUESTADO");
        Presupuesto.set("ENERO", 745888952);
        Presupuesto.set("FEBRERO", 1364597925);
        Presupuesto.set("MARZO", 1805315991);
        Presupuesto.set("ABRIL", 1800000000);
        Presupuesto.set("MAYO", 1600000000);
        Presupuesto.set("JUNIO", 1650518621);
        Presupuesto.set("JULIO", 1900000000);
//            Presupuesto.set("AGOSTO", 1764869949);
//            Presupuesto.set("SEPTIEMBRE", 1537191011);
//            Presupuesto.set("OCTUBRE", 1609890990);
//            Presupuesto.set("NOVIEMBRE", 1758610071);
//            Presupuesto.set("DICIEMBRE", 1700593588);
        Presupuesto.setXaxis(AxisType.X2);
        lineModel2.addSeries(Presupuesto);
        return lineModel2;

    }

    private void createLineModels() {
        lineModel2 = initCategoryModel();
        lineModel2.setAnimate(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis(""));
        lineModel2.getAxes().put(AxisType.X2, new CategoryAxis(""));
        lineModel2.setLegendPosition("se");
        lineModel2.setZoom(true);

        Axis yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setTickFormat("% 'd");
        yAxis.setMin(0);

        Axis XAxis = lineModel2.getAxis(AxisType.X);
        XAxis.setTickFormat("% 'd");
        XAxis.setMin(0);
        XAxis.setMax(12);

        Axis X2Axis = lineModel2.getAxis(AxisType.X2);
        X2Axis.setTickFormat("% 'd");
        X2Axis.setMin(0);
        X2Axis.setMax(12);
        lineModel2.getAxes().put(AxisType.Y, yAxis);

    }

    // recorrido mensual confecciones
    private LineChartModel recorridoMensualBigBag() {
        lineModelBigBag = new LineChartModel();

        for (FacturasSiesaController factor : listaTranscursoBigBagGrafica) {
            LineChartSeries Facturado = new LineChartSeries();
            Facturado.setLabel("FACTURADO");
            Facturado.set(factor.getMeses(), factor.getTotal());
            Facturado.setXaxis(AxisType.X);
            lineModelBigBag.addSeries(Facturado);
        }

        LineChartSeries Presupuesto = new LineChartSeries();
        Presupuesto.setLabel("PRESUPUESTADO");
        Presupuesto.set("ENERO", 107860436);
        Presupuesto.set("FEBRERO", 73531156);
        Presupuesto.set("MARZO", 102459180);
        Presupuesto.set("ABRIL", 85756983);
        Presupuesto.set("MAYO", 83018409);
        Presupuesto.set("JUNIO", 78465042);
        Presupuesto.set("JULIO", 83158498);
//            Presupuesto.set("AGOSTO", 118150701);
//            Presupuesto.set("SEPTIEMBRE", 108751461);
//            Presupuesto.set("OCTUBRE", 83558314);
//            Presupuesto.set("NOVIEMBRE", 88230787);
//            Presupuesto.set("DICIEMBRE", 92697683);
        Presupuesto.setXaxis(AxisType.X2);
        lineModelBigBag.addSeries(Presupuesto);
        return lineModelBigBag;

    }

    private void ModelBigBag() {
        lineModelBigBag = recorridoMensualBigBag();
        lineModelBigBag.setAnimate(true);
        lineModelBigBag.getAxes().put(AxisType.X, new CategoryAxis(""));
        lineModelBigBag.getAxes().put(AxisType.X2, new CategoryAxis(""));
        lineModelBigBag.setLegendPosition("se");
        lineModelBigBag.setZoom(true);

        Axis yAxis = lineModelBigBag.getAxis(AxisType.Y);
        yAxis.setTickFormat("% 'd");
        yAxis.setMin(0);

        Axis XAxis = lineModelBigBag.getAxis(AxisType.X);
        XAxis.setTickFormat("% 'd");
        XAxis.setMin(0);
        XAxis.setMax(12);

        Axis X2Axis = lineModelBigBag.getAxis(AxisType.X2);
        X2Axis.setTickFormat("% 'd");
        X2Axis.setMin(0);
        X2Axis.setMax(12);
        lineModelBigBag.getAxes().put(AxisType.Y, yAxis);

    }
}
