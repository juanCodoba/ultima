/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.FormatoSaludOcupacional;
import com.proyectoCFIP.sessions.FormatoSaludOcupacionalFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Luis Carlos Cabal
 */
@Named(value = "formatoSaludOcupacionalController")
@SessionScoped
public class FormatoSaludOcupacionalController implements Serializable {

   @EJB
    private FormatoSaludOcupacionalFacade formatoSaludOcupacionalFacade;
    private FormatoSaludOcupacional formatoSaludOcupacionalActual;
    private List<FormatoSaludOcupacional> listFormatoSaludOcupacional;
    private String nombreDocumento;
    public FormatoSaludOcupacionalController() {
    }

    public FormatoSaludOcupacionalFacade getFormatoSaludOcupacionalFacade() {
        return formatoSaludOcupacionalFacade;
    }

    public void setFormatoSaludOcupacionalFacade(FormatoSaludOcupacionalFacade formatoSaludOcupacionalFacade) {
        this.formatoSaludOcupacionalFacade = formatoSaludOcupacionalFacade;
    }

    public FormatoSaludOcupacional getFormatoSaludOcupacionalActual() {
        return formatoSaludOcupacionalActual;
    }

    public void setFormatoSaludOcupacionalActual(FormatoSaludOcupacional formatoSaludOcupacionalActual) {
        this.formatoSaludOcupacionalActual = formatoSaludOcupacionalActual;
    }

    public List<FormatoSaludOcupacional> getListFormatoSaludOcupacional() {
        return listFormatoSaludOcupacional = getFormatoSaludOcupacionalFacade().findAll();
    }

    public void setListFormatoSaludOcupacional(List<FormatoSaludOcupacional> listFormatoSaludOcupacional) {
        this.listFormatoSaludOcupacional = listFormatoSaludOcupacional;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }
    
    
    public String prepareCreate(){
        formatoSaludOcupacionalActual = new FormatoSaludOcupacional();
        return "/administrador/modSaludOcupacional/formato/crear";
    }
    public String prepareEdit(){
        return "/administrador/modSaludOcupacional/formato/editar";
    }
    public String prepareView(){
        return "";
    }
    public String prepareList(){
        return "/usuario/modSaludOcupacional/formato/lista";
    }
    public String update(){
         try {
            getFormatoSaludOcupacionalFacade().edit(formatoSaludOcupacionalActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo editado","El archivo fue editado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            formatoSaludOcupacionalActual = null;
            return "/usuario/modSaludOcupacional/formato/lista";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Archivo no editado","El archivo no fue editado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
    }
    public String add(){
        try {
            getFormatoSaludOcupacionalFacade().create(formatoSaludOcupacionalActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo guardado","El archivo fue guardado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            formatoSaludOcupacionalActual = null;
            return "/usuario/modSaludOcupacional/formato/lista";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Archivo no guardado","El archivo no fue guardado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
    }
    public void delete(){
    }
    
    
    //subir documento//
    
    private String getRandomDocumentoName() {
        int i = (int) (Math.random() * 10000000);
        return String.valueOf(i);
    }
       
    public void guardarDocumento(FileUploadEvent event) throws IOException {
        nombreDocumento = getRandomDocumentoName();
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        formatoSaludOcupacionalActual.setFormato(data);
         
       
        
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" +
                                    File.separator + "images" + File.separator + "photocam" + File.separator + nombreDocumento + ".jpeg";
         
        FileImageOutputStream imageOutput;
       
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo cargado","El archivo fue cargado correctamente");
             RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        catch(IOException e) {
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo cargado","El archivo fue cargado correctamente");
             RequestContext.getCurrentInstance().showMessageInDialog(message);
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
    
            private StreamedContent archivoDescarga;

        public StreamedContent getArchivoDescarga() throws FileNotFoundException {
        try {
            if (formatoSaludOcupacionalActual.getFormato()== null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe documento", "El documento no tiene acceso");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return null;
            } else {
                InputStream stream = new FileInputStream(formatoSaludOcupacionalActual.getFormato().toString());
                return archivoDescarga = new DefaultStreamedContent(stream, "application/pdf", "file.pdf");
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe documento", "El documento no tiene acceso");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
    }
}
