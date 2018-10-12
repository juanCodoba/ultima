/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.IndicadorSaludAcupacional;
import com.proyectoCFIP.sessions.IndicadorSaludAcupacionalFacade;
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
import javax.faces.FacesException;
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
@Named(value = "indicadorSaludOcupacionalController")
@SessionScoped
public class IndicadorSaludOcupacionalController implements Serializable {

    @EJB
    private IndicadorSaludAcupacionalFacade indicadorSaludOcupacionalFacade;
    private IndicadorSaludAcupacional indicadorSaludOcupacionalActual;
    private List<IndicadorSaludAcupacional> listIndicadorSaludOcupacional;
    private String nombreDocumento;
    public IndicadorSaludOcupacionalController() {
    }

    public IndicadorSaludAcupacionalFacade getIndicadorSaludOcupacionalFacade() {
        return indicadorSaludOcupacionalFacade;
    }

    public void setIndicadorSaludOcupacionalFacade(IndicadorSaludAcupacionalFacade indicadorSaludOcupacionalFacade) {
        this.indicadorSaludOcupacionalFacade = indicadorSaludOcupacionalFacade;
    }

    public IndicadorSaludAcupacional getIndicadorSaludOcupacionalActual() {
        return indicadorSaludOcupacionalActual;
    }

    public void setIndicadorSaludOcupacionalActual(IndicadorSaludAcupacional indicadorSaludOcupacionalActual) {
        this.indicadorSaludOcupacionalActual = indicadorSaludOcupacionalActual;
    }

    public List<IndicadorSaludAcupacional> getListIndicadorSaludOcupacional() {
        return listIndicadorSaludOcupacional = getIndicadorSaludOcupacionalFacade().findAll();
    }

    public void setListIndicadorSaludOcupacional(List<IndicadorSaludAcupacional> listIndicadorSaludOcupacional) {
        this.listIndicadorSaludOcupacional = listIndicadorSaludOcupacional;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }
    
    
    
    
    public String prepareCreate(){
        indicadorSaludOcupacionalActual = new IndicadorSaludAcupacional();
        return "/administrador/modSaludOcupacional/indicador/crear";
    }
    public String prepareEdit(){
        return "/administrador/modSaludOcupacional/indicador/editar";
    }
    public String prepareView(){
        return "";
    }
    public String prepareList(){
        return "/usuario/modSaludOcupacional/indicador/lista";
    }
    
    public String update(){
         try {
            getIndicadorSaludOcupacionalFacade().edit(indicadorSaludOcupacionalActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo editado","El archivo fue guardado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            indicadorSaludOcupacionalActual = null;
            return "/usuario/modSaludOcupacional/indicador/lista";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Archivo no editado","El archivo no fue guardado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
    }
    public String add(){
        try {
            getIndicadorSaludOcupacionalFacade().create(indicadorSaludOcupacionalActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo guardado","El archivo fue guardado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            indicadorSaludOcupacionalActual = null;
            return "/usuario/modSaludOcupacional/indicador/lista";
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
        indicadorSaludOcupacionalActual.setIndicador(data);
         
       
        
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
        private StreamedContent archivoDescarga;

        public StreamedContent getArchivoDescarga() throws FileNotFoundException {
        try {
            if (indicadorSaludOcupacionalActual.getIndicador() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe documento", "El documento no tiene acceso");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return null;
            } else {
                InputStream stream = new FileInputStream(indicadorSaludOcupacionalActual.getIndicador().getContentType());
                return archivoDescarga = new DefaultStreamedContent(stream, "application/pdf", "file.pdf");
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe documento", "El documento no tiene acceso");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
    }
}

