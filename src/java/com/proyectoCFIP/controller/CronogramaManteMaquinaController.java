/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.CronogramaMantenimientoMaquina;
import com.proyectoCFIP.entities.DiagnosticoMaquina;
import com.proyectoCFIP.entities.EstadoCronograma;
import com.proyectoCFIP.entities.MaquinaConfecciones;
import com.proyectoCFIP.entities.TipoMantenimiento;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.CronogramaMantenimientoMaquinaFacade;
import com.proyectoCFIP.sessions.DiagnosticoMaquinaFacade;
import com.proyectoCFIP.sessions.EmailSessionBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.primefaces.context.RequestContext;
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
public class CronogramaManteMaquinaController {

    @EJB
    private CronogramaMantenimientoMaquinaFacade cronogramaMaquinasConfeccionesFacade;
    private CronogramaMantenimientoMaquina cronogramaMaquinasConfeccionesActual;
    private List<CronogramaMantenimientoMaquina> cronogramaMaquinasConfeccionesList;
    private Usuario usuarioActual;
    private MaquinaConfecciones maquinaConfeccionesActual;
    @EJB
    private DiagnosticoMaquinaFacade diagnosticoMaquinaFacade;
    private DiagnosticoMaquina diagnosticoMaquinaActual;
    private List<DiagnosticoMaquina> diagnosticoMaquinaList;
    private String ticket;
    @EJB
    EmailSessionBean emailBean;
    
    public CronogramaManteMaquinaController() {
    }

    public CronogramaMantenimientoMaquinaFacade getCronogramaMaquinasConfeccionesFacade() {
        return cronogramaMaquinasConfeccionesFacade;
    }

    public void setCronogramaMaquinasConfeccionesFacade(CronogramaMantenimientoMaquinaFacade cronogramaMaquinasConfeccionesFacade) {
        this.cronogramaMaquinasConfeccionesFacade = cronogramaMaquinasConfeccionesFacade;
    }

    public CronogramaMantenimientoMaquina getCronogramaMaquinasConfeccionesActual() {
        return cronogramaMaquinasConfeccionesActual;
    }

    public void setCronogramaMaquinasConfeccionesActual(CronogramaMantenimientoMaquina cronogramaMaquinasConfecciones) {
        this.cronogramaMaquinasConfeccionesActual = cronogramaMaquinasConfecciones;
    }

    public List<CronogramaMantenimientoMaquina> getCronogramaMaquinasConfeccionesList() {
        return cronogramaMaquinasConfeccionesList = getCronogramaMaquinasConfeccionesFacade().findAll();
    }
    public List<CronogramaMantenimientoMaquina> getMantenimientosPendientesPrev() {
        return cronogramaMaquinasConfeccionesList = getCronogramaMaquinasConfeccionesFacade().consultaTipoMantenimientoEstado(new TipoMantenimiento(2,"preventivo"), new EstadoCronograma(2));
    }
    public List<CronogramaMantenimientoMaquina> getMantenimientosPendientesCorre() {
        return cronogramaMaquinasConfeccionesList = getCronogramaMaquinasConfeccionesFacade().consultaTipoMantenimientoEstado(new TipoMantenimiento(1,"correctivo"), new EstadoCronograma(2));
    }
    
    public List<CronogramaMantenimientoMaquina> getMantenimientosPreventivosTotal(){
        cronogramaMaquinasConfeccionesList = new ArrayList<>();
        return cronogramaMaquinasConfeccionesList = getCronogramaMaquinasConfeccionesFacade().consultaTipoMantenimientoTotalPrev(new TipoMantenimiento(2, "preventivo"),new TipoMantenimiento(3, "preventivo y correctivo") );
    }
    public List<CronogramaMantenimientoMaquina> getMantenimientosCorrectivosTotal(){
        cronogramaMaquinasConfeccionesList = new ArrayList<>();
        return cronogramaMaquinasConfeccionesList = getCronogramaMaquinasConfeccionesFacade().consultaTipoMantenimientoTotal(new TipoMantenimiento(1, "correctivo"));
    }
    public void setCronogramaMaquinasConfeccionesList(List<CronogramaMantenimientoMaquina> cronogramaMaquinasConfeccionesList) {
        this.cronogramaMaquinasConfeccionesList = cronogramaMaquinasConfeccionesList;
    }
    
