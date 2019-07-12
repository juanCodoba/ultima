/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Cargos;
import com.proyectoCFIP.entities.Profesiograma;
import com.proyectoCFIP.entities.Usuario;
import com.proyectoCFIP.sessions.ProfesiogramaFacade;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author junio
 */
@Named(value = "ProfesiogramaController")
@SessionScoped
public class ProfesiogramaController implements Serializable {

    @EJB
    private ProfesiogramaFacade profesiogramaFacade;
    private Profesiograma ProfesiogramaActual;
    private List<Profesiograma> listaProfesiograma;
    private List<Profesiograma> listaProfesiogramaTipo = null;



    private Cargos cargosActual;

    private Usuario usuarioActual;

    public ProfesiogramaController() {
    }

    public ProfesiogramaFacade getProfesiogramaFacade() {
        return profesiogramaFacade;
    }

    public void setProfesiogramaFacade(ProfesiogramaFacade profesiogramaFacade) {
        this.profesiogramaFacade = profesiogramaFacade;
    }

    public Profesiograma getProfesiogramaActual() {
        return ProfesiogramaActual;
    }

    public void setProfesiogramaActual(Profesiograma ProfesiogramaActual) {
        this.ProfesiogramaActual = ProfesiogramaActual;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public List<Profesiograma> getListaProfesiograma() {
        return listaProfesiograma = getProfesiogramaFacade().findAll();
    }

    public List<Profesiograma> getListaProfesiogramaTipo() {
        return listaProfesiogramaTipo;
    }

    public void setListaProfesiogramaTipo(List<Profesiograma> listaProfesiogramaTipo) {
        this.listaProfesiogramaTipo = listaProfesiogramaTipo;
    }

//    
//    //Consulta para agilizar el  select dependiente
//    public void listaPorTipoDeMaeFuncion() {
//        listaProfesiogramaTipo = getProfesiogramaFacade().consultaUsuarioTipo(clasificacionProfesiogramaActual);
//    }
//    //Cierre de Consulta para agilizar el  select dependiente

    public void prepareVer() {

        recargarLista();
        ProfesiogramaActual = new Profesiograma();

    }

//    //Consulta para agilizar el  select dependiente
//    public void listaPorTipoDeProfesiograma() {
//        listaProfesiogramaTipo = getProfesiogramaFacade().consultaUsuarioTipo(cargosActual);
//    }
//    //Cierre de Consulta para agilizar el  select dependiente
    public void setListaProfesiograma(List<Profesiograma> listaProfesiograma) {
        this.listaProfesiograma = listaProfesiograma;
    }

    public void recargarLista() {
        listaProfesiograma = null;
    }

    public Cargos getCargosActual() {
        return cargosActual;
    }

    public void setCargosActual(Cargos cargosActual) {
        this.cargosActual = cargosActual;
    }

    public Profesiograma getProfesiograma(java.lang.Integer id) {
        return getProfesiogramaFacade().find(id);
    }

    public String prepareEdit() {
        return "/administrador/modCalidad/maestroCargos/funcion/edit";
    }

    public String prepareCreate() {
        ProfesiogramaActual = new Profesiograma();
        return "/administrador/modCalidad/cargos/create6";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modCalidad/maestrosCargos/funcion/list";
    }

    public String add() {

        try {
//            ProfesiogramaActual.setEstado(Boolean.TRUE);
            getProfesiogramaFacade().create(ProfesiogramaActual);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Profesiograma creado", "El Profesiograma fue creado correctamente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            return "/usuario/modCalidad/cargos/list";

        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "";
        }
    }

    public String pasarSig() {

        try {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Funciones Asignadas ", "las funciones fueron asginada al cargo correspondiente");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            recargarLista();
            return "/administrador/modCalidad/cargos/create4";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "";

        }
    }

    public String update() {
        try {
            getProfesiogramaFacade().edit(ProfesiogramaActual);
            recargarLista();
            return "/administrador/modCalidad/cargos/list";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "";
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

    @FacesConverter(forClass = Profesiograma.class, value = "profesiogramaConverter")
    public static class ProfesiogramaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProfesiogramaController controller = (ProfesiogramaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ProfesiogramaController");
            return controller.getProfesiograma(getKey(value));
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
            if (object instanceof Profesiograma) {
                Profesiograma o = (Profesiograma) object;
                return getStringKey(o.getIdProfesiograma());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Profesiograma.class.getName());
            }
        }

    }
    
    
        public void cargarFichaLogo(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        byte[] data = IOUtils.toByteArray(file.getInputstream());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        
            //String newFileName = "\\\\172.16.0.241\\Volume_1\\06Profesiograma\\" + "\\" + "-" +ProfesiogramaActual.getDescripcion() + ".pdf";
            String newFileName = "/root/alojamientoPro/" + "-" + ProfesiogramaActual.getDescripcion() + ".pdf";

            ProfesiogramaActual.setUrlProfesiograma(newFileName);
            FileImageOutputStream imageOutput;
            try {
                imageOutput = new FileImageOutputStream(new File(newFileName));
                imageOutput.write(data, 0, data.length);
                imageOutput.close();
            } catch (IOException e) {
                throw new FacesException("Error in writing captured image.", e);
            }
        
    }  

}
