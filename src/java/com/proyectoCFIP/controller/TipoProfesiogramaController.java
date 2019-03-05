/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.TipoProfesiograma;
import com.proyectoCFIP.sessions.TipoProfesiogramaFacade;
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
public class TipoProfesiogramaController implements Serializable{

    @EJB
    private TipoProfesiogramaFacade tipoProfesiogramaFacade;
    private List<TipoProfesiograma> listaTipoProfesiograma = null;
    private TipoProfesiograma tipoProfesiogramaActual;
    
    public TipoProfesiogramaController() {

    }

    public List<TipoProfesiograma> getListaTipoProfesiograma() {
        listaTipoProfesiograma = new ArrayList<>();
        listaTipoProfesiograma = getTipoProfesiogramaFacade().findAll();
        return listaTipoProfesiograma;
    }


    

    public TipoProfesiogramaFacade getTipoProfesiogramaFacade() {
        return tipoProfesiogramaFacade;
    }

    public TipoProfesiograma getTipoProfesiogramaActual() {
        if (tipoProfesiogramaActual == null) {
            tipoProfesiogramaActual = new TipoProfesiograma();
        }
        return tipoProfesiogramaActual;
    }

    public void setTipoProfesiogramaActual(TipoProfesiograma tipoProfesiogramaActual) {
        this.tipoProfesiogramaActual = tipoProfesiogramaActual;
    }


    private void recargarLista() {
        listaTipoProfesiograma = null;
    }

    public String prepareCreate() {
        tipoProfesiogramaActual = new TipoProfesiograma();
        return "adminCrearTipoProfesiogramaComputador";
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

    public String addTipoProfesiograma() {
        try {
            getTipoProfesiogramaFacade().create(tipoProfesiogramaActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateTipoProfesiograma() {
        try {
            getTipoProfesiogramaFacade().edit(tipoProfesiogramaActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteTipoProfesiograma() {
        try {
            getTipoProfesiogramaFacade().remove(tipoProfesiogramaActual);
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
    
    public TipoProfesiograma getTipoProfesiograma(java.lang.Integer id) {
        return getTipoProfesiogramaFacade().find(id);
    }
    
    @FacesConverter(forClass = TipoProfesiograma.class)
    public static class TipoProfesiogramaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoProfesiogramaController controller = (TipoProfesiogramaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoProfesiogramaController");
            return controller.getTipoProfesiograma(getKey(value));
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
            if (object instanceof TipoProfesiograma) {
                TipoProfesiograma o = (TipoProfesiograma) object;
                return getStringKey(o.getIdTipoProfesiograma());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoProfesiograma.class.getName());
            }
        }
    }
}
