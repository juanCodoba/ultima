/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Documentos;
import com.proyectoCFIP.entities.Formatos;
import com.proyectoCFIP.entities.Procesos;
import com.proyectoCFIP.entities.Subprocesos;
import com.proyectoCFIP.sessions.DocumentosFacade;
import com.proyectoCFIP.sessions.FormatosFacade;
import com.proyectoCFIP.sessions.ProcesosFacade;
import com.proyectoCFIP.sessions.SubprocesosFacade;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import javax.faces.validator.ValidatorException;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class FormatosController {

    @EJB   
    private FormatosFacade formatosFacade; 
    @EJB
    private SubprocesosFacade subprocesosFacade;
    @EJB
    private ProcesosFacade procesosFacade;
    @EJB
    private DocumentosFacade documentosFacade;
    private Subprocesos subProcesosActual;
    private Procesos procesosActual;
    private Formatos formatosActual;
    private Documentos documentoActual;
    private List<Formatos> listaFormatos;
    private List<Formatos> filtros;
    private List<Documentos> listaDocumentos;
    private List<Subprocesos> listaSubprocesos;
    private StreamedContent file;
    public String nombreFormato;
    
    public FormatosController() {
    }

    public FormatosFacade getFormatosFacade() {
        return formatosFacade;
    }

    public void setFormatoFacade(FormatosFacade formatosFacade) {
        this.formatosFacade = formatosFacade;
    }

    public Formatos getFormatosActual() {
        return formatosActual;
    }

    public void setFormatosActual(Formatos formatosActual) {
        this.formatosActual = formatosActual;
    }

    public Documentos getDocumentoActual() {
        return documentoActual;
    }

    public void setDocumentoActual(Documentos documentoActual) {
        this.documentoActual = documentoActual;
    }

    public List<Documentos> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<Documentos> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }
    
    
    public void validarCodRegistro(FacesContext contex, UIComponent component, Object value)throws ValidatorException{
        Formatos codConsulta=getFormatosFacade().findByCodFormato((String)value);
        if(codConsulta != null){
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Codigo de registro repetido","El codigo del registro ya se encuentra registrado, favor intente con otro"));   
        }else{
            String codigo=(String) value;
            formatosActual.setCodigoFormato(codigo);
        }
    }
    public DocumentosFacade getDocumentosFacade() {
        return documentosFacade;
    }

    public void setDocumentosFacade(DocumentosFacade documentosFacade) {
        this.documentosFacade = documentosFacade;
    }
    
      public List<Documentos> ListaDocumentosSelectOne(){
        return getDocumentosFacade().findAll();
    }
    public List<Formatos> getListaFormatosProceso() {
        listaFormatos = new ArrayList<>();
        return listaFormatos = getFormatosFacade().consultaFormatoProceso(subProcesosActual);
    }
    public List<Documentos> getListaDocumentosProceso() {
        listaDocumentos = new ArrayList<>();
        return listaDocumentos = getDocumentosFacade().consultaDocumentosProceso(procesosActual);
    }
        //Procesos

    public SubprocesosFacade getSubprocesosFacade() {
        return subprocesosFacade;
    }

    public void setSubprocesosFacade(SubprocesosFacade subprocesosFacade) {
        this.subprocesosFacade = subprocesosFacade;
    }

    public Subprocesos getSubProcesosActual() {
        return subProcesosActual;
    }
    public Subprocesos getSubProcesosActual(Subprocesos subProceso) {
        subProcesosActual= subProceso;
        return subProcesosActual;
    }

    public void setSubProcesosActual(Subprocesos subProcesosActual) {
        this.subProcesosActual = subProcesosActual;
    }

    public ProcesosFacade getProcesosFacade() {
        return procesosFacade;
    }

    public void setProcesosFacade(ProcesosFacade procesosFacade) {
        this.procesosFacade = procesosFacade;
    }

    public Procesos getProcesosActual() {
        return procesosActual;
    }

    public void setProcesosActual(Procesos procesosActual) {
        this.procesosActual = procesosActual;
    }

    
    public void llenarLista(){
        listaSubprocesos = getSubprocesosFacade().consultaSubProcesos(formatosActual.getIdProceso());
    }
    
    public List<Subprocesos> getListaPorProcesos() {
        return  listaSubprocesos;
    }
    public void recargarLista(){
        listaFormatos = new ArrayList<>();
        formatosActual = new Formatos();
    }
    
    public StreamedContent getFile() {
        return file;
    }
    public String prepareEdit() {
        return "editarRegistro";
    }  
    public String prepareEditCalidoso() {
        return "adminEditFormato";
    }  
    public String prepareListUser() {
        return "/usuario/modCalidad/registros/listaRegistro";
    } 
    public String prepareListAdmin() {
        return "/administrador/modCalidad/registros/listaRegistro";
    } 
    public String prepareListUserCalidoso() {
        return "userListRegistrosCalidoso";
    }  
    
    public String prepareCreateFormato() {
        formatosActual = new Formatos();
        return "/administrador/modCalidad/registros/crearRegistro";
    }
    public String addFormato() {
        try {
                getFormatosFacade().create(formatosActual);
                addSuccessMessage("Nuevo formato guardado", formatosActual.getNombreFormato());
                recargarLista();
                return "listaRegistro";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
     public String addFormatoCalidoso() {
        try {
            getFormatosFacade().create(formatosActual);
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nuevo registro creado","El formato "+ formatosActual.getNombreFormato()+", se creo correctamente");
             RequestContext.getCurrentInstance().showMessageInDialog(message);
             recargarLista();
             return "adminListFormato";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateFormato() {
        try {
            getFormatosFacade().edit(formatosActual);
            addSuccessMessage("Formato editado correctamente", "El formato "+formatosActual.getNombreFormato()+", se edito correctamente");
            recargarLista();
            return "listaRegistro";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    
    public String updateFormatoCalidoso() {
        try {
            getFormatosFacade().edit(formatosActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo editado","El archivo fue editado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            return "adminListFormato";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Archivo no editado","El archivo no fue editado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
    }
    
    public String deleteFormato() {
        try {
            getFormatosFacade().remove(formatosActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo Eliminado","El archivo fue eliminado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Archivo no Eliminado","El archivo no fue eliminado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);      
        }
        return "adminViewRegistro";
    }
     
    
    
        //Guardar documento
    private String getRandomDocumentoName() {
        int i = (int) (Math.random() * 10000000);
        return String.valueOf(i);
    }

    public void setNombreFormato(String nombreFormato) {
        this.nombreFormato = nombreFormato;
    }
    
    public String getNombreFormato() {
        return nombreFormato;
    }
     public String volverDocumento(){
        return "adminViewRegistros";
    }
  
     
    /*public void guardarFormato(FileUploadEvent event) throws IOException {
        nombreFormato = getRandomDocumentoName();
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        formatosActual.setFormato(data);
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" +
                                    File.separator + "images" + File.separator + "photocam" + File.separator + nombreFormato + ".pdf";
        
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
    }*/
   
    public List<Formatos> getListaFormatos() {
        return getFormatosFacade().conFormatos();
    }
   
    public List<Formatos> getFiltros() {
        return filtros;
    }

    public void setFiltros(List<Formatos> filtros) {
        this.filtros = filtros;
    }

    public void setListaFormatos(List<Formatos> listaFormatos) {
        this.listaFormatos = listaFormatos;
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
    
    public Formatos getFormatos(java.lang.Integer id) {
        return getFormatosFacade().find(id);
    }
    
    
    
    //Prepare View Formatos
    public String subAdmisiones(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(1, "ADMISIONES Y REGISTROS");
        procesosActual = new Procesos(1, "ADMISIONES Y REGISTRO");
        return "registroProceso/listaRegistroProceso";
    }
    public String subGesAdemica(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(2, "GESTIÓN ACADÉMICA");
        procesosActual = new Procesos(2, "EDUCACIÓN FORMAL");
        return "registroProceso/listaRegistroProceso";
    }
    public String subGescomunitaria(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(3, "GESTIÓN COMUNITARIA");
        procesosActual = new Procesos(2, "EDUCACIÓN FORMAL");
        return "registroProceso/listaRegistroProceso";
    }
    public String subExtAcademica(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(4, "EXTENSIÓN ACADÉMICA");
        procesosActual = new Procesos(3,"FORMACIÓN PARA EL TRABAJO Y DESARROLLO HUMANO");
        return "registroProceso/listaRegistroProceso";
    }
    public String subCursos(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(5, "CURSOS Y TÉCNICOS");
        procesosActual = new Procesos(3, "FORMACIÓN PARA EL TRABAJO Y DESARROLLO HUMANO");
        return "registroProceso/listaRegistroProceso";
    }
    public String subTalento(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(6, "TALENTO HUMANO");
        procesosActual = new Procesos(4, "TALENTO HUMANO");
        return "registroProceso/listaRegistroProceso";
    }
    public String subEdificios(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(7, "EDIFICIOS");
        procesosActual = new Procesos(5, "MANTENIMIENTO");
        return "registroProceso/listaRegistroProceso";
    }
    
    public String subIndustrial(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(8, "TALLERES INDUSTRIALES");
        procesosActual = new Procesos(5, "MANTENIMIENTO");
        return "registroProceso/listaRegistroProceso";
    }
    public String subProduccion(){
        //MANTENIMIENTO PRODUCCION
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(9, "MANTENIMIENTO EQUIPOS DE PRODUCCION");
        procesosActual = new Procesos(5, "MANTENIMIENTO");
        return "registroProceso/listaRegistroProceso";
    }
    public String subCompras(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(10, "COMPRAS");
        procesosActual = new Procesos(7, "LOGISTICA Y SUMINISTROS");
        return "registroProceso/listaRegistroProceso";
    }
    public String subTallerSatelite(){
        //TALLER SATELITE
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(11, "TALLER SATELITE");
        procesosActual = new Procesos(9,"PRODUCCIÓN");
        return "registroProceso/listaRegistroProceso";
    }
    public String subAlmacen(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(12, "ALMACEN");
        procesosActual = new Procesos(7,"LOGISTICA Y SUMINISTROS");
        return "registroProceso/listaRegistroProceso";
    }
    public String subMercadeo(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(13, "MERCADEO");
        procesosActual = new Procesos(8, "MERCADEO");
        return "registroProceso/listaRegistroProceso";
    }
   
    public String subProduccionConfecciones(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(14, "PRODUCCIÓN DE CONFECCIONES");
        procesosActual = new Procesos(9, "PRODUCCIÓN");
        return "registroProceso/listaRegistroProceso";
    }
    public String subFacturacion(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(15, "FACTURACIÓN Y DESPACHO");
        procesosActual = new Procesos(10, "FACTURACIÓN Y DESPACHO");
        return "registroProceso/listaRegistroProceso";
    }
    public String subCostos(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(16, "COSTOS");
        procesosActual = new Procesos(11,"FINANCIERO");
        return "registroProceso/listaRegistroProceso";
    }
    public String subMejoraContinua(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(17, "MEJORA CONTINUA");
        procesosActual = new Procesos(12, "MEJORA CONTINUA");
        return "registroProceso/listaRegistroProceso";
    }
    public String subBigbag(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(18, "BIG BAG");
        procesosActual = new Procesos(13, "BIG BAG");
        return "registroProceso/listaRegistroProceso";
    }
    public String subDireccion(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(19, "DIRECCIONAMIENTO ESTRATÉGICO");
        procesosActual = new Procesos(14, "DIRECCION");
        return "registroProceso/listaRegistroProceso";
    }
    
    public String subSistemas(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(20, "TECNOLOGÍA");
        procesosActual = new Procesos(5, "MANTENIMIENTO");
        return "registroProceso/listaRegistroProceso";
    }
     public String subFinanciero(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(21, "FINANCIERO");
        procesosActual = new Procesos(11, "FINANCIERO");
        return "registroProceso/listaRegistroProceso";
    }
    public String subIngenieria(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(22, "INGENIERIA DE PRODUCTO");
        procesosActual = new Procesos(15, "INGENIERIA DE PRODUCTO");
        return "registroProceso/listaRegistroProceso";
    }
    public String subContratacion(){
         formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(23, "CONTRATACIÓN EXTERNA");
        procesosActual= new Procesos(7, "LOGISTICA Y SUMINISTROS");
        return "registroProceso/listaRegistroProceso";
    }
    public String subPlaneacion(){
        formatosActual = new Formatos();
        documentoActual = new Documentos();
        subProcesosActual= new Subprocesos(24, "PLANEACIÓN");
        procesosActual= new Procesos(16, "PLANEACIÓN");
        return "registroProceso/listaRegistroProceso";
    }
   
   
     public void prepareViewFormato(ActionEvent event) {
        formatosActual = new Formatos();
        formatosActual = (Formatos) event.getComponent().getAttributes().get("formatos");
        //listaActividadAprendizaje=actividadActual.getActividadAprendizajeList(); 
    }
    public void clearAllFilters() {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("registrosFormList:documentosTablePanel:documentosTable");
        if (!dataTable.getFilters().isEmpty()) {
            dataTable.reset();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("registrosFormList:documentosTablePanel:documentosTable");
        }
    }
     public void prepareFormato(ActionEvent event){
        formatosActual = new Formatos();
        formatosActual = (Formatos) event.getComponent().getAttributes().get("formatos");
    }   
   
    public void obtenerFormato() throws IOException{
        if(formatosActual.getLinkFormato()==null){
            addErrorMessage("Formato sin acceso","el formato no tiene acceso");
        }else{
            FacesContext.getCurrentInstance().getExternalContext().redirect(formatosActual.getLinkFormato());
        }
    } 

    @FacesConverter(forClass = Formatos.class, value="formatoConverter")
    public static class FormatosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FormatosController controller = (FormatosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "formatosController");
            return controller.getFormatos(getKey(value));
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
            if (object instanceof Formatos) {
                Formatos o = (Formatos) object;
                return getStringKey(o.getIdFormato());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Formatos.class.getName());
            }
        }

    }

}
