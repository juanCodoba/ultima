/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.Area;
import com.proyectoCFIP.entities.CronogramaManteDispositivo;
import com.proyectoCFIP.entities.DiagnosticoManteDispositivo;
import com.proyectoCFIP.entities.OtroDispositivo;
import com.proyectoCFIP.entities.TipoMantenimiento;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.CronogramaManteDispositivoFacade;
import com.proyectoCFIP.sessions.DiagnosticoManteDispositivoFacade;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.OtroDispositivoFacade;
import java.io.Serializable;
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
public class CronogramaControllerDis implements Serializable{
    
    @EJB
    private CronogramaManteDispositivoFacade cronogramaManteDispositivoFacade;
    @EJB
    private OtroDispositivoFacade otroDispositivoFacade;
    @EJB
    private DiagnosticoManteDispositivoFacade diagnosticoManteDispositivoFacade;
     @EJB
    EmailSessionBean emailBean;
    private DiagnosticoManteDispositivo diagnosticoManteDispositivoActual;
    private CronogramaManteDispositivo cronogramaManteDisActual;
    private List<CronogramaManteDispositivo> listaCronogramaManteDispositivo;
    private OtroDispositivo OtroDispositivoActual;
    private List<CronogramaManteDispositivo> listaCronogramaMantenimientos = null;
    private List<CronogramaManteDispositivo> listaCronogramaMantenimientosC = null;
    private List<CronogramaManteDispositivo> listaCronogramaMantenimientosP = null;
    private TipoMantenimiento tipoMantenimientoActual;
    private Usuario usuarioActual;
    private List<DiagnosticoManteDispositivo> listaDiagnosticoDis = null;
    private String cambioRepuesto ="";
    private String ticket="";

    public CronogramaControllerDis() {
    }
    
    public boolean isMantenimientoPreventivo(){
        return cronogramaManteDisActual.getIdTipoMantenimiento()== null? false : cronogramaManteDisActual.getIdTipoMantenimiento().getIdTipoMantenimiento()== (short) 2;
    }
    
    public boolean isMantenimientoCorrectivoPreventivo(){
        return cronogramaManteDisActual.getIdTipoMantenimiento()== null? false : cronogramaManteDisActual.getIdTipoMantenimiento().getIdTipoMantenimiento()== (short) 3;
    }

    public CronogramaManteDispositivoFacade getCronogramaManteDispositivoFacade() {
        return cronogramaManteDispositivoFacade;
    }

    public void setCronogramaManteDispositivoFacade(CronogramaManteDispositivoFacade cronogramaManteDispositivoFacade) {
        this.cronogramaManteDispositivoFacade = cronogramaManteDispositivoFacade;
    }

    public CronogramaManteDispositivo getCronogramaManteDisActual() {
        return cronogramaManteDisActual;
    }

    public void setCronogramaManteDisActual(CronogramaManteDispositivo cronogramaManteDisActual) {
        this.cronogramaManteDisActual = cronogramaManteDisActual;
    }

    public List<CronogramaManteDispositivo> getListaCronogramaManteDispositivo() {
        return getCronogramaManteDispositivoFacade().findAll();
    }

    public void setListaCronogramaManteDispositivo(List<CronogramaManteDispositivo> listaCronogramaManteDispositivo) {
        this.listaCronogramaManteDispositivo = listaCronogramaManteDispositivo;
    }

    public OtroDispositivo getOtroDispositivoActual() {
        return OtroDispositivoActual;
    }

    public void setOtroDispositivoActual(OtroDispositivo OtroDispositivoActual) {
        this.OtroDispositivoActual = OtroDispositivoActual;
    }

    public OtroDispositivoFacade getOtroDispositivoFacade() {
        return otroDispositivoFacade;
    }