    public List<CronogramaMantenimientoMaquina> getListaMantenimientosMaquina(){
        return cronogramaMaquinasConfeccionesList= getCronogramaMaquinasConfeccionesFacade().consultaMaquina(new EstadoCronograma(2, "sin cerrar"), maquinaConfeccionesActual);
    }
    public List<DiagnosticoMaquina> getListaDiagnosticoTicket() {
        return getDiagnosticoMaquinaFacade().consultaTicket(cronogramaMaquinasConfeccionesActual);
    }
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public MaquinaConfecciones getMaquinaConfeccionesActual() {
        return maquinaConfeccionesActual;
    }

    public void setMaquinaConfeccionesActual(MaquinaConfecciones maquinaConfeccionesActual) {
        this.maquinaConfeccionesActual = maquinaConfeccionesActual;
    }
    public void prepareViewDiagnostico(ActionEvent event) {
        cronogramaMaquinasConfeccionesActual = new CronogramaMantenimientoMaquina();
        cronogramaMaquinasConfeccionesActual = (CronogramaMantenimientoMaquina) event.getComponent().getAttributes().get("cronograma");
        cronogramaMaquinasConfeccionesList = new ArrayList<>();
        //listaActividadAprendizaje=actividadActual.getActividadAprendizajeList(); 
    }
    
