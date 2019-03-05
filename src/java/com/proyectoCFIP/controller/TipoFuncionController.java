/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.TipoFuncion;
import com.proyectoCFIP.sessions.TipoFuncionFacade;
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
public class TipoFuncionController implements Serializable{

    @EJB
    private TipoFuncionFacade tipoFuncionFacade;
    private List<TipoFuncion> listaTipoFuncion = null;
    private TipoFuncion tipoFuncionActual;
    
    public TipoFuncionController() {

    }

    public List<TipoFuncion> getListaTipoFuncion() {
        listaTipoFuncion = new ArrayList<>();
        listaTipoFuncion = getTipoFuncionFacade().findAll();
        return listaTipoFuncion;
    }


    

    public TipoFuncionFacade getTipoFuncionFacade() {
        return tipoFuncionFacade;
    }

    public TipoFuncion getTipoFuncionActual() {
        if (tipoFuncionActual == null) {
            tipoFuncionActual = new TipoFuncion();
        }
        return tipoFuncionActual;
    }

    public void setTipoFuncionActual(TipoFuncion tipoFuncionActual) {
        this.tipoFuncionActual = tipoFuncionActual;
    }


    private void recargarLista() {
        listaTipoFuncion = null;
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

    public String addTipoFuncion() {
        try {
            getTipoFuncionFacade().create(tipoFuncionActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateTipoFuncion() {
        try {
            getTipoFuncionFacade().edit(tipoFuncionActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteTipoFuncion() {
        try {
            getTipoFuncionFacade().remove(tipoFuncionActual);
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
    
    public TipoFuncion getTipoFuncion(java.lang.Integer id) {
        return getTipoFuncionFacade().find(id);
    }
    
    @FacesConverter(forClass = TipoFuncion.class,value="tipoFuncionConverter")
    public static class TipoFuncionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoFuncionController controller = (TipoFuncionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoFuncionController");
            return controller.getTipoFuncion(getKey(value));
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
            if (object instanceof TipoFuncion) {
                TipoFuncion o = (TipoFuncion) object;
                return getStringKey(o.getIdTipoFuncion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoFuncion.class.getName());
            }
        }
    }
}
