/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.NivelHabilidad;
import com.proyectoCFIP.sessions.NivelHabilidadFacade;
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
public class NivelHabilidadController implements Serializable{

    @EJB
    private NivelHabilidadFacade nivelHabilidadFacade;
    private List<NivelHabilidad> listaNivelHabilidad = null;
    private NivelHabilidad nivelHabilidadActual;
    
    public NivelHabilidadController() {

    }

    public List<NivelHabilidad> getListaNivelHabilidad() {
        listaNivelHabilidad = new ArrayList<>();
        listaNivelHabilidad = getNivelHabilidadFacade().findAll();
        return listaNivelHabilidad;
    }


    

    public NivelHabilidadFacade getNivelHabilidadFacade() {
        return nivelHabilidadFacade;
    }

    public NivelHabilidad getNivelHabilidadActual() {
        if (nivelHabilidadActual == null) {
            nivelHabilidadActual = new NivelHabilidad();
        }
        return nivelHabilidadActual;
    }

    public void setNivelHabilidadActual(NivelHabilidad nivelHabilidadActual) {
        this.nivelHabilidadActual = nivelHabilidadActual;
    }


    private void recargarLista() {
        listaNivelHabilidad = null;
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

    public String addNivelHabilidad() {
        try {
            getNivelHabilidadFacade().create(nivelHabilidadActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateNivelHabilidad() {
        try {
            getNivelHabilidadFacade().edit(nivelHabilidadActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteNivelHabilidad() {
        try {
            getNivelHabilidadFacade().remove(nivelHabilidadActual);
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
    
    public NivelHabilidad getNivelHabilidad(java.lang.Integer id) {
        return getNivelHabilidadFacade().find(id);
    }
    
    @FacesConverter(forClass = NivelHabilidad.class,value="nivelHabilidadConverter")
    public static class NivelHabilidadControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NivelHabilidadController controller = (NivelHabilidadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "nivelHabilidadController");
            return controller.getNivelHabilidad(getKey(value));
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
            if (object instanceof NivelHabilidad) {
                NivelHabilidad o = (NivelHabilidad) object;
                return getStringKey(o.getIdNivelHabilidad());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + NivelHabilidad.class.getName());
            }
        }
    }
}