    public String addMantenimientoPreventivo(){
         try {
            cronogramaMaquinasConfeccionesActual.setIdMaquinaConfecciones(maquinaConfeccionesActual);
            cronogramaMaquinasConfeccionesActual.setFechaReporte(new Date());
            cronogramaMaquinasConfeccionesActual.setEstado(false);
            cronogramaMaquinasConfeccionesActual.setIdEstadoCronograma(new EstadoCronograma(2));
            cronogramaMaquinasConfeccionesActual.setIdTipoMantenimiento(new TipoMantenimiento(2, "preventivo"));
            getCronogramaMaquinasConfeccionesFacade().create(cronogramaMaquinasConfeccionesActual);
            //ticket="CFIPMC-C";
            //sendMailRegistroTec();
            //sendMailRegistroUser();
            
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mantenimiento Preventivo Programado", "Se programo un mantenimiento preventivo para el dia "+cronogramaMaquinasConfeccionesActual.getFechaInicioMantenimiento());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "adminListProgramarMantenimiento";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String addReporte(){
         try {
            cronogramaMaquinasConfeccionesActual.setFechaReporte(new Date());
            cronogramaMaquinasConfeccionesActual.setFechaInicioMantenimiento(new Date());
            cronogramaMaquinasConfeccionesActual.setHoraMantenimiento(new Date());
            cronogramaMaquinasConfeccionesActual.setEstado(false);
            cronogramaMaquinasConfeccionesActual.setIdEstadoCronograma(new EstadoCronograma(2));
            cronogramaMaquinasConfeccionesActual.setIdTipoMantenimiento(new TipoMantenimiento(1, "correctivo"));
            cronogramaMaquinasConfeccionesActual.setIdUsuario(usuarioActual);
            getCronogramaMaquinasConfeccionesFacade().create(cronogramaMaquinasConfeccionesActual);
            //ticket="CFIPMC-C";
            sendMailRegistroTec();
            sendMailRegistroUser();
            return "paginaPrincipalMaquinasConfecciones";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    
        public String reprogramarMantenimiento(){
         try {
            getCronogramaMaquinasConfeccionesFacade().edit(cronogramaMaquinasConfeccionesActual);
            getMantenimientosPendientesPrev();
            return "adminListMantenimientoPreventivo";
          
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

    
    private ScheduleModel eventModel;
    private ScheduleModel eventModel2;
    private ScheduleEvent event = new DefaultScheduleEvent();
    DefaultScheduleEvent man= new DefaultScheduleEvent();
 
    public void init() {
        eventModel = new DefaultScheduleModel();
        eventModel2 = new DefaultScheduleModel();
        //Mantenimiento Correctivo
        for (Iterator<CronogramaMantenimientoMaquina> it = recargarListaMantenimientosC().iterator(); it.hasNext();) {
            CronogramaMantenimientoMaquina i = it.next();
            String horaReporte;
            DateFormat fecha1 = new SimpleDateFormat("hh:mma");
            horaReporte = fecha1.format(i.getHoraMantenimiento());
            eventModel.addEvent(new DefaultScheduleEvent(" - "+i.getIdUsuario().toString()+"\n - #TICKET "+i.getIdCronogramaMantenimientoMaquina()+"CFIPMC-CON\n - COD. MAQUINA:"+i.getIdMaquinaConfecciones().getCodigo().toUpperCase()+"\n - HORA: "+horaReporte,i.getFechaReporte(),i.getHoraMantenimiento(),"Modulo: "+i.getIdMaquinaConfecciones().getIdModulo().getNombre().toUpperCase()+" // "+"MANTENIMIENTO_CORRECTIVO"));
        }
         for (Iterator<CronogramaMantenimientoMaquina> it = recargarListaMantenimientosP().iterator(); it.hasNext();) {
            CronogramaMantenimientoMaquina i = it.next();
            String horaReporte;
            DateFormat fecha1 = new SimpleDateFormat("hh:mma");
            horaReporte = fecha1.format(i.getHoraMantenimiento());
            eventModel.addEvent(new DefaultScheduleEvent("- #TICKET "+i.getIdCronogramaMantenimientoMaquina()+"CFIPMP-CON\n - COD. MAQUINA:"+i.getIdMaquinaConfecciones().getCodigo().toUpperCase()+"\n - HORA: "+horaReporte,i.getFechaInicioMantenimiento(),i.getHoraMantenimiento(),"Modulo: "+i.getIdMaquinaConfecciones().getIdModulo().getNombre().toUpperCase()+" // "+"MANTENIMIENTO_PREVENTIVO"));
        }
    };
    public List<CronogramaMantenimientoMaquina> recargarListaMantenimientosC(){
        cronogramaMaquinasConfeccionesList = new ArrayList<>();
        cronogramaMaquinasConfeccionesList=  getCronogramaMaquinasConfeccionesFacade().consultaTipoMantenimientoEstado(new TipoMantenimiento(1, "correctivo"),new EstadoCronograma(2));
        return cronogramaMaquinasConfeccionesList;
    }
    public List<CronogramaMantenimientoMaquina> recargarListaMantenimientosP(){
        cronogramaMaquinasConfeccionesList = new ArrayList<>();
        cronogramaMaquinasConfeccionesList=   getCronogramaMaquinasConfeccionesFacade().consultaTipoMantenimientoEstado(new TipoMantenimiento(2, "preventivo"),new EstadoCronograma(2));
        return cronogramaMaquinasConfeccionesList;
    }
   
  
    public ScheduleModel getEventModel() {
        return eventModel;
    }
     public ScheduleModel getEventModel2() {
        return eventModel2;
    }
    public String prepareViewMantenimientos(){
        return "/Admin/moduloConfigMantenimiento/mantenimientosAnuales/adminViewMantenimientosAnualesCom";
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
    
    public String prepareViewCalendario(){
        init();
        return "/MaquinasConfecciones/cronogramaMaquina/adminViewCronograma";
     }
    
    public String prepareCreateReporte(){
        cronogramaMaquinasConfeccionesActual = new CronogramaMantenimientoMaquina();
        return "userCrearIncidenteMaquina";
    }
    
    public void prepareCreateMantePreve(){
        cronogramaMaquinasConfeccionesActual = new CronogramaMantenimientoMaquina();
    }
     public void prepareViewMantenimientoProg(){
        cronogramaMaquinasConfeccionesList = null;
    }
    public String prepareListPreventivosPendientes(){
        return "adminListMantenimientoPreventivo";
    }
    public String prepareListCorrectivosPendientes(){
        return "adminListMantenimientoCorrectivo";
    }
    
    public String prepareListProgramarMantenimiento(){
        return "adminListProgramarMantenimiento";
    }
     public String prepareListEstadoTicket(){
        return "userViewEstadoTicketMaquina";
    }
    public String prepareListDiagnosticos(){
        return "adminListDiagnosticosMaquina";
    }
     
    public String prepareListMantePreventivosAnual(){
        return "/MaquinasConfecciones/cronogramaMaquina/adminListAnualMantePreventivos";
    }
      public String prepareListManteCorrectivosAnual(){
        return "/MaquinasConfecciones/cronogramaMaquina/adminListAnualManteCorrectivos";
    }
     
    // DIAGNOSTICO MANTENIMIENTOS
    public DiagnosticoMaquinaFacade getDiagnosticoMaquinaFacade() {
        return diagnosticoMaquinaFacade;
    }

    public void setDiagnosticoMaquinaFacade(DiagnosticoMaquinaFacade diagnosticoMaquinaFacade) {
        this.diagnosticoMaquinaFacade = diagnosticoMaquinaFacade;
    }

    public DiagnosticoMaquina getDiagnosticoMaquinaActual() {
        return diagnosticoMaquinaActual;
    }

    public void setDiagnosticoMaquinaActual(DiagnosticoMaquina diagnosticoMaquinaActual) {
        this.diagnosticoMaquinaActual = diagnosticoMaquinaActual;
    }

    public List<DiagnosticoMaquina> getDiagnosticoMaquinaList() {
        return diagnosticoMaquinaList = getDiagnosticoMaquinaFacade().findAll();
    }

    public void setDiagnosticoMaquinaList(List<DiagnosticoMaquina> diagnosticoMaquinaList) {
        this.diagnosticoMaquinaList = diagnosticoMaquinaList;
    }
    
    public String addDiagnosticoManteCorrectivo(){
        diagnosticoMaquinaActual.setIdCronogramaMantenimientoMaquina(cronogramaMaquinasConfeccionesActual);
        getDiagnosticoMaquinaFacade().create(diagnosticoMaquinaActual);
        cronogramaMaquinasConfeccionesActual.setEstado(true);
        cronogramaMaquinasConfeccionesActual.setIdEstadoCronograma(new EstadoCronograma(3));
        updateCronograma();
        cronogramaMaquinasConfeccionesList= null;
        getMantenimientosPendientesCorre();
        //sendMailSolucionUser();
        return "adminListMantenimientoCorrectivo";
    }
    
    
    
     public String addDiagnosticoMantePreventivo(){
        if(diagnosticoMaquinaActual.getMantenimientoCp()== true){
            cronogramaMaquinasConfeccionesActual.setIdTipoMantenimiento(new TipoMantenimiento(3,"preventivo y correctivo"));
            cronogramaMaquinasConfeccionesFacade.edit(cronogramaMaquinasConfeccionesActual);
         }
        
        diagnosticoMaquinaActual.setIdCronogramaMantenimientoMaquina(cronogramaMaquinasConfeccionesActual);
        getDiagnosticoMaquinaFacade().create(diagnosticoMaquinaActual);
        cronogramaMaquinasConfeccionesActual.setEstado(true);
        cronogramaMaquinasConfeccionesActual.setIdEstadoCronograma(new EstadoCronograma(3));
        updateCronograma();
        cronogramaMaquinasConfeccionesList= null;
        getMantenimientosPendientesPrev();
        return "adminListMantenimientoPreventivo";
    }
    
    public void updateCronograma() {
        try {
            getCronogramaMaquinasConfeccionesFacade().edit(cronogramaMaquinasConfeccionesActual);
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }
    
    
    public String viewValoracion(){
        if(cronogramaMaquinasConfeccionesActual.getIdTipoMantenimiento().equals(new TipoMantenimiento(1, "correctivo"))){
           ticket="CFIPMC-M"; 
        }else{
             ticket="CFIPMP-M"; 
        }
        
        if(cronogramaMaquinasConfeccionesActual.getIdEstadoCronograma().equals(new EstadoCronograma(3))){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Valora el servicio prestado", "Por favor valora el servicio prestado para cerrar correctamente el ticket.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "userCrearValoracionTicketMaquina"; 
        }else if(cronogramaMaquinasConfeccionesActual.getIdEstadoCronograma().equals(new EstadoCronograma(1))){
          
            return "userViewTicketMaquina"; 
            
        }else{
            addErrorMessage("Ticket no diagnosticado"  , "Para valorar el servicio debes esperar a que nuestros técnicos diagnostiquen el ticket");
            return null;
        }
       
    }
    
    
    public String crearValoracionTicket(){
      try {
            cronogramaMaquinasConfeccionesActual.setIdEstadoCronograma(new EstadoCronograma(1));
            getCronogramaMaquinasConfeccionesFacade().edit(cronogramaMaquinasConfeccionesActual);
            addSuccessMessage("Valoración guardada", "El ticket ha sido cerrado correctamente");
            return "userViewTicketMaquina";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "userViewEstadoTicketMaquina";
        }  
    }
    public String viewDiagnosticoPrev(){
        ticket="CFIPMP-M";
        return "adminViewTicketMaquina";
    }
    public String viewDiagnosticoCorre(){
        ticket="CFIPMC-C";
        return "adminViewTicketMaquina";
    }
    public void prepareCreateDiagnostico(){
        diagnosticoMaquinaActual = new DiagnosticoMaquina();
    }
    public String list(){
        return "";
    }

    public String getTicket() {
        return ticket;
    }
    
      private void sendMail(String to, String subject, String body) {
        try {
            emailBean.sendMail(to, subject, body);
            JsfUtil.addSuccessMessage("Mensaje Enviado Exitosamente");
        } catch (NamingException | MessagingException ex) {
            JsfUtil.addErrorMessage("Error sending message " + ex.getClass().getName());
        }
    }
    
    //Reportes Siesa
    
    private void sendMailRegistroTec() {
      
        
        String subject = "FALLO REPORTADO DE MAQUINA DE CONFECCIONES"+", FALLO REPORTADO TICKET #"+ cronogramaMaquinasConfeccionesActual.getIdCronogramaMantenimientoMaquina()+"CFIPMC-CON" ;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(cronogramaMaquinasConfeccionesActual.getIdCronogramaMantenimientoMaquina());
        mensaje.append("CFIPMC-CON");
        mensaje.append("\nUSUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nCODIGO DE LA MAQUINA: ");
        mensaje.append(cronogramaMaquinasConfeccionesActual.getIdMaquinaConfecciones().getCodigo().toUpperCase());
        mensaje.append("\nSERIAL DE LA MAQUINA: ");
        mensaje.append(cronogramaMaquinasConfeccionesActual.getIdMaquinaConfecciones().getSerial().toUpperCase());
        mensaje.append("\nTIPO DE MAQUINA: ");
        mensaje.append(cronogramaMaquinasConfeccionesActual.getIdMaquinaConfecciones().getIdTipoMaquinaConfecciones().getNombre().toUpperCase());
        mensaje.append("\nMODULO: ");
        mensaje.append(cronogramaMaquinasConfeccionesActual.getIdMaquinaConfecciones().getIdModulo().getNombre().toUpperCase());
        mensaje.append("\nDESCRIPCIÓN DEL FALLO: ");
        mensaje.append(cronogramaMaquinasConfeccionesActual.getDescripcionProblema().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(cronogramaMaquinasConfeccionesActual.getFechaReporte().toLocaleString());
       
        sendMail("auxsistemas2@cfiprovidencia.com "+" "+" mto.confecciones@cfiprovidencia.com ", subject, mensaje.toString());  
    }
     
    private void sendMailRegistroUser() {
    
        String subject = "SU FALLO CON NUMERO DE TICKET #" +cronogramaMaquinasConfeccionesActual.getIdCronogramaMantenimientoMaquina()+"CFIPMC-CON";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(cronogramaMaquinasConfeccionesActual.getIdCronogramaMantenimientoMaquina());
        mensaje.append("CFIPMC-CON");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL FALLO REPORTADO");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nUSUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nCODIGO MAQUINA: ");
        mensaje.append(cronogramaMaquinasConfeccionesActual.getIdMaquinaConfecciones().getCodigo().toUpperCase());
        mensaje.append("\nTIPO DE MAQUINA: ");
        mensaje.append(cronogramaMaquinasConfeccionesActual.getIdMaquinaConfecciones().getIdTipoMaquinaConfecciones().getNombre().toUpperCase());
        mensaje.append("\nMODULO: ");
        mensaje.append(cronogramaMaquinasConfeccionesActual.getIdMaquinaConfecciones().getIdModulo().getNombre().toUpperCase());
        mensaje.append("\nDESCRIPCIÓN DEL FALLO: ");
        mensaje.append(cronogramaMaquinasConfeccionesActual.getDescripcionProblema().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(cronogramaMaquinasConfeccionesActual.getFechaReporte().toLocaleString());
       
        mensaje.append("\n\nPRONTO ESTAREMOS ATENDIENDO SU SOLICITUD.");
        sendMail(cronogramaMaquinasConfeccionesActual.getIdUsuario().getCorreoUsuario(), subject, mensaje.toString());
    }
    
    /*private void sendMailDiagnostico() {
        String codigoSiesa ="";
        String descripcionSiesa ="";
        if(diagnosticoReporteSiesaActual.getCodigoTicketSiesa()==null){
            codigoSiesa = "Ninguno";
        }else{
            codigoSiesa = diagnosticoReporteSiesaActual.getCodigoTicketSiesa();
        }
        if(diagnosticoReporteSiesaActual.getDescripcionTicketSiesa()==null){
            descripcionSiesa = "Ninguno";
        }else{
            descripcionSiesa = diagnosticoReporteSiesaActual.getDescripcionTicketSiesa();
        }
        
        SimpleDateFormat fecha = new SimpleDateFormat("MMM/dd/yyyy");
        String subject = "DIAGNOSTICO DEL TICKET #" + diagnosticoReporteSiesaActual.getIdReporteSiesa().getIdReporteSiesa()+"CFIPERP";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CODIGO DEL DIAGNOSTICO ");
        mensaje.append(diagnosticoReporteSiesaActual.getIdReporteSiesa().getIdReporteSiesa());
        mensaje.append("CFIPDIAG-E");
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL REPORTE ");
        mensaje.append("\nTIPO DE ERROR: ");
        mensaje.append(diagnosticoReporteSiesaActual.getIdReporteSiesa().getIdTipoError().getNombreError().toUpperCase());
        mensaje.append("\nDESCRIPCIÓN DE ERROR: ");
        mensaje.append(diagnosticoReporteSiesaActual.getIdReporteSiesa().getDescripcionReporte().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(diagnosticoReporteSiesaActual.getIdReporteSiesa().getFechaReporte().toLocaleString());
        mensaje.append("\nUSUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(diagnosticoReporteSiesaActual.getIdReporteSiesa().getIdUsuario().toString().toUpperCase());
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL DIAGNOSTICO:");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nREVISION: ");
        mensaje.append(diagnosticoReporteSiesaActual.getRevision().toUpperCase());
        mensaje.append("\nFECHA DE LA REVISION: ");
        mensaje.append(fecha.format(diagnosticoReporteSiesaActual.getFechaRevision()));
        mensaje.append("\nDIAGNOSTICO: ");
        mensaje.append(diagnosticoReporteSiesaActual.getDiagnostico().toUpperCase());
        mensaje.append("\nFECHA QUE SE CERRO EL TICKET: ");
        mensaje.append(fecha.format(diagnosticoReporteSiesaActual.getFechaDiagnostico()));
        mensaje.append("\nCODIGO DE SIESA IT: ");
        mensaje.append(codigoSiesa.toUpperCase());
        mensaje.append("\nDIAGNOSTICO DE SIESA IT: ");
        mensaje.append(descripcionSiesa.toUpperCase());
        
        mensaje.append("\nTECNICO QUE ATENDIO EL CASO: ");
        mensaje.append(diagnosticoReporteSiesaActual.getIdUsuario().toString());
        
        
        sendMail(diagnosticoReporteSiesaActual.getIdReporteSiesa().getIdUsuario().getCorreoUsuario() +" "+" lccabal@misena.edu.co "+" "+" sistemas@cfiprovidencia.com "+" "+" auxsistemas@cfiprovidencia.com ", subject, mensaje.toString());
    }*/
    private void sendMailSolucionUser() {
           
        String subject = "DIAGNOSTICO DEL TICKET #" + diagnosticoMaquinaActual.getIdCronogramaMantenimientoMaquina().getIdCronogramaMantenimientoMaquina()+"CFIPMC-CON";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CODIGO DEL DIAGNOSTICO ");
        mensaje.append(diagnosticoMaquinaActual.getIdDiagnosticoMaquina());
        mensaje.append("CFIPDIAG-CON");
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL MANTENIMIENTO CORRECTIVO");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDESCRIPCION DEL FALLO: ");
        mensaje.append(diagnosticoMaquinaActual.getIdCronogramaMantenimientoMaquina().getDescripcionProblema().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(diagnosticoMaquinaActual.getIdCronogramaMantenimientoMaquina().getFechaInicioMantenimiento().toLocaleString());
        mensaje.append("\nMODULO: ");
        mensaje.append(diagnosticoMaquinaActual.getIdCronogramaMantenimientoMaquina().getIdMaquinaConfecciones().getIdModulo());
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL DIAGNOSTICO:");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nREVISION: ");
        mensaje.append(diagnosticoMaquinaActual.getRevision().toUpperCase());
        mensaje.append("\nFECHA DE LA REVISION: ");
        mensaje.append(diagnosticoMaquinaActual.getFechaRevision().toLocaleString());
        mensaje.append("\nDIAGNOISTICO: ");
        mensaje.append(diagnosticoMaquinaActual.getDiagnostico().toUpperCase());
        mensaje.append("\nENTREGA DEL EQUIPO: ");
        mensaje.append(diagnosticoMaquinaActual.getFechaEntrega().toLocaleString());
        
        
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DE LA MAQUINA:");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nCODIGO MAQUINA: ");
        mensaje.append(diagnosticoMaquinaActual.getIdCronogramaMantenimientoMaquina().getIdMaquinaConfecciones().getCodigo().toUpperCase());
        mensaje.append("\nSERIAL: ");
        mensaje.append(diagnosticoMaquinaActual.getIdCronogramaMantenimientoMaquina().getIdMaquinaConfecciones().getSerial().toUpperCase());
        mensaje.append("\nMODELO: ");
        mensaje.append(diagnosticoMaquinaActual.getIdCronogramaMantenimientoMaquina().getIdMaquinaConfecciones().getIdMarca().getNombreMarca().toUpperCase());
 
        
       
    
   
        mensaje.append("\n\nSU INCIDENTE FUE CORREGIDO SATISFACTORIAMENTE");
        sendMail(usuarioActual.getCorreoUsuario()+" "+"auxsistemas2@cfiprovidencia.com"+" "+"sistemas@cfiprovidencia.com"+" "+"auxsistemas@cfiprovidencia.com", subject, mensaje.toString());
        //sendMail(diagnosticoMantenimientoActual.getIdCronogramaMantenimientos().getIdComputador().getIdUsuario().getCorreoUsuario()+" "+"auxsistemas2@cfiprovidencia.com"+" "+correoJornada, subject, mensaje.toString());

    }
}
