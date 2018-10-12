/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.Marca;
import com.proyectoCFIP.entities.Modelo;
import com.proyectoCFIP.entities.Tipo;
import com.proyectoCFIP.sessions.MarcaFacade;
import com.proyectoCFIP.sessions.ModeloFacade;
import com.proyectoCFIP.sessions.TipoFacade;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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
import javax.faces.event.PhaseId;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean(name = "modeloController")
@SessionScoped
public class ModeloController implements Serializable{
    
    @EJB 
    private MarcaFacade marcaFacade;
    @EJB
    private ModeloFacade modeloFacade;
    @EJB
    private TipoFacade tipoFacade;
    private List<Modelo> listaModelo = null;
    private Modelo modeloActual;
    private String nombreImagen;
    public ModeloController() {

    }
    
    //Select one Tipo pc
    public TipoFacade getTipoFacade() {
        return tipoFacade;
    }
    public void setTipoFacade(TipoFacade tipoFacade) {
        this.tipoFacade = tipoFacade;
    }
    public List<Tipo> getListaTipoSelectOne() {
        return getTipoFacade().findAll();
    }
    
    
    //Select one MarcaPc
    public MarcaFacade getMarcaFacade() {
        return marcaFacade;
    }

    public void setMarcaFacade(MarcaFacade marcaFacade) {
        this.marcaFacade = marcaFacade;
    }
    
     public List<Marca> getListaMarcaSelectOne() {
        return getMarcaFacade().findAll();
    }

    public List<Modelo> getListaModelo() {
        listaModelo = new ArrayList<>();
        listaModelo = getModeloFacade().findAll();
        return listaModelo;
    }
      public List<Modelo> getListaModeloDispositivoConec() {
        listaModelo = new ArrayList<>();
        listaModelo = getModeloFacade().consultaTipoDispoConec();
        return listaModelo;
    }
    

    

    public ModeloFacade getModeloFacade() {
        return modeloFacade;
    }

    public Modelo getModeloActual() {
        if (modeloActual == null) {
            modeloActual = new Modelo();
        }
        return modeloActual;
    }

    public void setModeloActual(Modelo modeloActual) {
        this.modeloActual = modeloActual;
    }


    private void recargarLista() {
        listaModelo = null;
    }

    public String prepareCreate() {
        modeloActual = new Modelo();
        nombreImagen= null;
        return "crear";
    }

    public String prepareEdit() {
        nombreImagen= null;
        return "editar";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "modelo/lista";
    }
    
    //imagen modelo
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
        modeloActual.setImagenModelo(data);
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" +
                                    File.separator + "images" + File.separator + "photocam" + File.separator + nombreImagen + ".jpeg";
         
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        }
        catch(IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
    }
    public String volverModelo(){
        return "lista";
    }
    public String addModelo() {
        try {
            getModeloFacade().create(modeloActual);
            addSuccessMessage("Modelo guardado", "El modelo "+modeloActual.getNombreModelo()+" ha sido guardado exitosamente");
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error al guardar el modelo ", "Por favor intentalo de nuevo");
            return "lista";
        }
    }
    public String updateModelo() {
        try {
            getModeloFacade().edit(modeloActual);
            addSuccessMessage("Modelo Editado", "El modelo "+modeloActual.getNombreModelo()+" ha sido editado exitosamente");
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "lista";
        }
    }

    public String deleteModelo() {
        try {
            getModeloFacade().remove(modeloActual);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
        return "lista";
    }
    
    public StreamedContent getImage() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Get ID value from actual request param.
            String id = context.getExternalContext().getRequestParameterMap().get("id_modelo");
            Modelo image = modeloFacade.find(Integer.valueOf(id));
            return new DefaultStreamedContent(new ByteArrayInputStream(image.getImagenModelo()));
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
    
    public Modelo getModelo(java.lang.Integer id) {
        return getModeloFacade().find(id);
    }
    

    @FacesConverter(forClass = Modelo.class)
    public static class ModeloControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ModeloController controller = (ModeloController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "modeloController");
            return controller.getModelo(getKey(value));
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
            if (object instanceof Modelo) {
                Modelo o = (Modelo) object;
                return getStringKey(o.getIdModelo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Modelo.class.getName());
            }
        }
    }
}
