/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.DiagnosticoReporteSiesa;
import com.proyectoCFIP.entities.EstadoCronograma;
import com.proyectoCFIP.entities.EstadoTicket;
import com.proyectoCFIP.entities.ReporteSiesa;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.DiagnosticoReporteSiesaFacade;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.ReporteSiesaFacade;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class ReporteSiesaController implements Serializable{

    @EJB
    private ReporteSiesaFacade reporteSiesaFacade;
    private ReporteSiesa reporteSiesaActual;
    private List<ReporteSiesa> reporteSiesaList;
    private Usuario usuarioActual;
    public String nombreImagen;
    @EJB
    private DiagnosticoReporteSiesaFacade diagnosticoReporteSiesaFacade;
    private DiagnosticoReporteSiesa diagnosticoReporteSiesaActual;
    private List<DiagnosticoReporteSiesa> diagnosticoReporteSiesaList;
    @EJB
    EmailSessionBean emailBean;
    
    
    public ReporteSiesaController() {
    }

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

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    
    public List<ReporteSiesa> getReporteSiesaListSinDiag() {
        return reporteSiesaList = getReporteSiesaFacade().consultaReporteEstadoSinDiagnosticar(new EstadoTicket(1, "Abierto"), new EstadoTicket(2, "Siesa IT"));
    }

    public DiagnosticoReporteSiesaFacade getDiagnosticoReporteSiesaFacade() {
        return diagnosticoReporteSiesaFacade;
    }

    public void setDiagnosticoReporteSiesaFacade(DiagnosticoReporteSiesaFacade diagnosticoReporteSiesaFacade) {
        this.diagnosticoReporteSiesaFacade = diagnosticoReporteSiesaFacade;
    }

    public DiagnosticoReporteSiesa getDiagnosticoReporteSiesaActual() {
        return diagnosticoReporteSiesaActual;
    }

    public void setDiagnosticoReporteSiesaActual(DiagnosticoReporteSiesa diagnosticoReporteSiesaActual) {
        this.diagnosticoReporteSiesaActual = diagnosticoReporteSiesaActual;
    }

    public List<DiagnosticoReporteSiesa> getDiagnosticoReporteSiesaList() {
        return diagnosticoReporteSiesaList = getDiagnosticoReporteSiesaFacade().findAll();
    }

    public void setDiagnosticoReporteSiesaList(List<DiagnosticoReporteSiesa> diagnosticoReporteSiesaList) {
        this.diagnosticoReporteSiesaList = diagnosticoReporteSiesaList;
    }
    
     public String prepareViewReportes(){
        return "/administrador/modSoporteIT/calendarioMantenimiento/diagnosticos/verDiagnosticosERP";
    }
    
    public String prepareListReportes(){
        return "/administrador/modSoporteIT/calendarioMantenimiento/fallosERP/listaErroresTotales";
    }
    public String prepareListErroresSinDiag(){
        return "/administrador/modSoporteIT/calendarioMantenimiento/fallosERP/listaErrores";
    }
     public String prepareView(){
        return "verError";
    }
     public String prepareCrearDiagnostico(){
         diagnosticoReporteSiesaActual = new DiagnosticoReporteSiesa();
        return "crearDiagnosticoError";
    }
    
    public ReporteSiesa getReporteSiesa(java.lang.Integer id) {
        return getReporteSiesaFacade().find(id);
    }
    public String prepareCreate(){
        reporteSiesaActual = new ReporteSiesa();
        return "/usuario/modSoporteSiesa/reportarFallo/crearFallo";
    }
    public String prepareListEstadoTicket(){
        return "/usuario/modSoporteSiesa/ticketsValoraciones/listaEstadoTicket";
    }
    
    public void recargarLista(){
        reporteSiesaList = null;
    }
    public List<DiagnosticoReporteSiesa> getListaDiagnosticoTicket() {
        return diagnosticoReporteSiesaList= getDiagnosticoReporteSiesaFacade().consultaTicket(reporteSiesaActual);
    }
    
    public void prepareViewDiagnostico(ActionEvent event) {
        reporteSiesaActual = new ReporteSiesa();
        reporteSiesaActual = (ReporteSiesa) event.getComponent().getAttributes().get("reporte");
        diagnosticoReporteSiesaList = new ArrayList<>();
        //listaActividadAprendizaje=actividadActual.getActividadAprendizajeList(); 
    }
    public String viewValoracionSiesa(){
        if(reporteSiesaActual.getIdEstadoTicket().equals(new EstadoTicket(3, "Sin valoracion"))){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Valora el servicio prestado", "Por favor valora el servicio prestado para cerrar correctamente el ticket.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "crearValoracionTicket"; 
        }else if(reporteSiesaActual.getIdEstadoTicket().equals(new EstadoTicket(4, "Cerrado"))){
            return "verValoracionTicket"; 
        }else{
            addErrorMessage("Ticket no diagnosticado"  , "Para valorar el servicio debes esperar a que nuestros técnicos diagnostiquen el ticket");
            return null;
        }
    }
    
    public String addDiagnosticoFallo(){
        diagnosticoReporteSiesaActual.setIdReporteSiesa(reporteSiesaActual);
        diagnosticoReporteSiesaActual.setIdUsuario(usuarioActual);
        getDiagnosticoReporteSiesaFacade().create(diagnosticoReporteSiesaActual);
        reporteSiesaActual.setIdEstadoTicket(new EstadoTicket(3, "Sin valoracion"));
        reporteSiesaActual.setEstadoReporte(true);
        update();
        addSuccessMessage("Diagnóstico guardado", "El diagnóstico ha sido realizado correctamente");
        sendMailDiagnostico();
        recargarLista();
        return "listaErrores";
    }
       public String crearValoracionTicket(){
      try {
            reporteSiesaActual.setIdEstadoTicket(new EstadoTicket(4, "Cerrado"));
            update();
            recargarLista();
            addSuccessMessage("Valoración guardada", "El ticket ha sido cerrado correctamente");
            return "verValoracionTicket";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "verValoracionTicket";
        }  
    }
    
     public void update() {
        try {
            getReporteSiesaFacade().edit(reporteSiesaActual);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }
    public String add(){
         try {
            reporteSiesaActual.setFechaReporte(new Date());
            reporteSiesaActual.setEstadoReporte(false);
            reporteSiesaActual.setIdEstadoTicket(new EstadoTicket(1, "Abierto"));
            reporteSiesaActual.setIdUsuario(usuarioActual);
            getReporteSiesaFacade().create(reporteSiesaActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Su fallo ha sido registrado", "Su fallo con numero de ticket "+reporteSiesaActual.getIdReporteSiesa()+"ERP pronto será atendido por alguno de nuestros técnicos.");
            recargarLista();
            sendMailRegistroTec();
            sendMailRegistroUser();
            return "/usuario/modSoporteSiesa/ticketsValoraciones/listaEstadoTicket";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
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
        reporteSiesaActual.setImagen(data);
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
    
    @FacesConverter(forClass = ReporteSiesa.class)
    public static class ReporteSiesaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReporteSiesaController controller = (ReporteSiesaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reporteSiesaController");
            return controller.getReporteSiesa(getKey(value));
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
            if (object instanceof ReporteSiesa) {
                ReporteSiesa o = (ReporteSiesa) object;
                return getStringKey(o.getIdReporteSiesa());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ReporteSiesa.class.getName());
            }
        }

    }
    public List<ReporteSiesa> getReporteSiesaUserList(){
        reporteSiesaList = getReporteSiesaFacade().consultaReporteEstadoUser(usuarioActual);
        return reporteSiesaList;
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
        String imagen = "";
        if (reporteSiesaActual.getImagen()== null){
            imagen = "NO";
        }else{
            imagen = "SI";
        }
        
        String subject = "FALLO REPORTADO DEL ERP"+", FALLO REPORTADO TICKET #"+ reporteSiesaActual.getIdReporteSiesa()+"CFIPERP" ;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(reporteSiesaActual.getIdReporteSiesa());
        mensaje.append("CFIPERP");
        mensaje.append("\nUSUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nTIPO DE ERROR: ");
        mensaje.append(reporteSiesaActual.getIdTipoError().getNombreError().toUpperCase());
        mensaje.append("\nDESCRIPCIÓN DEL FALLO: ");
        mensaje.append(reporteSiesaActual.getDescripcionReporte().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(reporteSiesaActual.getFechaReporte().toLocaleString());
        mensaje.append("\nCAPTURA DE PANTALLA: ");
        mensaje.append(imagen);
       
        sendMail("auxsistemas2@cfiprovidencia.com "+" "+" auxsistemas@cfiprovidencia.com "+" "+" sistemas@cfiprovidencia.com ", subject, mensaje.toString());  
    }
     
    private void sendMailRegistroUser() {
            String captura="";
            if(reporteSiesaActual.getImagen()== null){
                captura="No";
            }
            else{
                captura="Si";
            }
        String subject = "SU FALLO CON NUMERO DE TICKET #" + reporteSiesaActual.getIdReporteSiesa()+"CFIPERP";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(reporteSiesaActual.getIdReporteSiesa());
        mensaje.append("CFIPERP");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL FALLO REPORTADO");
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nTIPO DE ERROR: ");
        mensaje.append(reporteSiesaActual.getIdEstadoTicket().getNombre().toUpperCase());
        mensaje.append("\nDESCRIPCIÓN DEL FALLO: ");
        mensaje.append(reporteSiesaActual.getDescripcionReporte().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(reporteSiesaActual.getFechaReporte().toLocaleString());
        mensaje.append("\nUSUARIO QUE REALIZO EL REPORTE: ");
        mensaje.append(usuarioActual.toString().toUpperCase());
        mensaje.append("\nCAPTURA DE PANTALLA: ");
        mensaje.append(captura.toUpperCase());
       
        mensaje.append("\n\nPRONTO UNO DE NUESTROS TECNICOS ESTARA ATENDIENDO SU SOLICITUD.");
        sendMail(reporteSiesaActual.getIdUsuario().getCorreoUsuario(), subject, mensaje.toString());
    }
    
    private void sendMailDiagnostico() {
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
    }
    
}
