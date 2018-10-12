/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.EstadoSolicitudEdificio;
import com.proyectoCFIP.entities.EstadoSolicitudRefrigerio;
import com.proyectoCFIP.entities.RespuestaRefrigerio;
import com.proyectoCFIP.entities.RespuestaSolicitud;
import com.proyectoCFIP.entities.Seccion;
import com.proyectoCFIP.entities.SolicitudEdificio;
import com.proyectoCFIP.entities.TipoSeccion;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.EstadoSolicitudEdificioFacade;
import com.proyectoCFIP.sessions.RespuestaRefrigerioFacade;
import com.proyectoCFIP.sessions.RespuestaSolicitudFacade;
import com.proyectoCFIP.sessions.SeccionFacade;
import com.proyectoCFIP.sessions.SolicitudEdificioFacade;
import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
public class SolicitudEdificioController implements Serializable{

    @EJB
    private SolicitudEdificioFacade solicitudEdificioFacade;
    @EJB
    private SeccionFacade seccionFacade;
    @EJB
    private EstadoSolicitudEdificioFacade estadoSolicitudEdificioFacade;
    @EJB
    private RespuestaSolicitudFacade respuestaSolicitudFacade;
    private RespuestaSolicitud respuestaSolicitudActual;
    private List<RespuestaSolicitud> respuestaSolicitudList;
    @EJB
    private RespuestaRefrigerioFacade respuestaRefrigerioFacade;
    private RespuestaRefrigerio respuestaRefrigerioActual;
    private List<RespuestaRefrigerio> respuestaRefrigerioList;
    private EstadoSolicitudEdificio estadoSolicitudEdificioActual;
    private List <EstadoSolicitudEdificio> estadoSolicitudEdificioList;
    private SolicitudEdificio solicitudEdificioActual;
    private List<SolicitudEdificio> solicitudEdificioList;
    private Usuario usuarioActual;
    private Seccion idSeccion;
    private Date fechaAlquiler;
    private Date fechaFinAlquiler;
    private Date horaInicioAlquiler;
    private Date horaFinAlquiler;
    private Date fechaActual = new Date();
    
    private List<Seccion> listaSeccion;
    @EJB
    EmailSessionBean emailBean;
    public SolicitudEdificioController() {
    }
    
    public boolean isRefrigerio(){
        return solicitudEdificioActual.getEstadoRefrigerio()== null? false : solicitudEdificioActual.getEstadoRefrigerio() == true;
    }
      public boolean isSeccion(){
        return solicitudEdificioActual.getIdSeccion()== null? false : solicitudEdificioActual.getIdSeccion().getIdSeccion()== (short)78;
    }
     

    public SolicitudEdificioFacade getSolicitudEdificioFacade() {
        return solicitudEdificioFacade;
    }

    public void setSolicitudEdificioFacade(SolicitudEdificioFacade solicitudEdificioFacade) {
        this.solicitudEdificioFacade = solicitudEdificioFacade;
    }

    public SolicitudEdificio getSolicitudEdificioActual() {
        return solicitudEdificioActual;
    }

    public void setSolicitudEdificioActual(SolicitudEdificio solicitudEdificioActual) {
        this.solicitudEdificioActual = solicitudEdificioActual;
    }

    public List<SolicitudEdificio> getSolicitudEdificioList() {
        return solicitudEdificioList;
    }

    public void setSolicitudEdificioList(List<SolicitudEdificio> solicitudEdificioList) {
        this.solicitudEdificioList = solicitudEdificioList;
    }

   
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public SeccionFacade getSeccionFacade() {
        return seccionFacade;
    }

    public void setSeccionFacade(SeccionFacade seccionFacade) {
        this.seccionFacade = seccionFacade;
    }

    public EstadoSolicitudEdificioFacade getEstadoSolicitudEdificioFacade() {
        return estadoSolicitudEdificioFacade;
    }

    public void setEstadoSolicitudEdificioFacade(EstadoSolicitudEdificioFacade estadoSolicitudEdificioFacade) {
        this.estadoSolicitudEdificioFacade = estadoSolicitudEdificioFacade;
    }

    public EstadoSolicitudEdificio getEstadoSolicitudEdificioActual() {
        return estadoSolicitudEdificioActual;
    }

