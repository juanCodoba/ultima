/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.Documentos;
import com.proyectoCFIP.entities.HistorialDocumento;
import com.proyectoCFIP.entities.Procesos;
import com.proyectoCFIP.entities.Roles;
import com.proyectoCFIP.entities.Subprocesos;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.DocumentosFacade;
import com.proyectoCFIP.sessions.HistorialDocumentoFacade;
import com.proyectoCFIP.sessions.ProcesosFacade;
import com.proyectoCFIP.sessions.SubprocesosFacade;
import com.proyectoCFIP.sessions.UsuarioFacade;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import org.apache.commons.io.IOUtils;
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
public class DocumentosController {
    
    @EJB
    private DocumentosFacade documentosFacade;
    @EJB
    private SubprocesosFacade subprocesosFacade;
    @EJB
    private ProcesosFacade procesosFacade;
    @EJB
    private HistorialDocumentoFacade historialDocumentoFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    private Documentos documentosActual;
    private List<Documentos> listaDocumentos;
    private List<Documentos> filtros;
    private List<Subprocesos> listaSubprocesos;
    private UploadedFile archivo;
    private String nombreDocumento;
    private StreamedContent file;
    private List<HistorialDocumento> listaHistorialDocumento;
    private HistorialDocumento historialDocumentoActual;
    private List<Usuario> listaUsuario;
    private Usuario usuarioActual;
    