    public void setOtroDispositivoFacade(OtroDispositivoFacade otroDispositivoFacade) {
        this.otroDispositivoFacade = otroDispositivoFacade;
    }
    public List<CronogramaManteDispositivo> getListaMantenimientosTotalTipo(){
        listaCronogramaMantenimientos = new ArrayList<>();
        return listaCronogramaMantenimientos = getCronogramaManteDispositivoFacade().consultaTotalTipoPrev();
    }
    public List<CronogramaManteDispositivo> getListaMantenimientosTotalTipoCorre(){
        listaCronogramaMantenimientos = new ArrayList<>();
        return listaCronogramaMantenimientos = getCronogramaManteDispositivoFacade().consultaTotalTipoCorre();
    }
    private void recargarLista() {
        listaCronogramaManteDispositivo= null;
        listaCronogramaMantenimientosP=null;
        listaCronogramaMantenimientosC=null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    
    
    
    //Diagnostico mantenimiento
    
    public String prepareViewSolucionIncidentesDis(){
        return "adminViewSolucionIncidenteDis";
    }
    public String prepareViewMantenimientosCDis() {
        tipoMantenimientoActual = new TipoMantenimiento(1);
        return "/Admin/moduloConfigMantenimiento/adminViewIncidentesPendientesDis";
    }
    public String prepareViewMantenimientosPDis(){
        tipoMantenimientoActual = new TipoMantenimiento(2);
        return "/Admin/moduloConfigMantenimiento/adminViewMantenimientosPreventivosDis";
    }
    
    public String prepareViewMantenimientosPyCDis(){
        tipoMantenimientoActual = new TipoMantenimiento(3);
        return "/Admin/moduloConfigMantenimiento/adminViewMantenimientosCyPDis";
    }

    public DiagnosticoManteDispositivoFacade getDiagnosticoManteDispositivoFacade() {
        return diagnosticoManteDispositivoFacade;
    }

    public void setDiagnosticoManteDispositivoFacade(DiagnosticoManteDispositivoFacade diagnosticoManteDispositivoFacade) {
        this.diagnosticoManteDispositivoFacade = diagnosticoManteDispositivoFacade;
    }

    public DiagnosticoManteDispositivo getDiagnosticoManteDispositivoActual() {
        return diagnosticoManteDispositivoActual;
    }

    public void setDiagnosticoManteDispositivoActual(DiagnosticoManteDispositivo diagnosticoManteDispositivoActual) {
        this.diagnosticoManteDispositivoActual = diagnosticoManteDispositivoActual;
    }

    public TipoMantenimiento getTipoManteniminetoActual() {
        return tipoMantenimientoActual;
    }

    public void setTipoManteniminetoActual(TipoMantenimiento tipoManteniminetoActual) {
        this.tipoMantenimientoActual = tipoManteniminetoActual;
    }
    public void updateCronogramaDis() {
        try {
            getCronogramaManteDispositivoFacade().edit(cronogramaManteDisActual);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }
    public List<DiagnosticoManteDispositivo> getListaDiagnosticoTicket() {
        return getDiagnosticoManteDispositivoFacade().consultaTicket(cronogramaManteDisActual);
    }
    
    public List<OtroDispositivo> getListaOtroDispositivoSelectOne() {
        return getOtroDispositivoFacade().consultaUsuario(usuarioActual);
    }   
    
    public List<OtroDispositivo> getListaOtroDispositivoDocenteSelectOne() {
        return getOtroDispositivoFacade().consultaArea(new Area(1 , "Academica"));
    }
    
    public List<CronogramaManteDispositivo> getListaMantenimientosP(){
        listaCronogramaMantenimientos = new ArrayList<>();
        return listaCronogramaMantenimientos = getCronogramaManteDispositivoFacade().consultaCronogramaTipoPendiente(false, new TipoMantenimiento(2));
    }
    public List<CronogramaManteDispositivo> getListaMantenimientosC(){
        listaCronogramaMantenimientos = new ArrayList<>();
        return listaCronogramaMantenimientos = getCronogramaManteDispositivoFacade().consultaCronogramaTipoPendiente(false, new TipoMantenimiento(1));
    }
    public List<DiagnosticoManteDispositivo> getListaDiagnosticoDis() {
        listaDiagnosticoDis = getDiagnosticoManteDispositivoFacade().consultaDiagnosticoDis(OtroDispositivoActual);        
        return listaDiagnosticoDis;
    }
    
    public List<DiagnosticoManteDispositivo> getListaDiagnosticoTodoDis() {
        listaDiagnosticoDis = getDiagnosticoManteDispositivoFacade().findAll();        
        return listaDiagnosticoDis;
    }
    
    public String prepareCreateMantenimientoCyP(){
        cronogramaManteDisActual = new CronogramaManteDispositivo();
        return "adminCrearMantePrevDis";
    }
     public String prepareViewPendientesDis(){
        return "adminViewPendientesMantenimientoDis";
    }
    
    public String addDiagnostico(){
        if(diagnosticoManteDispositivoActual.getMantenimientoCorrectivo() == true){
            cronogramaManteDisActual.setIdTipoMantenimiento(new TipoMantenimiento(3,"preventivo y correctivo"));
            cronogramaManteDispositivoFacade.edit(cronogramaManteDisActual);
            OtroDispositivoActual.setEstadoProgramadoDis(false);
            otroDispositivoFacade.edit(OtroDispositivoActual);
         }
        diagnosticoManteDispositivoActual.setIdCronogramaManteDispositivo(cronogramaManteDisActual);
        diagnosticoManteDispositivoActual.setIdUsuario(usuarioActual);
        getDiagnosticoManteDispositivoFacade().create(diagnosticoManteDispositivoActual);
        cronogramaManteDisActual.setEstadoMantenimientoDis(true);
        cronogramaManteDispositivoFacade.edit(cronogramaManteDisActual);
        OtroDispositivoActual.setEstadoProgramadoDis(false);
        diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().setEstadoProgramadoDis(false);
        getOtroDispositivoFacade().edit(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo());
        updateCronogramaDis();
        recargarLista();
        ticket="CFIPMP-DIS";
        sendMailDiagPrevUser();
        addSuccessMessage("Diagnóstico guardado exitosamente", "El diagnóstico del ticket "+diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdCronogramaManteDispositivo()+ticket+" fue guardado con el codigo "+diagnosticoManteDispositivoActual.getIdDiagnosticoManteDispositivo()+"CFIPDIAG-DIS");
        return "adminViewSolucionIncidenteDis";
    }
    public String addDiagnosticoFallo(){
        diagnosticoManteDispositivoActual.setIdCronogramaManteDispositivo(cronogramaManteDisActual);
        diagnosticoManteDispositivoActual.setIdUsuario(usuarioActual);
        getDiagnosticoManteDispositivoFacade().create(diagnosticoManteDispositivoActual);
        cronogramaManteDisActual.setEstadoMantenimientoDis(true);
        updateCronogramaDis();
        recargarLista();
        sendMailSolucionUserDis();
        return "adminViewSolucionIncidenteDis";
    }
    public String prepareSolucionCorrectivo() {
        diagnosticoManteDispositivoActual = new DiagnosticoManteDispositivo();
        return "/Admin/moduloConfigMantenimiento/adminCrearDiagnosticoFalloDis";
    }
    public String prepareSolucionPreventivo() {
        diagnosticoManteDispositivoActual = new DiagnosticoManteDispositivo();
        return "/Admin/moduloConfigMantenimiento/adminCrearDiagnosticoDis";
    }
    public String prepareCyP() {
        tipoMantenimientoActual= new TipoMantenimiento(3);
        diagnosticoManteDispositivoActual = new DiagnosticoManteDispositivo();
        return "/Admin/moduloConfigMantenimiento/adminCrearDiagnosticoDis";
    }
    public String prepareViewMantenimientosTotales() {
        return "/Admin/moduloConfigMantenimiento/adminViewMantenimientosTotalesDis";
    }

    public void setCambioRepuesto(String cambioRepuesto) {
        this.cambioRepuesto = cambioRepuesto;
    }
    
    
    /*
     public String addDiagnosticoFallo(){
        diagnosticoMantenimientoActual.setIdCronogramaMantenimientos(cronogramaMantenimientosActual);
        getDiagnosticoMantenimientoFacade().create(diagnosticoMantenimientoActual);
        cronogramaMantenimientosActual.setEstadoMantenimiento(true);
        updateCronograma();
        recargarLista();
        sendMailSolucionUser();
        return "adminViewSolucionIncidente";
    }*/
    public String addCronogramaMantenimientoDis() {
        try {
            cronogramaManteDisActual.setDescripcionProblemaDis("Ninguno reportado por el usuario");
            cronogramaManteDisActual.setIdTipoMantenimiento(new TipoMantenimiento(2, "preventivo"));
            cronogramaManteDisActual.setFechaProgMantenimientoDis(new Date());
            cronogramaManteDisActual.setEstadoMantenimientoDis(false);
            cronogramaManteDisActual.setIdOtroDispositivo(OtroDispositivoActual);
            getCronogramaManteDispositivoFacade().create(cronogramaManteDisActual);
            OtroDispositivoActual.setEstadoProgramadoDis(true);
            getOtroDispositivoFacade().edit(OtroDispositivoActual);
            recargarLista();
            prepareViewCalendario();
            addSuccessMessage("Mantenimiento preventivo programado", "Mantenimiento preventivo programado exitosamente con numero de ticket "+cronogramaManteDisActual.getIdCronogramaManteDispositivo()+"CFIPMP-DIS");
            return "adminCalendarioCronogramaDis";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String prepareCreateReportarFallo() {
        cronogramaManteDisActual = new CronogramaManteDispositivo();
        return "/User/userCrearIncidenteDis";
    }
    public String addCronogramaMantenimientoFallo() {
        try {
            cronogramaManteDisActual.setIdTipoMantenimiento(new TipoMantenimiento(1, "correctivo"));
            cronogramaManteDisActual.setFechaProgMantenimientoDis(new Date());
            cronogramaManteDisActual.setFechaInicioMantenimientoDis(new Date());
            cronogramaManteDisActual.setHoraMantenimientoDis(new Date());
            cronogramaManteDisActual.setEstadoMantenimientoDis(false);
            cronogramaManteDisActual.setIdUsuario(usuarioActual);
            getCronogramaManteDispositivoFacade().create(cronogramaManteDisActual);
            recargarLista();
            ticket="CFIPMC-DIS";
            sendMailRegistroTecDis();
            sendMailRegistroUserDis();
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
    
    
    //Envio de correos
     private void sendMail(String to, String subject, String body) {
        try {
            emailBean.sendMail(to, subject, body);
            JsfUtil.addSuccessMessage("Mensaje Enviado Exitosamente");
        } catch (NamingException | MessagingException ex) {
            JsfUtil.addErrorMessage("Error sending message " + ex.getClass().getName());
        }
    }
    private void sendMailDiagPrevUser() {
            String seccion ="";
            String responsable ="";
            String bloque = "";
            String idLan="";
            if(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdSeccion()==null){
                seccion="N/A";
                bloque="N/A";
            }else{
                seccion=diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdSeccion().toString();
                bloque=diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdSeccion().getIdBloque().getNombreBloque();
            }
            if(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdUsuario()==null){
                responsable="N/A";
            }else{
                responsable=diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdUsuario().toString();
            }
        String subject = "DIAGNOSTICO DEL TICKET #" + diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdCronogramaManteDispositivo()+"CFIPMP-DIS";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CODIGO DEL DIAGNOSTICO ");
        mensaje.append(diagnosticoManteDispositivoActual.getIdDiagnosticoManteDispositivo());
        mensaje.append(ticket);
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL MANTENIMIENTO ");
        mensaje.append(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdTipoMantenimiento().getNombreTipoMantenimiento().toUpperCase());
        mensaje.append("\n---------------------------------------------------------------------");
       
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getFechaProgMantenimientoDis().toLocaleString());
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
        mensaje.append(diagnosticoManteDispositivoActual.getRevisionDis().toUpperCase());
        mensaje.append("\nFECHA DE LA REVISION: ");
        mensaje.append(diagnosticoManteDispositivoActual.getFechaRevisionDis().toLocaleString());
        mensaje.append("\nDIAGNOSTICO: ");
        mensaje.append(diagnosticoManteDispositivoActual.getDiagnosticoDis().toUpperCase());
        mensaje.append("\nENTREGA DEL EQUIPO: ");
        mensaje.append(diagnosticoManteDispositivoActual.getFechaEntregaDis().toLocaleString());
        mensaje.append("\nCAMBIO DE REPUESTO: ");
        mensaje.append(diagnosticoManteDispositivoActual.getCambioIdioma());
        mensaje.append("\nTECNICO QUE ATENDIO EL CASO: ");
        mensaje.append(diagnosticoManteDispositivoActual.getIdUsuario().toString());
        
        
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL ");
        mensaje.append(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdModelo().getIdTipo().getNombreTipo().toUpperCase());
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nSERVICE TAG O (S/N): ");
        mensaje.append(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getCodigoDispositivo().toUpperCase());
        mensaje.append("\nMODELO: ");
        mensaje.append(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdModelo().getNombreModelo().toUpperCase());
        mensaje.append("\nMARCA: ");
        mensaje.append(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdModelo().getIdMarca().getNombreMarca().toUpperCase());
        
        sendMail(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdUsuario().getCorreoUsuario()+" "+"lccabal@misena.edu.co"+" "+"sistemas@cfiprovidencia.com"+" "+"auxsistemas@cfiprovidencia.com", subject, mensaje.toString());
    }
    
     
     public void sendMailMantenimientoPreventivo() {
       try{
            String seccion ="";
            String responsable ="";
            String bloque = "";
            String correo = "";
            if(cronogramaManteDisActual.getIdOtroDispositivo().getIdSeccion()==null){
                seccion="N/A";
                bloque="N/A";
            }else{
                seccion=cronogramaManteDisActual.getIdOtroDispositivo().getIdSeccion().toString();
                bloque=cronogramaManteDisActual.getIdOtroDispositivo().getIdSeccion().getIdBloque().getNombreBloque();
            }
            if(cronogramaManteDisActual.getIdOtroDispositivo().getIdUsuario()==null){
                responsable="N/A";
            }else{
                responsable=cronogramaManteDisActual.getIdOtroDispositivo().getIdUsuario().toString();
            }
        String subject = "MANTENIMIENTO PREVENTIVO PROGRAMADO CON TICKET #" + cronogramaManteDisActual.getIdCronogramaManteDispositivo()+"CFIPMP-DIS";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(cronogramaManteDisActual.getIdCronogramaManteDispositivo());
        mensaje.append("CFIPMP-DIS");
        mensaje.append("\nEL MANTENIMIENTO PREVENTIVO DESTINADO PARA SU DISPOSITIVO ESTÁ A PUNTO "
                      + "\nDE SER EJECUTADO, ESPERAMOS SU TOTAL COLABORACIÓN, ESTE MANTENIMIENTO"
                      + "\nTIENE UNA DURACIÓN DE ENTRE 1 O 2 HORAS DEPENDIENDO DEL ESTADO DEL DISPOSITIVO. ");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL MANTENIMIENTO PREVENTIVO");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nFECHA Y HORA DEL MANTENIMIENTO: ");
        mensaje.append(cronogramaManteDisActual.getFechaProgMantenimientoDis().toLocaleString());
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
        mensaje.append(cronogramaManteDisActual.getIdOtroDispositivo().getCodigoDispositivo().toUpperCase());
        mensaje.append("\nTIPO: ");
        mensaje.append(cronogramaManteDisActual.getIdOtroDispositivo().getIdModelo().getIdTipo().getNombreTipo().toUpperCase());
        mensaje.append("\nMODELO: ");
        mensaje.append(cronogramaManteDisActual.getIdOtroDispositivo().getIdModelo().getNombreModelo().toUpperCase());
        mensaje.append("\nMARCA: ");
        mensaje.append(cronogramaManteDisActual.getIdOtroDispositivo().getIdModelo().getIdMarca().getNombreMarca().toUpperCase());
      
        sendMail(cronogramaManteDisActual.getIdOtroDispositivo().getIdUsuario().getCorreoUsuario(), subject, mensaje.toString());
         } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }
     
     
     private void sendMailRegistroTecDis() {
            String seccion ="";
            String responsable ="";
            String bloque = "";
            String nombreUsuario="";
            String correoJornada = "";
            String jornada="";
            if(cronogramaManteDisActual.getIdOtroDispositivo().getIdSeccion()==null){
                seccion="N/A";
                bloque="N/A";
            }else{
                seccion=cronogramaManteDisActual.getIdOtroDispositivo().getIdSeccion().toString();
                bloque=cronogramaManteDisActual.getIdOtroDispositivo().getIdSeccion().getIdBloque().getNombreBloque();
            }
            if(cronogramaManteDisActual.getIdOtroDispositivo().getIdUsuario()==null){
                responsable="N/A";
            }else{
                responsable=cronogramaManteDisActual.getIdOtroDispositivo().getIdUsuario().toString();
            }
             if(cronogramaManteDisActual.getNombreUsuarioReporte()==null){
                nombreUsuario=" ";
            }else{
                nombreUsuario = cronogramaManteDisActual.getNombreUsuarioReporte().toUpperCase();
            }
            if(cronogramaManteDisActual.getIdTipoJornada()==null){
                jornada="N/A";
            }else{
                jornada=cronogramaManteDisActual.getIdTipoJornada().getNombre();
            }
            if(cronogramaManteDisActual.getIdTipoJornada()==null){
                correoJornada="";
            }else{
                correoJornada=cronogramaManteDisActual.getIdTipoJornada().getCorreo();
            }
        String subject = seccion.toUpperCase()+", FALLO REPORTADO TICKET #"+ cronogramaManteDisActual.getIdCronogramaManteDispositivo()+ticket ;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(cronogramaManteDisActual.getIdCronogramaManteDispositivo());
        mensaje.append(ticket);
        mensaje.append("\nRESPONSABLE: ");
        mensaje.append(responsable.toUpperCase());
        mensaje.append("\nUSUARIO: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nNOMBRE DEL USUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(nombreUsuario);
        mensaje.append("\nDESCRIPCIÓN DEL FALLO: ");
        mensaje.append(cronogramaManteDisActual.getDescripcionProblemaDis().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(cronogramaManteDisActual.getFechaProgMantenimientoDis().toLocaleString());
        mensaje.append("\nJORNADA DONDE SE REPORTO EL FALLO: ");
        mensaje.append(jornada.toUpperCase());
        mensaje.append("\n-----------------------------------------------------");
        mensaje.append("\nDATOS DEL DISPOSITIVO");
        mensaje.append("\n-----------------------------------------------------");
        mensaje.append("\nSERVICE TAG O (S/N): ");
        mensaje.append(cronogramaManteDisActual.getIdOtroDispositivo().getCodigoDispositivo().toUpperCase());
        mensaje.append("\nTIPO: ");
        mensaje.append(cronogramaManteDisActual.getIdOtroDispositivo().getIdModelo().getIdTipo().getNombreTipo().toUpperCase());
        mensaje.append("\nMODELO: ");
        mensaje.append(cronogramaManteDisActual.getIdOtroDispositivo().getIdModelo().getNombreModelo().toUpperCase());
        mensaje.append("\nMARCA: ");
        mensaje.append(cronogramaManteDisActual.getIdOtroDispositivo().getIdModelo().getIdMarca().getNombreMarca().toUpperCase());
        
        sendMail(" auxsistemas2@cfiprovidencia.com "+" "+" auxsistemas@cfiprovidencia.com "+" "+" sistemas@cfiprovidencia.com "+" "+correoJornada, subject, mensaje.toString());
    }
     
      private void sendMailRegistroUserDis() {
            String seccion ="";
            String responsable ="";
            String bloque = "";
            String nombreUsuario = "";
            String correo = "";
            if(cronogramaManteDisActual.getIdOtroDispositivo().getIdSeccion()==null){
                seccion="N/A";
                bloque="N/A";
            }else{
                seccion=cronogramaManteDisActual.getIdOtroDispositivo().getIdSeccion().toString();
                bloque=cronogramaManteDisActual.getIdOtroDispositivo().getIdSeccion().getIdBloque().getNombreBloque();
            }
            if(cronogramaManteDisActual.getIdOtroDispositivo().getIdUsuario()==null){
                responsable="N/A";
            }else{
                responsable=cronogramaManteDisActual.getIdOtroDispositivo().getIdUsuario().toString();
            }
             if(cronogramaManteDisActual.getNombreUsuarioReporte()==null){
                nombreUsuario=" ";
            }else{
                nombreUsuario = cronogramaManteDisActual.getNombreUsuarioReporte().toUpperCase();
            }
                if(cronogramaManteDisActual.getCorreoUsuarioReporte()==null){
                correo=" ";
            }else{
                correo = cronogramaManteDisActual.getCorreoUsuarioReporte();
            }
        String subject = "SU FALLO CON NUMERO DE TICKET #" + cronogramaManteDisActual.getIdCronogramaManteDispositivo()+ticket;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(cronogramaManteDisActual.getIdCronogramaManteDispositivo());
        mensaje.append(ticket);
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL FALLO REPORTADO");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDESCRIPCIÓN DEL FALLO: ");
        mensaje.append(cronogramaManteDisActual.getDescripcionProblemaDis().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(cronogramaManteDisActual.getFechaProgMantenimientoDis().toLocaleString());
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
        mensaje.append("\nDATOS DEL DISPOSITIVO");
        mensaje.append("\n---------------------------------------------------------------------");
        
        mensaje.append("\nSERVICE TAG O (S/N): ");
        mensaje.append(cronogramaManteDisActual.getIdOtroDispositivo().getCodigoDispositivo().toUpperCase());
        mensaje.append("\nTIPO: ");
        mensaje.append(cronogramaManteDisActual.getIdOtroDispositivo().getIdModelo().getIdTipo().getNombreTipo().toUpperCase());
        mensaje.append("\nMODELO: ");
        mensaje.append(cronogramaManteDisActual.getIdOtroDispositivo().getIdModelo().getNombreModelo().toUpperCase());
        mensaje.append("\nMARCA: ");
        mensaje.append(cronogramaManteDisActual.getIdOtroDispositivo().getIdModelo().getIdMarca().getNombreMarca().toUpperCase());
         
        mensaje.append("\n\nPRONTO UNO DE NUESTROS TECNICOS ESTARA ATENDIENDO SU SOLICITUD");
      
        sendMail(cronogramaManteDisActual.getIdOtroDispositivo().getIdUsuario().getCorreoUsuario()+" "+ correo, subject, mensaje.toString());
    }
      
       private void sendMailSolucionUserDis() {
            String seccion ="";
            String responsable ="";
            String bloque = "";
            String correo="";
            String jornada = "";
            String correoJornada="";
            if(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdSeccion()==null){
                seccion="N/A";
                bloque="N/A";
            }else{
                seccion=diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdSeccion().toString();
                bloque=diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdSeccion().getIdBloque().getNombreBloque();
            }
            if(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdUsuario()==null){
                responsable="N/A";
            }else{
                responsable=diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdUsuario().toString();
            }
            if(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getCorreoUsuarioReporte()==null){
                correo="";
            }else{
                correo=diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getCorreoUsuarioReporte();
            }
            if(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdTipoJornada()==null){
                jornada="N/A";
            }else{
                jornada=diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdTipoJornada().getNombre();
            }
            if(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdTipoJornada()==null){
                correoJornada="";
            }else{
                correoJornada=diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdTipoJornada().getCorreo();
            }
        String subject = "DIAGNOSTICO DEL TICKET #" + diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdCronogramaManteDispositivo()+"CFIPMC-DIS";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CODIGO DEL DIAGNOSTICO ");
        mensaje.append(diagnosticoManteDispositivoActual.getIdDiagnosticoManteDispositivo());
        mensaje.append("CFIPDIAG-DIS");
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL MANTENIMIENTO CORRECTIVO");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDESCRIPCIÓN DEL FALLO: ");
        mensaje.append(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getDescripcionProblemaDis().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getFechaProgMantenimientoDis().toLocaleString());
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
        mensaje.append(diagnosticoManteDispositivoActual.getRevisionDis().toUpperCase());
        mensaje.append("\nFECHA DE LA REVISIÓN: ");
        mensaje.append(diagnosticoManteDispositivoActual.getFechaRevisionDis().toLocaleString());
        mensaje.append("\nDIAGNOISTICO: ");
        mensaje.append(diagnosticoManteDispositivoActual.getDiagnosticoDis().toUpperCase());
        mensaje.append("\nFECHA DE ENTREGA DEL EQUIPO: ");
        mensaje.append(diagnosticoManteDispositivoActual.getFechaEntregaDis().toLocaleString());
        mensaje.append("\nCAMBIO DE REPUESTO: ");
        mensaje.append(diagnosticoManteDispositivoActual.getCambioIdioma());
        
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL DISPOSITIVO:");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nSERVICE TAG O (S/N): ");
        mensaje.append(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getCodigoDispositivo().toUpperCase());
        mensaje.append("\nTIPO: ");
        mensaje.append(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdModelo().getIdTipo().getNombreTipo().toUpperCase());
        mensaje.append("\nMODELO: ");
        mensaje.append(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdModelo().getNombreModelo().toUpperCase());
        mensaje.append("\nMARCA: ");
        mensaje.append(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdModelo().getIdMarca().getNombreMarca().toUpperCase());
        
       
    
   
        mensaje.append("\n\nSU INCIDENTE FUE CORREGIDO SATISFACTORIAMENTE");        
        sendMail(diagnosticoManteDispositivoActual.getIdCronogramaManteDispositivo().getIdOtroDispositivo().getIdUsuario().getCorreoUsuario()+" "+correo +" "+"auxsistemas2@cfiprovidencia.com"+" "+"sistemas@cfiprovidencia.com"+" "+"auxsistemas@cfiprovidencia.com"+" "+correoJornada, subject, mensaje.toString());
    }

    
    
    
    
    
    // Cronograma Calendario
    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    DefaultScheduleEvent man= new DefaultScheduleEvent();
 
    public void initDis() {
        recargarListaMantenimiento();
        recargarListaMantenimientoP();
        recargarListaMantenimientoC();
        eventModel = new DefaultScheduleModel();
       
        //Mantenimientos Correctivos 
        for (Iterator<CronogramaManteDispositivo> it = recargarListaMantenimientoC().iterator(); it.hasNext();) {
            CronogramaManteDispositivo i = it.next();
            String seccion ="";
            String responsable ="";
            String bloque = "";
            if(i.getIdOtroDispositivo().getIdSeccion()==null){
                seccion="N/A";
                bloque="N/A";
            }else{
                seccion=i.getIdOtroDispositivo().getIdSeccion().toString();
                bloque=i.getIdOtroDispositivo().getIdSeccion().getIdBloque().getNombreBloque();
            }
            if(i.getIdOtroDispositivo().getIdUsuario()==null){
                responsable="N/A";
            }else{
                responsable=i.getIdOtroDispositivo().getIdUsuario().toString();
            }
            eventModel.addEvent(new DefaultScheduleEvent("/"+seccion.toUpperCase()+"\n/"+responsable.toUpperCase()+"\n/TICKET "+i.getIdCronogramaManteDispositivo()+"CFIPMC-DIS",i.getFechaInicioMantenimientoDis(),i.getHoraMantenimientoDis(),"MANTENIMIENTO_CORRECTIVO"));
    
        }
        
        //Mantenimientos Preventivos 
         for (Iterator<CronogramaManteDispositivo> it = recargarListaMantenimientoP().iterator(); it.hasNext();) {
            CronogramaManteDispositivo i = it.next();
            String seccion ="";
            String responsable ="";
            String bloque = "";
            if(i.getIdOtroDispositivo().getIdSeccion()==null){
                seccion="N/A";
                bloque="N/A";
            }else{
                seccion=i.getIdOtroDispositivo().getIdSeccion().toString();
                bloque=i.getIdOtroDispositivo().getIdSeccion().getIdBloque().getNombreBloque();
            }
            if(i.getIdOtroDispositivo().getIdUsuario()==null){
                responsable="N/A";
            }else{
                responsable=i.getIdOtroDispositivo().getIdUsuario().toString();
            }
            eventModel.addEvent(new DefaultScheduleEvent("/TICKET: \n#"+i.getIdCronogramaManteDispositivo()+"CFIPMP-DIS\n /SECCIÓN:\n"+seccion.toUpperCase(),i.getFechaProgMantenimientoDis(),i.getHoraMantenimientoDis(),"MANTENIMIENTO PREVENTIVO / RESPONSABLE: "+responsable.toUpperCase()+" / SERVICE TAG O (S/N): "+i.getIdOtroDispositivo().getCodigoDispositivo().toUpperCase()));
        }
    };
     public List<CronogramaManteDispositivo> recargarListaMantenimientoP(){
        listaCronogramaMantenimientosP = new ArrayList<>();
        listaCronogramaMantenimientosP=getCronogramaManteDispositivoFacade().consultaCronogramaTipoPendiente(false, new TipoMantenimiento(2));
        return listaCronogramaMantenimientosP;
     }
      public List<CronogramaManteDispositivo> recargarListaMantenimientoC(){
        listaCronogramaMantenimientosC = new ArrayList<>();
        listaCronogramaMantenimientosC=getCronogramaManteDispositivoFacade().consultaCronogramaTipoPendiente(false, new TipoMantenimiento(1));
        return listaCronogramaMantenimientosC;
     }
       public List<CronogramaManteDispositivo> recargarListaMantenimiento(){
        listaCronogramaMantenimientos = new ArrayList<>();
        listaCronogramaMantenimientos=getCronogramaManteDispositivoFacade().consultaCronogramaTipoPendiente(false, new TipoMantenimiento(3));
        return listaCronogramaMantenimientos;
     }
      public ScheduleModel getEventModel() {
        return eventModel;
    }
    
    public String prepareViewCalendario(){
        initDis();
        return "/Admin/moduloConfigMantenimiento/adminCalendarioCronogramaDis";
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
   
    public void prepareViewDiagnostico(ActionEvent event) {
        cronogramaManteDisActual = new CronogramaManteDispositivo();
        cronogramaManteDisActual = (CronogramaManteDispositivo) event.getComponent().getAttributes().get("mantenimientoDis");
        listaDiagnosticoDis = new ArrayList<>();
        //listaActividadAprendizaje=actividadActual.getActividadAprendizajeList(); 
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    
    public String viewDiagnostico(){
        ticket="CFIPMP-DIS";
        return "adminViewDiagnosticoMantenimientoPrevDis";
    }
    public String viewDiagnosticoCorre(){
        ticket="CFIPMC-DIS";
        return "adminViewDiagnosticoMantenimientoPrevDis";
    }
}