    public void setEstadoSolicitudEdificioActual(EstadoSolicitudEdificio estadoSolicitudEdificioActual) {
        this.estadoSolicitudEdificioActual = estadoSolicitudEdificioActual;
    }

    public RespuestaSolicitudFacade getRespuestaSolicitudFacade() {
        return respuestaSolicitudFacade;
    }

    public void setRespuestaSolicitudFacade(RespuestaSolicitudFacade respuestaSolicitudFacade) {
        this.respuestaSolicitudFacade = respuestaSolicitudFacade;
    }

    public RespuestaSolicitud getRespuestaSolicitudActual() {
        return respuestaSolicitudActual;
    }

    public void setRespuestaSolicitudActual(RespuestaSolicitud respuestaSolicitudActual) {
        this.respuestaSolicitudActual = respuestaSolicitudActual;
    }

    public List<RespuestaSolicitud> getRespuestaSolicitudList() {
        return respuestaSolicitudList;
    }

    public void setRespuestaSolicitudList(List<RespuestaSolicitud> respuestaSolicitudList) {
        this.respuestaSolicitudList = respuestaSolicitudList;
    }

    public RespuestaRefrigerioFacade getRespuestaRefrigerioFacade() {
        return respuestaRefrigerioFacade;
    }

    public void setRespuestaRefrigerioFacade(RespuestaRefrigerioFacade respuestaRefrigerioFacade) {
        this.respuestaRefrigerioFacade = respuestaRefrigerioFacade;
    }

    public RespuestaRefrigerio getRespuestaRefrigerioActual() {
        return respuestaRefrigerioActual;
    }

    public void setRespuestaRefrigerioActual(RespuestaRefrigerio respuestaRefrigerioActual) {
        this.respuestaRefrigerioActual = respuestaRefrigerioActual;
    }

    public List<RespuestaRefrigerio> getRespuestaRefrigerioList() {
        return respuestaRefrigerioList;
    }

    public void setRespuestaRefrigerioList(List<RespuestaRefrigerio> respuestaRefrigerioList) {
        this.respuestaRefrigerioList = respuestaRefrigerioList;
    }

    public Date getFechaFinAlquiler() {
        return fechaFinAlquiler;
    }

