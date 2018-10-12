/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Aplicacion;
import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.ComputadorInactivo;
import com.proyectoCFIP.entities.CronogramaComponente;
import com.proyectoCFIP.entities.DiagnosticoMantenimiento;
import com.proyectoCFIP.entities.HistorialComputador;
import com.proyectoCFIP.entities.Licencia;
import com.proyectoCFIP.entities.LicenciaPaqueteOffice;
import com.proyectoCFIP.entities.Modelo;
import com.proyectoCFIP.entities.Monitor;
import com.proyectoCFIP.entities.OtroDispositivo;
import com.proyectoCFIP.entities.Seccion;
import com.proyectoCFIP.entities.SistemaOperativo;
import com.proyectoCFIP.entities.TipoActualizacion;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.ComputadorFacade;
import com.proyectoCFIP.sessions.ComputadorInactivoFacade;
import com.proyectoCFIP.sessions.CronogramaComponenteFacade;
import com.proyectoCFIP.sessions.DiagnosticoMantenimientoFacade;
import com.proyectoCFIP.sessions.HistorialComputadorFacade;
import com.proyectoCFIP.sessions.LicenciaFacade;
import com.proyectoCFIP.sessions.LicenciaPaqueteOfficeFacade;
import com.proyectoCFIP.sessions.ModeloFacade;
import com.proyectoCFIP.sessions.MonitorFacade;
import com.proyectoCFIP.sessions.OtroDispositivoFacade;
import com.proyectoCFIP.sessions.SeccionFacade;
import com.proyectoCFIP.sessions.SistemaOperativoFacade;
import com.proyectoCFIP.sessions.TipoActualizacionFacade;
import com.proyectoCFIP.sessions.UsuarioFacade;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.validator.ValidatorException;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class ComputadorController implements Serializable{

    @EJB
    private ComputadorFacade computadorFacade;
    @EJB
    private HistorialComputadorFacade historialComputadorFacade;
    @EJB
    private ModeloFacade modeloFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private SeccionFacade seccionFacade;
    @EJB
    private MonitorFacade monitorFacade;
    @EJB
    private TipoActualizacionFacade tipoActualizacionFacade;
    @EJB
    private SistemaOperativoFacade sistemaOperativoFacade;
    @EJB
    private ComputadorInactivoFacade computadorInactivoFacade;
    @EJB
    private LicenciaFacade licenciaFacade;
    @EJB
    private OtroDispositivoFacade otroDispositivoFacade;
    @EJB
    private LicenciaPaqueteOfficeFacade licenciaPaqueteOfficeFacade;
    @EJB
    private CronogramaComponenteFacade cronogramaComponenteActualFacade;
    private OtroDispositivo otroDispositivoActual;
    private List<Computador> listaComputador = new ArrayList<>();
    private List<Computador> listaComputadoReporte = new ArrayList<>();
    private Computador computadorActual;
    private List<HistorialComputador> listaHistorialComputador = null;
    private List<ComputadorInactivo> listaComputadorInactivo;
    private HistorialComputador historialComputadorActual;
    private ComputadorInactivo computadorInactivoActual;
    private TipoActualizacion tipoActualizacionActual;
    private List<TipoActualizacion> listaTipoActualizacion = null;
    private DiagnosticoMantenimientoFacade diagnosticoMantenimientoFacade;
    private List<DiagnosticoMantenimiento> listaDiagnostico= null;
    private Aplicacion aplicacionActual;
    private List<Aplicacion> listaAplicacion =null;
    private List<Licencia> listaLicencia=null;
    private List<LicenciaPaqueteOffice> listaLicenciaPaqueteOffice=null;
    private Usuario usuarioActual;
    private List<Modelo> listaModelo;
    private CronogramaComponente cronogramaComponenteActual;
    private List<CronogramaComponente> listaCronogramaComponente;
    
    
    public boolean isActualizacionLugar(){
        return historialComputadorActual.getIdTipoActualizacion()== null? false : historialComputadorActual.getIdTipoActualizacion().getIdTipoActualizacion()== (short) 2;
    }
    
    public boolean isActualizacionComponente(){
        return historialComputadorActual.getIdTipoActualizacion()== null? false : historialComputadorActual.getIdTipoActualizacion().getIdTipoActualizacion()== (short) 1;
    }
   
    //metodo para listar formulario por tipo de maquina
    public boolean isComputador(){
        return computadorActual.getIdTipo()== null? false : computadorActual.getIdTipo().getIdTipo() == (short) 1 ? true:computadorActual.getIdTipo().getIdTipo() == (short) 2 ;
    }
    
    //Otro dispositivo

    public OtroDispositivoFacade getOtroDispositivoFacade() {
        return otroDispositivoFacade;
    }

    public void setOtroDispositivoFacade(OtroDispositivoFacade otroDispositivoFacade) {
        this.otroDispositivoFacade = otroDispositivoFacade;
    }

    public OtroDispositivo getOtroDispositivoActual() {
        return otroDispositivoActual;
    }

    public void setOtroDispositivoActual(OtroDispositivo otroDispositivoActual) {
        this.otroDispositivoActual = otroDispositivoActual;
    }
    public String updateOtroDispositivo() {
        try {
            getOtroDispositivoFacade().edit(otroDispositivoActual);
            return "list";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    
    public String prepareEditOtroDispositivo() {
        return "adminEditOtroDispositivo";
    }
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    
    public void llenarListaTipo(){
        listaModelo = getModeloFacade().consultaTipo(computadorActual.getIdTipo());
    }
    public List<Modelo> getListaModeloPorProceso(){
        return listaModelo;
    }
    
    // licencia

    public LicenciaFacade getLicenciaFacade() {
        return licenciaFacade;
    }

    public void setLicenciaFacade(LicenciaFacade licenciaFacade) {
        this.licenciaFacade = licenciaFacade;
    }
    public void llenarLista(){
        listaLicencia = getLicenciaFacade().consultaSO(computadorActual.getIdSistemaOperativo());
    }
    
    public List<Licencia> getListaPorSOSelectOne() {
        return  listaLicencia;
    }
    
      // licencia paquete office

    public LicenciaPaqueteOfficeFacade getLicenciaPaqueteOfficeFacade() {
        return licenciaPaqueteOfficeFacade;
    }

    public void setLicenciaPaqueteOfficeFacade(LicenciaPaqueteOfficeFacade licenciaPaqueteOfficeFacade) {
        this.licenciaPaqueteOfficeFacade = licenciaPaqueteOfficeFacade;
    }

  
    public void llenarListaLicenciaPaquete(){
        listaLicenciaPaqueteOffice = getLicenciaPaqueteOfficeFacade().consultaPaquete(computadorActual.getIdPaqueteOffice());
    }
    
    public List<LicenciaPaqueteOffice> getListaPorPaqueteSelectOne() {
        return  listaLicenciaPaqueteOffice;
    } 
    
    
    
    //Consulta Diagnosticos
    public DiagnosticoMantenimientoFacade getDiagnosticoMantenimientoFacade() {
        return diagnosticoMantenimientoFacade;
    }
    public void setDiagnosticoMantenimientoFacade(DiagnosticoMantenimientoFacade diagnosticoMantenimientoFacade) {
        this.diagnosticoMantenimientoFacade = diagnosticoMantenimientoFacade;
    }
    public List<DiagnosticoMantenimiento> getListaDiagnosticoMantenimiento() {
        listaDiagnostico = new ArrayList<>();
        listaDiagnostico= getDiagnosticoMantenimientoFacade().consultaDiagnostico(new Computador(1));
        return listaDiagnostico;
    }
    
    
    
    //Select one Responsable
    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }
    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }
    public List<Usuario> getListaUsuarioSelectOne() {
        return getUsuarioFacade().findAll();
    }
    
    //Select one Seccion
    public SeccionFacade getSeccionFacade() {
        return seccionFacade;
    }
    public void setSeccionFacade(SeccionFacade seccionFacade) {
        this.seccionFacade = seccionFacade;
    }
    public List<Seccion> getListaSeccionSelectOne() {
        return getSeccionFacade().findAll();
    }
    
    //Select one Monitor
    public MonitorFacade getMonitorFacade() {
        return monitorFacade;
    }
    public void setMonitorFacade(MonitorFacade monitorFacade) {
        this.monitorFacade = monitorFacade;
    }
    public List<Monitor> getListaMonitorSelectOne() {
        return getMonitorFacade().findAll();
    }


    //Select one  Modelo pc
    public ModeloFacade getModeloFacade() {
        return modeloFacade;
    }
    public void setModeloFacade(ModeloFacade modeloFacade) {
        this.modeloFacade = modeloFacade;
    }
    public List<Modelo> getListaModeloSelectOne() {
        return getModeloFacade().consultaTipoCompu();
    }
    
    //Select one Sistema operativo
    public SistemaOperativoFacade getSistemaOperativoFacade() {
        return sistemaOperativoFacade;
    }
    public void setSistemaOperativoFacade(SistemaOperativoFacade sistemaOperativoFacade) {
        this.sistemaOperativoFacade = sistemaOperativoFacade;
    }
    public List<SistemaOperativo> getListaSistemaOperativoSelectOne() {
        return getSistemaOperativoFacade().findAll();
    }
    
    //Select one tipo actualizacion historial
    public TipoActualizacion getTipoActualizacionActual() {
        return tipoActualizacionActual;
    }

    public void setTipoActualizacionActual(TipoActualizacion tipoActualizacionActual) {
        this.tipoActualizacionActual = tipoActualizacionActual;
    }
    
    public TipoActualizacionFacade getTipoActualizacionFacade() {
        return tipoActualizacionFacade;
    }

    public void setTipoActualizacionFacade(TipoActualizacionFacade tipoActualizacionFacade) {
        this.tipoActualizacionFacade = tipoActualizacionFacade;
    }
    
    public List<TipoActualizacion> getListaTipoActualizacionSelectOne() {
        return getTipoActualizacionFacade().findAll();
    }

    public List<TipoActualizacion> getListaTipoActualizacion() {
        return listaTipoActualizacion;
    }

    public void setListaTipoActualizacion(List<TipoActualizacion> listaTipoActualizacion) {
        this.listaTipoActualizacion = listaTipoActualizacion;
    }

    public List<Modelo> getListaModelo() {
        return listaModelo;
    }

    public void setListaModelo(List<Modelo> listaModelo) {
        this.listaModelo = listaModelo;
    }
    
    
    
    
    
   //Computador inactivo
    
    
    public ComputadorInactivoFacade getComputadorInactivoFacade() {
        return computadorInactivoFacade;

    }

    public void setComputadorInactivoFacade(ComputadorInactivoFacade computadorInactivoFacade) {
        this.computadorInactivoFacade = computadorInactivoFacade;
    }

    public List<Computador> getListaComputadorInactivo() {
        return getComputadorFacade().consultaComputadorActivo(false);
    }

    public void setListaComputadorInactivo(List<ComputadorInactivo> listaComputadorInactivo) {
        this.listaComputadorInactivo = listaComputadorInactivo;
    }

    public ComputadorInactivo getComputadorInactivoActual() {
        return computadorInactivoActual;
    }

    public void setComputadorInactivoActual(ComputadorInactivo computadorInactivoActual) {
        this.computadorInactivoActual = computadorInactivoActual;
    }

    public ComputadorController() {
    }
    //Agregar aplicacion

    public Aplicacion getAplicacionActual() {
        return aplicacionActual;
    }

    public void setAplicacionActual(Aplicacion aplicacionActual) {
        this.aplicacionActual = aplicacionActual;
    }

    public List<Aplicacion> getListaAplicacion() {
        return listaAplicacion;
    }

    public void setListaAplicacion(List<Aplicacion> listaAplicacion) {
        this.listaAplicacion = listaAplicacion;
    }
    
    
    // Historial
    public List<HistorialComputador> getListaHistorialComputador() {
        listaHistorialComputador=null;
          if (listaHistorialComputador == null) {
            try {
                listaHistorialComputador = getHistorialComputadorFacade().consultaCompu(computadorActual);
            } catch (Exception e) {
                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            }
        }
        return listaHistorialComputador;
    }

    

    public HistorialComputadorFacade getHistorialComputadorFacade() {
        return historialComputadorFacade;
    }

    public HistorialComputador getHistorialComputadorActual() {
        if (historialComputadorActual == null) {
            historialComputadorActual = new HistorialComputador();
        }
        return historialComputadorActual;
    }

    public void setHistorialComputadorActual(HistorialComputador historialComputadorActual) {
        this.historialComputadorActual = historialComputadorActual;
    }
 // Computador
    public List<Computador> getListaComputador() {
        listaComputador = new ArrayList<>();
        listaComputador = getComputadorFacade().findAll();
        return listaComputador;
    }
    public List<Computador> getListaComputadorActivo() {
        listaComputador = new ArrayList<>();
        listaComputador = getComputadorFacade().consultaComputadorActivo(true);
        return listaComputador;
    }
    public List<Computador> getListaComputadorUser() {
        listaComputador = new ArrayList<>();
        listaComputador = getComputadorFacade().consultaUsuario(usuarioActual);
        return listaComputador;
    }
     public List<Computador> getListaComputadorProgramado() {
        listaComputador = new ArrayList<>();
        listaComputador = getComputadorFacade().consultaComputadorProgramado(false, true);
        return listaComputador;
    }

    public ComputadorFacade getComputadorFacade() {
        return computadorFacade;
    }

    public Computador getComputadorActual() {
        if (computadorActual == null) {
            computadorActual = new Computador();
        }
        return computadorActual;
    }

    public void setComputadorActual(Computador computadorActual) {
        this.computadorActual = computadorActual;
    }
    private void recargarLista() {
        listaDiagnostico = null;
        listaHistorialComputador =null;
        listaComputador = null;
    }
    public String prepateViewComputadorUser(){
        return "/usuario/modSoporteIT/equiposCargo/listaEquiposCargo";
    }
    public String prepareViewProgramar(){
        return "/administrador/modSoporteIT/calendarioMantenimiento/preventivos/listaProgramarMantenimiento";
    }
    public String prepareCreate() {
        listaAplicacion = new ArrayList<>();
        computadorActual = new Computador();
        historialComputadorActual = new HistorialComputador();
        return "/administrador/modSoporteIT/inventarioTecnologico/crearEquipo";
    }
    public String prepareEdit() {
        historialComputadorActual = new HistorialComputador();
        return "editarEquipo";
    }
   
    public String prepareEditLic() {
        return "editarLicenciaComputador";
    }
    public String prepareEditActualizacionComponente() {
        historialComputadorActual = new HistorialComputador();
        tipoActualizacionActual = new TipoActualizacion(1);
        return "adminEditComputadorAC";
    }
        public String prepareEditActualizacionLugar() {
        historialComputadorActual = new HistorialComputador();
        tipoActualizacionActual = new TipoActualizacion(2);
        return "adminEditComputadorAL";
    }

    public String prepareViewComputadorInactivo(){
        listaComputadorInactivo = new ArrayList<>();
        listaComputadorInactivo=getComputadorInactivoFacade().findAll();
        return "/administrador/modSoporteIT/inventarioTecnologico/listaBaja";
    }

    public String prepareList() {
        return  "/administrador/modSoporteIT/inventarioTecnologico/listaInventario";
    }
    public String prepareInactivo(){
        historialComputadorActual = new HistorialComputador();
        return "/administrador/modSoporteIT/inventarioTecnologico/crearBaja";
    }
    
    public void addAplicacion(){
        listaAplicacion.add(aplicacionActual);
        aplicacionActual = new Aplicacion();
    }
    public String addComputador() {
        try {
            computadorActual.setEstadoComputador(true);
            if(computadorActual.getIdTipo().getIdTipo()>2){
                computadorActual.setDiscoDuro(null);
                computadorActual.setMemoriaRam(null);
                computadorActual.setIdLam("N/A");
                computadorActual.setIdPaqueteOffice(null);
                computadorActual.setIdSistemaOperativo(null);
                computadorActual.setIdMonitor(null);
                computadorActual.setProcesador("N/A");
                getComputadorFacade().create(computadorActual);
            }else{
                getComputadorFacade().create(computadorActual);
            }
            historialComputadorActual.setIdComputador(computadorActual);
            historialComputadorActual.setIdTipoActualizacion(new TipoActualizacion(4, "creación inicial"));
            if(computadorActual.getDiscoDuro()==null){
                historialComputadorActual.setDiscoDuro(null);
            }else{
                historialComputadorActual.setDiscoDuro(computadorActual.getDiscoDuro());
            }
            if(computadorActual.getMemoriaRam()==null){
                historialComputadorActual.setMemoriaRam(null);
            }else{
                historialComputadorActual.setMemoriaRam(computadorActual.getMemoriaRam());
            }
            if(computadorActual.getIdSeccion()==null){
                 historialComputadorActual.setSeccion("PENDIENTE");
            }else{
                 historialComputadorActual.setSeccion(computadorActual.getIdSeccion().toString());
            }
            if(computadorActual.getIdUsuario()==null){
                historialComputadorActual.setResponsable("PENDIENTE");
            }else{
                historialComputadorActual.setResponsable(computadorActual.getIdUsuario().toString());
            }
            if(computadorActual.getIdSistemaOperativo()==null){
                historialComputadorActual.setSistemaOperativo("N/A");
            }else{
                historialComputadorActual.setSistemaOperativo(computadorActual.getIdSistemaOperativo().toString());
            }
            if(computadorActual.getIdMonitor()==null){
                historialComputadorActual.setMonitor("N/A");
            }else{
                historialComputadorActual.setMonitor(computadorActual.getIdMonitor().toString());
            }
            if(computadorActual.getIdLam().equals("N/A")){
                historialComputadorActual.setIdLam("N/A");
            }else{
                historialComputadorActual.setIdLam(computadorActual.getIdLam());
            }
            historialComputadorActual.setFechaHistorial(new Date());
            historialComputadorActual.setDescripcion("Computador Creado en el sistema");
            getHistorialComputadorFacade().create(historialComputadorActual);
            addSuccessMessage("Computador guardado", "El computador con service tag o (S/N): "+computadorActual.getCodigoComputador().toUpperCase()+" ha sido guardado exitosamente");
            recargarLista();
            return viewHistorialComputadores();
        } catch (Exception e) {
            addErrorMessage("Error al guardar computador", "Por favor intentalo de nuevo");            
            return null;
        }
    }
    public String addComputadorInactivo() {
        try {
            computadorActual.setEstadoComputador(false);
            computadorActual.setIdUsuario(null);
            computadorActual.setAplicacionList(null);
            computadorActual.setIdLicencia(null);
            computadorActual.setIdPaqueteOffice(null);
            computadorActual.setIdSistemaOperativo(null);
            computadorActual.setIdMonitor(null);
            computadorActual.setIdSeccion(null);
            computadorActual.setIdLam(null);
            computadorActual.setIdLicenciaPaqueteOffice(null);
            getComputadorFacade().edit(computadorActual);
            
            historialComputadorActual.setIdComputador(computadorActual);
            historialComputadorActual.setIdTipoActualizacion(new TipoActualizacion(5, "dado de baja"));
            if(computadorActual.getDiscoDuro()==null){
                historialComputadorActual.setDiscoDuro(null);
            }else{
                historialComputadorActual.setDiscoDuro(computadorActual.getDiscoDuro());
            }
            if(computadorActual.getMemoriaRam()==null){
                historialComputadorActual.setMemoriaRam(null);
            }else{
                historialComputadorActual.setMemoriaRam(computadorActual.getMemoriaRam());
            }
            historialComputadorActual.setSeccion("");
            historialComputadorActual.setResponsable("");
            historialComputadorActual.setSistemaOperativo("");
            historialComputadorActual.setMonitor("");
            historialComputadorActual.setIdLam("");
            historialComputadorActual.setFechaHistorial(new Date());
            getHistorialComputadorFacade().create(historialComputadorActual);
            recargarLista();
            return "listaInventario";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String createHistorial(){
         try {
            historialComputadorActual.setIdComputador(computadorActual);
            if(computadorActual.getDiscoDuro()==null){
                historialComputadorActual.setDiscoDuro(null);
            }else{
                historialComputadorActual.setDiscoDuro(computadorActual.getDiscoDuro());
            }
            if(computadorActual.getMemoriaRam()==null){
                historialComputadorActual.setMemoriaRam(null);
            }else{
                historialComputadorActual.setMemoriaRam(computadorActual.getMemoriaRam());
            }
            if(computadorActual.getIdSeccion()==null){
                 historialComputadorActual.setSeccion("PENDIENTE");
            }else{
                 historialComputadorActual.setSeccion(computadorActual.getIdSeccion().toString());
            }
            if(computadorActual.getIdUsuario()==null){
                historialComputadorActual.setResponsable("PENDIENTE");
            }else{
                historialComputadorActual.setResponsable(computadorActual.getIdUsuario().toString());
            }
            if(computadorActual.getIdSistemaOperativo()==null){
                historialComputadorActual.setSistemaOperativo("PENDIENTE");
            }else{
                historialComputadorActual.setSistemaOperativo(computadorActual.getIdSistemaOperativo().toString());
            }
            if(computadorActual.getIdMonitor()==null){
                historialComputadorActual.setMonitor("PENDIENTE");
            }else{
                historialComputadorActual.setMonitor(computadorActual.getIdMonitor().toString());
            }
            if(computadorActual.getIdLam().equals("")){
                historialComputadorActual.setIdLam("PENDIENTE");
            }else{
                historialComputadorActual.setIdLam(computadorActual.getIdLam());
            }
            historialComputadorActual.setFechaHistorial(new Date());
            getHistorialComputadorFacade().create(historialComputadorActual);
            getComputadorFacade().edit(computadorActual);
            recargarLista();
            addSuccessMessage("Equipo actualizado", "El equipo con service tag o (S/N): "+computadorActual.getCodigoComputador().toUpperCase()+" ha sido actualizado exitosamente");
            return "listaInventario";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String editComputador(){
         try {
            getComputadorFacade().edit(computadorActual);
            recargarLista();
            return "list";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public void validarComputador(FacesContext contex, UIComponent component, Object value)throws ValidatorException{
        Computador computadorConsulta=getComputadorFacade().findByCodigoComputador((String)value);
        if(computadorConsulta != null){
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Service tag o (S/N) repetido","Service tag o (S/N) ya se encuentra registrado, intente con otro"));   
        }else{
            String codigo=(String) value;
            computadorActual.setCodigoComputador(codigo);
        }
    }
    public void validarMessage(FacesContext contex, UIComponent component, Object value)throws ValidatorException{
         throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solo numeros","Solo numeros"));   
    }
    public String updateComputador() {
        try {
            getComputadorFacade().edit(computadorActual);
            recargarLista();
            return "adminViewComputador";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public void prepareViewHistorial(ActionEvent event) {
        computadorActual = new Computador();
        computadorActual = (Computador) event.getComponent().getAttributes().get("computador");
        listaHistorialComputador = new ArrayList<>();
        //listaActividadAprendizaje=actividadActual.getActividadAprendizajeList(); 
    }
    public String viewHistorialComputadores(){
        return "/administrador/modSoporteIT/inventarioTecnologico/verEquipo";
    }

    public String deleteComputador() {
        try {
            getComputadorFacade().remove(computadorActual);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
        return "List";
    }
    public void deleteAplicacionLista(){
          try {
            listaAplicacion.remove(aplicacionActual);
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }
    public StreamedContent getImage() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Get ID value from actual request param.
            String id = context.getExternalContext().getRequestParameterMap().get("id_modelo");
            Computador image = computadorFacade.find(Integer.valueOf(id));
            return new DefaultStreamedContent(new ByteArrayInputStream(image.getIdModelo().getImagenModelo()));
        }
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
    
    public Computador getComputador(java.lang.Integer id) {
        return getComputadorFacade().find(id);
    }
    
    @FacesConverter(forClass = Computador.class, value="computadorConverter")
    public static class ComputadorControllerConverter implements Converter {
        
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ComputadorController controller = (ComputadorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "computadorController");
            return controller.getComputador(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Computador) {
                Computador o = (Computador) object;
                return getStringKey(o.getIdComputador());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Computador.class.getName());
            }
        }

    }
    //Cronograma componente

    public CronogramaComponenteFacade getCronogramaComponenteFacade() {
        return cronogramaComponenteActualFacade;
    }

    public void setCronogramaComponenteFacade(CronogramaComponenteFacade cronogramaComponenteActualFacade) {
        this.cronogramaComponenteActualFacade = cronogramaComponenteActualFacade;
    }

    public CronogramaComponente getCronogramaComponenteActual() {
        return cronogramaComponenteActual;
    }

    public void setCronogramaComponenteActual(CronogramaComponente cronogramaComponenteActual) {
        this.cronogramaComponenteActual = cronogramaComponenteActual;
    }

    public List<CronogramaComponente> getListaCronogramaComponente() {
        return listaCronogramaComponente = getCronogramaComponenteFacade().consultaComputador(computadorActual);
    }

    public void setListaCronogramaComponente(List<CronogramaComponente> listaCronogramaComponente) {
        this.listaCronogramaComponente = listaCronogramaComponente;
    }
     public void prepareCreateCronogramaComponente() {
        cronogramaComponenteActual = new CronogramaComponente();
    }
     public String addCronogramaComponente(){
       try {
            getCronogramaComponenteActual().setIdComputador(computadorActual);
            getCronogramaComponenteFacade().create(cronogramaComponenteActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Componente adicional creado", "El componente adicional fue creado");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            return "verEquipo"; 
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
     }
}
