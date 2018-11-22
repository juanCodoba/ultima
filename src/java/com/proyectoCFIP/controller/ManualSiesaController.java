/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Documentos;
import com.proyectoCFIP.entities.ManualSiesa;
import com.proyectoCFIP.entities.SubprocesoSuit;
import com.proyectoCFIP.sessions.ManualSiesaFacade;
import com.proyectoCFIP.sessions.SubprocesoSuitFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
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
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class ManualSiesaController implements Serializable {

    @EJB
    private ManualSiesaFacade manualSiesaFacade;
    @EJB
    private SubprocesoSuitFacade subprocesoSuitFacade;
    private ManualSiesa manualSiesaActual;
    private RetornoCampos reporteCampos;
    private List<ManualSiesa> manualSiesaList;
    private List<RetornoCampos> retornoCamposList;
    private List<SubprocesoSuit> subprocesoSuitList;
    private String nombreManual = "";
    private String cod;

    public ManualSiesaController() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public ManualSiesaFacade getManualSiesaFacade() {
        return manualSiesaFacade;
    }

    public void setManualSiesaFacade(ManualSiesaFacade manualSiesaFacade) {
        this.manualSiesaFacade = manualSiesaFacade;
    }

    public ManualSiesa getManualSiesaActual() {
        return manualSiesaActual;
    }

    public void setManualSiesaActual(ManualSiesa manualSiesaActual) {
        this.manualSiesaActual = manualSiesaActual;
    }

    public List<ManualSiesa> getManualSiesaList() {
        manualSiesaList = getManualSiesaFacade().findAll();
        return manualSiesaList;
    }

    public void setManualSiesaList(List<ManualSiesa> manualSiesaList) {
        this.manualSiesaList = manualSiesaList;
    }

    public SubprocesoSuitFacade getSubprocesoSuitFacade() {
        return subprocesoSuitFacade;
    }

    public void setSubprocesoSuitFacade(SubprocesoSuitFacade subprocesoSuitFacade) {
        this.subprocesoSuitFacade = subprocesoSuitFacade;
    }

    public void ListaCamposManual() {
        manualSiesaActual = new ManualSiesa(getCod());
    }

    public void llenarLista() {
        subprocesoSuitList = getSubprocesoSuitFacade().consultaSubProcesoSuit(manualSiesaActual.getIdSuit());
    }

    public List<SubprocesoSuit> getListaPorSuit() {
        return subprocesoSuitList;
    }

    public String prepareCreate() {
        manualSiesaActual = new ManualSiesa();
        return "/administrador/modDocumentoSiesa/crearManualSiesa";
    }

    public String prepareEdit() {
        return "/administrador/modDocumentoSiesa/editarManualSiesa";
    }

    public String deleteManual() {
        try {
            getManualSiesaFacade().remove(manualSiesaActual);
            addSuccessMessage("Archivo Eliminado", manualSiesaActual.getNombre());
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Archivo no Eliminado", "El archivo no fue eliminado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        return "listaDocumentoSiesa";
    }

    public String prepareView() {
        return "";
    }

    public String prepareList() {
        recargarLista();
        return "";
    }

    public String addManual() {
        try {
            getManualSiesaFacade().create(manualSiesaActual);
            addSuccessMessage("Documento guardado", manualSiesaActual.getNombre());
            return "/usuario/modDocumentoSiesa/listaDocumentoSiesa";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String updateManual() {
        try {
            getManualSiesaFacade().edit(manualSiesaActual);
            addSuccessMessage("Documento actualizado", manualSiesaActual.getNombre());
            return "/usuario/modDocumentoSiesa/listaDocumentoSiesa";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    private void recargarLista() {
        manualSiesaList = null;
    }

    public void validarCodManual(FacesContext contex, UIComponent component, Object value) throws ValidatorException {
        ManualSiesa codConsulta = getManualSiesaFacade().findByCodManual((String) value);
        if (codConsulta != null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Codigo del manual repetido", "El codigo del manual ya se encuentra registrado, favor intente con otro"));
        } else {
            String codigo = (String) value;
            manualSiesaActual.setCodigo(codigo);
        }
    }
    //Guardar documento

    private String getRandomDocumentoName() {
        int i = (int) (Math.random() * 10000000);
        return String.valueOf(i);
    }

    public String getNombreManual() {
        return nombreManual;
    }

    public void setNombreManual(String nombreManual) {
        this.nombreManual = nombreManual;
    }

//    public void guardarManual(FileUploadEvent event) throws IOException {
//        nombreManual = getRandomDocumentoName();
//        UploadedFile file = event.getFile();
//        byte[] data = IOUtils.toByteArray(file.getInputstream());
//        manualSiesaActual.setManual(data);
//        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//        String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo"
//                + File.separator + "images" + File.separator + "photocam" + File.separator + nombreManual + ".pdf";
//
//        FileImageOutputStream imageOutput;
//        try {
//            imageOutput = new FileImageOutputStream(new File(newFileName));
//            imageOutput.write(data, 0, data.length);
//            imageOutput.close();
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo cargado", "El archivo fue cargado correctamente");
//            RequestContext.getCurrentInstance().showMessageInDialog(message);
//        } catch (IOException e) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo cargado", "El archivo fue cargado correctamente");
//            RequestContext.getCurrentInstance().showMessageInDialog(message);
//        }
//    }

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

    public void cargarFichaLogo(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        
            String newFileName = "\\\\172.16.0.241\\Volume_1\\03siesaERP\\" + manualSiesaActual.getIdSuit().getNombreSuit()+"\\" + "-" +manualSiesaActual.getNombre() + ".pdf";
            //String newFileName = "/root/alojamientoFichasImg//02FICHASTECNICAS//" + manualSiesaActual.getNombre().toUpperCase() + "\\" + manualSiesaActual.getCodigo() + "-manual.pdf";

            manualSiesaActual.setManual(newFileName);
            FileImageOutputStream imageOutput;
            try {
                imageOutput = new FileImageOutputStream(new File(newFileName));
                imageOutput.write(data, 0, data.length);
                imageOutput.close();
            } catch (IOException e) {
                throw new FacesException("Error in writing captured image.", e);
            }
        
    }
    private StreamedContent file;

    public void obtenerFichaLogo() throws IOException {
        if (manualSiesaActual.getManual() == null) {
            addErrorMessage("Documento sin acceso", "el documento no tiene acceso");
        } else {
            InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(manualSiesaActual.getManual());
            file = new DefaultStreamedContent(stream, "file/pdf", "downloaded_optimus.pdf");
        }
    }

    private StreamedContent archivoDescarga;

    public StreamedContent getArchivoDescarga() throws FileNotFoundException {
        try {
            if (manualSiesaActual.getManual() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe documento", "El documento no tiene acceso");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return null;
            } else {
                InputStream stream = new FileInputStream(manualSiesaActual.getManual());
                return archivoDescarga = new DefaultStreamedContent(stream, "application/pdf", "file.pdf");
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe documento", "El documento no tiene acceso");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
    }

}
