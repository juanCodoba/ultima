/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Riesgo;
import com.proyectoCFIP.sessions.RiesgoFacade;
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
public class RiesgoController implements Serializable{

    @EJB
    private RiesgoFacade riesgoFacade;
    private List<Riesgo> listaRiesgo = null;
    private Riesgo riesgoActual;
    
    public RiesgoController() {

    }

    public List<Riesgo> getListaRiesgo() {
        listaRiesgo = new ArrayList<>();
        listaRiesgo = getRiesgoFacade().findAll();
        return listaRiesgo;
    }


    

    public RiesgoFacade getRiesgoFacade() {
        return riesgoFacade;
    }

    public Riesgo getRiesgoActual() {
        if (riesgoActual == null) {
            riesgoActual = new Riesgo();
        }
        return riesgoActual;
    }

    public void setRiesgoActual(Riesgo riesgoActual) {
        this.riesgoActual = riesgoActual;
    }


    private void recargarLista() {
        listaRiesgo = null;
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

    public String addRiesgo() {
        try {
            getRiesgoFacade().create(riesgoActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateRiesgo() {
        try {
            getRiesgoFacade().edit(riesgoActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteRiesgo() {
        try {
            getRiesgoFacade().remove(riesgoActual);
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
    
    public Riesgo getRiesgo(java.lang.Integer id) {
        return getRiesgoFacade().find(id);
    }
    
    @FacesConverter(forClass = Riesgo.class,value="riesgoConverter")
    public static class RiesgoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RiesgoController controller = (RiesgoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "riesgoController");
            return controller.getRiesgo(getKey(value));
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
            if (object instanceof Riesgo) {
                Riesgo o = (Riesgo) object;
                return getStringKey(o.getIdRiesgo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Riesgo.class.getName());
            }
        }
    }
}
