/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Raci;
import com.proyectoCFIP.sessions.RaciFacade;
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
public class RaciController implements Serializable{

    @EJB
    private RaciFacade raciFacade;
    private List<Raci> listaRaci = null;
    private Raci raciActual;
    
    public RaciController() {

    }

    public List<Raci> getListaRaci() {
        listaRaci = new ArrayList<>();
        listaRaci = getRaciFacade().findAll();
        return listaRaci;
    }


    

    public RaciFacade getRaciFacade() {
        return raciFacade;
    }

    public Raci getRaciActual() {
        if (raciActual == null) {
            raciActual = new Raci();
        }
        return raciActual;
    }

    public void setRaciActual(Raci raciActual) {
        this.raciActual = raciActual;
    }


    private void recargarLista() {
        listaRaci = null;
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

    public String addRaci() {
        try {
            getRaciFacade().create(raciActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateRaci() {
        try {
            getRaciFacade().edit(raciActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteRaci() {
        try {
            getRaciFacade().remove(raciActual);
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
    
    public Raci getRaci(java.lang.Integer id) {
        return getRaciFacade().find(id);
    }
    
    @FacesConverter(forClass = Raci.class,value="raciConverter")
    public static class RaciControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RaciController controller = (RaciController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "raciController");
            return controller.getRaci(getKey(value));
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
            if (object instanceof Raci) {
                Raci o = (Raci) object;
                return getStringKey(o.getIdRaci());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Raci.class.getName());
            }
        }
    }
}
