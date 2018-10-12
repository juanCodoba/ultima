/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.controller.util.JsfUtil;
import com.proyectoCFIP.entities.CambioRegistro;
import com.proyectoCFIP.entities.DiagnosticoTicket;
import com.proyectoCFIP.entities.Formatos;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.CambioRegistroFacade;
import com.proyectoCFIP.sessions.DiagnosticoTicketFacade;
import com.proyectoCFIP.sessions.EmailSessionBean;
import com.proyectoCFIP.sessions.FormatosFacade;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class CambioRegistroController implements  Serializable{

    @EJB
    private CambioRegistroFacade cambioRegistroFacade;
    @EJB
    private FormatosFacade formatosFacade;
    @EJB
    EmailSessionBean emailBean;
    @EJB
    private DiagnosticoTicketFacade diagnosticoTicketFacade;
    private CambioRegistro cambioRegistroActual;
    private List<CambioRegistro> listaCambioRegistro;
    private Usuario usuarioActual;
    private DiagnosticoTicket diagnosticoTicketActual;
    
    public CambioRegistroController() {
    }
    
    //GET AND SET

    public DiagnosticoTicketFacade getDiagnosticoTicketFacade() {
        return diagnosticoTicketFacade;
    }

    public void setDiagnosticoTicketFacade(DiagnosticoTicketFacade diagnosticoTicketFacade) {
        this.diagnosticoTicketFacade = diagnosticoTicketFacade;
    }

    public DiagnosticoTicket getDiagnosticoTicketActual() {
        return diagnosticoTicketActual;
    }

    public void setDiagnosticoTicketActual(DiagnosticoTicket diagnosticoTicketActual) {
        this.diagnosticoTicketActual = diagnosticoTicketActual;
    }

    
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    
    
    public CambioRegistroFacade getCambioRegistroFacade() {
        return cambioRegistroFacade;
    }

    public void setCambioRegistroFacade(CambioRegistroFacade cambioRegistroFacade) {
        this.cambioRegistroFacade = cambioRegistroFacade;
    }

    public CambioRegistro getCambioRegistroActual() {
        return cambioRegistroActual;
    }

    public void setCambioRegistroActual(CambioRegistro cambioRegistroActual) {
        this.cambioRegistroActual = cambioRegistroActual;
    }

    public List<CambioRegistro> getListaCambioRegistro() {
        return listaCambioRegistro= getCambioRegistroFacade().findAll();
    }
     public List<CambioRegistro> getListaCambioRegistroSinSolucion() {
        return listaCambioRegistro= getCambioRegistroFacade().consultaCambioRegistroSinSolucionar(false);
    }
    public List<DiagnosticoTicket> getListaDiagnosticoTicket() {
        return getDiagnosticoTicketFacade().findAll();
    }
    public List<Formatos> getListaFormatos() {
        return getFormatosFacade().findAll();
    }
       
    public void setListaCambioRegistro(List<CambioRegistro> listaCambioRegistro) {
        this.listaCambioRegistro = listaCambioRegistro;
    }

    public FormatosFacade getFormatosFacade() {
        return formatosFacade;
    }

    public void setFormatosFacade(FormatosFacade formatosFacade) {
        this.formatosFacade = formatosFacade;
    }
    
    
    public String prepareCreate(){
        cambioRegistroActual = new CambioRegistro();
        return "userCrearCambioReporte";
    }
    public String prepareCreateNuevoFormato(){
        cambioRegistroActual = new CambioRegistro();
        return "userSolicitudNuevoFormato";
    }
    
    public String prepareList(){
        return "/Calidad/AdminCalidad/adminListTicketActualizacionRegistro";
    }
    public String prepareListDiagnostico(){
        return "/Calidad/AdminCalidad/adminListDiagnosticoTicket";
    }
    public String addDiagnostico(){
        diagnosticoTicketActual.setIdUsuario(usuarioActual);
        diagnosticoTicketActual.setIdCambioRegistro(cambioRegistroActual);
        diagnosticoTicketActual.setFechaDiagnostico(new Date());
        getDiagnosticoTicketFacade().create(diagnosticoTicketActual);
        
        cambioRegistroActual.setEstadoCambio(true);
        getCambioRegistroFacade().edit(cambioRegistroActual);
        sendMailDiagPrevUser();
        return "/User/paginaPrincipalCalidad";
    }
    
    public String prepareDiagnostico(){
        diagnosticoTicketActual= new DiagnosticoTicket();
        return "adminCrearDiagnosticoTicket";
    }
    public String addNuevoFormato(){
        try {
            cambioRegistroActual.setFechaCambio(new Date());
            cambioRegistroActual.setIdUsuario(usuarioActual);
            getCambioRegistroFacade().create(cambioRegistroActual);            
            return "paginaPrincipalCalidad";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    
    public String add(){
        try {
            cambioRegistroActual.setFechaCambio(new Date());
            cambioRegistroActual.setIdUsuario(usuarioActual);
            getCambioRegistroFacade().create(cambioRegistroActual);
            sendMailRegistroCalidad();
            sendMailRegistroUser();
            
            return "paginaPrincipalCalidad";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    
         //Guardar documento
    private String nombreDocumento;
    private String getRandomDocumentoName() {
        int i = (int) (Math.random() * 10000000);
        return String.valueOf(i);
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }
    
    public String getNombreDocumento() {
        return nombreDocumento;
    }
     public String volverDocumento(){
        return "adminViewDocumentoInstructivo";
    }
  
     
     public void guardarDocumento(FileUploadEvent event) throws IOException {
        nombreDocumento = getRandomDocumentoName();
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        cambioRegistroActual.setDocumentoDemo(data);
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" +
                                    File.separator + "images" + File.separator + "photocam" + File.separator + nombreDocumento + ".jpeg";
         
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            FacesMessage message = new FacesMessage("Completado", event.getFile().getFileName() + " \nArchivo cargado satisfactoriamente.");
            FacesContext.getCurrentInstance().addMessage(null, message);
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
    
    private void sendMail(String to, String subject, String body) {
        try {
            emailBean.sendMail(to, subject, body);
            JsfUtil.addSuccessMessage("Mensaje Enviado Exitosamente");
        } catch (NamingException | MessagingException ex) {
            JsfUtil.addErrorMessage("Error sending message " + ex.getClass().getName());
        }
    }
     private void sendMailRegistroUser() {
        String subject = "SU REPORTE CON NUMERO DE TICKET #" + cambioRegistroActual.getIdCambioRegistro()+"CFIP-AF-CAL";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(cambioRegistroActual.getIdCambioRegistro());
        mensaje.append("CFIP-AF-CAL");
        mensaje.append("\nDESCRIPCIÓN DEL REPORTE: ");
        mensaje.append(cambioRegistroActual.getDescripcionCambio().toUpperCase());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(cambioRegistroActual.getFechaCambio().toLocaleString());
        
        
        mensaje.append("\n---------------------------------------------------------------------");
        mensaje.append("\nDATOS DEL FORMATO");
        mensaje.append("\n---------------------------------------------------------------------");
        
        mensaje.append("\nCODIGO DEL FORMATO: ");
        mensaje.append(cambioRegistroActual.getIdFormato().getCodigoFormato().toUpperCase());
        mensaje.append("\nNOMBRE DEL FORMATO: ");
        mensaje.append(cambioRegistroActual.getIdFormato().getNombreFormato().toUpperCase());
        mensaje.append("\nPROCESO: ");
        mensaje.append(cambioRegistroActual.getIdFormato().getIdProceso().getNombreProceso().toUpperCase());
         
        mensaje.append("\n\nGRACIAS POR ENVIARNOS SUS SUGERENCIAS, PRONTO ESTAREMOS RESPONDIENDOLAS");
      
        sendMail(cambioRegistroActual.getIdUsuario().getCorreoUsuario(), subject, mensaje.toString());
    }
    
    
    
    private void sendMailRegistroCalidad() {
        String subject = "ACTUALIZACIÓN DE FORMATO TICKET #"+ cambioRegistroActual.getIdCambioRegistro()+"CFIP-AF-CAL" ;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("TICKET # ");
        mensaje.append(cambioRegistroActual.getIdCambioRegistro());
        mensaje.append("CFIP-AF-CAL");
        mensaje.append("\nCODIGO DEL FORMATO: ");
        mensaje.append(cambioRegistroActual.getIdFormato().getCodigoFormato().toUpperCase());
        mensaje.append("\nNOMBRE DEL FORMATO: ");
        mensaje.append(cambioRegistroActual.getIdFormato().getNombreFormato().toUpperCase());
        mensaje.append("\nPROCESO: ");
        mensaje.append(cambioRegistroActual.getIdFormato().getIdProceso().getNombreProceso().toUpperCase());
        mensaje.append("\nSUBPROCESO: ");
        mensaje.append(cambioRegistroActual.getIdFormato().getSubprocesoNombre().toUpperCase());
        mensaje.append("\nDESCRIPCIÓN ACTUALIZACION DE FORMATO: ");
        mensaje.append(cambioRegistroActual.getDescripcionCambio().toUpperCase());
        mensaje.append("\nUSUARIO QUE ENVIO EL REPORTE: ");
        mensaje.append(cambioRegistroActual.getIdUsuario().toString().toUpperCase());
        mensaje.append("\nCORREO DEL USUARIO: ");
        mensaje.append(cambioRegistroActual.getIdUsuario().getCorreoUsuario());
        mensaje.append("\nFECHA Y HORA DEL REPORTE: ");
        mensaje.append(cambioRegistroActual.getFechaCambio().toLocaleString());
       
        
        sendMail("calidad.educativa@cfiprovidencia.com", subject, mensaje.toString());
    }
    
     private void sendMailDiagPrevUser() {
        String subject = "RESPUESTA DE SU TICKET #" + diagnosticoTicketActual.getIdCambioRegistro().getIdCambioRegistro()+"CFIP-AF-CAL";
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("CODIGO DE LA RESPUESTA ");
        mensaje.append(diagnosticoTicketActual.getIdDiagnosticoTicket());
        mensaje.append("CFIPDIAG-CAL");
        
        mensaje.append("\n");
        mensaje.append("\nSU REPORTE FUE:");
        mensaje.append(diagnosticoTicketActual.getIdCambioRegistro().getDescripcionCambio().toUpperCase());
        mensaje.append("\nFECHA Y HORA DE SU REPORTE: ");
        mensaje.append(diagnosticoTicketActual.getIdCambioRegistro().getFechaCambio().toLocaleString());
        
        mensaje.append("\n");
        mensaje.append("\nRESPUESTA: ");
        mensaje.append(diagnosticoTicketActual.getDescripcionDiagnostico().toUpperCase());
        mensaje.append("\nSU SOLICITUD FUE ATENDIDA POR: ");
        mensaje.append(diagnosticoTicketActual.getIdUsuario().toString().toUpperCase());
        mensaje.append("\nFECHA QUE FUE ATENTIDA SU SOLICITUD: ");
        mensaje.append(diagnosticoTicketActual.getFechaDiagnostico().toLocaleString());
        
        
        sendMail(diagnosticoTicketActual.getIdCambioRegistro().getIdUsuario().getCorreoUsuario(), subject, mensaje.toString());
    }
}
