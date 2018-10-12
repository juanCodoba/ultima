/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Tipo;
import com.proyectoCFIP.sessions.TipoFacade;
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
public class TipoController implements Serializable{

    @EJB
    private TipoFacade tipoFacade;
    private List<Tipo> listaTipo = null;
    private Tipo tipoActual;
    
    public TipoController() {

    }

    public List<Tipo> getListaTipo() {
        listaTipo = new ArrayList<>();
        listaTipo = getTipoFacade().findAll();
        return listaTipo;
    }
    public List<Tipo> getListaTipoConsulta() {
        return getTipoFacade().consultaTipo();
    }

    

    public TipoFacade getTipoFacade() {
        return tipoFacade;
    }

    public Tipo getTipoActual() {
        if (tipoActual == null) {
            tipoActual = new Tipo();
        }
        return tipoActual;
    }

    public void setTipoActual(Tipo tipoActual) {
        this.tipoActual = tipoActual;
    }


    private void recargarLista() {
        listaTipo = null;
    }

    public String prepareCreate() {
        tipoActual = new Tipo();
        return "adminCrearTipoComputador";
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

    public String addTipo() {
        try {
            getTipoFacade().create(tipoActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateTipo() {
        try {
            getTipoFacade().edit(tipoActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteTipo() {
        try {
            getTipoFacade().remove(tipoActual);
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
    
    public Tipo getTipo(java.lang.Integer id) {
        return getTipoFacade().find(id);
    }
    
    @FacesConverter(forClass = Tipo.class)
    public static class TipoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoController controller = (TipoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoController");
            return controller.getTipo(getKey(value));
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
            if (object instanceof Tipo) {
                Tipo o = (Tipo) object;
                return getStringKey(o.getIdTipo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Tipo.class.getName());
            }
        }
    }
}
