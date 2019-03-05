/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Oportunidad;
import com.proyectoCFIP.sessions.OportunidadFacade;
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
public class OportunidadController implements Serializable{

    @EJB
    private OportunidadFacade oportunidadFacade;
    private List<Oportunidad> listaOportunidad = null;
    private Oportunidad oportunidadActual;
    
    public OportunidadController() {

    }

    public List<Oportunidad> getListaOportunidad() {
        listaOportunidad = new ArrayList<>();
        listaOportunidad = getOportunidadFacade().findAll();
        return listaOportunidad;
    }


    

    public OportunidadFacade getOportunidadFacade() {
        return oportunidadFacade;
    }

    public Oportunidad getOportunidadActual() {
        if (oportunidadActual == null) {
            oportunidadActual = new Oportunidad();
        }
        return oportunidadActual;
    }

    public void setOportunidadActual(Oportunidad oportunidadActual) {
        this.oportunidadActual = oportunidadActual;
    }


    private void recargarLista() {
        listaOportunidad = null;
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

    public String addOportunidad() {
        try {
            getOportunidadFacade().create(oportunidadActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateOportunidad() {
        try {
            getOportunidadFacade().edit(oportunidadActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteOportunidad() {
        try {
            getOportunidadFacade().remove(oportunidadActual);
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
    
    public Oportunidad getOportunidad(java.lang.Integer id) {
        return getOportunidadFacade().find(id);
    }
    
    @FacesConverter(forClass = Oportunidad.class,value="oportunidadConverter")
    public static class OportunidadControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OportunidadController controller = (OportunidadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "oportunidadController");
            return controller.getOportunidad(getKey(value));
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
            if (object instanceof Oportunidad) {
                Oportunidad o = (Oportunidad) object;
                return getStringKey(o.getIdOportunidad());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Oportunidad.class.getName());
            }
        }
    }
}
