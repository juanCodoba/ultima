/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.CronogramaActividadesEdificios;
import com.proyectoCFIP.entities.DiagnosticoActividadEdificios;
import com.proyectoCFIP.entities.EstadoActividad;
import com.proyectoCFIP.entities.TipoActividad;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.CronogramaActividadesEdificiosFacade;
import com.proyectoCFIP.sessions.DiagnosticoActividadEdificiosFacade;
import com.proyectoCFIP.sessions.EmailSessionBean;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
public class CronogramaActividadesEdificiosController implements Serializable{

    @EJB
    private CronogramaActividadesEdificiosFacade cronogramaActividadesFacade;
    private CronogramaActividadesEdificios cronogramaActividadesActual;
    private List<CronogramaActividadesEdificios> cronogramaActividadesList;
    private Usuario usuarioActual;
    private String nombreImagen;
    private TipoActividad tipoActividad;
    private String descripcion;
    private Date fechaActual;
    @EJB
    private DiagnosticoActividadEdificiosFacade diagnosticoActividadFacade;
    private DiagnosticoActividadEdificios diagnosticoActividadActual;
    private List<DiagnosticoActividadEdificios> diagnosticoActividadList;
    private String ticket;
    private String stringTipoActividad;
    @EJB
    EmailSessionBean emailBean;
    public Date fechaParametro1;
    public Date fechaParametro2;
    
    
    public CronogramaActividadesEdificiosController() {
    }

    public CronogramaActividadesEdificiosFacade getCronogramaActividadesFacade() {
        return cronogramaActividadesFacade;
    }

    public void setCronogramaActividadesFacade(CronogramaActividadesEdificiosFacade cronogramaActividadesFacade) {
        this.cronogramaActividadesFacade = cronogramaActividadesFacade;
    }

    public CronogramaActividadesEdificios getCronogramaActividadesActual() {
        return cronogramaActividadesActual;
    }

    public void setCronogramaActividadesActual(CronogramaActividadesEdificios cronogramaActividadesActual) {
        this.cronogramaActividadesActual = cronogramaActividadesActual;
    }

    public List<CronogramaActividadesEdificios> getCronogramaActividadesListDaños() {
        return cronogramaActividadesList = getCronogramaActividadesFacade().consultaTicketDaños(tipoActividad, usuarioActual);
    }
    public List<CronogramaActividadesEdificios> getListTipoDaños(){
         return cronogramaActividadesList = getCronogramaActividadesFacade().tipoActividad(new TipoActividad(1, "Daño de la infraestructura"));
    }
    public List<CronogramaActividadesEdificios> getListInconsistenciaAseos(){
         return cronogramaActividadesList = getCronogramaActividadesFacade().tipoActividad(new TipoActividad(2, "Inconsistencia de aseos"));
    }
    
    public List<CronogramaActividadesEdificios> getCronogramaActividadesListIncoAseo() {
        return cronogramaActividadesList = getCronogramaActividadesFacade().consultaTicketDaños(new TipoActividad(2, "Inconsistencia de aseos"), usuarioActual);
    }
   