    public DocumentosController() {
        
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    
    
    
  //Select one  proceso
    public ProcesosFacade getProcesosFacade() {
        return procesosFacade;
    }
    public void setProcesosFacade(ProcesosFacade procesosFacade) {
        this.procesosFacade = procesosFacade;
    }
    public List<Procesos> getListaProcesosFacadeSelectOne() {
        return getProcesosFacade().findAll();
    }
    
    public DocumentosFacade getDocumentosFacade() {
        return documentosFacade;
    }

    public void setDocumentosFacade(DocumentosFacade documentosFacade) {
        this.documentosFacade = documentosFacade;
    }

    public Documentos getDocumentosActual() {
        return documentosActual;
    }

    public void setDocumentosActual(Documentos documentosActual) {
        this.documentosActual = documentosActual;
    }
    public List<Documentos> getFiltros() {
        return filtros;
    }

    public void setFiltros(List<Documentos> filtros) {
        this.filtros = filtros;
    }
    
    public List<Documentos> getListaDocumentos(){
        return listaDocumentos= getDocumentosFacade().conDocumentos();
    }

    public void setListaDocumentos(List<Documentos> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }
    public void validarCodDocumento(FacesContext contex, UIComponent component, Object value)throws ValidatorException{
        Documentos codConsulta=getDocumentosFacade().findByCodDocumento((String)value);
        if(codConsulta != null){
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Codigo de documento repetido","El codigo del documento ya se encuentra registrado, favor intente con otro"));   
        }else{
            String codigo=(String) value;
            documentosActual.setCodigoDocumento(codigo);
        }
    }
    public List<Usuario> getListaUsuariosDocumentos() {
      return  listaUsuario = getUsuarioFacade().consultaUsuarioDocumento(documentosActual); 
    }
    public List<Documentos> ListaDocumentosSelectOne(){
        return getDocumentosFacade().findAll();
    }
    
    
    public Documentos getDocumentos(java.lang.Integer id) {
        return getDocumentosFacade().find(id);
    }
    
    //Procesos

    public SubprocesosFacade getSubprocesosFacade() {
        return subprocesosFacade;
    }

    public void setSubprocesosFacade(SubprocesosFacade subprocesosFacade) {
        this.subprocesosFacade = subprocesosFacade;
    }

    public void llenarLista(){
        listaSubprocesos = getSubprocesosFacade().consultaSubProcesos(documentosActual.getIdProceso());
    }
    public void agregarUsuarioList(){
        listaUsuario.add(usuarioActual);
    }
    
    public List<Subprocesos> getListaPorProcesos() {
        return  listaSubprocesos;
    }
    
    public StreamedContent getFile() {
        return file;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }
    
    
    //Historial computador

    public HistorialDocumentoFacade getHistorialDocumentoFacade() {
        return historialDocumentoFacade;
    }

    public void setHistorialDocumentoFacade(HistorialDocumentoFacade historialDocumentoFacade) {
        this.historialDocumentoFacade = historialDocumentoFacade;
    }

    public HistorialDocumento getHistorialDocumentoActual() {
        return historialDocumentoActual;
    }

    public void setHistorialDocumentoActual(HistorialDocumento historialDocumentoActual) {
        this.historialDocumentoActual = historialDocumentoActual;
    }
    public List<HistorialDocumento> getListaHistorialDocumento() {
        listaHistorialDocumento=null;
          if (listaHistorialDocumento == null) {
            try {
                listaHistorialDocumento = getHistorialDocumentoFacade().consultaDocu(documentosActual);
            } catch (Exception e) {
                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            }
        }
        return listaHistorialDocumento;
    }
    
    public void prepareViewHistorial(ActionEvent event) {
        documentosActual = new Documentos();
        documentosActual = (Documentos) event.getComponent().getAttributes().get("documentos");
        listaHistorialDocumento = new ArrayList<>();
        //listaActividadAprendizaje=actividadActual.getActividadAprendizajeList(); 
    }
    public String viewHistorialDocumentos(){
        return "adminHistorialDocumento";
    }
     public List<Usuario> getListaUsuariosRol() {
       return listaUsuario = getUsuarioFacade().consultaUsuarioRol(new Roles("CALIDAD", "Usuario calidad"));
        
    }
     public String prepareViewDistribucion(){
        return "adminViewDistribucionDocumento";
    }
     public String editDistribucion(){
         try {
            getDocumentosFacade().edit(documentosActual);
            addSuccessMessage("Distribución de documento actualizada", null);
            return "adminViewDistribucionDocumento";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
     
    public String deleteDocumento() {
        try {
            getDocumentosFacade().remove(documentosActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo Eliminado","El archivo fue eliminado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Archivo no Eliminado","El archivo no fue eliminado correctamente!!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);   
        }
        return "listDocumento";
    }
    
    public String prepareEdit() {
        return "editarDocumento";
    }  
  
    public String prepareListAdmin() {
        return "/administrador/modCalidad/documentos/listaDocumento";
    }
    public String prepareListPermiso() {
        return "/administrador/modCalidad/documentos/editarPermisoDocumento";
    }
    public String prepareCreateDocumento() {
        documentosActual = new Documentos();
        return "/administrador/modCalidad/documentos/crearDocumento";
    }
    public String prepareCreateDocumentoCalidoso() {
        historialDocumentoActual = new HistorialDocumento();
        documentosActual = new Documentos();
        return "/Calidoso/AdminCalidoso/adminCrearDocumento";
    }
     public String prepareListUser() {
        return "/usuario/modCalidad/documentos/listaDocumento";
    } 
     
    private void recargarLista(){
        listaDocumentos = new ArrayList<>();
        documentosActual = new Documentos();
    }
    public String addDocumento() {
        try {
            getDocumentosFacade().create(documentosActual);
            addSuccessMessage("Nuevo documento guardado", documentosActual.getNombreDocumento());
            recargarLista();
            return "listaDocumento";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    
    public String updateDocumento(){
         try {
            getDocumentosFacade().edit(documentosActual);
            addSuccessMessage("Archivo editado",documentosActual.getNombreDocumento());
            recargarLista();
            return "listaDocumento";
        } catch (Exception e) {
            addErrorMessage("Archivo no editado","El archivo no fue editado correctamente!!");
            return null;
        }
    }
    public void prepareDocumento(ActionEvent event){
        documentosActual = new Documentos();
        documentosActual = (Documentos) event.getComponent().getAttributes().get("documento");
    }   
   
    public void obtenerDocumento() throws IOException{
        if(documentosActual.getLinkDocumento()==null){
            addErrorMessage("Documento sin acceso","el documento no tiene acceso");
        }else{
            FacesContext.getCurrentInstance().getExternalContext().redirect(documentosActual.getLinkDocumento());
        }
    }   
    
    //Guardar documento
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
  
     
    /*public void guardarDocumento(FileUploadEvent event) throws IOException {
        nombreDocumento = getRandomDocumentoName();
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        documentosActual.setDocumento(data);
         
        historialDocumentoActual.setDocumento(data);
        
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
    }*/
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
    
    @FacesConverter(forClass = Documentos.class)
    public static class DocumentosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DocumentosController controller = (DocumentosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "documentosController");
            return controller.getDocumentos(getKey(value));
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
            if (object instanceof Documentos) {
                Documentos o = (Documentos) object;
                return getStringKey(o.getIdDocumento());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Documentos.class.getName());
            }
        }

    }

}