    public void setFechaFinAlquiler(Date fechaFinAlquiler) {
        this.fechaFinAlquiler = fechaFinAlquiler;
    }
    
    
    
    
    public void info(){
        if(idSeccion.getIdSeccion()== (short)78){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Para reservar el auditorio primero se enviara una solicitud al jefe de mantenimiento de edificios y este validara si para la fecha se puede reservar el mismo"));
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Solitud de sala", "Para reservar el auditorio primero se enviara una solicitud al jefe de mantenimiento de edificios y este validara si para la fecha se puede reservar el mismo");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }else if(idSeccion.getIdSeccion()== (short)13 || idSeccion.getIdSeccion()== (short)77 ||idSeccion.getIdSeccion()== (short)79){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Esta sala no necesita ser validada por parte del jefe de mantenimiento de edificios para ser reservada. Recuerda que la capacidad maxima de esta sala es de 30 personas, juntando la sala 1 y 2 queda con una capacidad maxima de 60 personas"));
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Esta sala no necesita ser validada por parte del jefe de mantenimiento de edificios para ser reservada."));
        }        
    }
    
     public List<Seccion> getListaSeccionEspecial() {
        listaSeccion = new ArrayList<>();
        listaSeccion = getSeccionFacade().consultaSeccionEspecial(new TipoSeccion(1,"Sala especial"),new TipoSeccion(2,"Auditorio"));
        return listaSeccion;
    }
   public String addSolicitud() {
        SolicitudEdificio solicitudEdificio=getSolicitudEdificioFacade().findByValidarReserva(fechaAlquiler,horaInicioAlquiler, horaFinAlquiler, idSeccion);
        SolicitudEdificio solicitudEdificioAudiovisuales1=getSolicitudEdificioFacade().findByValidarAudiovisuales(fechaAlquiler,horaInicioAlquiler, horaFinAlquiler, new Seccion(13, "Cuarto de audiovisuales 1" ));
        SolicitudEdificio solicitudEdificioAudiovisuales2=getSolicitudEdificioFacade().findByValidarAudiovisuales(fechaAlquiler,horaInicioAlquiler, horaFinAlquiler, new Seccion(77, "Cuarto de audiovisuales 2"));
        SolicitudEdificio solicitudEdificioAudiovisuales1y2=getSolicitudEdificioFacade().findByValidarAudiovisuales(fechaAlquiler,horaInicioAlquiler, horaFinAlquiler, new Seccion(79, "Cuarto de audiovisuales 1 y 2" ));
        if(solicitudEdificio != null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puedes reservar esta sala","La sala cuenta con una reserva a la misma hora, por favor verifica las horas disponibles en el cronograma");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }else if(idSeccion.getIdSeccion()== (short) 13 && solicitudEdificioAudiovisuales1y2 != null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puedes reservar esta sala","La sala de audiovisuales 1 y 2 ya se encuentra reservada");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
        else if(idSeccion.getIdSeccion()== (short) 77 && solicitudEdificioAudiovisuales1y2 != null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puedes reservar esta sala","La sala de audiovisuales 1 y 2 ya se encuentra reservada");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
        else if(idSeccion.getIdSeccion()== (short) 79 && solicitudEdificioAudiovisuales1 != null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puedes reservar esta sala","La sala de audiovisuales 1 y 2 ya se encuentra reservada");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
        else if(idSeccion.getIdSeccion()== (short) 79 && solicitudEdificioAudiovisuales2 != null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puedes reservar esta sala","La sala de audiovisuales 1 y 2 ya se encuentra reservada");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
        else if(horaFinAlquiler.before(horaInicioAlquiler)){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al realizar la reservación","La hora de comienzo de la reserva no puede ser mayor a la hora de finalización");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
        else{
            try{
            
                if(idSeccion.getIdSeccion()== (short)78){
                    solicitudEdificioActual.setIdEstadoSolicitudEdificio(new EstadoSolicitudEdificio(2, "Solicitado"));
                }else{
                    solicitudEdificioActual.setIdEstadoSolicitudEdificio(new EstadoSolicitudEdificio(1, "Reservado"));
                }
                if(solicitudEdificioActual.getRefrigerio() == true){
                    solicitudEdificioActual.setIdEstadoSolicitudRefrigerio(new EstadoSolicitudRefrigerio(3, "Solicitado"));
                }else{
                    solicitudEdificioActual.setIdEstadoSolicitudRefrigerio(new EstadoSolicitudRefrigerio(4, "Sin solicitar"));
                }
                    Date fecha=fechaAlquiler;
                    solicitudEdificioActual.setFechaAlquiler(fecha); 
                    Date hora1=(Date) horaInicioAlquiler;
                    solicitudEdificioActual.setHoraInicioAlquiler(hora1); 
                    Date hora2=(Date) horaFinAlquiler;
                    solicitudEdificioActual.setHoraFinAlquiler(hora2); 
                    solicitudEdificioActual.setFechaSolicitud(new Date());
                    solicitudEdificioActual.setIdUsuario(usuarioActual);
                    solicitudEdificioActual.setIdSeccion(idSeccion);
                    getSolicitudEdificioFacade().create(solicitudEdificioActual);
                    init();
                    reservarSalaTech();
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Su reserva fue realizada correctamente","Se reservo la Seccion: "+solicitudEdificioActual.getIdSeccion().getNombreSeccion().toUpperCase());
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                    return "/usuario/modManteEdificios/reservaSala/verCalendarioReserva";  
            }catch (Exception e) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al realizar la reserva","Vuelve a intentarlo llenando los campos completamente");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                    return null;
            }
        }        
    }

    public void getMessageDiag(){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al realizar la reservación","La hora de comienzo de la reserva no puede ser mayor a la hora de finalización");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }  
    public void prepareCreate(){
        fechaAlquiler = null;
        fechaFinAlquiler = null;
        horaInicioAlquiler = null;
        horaFinAlquiler = null;
        idSeccion = null;
        solicitudEdificioActual = new SolicitudEdificio();
    }
      public String prepareCreateMultiples(){
        fechaAlquiler = null;
        horaInicioAlquiler = null;
        horaFinAlquiler = null;
        idSeccion = null;
        solicitudEdificioActual = new SolicitudEdificio();
        return "userCrearReservaMultiple";
    }
    
    public String prepareListSolicitudesUser(){
        return "/usuario/modManteEdificios/estadoSolicitudes/listaEstadoSolicitud";
    }
    public void prepareCrearRespuesta(){
        respuestaSolicitudActual = new RespuestaSolicitud();
    }
    public void prepareCrearRespuestaRefrigerio(){
        respuestaRefrigerioActual = new RespuestaRefrigerio();
    }
    public void addRespuesta(){
        respuestaSolicitudActual.setIdSolicitudEdificio(solicitudEdificioActual);
        respuestaSolicitudActual.setFechaRespuesta(new Date());
        respuestaSolicitudFacade.create(respuestaSolicitudActual);
        solicitudEdificioFacade.edit(solicitudEdificioActual);
        respuestaSolicitudAuditorio();
        init();
        getSolicitudEdificioList();
    }
     public void addRespuestaRefrigerio(){
        respuestaRefrigerioActual.setIdSolicitudEdificio(solicitudEdificioActual);
        respuestaRefrigerioActual.setFechaRespuesta(new Date());
        respuestaRefrigerioFacade.create(respuestaRefrigerioActual);
        solicitudEdificioFacade.edit(solicitudEdificioActual);
        respuestaSolicitudRefrigerio();
        init();
        getSolicitudEdificioList();
    }
    public void addRespuestaEdificio(){
        respuestaRefrigerioActual.setIdSolicitudEdificio(solicitudEdificioActual);
        respuestaRefrigerioActual.setFechaRespuesta(new Date());
        respuestaRefrigerioFacade.create(respuestaRefrigerioActual);
        solicitudEdificioFacade.edit(solicitudEdificioActual);
        init();
        getSolicitudEdificioList();
    }
    public void prepareCancelar(){
        if(solicitudEdificioActual.getFechaAlquiler().before(new Date()) && solicitudEdificioActual.getHoraInicioAlquiler().before(new Date())){
                 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede cancelar la reserva","La fecha de la reservación se ha cumplido "+solicitudEdificioActual.getIdSeccion().getNombreSeccion().toUpperCase());
                 RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        else{
            solicitudEdificioActual.setIdEstadoSolicitudEdificio(new EstadoSolicitudEdificio(4, "Cancelado"));
            getSolicitudEdificioFacade().edit(solicitudEdificioActual);
            salaCanceladaMensaje();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Reserva cancelada","La reservación fue cancelada"+solicitudEdificioActual.getIdSeccion().getNombreSeccion().toUpperCase());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
    public void recargarList(){
        
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
    private ScheduleEvent event = new DefaultScheduleEvent();
    DefaultScheduleEvent man= new DefaultScheduleEvent();
 
    public void init() {
        eventModel = new DefaultScheduleModel();
        
        //Salas especiales reservadas
        for (Iterator<SolicitudEdificio> it = getRecargarListaReservas().iterator(); it.hasNext();) {
            SolicitudEdificio i = it.next();
            String fechaInicio;
            DateFormat fecha1 = new SimpleDateFormat("hh:mma");
            fechaInicio = fecha1.format(i.getHoraInicioAlquiler());
            String fechaFin;
            DateFormat fecha2 = new SimpleDateFormat("hh:mma");
            fechaFin = fecha2.format(i.getHoraFinAlquiler());
            Date FechaFinAlquiler = null;
            if(i.getFechaFinAlquiler()==null){
                FechaFinAlquiler = i.getFechaAlquiler();
            }else{
                FechaFinAlquiler = i.getFechaFinAlquiler();
            }
            eventModel.addEvent(new DefaultScheduleEvent("-"+i.getIdSeccion().getNombreSeccion().toUpperCase()+"\n-"+i.getIdUsuario().toString().toUpperCase()+"\n-"+fechaInicio +" - "+fechaFin+ "\n-REFRIGERIO: "+i.getIdEstadoSolicitudRefrigerio().getNombre().toUpperCase(),i.getFechaAlquiler(), FechaFinAlquiler, "RESERVADA")); 
         
        }
        //Solicitudes Canceladas
       
        //Salas especiales solicitadas
        for (Iterator<SolicitudEdificio> it = getRecargarListaSolicitud().iterator(); it.hasNext();) {
            SolicitudEdificio i = it.next();
            String fechaInicio;
            DateFormat fecha1 = new SimpleDateFormat("hh:mma");
            fechaInicio = fecha1.format(i.getHoraInicioAlquiler());
            String fechaFin;
            DateFormat fecha2 = new SimpleDateFormat("hh:mma");
            fechaFin = fecha2.format(i.getHoraFinAlquiler());
            Date FechaFinAlquiler = null;
            if(i.getFechaFinAlquiler()==null){
                FechaFinAlquiler = i.getFechaAlquiler();
            }else{
                FechaFinAlquiler = i.getFechaFinAlquiler();
            }            
            eventModel.addEvent(new DefaultScheduleEvent("-"+i.getIdSeccion().getNombreSeccion().toUpperCase()+"\n-"+i.getIdUsuario().toString().toUpperCase()+"\n-"+fechaInicio +" - "+fechaFin+"\n-REFRIGERIO: "+i.getIdEstadoSolicitudRefrigerio().getNombre().toUpperCase(),i.getFechaAlquiler(), FechaFinAlquiler,"SOLICITUD"));  
        }
    };
    public List<SolicitudEdificio> getRecargarListaReservas(){
        solicitudEdificioList = new ArrayList<>();
        solicitudEdificioList=  getSolicitudEdificioFacade().consultaEstado(new EstadoSolicitudEdificio(1, "Reservado"));
        return solicitudEdificioList;
    }

    public List<SolicitudEdificio> getRecargarListaSolicitud(){
        solicitudEdificioList = new ArrayList<>();
        solicitudEdificioList=  getSolicitudEdificioFacade().consultaEstado(new EstadoSolicitudEdificio(2, "Solicitado"));
        return solicitudEdificioList;
    }
    public List<SolicitudEdificio> getRecargarListaCancelado(){
        solicitudEdificioList = new ArrayList<>();
        solicitudEdificioList=  getSolicitudEdificioFacade().consultaEstado(new EstadoSolicitudEdificio(4, "Cancelado"));
        return solicitudEdificioList;
    }
    public List<SolicitudEdificio> getRecargarListaSolicitudRefrigerio(){
        solicitudEdificioList = new ArrayList<>();
        solicitudEdificioList=  getSolicitudEdificioFacade().consultaEstadoRefrigerio(new EstadoSolicitudRefrigerio(3, "Solicitado"));
        return solicitudEdificioList;
    }
      public List<SolicitudEdificio> getRecargarListaSolicitudDia(){
        solicitudEdificioList = new ArrayList<>();
        solicitudEdificioList=  getSolicitudEdificioFacade().consultaEstadoDia(new Date());
        return solicitudEdificioList;
    }
        public List<SolicitudEdificio> getSolicitudEdificioUserlist(){
        solicitudEdificioList = new ArrayList<>();
        solicitudEdificioList=  getSolicitudEdificioFacade().consultaSolicitudUser(usuarioActual);
        return solicitudEdificioList;
    }
  
    public ScheduleModel getEventModel() {
        return eventModel;
    }
    public String prepareViewMantenimientos(){
        return "/Admin/moduloConfigMantenimiento/mantenimientosAnuales/adminViewMantenimientosAnualesCom";
    }
    
    public String prepareViewCalendario(){
        getRecargarListaReservas();
        init();
        solicitudEdificioActual = new SolicitudEdificio();
        return "/usuario/modManteEdificios/reservaSala/verCalendarioReserva";
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

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public Date getHoraInicioAlquiler() {
        return horaInicioAlquiler;
    }

    public void setHoraInicioAlquiler(Date horaInicioAlquiler) {
        this.horaInicioAlquiler = horaInicioAlquiler;
    }

    public Date getHoraFinAlquiler() {
        return horaFinAlquiler;
    }

    public void setHoraFinAlquiler(Date horaFinAlquiler) {
        this.horaFinAlquiler = horaFinAlquiler;
    }

    public Seccion getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Seccion idSeccion) {
        this.idSeccion = idSeccion;
    }
    
    
    
    /*public void validarHora(FacesContext contex, UIComponent component, Object value)throws ValidatorException{
        
        SolicitudEdificio solicitudEdificio=getSolicitudEdificioFacade().findByHora(fechaAlquiler,(Date)value);
        if(solicitudEdificio != null){
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puedes reservar","Ya esta sala se encuentra reservada"));   
        }else{
            Date fecha=fechaAlquiler;
            solicitudEdificioActual.setFechaAlquiler(fecha); 
            Date hora=(Date) value;
            solicitudEdificioActual.setHoraInicioAlquiler(hora); 
        }
    }
      */ 

    public Date getFechaActual() {
        fechaActual = new Date();
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
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
    private void reservarSalaTech() {
        String refrigerio;
        String auditorio;
        if(solicitudEdificioActual.getIdEstadoSolicitudEdificio().getIdEstadoSolicitudEdificio()==2){
            auditorio = "SI SOLICITADO";
        }else{
            auditorio  = "NO SOLICITADO";
        }
        
        if(solicitudEdificioActual.getRefrigerio() == true){
            refrigerio = "SI SOLICITADO";
        }else{
            refrigerio = "NO SOLICITADO";
        }
        String fecha3;
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        fecha3 = fecha.format(solicitudEdificioActual.getFechaAlquiler());
        String fechaFin;
        DateFormat fechaFinal = new SimpleDateFormat("dd/MM/yyyy");
        if(solicitudEdificioActual.getFechaFinAlquiler() ==null){
            fechaFin = fechaFinal.format(solicitudEdificioActual.getFechaAlquiler());
        }else{
            fechaFin = fechaFinal.format(solicitudEdificioActual.getFechaFinAlquiler());
        }          
        String horaInicio;
        DateFormat fecha1 = new SimpleDateFormat("hh:mma");
        horaInicio = fecha1.format(solicitudEdificioActual.getHoraInicioAlquiler());
        String horaFin;
        DateFormat fecha2 = new SimpleDateFormat("hh:mma");
        horaFin = fecha2.format(solicitudEdificioActual.getHoraFinAlquiler());
       
        String subject = "RESERVA DE SALA: "+solicitudEdificioActual.getIdSeccion().getNombreSeccion().toUpperCase()+", NUMERO DE TICKET #"+ solicitudEdificioActual.getIdSolicitudEdificio()+"CFIPRS-I" ;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET: #");
        mensaje.append(solicitudEdificioActual.getIdSolicitudEdificio());
        mensaje.append("CFIPRS-I");
        mensaje.append("\nUSUARIO QUE REALIZO LA RESERVA: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nSECCIÓN: ");
        mensaje.append(solicitudEdificioActual.getIdSeccion().getNombreSeccion().toUpperCase());
        mensaje.append("\nBLOQUE: ");
        mensaje.append(solicitudEdificioActual.getIdSeccion().getIdBloque().getNombreBloque().toUpperCase());
        mensaje.append("\nDESCRIPCIÓN DE LA RESERVA: ");
        mensaje.append(solicitudEdificioActual.getDescripcion().toUpperCase());
        mensaje.append("\nFECHA DE INICIO DE LA RESERVA: ");
        mensaje.append(fecha3);
        mensaje.append("\nHORA DE INICIO DE LA RESERVA: ");
        mensaje.append(horaInicio);
        mensaje.append("\nHORA QUE FINALIZA LA RESERVA: ");
        mensaje.append(horaFin);
        mensaje.append("\n\n----------------------------------------------------\n ");
        mensaje.append("\nSOLICITUD AUDITORIO: ");
        mensaje.append(auditorio);
        mensaje.append("\nSOLICITUD REFRIGERIO: ");
        mensaje.append(refrigerio);
        mensaje.append("\nDESCRIPCIÓN SOLICITUD REFRIGERIO: ");
        mensaje.append(solicitudEdificioActual.getDescRefrigerio().toUpperCase());
        mensaje.append("\n\n----------------------------------------------------\n ");
       
        sendMail(solicitudEdificioActual.getIdUsuario().getCorreoUsuario()+" "+"auxsistemas2@cfiprovidencia.com"+" "+"mantenimiento@cfiprovidencia.com"+" "+"biblioteca@cfiprovidencia.com"+" "+"rectoria@cfiprovidencia.com"+" "+"coordinacion.academica@cfiprovidencia.com", subject, mensaje.toString());  
    }
    private void respuestaSolicitudRefrigerio() {
       
        String fecha3;
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        fecha3 = fecha.format(solicitudEdificioActual.getFechaAlquiler());
        String fechaFin;
        DateFormat fechaFinal = new SimpleDateFormat("dd/MM/yyyy");
        if(solicitudEdificioActual.getFechaFinAlquiler() ==null){
            fechaFin = fechaFinal.format(solicitudEdificioActual.getFechaAlquiler());
        }else{
            fechaFin = fechaFinal.format(solicitudEdificioActual.getFechaFinAlquiler());
        }      
        
        String horaInicio;
        DateFormat fecha1 = new SimpleDateFormat("hh:mma");
        horaInicio = fecha1.format(solicitudEdificioActual.getHoraInicioAlquiler());
        String horaFin;
        DateFormat fecha2 = new SimpleDateFormat("hh:mma");
        horaFin = fecha2.format(solicitudEdificioActual.getHoraFinAlquiler());
       
        String subject = "RESPUESTA SOLICITUD REFRIGERIO DE SALA: "+solicitudEdificioActual.getIdSeccion().getNombreSeccion().toUpperCase()+", NUMERO DE TICKET #"+ solicitudEdificioActual.getIdSolicitudEdificio()+"CFIPRS-I" ;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET: #");
        mensaje.append(solicitudEdificioActual.getIdSolicitudEdificio());
        mensaje.append("CFIPRS-I");
        mensaje.append("\nUSUARIO QUE REALIZO LA RESERVA: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nSECCIÓN: ");
        mensaje.append(solicitudEdificioActual.getIdSeccion().getNombreSeccion().toUpperCase());
        mensaje.append("\nBLOQUE: ");
        mensaje.append(solicitudEdificioActual.getIdSeccion().getIdBloque().getNombreBloque().toUpperCase());
        mensaje.append("\nDESCRIPCIÓN DE LA RESERVA: ");
        mensaje.append(solicitudEdificioActual.getDescripcion().toUpperCase());
         mensaje.append("\nFECHA DE INICIO DE LA RESERVA: ");
        mensaje.append(fecha3);
        mensaje.append("\nFECHA QUE FINALIZA DE LA RESERVA: ");
        mensaje.append(fechaFin);
        mensaje.append("\nHORA DE INICIO DE LA RESERVA: ");
        mensaje.append(horaInicio);
        mensaje.append("\nHORA FIN DE LA RESERVA: ");
        mensaje.append(horaFin);
        mensaje.append("\n\n----------------------------------------------------\n ");
        mensaje.append("\nESTADO SOLICITUD REFRIGERIO: ");
        mensaje.append(solicitudEdificioActual.getIdEstadoSolicitudRefrigerio().getNombre().toUpperCase());
        mensaje.append("\nRESPUESTA DE LA SOLICITUD: ");
        mensaje.append(respuestaRefrigerioActual.getDescripcion());
        mensaje.append("\n\n----------------------------------------------------\n ");
       
        sendMail(solicitudEdificioActual.getIdUsuario().getCorreoUsuario()+" mantenimiento@cfiprovidencia.com", subject, mensaje.toString());  
    }
    private void respuestaSolicitudAuditorio() {
       
        String fecha3;
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        fecha3 = fecha.format(solicitudEdificioActual.getFechaAlquiler());
        String fechaFin;
        DateFormat fechaFinal = new SimpleDateFormat("dd/MM/yyyy");
        if(solicitudEdificioActual.getFechaFinAlquiler() ==null){
            fechaFin = fechaFinal.format(solicitudEdificioActual.getFechaAlquiler());
        }else{
            fechaFin = fechaFinal.format(solicitudEdificioActual.getFechaFinAlquiler());
        }      
        String horaInicio;
        DateFormat fecha1 = new SimpleDateFormat("hh:mma");
        horaInicio = fecha1.format(solicitudEdificioActual.getHoraInicioAlquiler());
        String horaFin;
        DateFormat fecha2 = new SimpleDateFormat("hh:mma");
        horaFin = fecha2.format(solicitudEdificioActual.getHoraFinAlquiler());
       
        String subject = "RESPUESTA SOLICITUD DE: "+solicitudEdificioActual.getIdSeccion().getNombreSeccion().toUpperCase()+", NUMERO DE TICKET #"+ solicitudEdificioActual.getIdSolicitudEdificio()+"CFIPRS-I" ;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET: #");
        mensaje.append(solicitudEdificioActual.getIdSolicitudEdificio());
        mensaje.append("CFIPRS-I");
        mensaje.append("\nUSUARIO QUE REALIZO LA RESERVA: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nSECCIÓN: ");
        mensaje.append(solicitudEdificioActual.getIdSeccion().getNombreSeccion().toUpperCase());
        mensaje.append("\nBLOQUE: ");
        mensaje.append(solicitudEdificioActual.getIdSeccion().getIdBloque().getNombreBloque().toUpperCase());
        mensaje.append("\nDESCRIPCIÓN DE LA RESERVA: ");
        mensaje.append(solicitudEdificioActual.getDescripcion().toUpperCase());
        mensaje.append("\nFECHA DE INICIO DE LA RESERVA: ");
        mensaje.append(fecha3);
        mensaje.append("\nFECHA QUE FINALIZA DE LA RESERVA: ");
        mensaje.append(fechaFin);
        mensaje.append("\nHORA INICIO DE LA RESERVA: ");
        mensaje.append(horaInicio);
        mensaje.append("\nHORA FIN DE LA RESERVA: ");
        mensaje.append(horaFin);
        mensaje.append("\n\n----------------------------------------------------\n ");
        mensaje.append("\nESTADO SOLICITUD AUDITORIO: ");
        mensaje.append(solicitudEdificioActual.getIdEstadoSolicitudEdificio().getNombre().toUpperCase());
        mensaje.append("\nRESPUESTA DEL AUDITORIO: ");
        mensaje.append(respuestaSolicitudActual.getDescripcion());
        mensaje.append("\n\n----------------------------------------------------\n ");
       
        sendMail(solicitudEdificioActual.getIdUsuario().getCorreoUsuario()+" mantenimiento@cfiprovidencia.com", subject, mensaje.toString());  
    }
    private void salaCanceladaMensaje() {
       
        String fecha3;
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        fecha3 = fecha.format(solicitudEdificioActual.getFechaAlquiler());
        String fechaFin;
        DateFormat fechaFinal = new SimpleDateFormat("dd/MM/yyyy");
        if(solicitudEdificioActual.getFechaFinAlquiler() ==null){
            fechaFin = fechaFinal.format(solicitudEdificioActual.getFechaAlquiler());
        }else{
            fechaFin = fechaFinal.format(solicitudEdificioActual.getFechaFinAlquiler());
        }      
        String horaInicio;
        DateFormat fecha1 = new SimpleDateFormat("hh:mma");
        horaInicio = fecha1.format(solicitudEdificioActual.getHoraInicioAlquiler());
        String horaFin;
        DateFormat fecha2 = new SimpleDateFormat("hh:mma");
        horaFin = fecha2.format(solicitudEdificioActual.getHoraFinAlquiler());
       
        String subject = "RESERVACIÓN CANCELADA!! SALA DE: "+solicitudEdificioActual.getIdSeccion().getNombreSeccion().toUpperCase()+", NUMERO DE TICKET #"+ solicitudEdificioActual.getIdSolicitudEdificio()+"CFIPRS-I" ;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET: #");
        mensaje.append(solicitudEdificioActual.getIdSolicitudEdificio());
        mensaje.append("CFIPRS-I");
        mensaje.append("\nUSUARIO QUE REALIZO LA RESERVA: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nSECCIÓN: ");
        mensaje.append(solicitudEdificioActual.getIdSeccion().getNombreSeccion().toUpperCase());
        mensaje.append("\nBLOQUE: ");
        mensaje.append(solicitudEdificioActual.getIdSeccion().getIdBloque().getNombreBloque().toUpperCase());
        mensaje.append("\nDESCRIPCIÓN DE LA RESERVA: ");
        mensaje.append(solicitudEdificioActual.getDescripcion().toUpperCase());
         mensaje.append("\nFECHA DE INICIO DE LA RESERVA: ");
        mensaje.append(fecha3);
        mensaje.append("\nFECHA QUE FINALIZA DE LA RESERVA: ");
        mensaje.append(fechaFin);
        mensaje.append("\nHORA INICIO DE LA RESERVA: ");
        mensaje.append(horaInicio);
        mensaje.append("\nHORA FIN DE LA RESERVA: ");
        mensaje.append(horaFin);
        mensaje.append("\n\n----------------------------------------------------\n ");
        mensaje.append("\nLA SALA FUE CANCELADA POR EL USUARIO QUE LA RESERVO ");
        mensaje.append("\n\n----------------------------------------------------\n ");
       
        sendMail("auxsistemas2@cfiprovidencia.com"+" mantenimiento@cfiprovidencia.com", subject, mensaje.toString());  
    }

}
