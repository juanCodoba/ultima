/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Monitor;
import com.proyectoCFIP.sessions.MonitorFacade;
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
public class MonitorController implements Serializable{

    @EJB
    private MonitorFacade monitorFacade;
    private List<Monitor> listaMonitor = null;
    private Monitor monitorActual;
    
    public MonitorController() {

    }

    public List<Monitor> getListaMonitor() {
        listaMonitor = new ArrayList<>();
        listaMonitor = getMonitorFacade().findAll();
        return listaMonitor;
    }

    

    public MonitorFacade getMonitorFacade() {
        return monitorFacade;
    }

    public Monitor getMonitorActual() {
        if (monitorActual == null) {
            monitorActual = new Monitor();
        }
        return monitorActual;
    }

    public void setMonitorActual(Monitor monitorActual) {
        this.monitorActual = monitorActual;
    }


    private void recargarLista() {
        listaMonitor = null;
    }

    public String prepareCreate() {
        monitorActual = new Monitor();
        return "adminCrearMonitor";
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

    public String addMonitor() {
        try {
            getMonitorFacade().create(monitorActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateMonitor() {
        try {
            getMonitorFacade().edit(monitorActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteMonitor() {
        try {
            getMonitorFacade().remove(monitorActual);
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
    
    public Monitor getMonitor(java.lang.Integer id) {
        return getMonitorFacade().find(id);
    }
    @FacesConverter(forClass = Monitor.class)
    public static class MonitorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MonitorController controller = (MonitorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "monitorController");
            return controller.getMonitor(getKey(value));
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
            if (object instanceof Monitor) {
                Monitor o = (Monitor) object;
                return getStringKey(o.getIdMonitor());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Monitor.class.getName());
            }
        }

    }
}
