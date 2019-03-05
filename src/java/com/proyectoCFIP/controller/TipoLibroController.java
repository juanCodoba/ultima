/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.TipoLibro;
import com.proyectoCFIP.sessions.TipoLibroFacade;
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
public class TipoLibroController implements Serializable{

    @EJB
    private TipoLibroFacade tipoLibroFacade;
    private List<TipoLibro> listaTipoLibro = null;
    private TipoLibro tipoLibroActual;
    
    public TipoLibroController() {

    }

    public List<TipoLibro> getListaTipoLibro() {
        listaTipoLibro = new ArrayList<>();
        listaTipoLibro = getTipoLibroFacade().findAll();
        return listaTipoLibro;
    }


    

    public TipoLibroFacade getTipoLibroFacade() {
        return tipoLibroFacade;
    }

    public TipoLibro getTipoLibroActual() {
        if (tipoLibroActual == null) {
            tipoLibroActual = new TipoLibro();
        }
        return tipoLibroActual;
    }

    public void setTipoLibroActual(TipoLibro tipoLibroActual) {
        this.tipoLibroActual = tipoLibroActual;
    }


    private void recargarLista() {
        listaTipoLibro = null;
    }

    public String prepareCreate() {
        tipoLibroActual = new TipoLibro();
        return "adminCrearTipoLibroComputador";
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

    public String addTipoLibro() {
        try {
            getTipoLibroFacade().create(tipoLibroActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateTipoLibro() {
        try {
            getTipoLibroFacade().edit(tipoLibroActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteTipoLibro() {
        try {
            getTipoLibroFacade().remove(tipoLibroActual);
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
    
    public TipoLibro getTipoLibro(java.lang.Integer id) {
        return getTipoLibroFacade().find(id);
    }
    
    @FacesConverter(forClass = TipoLibro.class)
    public static class TipoLibroControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoLibroController controller = (TipoLibroController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoLibroController");
            return controller.getTipoLibro(getKey(value));
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
            if (object instanceof TipoLibro) {
                TipoLibro o = (TipoLibro) object;
                return getStringKey(o.getIdTipoLibro());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoLibro.class.getName());
            }
        }
    }
}
