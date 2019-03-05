/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.NivelCargo;
import com.proyectoCFIP.sessions.NivelCargoFacade;
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
public class NivelCargoController implements Serializable{

    @EJB
    private NivelCargoFacade nivelCargoFacade;
    private List<NivelCargo> listaNivelCargo = null;
    private NivelCargo nivelCargoActual;
    
    public NivelCargoController() {

    }

    public List<NivelCargo> getListaNivelCargo() {
        listaNivelCargo = new ArrayList<>();
        listaNivelCargo = getNivelCargoFacade().findAll();
        return listaNivelCargo;
    }


    

    public NivelCargoFacade getNivelCargoFacade() {
        return nivelCargoFacade;
    }

    public NivelCargo getNivelCargoActual() {
        if (nivelCargoActual == null) {
            nivelCargoActual = new NivelCargo();
        }
        return nivelCargoActual;
    }

    public void setNivelCargoActual(NivelCargo nivelCargoActual) {
        this.nivelCargoActual = nivelCargoActual;
    }


    private void recargarLista() {
        listaNivelCargo = null;
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

    public String addNivelCargo() {
        try {
            getNivelCargoFacade().create(nivelCargoActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateNivelCargo() {
        try {
            getNivelCargoFacade().edit(nivelCargoActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteNivelCargo() {
        try {
            getNivelCargoFacade().remove(nivelCargoActual);
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
    
    public NivelCargo getNivelCargo(java.lang.Integer id) {
        return getNivelCargoFacade().find(id);
    }
    
    @FacesConverter(forClass = NivelCargo.class,value="nivelCargoConverter")
    public static class NivelCargoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NivelCargoController controller = (NivelCargoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "nivelCargoController");
            return controller.getNivelCargo(getKey(value));
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
            if (object instanceof NivelCargo) {
                NivelCargo o = (NivelCargo) object;
                return getStringKey(o.getIdNivelCargo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + NivelCargo.class.getName());
            }
        }
    }
}
