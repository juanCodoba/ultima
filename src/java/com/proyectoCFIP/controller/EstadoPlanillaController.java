/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.EstadoConsecPlanilla;
import com.proyectoCFIP.sessions.EstadoConsecPlanillaFacade;
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
@Named(value = "EstadoConsecPlanillaController")
@SessionScoped
public class EstadoPlanillaController implements Serializable {

    @EJB
    private EstadoConsecPlanillaFacade EstadoConsecPlanillaFacade;
    private EstadoConsecPlanilla EstadoConsecPlanillaActual;
    private List<EstadoConsecPlanilla> ListaEstadoConsecPlanilla;
    
    
    public EstadoPlanillaController() {
    }
    public EstadoConsecPlanillaFacade getEstadoConsecPlanillaFacade() {
        return EstadoConsecPlanillaFacade;
    }

    public void setEstadoConsecPlanillaFacade(EstadoConsecPlanillaFacade EstadoConsecPlanillaFacade) {
        this.EstadoConsecPlanillaFacade = EstadoConsecPlanillaFacade;
    }

    public EstadoConsecPlanilla getEstadoConsecPlanillaActual() {
        return EstadoConsecPlanillaActual;
    }

    public void setEstadoConsecPlanillaActual(EstadoConsecPlanilla EstadoConsecPlanillaActual) {
        this.EstadoConsecPlanillaActual = EstadoConsecPlanillaActual;
    }

    public List<EstadoConsecPlanilla> getListaEstadoConsecPlanilla() {
        return ListaEstadoConsecPlanilla = getEstadoConsecPlanillaFacade().findAll();
    }

    public void setListaEstadoConsecPlanilla(List<EstadoConsecPlanilla> ListaEstadoConsecPlanilla) {
        this.ListaEstadoConsecPlanilla = ListaEstadoConsecPlanilla;
    }
    
    public void recargarLista(){
        ListaEstadoConsecPlanilla=null;
    }
    public EstadoConsecPlanilla getEstadoConsecPlanilla(java.lang.Integer id) {
        return getEstadoConsecPlanillaFacade().find(id);
    }
     public String prepareEdit() {
        return "/administrador/modBiblioteca/editar/editarLibroBi";
    }
      public String prepareCreate() {
        EstadoConsecPlanillaActual = new EstadoConsecPlanilla();
        return "/administrador/modBiblioteca/crear/crearLibroBi";
    }

    public String prepareView() {
        return "ver";
    }

    public String prepareList() {
        recargarLista();
        return "/administrador/modBiblioteca/EstadoConsecPlanilla/lista";
    }

    public String prepareListusuario() {
        recargarLista();
        return "/usuario/modSaludOcupacional/reporte/lista";
    }

    public String add() {
        try { 
            getEstadoConsecPlanillaFacade().create(EstadoConsecPlanillaActual);
            recargarLista();
            return "lista";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String update() {
        try {
            getEstadoConsecPlanillaFacade().edit(EstadoConsecPlanillaActual);
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
    
    @FacesConverter(forClass = EstadoConsecPlanilla.class )
    public static class EstadoConsecPlanillaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadoPlanillaController controller = (EstadoPlanillaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "EstadoConsecPlanillaController");
            return controller.getEstadoConsecPlanilla(getKey(value));
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
            if (object instanceof EstadoConsecPlanilla) {
                EstadoConsecPlanilla o = (EstadoConsecPlanilla) object;
                return getStringKey(o.getIdEstadoConsecPlanilla());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EstadoConsecPlanilla.class.getName());
            }
        }
    }
    

}
