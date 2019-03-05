/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.MaePeriodicidad;
import com.proyectoCFIP.sessions.MaePeriodicidadFacade;
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
public class periodicidadController implements Serializable{

    @EJB
    private MaePeriodicidadFacade MaePeriodicidadFacade;
    private List<MaePeriodicidad> listaMaePeriodicidad = null;
    private MaePeriodicidad MaePeriodicidadActual;
    
    public periodicidadController() {

    }

    public List<MaePeriodicidad> getListaMaePeriodicidad() {
        listaMaePeriodicidad = new ArrayList<>();
        listaMaePeriodicidad = getMaePeriodicidadFacade().findAll();
        return listaMaePeriodicidad;
    }


    

    public MaePeriodicidadFacade getMaePeriodicidadFacade() {
        return MaePeriodicidadFacade;
    }

    public MaePeriodicidad getMaePeriodicidadActual() {
        if (MaePeriodicidadActual == null) {
            MaePeriodicidadActual = new MaePeriodicidad();
        }
        return MaePeriodicidadActual;
    }

    public void setMaePeriodicidadActual(MaePeriodicidad MaePeriodicidadActual) {
        this.MaePeriodicidadActual = MaePeriodicidadActual;
    }


    private void recargarLista() {
        listaMaePeriodicidad = null;
    }

    public String prepareCreate() {
        MaePeriodicidadActual = new MaePeriodicidad();
        return "adminCrearMaePeriodicidadComputador";
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

    public String addMaePeriodicidad() {
        try {
            getMaePeriodicidadFacade().create(MaePeriodicidadActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateMaePeriodicidad() {
        try {
            getMaePeriodicidadFacade().edit(MaePeriodicidadActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteMaePeriodicidad() {
        try {
            getMaePeriodicidadFacade().remove(MaePeriodicidadActual);
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
    
    public MaePeriodicidad getMaePeriodicidad(java.lang.Integer id) {
        return getMaePeriodicidadFacade().find(id);
    }
    
    @FacesConverter(forClass = MaePeriodicidad.class, value = "periodicidadConverter")
    public static class MaePeriodicidadControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            periodicidadController controller = (periodicidadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "periodicidadController");
            return controller.getMaePeriodicidad(getKey(value));
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
            if (object instanceof MaePeriodicidad) {
                MaePeriodicidad o = (MaePeriodicidad) object;
                return getStringKey(o.getIdMaePeriodicidad());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MaePeriodicidad.class.getName());
            }
        }
    }
}
