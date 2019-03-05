/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.TipoCargo;
import com.proyectoCFIP.sessions.TipoCargoFacade;
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
public class TipoCargoController implements Serializable{

    @EJB
    private TipoCargoFacade tipoCargoFacade;
    private List<TipoCargo> listaTipoCargo = null;
    private TipoCargo tipoCargoActual;
    
    public TipoCargoController() {

    }

    public List<TipoCargo> getListaTipoCargo() {
        listaTipoCargo = new ArrayList<>();
        listaTipoCargo = getTipoCargoFacade().findAll();
        return listaTipoCargo;
    }


    

    public TipoCargoFacade getTipoCargoFacade() {
        return tipoCargoFacade;
    }

    public TipoCargo getTipoCargoActual() {
        if (tipoCargoActual == null) {
            tipoCargoActual = new TipoCargo();
        }
        return tipoCargoActual;
    }

    public void setTipoCargoActual(TipoCargo tipoCargoActual) {
        this.tipoCargoActual = tipoCargoActual;
    }


    private void recargarLista() {
        listaTipoCargo = null;
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

    public String addTipoCargo() {
        try {
            getTipoCargoFacade().create(tipoCargoActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateTipoCargo() {
        try {
            getTipoCargoFacade().edit(tipoCargoActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteTipoCargo() {
        try {
            getTipoCargoFacade().remove(tipoCargoActual);
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
    
    public TipoCargo getTipoCargo(java.lang.Integer id) {
        return getTipoCargoFacade().find(id);
    }
    
    @FacesConverter(forClass = TipoCargo.class,value="tipoCargoConverter")
    public static class TipoCargoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoCargoController controller = (TipoCargoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoCargoController");
            return controller.getTipoCargo(getKey(value));
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
            if (object instanceof TipoCargo) {
                TipoCargo o = (TipoCargo) object;
                return getStringKey(o.getIdTipoCargo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoCargo.class.getName());
            }
        }
    }
}
