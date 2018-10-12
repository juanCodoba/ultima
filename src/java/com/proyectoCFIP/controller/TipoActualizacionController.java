/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.TipoActualizacion;
import com.proyectoCFIP.sessions.TipoActualizacionFacade;
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
public class TipoActualizacionController implements Serializable{

   @EJB
    private TipoActualizacionFacade tipoActualizacionFacade;
    private List<TipoActualizacion> listaTipoActualizacion = null;
    private TipoActualizacion tipoActualizacionActual;
    
    public TipoActualizacionController() {

    }

    public List<TipoActualizacion> getListaTipoActualizacion() {
        try {
            if (listaTipoActualizacion == null) {
                listaTipoActualizacion = getTipoActualizacionFacade().findAll();
            }
            return listaTipoActualizacion;
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    

    public TipoActualizacionFacade getTipoActualizacionFacade() {
        return tipoActualizacionFacade;
    }
    public List<TipoActualizacion> getListaTipoActualizacionSelectOne() {
        return getTipoActualizacionFacade().consultaTipoActualizacion();
    }
    public TipoActualizacion getTipoActualizacionActual() {
        if (tipoActualizacionActual == null) {
            tipoActualizacionActual = new TipoActualizacion();
        }
        return tipoActualizacionActual;
    }

    public void setTipoActualizacionActual(TipoActualizacion tipoActualizacionActual) {
        this.tipoActualizacionActual = tipoActualizacionActual;
    }


    private void recargarLista() {
        listaTipoActualizacion = null;
    }

    public String prepareCreate() {
        tipoActualizacionActual = new TipoActualizacion();
        return "";
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

    public String addTipoActualizacion() {
        try {
            getTipoActualizacionFacade().create(tipoActualizacionActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateTipoActualizacion() {
        try {
            getTipoActualizacionFacade().edit(tipoActualizacionActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteTipoActualizacion() {
        try {
            getTipoActualizacionFacade().remove(tipoActualizacionActual);
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
    
    public TipoActualizacion getTipoActualizacion(java.lang.Integer id) {
        return getTipoActualizacionFacade().find(id);
    }
    
    @FacesConverter(forClass = TipoActualizacion.class)
    public static class TipoActualizacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoActualizacionController controller = (TipoActualizacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoActualizacionController");
            return controller.getTipoActualizacion(getKey(value));
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
            if (object instanceof TipoActualizacion) {
                TipoActualizacion o = (TipoActualizacion) object;
                return getStringKey(o.getIdTipoActualizacion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoActualizacion.class.getName());
            }
        }

    }

}    