    public void setCronogramaActividadesList(List<CronogramaActividadesEdificios> cronogramaActividadesList) {
        this.cronogramaActividadesList = cronogramaActividadesList;
    }
     public CronogramaActividadesEdificios getCronogramaActividadesEdificios(java.lang.Integer id) {
        return getCronogramaActividadesFacade().find(id);
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(TipoActividad tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public DiagnosticoActividadEdificiosFacade getDiagnosticoActividadFacade() {
        return diagnosticoActividadFacade;
    }

    public void setDiagnosticoActividadFacade(DiagnosticoActividadEdificiosFacade diagnosticoActividadFacade) {
        this.diagnosticoActividadFacade = diagnosticoActividadFacade;
    }

    public DiagnosticoActividadEdificios getDiagnosticoActividadActual() {
        return diagnosticoActividadActual;
    }

    public void setDiagnosticoActividadActual(DiagnosticoActividadEdificios diagnosticoActividadActual) {
        this.diagnosticoActividadActual = diagnosticoActividadActual;
    }
     public List<DiagnosticoActividadEdificios> getListaDiagnosticoTicket() {
        return getDiagnosticoActividadFacade().consultaTicket(cronogramaActividadesActual);
    }

    public List<DiagnosticoActividadEdificios> getDiagnosticoActividadList() {
        return diagnosticoActividadList = getDiagnosticoActividadFacade().consultaDiagnosticoAseos(new TipoActividad(3));
    }
    public List<DiagnosticoActividadEdificios> getDiagnosticoActividadAseoList() {
        return diagnosticoActividadList = getDiagnosticoActividadFacade().consultaDiagnosticoAseos(new TipoActividad(2, "Inconsistencia de aseos"));
    }
      public List<DiagnosticoActividadEdificios> getDiagnosticoActividadDañosList() {
        return diagnosticoActividadList = getDiagnosticoActividadFacade().consultaDiagnosticoAseos(new TipoActividad(1, "Daño de la infraestructura"));
    }
    public void setDiagnosticoActividadList(List<DiagnosticoActividadEdificios> diagnosticoActividadList) {
        this.diagnosticoActividadList = diagnosticoActividadList;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getStringTipoActividad() {
        return stringTipoActividad;
    }

    public void setStringTipoActividad(String stringTipoActividad) {
        this.stringTipoActividad = stringTipoActividad;
    }
    
    
    
    

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    public List<CronogramaActividadesEdificios> getActividadesProgramadasList(){
        return getCronogramaActividadesFacade().consultaCronograma(new TipoActividad(3,"Actividad programada"), new EstadoActividad(1, "Abierto"));    
    }
    public List<CronogramaActividadesEdificios> getActividadesDañosList(){
        return getCronogramaActividadesFacade().consultaCronograma(new TipoActividad(1,"Daño de la infraestructura"), new EstadoActividad(1, "Abierto"));
    }
    public List<CronogramaActividadesEdificios> getListaReporteDaños(){
        cronogramaActividadesList = new ArrayList<>();
        //  return listaCronogramaMantenimientos = getCronogramaMantenimientosFacade().findAll();
        return cronogramaActividadesList = getCronogramaActividadesFacade().consultaDaños(fechaParametro1, fechaParametro2);
    }
    public List<CronogramaActividadesEdificios> getInconsistenciaAseosList(){
        return cronogramaActividadesList=  getCronogramaActividadesFacade().consultaCronograma(new TipoActividad(2,"Inconsistencia de aseos"), new EstadoActividad(1, "Abierto"));

    }
    
    public void addDiagnosticoActividad(){
        diagnosticoActividadActual.setIdCronogramaActividadesEdificios(cronogramaActividadesActual);
        getDiagnosticoActividadFacade().create(diagnosticoActividadActual);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket Cerrado", "El ticket fue cerrado correctamente");
        cronogramaActividadesActual.setEstado(true);
        cronogramaActividadesActual.setIdEstadoActividad(new EstadoActividad(2));
        updateActividad();
        cronogramaActividadesList= null;
        getActividadesDañosList();
        getActividadesProgramadasList();
        getInconsistenciaAseosList();
        sendMailDiagnostico();
    }
    
    public void updateActividad() {
        try {
            getCronogramaActividadesFacade().edit(cronogramaActividadesActual);
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }
    
    public String addReporteDaño(){
         try {
            cronogramaActividadesActual.setFechaReporte(new Date());
            cronogramaActividadesActual.setHoraReporte(new Date());
            cronogramaActividadesActual.setEstado(false);
            cronogramaActividadesActual.setIdEstadoActividad(new EstadoActividad(1));
            cronogramaActividadesActual.setIdTipoActividad(new TipoActividad(1, "DAÑO DE INFRAESTRUCTURA"));
            cronogramaActividadesActual.setIdUsuario(usuarioActual);
            getCronogramaActividadesFacade().create(cronogramaActividadesActual);
            ticket="CFIPRD-I";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Su daño ha sido registrado", "Su daño con numero de ticket "+cronogramaActividadesActual.getIdCronogramaActividadesEdificios()+ticket +" pronto será atendido.");
            recargarLista();
            sendMailRegistroTec();
            sendMailRegistroUser();
            return "/usuario/modManteEdificios/estadoSolicitudes/listaEstadoSolicitud";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String crearValoracionTicket(){
      try {
            cronogramaActividadesActual.setIdEstadoActividad(new EstadoActividad(3));
            getCronogramaActividadesFacade().edit(cronogramaActividadesActual);
            addSuccessMessage("Valoración guardada", "El ticket ha sido cerrado correctamente");
            return "verValoracionTicket";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "crearValoracionTicket";
        }  
    }
    public String addActividad(){
         try {
            cronogramaActividadesActual.setEstado(false);
            cronogramaActividadesActual.setIdEstadoActividad(new EstadoActividad(1));
            cronogramaActividadesActual.setIdTipoActividad(new TipoActividad(3));
            getCronogramaActividadesFacade().create(cronogramaActividadesActual);
            recargarLista();
            //ticket="CFIPMC-C";
            //sendMailRegistroTec();
            //sendMailRegistroUser();
            init();
            addSuccessMessage("Actividad creada", "Se acabo de agregar una actividad al calendario");
            return "verCalendarioActividades";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String addReporteAseo(){
         try {
            ticket="CFIPIA-I";
            cronogramaActividadesActual.setFechaReporte(new Date());
            cronogramaActividadesActual.setHoraReporte(new Date());
            cronogramaActividadesActual.setEstado(false);
            cronogramaActividadesActual.setIdEstadoActividad(new EstadoActividad(1));
            cronogramaActividadesActual.setIdTipoActividad(new TipoActividad(2, "INCONSISTENCIA DE ASEO"));
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Su inconsistencia ha sido registrada", "Su inconsistencia con numero de ticket "+cronogramaActividadesActual.getIdCronogramaActividadesEdificios()+ticket +" pronto será atendido.");
            cronogramaActividadesActual.setIdUsuario(usuarioActual);
            getCronogramaActividadesFacade().create(cronogramaActividadesActual);
            recargarLista();
            sendMailRegistroTec();
            sendMailRegistroUser();
            return "/usuario/modManteEdificios/estadoSolicitudes/listaEstadoSolicitud";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public void prepareCreateActividad(){
        cronogramaActividadesActual = new CronogramaActividadesEdificios();
    }
    public String prepareListActividad(){
        return "/administrador/modManteEdificios/calendarioActividades/actividadesDiarias/listaActividadesDiarias";
    }
    public String prepareListDaños(){
        return "/administrador/modManteEdificios/calendarioActividades/danosInfraestructura/listaDanosInfraestructura";
    }
    public String prepareListInconsistenciaAseo(){
        return "/administrador/modManteEdificios/calendarioActividades/inconsistenciasAseos/listaInconsistenciasAseos";
    }
    public String prepareCreate(){
        cronogramaActividadesActual = new CronogramaActividadesEdificios();
        return "/usuario/modManteEdificios/reportarDano/crearDano";
    }
    public String prepareListDiagnosticosAseos(){
        return "/administrador/modManteEdificios/calendarioActividades/inconsistenciasAseos/listaDiagnosticoAseos";
    }
    public String prepareListDiagnosticosDaños(){
        return "/administrador/modManteEdificios/calendarioActividades/danosInfraestructura/listaDiagnosticoDanos";
    }
    public String prepareListDiagnosticosActividades(){
        return "/administrador/modManteEdificios/calendarioActividades/actividadesDiarias/listaDiagnosticoActividades";
    }
    public String prepareListAnualDaños(){
        ticket = "CFIPRD-I";
        return "/administrador/modManteEdificios/calendarioActividades/danosInfraestructura/listaDanosTotal";
    }
    public String prepareListAnualInconsistenciaAseo(){
        ticket = "CFIPIA-I";
        return "/administrador/modManteEdificios/calendarioActividades/inconsistenciasAseos/listaAseosTotal";
    }
    public void prepareCreateDiagnostico(){
        diagnosticoActividadActual = new DiagnosticoActividadEdificios();
    }
    public String prepareCreateIncidenteAseo(){
        cronogramaActividadesActual = new CronogramaActividadesEdificios();
        return "/usuario/modManteEdificios/incidenteAseo/crearIncidenteAseo";
    }
    public String prepareListEstadoTicketsDaños() {
        stringTipoActividad = "(Daño de la infraestructura)";
        ticket = "CFIPRD-I";
        cronogramaActividadesList = new ArrayList<>();
        tipoActividad = new TipoActividad(1, "Daño de la infraestructura");
        return "/usuario/modManteEdificios/ticketsValoraciones/listaEstadoTicket";
    }
     public String prepareListEstadoTicketsAseos() {
        stringTipoActividad = "(Inconsistencia de aseo)";
        ticket = "CFIPIA-I";
        cronogramaActividadesList = new ArrayList<>();
        tipoActividad = new TipoActividad(2, "Inconsistencia de aseos");        
        return "/usuario/modManteEdificios/ticketsValoraciones/listaEstadoTicket";
    }
    public void recargarLista(){
        cronogramaActividadesList = null;
    }
    
    public void prepareViewDiagnostico(ActionEvent event) {
        cronogramaActividadesActual = new CronogramaActividadesEdificios();
        cronogramaActividadesActual = (CronogramaActividadesEdificios) event.getComponent().getAttributes().get("mantenimiento");
        cronogramaActividadesList = new ArrayList<>();
        //listaActividadAprendizaje=actividadActual.getActividadAprendizajeList(); 
    }
     public String viewValoracionDaño(){
         if(cronogramaActividadesActual.getIdTipoActividad().equals(new TipoActividad(1))){
             ticket="CFIPRD-I";
         }else if(cronogramaActividadesActual.getIdTipoActividad().equals(new TipoActividad(2))){
             ticket="CFIPIA-I";
         }
        
        if(cronogramaActividadesActual.getIdEstadoActividad().equals(new EstadoActividad(2))){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Valora el servicio prestado", "Por favor valora el servicio prestado para cerrar correctamente el ticket.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "/usuario/modManteEdificios/ticketsValoraciones/crearValoracionTicket"; 
        }else if(cronogramaActividadesActual.getIdEstadoActividad().equals(new EstadoActividad(3))){
            return "/usuario/modManteEdificios/ticketsValoraciones/verValoracionTicket"; 
        }else{
            addErrorMessage("Ticket no diagnosticado"  , "Para valorar el servicio debes esperar a que nuestros técnicos diagnostiquen el ticket");
            return null;
        }
       
    }
     public String viewValoracion(){
        if(cronogramaActividadesActual.getIdTipoActividad().equals(new TipoActividad(1))){
             ticket="CFIPRD-I";
        }else if(cronogramaActividadesActual.getIdTipoActividad().equals(new TipoActividad(2))){
             ticket="CFIPIA-I";
        }
        return "/administrador/modManteEdificios/calendarioActividades/ticketsValoraciones/verValoracionTicket"; 
     }
    //imagen
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
        cronogramaActividadesActual.setImagen(data);
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" +
                                    File.separator + "images" + File.separator + "photocam" + File.separator + nombreImagen + ".jpg";
         
        FileImageOutputStream imageOutput;
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proceso terminado", "La imagen ha sido cargada correctamente.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);        
                try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        
        }
        catch(IOException e) {
            throw new FacesException("Error in writing captured image.", e);
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

    @FacesConverter(forClass = CronogramaActividadesEdificios.class)
    public static class CronogramaActividadesEdificiosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CronogramaActividadesEdificiosController controller = (CronogramaActividadesEdificiosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cronogramaActividadesEdificiosController");
            return controller.getCronogramaActividadesEdificios(getKey(value));
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
            if (object instanceof CronogramaActividadesEdificios) {
                CronogramaActividadesEdificios o = (CronogramaActividadesEdificios) object;
                return getStringKey(o.getIdCronogramaActividadesEdificios());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CronogramaActividadesEdificios.class.getName());
            }
        }

    }  

    public Date getFechaActual() {
        return fechaActual = new Date();
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }
    

    private ScheduleModel eventModel;
    private ScheduleModel eventModel2;
    private ScheduleEvent event = new DefaultScheduleEvent();
    DefaultScheduleEvent man= new DefaultScheduleEvent();
 
    public void init() {
        eventModel = new DefaultScheduleModel();
        eventModel2 = new DefaultScheduleModel();
        //Daños en la infraestructura
        for (Iterator<CronogramaActividadesEdificios> it = recargarListaActividadesDaños().iterator(); it.hasNext();) {
            CronogramaActividadesEdificios i = it.next();
            String horaReporte;
            DateFormat fecha1 = new SimpleDateFormat("hh:mma");
            horaReporte = fecha1.format(i.getHoraReporte());
            i.getDescripcion();
            eventModel.addEvent(new DefaultScheduleEvent(" - "+i.getIdUsuario().toString()+"\n - #TICKET "+i.getIdCronogramaActividadesEdificios()+"CFIPRD-I\n - "+i.getIdSeccion().getNombreSeccion().toUpperCase()+"\n - HORA: "+horaReporte,i.getFechaReporte(),i.getHoraReporte(),"DAÑO_INFRAESTRUCTURA"));
            eventModel2.addEvent(new DefaultScheduleEvent(" - "+i.getIdUsuario().toString()+"\n  "+i.getIdCronogramaActividadesEdificios()+"CFIPRD-I\n - "+i.getIdSeccion().getNombreSeccion().toUpperCase()+"\n - HORA: "+horaReporte,i.getFechaReporte(),i.getHoraReporte(),"DAÑO_INFRAESTRUCTURA"));
        }
        //Actividades diarias
        
        for (Iterator<CronogramaActividadesEdificios> it = recargarListaActividadesProgramadas().iterator(); it.hasNext();) {
            CronogramaActividadesEdificios i = it.next();
            String horaReporte;
            DateFormat fecha1 = new SimpleDateFormat("hh:mma");
            horaReporte = fecha1.format(i.getHoraReporte());
            i.getDescripcion();
            eventModel.addEvent(new DefaultScheduleEvent(" - "+i.getIdUsuario().toString()+"\n - "+i.getDescripcion()+"\n - "+i.getIdSeccion().getNombreSeccion().toUpperCase()+"\n - HORA: "+horaReporte,i.getFechaReporte(),i.getHoraReporte(),"ACTIVIDAD_PROGRAMADA"));
            eventModel2.addEvent(new DefaultScheduleEvent(" - "+i.getIdUsuario().toString()+"\n  "+i.getIdCronogramaActividadesEdificios()+"CFIP-A-D\n - "+i.getIdSeccion().getNombreSeccion().toUpperCase()+"\n - HORA: "+horaReporte,i.getFechaReporte(),i.getHoraReporte(),"DAÑO_INFRAESTRUCTURA"));
        }
        //Inconsistencia en los aseos
        for (Iterator<CronogramaActividadesEdificios> it = recargarListaInconsistenciaAseos().iterator(); it.hasNext();) {
            CronogramaActividadesEdificios i = it.next();
            String horaReporte;
            DateFormat fecha1 = new SimpleDateFormat("hh:mma");
            horaReporte = fecha1.format(i.getHoraReporte());
            i.getDescripcion();
            eventModel.addEvent(new DefaultScheduleEvent(" - "+i.getIdUsuario().toString()+"\n - #TICKET "+i.getIdCronogramaActividadesEdificios()+"CFIPIA-I\n - "+i.getIdSeccion().getNombreSeccion().toUpperCase()+"\n - HORA: "+horaReporte,i.getFechaReporte(),i.getHoraReporte(),"INCONSISTENCIA_ASEO"));
            eventModel2.addEvent(new DefaultScheduleEvent(" - "+i.getIdUsuario().toString()+"\n  "+i.getIdCronogramaActividadesEdificios()+"CFIPIA-I\n - "+i.getIdSeccion().getNombreSeccion().toUpperCase()+"\n - HORA: "+horaReporte,i.getFechaReporte(),i.getHoraReporte(),"DAÑO_INFRAESTRUCTURA"));
        }
    };
    public List<CronogramaActividadesEdificios> recargarListaActividadesDaños(){
        cronogramaActividadesList = new ArrayList<>();
        cronogramaActividadesList=  getCronogramaActividadesFacade().consultaCronograma(new TipoActividad(1,"Daño de la infraestructura"), new EstadoActividad(1, "Abierto"));
        return cronogramaActividadesList;
    }
    public List<CronogramaActividadesEdificios> recargarListaActividadesProgramadas(){
        cronogramaActividadesList = new ArrayList<>();
        cronogramaActividadesList=  getCronogramaActividadesFacade().consultaCronograma(new TipoActividad(3,"Actividad programada"), new EstadoActividad(1, "Abierto"));
        return cronogramaActividadesList;
    }
    public List<CronogramaActividadesEdificios> recargarListaInconsistenciaAseos(){
        cronogramaActividadesList = new ArrayList<>();
        cronogramaActividadesList=  getCronogramaActividadesFacade().consultaCronograma(new TipoActividad(2,"Inconsistencia de aseos"), new EstadoActividad(1, "Abierto"));
        return cronogramaActividadesList;
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
    
    public String prepareViewCalendario(){
        init();
        return "/administrador/modManteEdificios/calendarioActividades/verCalendarioActividades";
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    
    
    private void sendMailRegistroTec() {
        String imagen = "";
        String nombreUsuario="";
        String jornada = "";
        String correoJornada =  "";
        if (cronogramaActividadesActual.getImagen()== null){
            imagen = "NO";
        }else{
            imagen = "SI";
        }
        if(cronogramaActividadesActual.getNombreUsuarioReporte()==null){
            nombreUsuario="N/A";
        }else{
            nombreUsuario = cronogramaActividadesActual.getNombreUsuarioReporte().toUpperCase();
        }
       
        if(cronogramaActividadesActual.getIdTipoJornada()==null){
            jornada="N/A";
        }else{
            jornada=cronogramaActividadesActual.getIdTipoJornada().getNombre();
        }
        if(cronogramaActividadesActual.getIdTipoJornada()==null){
            correoJornada="";
        }else{
            correoJornada=cronogramaActividadesActual.getIdTipoJornada().getCorreo();
        }
        
        String subject = "REPORTE DE "+cronogramaActividadesActual.getIdTipoActividad().getNombre().toUpperCase()+", NUMERO DE TICKET #"+ cronogramaActividadesActual.getIdCronogramaActividadesEdificios()+ticket ;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(cronogramaActividadesActual.getIdCronogramaActividadesEdificios());
        mensaje.append(ticket);
        mensaje.append("\nUSUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nNOMBRE DEL USUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(nombreUsuario);
        mensaje.append("\nSECCIÓN: ");
        mensaje.append(cronogramaActividadesActual.getIdSeccion().getNombreSeccion().toUpperCase());
        mensaje.append("\nBLOQUE: ");
        mensaje.append(cronogramaActividadesActual.getIdSeccion().getIdBloque().getNombreBloque().toUpperCase());
        mensaje.append("\nJORNADA DONDE SE REPORTO EL FALLO: ");
        mensaje.append(jornada.toUpperCase());
        mensaje.append("\n\n-------------------------------------\n\n ");
        mensaje.append("\nTIPO DE REPORTE: ");
        mensaje.append(cronogramaActividadesActual.getIdTipoActividad().getNombre().toUpperCase());
        mensaje.append("\nDESCRIPCIÓN DEL REPORTE: ");
        mensaje.append(cronogramaActividadesActual.getDescripcion().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(cronogramaActividadesActual.getFechaReporte().toLocaleString());
        mensaje.append("\nCAPTURA DE PANTALLA: ");
        mensaje.append(imagen);
       
        sendMail("auxsistemas2@cfiprovidencia.com "+" "+" mantenimiento@cfiprovidencia.com "+" "+correoJornada, subject, mensaje.toString());  
    }
    
    private void sendMailRegistroUser() {
            String captura="";
            String nombreUsuario="";
            String correo="";
            if(cronogramaActividadesActual.getImagen()== null){
                captura="No";
            }
            else{
                captura="Si";
            }
            if(cronogramaActividadesActual.getNombreUsuarioReporte()==null){
                nombreUsuario=" ";
            }else{
                nombreUsuario = cronogramaActividadesActual.getNombreUsuarioReporte().toUpperCase();
            }
             if(cronogramaActividadesActual.getCorreoUsuarioReporte()==null){
                correo=" ";
            }else{
                correo = cronogramaActividadesActual.getCorreoUsuarioReporte();
            }
        String subject = "SU REPORTE CON NUMERO DE TICKET #" + cronogramaActividadesActual.getIdCronogramaActividadesEdificios()+ticket;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(cronogramaActividadesActual.getIdCronogramaActividadesEdificios());
        mensaje.append(ticket);
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS REPORTE");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nTIPO DE REPORTE: ");
        mensaje.append(cronogramaActividadesActual.getIdTipoActividad().getNombre().toUpperCase());
        mensaje.append("\nSECCIÓN: ");
        mensaje.append(cronogramaActividadesActual.getIdSeccion().getNombreSeccion().toUpperCase());
        mensaje.append("\nBLOQUE: ");
        mensaje.append(cronogramaActividadesActual.getIdSeccion().getIdBloque().getNombreBloque().toUpperCase());
        mensaje.append("\nDESCRIPCIÓN DEL REPORTE: ");
        mensaje.append(cronogramaActividadesActual.getDescripcion().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(cronogramaActividadesActual.getFechaReporte().toLocaleString());
        mensaje.append("\n\n-------------------------------------\n\n ");
        mensaje.append("\nUSUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nNOMBRE DEL USUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(nombreUsuario);
        mensaje.append("\nCAPTURA DE PANTALLA: ");
        mensaje.append(captura.toUpperCase());
       
        mensaje.append("\n\nPRONTO UNA PERSONA DE LA SECCIÓN DE MANTENIMIENTO DE EDIFICIOS ATENDERA SU CASO.");
        sendMail(cronogramaActividadesActual.getIdUsuario().getCorreoUsuario()+ " "+correo, subject, mensaje.toString());
    }
    
    private void sendMailDiagnostico() {        
        SimpleDateFormat fecha = new SimpleDateFormat("MMM/dd/yyyy");
        String subject = "DIAGNOSTICO DEL TICKET #" + diagnosticoActividadActual.getIdCronogramaActividadesEdificios().getIdCronogramaActividadesEdificios()+"CFIP-D-I";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CODIGO DEL DIAGNOSTICO ");
        mensaje.append(diagnosticoActividadActual.getIdCronogramaActividadesEdificios().getIdCronogramaActividadesEdificios());
        mensaje.append("CFIP-D-I");
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL REPORTE ");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nSECCIÓN: ");
        mensaje.append(cronogramaActividadesActual.getIdSeccion().getNombreSeccion().toUpperCase());
        mensaje.append("\nBLOQUE: ");
        mensaje.append(cronogramaActividadesActual.getIdSeccion().getIdBloque().getNombreBloque().toUpperCase());
        mensaje.append("\nTIPO DE REPORTE: ");
        mensaje.append(diagnosticoActividadActual.getIdCronogramaActividadesEdificios().getIdTipoActividad().getNombre().toUpperCase());
        mensaje.append("\nDESCRIPCIÓN DE LA ACTIVIDAD: ");
        mensaje.append(diagnosticoActividadActual.getIdCronogramaActividadesEdificios().getDescripcion().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(diagnosticoActividadActual.getIdCronogramaActividadesEdificios().getFechaReporte().toLocaleString());
        mensaje.append("\nUSUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(diagnosticoActividadActual.getIdCronogramaActividadesEdificios().getIdUsuario().toString().toUpperCase());
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL DIAGNOSTICO:");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nREVISION: ");
        mensaje.append(diagnosticoActividadActual.getDescripcionRevision().toUpperCase());
        mensaje.append("\nFECHA DE LA REVISION: ");
        mensaje.append(fecha.format(diagnosticoActividadActual.getFechaRevision()));
        mensaje.append("\nDIAGNOSTICO: ");
        mensaje.append(diagnosticoActividadActual.getDescripcionDiagnostico().toUpperCase());
        mensaje.append("\nFECHA QUE SE CERRO EL TICKET: ");
        mensaje.append(fecha.format(diagnosticoActividadActual.getFechaDiagnostico()));
        sendMail(diagnosticoActividadActual.getIdCronogramaActividadesEdificios().getIdUsuario().getCorreoUsuario() +" "+" mantenimiento@cfiprovidencia.com ", subject, mensaje.toString());
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
    public void initReport() throws JRException{
        setFechaParametro1(getFechaParametro1());
        setFechaParametro2(getFechaParametro2());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "images" +
                                    File.separator + "logocfip.png";
            Map parametros = new HashMap();
            parametros.put("parameter1", fechaParametro1);
            parametros.put("parameter2", fechaParametro2);
            parametros.put("logo", newFileName);
        JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(getListaReporteDaños());
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/indicadorDanoInfraestructura.jasper");
       jasperPrint=JasperFillManager.fillReport(reportPath, parametros,beanCollectionDataSource);
    }
   
    // indicadores correctivos
   public void PDF(ActionEvent actionEvent) throws JRException, IOException{
       initReport();
       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
       httpServletResponse.addHeader("Content-disposition", "attachment; filename=IndicadorDanoInfraestructura.pdf");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
       FacesContext.getCurrentInstance().responseComplete();
   }
}
