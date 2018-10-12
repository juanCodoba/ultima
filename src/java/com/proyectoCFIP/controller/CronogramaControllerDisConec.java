/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.Area;
import com.proyectoCFIP.entities.CronogramaManteDisCon;
import com.proyectoCFIP.entities.CronogramaManteDispositivo;
import com.proyectoCFIP.entities.DiagnosticoMantenimientoDisCon;
import com.proyectoCFIP.entities.DispositivoConectividad;
import com.proyectoCFIP.entities.TipoMantenimiento;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.CronogramaManteDisConFacade;
import com.proyectoCFIP.sessions.DiagnosticoMantenimientoDisConFacade;
import com.proyectoCFIP.sessions.DispositivoConectividadFacade;
import com.proyectoCFIP.sessions.EmailSessionBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class CronogramaControllerDisConec {

    @EJB
    private CronogramaManteDisConFacade cronogramaFacade;
    @EJB
    private DispositivoConectividadFacade dispositivoConecFacade;
    @EJB
    EmailSessionBean emailBean;
    @EJB
    private DiagnosticoMantenimientoDisConFacade diagosticoMantenimientoFacade;
    @EJB
    private CronogramaManteDisConFacade cronogramaManteDisConFacade;
    private CronogramaManteDisCon cronogramaMantenimientoActual;
    private DispositivoConectividad dispositivoConectividadActual;
    private Usuario usuarioActual;
    private String ticket="";
    private DiagnosticoMantenimientoDisCon diagnosticoActual;
    public List<CronogramaManteDisCon> listaCronogramaMantenimiento;
    private List<CronogramaManteDisCon> listaCronogramaMantenimientosC = null;
    private List<CronogramaManteDisCon> listaCronogramaMantenimientosP = null;
    private TipoMantenimiento tipoMantenimientoActual;
    public CronogramaControllerDisConec() {
    }
    
    
    public List<CronogramaManteDisCon> getListaMantenimientosP(){
        listaCronogramaMantenimiento = new ArrayList<>();
        return listaCronogramaMantenimiento = getCronogramaManteDisConFacade().consultaCronogramaTipoPendiente(false, new TipoMantenimiento(2));
    }
    public List<CronogramaManteDisCon> getListaMantenimientosC(){
        listaCronogramaMantenimiento = new ArrayList<>();
        return listaCronogramaMantenimiento = getCronogramaManteDisConFacade().consultaCronogramaTipoPendiente(false, new TipoMantenimiento(1));
    }
    
    //GET AND SET
    public List<CronogramaManteDisCon> getListaCronogramaMantenimientos() {
        return getCronogramaManteDisConFacade().findAll();
    }
    public DiagnosticoMantenimientoDisCon getDiagnosticoActual() {
        return diagnosticoActual;
    }

    public void setDiagnosticoActual(DiagnosticoMantenimientoDisCon diagnosticoActual) {
        this.diagnosticoActual = diagnosticoActual;
    }
    
    
    public CronogramaManteDisConFacade getCronogramaFacade() {
        return cronogramaFacade;
    }

    public void setCronogramaFacade(CronogramaManteDisConFacade cronogramaFacade) {
        this.cronogramaFacade = cronogramaFacade;
    }

    public CronogramaManteDisCon getCronogramaMantenimientoActual() {
        return cronogramaMantenimientoActual;
    }

    public void setCronogramaMantenimientoActual(CronogramaManteDisCon cronogramaMantenimientoActual) {
        this.cronogramaMantenimientoActual = cronogramaMantenimientoActual;
    }

    public DispositivoConectividad getDispositivoConectividadActual() {
        return dispositivoConectividadActual;
    }

    public void setDispositivoConectividadActual(DispositivoConectividad dispositivoConectividadActual) {
        this.dispositivoConectividadActual = dispositivoConectividadActual;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public DispositivoConectividadFacade getDispositivoConecFacade() {
        return dispositivoConecFacade;
    }

    public void setDispositivoConecFacade(DispositivoConectividadFacade dispositivoConecFacade) {
        this.dispositivoConecFacade = dispositivoConecFacade;
    }
    
    
    public List<DispositivoConectividad> getListaDispoConecSelectOne() {
        return getDispositivoConecFacade().consultaUsuario(usuarioActual);
    }   
    
     public List<DispositivoConectividad> getListaDispositivoDocenteSelectOne() {
        return getDispositivoConecFacade().consultaArea(new Area(1 , "Academica"));
    }

    public DiagnosticoMantenimientoDisConFacade getDiagosticoMantenimientoFacade() {
        return diagosticoMantenimientoFacade;
    }

    public void setDiagosticoMantenimientoFacade(DiagnosticoMantenimientoDisConFacade diagosticoMantenimientoFacade) {
        this.diagosticoMantenimientoFacade = diagosticoMantenimientoFacade;
    }
    
       public List<DiagnosticoMantenimientoDisCon> getListaDiagnosticoTicket() {
        return getDiagosticoMantenimientoFacade().consultaTicket(cronogramaMantenimientoActual);
    }
    
  
    public List<CronogramaManteDisCon> getListaMantenimientosTotalTipo(){
        listaCronogramaMantenimiento = new ArrayList<>();
        return listaCronogramaMantenimiento = getCronogramaManteDisConFacade().consultaTotalTipoPrev();
    }
    public List<CronogramaManteDisCon> getListaMantenimientosTotalTipoCorre(){
        listaCronogramaMantenimiento = new ArrayList<>();
        return listaCronogramaMantenimiento = getCronogramaManteDisConFacade().consultaTotalTipoCorre();
    }
    public void prepareViewDiagnostico(ActionEvent event) {
        cronogramaMantenimientoActual = new CronogramaManteDisCon();
        cronogramaMantenimientoActual = (CronogramaManteDisCon) event.getComponent().getAttributes().get("mantenimientoDC");
        listaCronogramaMantenimiento = new ArrayList<>();
        //listaActividadAprendizaje=actividadActual.getActividadAprendizajeList(); 
    }
    public String prepareViewMantenimientosCorreDC() {
        return "/Admin/moduloConfigMantenimiento/adminViewIncidentesPendientesDC";
    }
    public String prepareViewMantenimientosPrevDC(){
        return "/Admin/moduloConfigMantenimiento/adminViewMantenimientosPreventivosDC";
    }
     public List<CronogramaManteDisCon> getListaMantenimientosDisConEstadoUser(){
        return getCronogramaManteDisConFacade().consultaCronogramaEstadoUser(usuarioActual);   
    }
    public List<CronogramaManteDisCon> getListaMantenimientosDisConEstadoUserDocente(){
           if(usuarioActual.getIdUsuario()== 304) {
                return getCronogramaManteDisConFacade().consultaCronogramaEstadoUserDocente(); 
           }else{
                return getCronogramaManteDisConFacade().consultaCronogramaEstadoUser(usuarioActual); 
           }
    }
    
    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    
    public String viewDiagnostico(){
        ticket="CFIPMP-DC";
        return "adminViewDiagnosticoMantenimientoPrevDC";
    }
     public String viewDiagnosticoCorre(){
        ticket="CFIPMC-DC";
        return "adminViewDiagnosticoMantenimientoPrevDC";
    }
    
    public String prepareViewMantenimientosTotales() {
        return "/Admin/moduloConfigMantenimiento/adminViewMantenimientosTotalesDC";
    }
    public String prepareSolucionPreventivo(){
        tipoMantenimientoActual= new TipoMantenimiento(2);
        diagnosticoActual = new DiagnosticoMantenimientoDisCon();
        return "adminCrearDiagnosticoDC";
    }

    public String prepareViewPendientesDC(){
        return "adminViewPendientesMantenimientoDC";
    }
    public String prepareViewSolucionIncidentesDisCon(){
        return "adminViewSolucionIncidenteDC";
    }
    public String prepareCreateReportarFallo() {
        cronogramaMantenimientoActual = new CronogramaManteDisCon();
        return "/User/userCrearIncidenteDisConec";
    }
    public String addDiagnostico(){
     if(diagnosticoActual.getMantenimientoCorrectivo() == true){
        cronogramaMantenimientoActual.setIdTipoMantenimiento(new TipoMantenimiento(3,"preventivo y correctivo"));
        cronogramaFacade.edit(cronogramaMantenimientoActual);
        cronogramaMantenimientoActual.getIdDispositivoConectividad().setEstadoProgramado(false);
        dispositivoConecFacade.edit(cronogramaMantenimientoActual.getIdDispositivoConectividad());
     }
    diagnosticoActual.setIdCronogramaManteDisCon(cronogramaMantenimientoActual);
    diagnosticoActual.setIdUsuario(usuarioActual);
    diagosticoMantenimientoFacade.create(diagnosticoActual);
    cronogramaMantenimientoActual.setEstadoMantenimientoDisCon(true);
    cronogramaMantenimientoActual.getIdDispositivoConectividad().setEstadoProgramado(false);
    dispositivoConecFacade.edit(cronogramaMantenimientoActual.getIdDispositivoConectividad());
    cronogramaFacade.edit(cronogramaMantenimientoActual);
    ticket="CFIPDIAG-DC";
    sendMailDiagPrevUser();
        return "adminViewSolucionIncidenteDC";
    }
      
    public String addDiagnosticoFallo(){
        diagnosticoActual.setIdCronogramaManteDisCon(cronogramaMantenimientoActual);
        diagnosticoActual.setIdUsuario(usuarioActual);
        diagosticoMantenimientoFacade.create(diagnosticoActual);
        cronogramaMantenimientoActual.setEstadoMantenimientoDisCon(true);
        cronogramaManteDisConFacade.edit(cronogramaMantenimientoActual);
        recargarLista();
        sendMailSolucionUserDC();
        return "adminViewSolucionIncidenteDC";
    }
      public String prepareSolucionCorrectivo() {
        tipoMantenimientoActual= new TipoMantenimiento(1);
        diagnosticoActual = new DiagnosticoMantenimientoDisCon();
        return "/Admin/moduloConfigMantenimiento/adminCrearDiagnosticoFalloDC";
    }
    public List<DiagnosticoMantenimientoDisCon> getListaDiagnosticoMantenimiento(){
        return getDiagosticoMantenimientoFacade().findAll();
    }
    public String addCronogramaMantenimientoDC() {
        try {
            cronogramaMantenimientoActual.setDescripcionProblemaDisCon("Ninguno reportado por el usuario");
            cronogramaMantenimientoActual.setIdTipoMantenimiento(new TipoMantenimiento(2, "preventivo"));
            cronogramaMantenimientoActual.setFechaProgMantenimientoDisCon(new Date());
            cronogramaMantenimientoActual.setEstadoMantenimientoDisCon(false);
            cronogramaMantenimientoActual.setIdDispositivoConectividad(dispositivoConectividadActual);
            getCronogramaManteDisConFacade().create(cronogramaMantenimientoActual);
            dispositivoConectividadActual.setEstadoProgramado(true);
            getDispositivoConecFacade().edit(dispositivoConectividadActual);
            recargarLista();
            prepareViewCalendario();
            addSuccessMessage("Mantenimiento preventivo programado", "Mantenimiento preventivo programado exitosamente con numero de ticket "+cronogramaMantenimientoActual.getIdCronogramaManteDisCon()+"CFIPMP-DC");
            return "adminCalendarioCronogramaDC";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
     public String prepareCreateMantenimientoP(){
        cronogramaMantenimientoActual = new CronogramaManteDisCon();
        return "adminCrearMantePrevDC";
    }
    public String addCronogramaMantenimientoFallo() {
        try {
            cronogramaMantenimientoActual.setIdTipoMantenimiento(new TipoMantenimiento(1, "correctivo"));
            cronogramaMantenimientoActual.setFechaProgMantenimientoDisCon(new Date());
            cronogramaMantenimientoActual.setFechaInicioMantenimientoDisCon(new Date());
            cronogramaMantenimientoActual.setHoraMantenimientoDisCon(new Date());
            cronogramaMantenimientoActual.setEstadoMantenimientoDisCon(false);
            cronogramaMantenimientoActual.setIdUsuario(usuarioActual);
            getCronogramaFacade().create(cronogramaMantenimientoActual);
            //recargarLista();
            ticket="CFIPMC-DC";
            sendMailRegistroTec();
            sendMailRegistroUser();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
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
    
     
     private void sendMail(String to, String subject, String body) {
        try {
            emailBean.sendMail(to, subject, body);
            JsfUtil.addSuccessMessage("Mensaje Enviado Exitosamente");
        } catch (NamingException | MessagingException ex) {
            JsfUtil.addErrorMessage("Error sending message " + ex.getClass().getName());
        }
    }
     
      private void sendMailRegistroTec() {
        
            String seccion ="";
            String responsable ="";
            String bloque = "";
            String nombreUsuario= "";
            String correoJornada = "";
            String jornada= "";
            if(cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdSeccion()==null){
                seccion="N/A";
                bloque="N/A";
            }else{
                seccion=cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdSeccion().toString();
            }
            if(cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdUsuario()==null){
                responsable="N/A";
            }else{
                responsable=cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdUsuario().toString();
            }
             if(cronogramaMantenimientoActual.getNombreUsuarioReporte()==null){
                nombreUsuario=" ";
            }else{
                nombreUsuario = cronogramaMantenimientoActual.getNombreUsuarioReporte().toUpperCase();
            }
             if(cronogramaMantenimientoActual.getIdTipoJornada()==null){
                jornada="N/A";
            }else{
                jornada=cronogramaMantenimientoActual.getIdTipoJornada().getNombre();
            }
            if(cronogramaMantenimientoActual.getIdTipoJornada()==null){
                correoJornada="";
            }else{
                correoJornada=cronogramaMantenimientoActual.getIdTipoJornada().getCorreo();
            }
        String subject = seccion.toUpperCase()+", FALLO REPORTADO TICKET #"+ cronogramaMantenimientoActual.getIdCronogramaManteDisCon()+ticket ;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(cronogramaMantenimientoActual.getIdCronogramaManteDisCon());
        mensaje.append(ticket);
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(responsable.toUpperCase());
         mensaje.append("\nUSUARIO: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nNOMBRE DEL USUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(nombreUsuario);
        mensaje.append("\nDESCRIPCIÓN DEL FALLO: ");
        mensaje.append(cronogramaMantenimientoActual.getDescripcionProblemaDisCon().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(cronogramaMantenimientoActual.getFechaInicioMantenimientoDisCon().toLocaleString());
        mensaje.append("\nJORNADA DONDE SE REPORTO EL FALLO: ");
        mensaje.append(jornada.toUpperCase());
        mensaje.append("\n-----------------------------------------------------");
        mensaje.append("\nDATOS DEL DISPOSITIVO DE CONECTIVIDAD");
        mensaje.append("\n-----------------------------------------------------");
        mensaje.append("\nTIPO DE DISPOSITIVO: ");
        mensaje.append(cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdTipo().getNombreTipo().toUpperCase());
        mensaje.append("\nSERVICE TAG O (S/N): ");
        mensaje.append(cronogramaMantenimientoActual.getIdDispositivoConectividad().getCodigoNA().toUpperCase());
        mensaje.append("\nMODELO: ");
        mensaje.append(cronogramaMantenimientoActual.getIdDispositivoConectividad().getModeloNA().toUpperCase());
       
        
        sendMail("auxsistemas2@cfiprovidencia.com"+" "+"sistemas@cfiprovidencia.com"+" "+"auxsistemas@cfiprovidencia.com"+" "+jornada, subject, mensaje.toString());
    }
    
      private void sendMailDiagPrevUser() {
           String seccion ="";
            String responsable ="";
            String bloque = "";
            if(cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdSeccion()==null){
                seccion="N/A";
                bloque="N/A";
            }else{
                seccion=cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdSeccion().toString();
                bloque=cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdSeccion().getIdBloque().getNombreBloque().toUpperCase();
            }
            if(cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdUsuario()==null){
                responsable="N/A";
            }else{
                responsable=cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdUsuario().toString();
            }
           
        String subject = "DIAGNOSTICO DEL TICKET #" + diagnosticoActual.getIdCronogramaManteDisCon().getIdCronogramaManteDisCon()+"CFIPMP-DIS";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CODIGO DEL DIAGNOSTICO ");
        mensaje.append(diagnosticoActual.getIdDiagnosticoManteDisCon());
        mensaje.append(ticket);
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL MANTENIMIENTO ");
        mensaje.append(diagnosticoActual.getIdCronogramaManteDisCon().getIdTipoMantenimiento().getNombreTipoMantenimiento().toUpperCase());
        mensaje.append("\n---------------------------------------------------------------------");
       
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(diagnosticoActual.getIdCronogramaManteDisCon().getFechaProgMantenimientoDisCon().toLocaleString());
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(responsable.toUpperCase());
        mensaje.append("\nSECCIÓN: ");
        mensaje.append(seccion.toUpperCase());
        mensaje.append("\nBLOQUE: ");
        mensaje.append(bloque.toUpperCase());
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL DIAGNOSTICO:");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nREVISION: ");
        mensaje.append(diagnosticoActual.getRevisionDisCon().toUpperCase());
        mensaje.append("\nFECHA DE LA REVISION: ");
        mensaje.append(diagnosticoActual.getFechaRevisionDisCon().toLocaleString());
        mensaje.append("\nDIAGNOSTICO: ");
        mensaje.append(diagnosticoActual.getDiagnosticoDisCon().toUpperCase());
        mensaje.append("\nENTREGA DEL EQUIPO: ");
        mensaje.append(diagnosticoActual.getFechaEntragaDisCon().toLocaleString());
        mensaje.append("\nTECNICO QUE ATENDIO EL CASO: ");
        mensaje.append(diagnosticoActual.getIdUsuario().toString());
        
        
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL DISPOSITIVO ");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nTIPO: ");
        mensaje.append(diagnosticoActual.getIdCronogramaManteDisCon().getIdDispositivoConectividad().getIdTipo().getNombreTipo().toUpperCase());
        mensaje.append("\nSERVICE TAG O (S/N): ");
        mensaje.append(diagnosticoActual.getIdCronogramaManteDisCon().getIdDispositivoConectividad().getCodigoNA().toUpperCase());
        mensaje.append("\nMODELO: ");
        mensaje.append(diagnosticoActual.getIdCronogramaManteDisCon().getIdDispositivoConectividad().getModeloNA().toUpperCase());
        mensaje.append("\nMARCA: ");
        mensaje.append(diagnosticoActual.getIdCronogramaManteDisCon().getIdDispositivoConectividad().getMarcaNA().toUpperCase());
        
        sendMail(diagnosticoActual.getIdCronogramaManteDisCon().getIdDispositivoConectividad().getIdUsuario().getCorreoUsuario()+" "+"lccabal@misena.edu.co"+" "+"sistemas@cfiprovidencia.com"+" "+"auxsistemas@cfiprovidencia.com", subject, mensaje.toString());
    }
    private void sendMailSolucionUserDC() {
            String seccion ="";
            String responsable ="";
            String bloque = "";
            String correo = "";
            String jornada = "";
            String correoJornada = "";
            if(diagnosticoActual.getIdCronogramaManteDisCon().getIdDispositivoConectividad().getIdSeccion()==null){
                seccion="N/A";
                bloque="N/A";
            }else{
                seccion=diagnosticoActual.getIdCronogramaManteDisCon().getIdDispositivoConectividad().getIdSeccion().toString();
                bloque=diagnosticoActual.getIdCronogramaManteDisCon().getIdDispositivoConectividad().getIdSeccion().getIdBloque().getNombreBloque();
            }
            if(diagnosticoActual.getIdCronogramaManteDisCon().getIdDispositivoConectividad().getIdUsuario()==null){
                responsable="N/A";
            }else{
                responsable=diagnosticoActual.getIdCronogramaManteDisCon().getIdDispositivoConectividad().getIdUsuario().toString();
            }
             if(diagnosticoActual.getIdCronogramaManteDisCon().getCorreoUsuarioReporte()==null){
                correo="";
            }else{
                correo=diagnosticoActual.getIdCronogramaManteDisCon().getCorreoUsuarioReporte();
            }
            if(diagnosticoActual.getIdCronogramaManteDisCon().getIdTipoJornada()==null){
                jornada="N/A";
            }else{
                jornada=diagnosticoActual.getIdCronogramaManteDisCon().getIdTipoJornada().getNombre();
            }
            if(diagnosticoActual.getIdCronogramaManteDisCon().getIdTipoJornada()==null){
                correoJornada="";
            }else{
                correoJornada=diagnosticoActual.getIdCronogramaManteDisCon().getIdTipoJornada().getCorreo();
            }
        String subject = "DIAGNOSTICO DEL TICKET #" + diagnosticoActual.getIdCronogramaManteDisCon().getIdCronogramaManteDisCon()+"CFIPMC-DC";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CODIGO DEL DIAGNOSTICO ");
        mensaje.append(diagnosticoActual.getIdDiagnosticoManteDisCon());
        mensaje.append("CFIPDIAG-DC");
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL MANTENIMIENTO CORRECTIVO");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDESCRIPCIÓN DEL FALLO: ");
        mensaje.append(diagnosticoActual.getIdCronogramaManteDisCon().getDescripcionProblemaDisCon().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(diagnosticoActual.getIdCronogramaManteDisCon().getFechaProgMantenimientoDisCon().toLocaleString());
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(responsable.toUpperCase());
        mensaje.append("\nSECCIÓN: ");
        mensaje.append(seccion.toUpperCase());
        mensaje.append("\nBLOQUE: ");
        mensaje.append(bloque.toUpperCase());
        mensaje.append("\nJORNADA DONDE SE REPORTO EL FALLO: ");
        mensaje.append(jornada.toUpperCase());
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL DIAGNOSTICO:");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nREVISIÓN: ");
        mensaje.append(diagnosticoActual.getRevisionDisCon().toUpperCase());
        mensaje.append("\nFECHA DE LA REVISIÓN: ");
        mensaje.append(diagnosticoActual.getFechaRevisionDisCon().toLocaleString());
        mensaje.append("\nDIAGNOISTICO: ");
        mensaje.append(diagnosticoActual.getDiagnosticoDisCon().toUpperCase());
        mensaje.append("\nFECHA DE ENTREGA DEL DISPOSITIVO: ");
        mensaje.append(diagnosticoActual.getFechaEntragaDisCon().toLocaleString());
        
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL DISPOSITIVO:");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nSERVICE TAG O (S/N): ");
        mensaje.append(diagnosticoActual.getIdCronogramaManteDisCon().getIdDispositivoConectividad().getCodigoNA().toUpperCase());
        mensaje.append("\nTIPO: ");
        mensaje.append(diagnosticoActual.getIdCronogramaManteDisCon().getIdDispositivoConectividad().getIdTipo().getNombreTipo().toUpperCase());
        mensaje.append("\nMODELO: ");
        mensaje.append(diagnosticoActual.getIdCronogramaManteDisCon().getIdDispositivoConectividad().getModeloNA().toUpperCase());
        mensaje.append("\nMARCA: ");
        mensaje.append(diagnosticoActual.getIdCronogramaManteDisCon().getIdDispositivoConectividad().getMarcaNA().toUpperCase());
        
        mensaje.append("\n\nSU INCIDENTE FUE CORREGIDO SATISFACTORIAMENTE");        
        sendMail(diagnosticoActual.getIdCronogramaManteDisCon().getIdDispositivoConectividad().getIdUsuario().getCorreoUsuario()+" "+correo+" "+"auxsistemas2@cfiprovidencia.com"+" "+"sistemas@cfiprovidencia.com"+" "+"auxsistemas@cfiprovidencia.com"+" "+correoJornada, subject, mensaje.toString());
    }
    private void sendMailRegistroUser() {
            String seccion ="";
            String responsable ="";
            String bloque = "";
            String nombreUsuario="";
            String correo="";
            if(cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdSeccion()==null){
                seccion="N/A";
                bloque="N/A";
            }else{
                seccion=cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdSeccion().toString();
                bloque=cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdSeccion().getIdBloque().getNombreBloque();
            }
            if(cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdUsuario()==null){
                responsable="N/A";
            }else{
                responsable=cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdUsuario().toString();
            }
             if(cronogramaMantenimientoActual.getNombreUsuarioReporte()==null){
                nombreUsuario=" ";
            }else{
                nombreUsuario = cronogramaMantenimientoActual.getNombreUsuarioReporte().toUpperCase();
            }
            if(cronogramaMantenimientoActual.getCorreoUsuarioReporte()==null){
                correo=" ";
            }else{
                correo = cronogramaMantenimientoActual.getCorreoUsuarioReporte();
            }
            
            
        String subject = "SU FALLO CON NUMERO DE TICKET #" + cronogramaMantenimientoActual.getIdCronogramaManteDisCon()+ticket;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(cronogramaMantenimientoActual.getIdCronogramaManteDisCon());
        mensaje.append(ticket);
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL FALLO REPORTADO");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDESCRIPCIÓN DEL FALLO: ");
        mensaje.append(cronogramaMantenimientoActual.getDescripcionProblemaDisCon().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(cronogramaMantenimientoActual.getFechaProgMantenimientoDisCon().toLocaleString());
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(responsable.toUpperCase());
        mensaje.append("\nUSUARIO: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nNOMBRE DEL USUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(nombreUsuario);
        mensaje.append("\nSECCIÓN: ");
        mensaje.append(seccion.toUpperCase());
        mensaje.append("\nBLOQUE: ");
        mensaje.append(bloque.toUpperCase());
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL DISPOSITIVO DE CONECTIVIDAD");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nTIPO DE DISPOSITIVO: ");
        mensaje.append(cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdTipo().getNombreTipo().toUpperCase());
        mensaje.append("\nSERVICE TAG O (S/N): ");
        mensaje.append(cronogramaMantenimientoActual.getIdDispositivoConectividad().getCodigoNA().toUpperCase());
        mensaje.append("\nMODELO: ");
        mensaje.append(cronogramaMantenimientoActual.getIdDispositivoConectividad().getModeloNA().toUpperCase());
       
         
        mensaje.append("\n\nPRONTO UNO DE NUESTROS TECNICOS ESTARA ATENDIENDO SU SOLICITUD.");
      
        sendMail(cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdUsuario().getCorreoUsuario()+" "+ correo, subject, mensaje.toString());
    }
    public void sendMailMantenimientoPreventivo() {
       try{
             String seccion ="";
            String responsable ="";
            String bloque = "";
            if(cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdSeccion()==null){
                seccion="N/A";
                bloque="N/A";
            }else{
                seccion=cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdSeccion().toString();
                bloque=cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdSeccion().getIdBloque().getNombreBloque();
            }
            if(cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdUsuario()==null){
                responsable="N/A";
            }else{
                responsable=cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdUsuario().toString();
            }
            
        String subject = "MANTENIMIENTO PREVENTIVO PROGRAMADO CON TICKET #" + cronogramaMantenimientoActual.getIdCronogramaManteDisCon()+"CFIPMP-DC";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(cronogramaMantenimientoActual.getIdCronogramaManteDisCon());
        mensaje.append("CFIPMP-DC");
        mensaje.append("\nEL MANTENIMIENTO PREVENTIVO DESTINADO PARA SU DISPOSITIVO ESTÁ A PUNTO "
                      + "\nDE SER EJECUTADO, ESPERAMOS SU TOTAL COLABORACIÓN, ESTE MANTENIMIENTO"
                      + "\nTIENE UNA DURACIÓN DE ENTRE 1 O 2 HORAS DEPENDIENDO DEL ESTADO DEL DISPOSITIVO. ");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL MANTENIMIENTO PREVENTIVO");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nFECHA Y HORA DEL MANTENIMIENTO: ");
        mensaje.append(cronogramaMantenimientoActual.getFechaProgMantenimientoDisCon().toLocaleString());
        mensaje.append("\nRESPONSABLE DEL DISPOSITIVO: ");
        mensaje.append(responsable.toUpperCase());
        mensaje.append("\nSECCIÓN: ");
        mensaje.append(seccion.toUpperCase());
        mensaje.append("\nBLOQUE: ");
        mensaje.append(bloque.toUpperCase());
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL DISPOSITIVO");
        mensaje.append("\n---------------------------------------------------------------------");
        
        mensaje.append("\nSERVICE TAG O (S/N): ");
        mensaje.append(cronogramaMantenimientoActual.getIdDispositivoConectividad().getCodigoNA().toUpperCase());
        mensaje.append("\nTIPO: ");
        mensaje.append(cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdTipo().getNombreTipo().toUpperCase());
        mensaje.append("\nMODELO: ");
        mensaje.append(cronogramaMantenimientoActual.getIdDispositivoConectividad().getModeloNA().toUpperCase());
        mensaje.append("\nMARCA: ");
        mensaje.append(cronogramaMantenimientoActual.getIdDispositivoConectividad().getMarcaNA().toUpperCase());
      
        sendMail(cronogramaMantenimientoActual.getIdDispositivoConectividad().getIdUsuario().getCorreoUsuario(), subject, mensaje.toString());
         } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }
     
    
    private void recargarLista() {
        listaCronogramaMantenimientosC =null;
        listaCronogramaMantenimientosP =null;
    }

    public CronogramaManteDisConFacade getCronogramaManteDisConFacade() {
        return cronogramaManteDisConFacade;
    }

    // Cronograma Calendario
    public void setCronogramaManteDisConFacade(CronogramaManteDisConFacade cronogramaManteDisConFacade) {
        this.cronogramaManteDisConFacade = cronogramaManteDisConFacade;
    }

       
    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    DefaultScheduleEvent man= new DefaultScheduleEvent();
 
    public void init() {
        eventModel = new DefaultScheduleModel();
        
        //Mantenimientos Correctivos 
        for (Iterator<CronogramaManteDisCon> it = recargarListaMantenimientoC().iterator(); it.hasNext();) {
            CronogramaManteDisCon i = it.next();
            String codigo ="";
            String responsable ="";
            if(i.getIdDispositivoConectividad().getCodigo()==null){
                codigo="N/A";
            }else{
                codigo=i.getIdDispositivoConectividad().getCodigo();
            }
             if(i.getIdDispositivoConectividad().getIdUsuario()==null){
                responsable="N/A";
            }else{
                responsable=i.getIdDispositivoConectividad().getIdUsuario().toString();
            }
          eventModel.addEvent(new DefaultScheduleEvent("/"+i.getIdDispositivoConectividad().getIdTipo().getNombreTipo().toUpperCase()+"\n/"+responsable+"\n/TICKET "+i.getIdCronogramaManteDisCon()+"CFIPMC-DC",i.getFechaProgMantenimientoDisCon(),i.getHoraMantenimientoDisCon(),"MANTENIMIENTO_CORRECTIVO"));
    
        }
        
        //Mantenimientos Preventivos 
         for (Iterator<CronogramaManteDisCon> it = recargarListaMantenimientoP().iterator(); it.hasNext();) {
            CronogramaManteDisCon i = it.next();
            String codigo ="";
            String responsable ="";
            if(i.getIdDispositivoConectividad().getCodigo()==null){
                codigo="N/A";
            }else{
                codigo=i.getIdDispositivoConectividad().getCodigo();
            }
             if(i.getIdDispositivoConectividad().getIdUsuario()==null){
                responsable="N/A";
            }else{
                responsable=i.getIdDispositivoConectividad().getIdUsuario().toString();
            }
            eventModel.addEvent(new DefaultScheduleEvent("/TICKET: \n#"+i.getIdCronogramaManteDisCon()+"CFIPMP-DC\n /SECCIÓN:\n"+i.getIdDispositivoConectividad().getSeccionNA().toUpperCase(),i.getFechaInicioMantenimientoDisCon(),i.getHoraMantenimientoDisCon(),"MANTENIMIENTO PREVENTIVO / RESPONSABLE: "+responsable.toUpperCase()+" / SERVICE TAG O (S/N): "+i.getIdDispositivoConectividad().getCodigoNA().toUpperCase()));
        }
    };
     public List<CronogramaManteDisCon> recargarListaMantenimientoP(){
         recargarLista();
          if (listaCronogramaMantenimientosP  == null) {
            try {
                 listaCronogramaMantenimientosP=getCronogramaManteDisConFacade().consultaCronogramaTipoPendiente(false, new TipoMantenimiento(2));
            } catch (Exception e) {
                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            }
        }
        return listaCronogramaMantenimientosP;
     }
      public List<CronogramaManteDisCon> recargarListaMantenimientoC(){
        listaCronogramaMantenimientosC = new ArrayList<>();
        listaCronogramaMantenimientosC=getCronogramaManteDisConFacade().consultaCronogramaTipoPendiente(false, new TipoMantenimiento(1));
        return listaCronogramaMantenimientosC;
     }
      public ScheduleModel getEventModel() {
        return eventModel;
    }
    
    public String prepareViewCalendario(){
        recargarListaMantenimientoP();
        init();
        return "/Admin/moduloConfigMantenimiento/adminCalendarioCronogramaDC";
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
   
   
}
