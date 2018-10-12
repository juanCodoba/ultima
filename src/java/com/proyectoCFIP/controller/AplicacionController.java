
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoCFIP.controller;

import com.proyectoCFIP.entities.Aplicacion;
import com.proyectoCFIP.sessions.AplicacionFacade;
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
public class AplicacionController implements Serializable{

    @EJB
    private AplicacionFacade aplicacionFacade;
    private List<Aplicacion> listaLicencia = null;
    private Aplicacion aplicacionActual;

    /**
     * Creates a new instance of LicenciaController
     */
    public AplicacionController() {
    }
   
    public List<Aplicacion> getListaAplicacion() {
       return getAplicacionFacade().findAll();
    }

    

    public AplicacionFacade getAplicacionFacade() {
        return aplicacionFacade;
    }

    public Aplicacion getAplicacionActual() {
        if (aplicacionActual == null) {
            aplicacionActual = new Aplicacion();
        }
        return aplicacionActual;
    }

    public void setAplicacionActual(Aplicacion aplicacionActual) {
        this.aplicacionActual = aplicacionActual;
    }


    private void recargarLista() {
        listaLicencia = null;
    }

    public String prepareCreate() {
        aplicacionActual = new Aplicacion();
        return "/Admin/configuracionesGenerales/adminCrearAplicacion";
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

    public String addAplicacion() {
        try {
            getAplicacionFacade().create(aplicacionActual);
            recargarLista();
            return "/User/paginaPrincipal";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
    public String updateAplicacion() {
        try {
            getAplicacionFacade().edit(aplicacionActual);
            recargarLista();
            return "";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteAplicacion() {
        try {
            getAplicacionFacade().remove(aplicacionActual);
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
    
    public Aplicacion getAplicacion(java.lang.Integer id) {
        return getAplicacionFacade().find(id);
    }
    
    
    @FacesConverter(forClass = Aplicacion.class)
    public static class AplicacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AplicacionController controller = (AplicacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "aplicacionController");
            return controller.getAplicacion(getKey(value));
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
            if (object instanceof Aplicacion) {
                Aplicacion o = (Aplicacion) object;
                return getStringKey(o.getIdAplicacion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Aplicacion.class.getName());
            }
        }

    }

}
