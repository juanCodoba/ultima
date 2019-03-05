/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.TipoMantenimiento;
import com.proyectoCFIP.sessions.TipoMantenimientoFacade;
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
public class TipoMantenimientoController {

    @EJB
    private TipoMantenimientoFacade tipoMantenimientoFacade;
    private List<TipoMantenimiento> listaTipoMantenimiento = null;
    private TipoMantenimiento tipoMantenimientoActual;
    
    public TipoMantenimientoController() {

    }

    public List<TipoMantenimiento> getListaComputador() {
        listaTipoMantenimiento = new ArrayList<>();
        listaTipoMantenimiento = getTipoMantenimientoFacade().findAll();
        return listaTipoMantenimiento;
    }

    public List<TipoMantenimiento> getListaTipoMantenimientoSelectOne() {
        return getTipoMantenimientoFacade().consultaTipoMantenimiento();
    }
    public List<TipoMantenimiento> getListaTipoMantenimientoFalloSelectOne() {
        return getTipoMantenimientoFacade().consultaTipoFalloMantenimiento();
    }
        public List<TipoMantenimiento> getListaTipoMantenimiento() {
        return getTipoMantenimientoFacade().findAll();
    }


    public TipoMantenimientoFacade getTipoMantenimientoFacade() {
        return tipoMantenimientoFacade;
    }

    public TipoMantenimiento getTipoMantenimientoActual() {
        if (tipoMantenimientoActual == null) {
            tipoMantenimientoActual = new TipoMantenimiento();
        }
        return tipoMantenimientoActual;
    }

    public void setTipoMantenimientoActual(TipoMantenimiento tipoMantenimientoActual) {
        this.tipoMantenimientoActual = tipoMantenimientoActual;
    }


    private void recargarLista() {
        listaTipoMantenimiento = null;
    }

    public String prepareCreate() {
        tipoMantenimientoActual = new TipoMantenimiento();
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

    public String addTipoMantenimiento() {
        try {
            getTipoMantenimientoFacade().create(tipoMantenimientoActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateTipoMantenimiento() {
        try {
            getTipoMantenimientoFacade().edit(tipoMantenimientoActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteTipoMantenimiento() {
        try {
            getTipoMantenimientoFacade().remove(tipoMantenimientoActual);
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
    
    public TipoMantenimiento getTipoMantenimiento(java.lang.Integer id) {
        return getTipoMantenimientoFacade().find(id);
    }
    
    @FacesConverter(forClass = TipoMantenimiento.class,value = "tipoMantenimientoConverter")
    public static class TipoMantenimientoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoMantenimientoController controller = (TipoMantenimientoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoMantenimientoController");
            return controller.getTipoMantenimiento(getKey(value));
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
            if (object instanceof TipoMantenimiento) {
                TipoMantenimiento o = (TipoMantenimiento) object;
                return getStringKey(o.getIdTipoMantenimiento());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoMantenimiento.class.getName());
            }
        }

    }
    
}
