/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.FtCliente;
import com.proyectoCFIP.entities.TipoEstudiante;
import com.proyectoCFIP.sessions.TipoEstudianteFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author junio
 */
@Named(value = "TipoEstudianteController")
@SessionScoped
public class TipoEstudianteController implements Serializable {

    @EJB
    private TipoEstudianteFacade tipoEstudianteFacade;
    private TipoEstudiante tipoEstudianteActual;
    private List<TipoEstudiante> listaTipoEstudiante;
    
    
    public TipoEstudianteController() {
    }
    public TipoEstudianteFacade getTipoEstudianteFacade() {
        return tipoEstudianteFacade;
    }

    public void setTipoEstudianteFacade(TipoEstudianteFacade tipoEstudianteFacade) {
        this.tipoEstudianteFacade = tipoEstudianteFacade;
    }

    public TipoEstudiante getTipoEstudianteActual() {
        return tipoEstudianteActual;
    }

    public void setTipoEstudianteActual(TipoEstudiante tipoEstudianteActual) {
        this.tipoEstudianteActual = tipoEstudianteActual;
    }

    public List<TipoEstudiante> getListaTipoEstudiante() {
        return listaTipoEstudiante = getTipoEstudianteFacade().findAll();
    }

    public void setListaTipoEstudiante(List<TipoEstudiante> listaTipoEstudiante) {
        this.listaTipoEstudiante = listaTipoEstudiante;
    }
    
    public void recargarLista(){
        listaTipoEstudiante=null;
    }
    public TipoEstudiante getTipoEstudiante(java.lang.Integer id) {
        return getTipoEstudianteFacade().find(id);
    }
    

    
     public String prepareEdit() {
        return "/administrador/modBiblioteca/editar/editarLibroBi";
    }
      public String prepareCreate() {
        tipoEstudianteActual = new TipoEstudiante();
        return "/administrador/modBiblioteca/crear/crearLibroBi";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modBiblioteca/tipoEstudiante/lista";
    }

    public String prepareListusuario() {
        recargarLista();
        return "/usuario/modSaludOcupacional/reporte/lista";
    }

    public String add() {
        try { 
            getTipoEstudianteFacade().create(tipoEstudianteActual);
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String update() {
        try {
            getTipoEstudianteFacade().edit(tipoEstudianteActual);
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
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
    
    @FacesConverter(forClass = TipoEstudiante.class)
    public static class TipoEstudianteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoEstudianteController controller = (TipoEstudianteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "TipoEstudianteController");
            return controller.getTipoEstudiante(getKey(value));
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
            if (object instanceof TipoEstudiante) {
                TipoEstudiante o = (TipoEstudiante) object;
                return getStringKey(o.getIdTipoEstudiante());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoEstudiante.class.getName());
            }
        }
    }
}
