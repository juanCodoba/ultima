/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.MaeTipoHerramienta;
import com.proyectoCFIP.sessions.MaeTipoHerramientaFacade;
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
public class TipoHerramientaController implements Serializable{

    @EJB
    private MaeTipoHerramientaFacade MaeTipoHerramientaFacade;
    private List<MaeTipoHerramienta> listaMaeTipoHerramienta = null;
    private MaeTipoHerramienta MaeTipoHerramientaActual;
    
    public TipoHerramientaController() {

    }

    public List<MaeTipoHerramienta> getListaMaeTipoHerramienta() {
        listaMaeTipoHerramienta = new ArrayList<>();
        listaMaeTipoHerramienta = getMaeTipoHerramientaFacade().findAll();
        return listaMaeTipoHerramienta;
    }


    

    public MaeTipoHerramientaFacade getMaeTipoHerramientaFacade() {
        return MaeTipoHerramientaFacade;
    }

    public MaeTipoHerramienta getMaeTipoHerramientaActual() {
        if (MaeTipoHerramientaActual == null) {
            MaeTipoHerramientaActual = new MaeTipoHerramienta();
        }
        return MaeTipoHerramientaActual;
    }

    public void setMaeTipoHerramientaActual(MaeTipoHerramienta MaeTipoHerramientaActual) {
        this.MaeTipoHerramientaActual = MaeTipoHerramientaActual;
    }


    private void recargarLista() {
        listaMaeTipoHerramienta = null;
    }

    public String prepareCreate() {
        MaeTipoHerramientaActual = new MaeTipoHerramienta();
        return "adminCrearMaeTipoHerramientaComputador";
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

    public String addMaeTipoHerramienta() {
        try {
            getMaeTipoHerramientaFacade().create(MaeTipoHerramientaActual);
            recargarLista();
            return "paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateMaeTipoHerramienta() {
        try {
            getMaeTipoHerramientaFacade().edit(MaeTipoHerramientaActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteMaeTipoHerramienta() {
        try {
            getMaeTipoHerramientaFacade().remove(MaeTipoHerramientaActual);
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
    
    public MaeTipoHerramienta getMaeTipoHerramienta(java.lang.Integer id) {
        return getMaeTipoHerramientaFacade().find(id);
    }
    
    @FacesConverter(forClass = MaeTipoHerramienta.class, value = "maeTipoHerrConverter")
    public static class MaeTipoHerramientaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoHerramientaController controller = (TipoHerramientaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoHerramientaController");
            return controller.getMaeTipoHerramienta(getKey(value));
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
            if (object instanceof MaeTipoHerramienta) {
                MaeTipoHerramienta o = (MaeTipoHerramienta) object;
                return getStringKey(o.getIdMaeTipoHerramienta());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MaeTipoHerramienta.class.getName());
            }
        }
    }
}
