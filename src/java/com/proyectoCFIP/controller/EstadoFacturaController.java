/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.EstadoFactura;
import com.proyectoCFIP.sessions.EstadoFacturaFacade;
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
@Named(value = "EstadoFacturaController")
@SessionScoped
public class EstadoFacturaController implements Serializable {

    @EJB
    private EstadoFacturaFacade EstadoFacturaFacade;
    private EstadoFactura EstadoFacturaActual;
    private List<EstadoFactura> ListaEstadoLib;
    
    
    public EstadoFacturaController() {
    }
    public EstadoFacturaFacade getEstadoFacturaFacade() {
        return EstadoFacturaFacade;
    }

    public void setEstadoFacturaFacade(EstadoFacturaFacade EstadoFacturaFacade) {
        this.EstadoFacturaFacade = EstadoFacturaFacade;
    }

    public EstadoFactura getEstadoFacturaActual() {
        return EstadoFacturaActual;
    }

    public void setEstadoFacturaActual(EstadoFactura EstadoFacturaActual) {
        this.EstadoFacturaActual = EstadoFacturaActual;
    }

    public List<EstadoFactura> getListaEstadoFactura() {
        return ListaEstadoLib = getEstadoFacturaFacade().findAll();
    }

    public void setListaEstadoFactura(List<EstadoFactura> ListaEstadoLib) {
        this.ListaEstadoLib = ListaEstadoLib;
    }
    
    public void recargarLista(){
        ListaEstadoLib=null;
    }
    public EstadoFactura getEstadoFactura(java.lang.Integer id) {
        return getEstadoFacturaFacade().find(id);
    }
     public String prepareEdit() {
        return "/administrador/modBiblioteca/editar/editarLibroBi";
    }
      public String prepareCreate() {
        EstadoFacturaActual = new EstadoFactura();
        return "/administrador/modBiblioteca/crear/crearLibroBi";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modBiblioteca/EstadoFactura/lista";
    }

    public String prepareListusuario() {
        recargarLista();
        return "/usuario/modSaludOcupacional/reporte/lista";
    }

    public String add() {
        try { 
            getEstadoFacturaFacade().create(EstadoFacturaActual);
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String update() {
        try {
            getEstadoFacturaFacade().edit(EstadoFacturaActual);
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
    
    @FacesConverter(forClass = EstadoFactura.class )
    public static class EstadoFacturaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadoFacturaController controller = (EstadoFacturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "EstadoFacturaController");
            return controller.getEstadoFactura(getKey(value));
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
            if (object instanceof EstadoFactura) {
                EstadoFactura o = (EstadoFactura) object;
                return getStringKey(o.getIdEstadoFactura());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EstadoFactura.class.getName());
            }
        }
    }
    

}
