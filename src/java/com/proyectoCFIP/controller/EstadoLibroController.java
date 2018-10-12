/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.EstadoLibro;
import com.proyectoCFIP.sessions.EstadoLibroFacade;
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
@Named(value = "EstadoLibroController")
@SessionScoped
public class EstadoLibroController implements Serializable {

    @EJB
    private EstadoLibroFacade EstadoLibroFacade;
    private EstadoLibro EstadoLibroActual;
    private List<EstadoLibro> ListaEstadoLib;
    
    
    public EstadoLibroController() {
    }
    public EstadoLibroFacade getEstadoLibroFacade() {
        return EstadoLibroFacade;
    }

    public void setEstadoLibroFacade(EstadoLibroFacade EstadoLibroFacade) {
        this.EstadoLibroFacade = EstadoLibroFacade;
    }

    public EstadoLibro getEstadoLibroActual() {
        return EstadoLibroActual;
    }

    public void setEstadoLibroActual(EstadoLibro EstadoLibroActual) {
        this.EstadoLibroActual = EstadoLibroActual;
    }

    public List<EstadoLibro> getListaEstadoLibro() {
        return ListaEstadoLib = getEstadoLibroFacade().findAll();
    }

    public void setListaEstadoLibro(List<EstadoLibro> ListaEstadoLib) {
        this.ListaEstadoLib = ListaEstadoLib;
    }
    
    public void recargarLista(){
        ListaEstadoLib=null;
    }
    public EstadoLibro getEstadoLibro(java.lang.Integer id) {
        return getEstadoLibroFacade().find(id);
    }
     public String prepareEdit() {
        return "/administrador/modBiblioteca/editar/editarLibroBi";
    }
      public String prepareCreate() {
        EstadoLibroActual = new EstadoLibro();
        return "/administrador/modBiblioteca/crear/crearLibroBi";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modBiblioteca/EstadoLibro/lista";
    }

    public String prepareListusuario() {
        recargarLista();
        return "/usuario/modSaludOcupacional/reporte/lista";
    }

    public String add() {
        try { 
            getEstadoLibroFacade().create(EstadoLibroActual);
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String update() {
        try {
            getEstadoLibroFacade().edit(EstadoLibroActual);
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
    
    @FacesConverter(forClass = EstadoLibro.class )
    public static class EstadoLibroControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadoLibroController controller = (EstadoLibroController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "EstadoLibroController");
            return controller.getEstadoLibro(getKey(value));
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
            if (object instanceof EstadoLibro) {
                EstadoLibro o = (EstadoLibro) object;
                return getStringKey(o.getIdEstadoLibro());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EstadoLibro.class.getName());
            }
        }
    }
    

}
