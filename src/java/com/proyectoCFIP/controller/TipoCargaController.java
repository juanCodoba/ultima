/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.TipoCarga;
import com.proyectoCFIP.sessions.TipoCargaFacade;
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
public class TipoCargaController implements Serializable{

    @EJB
    private TipoCargaFacade tipoFacade;
    private List<TipoCarga> listaTipoCarga = null;
    private TipoCarga tipoActual;
    
    public TipoCargaController() {

    }

    public List<TipoCarga> getListaTipoCarga() {
        listaTipoCarga = new ArrayList<>();
        listaTipoCarga = getTipoCargaFacade().findAll();
        return listaTipoCarga;
    }


    

    public TipoCargaFacade getTipoCargaFacade() {
        return tipoFacade;
    }

    public TipoCarga getTipoCargaActual() {
        if (tipoActual == null) {
            tipoActual = new TipoCarga();
        }
        return tipoActual;
    }

    public void setTipoCargaActual(TipoCarga tipoActual) {
        this.tipoActual = tipoActual;
    }


    private void recargarLista() {
        listaTipoCarga = null;
    }

    public String prepareCreate() {
        tipoActual = new TipoCarga();
        return "adminCrearTipoCargaComputador";
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

    public String addTipoCarga() {
        try {
            getTipoCargaFacade().create(tipoActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateTipoCarga() {
        try {
            getTipoCargaFacade().edit(tipoActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteTipoCarga() {
        try {
            getTipoCargaFacade().remove(tipoActual);
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
    
    public TipoCarga getTipoCarga(java.lang.Integer id) {
        return getTipoCargaFacade().find(id);
    }
    
    @FacesConverter(forClass = TipoCarga.class)
    public static class TipoCargaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoCargaController controller = (TipoCargaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoCargaController");
            return controller.getTipoCarga(getKey(value));
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
            if (object instanceof TipoCarga) {
                TipoCarga o = (TipoCarga) object;
                return getStringKey(o.getIdTipoCarga());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoCarga.class.getName());
            }
        }
    }
}
