/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.EstadoCronograma;
import com.proyectoCFIP.sessions.EstadoCronogramaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior Cabal
 */
@ManagedBean
@SessionScoped
public class EstadoCronogramaController implements Serializable{

    @EJB
    private EstadoCronogramaFacade estadoCronogramaFacade;
    private List<EstadoCronograma> listaEstadoCronograma = null;
    private EstadoCronograma estadoCronogramaActual;
    
    public EstadoCronogramaController() {

    }

    public List<EstadoCronograma> getListaEstadoCronograma() {
        listaEstadoCronograma = new ArrayList<>();
        listaEstadoCronograma = getEstadoCronogramaFacade().findAll();
        return listaEstadoCronograma;
    }
    public List<EstadoCronograma> getListaEstadoCronogramaConsulta() {
        return getEstadoCronogramaFacade().consultaTotalCR();
    }

    

    public EstadoCronogramaFacade getEstadoCronogramaFacade() {
        return estadoCronogramaFacade;
    }

    public EstadoCronograma getEstadoCronogramaActual() {
        if (estadoCronogramaActual == null) {
            estadoCronogramaActual = new EstadoCronograma();
        }
        return estadoCronogramaActual;
    }

    public void setEstadoCronogramaActual(EstadoCronograma estadoCronogramaActual) {
        this.estadoCronogramaActual = estadoCronogramaActual;
    }


    private void recargarLista() {
        listaEstadoCronograma = null;
    }

    public String prepareCreate() {
        estadoCronogramaActual = new EstadoCronograma();
        return "adminCrearEstadoCronogramaComputador";
    }

    public String prepareEdit() {
        return "";
    }

    public String prepareView() {
        return "";
    }

    public String prepareList() {
        recargarLista();
        return "";
    }

    public String addEstadoCronograma() {
        try {
            getEstadoCronogramaFacade().create(estadoCronogramaActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateEstadoCronograma() {
        try {
            getEstadoCronogramaFacade().edit(estadoCronogramaActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteEstadoCronograma() {
        try {
            getEstadoCronogramaFacade().remove(estadoCronogramaActual);
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
        return "List";
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
    
    public EstadoCronograma getEstadoCronograma(java.lang.Integer id) {
        return getEstadoCronogramaFacade().find(id);
    }
    
    @FacesConverter(forClass = EstadoCronograma.class)
    public static class EstadoCronogramaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadoCronogramaController controller = (EstadoCronogramaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estadoCronogramaController");
            return controller.getEstadoCronograma(getKey(value));
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
            if (object instanceof EstadoCronograma) {
                EstadoCronograma o = (EstadoCronograma) object;
                return getStringKey(o.getIdEstado());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EstadoCronograma.class.getName());
            }
        }
    }
}
