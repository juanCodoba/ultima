/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.Area;
import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.CronogramaComponente;
import com.proyectoCFIP.entities.CronogramaManteDisCon;
import com.proyectoCFIP.entities.CronogramaManteDispositivo;
import com.proyectoCFIP.entities.CronogramaMantenimientos;
import com.proyectoCFIP.entities.DiagnosticoMantenimiento;
import com.proyectoCFIP.entities.EstadoCronograma;
import com.proyectoCFIP.entities.EstadoTicket;
import com.proyectoCFIP.entities.ReporteSiesa;
import com.proyectoCFIP.entities.Roles;
import com.proyectoCFIP.entities.SistemasEquiposUsuarios;
import com.proyectoCFIP.entities.Tipo;
import com.proyectoCFIP.entities.TipoMantenimiento;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.entities.Valoracion;
import com.proyectoCFIP.sessions.ComputadorFacade;
import com.proyectoCFIP.sessions.CronogramaComponenteFacade;
import com.proyectoCFIP.sessions.CronogramaManteDisConFacade;
import com.proyectoCFIP.sessions.CronogramaManteDispositivoFacade;
import com.proyectoCFIP.sessions.CronogramaMantenimientosFacade;
import com.proyectoCFIP.sessions.DiagnosticoMantenimientoFacade;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.ReporteSiesaFacade;
import com.proyectoCFIP.sessions.SistemasEquiposUsuariosFacade;
import com.proyectoCFIP.sessions.TipoMantenimientoFacade;
import com.proyectoCFIP.sessions.UsuarioFacade;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.el.MethodExpression;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class CronogramaController implements Serializable {

    @EJB
    private CronogramaMantenimientosFacade CronogramaMantenimientosFacade;
    @EJB
    private ComputadorFacade computadorFacade;
    @EJB
    EmailSessionBean emailBean;
    @EJB
    private DiagnosticoMantenimientoFacade diagnosticoMantenimientoFacade;
    @EJB
    private TipoMantenimientoFacade tipoMantenimientoFacade;
    private List<CronogramaMantenimientos> listaCronogramaMantenimientos = null;
    private List<CronogramaMantenimientos> listaCronogramaMantenimientosC = null;
    private List<CronogramaMantenimientos> listaCronogramaMantenimientosP = null;
    private List<Computador> listaComputador = null;
    private Computador computadorActual;
    private CronogramaMantenimientos cronogramaMantenimientosActual;
    private List<DiagnosticoMantenimiento> listaDiagnosticoMantenimiento = null;
    private DiagnosticoMantenimiento diagnosticoMantenimientoActual;
    private Usuario usuarioActual;
    private TipoMantenimiento tipoMantenimientoActual;
    private UsuarioFacade usuarioFacade;
    private Tipo tipoActual;
    private List<Computador> listaComputadorTipoDocente = null;
    private List<Computador> listaComputadorTipoUsuario = null;
    public String ticket = "";
    public String nombreImagen;
    public Date fechaParametro1;
    public Date fechaParametro2;
    public Area area;
    @EJB
    private ReporteSiesaFacade reporteSiesaFacade;
    private ReporteSiesa reporteSiesaActual;
    private List<ReporteSiesa> reporteSiesaList;
    @EJB
    private SistemasEquiposUsuariosFacade sistemasEquiposUsuariosFacade;
    private List<SistemasEquiposUsuarios> listaSistemasEquiposUsuarios = null;
    private SistemasEquiposUsuarios sistemasEquiposUsuariosActual;
    @EJB
    private CronogramaComponenteFacade cronogramaComponenteActualFacade;
    private CronogramaComponente cronogramaComponenteActual;
    private List<CronogramaComponente> listaCronogramaComponente;

    private EstadoCronograma estadoCronogramaActual;

    /// Plan de mejora
    private List<CronogramaMantenimientos> listaTicketsSinCerrar;

    public boolean isMantenimientoPreventivo() {
        return cronogramaMantenimientosActual.getIdTipoMantenimiento() == null ? false : cronogramaMantenimientosActual.getIdTipoMantenimiento().getIdTipoMantenimiento() == (short) 2;
    }

    public boolean isMantenimientoCorrectivoPreventivo() {
        return cronogramaMantenimientosActual.getIdTipoMantenimiento() == null ? false : cronogramaMantenimientosActual.getIdTipoMantenimiento().getIdTipoMantenimiento() == (short) 3;
    }
    //Select one computador

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public ComputadorFacade getComputadorFacade() {
        return computadorFacade;
    }

    public void setComputadorFacade(ComputadorFacade computadorFacade) {
        this.computadorFacade = computadorFacade;
    }

    public List<Computador> getListaComputadorSelectOne() {
        return getComputadorFacade().consultaUsuario(usuarioActual);
    }

    public List<Computador> getListaComputadoresAutoComplete(String query) {
        try {
            return getComputadorFacade().findByNombre(query);
        } catch (Exception ex) {
            Logger.getLogger(CronogramaMantenimientos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Computador> getListaComputadorProgramado() {
        listaComputador = new ArrayList<>();
        listaComputador = getComputadorFacade().findAll();
        //  listaComputador = getComputadorFacade().consultaComputadorProgramado(false, true);
        return listaComputador;
    }

    public Computador getComputadorActual() {
        return computadorActual;
    }

    public void setComputadorActual(Computador computadorActual) {
        this.computadorActual = computadorActual;
    }

    public String getTicket() {
        return ticket;
    }

    public EstadoCronograma getEstadoCronogramaActual() {
        return estadoCronogramaActual;
    }

    public void setEstadoCronogramaActual(EstadoCronograma estadoCronogramaActual) {
        this.estadoCronogramaActual = estadoCronogramaActual;
    }

    public CronogramaController() {
    }
    //Reportes siesa

    public ReporteSiesaFacade getReporteSiesaFacade() {
        return reporteSiesaFacade;
    }

    public void setReporteSiesaFacade(ReporteSiesaFacade reporteSiesaFacade) {
        this.reporteSiesaFacade = reporteSiesaFacade;
    }

    public ReporteSiesa getReporteSiesaActual() {
        return reporteSiesaActual;
    }

    public void setReporteSiesaActual(ReporteSiesa reporteSiesaActual) {
        this.reporteSiesaActual = reporteSiesaActual;
    }

    public List<ReporteSiesa> getReporteSiesaList() {
        return reporteSiesaList = getReporteSiesaFacade().findAll();
    }

    public void setReporteSiesaList(List<ReporteSiesa> reporteSiesaList) {
        this.reporteSiesaList = reporteSiesaList;
    }

    public List<ReporteSiesa> getReporteSiesaListUser() {
        return reporteSiesaList = getReporteSiesaFacade().consultaReporteEstadoUser(usuarioActual);
    }

    // Cronograma
    public List<CronogramaMantenimientos> getListaCronogramaMantenimientoEstado() {
        listaCronogramaMantenimientos = new ArrayList<>();
        listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaEstadoTicketsDiagnostico();
        return listaCronogramaMantenimientos;
    }

    public List<CronogramaMantenimientos> getListaCronogramaMantenimientos() {
        listaCronogramaMantenimientos = new ArrayList<>();
        listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().findAll();
        return listaCronogramaMantenimientos;
    }

    public List<Computador> getListaReport() {
        return getComputadorFacade().consultaReport();

    }

    public List<CronogramaMantenimientos> getListaMantenimientosCompu() {
        listaCronogramaMantenimientos = new ArrayList<>();
        return listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaCompu(new EstadoCronograma(2), computadorActual);
    }

    public List<CronogramaMantenimientos> getListaMantenimientosPendiente() {
        listaCronogramaMantenimientos = new ArrayList<>();
        return listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaCronogramaTipoPendiente(new EstadoCronograma(2), tipoMantenimientoActual);
    }

    public List<CronogramaMantenimientos> getListaMantenimientosTotalTipo() {
        listaCronogramaMantenimientos = new ArrayList<>();
        return listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaTotalTipoPrev();
    }

    public List<CronogramaMantenimientos> getListaMantenimientosTotalTipoCorre() {
        listaCronogramaMantenimientos = new ArrayList<>();
        return listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaTotalTipoCorre();
    }

    public List<DiagnosticoMantenimiento> getListaMantenimientosTotalCR() {
        listaDiagnosticoMantenimiento = new ArrayList<>();
        return listaDiagnosticoMantenimiento = getDiagnosticoMantenimientoFacade().consultaTotalCR();
    }
    
        public List<CronogramaMantenimientos> getListaMantenimientosEditCR() {
        listaCronogramaMantenimientos = new ArrayList<>();
        return listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaTotalCR();
    }

    public List<CronogramaMantenimientos> getListaReporteCorrectivo() {
        listaCronogramaMantenimientos = new ArrayList<>();
        //  return listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().findAll();
        return listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaReporteCorrectivo(fechaParametro1, fechaParametro2);
    }

    public List<CronogramaMantenimientos> getListaReportePreventivo() {
        listaCronogramaMantenimientos = new ArrayList<>();
        return listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaReportePreventivo(fechaParametro1, fechaParametro2);
    }

    public List<CronogramaMantenimientos> getListaReporteCorrectivoEstrellas() {
        listaCronogramaMantenimientos = new ArrayList<>();
        return listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaReporteCorrectivoEstrellas(fechaParametro1, fechaParametro2);
    }

    public List<CronogramaMantenimientos> getListaMantenimientosP() {
        listaCronogramaMantenimientos = new ArrayList<>();
        return listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaCronogramaTipoPendiente(new EstadoCronograma(2), new TipoMantenimiento(2));
    }

    public List<CronogramaMantenimientos> getListaMantenimientosC() {
        listaCronogramaMantenimientos = new ArrayList<>();
        return listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaCronogramaTipoPendiente(new EstadoCronograma(2), new TipoMantenimiento(1));
    }

    public List<CronogramaMantenimientos> getListaMantenimientosPyC() {
        listaCronogramaMantenimientos = new ArrayList<>();
        return listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaCronogramaTipoPendiente(new EstadoCronograma(2), new TipoMantenimiento(3));
    }

    public boolean isAbierto() {
        return cronogramaMantenimientosActual.getEstadoMantenimiento().getIdEstado() == null ? false
                : cronogramaMantenimientosActual.getEstadoMantenimiento().getIdEstado() == (short) 3;
    }

    public boolean isRevisado() {
        return cronogramaMantenimientosActual.getEstadoMantenimiento().getIdEstado() == null ? false
                : cronogramaMantenimientosActual.getEstadoMantenimiento().getIdEstado() == (short) 4;
    }
    // Plan de mejora continua

    public List<CronogramaMantenimientos> getListaTicketsSinCerrar() {
        listaTicketsSinCerrar = null;
        return listaTicketsSinCerrar = getCronogramaMantenimientosFacade().consultaEstadoTickets(usuarioActual);
    }

    public void setListaTicketsSinCerrar(List<CronogramaMantenimientos> listaTicketsSinCerrar) {
        this.listaTicketsSinCerrar = listaTicketsSinCerrar;
    }

    public String preparePaginaPrincipal() {
        int tamaño = getListaTicketsSinCerrar().size();
        if (tamaño < 1) {
            return "/usuario/modSoporteIT/paginaPrincipal";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Cierre de tickets", "Tienes tickets sin cerrar, para continuar por favor valora cada servicio");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "/usuario/modSoporteIT/ticketsValoraciones/listaTicketSinCerrarDocentes";
        }
    }

    public CronogramaMantenimientosFacade getCronogramaMantenimientosFacade() {
        return CronogramaMantenimientosFacade;
    }

    public CronogramaMantenimientos getCronogramaMantenimientosActual() {
        return cronogramaMantenimientosActual;
    }

    public void setCronogramaMantenimientosActual(CronogramaMantenimientos cronogramaMantenimientosActual) {
        this.cronogramaMantenimientosActual = cronogramaMantenimientosActual;
    }

    public List<CronogramaMantenimientos> getListaCronogramaMantenimientosC() {
        return getCronogramaMantenimientosFacade().consultaTotalTipo(new TipoMantenimiento(1));
    }

    public void setListaCronogramaMantenimientosC(List<CronogramaMantenimientos> listaCronogramaMantenimientosC) {
        this.listaCronogramaMantenimientosC = listaCronogramaMantenimientosC;
    }

    public List<CronogramaMantenimientos> getListaCronogramaMantenimientosP() {
        return listaCronogramaMantenimientosP;
    }

    public void setListaCronogramaMantenimientosP(List<CronogramaMantenimientos> listaCronogramaMantenimientosP) {
        this.listaCronogramaMantenimientosP = listaCronogramaMantenimientosP;
    }

    //tipo
    public Tipo getTipoActual() {
        return tipoActual;
    }

    public void setTipoActual(Tipo tipoActual) {
        this.tipoActual = tipoActual;
    }

    public void llenarListaTipoDocente() {
        listaComputadorTipoDocente = getComputadorFacade().consultaArea(new Area(1, "Academica"), tipoActual);
    }

    public void llenarListaTipoUsuario() {
        listaComputadorTipoUsuario = getComputadorFacade().consultaUsuarioTipo(usuarioActual, tipoActual);
    }

    public List<Computador> getListaComputadorTipoDocente() {
        return listaComputadorTipoDocente;
    }

    public void setListaComputadorTipoDocente(List<Computador> listaComputadorTipoDocente) {
        this.listaComputadorTipoDocente = listaComputadorTipoDocente;
    }

    public List<Computador> getListaComputadorTipoUsuario() {
        return listaComputadorTipoUsuario;
    }

    public void setListaComputadorTipoUsuario(List<Computador> listaComputadorTipoUsuario) {
        this.listaComputadorTipoUsuario = listaComputadorTipoUsuario;
    }

    //Select one Usuario
    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public List<Usuario> getListaUsuarioSelectOne() {
        return getUsuarioFacade().findAll();
    }
    //prestamo equipos

    public SistemasEquiposUsuariosFacade getSistemasEquiposUsuariosFacade() {
        return sistemasEquiposUsuariosFacade;
    }

    public void setSistemasEquiposUsuariosFacade(SistemasEquiposUsuariosFacade sistemasEquiposUsuariosFacade) {
        this.sistemasEquiposUsuariosFacade = sistemasEquiposUsuariosFacade;
    }

    public List<SistemasEquiposUsuarios> getListaSistemasEquiposUsuarios() {
        return listaSistemasEquiposUsuarios;
    }

    public void setListaSistemasEquiposUsuarios(List<SistemasEquiposUsuarios> listaSistemasEquiposUsuarios) {
        this.listaSistemasEquiposUsuarios = listaSistemasEquiposUsuarios;
    }

    public SistemasEquiposUsuarios getSistemasEquiposUsuariosActual() {
        return sistemasEquiposUsuariosActual;
    }

    public void setSistemasEquiposUsuariosActual(SistemasEquiposUsuarios sistemasEquiposUsuariosActual) {
        this.sistemasEquiposUsuariosActual = sistemasEquiposUsuariosActual;
    }
    //Cronograma Componente

    public CronogramaComponenteFacade getCronogramaComponenteActualFacade() {
        return cronogramaComponenteActualFacade;
    }

    public void setCronogramaComponenteActualFacade(CronogramaComponenteFacade cronogramaComponenteActualFacade) {
        this.cronogramaComponenteActualFacade = cronogramaComponenteActualFacade;
    }

    public CronogramaComponente getCronogramaComponenteActual() {
        return cronogramaComponenteActual;
    }

    public void setCronogramaComponenteActual(CronogramaComponente cronogramaComponenteActual) {
        this.cronogramaComponenteActual = cronogramaComponenteActual;
    }

    public List<CronogramaComponente> getListaCronogramaComponente() {
        return listaCronogramaComponente = getCronogramaComponenteActualFacade().findAll();
    }

    public void setListaCronogramaComponente(List<CronogramaComponente> listaCronogramaComponente) {
        this.listaCronogramaComponente = listaCronogramaComponente;
    }

    //diagnostico
    public DiagnosticoMantenimientoFacade getDiagnosticoMantenimientoFacade() {
        return diagnosticoMantenimientoFacade;
    }

    public void setDiagnosticoMantenimientoFacade(DiagnosticoMantenimientoFacade diagnosticoMantenimientoFacade) {
        this.diagnosticoMantenimientoFacade = diagnosticoMantenimientoFacade;
    }

    public List<DiagnosticoMantenimiento> getListaDiagnosticoTicket() {
        return getDiagnosticoMantenimientoFacade().consultaTicket(cronogramaMantenimientosActual);
    }

    public List<DiagnosticoMantenimiento> getListaDiagnosticoMantenimiento() {
        listaDiagnosticoMantenimiento = null;
        if (listaDiagnosticoMantenimiento == null) {
            try {
                listaDiagnosticoMantenimiento = getDiagnosticoMantenimientoFacade().diagnosticosTotales();
            } catch (Exception e) {
                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            }
        }
        return listaDiagnosticoMantenimiento;

    }

    public List<DiagnosticoMantenimiento> getListaDiagnosticoCompu() {
        listaDiagnosticoMantenimiento = getDiagnosticoMantenimientoFacade().consultaDiagnostico(computadorActual);
        return listaDiagnosticoMantenimiento;
    }

    public void setListaDiagnosticoMantenimiento(List<DiagnosticoMantenimiento> listaDiagnosticoMantenimiento) {
        this.listaDiagnosticoMantenimiento = listaDiagnosticoMantenimiento;
    }

    public DiagnosticoMantenimiento getDiagnosticoMantenimientoActual() {
        return diagnosticoMantenimientoActual;
    }

    public TipoMantenimientoFacade getTipoMantenimientoFacade() {
        return tipoMantenimientoFacade;
    }

    public void setTipoMantenimientoFacade(TipoMantenimientoFacade tipoMantenimientoFacade) {
        this.tipoMantenimientoFacade = tipoMantenimientoFacade;
    }

    public List<TipoMantenimiento> listaTipoMantenimientoSelectOne() {

        return getTipoMantenimientoFacade().findAll();
    }

    public void setDiagnosticoMantenimientoActual(DiagnosticoMantenimiento diagnosticoMantenimientoActual) {
        this.diagnosticoMantenimientoActual = diagnosticoMantenimientoActual;
    }

    private void recargarLista() {
        listaCronogramaMantenimientos = null;
        listaCronogramaMantenimientosC = null;
        listaCronogramaMantenimientosP = null;
    }

    public void viewDiagnosticoCronograma() {
        diagnosticoMantenimientoActual = null;
    }

    public void recargarListaCronogramaPendiente() {
        listaCronogramaMantenimientos = null;
        listaCronogramaMantenimientos.addAll(getCronogramaMantenimientosFacade().consultaCronogramaPendiente(2));
    }

    public String prepareCreateReportarFalloDocente() {
        nombreImagen = null;
        tipoActual = new Tipo();
        cronogramaMantenimientosActual = new CronogramaMantenimientos();
        listaCronogramaMantenimientosC = null;
        return "/Docente/docenteCrearIncidente";
    }

    public String prepareCreateReportarFallo() {
        nombreImagen = null;
        tipoActual = new Tipo();
        cronogramaMantenimientosActual = new CronogramaMantenimientos();
        listaCronogramaMantenimientosC = null;
        return "/usuario/modSoporteIT/reportarFallo/crearFallo";
    }

    public String prepareViewEstadoTickets() {
        listaCronogramaMantenimientos = new ArrayList<>();
        return "/usuario/modSoporteIT/ticketsValoraciones/listaEstadoTicket";
    }

    public String prepareEdit() {
        return "/administrador/modSoporteIT/calendarioMantenimiento/correctivos/editarFallo";
    }

    public String prepareSolucionCorrectivo() {
        tipoMantenimientoActual = new TipoMantenimiento(1);
        diagnosticoMantenimientoActual = new DiagnosticoMantenimiento();
        return "/administrador/modSoporteIT/calendarioMantenimiento/correctivos/crearDiagnostico";
    }

    public String prepareEditCorrectivo() {
        return "/administrador/modSoporteIT/calendarioMantenimiento/correctivos/editDiagnostico";
    }

    public String prepareSolucionPreventivo() {
        diagnosticoMantenimientoActual = new DiagnosticoMantenimiento();
        return "/administrador/modSoporteIT/calendarioMantenimiento/preventivos/crearDiagnostico";
    }

    public String prepareCyP() {
        tipoMantenimientoActual = new TipoMantenimiento(3);
        diagnosticoMantenimientoActual = new DiagnosticoMantenimiento();
        return "/Admin/moduloConfigMantenimiento/adminCrearDiagnostico";
    }

    public String prepareCreateMantenimientoP() {
        cronogramaMantenimientosActual = new CronogramaMantenimientos();
        return "crearMantenimiento";
    }

    public String prepareViewCronogramaSolucionadosCoDis() {
        listaDiagnosticoMantenimiento = new ArrayList<>();
        return "/Admin/moduloConfigMantenimiento/adminViewSolucionIncidente";
    }

    public String prepareViewCronogramaSolucionados() {
        listaDiagnosticoMantenimiento = new ArrayList<>();
        return "/administrador/modSoporteIT/calendarioMantenimiento/diagnosticos/listaDiagnosticosMantenimientos";
    }

    public String prepareViewMantenimientosCorrectivosAnual() {
        return "/administrador/modSoporteIT/calendarioMantenimiento/correctivos/listaCorrectivosTotales";
    }

    public String prepareViewSolucionIncidentes() {
        return "/Admin/adminViewSolucionIncidente";
    }

    public String prepareViewMantenimientosTotales() {
        return "/Admin/moduloConfigMantenimiento/adminViewMantenimientosTotales";
    }

    public String prepareViewMantenimientosRT() {
        return "/administrador/modSoporteIT/calendarioMantenimiento/correctivos/revisados";
    }

    public String prepareUpdateMantenimientosRT() {
        return "/administrador/modSoporteIT/calendarioMantenimiento/correctivos/editDiagnostico";
    }

    public String prepareViewMantenimientosC() {
        tipoMantenimientoActual = new TipoMantenimiento(1);
        return "/administrador/modSoporteIT/calendarioMantenimiento/correctivos/listaMantenimientosCorrectivos";
    }

    public String prepareViewMantenimientosP() {
        tipoMantenimientoActual = new TipoMantenimiento(2);
        return "/administrador/modSoporteIT/calendarioMantenimiento/preventivos/listaMantenimientosPreventivos";
    }

    public String prepareViewMantenimientosPyC() {
        tipoMantenimientoActual = new TipoMantenimiento(3);
        return "/Admin/moduloConfigMantenimiento/adminViewMantenimientosCyP";
    }

    public String prepareList() {
        recargarLista();
        return "";
    }

    public String addCronogramaMantenimiento() {
        try {
            cronogramaMantenimientosActual.setFechaProgMantenimiento(new Date());
            cronogramaMantenimientosActual.setEstadoMantenimiento(new EstadoCronograma(2));
            cronogramaMantenimientosActual.setIdComputador(computadorActual);
            cronogramaMantenimientosActual.setEstadoReporte(false);
            cronogramaMantenimientosActual.setIdTipoMantenimiento(new TipoMantenimiento(2, "preventivo"));
            cronogramaMantenimientosActual.setDescripcionProblema("Ninguno reportado por el usuario");
            getCronogramaMantenimientosFacade().create(cronogramaMantenimientosActual);
            computadorActual.setEstadoProgramado(true);
            getComputadorFacade().edit(computadorActual);
            recargarLista();
            prepareViewCalendario();
            addSuccessMessage("Mantenimiento preventivo programado", "Mantenimiento preventivo programado exitosamente con numero de ticket " + cronogramaMantenimientosActual.getIdCronogramaMantenimientos() + "CFIPMP-C");
            return "listaProgramarMantenimiento";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String addFalloDocente() {
        try {

            cronogramaMantenimientosActual.setIdTipoMantenimiento(new TipoMantenimiento(1, "correctivo"));
            cronogramaMantenimientosActual.setFechaProgMantenimiento(new Date());
            cronogramaMantenimientosActual.setFechaInicioMantenimiento(new Date());
            cronogramaMantenimientosActual.setHoraMantenimiento(new Date());
            cronogramaMantenimientosActual.setEstadoMantenimiento(new EstadoCronograma(2));
            getCronogramaMantenimientosFacade().create(cronogramaMantenimientosActual);
            recargarLista();
            ticket = "CFIPMC-C";
            sendMailRegistroTec();
            sendMailRegistroUser();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String addCronogramaMantenimientoFallo() {
        try {

            cronogramaMantenimientosActual.setIdTipoMantenimiento(new TipoMantenimiento(1, "correctivo"));
            cronogramaMantenimientosActual.setFechaProgMantenimiento(new Date());
            cronogramaMantenimientosActual.setFechaInicioMantenimiento(new Date());
            cronogramaMantenimientosActual.setHoraMantenimiento(new Date());
            cronogramaMantenimientosActual.setEstadoReporte(false);
            cronogramaMantenimientosActual.setIdUsuario(usuarioActual);
            cronogramaMantenimientosActual.setEstadoMantenimiento(new EstadoCronograma(2));
            getCronogramaMantenimientosFacade().create(cronogramaMantenimientosActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Su fallo ha sido registrado", "Su fallo con numero de ticket " + cronogramaMantenimientosActual.getIdCronogramaMantenimientos() + "CFIPMC-C pronto será atendido por alguno de nuestros técnicos.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            ticket = "CFIPMC-C";
            sendMailRegistroTec();
            sendMailRegistroUser();
            return "/usuario/modSoporteIT/ticketsValoraciones/listaEstadoTicket";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String addDiagnostico() {
        if (diagnosticoMantenimientoActual.getMantenimientoCorrectivo() == true) {

            cronogramaMantenimientosActual.setIdTipoMantenimiento(new TipoMantenimiento(3, "preventivo y correctivo"));
            CronogramaMantenimientosFacade.edit(cronogramaMantenimientosActual);
            cronogramaMantenimientosActual.getIdComputador().setEstadoProgramado(false);
            getComputadorFacade().edit(cronogramaMantenimientosActual.getIdComputador());
        }

        ticket = "CFIPMP-C";
        addSuccessMessage("Diagnóstico realizado", "El Diagnostico fue guardado correctamente con el codigo " + diagnosticoMantenimientoActual.getIdMantenimiento() + " DIAG-M");
        diagnosticoMantenimientoActual.setIdCronogramaMantenimientos(cronogramaMantenimientosActual);
        diagnosticoMantenimientoActual.setTecnicoResponsable(usuarioActual);
        getDiagnosticoMantenimientoFacade().create(diagnosticoMantenimientoActual);
        cronogramaMantenimientosActual.setEstadoMantenimiento(new EstadoCronograma(3));
        cronogramaMantenimientosActual.setEstadoReporte(true);
        cronogramaMantenimientosActual.setFechaDiagnostico(new Date());
        CronogramaMantenimientosFacade.edit(cronogramaMantenimientosActual);
        cronogramaMantenimientosActual.getIdComputador().setEstadoProgramado(false);
        getComputadorFacade().edit(cronogramaMantenimientosActual.getIdComputador());
        //sendMailDiagPrevUser();
        return "listaMantenimientosPreventivos";
    }

    public String addDiagnostico1() {
        if (diagnosticoMantenimientoActual.getMantenimientoCorrectivo() == true) {
            cronogramaMantenimientosActual.setIdTipoMantenimiento(new TipoMantenimiento(3, "preventivo y correctivo"));
            CronogramaMantenimientosFacade.edit(cronogramaMantenimientosActual);
            cronogramaMantenimientosActual.getIdComputador().setEstadoProgramado(false);
            computadorFacade.edit(computadorActual);
        }
        diagnosticoMantenimientoActual.setIdCronogramaMantenimientos(cronogramaMantenimientosActual);
        diagnosticoMantenimientoActual.setTecnicoResponsable(usuarioActual);
        getDiagnosticoMantenimientoFacade().create(diagnosticoMantenimientoActual);
        cronogramaMantenimientosActual.setEstadoMantenimiento(new EstadoCronograma(3));
        cronogramaMantenimientosActual.getIdComputador().setEstadoProgramado(false);
        computadorFacade.edit(computadorActual);
        CronogramaMantenimientosFacade.edit(cronogramaMantenimientosActual);
        //sendMailDiagPrevUser();
        recargarLista();
        listaDiagnosticoMantenimiento = null;
        return "adminViewSolucionIncidenteCo";
    }

    public String addDiagnosticoFallo() {
        diagnosticoMantenimientoActual.setIdCronogramaMantenimientos(cronogramaMantenimientosActual);
        diagnosticoMantenimientoActual.setTecnicoResponsable(usuarioActual);
        getDiagnosticoMantenimientoFacade().create(diagnosticoMantenimientoActual);
        cronogramaMantenimientosActual.setFechaDiagnostico(new Date());
        cronogramaMantenimientosActual.setEstadoReporte(true);
        addSuccessMessage("Diagnóstico realizado", "El Diagnostico fue guardado correctamente con el codigo " + diagnosticoMantenimientoActual.getIdMantenimiento() + " DIAG-M");
        updateCronograma();
        recargarLista();
        //sendMailSolucionUser();
        return "listaMantenimientosCorrectivos";
    }

    public String updateDiagnostico() {
//        diagnosticoMantenimientoActual.setIdCronogramaMantenimientos(cronogramaMantenimientosActual);
//        diagnosticoMantenimientoActual.setTecnicoResponsable(usuarioActual);
        getDiagnosticoMantenimientoFacade().edit(diagnosticoMantenimientoActual);
        addSuccessMessage("Diagnóstico realizado", "El Diagnostico fue guardado correctamente con el codigo " + diagnosticoMantenimientoActual.getIdMantenimiento() + " DIAG-M");


        //updateCronograma();
        //updateFallo();
        recargarLista();
        sendMailSolucionUser();
        return "actualizacionEstados";

    }
    
       public String updateFallo() {
//        diagnosticoMantenimientoActual.setIdCronogramaMantenimientos(cronogramaMantenimientosActual);
//        diagnosticoMantenimientoActual.setTecnicoResponsable(usuarioActual);
        getCronogramaMantenimientosFacade().edit(cronogramaMantenimientosActual);
        addSuccessMessage("Diagnóstico realizado", "El Mantenimiento fue editado correctamente con el codigo " );


        //updateCronograma();
        recargarLista();
        //sendMailSolucionUser();
        return "revisados";

    }
    

    public String crearValoracionTicket() {
        try {
            cronogramaMantenimientosActual.setFechaValoracion(new Date());
            cronogramaMantenimientosActual.setEstadoMantenimiento(new EstadoCronograma(1));
            getCronogramaMantenimientosFacade().edit(cronogramaMantenimientosActual);
            recargarLista();
            addSuccessMessage("Valoración guardada", "El ticket ha sido cerrado correctamente");
            return "/usuario/modSoporteIT/ticketsValoraciones/verValoracionTicket";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "/usuario/modSoporteIT/ticketsValoraciones/listaEstadoTicket";
        }
    }

    public void updateCronograma() {
        try {
            getCronogramaMantenimientosFacade().edit(cronogramaMantenimientosActual);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public String deleteCronograma() {
        try {
            getCronogramaMantenimientosFacade().remove(cronogramaMantenimientosActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mantenimiento eliminado", "El mantenimiento fue eliminado");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
        return "listaProgramarMantenimiento";
    }

    public String prepareViewIncidentePendiente() {
        return "adminDialogDatosIncidente";
    }

    private void addErrorMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    private void addSuccessMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    private void sendMail(String to, String subject, String body) {
        try {
            emailBean.sendMail(to, subject, body);
            JsfUtil.addSuccessMessage("Mensaje Enviado Exitosamente");
        } catch (NamingException | MessagingException ex) {
            JsfUtil.addErrorMessage("Error sending message " + ex.getClass().getName());
        }
    }

    private void sendMailRegistroTec() {

        String seccion = "";
        String responsable = "";
        String bloque = "";
        String idLan = "";
        String captura = "";
        String nombreUsuario = "";
        String jornada = "";
        String correoJornada = "";
        String telefono = "";
        if (cronogramaMantenimientosActual.getTelefono() == null) {
            telefono = "";
        } else {
            telefono = cronogramaMantenimientosActual.getTelefono();
        }
        if (cronogramaMantenimientosActual.getIdComputador().getIdSeccion() == null) {
            seccion = "N/A";
            bloque = "N/A";
        } else {
            seccion = cronogramaMantenimientosActual.getIdComputador().getIdSeccion().toString();
            bloque = cronogramaMantenimientosActual.getIdComputador().getIdSeccion().getIdBloque().getNombreBloque();
        }
        if (cronogramaMantenimientosActual.getIdComputador().getIdUsuario() == null) {
            responsable = "N/A";
        } else {
            responsable = cronogramaMantenimientosActual.getIdComputador().getIdUsuario().toString();
        }
        if (cronogramaMantenimientosActual.getIdComputador().getIdLam().equals("")) {
            idLan = "N/A";
        } else {
            idLan = cronogramaMantenimientosActual.getIdComputador().getIdLam();
        }
        if (cronogramaMantenimientosActual.getNombreUsuarioReporte() == null) {
            nombreUsuario = " ";
        } else {
            nombreUsuario = cronogramaMantenimientosActual.getNombreUsuarioReporte().toUpperCase();
        }
        if (cronogramaMantenimientosActual.getImagenMantenimiento() == null) {
            captura = "NO";
        } else {
            captura = "SI";
        }
        if (cronogramaMantenimientosActual.getIdTipoJornada() == null) {
            jornada = "N/A";
        } else {
            jornada = cronogramaMantenimientosActual.getIdTipoJornada().getNombre();
        }
        if (cronogramaMantenimientosActual.getIdTipoJornada() == null) {
            correoJornada = "";
        } else {
            correoJornada = cronogramaMantenimientosActual.getIdTipoJornada().getCorreo();
        }
        String subject = seccion.toUpperCase() + ", FALLO REPORTADO TICKET #" + cronogramaMantenimientosActual.getIdCronogramaMantenimientos() + ticket;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(cronogramaMantenimientosActual.getIdCronogramaMantenimientos());
        mensaje.append(ticket);
        mensaje.append("\nRESPONSABLE DEL EQUIPO: ");
        mensaje.append(responsable.toUpperCase());
        mensaje.append("\nUSUARIO: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nNOMBRE DEL USUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(nombreUsuario);
        mensaje.append("\nTELEFONO: ");
        mensaje.append(telefono);
        mensaje.append("\nAREA DONDE SE REPORTO EL FALLO: ");
        mensaje.append(cronogramaMantenimientosActual.getIdComputador().getIdSeccion().getIdArea().getNombreArea().toUpperCase());
        mensaje.append("\nJORNADA DONDE SE REPORTO EL FALLO: ");
        mensaje.append(jornada.toUpperCase());
        mensaje.append("\nDESCRIPCIÓN DEL FALLO: ");
        mensaje.append(cronogramaMantenimientosActual.getDescripcionProblema().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(cronogramaMantenimientosActual.getFechaProgMantenimiento().toLocaleString());
        mensaje.append("\n-----------------------------------------------------");
        mensaje.append("\nDATOS DEL EQUIPO");
        mensaje.append("\n-----------------------------------------------------");
        mensaje.append("\nSERVICE TAG O (S/N): ");
        mensaje.append(cronogramaMantenimientosActual.getIdComputador().getCodigoComputador().toUpperCase());
        mensaje.append("\nTIPO: ");
        mensaje.append(cronogramaMantenimientosActual.getIdComputador().getIdModelo().getIdTipo().getNombreTipo().toUpperCase());
        mensaje.append("\nMODELO: ");
        mensaje.append(cronogramaMantenimientosActual.getIdComputador().getIdModelo().getNombreModelo().toUpperCase());
        mensaje.append("\nMARCA: ");
        mensaje.append(cronogramaMantenimientosActual.getIdComputador().getIdModelo().getIdMarca().getNombreMarca().toUpperCase());

        sendMail("luis.cabal@cfiprovidencia.com " + " luis.palacios@cfiprovidencia.com " + " victor.caicedo@cfiprovidencia.com " + " " + " jorgeh.sanchez@cfiprovidencia.com " + " " + correoJornada, subject, mensaje.toString());
    }

    private void sendMailRegistroUser() {
        String seccion = "";
        String responsable = "";
        String bloque = "";
        String idLan = "";
        String captura = "";
        String nombreUsuario = "";
        String correo = "";
        if (cronogramaMantenimientosActual.getIdComputador().getIdSeccion() == null) {
            seccion = "N/A";
            bloque = "N/A";
        } else {
            seccion = cronogramaMantenimientosActual.getIdComputador().getIdSeccion().toString();
            bloque = cronogramaMantenimientosActual.getIdComputador().getIdSeccion().getIdBloque().getNombreBloque();
        }
        if (cronogramaMantenimientosActual.getIdComputador().getIdUsuario() == null) {
            responsable = "N/A";
        } else {
            responsable = cronogramaMantenimientosActual.getIdComputador().getIdUsuario().toString();
        }
        if (cronogramaMantenimientosActual.getIdComputador().getIdLam().equals("")) {
            idLan = "N/A";
        } else {
            idLan = cronogramaMantenimientosActual.getIdComputador().getIdLam();
        }
        if (cronogramaMantenimientosActual.getImagenMantenimiento() == null) {
            captura = "No";
        } else {
            captura = "Si";
        }
        if (cronogramaMantenimientosActual.getNombreUsuarioReporte() == null) {
            nombreUsuario = " ";
        } else {
            nombreUsuario = cronogramaMantenimientosActual.getNombreUsuarioReporte().toUpperCase();
        }
        if (cronogramaMantenimientosActual.getCorreoUsuarioReporte() == null) {
            correo = " ";
        } else {
            correo = cronogramaMantenimientosActual.getCorreoUsuarioReporte();
        }
        String subject = "SU FALLO CON NUMERO DE TICKET #" + cronogramaMantenimientosActual.getIdCronogramaMantenimientos() + ticket;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(cronogramaMantenimientosActual.getIdCronogramaMantenimientos());
        mensaje.append(ticket);
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL FALLO REPORTADO");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDESCRIPCIÓN DEL FALLO: ");
        mensaje.append(cronogramaMantenimientosActual.getDescripcionProblema().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(cronogramaMantenimientosActual.getFechaProgMantenimiento().toLocaleString());
        mensaje.append("\nRESPONSABLE DEL EQUIPO: ");
        mensaje.append(responsable.toUpperCase());
        mensaje.append("\nUSUARIO: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nNOMBRE DEL USUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(nombreUsuario);
        mensaje.append("\nSECCIÓN: ");
        mensaje.append(seccion.toUpperCase());
        mensaje.append("\nAREA: ");
        mensaje.append(cronogramaMantenimientosActual.getIdComputador().getIdSeccion().getIdArea().getNombreArea().toUpperCase());
        mensaje.append("\nBLOQUE: ");
        mensaje.append(bloque.toUpperCase());
        mensaje.append("\nCAPTURA DE PANTALLA: ");
        mensaje.append(captura.toUpperCase());

        mensaje.append("\n\nPRONTO UNO DE NUESTROS TECNICOS ESTARA ATENDIENDO SU SOLICITUD.");
        mensaje.append("\n\nTu opinión cuenta, no te olvides de calificar nuestros servicios, esto nos ayudara a mejorar. ");
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail(cronogramaMantenimientosActual.getIdComputador().getIdUsuario().getCorreoUsuario() + " " + correo, subject, mensaje.toString());
    }

    public void sendMailMantenimientoPreventivo() {
       
            String seccion = "";
            String responsable = "";
            String bloque = "";
            String idLan = "";
            SimpleDateFormat fecha = new SimpleDateFormat("MMM/dd/yyyy");
            SimpleDateFormat hora = new SimpleDateFormat("hh:mm:ss");
            if (cronogramaMantenimientosActual.getIdComputador().getIdSeccion() == null) {
                seccion = "N/A";
                bloque = "N/A";
            } else {
                seccion = cronogramaMantenimientosActual.getIdComputador().getIdSeccion().toString();
                bloque = cronogramaMantenimientosActual.getIdComputador().getIdSeccion().getIdBloque().getNombreBloque();
            }
            if (cronogramaMantenimientosActual.getIdComputador().getIdUsuario() == null) {
                responsable = "N/A";
            } else {
                responsable = cronogramaMantenimientosActual.getIdComputador().getIdUsuario().toString();
            }
            if (cronogramaMantenimientosActual.getIdComputador().getIdLam().equals("")) {
                idLan = "N/A";
            } else {
                idLan = cronogramaMantenimientosActual.getIdComputador().getIdLam();
            }
            String subject = "ATENCIÓN!! MANTENIMIENTO PREVENTIVO DE SU COMPUTADOR PROGRAMADO CON TICKET #" + cronogramaMantenimientosActual.getIdCronogramaMantenimientos() + "CFIPMP-C";
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("TICKET # ");
            mensaje.append(cronogramaMantenimientosActual.getIdCronogramaMantenimientos());
            mensaje.append("CFIPMP-C");
            mensaje.append("\nEL MANTENIMIENTO PREVENTIVO DESTINADO PARA SU COMPUTADOR ESTÁ A PUNTO "
                    + "\nDE SER EJECUTADO, ESPERAMOS SU TOTAL COLABORACIÓN, ESTE MANTENIMIENTO"
                    + "\nTIENE UNA DURACIÓN DE ENTRE 1 O 2 HORAS DEPENDIENDO DEL ESTADO DEL COMPUTADOR. ");
            mensaje.append("\n---------------------------------------------------------------------");
            mensaje.append("\nDATOS DEL MANTENIMIENTO PREVENTIVO");
            mensaje.append("\n---------------------------------------------------------------------");
            mensaje.append("\nFECHA DEL MANTENIMIENTO: ");
            mensaje.append(fecha.format(cronogramaMantenimientosActual.getFechaInicioMantenimiento()));
            mensaje.append("\nHORA DEL MANTENIMIENTO: ");
            mensaje.append(hora.format(cronogramaMantenimientosActual.getHoraMantenimiento()));
            mensaje.append("\nRESPONSABLE DEL COMPUTADOR: ");
            mensaje.append(responsable.toUpperCase());
            mensaje.append("\nSECCIÓN: ");
            mensaje.append(seccion.toUpperCase());
            mensaje.append("\nBLOQUE: ");
            mensaje.append(bloque.toUpperCase());

            mensaje.append("\n---------------------------------------------------------------------");
            mensaje.append("\nDATOS DEL COMPUTADOR");
            mensaje.append("\n---------------------------------------------------------------------");

            mensaje.append("\nSERVICE TAG O (S/N): ");
            mensaje.append(cronogramaMantenimientosActual.getIdComputador().getCodigoComputador().toUpperCase());
            mensaje.append("\nID LAN: ");
            mensaje.append(idLan.toUpperCase());
            mensaje.append("\nTIPO: ");
            mensaje.append(cronogramaMantenimientosActual.getIdComputador().getIdModelo().getIdTipo().getNombreTipo().toUpperCase());
            mensaje.append("\nMODELO: ");
            mensaje.append(cronogramaMantenimientosActual.getIdComputador().getIdModelo().getNombreModelo().toUpperCase());
            mensaje.append("\nMARCA: ");
            mensaje.append(cronogramaMantenimientosActual.getIdComputador().getIdModelo().getIdMarca().getNombreMarca().toUpperCase());

            sendMail(cronogramaMantenimientosActual.getIdComputador().getIdUsuario().getCorreoUsuario() + " " + "juan.cordoba@cfiprovidencia.com" + " " + "jorgeh.sanchez@cfiprovidencia.com" + " " + "victor.caicedo@cfiprovidencia.com", subject, mensaje.toString());

    }

    private void sendMailDiagPrevUser() {
        String seccion = "";
        String responsable = "";
        String bloque = "";
        String idLan = "";
        String nombreUsuario = "";
        String correo = "";
        SimpleDateFormat fecha = new SimpleDateFormat("MMM/dd/yyyy");
        if (diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdSeccion() == null) {
            seccion = "N/A";
            bloque = "N/A";
        } else {
            seccion = diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdSeccion().toString();
            bloque = diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdSeccion().getIdBloque().getNombreBloque();
        }
        if (diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdUsuario() == null) {
            responsable = "N/A";
        } else {
            responsable = diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdUsuario().toString();
        }
        if (diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdLam().equals("")) {
            idLan = "N/A";
        } else {
            idLan = diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdLam();
        }
        if (diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getNombreUsuarioReporte() == null) {
            nombreUsuario = " ";
        } else {
            nombreUsuario = diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getNombreUsuarioReporte().toUpperCase();
        }
        if (diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getCorreoUsuarioReporte() == null) {
            correo = " ";
        } else {
            correo = diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getCorreoUsuarioReporte().toUpperCase();
        }
        String subject = "DIAGNOSTICO DEL TICKET #" + diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdCronogramaMantenimientos() + ticket;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CODIGO DEL DIAGNOSTICO ");
        mensaje.append(diagnosticoMantenimientoActual.getIdMantenimiento());
        mensaje.append("CFIPDIAG-C");

        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL MANTENIMIENTO ");
        mensaje.append(diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdTipoMantenimiento().getNombreTipoMantenimiento().toUpperCase());
        mensaje.append("\n---------------------------------------------------------------------");

        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getFechaProgMantenimiento().toLocaleString());
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(responsable.toUpperCase());
        mensaje.append("\nSECCIÓN: ");
        mensaje.append(seccion.toUpperCase());
        mensaje.append("\nUSUARIO: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nNOMBRE DEL USUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(nombreUsuario);
        mensaje.append("\nBLOQUE: ");
        mensaje.append(bloque.toUpperCase());

        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL DIAGNOSTICO:");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nREVISION: ");
        mensaje.append(diagnosticoMantenimientoActual.getRevision().toUpperCase());
        mensaje.append("\nFECHA DE LA REVISION: ");
        mensaje.append(fecha.format(diagnosticoMantenimientoActual.getFechaRevision()));
        mensaje.append("\nDIAGNOSTICO: ");
        mensaje.append(diagnosticoMantenimientoActual.getDiagnostico().toUpperCase());
        mensaje.append("\nFECHA ENTREGA DEL EQUIPO: ");
        mensaje.append(fecha.format(diagnosticoMantenimientoActual.getFechaEntrega()));
        mensaje.append("\nTECNICO QUE ATENDIO EL CASO: ");
        mensaje.append(diagnosticoMantenimientoActual.getTecnicoResponsable().toString());

        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL COMPUTADOR:");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nSERVICE TAG O (S/N): ");
        mensaje.append(diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getCodigoComputador().toUpperCase());
        mensaje.append("\nID LAN: ");
        mensaje.append(idLan.toUpperCase());
        mensaje.append("\nMODELO: ");
        mensaje.append(diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdModelo().getNombreModelo().toUpperCase());
        mensaje.append("\nMARCA: ");
        mensaje.append(diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdModelo().getIdMarca().getNombreMarca().toUpperCase());

        sendMail(diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdUsuario().getCorreoUsuario() + correo + " " + "luis.cabal@cfiprovidenciacom" + " " + "jorgeh.sanchez@cfiprovidencia.com" + " " + "victor.caicedo@cfiprovidencia.com", subject, mensaje.toString());
    }

    private void sendMailSolucionUser() {
        String seccion = "";
        String responsable = "";
        String bloque = "";
        String idLan = "";
        String correo = "";
        String jornada = "";
        String correoJornada = "";
        if (diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdSeccion() == null) {
            seccion = "N/A";
            bloque = "N/A";
        } else {
            seccion = diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdSeccion().toString();
            bloque = diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdSeccion().getIdBloque().getNombreBloque();
        }
        if (diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdUsuario() == null) {
            responsable = "N/A";
        } else {
            responsable = diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdUsuario().toString();
        }
        if (diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdLam().equals("")) {
            idLan = "N/A";
        } else {
            idLan = diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdLam();
        }
        if (diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getCorreoUsuarioReporte() == null) {
            correo = "";
        } else {
            correo = diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getCorreoUsuarioReporte();
        }
        if (diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdTipoJornada() == null) {
            jornada = "N/A";
        } else {
            jornada = diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdTipoJornada().getNombre();
        }
        if (diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdTipoJornada() == null) {
            correoJornada = "";
        } else {
            correoJornada = diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdTipoJornada().getCorreo();
        }
        String subject = "DIAGNOSTICO DEL TICKET #" + diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdCronogramaMantenimientos() + "CFIPMC-C";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CODIGO DEL DIAGNOSTICO ");
        mensaje.append(diagnosticoMantenimientoActual.getIdMantenimiento());
        mensaje.append("CFIPDIAG-C");

        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL MANTENIMIENTO CORRECTIVO");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDESCRIPCION DEL FALLO: ");
        mensaje.append(diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getDescripcionProblema().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getFechaProgMantenimiento().toLocaleString());
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(responsable.toUpperCase());
        mensaje.append("\nSECCION: ");
        mensaje.append(seccion.toUpperCase());
        mensaje.append("\nBLOQUE: ");
        mensaje.append(bloque.toUpperCase());
        mensaje.append("\nJORNADA DONDE SE REPORTO EL FALLO: ");
        mensaje.append(jornada.toUpperCase());

        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL DIAGNOSTICO:");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nREVISION: ");
        mensaje.append(diagnosticoMantenimientoActual.getRevision().toUpperCase());
        mensaje.append("\nFECHA DE LA REVISION: ");
        mensaje.append(diagnosticoMantenimientoActual.getFechaRevision().toLocaleString());
        mensaje.append("\nDIAGNOISTICO: ");
        mensaje.append(diagnosticoMantenimientoActual.getDiagnostico().toUpperCase());
        mensaje.append("\nENTREGA DEL EQUIPO: ");
        mensaje.append(diagnosticoMantenimientoActual.getFechaEntrega().toLocaleString());
        mensaje.append("\nTECNICO QUE ATENDIO EL CASO: ");
        mensaje.append(diagnosticoMantenimientoActual.getTecnicoResponsable().toString());

        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL COMPUTADOR:");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nSERVICE TAG O (S/N): ");
        mensaje.append(diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getCodigoComputador().toUpperCase());
        mensaje.append("\nID LAN: ");
        mensaje.append(idLan.toUpperCase());
        mensaje.append("\nMODELO: ");
        mensaje.append(diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdModelo().getNombreModelo().toUpperCase());
        mensaje.append("\nMARCA: ");
        mensaje.append(diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdModelo().getIdMarca().getNombreMarca().toUpperCase());

        mensaje.append("\n\nSU INCIDENTE FUE CORREGIDO SATISFACTORIAMENTE");
        mensaje.append("\n\nTu opinión cuenta, no te olvides de calificar nuestros servicios, esto nos ayudara a mejorar. ");
        mensaje.append("\n\nTodos los Derechos Reservados www.cfiprovidencia.com © 2017.");
        sendMail(diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdUsuario().getCorreoUsuario() + " " + "auxsistemas2@cfiprovidencia.com" + " " + "sistemas@cfiprovidencia.com" + " " + "auxsistemas@cfiprovidencia.com" + " " + correo + " " + correoJornada, subject, mensaje.toString());
        //sendMail(diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdUsuario().getCorreoUsuario()+" "+"auxsistemas2@cfiprovidencia.com"+" "+correoJornada, subject, mensaje.toString());

    }

    public void enviandoCorreo() {
        addMessage("Correo enviado", "correo enviado");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Cronograma Calendario
    public void setCronogramaMantenimientosFacade(CronogramaMantenimientosFacade CronogramaMantenimientosFacade) {
        this.CronogramaMantenimientosFacade = CronogramaMantenimientosFacade;
    }

    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    DefaultScheduleEvent man = new DefaultScheduleEvent();

    public void init() {
        eventModel = new DefaultScheduleModel();
        //Mantenimientos Preventivos y Correctivos 
        for (Iterator<CronogramaMantenimientos> it = recargarListaMantenimiento().iterator(); it.hasNext();) {
            CronogramaMantenimientos i = it.next();
            eventModel.addEvent(new DefaultScheduleEvent("- " + i.getIdComputador().getIdSeccion().toString() + "\n- " + i.getIdComputador().getIdUsuario().toString() + "\n- TICKET " + i.getIdCronogramaMantenimientos() + "CFIP-MCP", i.getFechaInicioMantenimiento(), i.getHoraMantenimiento(), "MANTENIMIENTO_CORRECTIVO_Y_PREVENTIVO"));

        }
        //Mantenimientos Siesa Ticket abierto
        for (Iterator<ReporteSiesa> it = recargarListaReporteSiesaAbierto().iterator(); it.hasNext();) {
            ReporteSiesa i = it.next();
            eventModel.addEvent(new DefaultScheduleEvent("- " + i.getIdUsuario().toString() + "\n- TICKET " + i.getIdReporteSiesa() + "CFIPERP", i.getFechaReporte(), i.getFechaReporte(), "FALLO_DE_ERP_ABIERTO"));
        }
        //Mantenimientos Siesa Ticket Siesa it
        for (Iterator<ReporteSiesa> it = recargarListaReporteSiesaSiesaIT().iterator(); it.hasNext();) {
            ReporteSiesa i = it.next();
            eventModel.addEvent(new DefaultScheduleEvent("- " + i.getIdUsuario().toString() + "\n- TICKET " + i.getIdReporteSiesa() + "CFIPERP", i.getFechaReporte(), i.getFechaReporte(), "FALLO_DE_ERP_SIESA_IT"));
        }
        // Uso equipos
        for (Iterator<SistemasEquiposUsuarios> it = recargarListaPrestamoEquipos().iterator(); it.hasNext();) {
            SistemasEquiposUsuarios i = it.next();
            eventModel.addEvent(new DefaultScheduleEvent("- " + i.getIdsistemasequiposUsuarios() + " D-US" + "\n- " + i.toString(), i.getFechaVencimiento(), i.getFechaVencimiento(), "USO_EQUIPOS"));
        }
        // Programacion de equipos
        for (Iterator<CronogramaComponente> it = recargarListaCronogramaComponente().iterator(); it.hasNext();) {
            CronogramaComponente i = it.next();
            eventModel.addEvent(new DefaultScheduleEvent("- CAMBIO DE COMPONENTE \n" + "- " + i.getIdComputador().getIdUsuario().toString() + "\n" + "- " + i.getIdItemComponente().toString(), i.getFechaCambio(), i.getFechaCambio(), "CAMBIO_COMPONENTE"));
        }
        //Mantenimientos Correctivos 
        for (Iterator<CronogramaMantenimientos> it = recargarListaMantenimientoC().iterator(); it.hasNext();) {
            CronogramaMantenimientos i = it.next();
            String seccion = "";
            String responsable = "";
            String horaReporte;
            DateFormat fecha1 = new SimpleDateFormat("hh:mma");
            horaReporte = fecha1.format(i.getFechaProgMantenimiento());
            if (i.getIdComputador().getIdSeccion() == null) {
                seccion = "N/A";
            } else {
                seccion = i.getIdComputador().getIdSeccion().toString();
            }
            if (i.getIdComputador().getIdUsuario() == null) {
                responsable = "N/A";
            } else {
                responsable = i.getIdComputador().getIdUsuario().toString();
            }
            eventModel.addEvent(new DefaultScheduleEvent("- #TICKET " + i.getIdCronogramaMantenimientos()
                    + "CFIPMC-C\n - " + seccion.toUpperCase()
                    + "\n - " + responsable.toUpperCase()
                    + "\n - HORA: " + horaReporte, i.getFechaInicioMantenimiento(), i.getHoraMantenimiento(), "SERVICE TAG O (S/N): "
                    + i.getIdComputador().getCodigoComputador().toUpperCase()
                    + " // " + "MANTENIMIENTO_CORRECTIVO"));
        }

        //Mantenimientos Preventivos 
        for (Iterator<CronogramaMantenimientos> it = recargarListaMantenimientoP().iterator(); it.hasNext();) {
            CronogramaMantenimientos i = it.next();
            String seccion = "";
            String responsable = "";
            String bloque = "";
            String horaReporte;
            DateFormat fecha1 = new SimpleDateFormat("hh:mma");
            horaReporte = fecha1.format(i.getHoraMantenimiento());
            if (i.getIdComputador().getIdSeccion() == null) {
                seccion = "N/A";
                bloque = "N/A";
            } else {
                seccion = i.getIdComputador().getIdSeccion().toString();
                bloque = i.getIdComputador().getIdSeccion().getIdBloque().getNombreBloque();
            }
            if (i.getIdComputador().getIdUsuario() == null) {
                responsable = "N/A";
            } else {
                responsable = i.getIdComputador().getIdUsuario().toString();
            }

            eventModel.addEvent(new DefaultScheduleEvent("- #TICKET " + i.getIdCronogramaMantenimientos()
                    + "CFIPMP-C\n - " + seccion.toUpperCase()
                    + "\n - " + responsable.toUpperCase()
                    + "\n - HORA: "
                    + horaReporte, i.getFechaInicioMantenimiento(), i.getHoraMantenimiento(), "SERVICE TAG O (S/N): "
                    + i.getIdComputador().getCodigoComputador().toUpperCase() + " // " + "MANTENIMIENTO_PREVENTIVO"));

        }
        //Mantenimientos Revisados 
        for (Iterator<CronogramaMantenimientos> it = recargarListaMantenimientoRe().iterator(); it.hasNext();) {
            CronogramaMantenimientos i = it.next();
            String seccion = "";
            String responsable = "";
            String horaReporte;
            DateFormat fecha1 = new SimpleDateFormat("hh:mma");
            horaReporte = fecha1.format(i.getFechaProgMantenimiento());
            if (i.getIdComputador().getIdSeccion() == null) {
                seccion = "N/A";
            } else {
                seccion = i.getIdComputador().getIdSeccion().toString();
            }
            if (i.getIdComputador().getIdUsuario() == null) {
                responsable = "N/A";
            } else {
                responsable = i.getIdComputador().getIdUsuario().toString();
            }
            eventModel.addEvent(new DefaultScheduleEvent("- #TICKET " + i.getIdCronogramaMantenimientos()
                    + "CFIPMR-R\n - " + seccion.toUpperCase()
                    + "\n - " + responsable.toUpperCase()
                    + "\n - HORA: "
                    + horaReporte, i.getFechaInicioMantenimiento(), i.getHoraMantenimiento(), "SERVICE TAG O (S/N): "
                    + i.getIdComputador().getCodigoComputador().toUpperCase()
                    + " // " + "MANTENIMIENTO_REVISADO"));

        }

    }

    ;
     public List<CronogramaMantenimientos> recargarListaMantenimientoP() {
        recargarLista();
        if (listaCronogramaMantenimientosP == null) {
            try {
                listaCronogramaMantenimientosP = getCronogramaMantenimientosFacade().consultaCronogramaTipoPendiente(new EstadoCronograma(2), new TipoMantenimiento(2));
            } catch (Exception e) {
                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            }
        }
        return listaCronogramaMantenimientosP;
    }

    public List<CronogramaMantenimientos> recargarListaMantenimientoC() {
        listaCronogramaMantenimientosC = new ArrayList<>();
        listaCronogramaMantenimientosC = getCronogramaMantenimientosFacade().consultaCronogramaTipoPendiente(new EstadoCronograma(2), new TipoMantenimiento(1));
        return listaCronogramaMantenimientosC;
    }

    public List<CronogramaMantenimientos> recargarListaMantenimiento() {
        listaCronogramaMantenimientos = new ArrayList<>();
        listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaCronogramaTipoPendiente(new EstadoCronograma(2), new TipoMantenimiento(3));
        return listaCronogramaMantenimientos;
    }

    public List<CronogramaMantenimientos> recargarListaMantenimientoRe() {
        listaCronogramaMantenimientos = new ArrayList<>();
        listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaCronogramaTipoPendienteCuatro(new EstadoCronograma(4));
        return listaCronogramaMantenimientos;
    }

    public List<ReporteSiesa> recargarListaReporteSiesaAbierto() {
        reporteSiesaList = new ArrayList<>();
        reporteSiesaList = getReporteSiesaFacade().consultaReporteEstadoTicket(new EstadoTicket(1, "Abierto"));
        return reporteSiesaList;
    }

    public List<ReporteSiesa> recargarListaReporteSiesaSiesaIT() {
        reporteSiesaList = new ArrayList<>();
        reporteSiesaList = getReporteSiesaFacade().consultaReporteEstadoTicket(new EstadoTicket(2, "Siesa IT"));
        return reporteSiesaList;
    }

    public List<SistemasEquiposUsuarios> recargarListaPrestamoEquipos() {
        listaSistemasEquiposUsuarios = new ArrayList<>();
        listaSistemasEquiposUsuarios = getSistemasEquiposUsuariosFacade().findAll();
        return listaSistemasEquiposUsuarios;
    }

    public List<CronogramaComponente> recargarListaCronogramaComponente() {
        listaCronogramaComponente = new ArrayList<>();
        listaCronogramaComponente = getCronogramaComponenteActualFacade().consultaFecha();
        return listaCronogramaComponente;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public String prepareViewMantenimientos() {
        return "/administrador/modSoporteIT/calendarioMantenimiento/preventivos/listaPreventivosTotales";
    }

    public String prepareViewCalendario() {
        fechaParametro1 = new Date();
        fechaParametro2 = new Date();
        recargarListaMantenimientoP();
        init();
        int contTicket = 0;
        for (CronogramaMantenimientos items : getListaCronogramaMantenimientoEstado()) {
            int cont = 0;
            int diffDays = 0;
            Calendar fecha1 = new GregorianCalendar();
            fecha1.setLenient(false);
            Calendar fecha2 = new GregorianCalendar();
            fecha2.setLenient(false);
            if (items.getFechaDiagnostico() == null) {
                fecha1.setTime(new Date());
            } else {
                fecha2.setTime(new Date());
                fecha1.setTime(items.getFechaDiagnostico());
            }
            if (fecha2.before(fecha1) || fecha2.equals(fecha1)) {
                diffDays = 0;
            } else {
                while (fecha1.before(fecha2)) {
                    diffDays++;
                    fecha1.add(Calendar.DATE, 1);
                }
                if (diffDays > 7) {
                    items.setFechaValoracion(new Date());
                    items.setEstadoMantenimiento(new EstadoCronograma(1));
                    items.setDescripcionValoracion("Tiquete cerrado automaticamente por el sistema");
                    getCronogramaMantenimientosFacade().edit(items);
                    contTicket++;
                }
            }
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Cierre de tiquetes Automaticos", "fueron cerrados " + contTicket + " casos sin valorar");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        return "/administrador/modSoporteIT/calendarioMantenimiento/viewCalendarioMantenimiento";
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
    //LISTA ESTADOS TICKETS
    @EJB
    private CronogramaManteDispositivoFacade cronogramaManteDispositivoFacade;
    @EJB
    private CronogramaManteDisConFacade cronogramaManteDisConFacade;
    private List<CronogramaManteDispositivo> listaCronogramaManteDispositivo;
    private List<CronogramaManteDisCon> listaCronogramaManteDisCon;

    public CronogramaManteDispositivoFacade getCronogramaManteDispositivoFacade() {
        return cronogramaManteDispositivoFacade;
    }

    public void setCronogramaManteDispositivoFacade(CronogramaManteDispositivoFacade cronogramaManteDispositivoFacade) {
        this.cronogramaManteDispositivoFacade = cronogramaManteDispositivoFacade;
    }

    public CronogramaManteDisConFacade getCronogramaManteDisConFacade() {
        return cronogramaManteDisConFacade;
    }

    public void setCronogramaManteDisConFacade(CronogramaManteDisConFacade cronogramaManteDisConFacade) {
        this.cronogramaManteDisConFacade = cronogramaManteDisConFacade;
    }

    public List<CronogramaMantenimientos> getListaMantenimientosEstadoUserDocente() {

        if (usuarioActual.getIdUsuario() == 304) {
            return getCronogramaMantenimientosFacade().consultaCronogramaEstadoUserDocente();
        } else {
            return getCronogramaMantenimientosFacade().consultaCronogramaEstadoUser(usuarioActual);
        }
    }

    public List<CronogramaMantenimientos> getListaMantenimientosEstadoUser() {
        listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaCronogramaEstadoUser(usuarioActual);
        return listaCronogramaMantenimientos;
    }

    public List<CronogramaManteDispositivo> getListaMantenimientosDisEstadoUserDocente() {
        if (usuarioActual.getIdUsuario() == 304) {
            return getCronogramaManteDispositivoFacade().consultaCronogramaEstadoUserDocente();
        } else {
            return getCronogramaManteDispositivoFacade().consultaCronogramaEstadoUser(usuarioActual);
        }
    }

    public List<CronogramaManteDispositivo> getListaMantenimientosDisEstadoUser() {
        listaCronogramaManteDispositivo = getCronogramaManteDispositivoFacade().consultaCronogramaEstadoUser(usuarioActual);
        return listaCronogramaManteDispositivo;
    }

    public List<CronogramaManteDisCon> getListaMantenimientosDisConEstadoUser() {
        listaCronogramaManteDisCon = getCronogramaManteDisConFacade().consultaCronogramaEstadoUser(usuarioActual);
        return listaCronogramaManteDisCon;
    }

    public int sortByModel(CronogramaMantenimientos obj1, CronogramaMantenimientos obj2) {
        return 1;
    }

    public MethodExpression getOrdina() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getExpressionFactory().createMethodExpression(context.getELContext(), "#{mantenimiento.fechaMesString}", Integer.class, new Class[]{Object.class, Object.class});
    }

    public List<DiagnosticoMantenimiento> getListaDiagnostico() {
        listaDiagnosticoMantenimiento = null;
        if (listaDiagnosticoMantenimiento == null) {
            try {
                listaDiagnosticoMantenimiento = getDiagnosticoMantenimientoFacade().consultaTicket(cronogramaMantenimientosActual);
            } catch (Exception e) {
                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            }
        }
        return listaDiagnosticoMantenimiento;
    }

    public void prepareViewDiagnostico(ActionEvent event) {
        cronogramaMantenimientosActual = new CronogramaMantenimientos();
        cronogramaMantenimientosActual = (CronogramaMantenimientos) event.getComponent().getAttributes().get("mantenimiento");
        listaDiagnosticoMantenimiento = new ArrayList<>();
        //listaActividadAprendizaje=actividadActual.getActividadAprendizajeList(); 
    }

    public String viewDiagnosticoPrev() {
        ticket = "CFIPMP";
        return "/administrador/modSoporteIT/calendarioMantenimiento/diagnosticos/verDiagnosticosMantenimientos";
    }

    public String viewValoracionCorre() {
        ticket = "CFIPMC-C";
        if (cronogramaMantenimientosActual.getEstadoMantenimiento().equals(new EstadoCronograma(3))) {
            cronogramaMantenimientosActual.setValoracion(1);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Valora el servicio prestado", "Por favor valora el servicio prestado para cerrar correctamente el ticket.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "crearValoracionTicket";
        } else if (cronogramaMantenimientosActual.getEstadoMantenimiento().equals(new EstadoCronograma(1))) {
            return "verValoracionTicket";
        } else {
            addErrorMessage("Ticket no diagnosticado", "Para valorar el servicio debes esperar a que nuestros técnicos diagnostiquen el ticket");
            return null;
        }

    }

    public String viewDiagnosticoCorre() {
        ticket = "CFIPMC";
        return "/administrador/modSoporteIT/calendarioMantenimiento/diagnosticos/verDiagnosticosMantenimientos";
    }

    private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);

        return String.valueOf(i);
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void guardarImagen(FileUploadEvent event) throws IOException {
        nombreImagen = getRandomImageName();
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        cronogramaMantenimientosActual.setImagenMantenimiento(data);
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo"
                + File.separator + "images" + File.separator + "photocam" + File.separator + nombreImagen + ".jpg";

        FileImageOutputStream imageOutput;
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proceso terminado", "La imagen ha sido cargada correctamente.");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();

        } catch (IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
    }

    public String volverModelo() {
        return "adminViewModelo";
    }
    //Reporte indicadores

    public Date getFechaParametro1() {
        return fechaParametro1;
    }

    public void setFechaParametro1(Date fechaParametro1) {
        this.fechaParametro1 = fechaParametro1;
    }

    public Date getFechaParametro2() {
        return fechaParametro2;
    }

    public void setFechaParametro2(Date fechaParametro2) {
        this.fechaParametro2 = fechaParametro2;
    }

    JasperPrint jasperPrint;

    public void initReport() throws JRException {
        setFechaParametro1(getFechaParametro1());
        setFechaParametro2(getFechaParametro2());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "images"
                + File.separator + "logocfip.png";
        Map parametros = new HashMap();
        parametros.put("parameter1", fechaParametro1);
        parametros.put("parameter2", fechaParametro2);
        parametros.put("logo", newFileName);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(getListaReporteCorrectivo());
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/indicador1.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, parametros, beanCollectionDataSource);
    }

    public void initReport2() throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(getListaMantenimientosTotalTipoCorre());
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("reporteEstrellasCorrectivo.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
    }

    // indicadores correctivos
    public void PDF(ActionEvent actionEvent) throws JRException, IOException {
        initReport();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorMantenimientosCorrectivos.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void DOCX(ActionEvent actionEvent) throws JRException, IOException {
        initReport();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorMantenimientosCorrectivos.docx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRDocxExporter docxExporter = new JRDocxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void XLSX(ActionEvent actionEvent) throws JRException, IOException {
        initReport();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorMantenimientosCorrectivos.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter docxExporter = new JRXlsxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void ODT(ActionEvent actionEvent) throws JRException, IOException {
        initReport();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorMantenimientosCorrectivos.odt");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JROdtExporter docxExporter = new JROdtExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void PPT(ActionEvent actionEvent) throws JRException, IOException {
        initReport();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorMantenimientosCorrectivos.pptx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRPptxExporter docxExporter = new JRPptxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }
    //indicadores valoraciones mantenimientos correctivos

    public void initReportEstreCorre() throws JRException {
        setFechaParametro1(getFechaParametro1());
        setFechaParametro2(getFechaParametro2());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "images"
                + File.separator + "logocfip.png";
        Map parametros = new HashMap();
        parametros.put("parameter1", fechaParametro1);
        parametros.put("parameter2", fechaParametro2);
        parametros.put("logo", newFileName);
        //JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(getListaReportePreventivo());
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(getListaReporteCorrectivoEstrellas());
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/indicadorEstrellas.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, parametros, beanCollectionDataSource);
    }

    public void PDFEstreCorre(ActionEvent actionEvent) throws JRException, IOException {
        initReportEstreCorre();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorValoraciones.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void DOCXEstreCorre(ActionEvent actionEvent) throws JRException, IOException {
        initReportEstreCorre();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorValoraciones.docx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRDocxExporter docxExporter = new JRDocxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void XLSXEstreCorre(ActionEvent actionEvent) throws JRException, IOException {
        initReportEstreCorre();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorValoraciones.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter docxExporter = new JRXlsxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void ODTEstreCorre(ActionEvent actionEvent) throws JRException, IOException {
        initReportEstreCorre();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorValoraciones.odt");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JROdtExporter docxExporter = new JROdtExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void PPTEstreCorre(ActionEvent actionEvent) throws JRException, IOException {
        initReportEstreCorre();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorValoraciones.pptx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRPptxExporter docxExporter = new JRPptxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    //indicadores preventivos
    public void initReportPrev() throws JRException {
        setFechaParametro1(getFechaParametro1());
        setFechaParametro2(getFechaParametro2());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "images"
                + File.separator + "logocfip.png";
        Map parametros = new HashMap();
        parametros.put("parameter1", fechaParametro1);
        parametros.put("parameter2", fechaParametro2);
        parametros.put("logo", newFileName);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(getListaReportePreventivo());
        //JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(getListaReporteCorrectivoEstrellas());
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/indicador2.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, parametros, beanCollectionDataSource);
    }

    public void PDFPrev(ActionEvent actionEvent) throws JRException, IOException {
        initReportPrev();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorMantenimientosPreventivos.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void DOCXPrev(ActionEvent actionEvent) throws JRException, IOException {
        initReportPrev();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorMantenimientosPreventivos.docx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRDocxExporter docxExporter = new JRDocxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void XLSXPrev(ActionEvent actionEvent) throws JRException, IOException {
        initReportPrev();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorMantenimientosPreventivos.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter docxExporter = new JRXlsxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void ODTPrev(ActionEvent actionEvent) throws JRException, IOException {
        initReportPrev();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorMantenimientosPreventivos.odt");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JROdtExporter docxExporter = new JROdtExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void PPTPrev(ActionEvent actionEvent) throws JRException, IOException {
        initReportPrev();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorMantenimientosPreventivos.pptx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRPptxExporter docxExporter = new JRPptxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void indicadorMensaje() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Acceso Denegado", "Se debe cumplir los mantenimientos preventivos de diciembre para poder generar el indicador");
        RequestContext.getCurrentInstance().showMessageInDialog(message);

    }

    public List<CronogramaMantenimientos> getListaReporteTiempo() {
        listaCronogramaMantenimientos = new ArrayList<>();
        return listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().consultaReporteCorrectivoTiempo(getFechaParametro1(), getFechaParametro2());
    }
    
        public List<DiagnosticoMantenimiento> getListaReporteTiempo2() {
        listaDiagnosticoMantenimiento = new ArrayList<>();
        return listaDiagnosticoMantenimiento = getDiagnosticoMantenimientoFacade().consultaReporteCorrectivoTiempo(getFechaParametro1(), getFechaParametro2());
    }

    public String prepareViewReporteTiempo() {
        return "verReporteTiempo";
    }
}
